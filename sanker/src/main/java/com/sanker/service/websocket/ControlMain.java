package com.sanker.service.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONObject;

import org.springframework.web.context.ContextLoader;

import com.sanker.entity.game.GameRoom;
import com.sanker.entity.game.Ledger;
import com.sanker.entity.player.Player;
import com.sanker.entity.player.PlayerGameInfo;
import com.sanker.param.Param;
import com.sanker.service.game.GameAreaNumService;
import com.sanker.service.game.GameRoomService;
import com.sanker.service.game.LedgerService;
import com.sanker.service.inform.MailService;
import com.sanker.service.logic.CalculateMultiple;
import com.sanker.service.logic.ControlRoom;
import com.sanker.service.logic.FenJie;
import com.sanker.service.logic.HuPaiLogic;
import com.sanker.service.logic.Logic;
import com.sanker.service.player.PlayerRelationService;
import com.sanker.service.player.PlayerService;
import com.sanker.service.utils.JSONHelper;
import com.sanker.weiXin.util.AdvancedUtil;
import com.sanker.weiXin.util.HeadImgUtil;
import com.sanker.weiXin.util.Sign;
import com.sanker.weiXin.util.Token_ticket_Thread;
import com.sanker.weiXin.util.Weixin_rechargeUtil;

/**
 * 控制
 * 
 * @author 滕洁
 * @date 2016-11-29
 */
@ServerEndpoint("/websocket/{playerId}")
public class ControlMain {

	// 链接中的用户
	private static final Map<String, ControlMain> connections = new LinkedHashMap<String, ControlMain>();

	// 断开链接的用户Id（非正常离开） ?
	//private static List<String> disconnectConnection = new ArrayList<String>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 用户打开链接
	 */
	@SuppressWarnings("static-access")
	@OnOpen
	public void onOpen(@PathParam("playerId") String playerId, Session session) {
		this.session = session;
		System.out.println(playerId);
		if (!this.connections.containsKey(playerId)) {

			connections.put(playerId, this);
			/**
			 * 检查player Id是否合法
			 */

			PlayerService playerService = (PlayerService) ControlRoom.getServiceBean("PlayerService");
			Player playerInfo = playerService.getPlayerById(playerId);
			if (playerInfo == null) {
				//非法
				Map<String, String> msg = new HashMap<String, String>();
				msg.put("title", "pId_check");
				msg.put("result", "fail");
				this.send_msg_judge(playerId, JSONHelper.toJson(msg));
				this.connections.remove(playerId);
				return;
			} else {
				//合法
				Map<String, String> msg = new HashMap<String, String>();
				msg.put("title", "pId_check");
				msg.put("result", "success");
				this.send_msg_judge(playerId, JSONHelper.toJson(msg));
			}

			// 修改状态
			playerService.updatePlayerStatus(playerId, 1);

			ControlRoom.playerSex.put(playerId, playerInfo.getSex() == '男' ? "m" : "w");
			Map<String, Object> playerInfoMap = new HashMap<String, Object>();
			playerInfoMap.put("title", "playerInfo");
			playerInfoMap.put("info", playerInfo);

			this.send_msg_judge(playerId, JSONHelper.toJson(playerInfoMap));

		}

	}

	/**
	 * 判断玩家是否存在于对局中
	 */
	public void is_inGame(String playerId) {
		// 如果当前用户存在于正在进行的牌局中，让用户进入房间
		GameRoomService gameRoomService = (GameRoomService) ControlRoom.getServiceBean("GameRoomService");
		String roomId = gameRoomService.isHavaRelation(playerId);

		if (roomId != null) {

			//判断controlroom是否含有roomId

			if (ControlRoom.allLogic.get(roomId) != null) {

				((PlayerService) ControlRoom.getServiceBean("PlayerService")).updatePlayerStatus(playerId, 2);
				this.reconnection(roomId, playerId, "reconnection");
			} else {

				//判断roomId是否存在于 controlroom中
				if (!ControlRoom.gamePlayerPosition.containsKey(roomId)) {

					gameRoomService.deleteGameRoomById(roomId);
					gameRoomService.deleteRelationByRoomId(roomId);
					((LedgerService) ControlRoom.getServiceBean("LedgerService")).deleteLedger(playerId);
					return;
				}

				((PlayerService) ControlRoom.getServiceBean("PlayerService")).updatePlayerStatus(playerId, 2);
				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("title", "haveRelation");
				msg.put("userList", this.getotherinfoList(roomId, playerId));
				msg.put("selfPosition", ControlRoom.getDirection(roomId, playerId));
				msg.put("rule", ControlRoom.roomRule.get(roomId));
				if (ControlRoom.roomRule.get(roomId).getType() == 0) {
					msg.put("ownerPlayerPosition", ControlRoom.getDirection(roomId, ControlRoom.roomRule.get(roomId).getHouseOwnerId()));
				}
				this.send_msg_judge(playerId, JSONHelper.toJson(msg));
			}
		} else {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("title", "check_inGame_f");
			this.send_msg_judge(playerId, JSONHelper.toJson(msg));
		}
	}

	/**
	 * 收到消息
	 */
	@OnMessage
	public void onMessage(@PathParam("playerId") String playerId, String message, Session session) {

		JSONObject jsonObject = JSONObject.fromObject(message);
		String index = jsonObject.getString("index");
		System.out.println("收到消息："+playerId+"=====》》》》》"+index);
		if (index.equals("addRoom")) {

			/**
			 * 先判断——是否已存在于房间中
			 */
			GameRoomService gameRoomService = (GameRoomService) ControlRoom.getServiceBean("GameRoomService");
			String is_inRoom = gameRoomService.isHavaRelation(playerId);

			if (is_inRoom != null) {

				return;
			}

			String roomId = jsonObject.getString("roomId");
			// 加入好友房间
			//判断roomId是否有效
			if (roomId != null && gameRoomService.getGameRoomById(roomId) != null) {
				// 如果当前房间中不存在该玩家
				if (!roomId.equals(gameRoomService.isHavaRelation(playerId))) {

					this.addRoom(jsonObject.getString("roomId"), playerId);
				}
			} else {
				Map<String, String> map = new HashMap<>();
				map.put("title", "addRoom");//为了前端判断是否有引导操作
				map.put("content", "n_r");
				this.send_msg_judge(playerId, JSONHelper.toJson(map));
			}
		} else if (index.equals("runGold")) {
			//System.out.println(jsonObject);
			// 加入金币对局
			// this.runGold(jsonObject.getString("gameArea"),
			// jsonObject.getInt("gameType"),
			// jsonObject.getInt("playerNum"),jsonObject.getInt("score"),playerId);
			if (jsonObject.getInt("gameType") == -1) {
				this.addAiGame(playerId, jsonObject.getInt("playerNum"), jsonObject.getString("gameArea"));
			} else {
				this.runGold(jsonObject.getString("gameArea"), jsonObject.getInt("gameType"), jsonObject.getInt("playerNum"), jsonObject.getInt("score"), playerId);
			}

		} else if (index.equals("createRoom")) {
			// 创建好友房间

			/*//System.out.println(jsonObject.toString());
			//System.out.println(jsonObject.getInt("gameNum") + "   " + jsonObject.getInt("topMultiple") + "  " + jsonObject.getString("gameArea"));*/
			this.createRoom(playerId, jsonObject.getString("gameArea"), jsonObject.getInt("gameType"), jsonObject.getInt("gameNum"), jsonObject.getInt("playerNum"), jsonObject.getInt("topMultiple"), jsonObject.getString("rule"));

		} else if (index.equals("chuPai")) {

			// 将出牌添加到步骤中
			ControlRoom.stepRecord.get(jsonObject.getString("roomId")).add(ControlRoom.getStepJson(jsonObject.getString("roomId"), playerId, jsonObject.getString("pai"), "chuPai"));

			// 删除caozuoNum
			this.deleteCaoZuo(jsonObject.getString("roomId"), playerId, true);

			// 出牌发送消息
			if (this.chuPai(playerId, jsonObject.getString("roomId"), jsonObject.getString("pai"))) {

			} else {
				// 判断是否有人胡

				// 没有牌，结束对局
				this.gameOver(jsonObject.getString("roomId"), null);
			}
			if (jsonObject.get("additional") != null) {
				this.sendAdditional(jsonObject.getString("roomId"), playerId, jsonObject.getString("additional"));
			}
		} else if (index.equals("caozuoChoose")) {
			// 玩家选择相应的操作
			//System.out.println("玩家选择的操作" + jsonObject);
			this.caoZuoChoose(playerId, jsonObject.getString("name"), jsonObject.getString("roomId"), jsonObject.getString("pai"));

		} else if (index.equals("nextGame")) {// 下一局

			this.clickNextRoom(jsonObject.getString("roomId"), jsonObject.getString("type"), playerId);

		} else if (index.equals("exitTable")) {// 退出房间

			// 清除controlroom数据，删除数据库中player_room_ralation数据
			this.playerExitRoom(jsonObject.getString("roomId"), playerId);
		} else if (index.equals("sendTalk")) {

			this.sendTalk(jsonObject.getString("roomId"), playerId, jsonObject.getString("type"), jsonObject.getString("name"));
		} else if (index.equals("tg")) {// 玩家选择托管 但是还是停留在table中

			this.tgHandle(jsonObject.getString("type"), playerId, jsonObject.getString("roomId"));
		} else if (index.equals("getHuHint")) {

			this.getHuHint(playerId, jsonObject.getString("roomId"));
		} else if (index.equals("changeTable")) {// 换桌
			GameRoom room = ControlRoom.roomRule.get(jsonObject.getString("roomId"));

			this.runGold(room.getGameArea(), room.getGameType(), room.getPlayerNum(), room.getBaseScore(), playerId);
		} else if (index.equals("saveImg")) {// 保存图片Test

			HeadImgUtil.generateImage(jsonObject.getString("imgStr"));
		} else if (index.equals("getLedger")) {//

			this.getLedger(playerId, jsonObject.getString("roomId"));
		} else if (index.equals("chooseQue")) {// 缺

			this.chooseQue(jsonObject.getString("roomId"), jsonObject.getString("paiType"), playerId);
		} else if (index.equals("additional")) {// 能胡时的附加操作 摆/听
			//System.out.println(jsonObject.getString("type") + "     additionalType");
			this.additional(playerId, jsonObject.getString("roomId"), jsonObject.getString("type"), jsonObject.getBoolean("flag"));
		} else if (index.equals("ipCity")) {// ip地址

			// //System.out.println(jsonObject.getString("city"));
			// 返回地区list
			PlayerService playerService = (PlayerService) ControlRoom.getServiceBean("PlayerService");
			List<String> gameAreaList = playerService.getGameAreaList(playerId, jsonObject.getString("city"));
			Map<String, Object> gameAreaMsg = new HashMap<String, Object>();
			gameAreaMsg.put("title", "gameAreaMsg");
			gameAreaMsg.put("areaList", gameAreaList);
			gameAreaMsg.put("type", jsonObject.getString("type"));
			this.send_msg_judge(playerId, JSONHelper.toJson(gameAreaMsg));

		} else if (index.equals("sendLike")) {
			//System.out.println("sendLike!");
			this.sendLike(playerId, jsonObject.getString("receiverId"), jsonObject.getString("senderName"));

		} else if (index.equals("receiveLike")) {// 领取好友点赞
			if (((PlayerService) ControlRoom.getServiceBean("PlayerService")).receiveLike(playerId)) {
				// 更新个人信息
				this.sendPlayerInfo(playerId, "receiveLike");
			}
		} else if (index.equals("exitMatching")) {
			/**
			 * 退出匹配 清空数据
			 */
			this.exitMatching(playerId);

		} else if (index.equals("socketTest")) {
			//System.out.println(jsonObject.get("str"));
		} else if (index.equals("getConfig")) {
			// 微信分享配置注入
			this.getJssdkConfigure(playerId, jsonObject.getString("url"));
		} else if (index.equals("getPlayerInfo")) {

			this.sendPlayerInfo(playerId, "playerInfo");
		} else if (index.equals("getOtherPlayerInfo")) {
			//System.out.println(playerId + "  " + jsonObject.getString("playerId") + "  " + jsonObject.getString("type"));
			this.sendOtherPlayerInfo(playerId, jsonObject.getString("playerId"), jsonObject.getString("type"));
		} else if (index.equals("swapPai")) {
			// 换三张
			System.out.println(jsonObject.toString());
			String[] paiStr = jsonObject.getString("values").split(",");
			int[] paiInt = new int[3];
			for (int i = 0; i < 3; i++) {
				paiInt[i] = Integer.valueOf(paiStr[i]);
			}
			this.swapPai(playerId, jsonObject.getString("roomId"), paiInt);

		} else if (index.equals("choosePiao")) {

			this.choosePiao(playerId, jsonObject.getString("roomId"), jsonObject.getString("value"));
		} else if (index.equals("gameOver")) {

			this.gameOver(jsonObject.getString("roomId"), playerId);
		} else if (index.equals("getGameArea")) {

		} else if (index.equals("heartbeatCheck")) {
			////System.out.println(jsonObject);
			Map<String, String> map = new HashMap<>();
			map.put("title", "heartbeatCheck");
			this.send_msg_judge(playerId, JSONHelper.toJson(map));

		} else if (index.equals("js_room")) {

			this.js_room(playerId, jsonObject.getString("roomId"), jsonObject.getString("type"), jsonObject.getString("chooseResult"));
		} else if (index.equals("pay")) {

			String type = jsonObject.getString("type");
			if (type.equals("gold")) {
				this.pay_gold(playerId, jsonObject.getString("signStr"));
			} else if (type.equals("diamond")) {
				this.pay_diamond(playerId, jsonObject.getString("signStr"), jsonObject.getString("ip"));
			}

		} else if (index.equals("check_inGame")) {

			this.is_inGame(playerId);
		} else if (index.equals("get_onLine")) {

			this.get_onLine(playerId);
		} else if (index.equals("invitation_onLine")) {
			//邀请在线好友

		} else {

			//System.out.println("otherIndex!");
		}

	}

	/**
	 * 获取在线玩家
	 */
	private void get_onLine(String playerId) {

		PlayerService playerService = (PlayerService) ControlRoom.getServiceBean("PlayerService");

		Map<String, Object> msg = new HashMap<>();
		msg.put("title", "get_onLine");
		msg.put("friend_list", playerService.get_friendListByStatus(playerId, 1));

		this.send_msg_judge(playerId, JSONHelper.toJson(msg));
	}

	/**
	 * 玩家充值砖石
	 */
	private void pay_diamond(String playerId, String signStr, String ip) {

		String diamond_sign = signStr.substring(signStr.length() - 1);
		String openId = ((PlayerService) ControlRoom.getServiceBean("PlayerService")).get_openId(playerId);

		Map<String, Object> recharge_money = Weixin_rechargeUtil.get_rechargeDiamond_money(diamond_sign);
		SortedMap<Object, Object> payMap = AdvancedUtil.wx_pay(openId, ip, Integer.valueOf(recharge_money.get("money_num").toString()));
		Map<String, Object> msgMap = new HashMap<>();
		msgMap.put("title", "msg_pay");
		if (payMap == null) {
			msgMap.put("flag", "success");
		} else {
			msgMap.put("flag", "fail");
			msgMap.put("config", payMap);
		}
		this.send_msg_judge(playerId, JSONHelper.toJson(payMap));
	}

	/**
	 * 玩家充值金豆
	 */
	private void pay_gold(String playerId, String signStr) {

		PlayerService playerService = (PlayerService) ControlRoom.getServiceBean("PlayerService");

		Map<String, Integer> gold_recharegeMap = Weixin_rechargeUtil.get_rechargeGold_money(signStr.substring(signStr.length() - 1));

		Map<String, Object> msgMap = new HashMap<>();
		msgMap.put("title", "pay_gold");
		msgMap.put("mapInfo", gold_recharegeMap);
		//判断玩家钻石数量
		Integer diamond_num = playerService.get_diamondNum(playerId);
		if (diamond_num >= gold_recharegeMap.get("diamond_num")) {
			playerService.update_diamond_gold("gold", playerId, gold_recharegeMap.get("gold_num"));
			playerService.update_diamond_gold("diamond", playerId, -gold_recharegeMap.get("diamond_num"));
			msgMap.put("resultStr", "success");
		} else {
			msgMap.put("resultStr", "fail");
		}
		this.send_msg_judge(playerId, JSONHelper.toJson(msgMap));
	}

	/**
	 * 玩家对飘的选择
	 */
	@SuppressWarnings("static-access")
	private void choosePiao(String playerId, String roomId, String value) {
		ControlRoom.piaoPlayer.get(roomId).put(playerId, value);
		Integer choosePiaoNum = ControlRoom.piaoPlayer.get(roomId).size();
		if (choosePiaoNum == ControlRoom.roomRule.get(roomId).getPlayerNum()) {
			// 选飘完成
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("title", "piaoComplete");
			Map<String, String> valueMap = new HashMap<>();
			for (Entry<String, String> entry : ControlRoom.piaoPlayer.get(roomId).entrySet()) {
				valueMap.put(ControlRoom.getDirection(roomId, entry.getKey()), entry.getValue());
			}
			msg.put("value", valueMap);
			// 发送消息
			for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
				this.send_msg_judge(str, JSONHelper.toJson(msg));
			}
			/**
			 * 庄家出牌
			 */
			String playingId = ControlRoom.allLogic.get(roomId).playingId;
			if (ControlRoom.roomRule.get(roomId).getRule().contains(Param.SWAP_3)) {
				ControlRoom.allLogic.get(roomId).lastPai = ControlRoom.allLogic.get(roomId).usermj.get(playingId).get(0);
			}
			if (this.connections.containsKey(playingId)) {
				// 开始出牌
				this.mopaiCaozuo(playingId, roomId, ControlRoom.allLogic.get(roomId).lastPai);
			}
		}
	}

	/**
	 * 玩家选择换三张
	 */
	@SuppressWarnings("static-access")
	private void swapPai(String playerId, String roomId, int[] index) {

		if (!ControlRoom.swapArray.get(roomId).containsKey(playerId)) {

			ControlRoom.swapArray.get(roomId).put(playerId, index);
			if (ControlRoom.swapArray.get(roomId).size() == ControlRoom.roomRule.get(roomId).getPlayerNum()) {
				// 如果所有玩家都完成换三张的选择 进行换三张操作
				/**
				 * 随机0-2 0：顺时针    1：逆时针     2：对家      注：只有对家玩家数为4时才有对家换牌
				 * 
				 * 获取随机数
				 */
				int randomNum = 0;
				if (ControlRoom.roomRule.get(roomId).getPlayerNum() == 4) {
					randomNum = (int) (Math.random() * 3);
				} else {
					randomNum = (int) (Math.random() * 2);
				}
				Map<String, List<String>> swapMap = ControlRoom.allLogic.get(roomId).swap3(randomNum, ControlRoom.swapArray.get(roomId));
				
				for (Entry<String, List<String>> entry : swapMap.entrySet()) {
					Map<String, Object> swapMsg = new HashMap<>();
					swapMsg.put("title", "swapDone");
					swapMsg.put("swapType", randomNum);
					swapMsg.put("swapPaiList", entry.getValue());
					swapMsg.put("paiList", ControlRoom.allLogic.get(roomId).usermj.get(entry.getKey()));
					this.send_msg_judge(entry.getKey(), JSONHelper.toJson(swapMsg));
				}
				/**
				 * 判断是否需要选缺
				 */
				if (ControlRoom.roomRule.get(roomId).getRule().contains(Param.QUE_H)) {
					Map<String, String> mapQue = new HashMap<String, String>();
					mapQue.put("title", "chooseQue");
					for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
						mapQue.put("quePai", this.recommend_que(roomId, str));
						this.send_msg_judge(str, JSONHelper.toJson(mapQue));
					}
				} else {

					// 判断是否能选飘
					if (ControlRoom.roomRule.get(roomId).getRule().contains(Param.PIAO_H)) {

						ControlRoom.piaoPlayer.put(roomId, new HashMap<String, String>());

						Map<String, String> mapPiao = new HashMap<>();
						mapPiao.put("title", "choosePiao");

						for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
							this.send_msg_judge(str, JSONHelper.toJson(mapPiao));
						}
					} else {
						String playingId = ControlRoom.allLogic.get(roomId).playingId;
						if (ControlRoom.roomRule.get(roomId).getRule().contains(Param.SWAP_3)) {
							ControlRoom.allLogic.get(roomId).lastPai = ControlRoom.allLogic.get(roomId).usermj.get(playingId).get(0);
						}
						// 开始出牌
						if (this.connections.containsKey(playingId)) {
							// 开始出牌
							this.mopaiCaozuo(playingId, roomId, ControlRoom.allLogic.get(roomId).lastPai);
						}
					}
				}
			}
		}

	}

	/**
	 * 将玩家选择摆/听的消息发送给其他玩家
	 */
	private void sendAdditional(String roomId, String playerId, String title) {
		if (ControlRoom.playerAdditional.get(roomId) == null) {
			ControlRoom.playerAdditional.put(roomId, new HashMap<String, String>());
			ControlRoom.playerAdditional.get(roomId).put(playerId, title);
		} else {
			if (ControlRoom.playerAdditional.get(roomId).get(playerId) == null) {
				ControlRoom.playerAdditional.get(roomId).put(playerId, title);
			} else {
				ControlRoom.playerAdditional.get(roomId).put(playerId, ControlRoom.playerAdditional.get(roomId).get(playerId));
			}
		}
		if (title.equals("Bai")) {
			Map<String, Object> baiHint = new HashMap<String, Object>();
			baiHint.put("title", "baiHint");
			baiHint.put("playerId", playerId);
			baiHint.put("position", ControlRoom.getDirection(roomId, playerId));
			baiHint.put("huList", this.canHu(playerId, roomId));
			baiHint.put("paiList", ControlRoom.allLogic.get(roomId).usermj.get(playerId));
			baiHint.put("sex", ControlRoom.playerSex.get(playerId));
			//向player_room_info中添加canhulist
			ControlRoom.player_room_info.get(roomId).get(playerId).put("canHuList", baiHint.get("huList"));
			ControlRoom.player_room_info.get(roomId).get(playerId).put("paiList", baiHint.get("paiList"));
			String map_str = JSONHelper.toJson(baiHint);
			for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
				this.send_msg_judge(str, map_str);
			}
		} else if (title.equals("Ting")) {
			Map<String, Object> tingHint = new HashMap<String, Object>();
			tingHint.put("title", "tingHint");
			tingHint.put("playerId", playerId);
			tingHint.put("sex", ControlRoom.playerSex.get(playerId));
			tingHint.put("position", ControlRoom.getDirection(roomId, playerId));
			String map_Str = JSONHelper.toJson(tingHint);
			for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
				this.send_msg_judge(str, map_Str);
			}
		}

	}

	/**
	 * 退出匹配 清空数据
	 */
	private void exitMatching(String playerId) {

		String roomId = this.getRoomIdByPlayer(playerId);
		if (!"".equals(roomId)) {
			ControlRoom.gamePlayerPosition.get(roomId).remove(ControlRoom.getDirection(roomId, playerId));
			if (ControlRoom.gamePlayerPosition.get(roomId).size() == 0) {
				// 房间中所有的玩家都匹配失败
				////System.out.println("是否需要移除 ControlRoom 中的数据？");
				this.clearData(roomId);
			}
		}

	}

	/**
	 * 清除对局所有数据
	 */
	private void clearData(String roomId) {

		// 删除房间
		((GameRoomService) ControlRoom.getServiceBean("GameRoomService")).deleteGameRoomById(roomId);

		ControlRoom.allLogic.remove(roomId);
		ControlRoom.bankerMap.remove(roomId);
		ControlRoom.this_banker.remove(roomId);
		ControlRoom.benJin.remove(roomId);
		ControlRoom.caoZuoNum.remove(roomId);
		ControlRoom.friendGameNum.remove(roomId);
		ControlRoom.friendsScore.remove(roomId);
		ControlRoom.gamePlayerPosition.remove(roomId);
		ControlRoom.gangType.remove(roomId);
		//ControlRoom.goldRoom.remove(roomId);
		ControlRoom.huPlayer.remove(roomId);
		ControlRoom.nextRoom.remove(roomId);
		ControlRoom.piaoPlayer.remove(roomId);
		ControlRoom.playerAdditional.remove(roomId);
		ControlRoom.queChoosed.remove(roomId);
		ControlRoom.roomRule.remove(roomId);
		ControlRoom.stepRecord.remove(roomId);
		ControlRoom.swapArray.remove(roomId);
		ControlRoom.js_roomPlayer.remove(roomId);
		ControlRoom.player_room_info.remove(roomId);

	}

	/**
	 * 更新玩家游戏数据后，重新发送玩家游戏数据
	 */
	private void sendPlayerInfo(String playerId, String type) {
		Player playerInfo = getPlayerInfo(playerId);
		Map<String, Object> playerInfoMap = new HashMap<String, Object>();
		playerInfoMap.put("title", "playerInfo");
		playerInfoMap.put("type", type);
		playerInfoMap.put("info", playerInfo);

		this.send_msg_judge(playerId, JSONHelper.toJson(playerInfoMap));
	}

	/**
	 * 发送其他玩家游戏数据
	 */
	private void sendOtherPlayerInfo(String playerId, String otherPlayerId, String type) {
		////System.out.println(playerId + "    " + type + "   " + otherPlayerId);
		PlayerService playerService = (PlayerService) ControlRoom.getServiceBean("PlayerService");
		Player playerInfo = playerService.getPlayerById(otherPlayerId);
		Map<String, Object> playerInfoMap = new HashMap<String, Object>();
		playerInfoMap.put("title", "otherPlayerInfo");
		playerInfoMap.put("info", playerInfo);
		playerInfoMap.put("type", type);
		if (type.equals("getInfo")) {
			playerInfoMap.put("playerGameInfo", playerService.getPlayerGameInfo(otherPlayerId));
		}
		this.send_msg_judge(playerId, JSONHelper.toJson(playerInfoMap));
	}

	/**
	 * 点赞
	 */
	private void sendLike(String senderId, String receiverId, String senderName) {
		MailService mailService = (MailService) ControlRoom.getServiceBean("MailService");
		String content = "\t\t" + senderName + "给您点赞了。赠送您金豆x200、魅力值x1。快去回赠他吧。";
		Map<String, Object> likeMsg = new HashMap<String, Object>();
		likeMsg.put("title", "sendLike");
		likeMsg.put("receiverId", receiverId);

		if (mailService.addMail(senderId, senderName, receiverId, "好友点赞", content, "like")) {
			// 修改点赞状态
			((PlayerRelationService) ControlRoom.getServiceBean("PlayerRelationService")).updateSendLikeFlag(senderId, receiverId);
			// 判断接收人是否在线 在线则推送邮件
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "havaNewMail");
			this.send_msg_judge(receiverId, JSONHelper.toJson(map));
			likeMsg.put("flag", "t");
		} else {
			likeMsg.put("flag", "f");
		}
	}

	/**
	 * additional
	 */
	private void additional(String playerId, String roomId, String type, boolean flag) {
		if (ControlRoom.playerAdditional.get(roomId) == null) {
			ControlRoom.playerAdditional.put(roomId, new HashMap<String, String>());
		}

		////System.out.println(playerId + "  " + roomId + "  " + type + "  " + flag);
		if (flag) {
			if (ControlRoom.playerAdditional.get(roomId).containsKey(playerId)) {
				ControlRoom.playerAdditional.get(roomId).put(playerId, ControlRoom.playerAdditional.get(roomId).get(playerId) + type);
			} else {
				ControlRoom.playerAdditional.get(roomId).put(playerId, type);
			}
		} else {
			// 截取字符串
			String str = ControlRoom.playerAdditional.get(roomId).get(playerId);
			ControlRoom.playerAdditional.get(roomId).put(playerId, str.replace(type, ""));
		}
	}

	/**
	 * 选缺
	 */
	@SuppressWarnings("static-access")
	private void chooseQue(String roomId, String paiType, String playerId) {
		ControlRoom.quePaiMap.put(playerId, paiType);

		String position = ControlRoom.getDirection(roomId, playerId);

		// 对牌进行重排
		List<String> paiList = ControlRoom.allLogic.get(roomId).sortPai(ControlRoom.allLogic.get(roomId).usermj.get(playerId), playerId);
		ControlRoom.allLogic.get(roomId).usermj.get(playerId).clear();
		ControlRoom.allLogic.get(roomId).usermj.get(playerId).addAll(paiList);
		if (ControlRoom.queChoosed.containsKey(roomId)) {

			if (!ControlRoom.queChoosed.get(roomId).containsKey(position)) {
				ControlRoom.queChoosed.get(roomId).put(position, paiType);
			}
		} else {
			ControlRoom.queChoosed.put(roomId, new HashMap<String, String>());
			ControlRoom.queChoosed.get(roomId).put(position, paiType);
		}

		// 判断是否牌局中所有的玩家都完成选缺
		if (ControlRoom.queChoosed.get(roomId).size() == ControlRoom.roomRule.get(roomId).getPlayerNum()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "queComplete");
			map.put("queType", ControlRoom.queChoosed.get(roomId));

			for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()){
				map.put("paiList", ControlRoom.allLogic.get(roomId).usermj.get(str));
				this.send_msg_judge(str,JSONHelper.toJson(map));
			}
			// 判断是否能选飘
			if (ControlRoom.roomRule.get(roomId).getRule().contains(Param.PIAO_H)) {

				ControlRoom.piaoPlayer.put(roomId, new HashMap<String, String>());

				Map<String, Object> mapPiao = new HashMap<>();
				mapPiao.put("title", "choosePiao");

				for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
					mapPiao.put("paiList", ControlRoom.allLogic.get(roomId).usermj.get(str));
					this.send_msg_judge(str, JSONHelper.toJson(mapPiao));
				}
			} else {
				String playingId = ControlRoom.allLogic.get(roomId).playingId;
				if (ControlRoom.roomRule.get(roomId).getRule().contains(Param.SWAP_3)) {
					ControlRoom.allLogic.get(roomId).lastPai = ControlRoom.allLogic.get(roomId).usermj.get(playingId).get(0);
				}
				// 开始出牌
				if (this.connections.containsKey(playingId)) {
					// 开始出牌
					this.mopaiCaozuo(playingId, roomId, ControlRoom.allLogic.get(roomId).lastPai);
				}
			}

			/*
			 * String playingId = ControlRoom.allLogic.get(roomId).playingId; if
			 * (
			 * ControlRoom.roomRule.get(roomId).getRule().contains(Param.SWAP_3)
			 * ) { ControlRoom.allLogic.get(roomId).lastPai =
			 * ControlRoom.allLogic.get(roomId).usermj.get(playingId).get(0); }
			 * // 开始出牌 this.mopaiCaozuo(playingId, roomId,
			 * ControlRoom.allLogic.get(roomId).lastPai);
			 */
		}

	}

	/**
	 * 获取流水
	 */
	private void getLedger(String playerId, String roomId) {

		LedgerService ledgerService = (LedgerService) ControlRoom.getServiceBean("LedgerService");

		/**
		 * 判断是好友房还是其他房 如果是好友房
		 */
		Map<String, Object> ledgerMsg = new HashMap<String, Object>();
		ledgerMsg.put("title", "getLedgers");
		for (Map.Entry<String, String> entry : ControlRoom.gamePlayerPosition.get(roomId).entrySet()) {

			if (ControlRoom.roomRule.get(roomId).getType() == 0) {// 好友房
				// 查询好友房单局
				if (ControlRoom.friendGameNum.get(roomId) < ControlRoom.roomRule.get(roomId).getPlayerNum()) {
					if (entry.getValue().equals(playerId)) {
						ledgerMsg.put(entry.getKey(), ledgerService.getListByPlayer(playerId, ControlRoom.friendGameNum.get(roomId)));
					} else {
						ledgerMsg.put(entry.getKey(), ledgerService.getLedgerSumByPlayer(playerId, ControlRoom.friendGameNum.get(roomId)));
					}
					// 查询整局
				} else {

				}
			} else if (ControlRoom.roomRule.get(roomId).getType() == 1) {// 金币场
				if (entry.getValue().equals(playerId)) {
					ledgerMsg.put(entry.getKey(), ledgerService.getListByPlayer(playerId));
				} else {
					ledgerMsg.put(entry.getKey(), ledgerService.getLedgerSumByPlayer(playerId));
				}

			}

		}

	}

	/**
	 * 进入机器人对局
	 */
	private void addAiGame(String playerId, Integer playerNum, String gameArea) {
		/**
		 * 创建特殊房间
		 */
		GameRoom room = new GameRoom();
		room.setBaseScore(100);
		room.setGameArea(gameArea);
		room.setGameType(0);// 血战/血流
		room.setType(1);// 好友/金币
		room.setPlayerNum(playerNum);
		room.setRule("ai");
		room.setId(playerId);

		ControlRoom.playerSex.put("101", "m");
		ControlRoom.playerSex.put("102", "w");
		ControlRoom.playerSex.put("103", "m");
		ControlRoom.roomRule.put(room.getId(), room);
		ControlRoom.gamePlayerPosition.put(playerId, new HashMap<String, String>());
		this.addGamePosition(room.getId(), playerId);
		ControlRoom.queChoosed.put(playerId, new HashMap<String, String>());
		for (int i = 0; i < playerNum - 1; i++) {
			this.addGamePosition(room.getId(), (101 + i) + "");
			// 如果是缺的地域
			if (gameArea.equals("chengDu") || gameArea.equals("guangAn") || gameArea.equals("yiBin") || gameArea.equals("luZhou") || gameArea.equals("deYang")) {
				String paiType = i == 0 ? "T" : i == 1 ? "D" : "W";
				ControlRoom.queChoosed.get(playerId).put(ControlRoom.getDirection(playerId, "" + (101 + i)), paiType);
			}
		}
		// this.reconnection(room.getId(), playerId, "haveRelation");
		// this.roomFaPai(room.getId());
		this.addRoom(playerId, playerId);

	}

	/**
	 * 托管与取消托管
	 */
	private void tgHandle(String type, String playerId, String roomId) {

		if (type.equals("confirmTg")) {

			ControlRoom.player_room_info.get(roomId).get(playerId).put("tg", "y");
		} else {

			ControlRoom.player_room_info.get(roomId).get(playerId).remove("tg");
		}

		Map<String, Object> tgMap = new HashMap<String, Object>();
		tgMap.put("title", "otherTg");
		tgMap.put("type", type);
		tgMap.put("position", ControlRoom.getDirection(roomId, playerId));
		String map_Str = JSONHelper.toJson(tgMap);
		for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {

			if (!str.equals(playerId)) {
				this.send_msg_judge(str, map_Str);
			}

		}

	}

	/**
	 * 交流
	 */
	private void sendTalk(String roomId, String playerId, String type, String name) {
		Map<String, Object> sendTalk = new HashMap<String, Object>();
		sendTalk.put("title", "sendTalk");
		sendTalk.put("type", type);
		sendTalk.put("name", name);
		sendTalk.put("sex", ControlRoom.playerSex.get(playerId));
		sendTalk.put("position", ControlRoom.getDirection(roomId, playerId));
		String map_Str = JSONHelper.toJson(sendTalk);
		for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
			if (!str.equals(playerId)) {
				this.send_msg_judge(str, map_Str);
			}
		}
	}

	/**
	 * 玩家退出房间
	 * 
	 * @return
	 */
	private void playerExitRoom(String roomId, String playerId) {

		GameRoom room = ControlRoom.roomRule.get(roomId);

		// 发送给房间中的其他玩家
		Map<String, Object> exitRoom = new HashMap<String, Object>();
		exitRoom.put("title", "exitRoom");
		exitRoom.put("position", ControlRoom.getDirection(roomId, playerId));
		exitRoom.put("playerId", playerId);

		String map_Str = JSONHelper.toJson(exitRoom);
		for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
			if (!str.equals(playerId))
				this.send_msg_judge(str, map_Str);
		}

		if (room.getType() == 0 && playerId.equals(room.getHouseOwnerId())) {
			this.js_room_success(roomId);
		} else {
			ControlRoom.gamePlayerPosition.get(roomId).remove(ControlRoom.getDirection(roomId, playerId));
			((GameRoomService) ControlRoom.getServiceBean("GameRoomService")).deleteRelationByPlayerId(playerId, roomId);
			if (ControlRoom.gamePlayerPosition.get(roomId).size() == 0) {
				for (String str : ControlRoom.allLogic.get(roomId).usermj.keySet()) {
					// 删除流水
					((LedgerService) ControlRoom.getServiceBean("LedgerService")).deleteLedger(str);
					if (ControlRoom.quePaiMap.containsKey(str)) {
						ControlRoom.quePaiMap.remove(str);
					}
				}

				this.clearData(roomId);
			}
		}

	}

	/**
	 * 玩家点击下一局
	 * 清空数据
	 */
	private void clickNextRoom(String roomId, String gameType, String playerId) {

		List<String> userList = new ArrayList<>(ControlRoom.gamePlayerPosition.get(roomId).values());

		Map<String, Object> ready_msg = new HashMap<>();
		ready_msg.put("title", "ready_msg");
		ready_msg.put("position", ControlRoom.getDirection(roomId, playerId));
		String msg_Str = JSONHelper.toJson(ready_msg);
		if (!ControlRoom.roomRule.get(roomId).getRule().equals("ai")) {
			for (String str : userList) {
				if (!playerId.equals(str))
					this.send_msg_judge(str, msg_Str);
			}
		}
		if (ControlRoom.nextRoom.containsKey(roomId)) {

			if (!ControlRoom.nextRoom.get(roomId).contains(playerId)) {
				ControlRoom.nextRoom.get(roomId).add(playerId);
			}

			if (ControlRoom.nextRoom.get(roomId).size() == ControlRoom.gamePlayerPosition.get(roomId).size()) {
				if (gameType.equals("friends")) {
					ControlRoom.allLogic.remove(roomId);
					for (String str : userList) {
						ControlRoom.quePaiMap.remove(str);
						//ControlRoom.playerMultiple.remove(str);
					}
					ControlRoom.gangType.remove(roomId);
					ControlRoom.huPlayer.remove(roomId);
					ControlRoom.benJin.remove(roomId);
					ControlRoom.caoZuoNum.remove(roomId);
					if (ControlRoom.roomRule.get(roomId).getGameNum() == ControlRoom.friendGameNum.get(roomId)) {
						ControlRoom.roomRule.remove(roomId);
						ControlRoom.gamePlayerPosition.remove(roomId);
						ControlRoom.friendRoom.remove(roomId);
						ControlRoom.friendGameNum.remove(roomId);

					} else {
						if (ControlRoom.gamePlayerPosition.get(roomId).size() == ControlRoom.roomRule.get(roomId).getPlayerNum()) {
							/**
							 * 选庄 第一位赢的玩家为庄， 如果第一位是一炮多响 则点炮的玩家为庄
							 */

							// 好友场中，还有对局没有完成
							ControlRoom.friendGameNum.put(roomId, ControlRoom.friendGameNum.get(roomId) + 1);
							this.roomFaPai(roomId);
						}
					}
				} else if (gameType.equals("gold")) {
					ControlRoom.allLogic.remove(roomId);
					for (String str : userList) {
						ControlRoom.quePaiMap.remove(str);
						//ControlRoom.playerMultiple.remove(str);
					}
					ControlRoom.gangType.remove(roomId);
					ControlRoom.huPlayer.remove(roomId);
					ControlRoom.benJin.remove(roomId);
					ControlRoom.caoZuoNum.remove(roomId);
					this.roomFaPai(roomId);
					/*if (ControlRoom.nextRoom.get(roomId).size() == ControlRoom.roomRule.get(roomId).getPlayerNum()) {
					}*/
				}
				ControlRoom.nextRoom.remove(roomId);
			}

		} else {
			if (ControlRoom.roomRule.get(roomId).getRule().equals("ai")) {
				this.roomFaPai(roomId);
			} else {
				ControlRoom.nextRoom.put(roomId, new ArrayList<String>());
				ControlRoom.nextRoom.get(roomId).add(playerId);
			}
		}

	}

	/**
	 * 发牌前初始化数据
	 */
	private void initData(String roomId) {
		ControlRoom.caoZuoNum.put(roomId, null);
		ControlRoom.huPlayer.put(roomId, new ArrayList<String>());
		ControlRoom.gangType.put(roomId, new HashMap<String, Map<String, String>>());
		/**
		 * 创建房间 初始化数据
		 */
		if (ControlRoom.caoZuoNum.get(roomId) == null) {
			ControlRoom.caoZuoNum.put(roomId, new HashMap<String, List<String>>());
			ControlRoom.caoZuoNum.get(roomId).put("canGang", new ArrayList<String>());
			ControlRoom.caoZuoNum.get(roomId).put("canPeng", new ArrayList<String>());
			ControlRoom.caoZuoNum.get(roomId).put("canHu", new ArrayList<String>());
			ControlRoom.caoZuoNum.get(roomId).put("gang", new ArrayList<String>());
			ControlRoom.caoZuoNum.get(roomId).put("peng", new ArrayList<String>());
			ControlRoom.caoZuoNum.get(roomId).put("hu", new ArrayList<String>());
		}
		/*************************************************************/
		/**
		 * 判断是否要打缺
		 */
		//		if(ControlRoom.roomRule.get(roomId).getRule().contains(Param.QUE_H)){xxx

		for (String playerId : ControlRoom.gamePlayerPosition.get(roomId).values()) {
			ControlRoom.gangType.get(roomId).put(playerId, new HashMap<String, String>());
			ControlRoom.quePaiMap.put(playerId, "");
		}

		//		}

	}

	/**
	 * 创建好友房间
	 */
	private void createRoom(String playerId, String gameArea, Integer gameType, Integer gameNum, Integer playerNum, Integer topMultiple, String rule) {
		GameRoomService room_service = (GameRoomService) ControlRoom.getServiceBean("GameRoomService");
		GameRoom gameRoom = room_service.createRoom(0, playerId, gameType, gameNum, playerNum, gameArea, 1, rule, topMultiple);
		String roomId = gameRoom.getId();

		gameRoom = room_service.getGameRoomById(roomId);

		/*************************************************************/
		ControlRoom.friendRoom.add(roomId);
		ControlRoom.roomRule.put(roomId, gameRoom);
		ControlRoom.gamePlayerPosition.put(roomId, new HashMap<String, String>());

		ControlRoom.js_roomPlayer.put(roomId, new LinkedHashMap<String, String>());
		this.addGamePosition(roomId, playerId);
		((PlayerService) ControlRoom.getServiceBean("PlayerService")).updatePlayerStatus(playerId, 2);
		// 创建关系
		((GameRoomService) ContextLoader.getCurrentWebApplicationContext().getBean("GameRoomService")).createRelation(playerId, roomId);
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("title", "createRoom");
		msg.put("type", "friend");
		msg.put("rule", gameRoom);
		msg.put("selfPosition", ControlRoom.getDirection(roomId, playerId));
		this.send_msg_judge(playerId, JSONHelper.toJson(msg));
	}

	/**
	 * 加入好友房间
	 */
	private void addRoom(String roomId, String playerId) {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("title", "addRoom");
		msg.put("ownerPlayerPosition", ControlRoom.getDirection(roomId, ControlRoom.roomRule.get(roomId).getHouseOwnerId()));
		Integer playerNum = ControlRoom.roomRule.get(roomId).getPlayerNum();
		if (ControlRoom.gamePlayerPosition.get(roomId).size() < playerNum && !ControlRoom.gamePlayerPosition.get(roomId).containsValue(playerId)) {
			this.addGamePosition(roomId, playerId);
			// 创建关系
			((GameRoomService) ContextLoader.getCurrentWebApplicationContext().getBean("GameRoomService")).createRelation(playerId, roomId);
			// 发送进入房间的消息 并且发送房间中已存在的玩家消息
			msg.put("content", "t");
			msg.put("userList", this.getotherinfoList(roomId, playerId));
			msg.put("selfPosition", ControlRoom.getDirection(roomId, playerId));
			msg.put("rule", ControlRoom.roomRule.get(roomId));
		} else {
			// 如果当前用户已经在该牌局中
			if (ControlRoom.gamePlayerPosition.get(roomId).containsValue(playerId)) {
				msg.put("title", "addRoom");
				msg.put("content", "t");
				msg.put("userList", this.getotherinfoList(roomId, playerId));
				msg.put("selfPosition", ControlRoom.getDirection(roomId, playerId));
				msg.put("rule", ControlRoom.roomRule.get(roomId));
			} else
				msg.put("content", "f");
		}
		this.send_msg_judge(playerId, JSONHelper.toJson(msg));
		if (msg.get("content").equals("t")) {
			((PlayerService) ControlRoom.getServiceBean("PlayerService")).updatePlayerStatus(playerId, 2);
			// 提醒该房间中的所有玩家有新玩家加入 相对位置、玩家信息
			List<String> playerIdList = new ArrayList<String>(ControlRoom.gamePlayerPosition.get(roomId).values());
			Map<String, Object> hint = new HashMap<String, Object>();
			hint.put("title", "playerAdd");
			hint.put("position", ControlRoom.getDirection(roomId, playerId));
			hint.put("addPlayerId", this.getPlayerInfo(playerId));
			//hint.put("playerNum", ControlRoom.roomRule.get(roomId).getPlayerNum());
			String map_Str = JSONHelper.toJson(hint);
			for (String string : playerIdList) {
				// 针对非掉线 和 单击场做出甄别
				this.send_msg_judge(string, map_Str);

			}
			// 当好友房间中人数达到 该房间的规定人数
			if (ControlRoom.gamePlayerPosition.get(roomId).size() == playerNum) {
				this.roomFaPai(roomId);
			}

		}
	}

	/**
	 * 加入金币对局
	 */
	private void runGold(String gameArea, Integer gameType, Integer playerNum, Integer score, String playerId) {

		/**
		 * 获取玩家gold
		 */
		Integer gold = ((PlayerService) ControlRoom.getServiceBean("PlayerService")).getGoldByPlayerId(playerId);
		GameRoom room = new GameRoom();

		/**
		 * 2017-01-21 00:27:58 加入金币数匹配以及底分匹配
		 */
		if (ControlRoom.goldRoom.get(gameArea) == null || ControlRoom.goldRoom.get(gameArea).size() == 0) {
			// 地域为空
			ControlRoom.goldRoom.put(gameArea, new HashMap<Integer, Map<Integer, List<String>>>());
			ControlRoom.goldRoom.get(gameArea).put(gameType, new HashMap<Integer, List<String>>());
			ControlRoom.goldRoom.get(gameArea).get(gameType).put(score, new ArrayList<String>());

			room = ((GameRoomService) ContextLoader.getCurrentWebApplicationContext().getBean("GameRoomService")).createRoom(1, null, gameType, null, playerNum, gameArea, score, "gold", 0);

			// 设置匹配区间 键——>获取玩家gold
			room.setGoldInterval(gold);
			ControlRoom.roomRule.put(room.getId(), room);
			// 添加到controlRoom
			ControlRoom.goldRoom.get(gameArea).get(gameType).get(score).add(room.getId());
			ControlRoom.gamePlayerPosition.put(room.getId(), new HashMap<String, String>());

			// 将玩家添加到房间中
			this.addGamePosition(room.getId(), playerId);
		} else if (ControlRoom.goldRoom.get(gameArea).get(gameType) == null || ControlRoom.goldRoom.get(gameArea).get(gameType).size() == 0) {
			// 玩法为空
			ControlRoom.goldRoom.get(gameArea).put(gameType, new HashMap<Integer, List<String>>());
			ControlRoom.goldRoom.get(gameArea).get(gameType).put(score, new ArrayList<String>());

			room = ((GameRoomService) ContextLoader.getCurrentWebApplicationContext().getBean("GameRoomService")).createRoom(1, null, gameType, null, playerNum, gameArea, score, "gold", 0);

			// 设置匹配区间 键——>获取玩家gold
			room.setGoldInterval(gold);
			ControlRoom.roomRule.put(room.getId(), room);
			// 添加到controlRoom
			ControlRoom.goldRoom.get(gameArea).get(gameType).get(score).add(room.getId());
			ControlRoom.gamePlayerPosition.put(room.getId(), new HashMap<String, String>());

			// 将玩家添加到房间中
			this.addGamePosition(room.getId(), playerId);
		} else if (ControlRoom.goldRoom.get(gameArea).get(gameType).get(score) == null || ControlRoom.goldRoom.get(gameArea).get(gameType).get(score).size() == 0) {
			// 底分为空
			ControlRoom.goldRoom.get(gameArea).get(gameType).put(score, new ArrayList<String>());

			room = ((GameRoomService) ContextLoader.getCurrentWebApplicationContext().getBean("GameRoomService")).createRoom(1, null, gameType, null, playerNum, gameArea, score, "gold", 0);

			// 设置匹配区间 键——>获取玩家gold
			room.setGoldInterval(gold);
			ControlRoom.roomRule.put(room.getId(), room);
			// 添加到controlRoom
			ControlRoom.goldRoom.get(gameArea).get(gameType).get(score).add(room.getId());
			ControlRoom.gamePlayerPosition.put(room.getId(), new HashMap<String, String>());

			// 将玩家添加到房间中
			this.addGamePosition(room.getId(), playerId);
		} else {
			/**
			 * 区间匹配：
			 * 
			 * 初始区间值 最大区间值 init->max ? max-create 0.1 ++ 每个区间循环3次，最多叠加到0.5->
			 * 
			 */
			// double intervelNum = 0.1;
			Integer n = 0;
			Integer loopNum = 0;
			// 进行区间匹配
			while (true) {
				////System.out.println(playerId + "  " + gameArea);
				// 获取房间中所有的键值
				Map<String, Integer> goldInterval = new HashMap<String, Integer>();
				List<String> invalidRoomId = new ArrayList<>();
				for (String roomId : ControlRoom.goldRoom.get(gameArea).get(gameType).get(score)) {
					if (ControlRoom.gamePlayerPosition.containsKey(roomId) && ControlRoom.roomRule.containsKey(roomId)) {
						if (ControlRoom.gamePlayerPosition.get(roomId).size() < ControlRoom.roomRule.get(roomId).getPlayerNum()) {

							goldInterval.put(roomId, ControlRoom.roomRule.get(roomId).getGoldInterval());
						}
					} else {
						invalidRoomId.add(roomId);
					}
				}
				for (String rId : invalidRoomId) {
					ControlRoom.goldRoom.get(gameArea).get(gameType).get(score).remove(rId);
				}
				// 开始匹配
				if (loopNum % 3 == 0) {
					n++;
					if (n == 6) {
						// 创建房间
						room = ((GameRoomService) ContextLoader.getCurrentWebApplicationContext().getBean("GameRoomService")).createRoom(1, null, gameType, null, playerNum, gameArea, score, "gold", 0);

						// 设置匹配区间 键——>获取玩家gold
						room.setGoldInterval(((PlayerService) ControlRoom.getServiceBean("PlayerService")).getGoldByPlayerId(playerId));
						ControlRoom.roomRule.put(room.getId(), room);
						// 添加到controlRoom
						ControlRoom.goldRoom.get(gameArea).get(gameType).get(score).add(room.getId());
						ControlRoom.gamePlayerPosition.put(room.getId(), new HashMap<String, String>());

						// 将玩家添加到房间中
						this.addGamePosition(room.getId(), playerId);
						return;
					}
				}
				for (Entry<String, Integer> entry : goldInterval.entrySet()) {
					double intervalNum = 0.1 * n;
					if (gold * (1 - intervalNum) < entry.getValue() && gold * (1 + intervalNum) > entry.getValue()) {
						this.addGamePosition(entry.getKey(), playerId);
						return;
					}
				}
				loopNum++;
			}
		}

	}

	/**
	 * 房间中加入的人数达到规定人数时，发牌
	 */
	@SuppressWarnings("static-access")
	private void roomFaPai(String roomId) {
		//System.out.println("xxxxxxxxxxxxxxxxxxxxfriendGameNumxxxxxxxxxxxxxxxx" + ControlRoom.friendGameNum.get(roomId));
		GameRoom gameRoom = ControlRoom.roomRule.get(roomId);
//		System.out.println("GameRoom ===>>>1250   "+gameRoom);
		if (gameRoom.getType() == 0) {
			if (ControlRoom.friendGameNum.get(roomId) == null) {
				ControlRoom.friendGameNum.put(roomId, 1);
				ControlRoom.bankerMap.put(roomId, ControlRoom.gamePlayerPosition.get(roomId).get("south"));
				ControlRoom.this_banker.put(roomId, ControlRoom.gamePlayerPosition.get(roomId).get("south"));

			} else {
				ControlRoom.this_banker.put(roomId, ControlRoom.bankerMap.get(roomId));
			}

			/**
			 * 初始化 好友场积分
			 */
			if (ControlRoom.friendsScore.get(roomId) == null) {
				ControlRoom.friendsScore.put(roomId, new HashMap<String, Integer>());
				for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
					ControlRoom.friendsScore.get(roomId).put(str, 0);
				}
			}
		} else if (gameRoom.getType() == 1) {
			if (!ControlRoom.bankerMap.containsKey(roomId)) {
				ControlRoom.bankerMap.put(roomId, ControlRoom.gamePlayerPosition.get(roomId).get("south"));
				ControlRoom.this_banker.put(roomId, ControlRoom.gamePlayerPosition.get(roomId).get("south"));
			}
		}

		ControlRoom.player_room_info.put(roomId, new HashMap<String, Map<String, Object>>());
		for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
			ControlRoom.player_room_info.get(roomId).put(str, new HashMap<String, Object>());
		}

		/**
		 * 删除其他信息 1.删除additional
		 */
		ControlRoom.playerAdditional.remove(roomId);
		/**
		 * 为房间添加additional
		 */
		/*	if (gameRoom.getRule().contains(Param.TING_H) || gameRoom.getRule().contains(Param.BAI_H)) {
				
				if (!ControlRoom.playerAdditional.containsKey(roomId)) {

					ControlRoom.playerAdditional.put(roomId, new HashMap<String, String>());
				}
				
				for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
					if (!ControlRoom.playerAdditional.get(roomId).containsKey(str)) {
						ControlRoom.playerAdditional.get(roomId).put(str, null);
					}
				}
			}*/

		// 刷新步骤记录
		ControlRoom.stepRecord.put(roomId, new ArrayList<String>());

		// 删除nextRoom
		if (ControlRoom.nextRoom.containsKey(roomId)) {

			ControlRoom.nextRoom.remove(roomId);
		}

		// 删除流水
		if (ControlRoom.friendGameNum.containsKey(roomId) && ControlRoom.friendGameNum.get(roomId) == 1 || ControlRoom.roomRule.get(roomId).getType() == 1) {
			for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
				ControlMultiple.deleteLedger(str);
			}
		}

		this.initData(roomId);
		Logic logic = null;
		// 发牌 判断地域
		String gameArea = gameRoom.getGameArea();
		if (gameArea.equals("luZhou")) {// 泸州鬼牌
			if (gameRoom.getRule().contains(Param.LZ_ONE)) {

				logic = new Logic(Param.TY_GUI, Param.LZ_ONE);
			}
			if (gameRoom.getRule().contains(Param.LZ_TWO)) {

				logic = new Logic(Param.TY_GUI, Param.LZ_TWO);
			}
			if (gameRoom.getRule().contains(Param.LZ_THREE)) {

				logic = new Logic(Param.TY_GUI, Param.LZ_THREE);
			}

		} else if (gameArea.equals("guangAn")) {// 广安 中发白

			logic = new Logic(Param.TY_ZFB, null);
		} else if (gameArea.equals("ziGong") || gameArea.equals("neiJiang")) {// 自贡
			logic = new Logic(Param.TY_CGPT, null);
		} else {
			logic = new Logic(Param.TY_CGPF, null);
		}

		logic.playingId = ControlRoom.this_banker.get(roomId);
		List<String> playerList = this.getPlayerList(roomId);
		ControlRoom.allLogic.put(roomId, logic);
		String paiNum = gameRoom.getRule().contains(Param.PAINUM_SEVEN) ? Param.PAINUM_SEVEN : gameRoom.getRule().contains(Param.PAINUM_TEN) ? Param.PAINUM_TEN : Param.PAINUM_THIRTEEN;

		// 发牌
		String lastPai = ControlRoom.allLogic.get(roomId).fapai(playerList, paiNum);
		//为庄家添加 上一次摸牌信息（牌和index ）
		ControlRoom.player_room_info.get(roomId).get(logic.playingId).put("lastm_pai", lastPai);
		ControlRoom.player_room_info.get(roomId).get(logic.playingId).put("lastm_index", ControlRoom.allLogic.get(roomId).usermj.get(logic.playingId).lastIndexOf(lastPai));

		ControlRoom.stepRecord.get(roomId).add(ControlRoom.getStepJson(roomId, logic.playingId, "", "moPai"));
		if (ControlRoom.roomRule.get(roomId).getGameArea().equals("yiBin")) {
			ControlRoom.benJin.put(roomId, lastPai);
		}
		for (String string : playerList) {// 发牌
			if (this.connections.containsKey(string)) {
				this.reconnection(roomId, string, "faPai");
			}
		}

		/**
		 * 发牌后等待
		 * 
		 * 换三张
		 * 
		 * 选缺
		 * 
		 * 选飘 ...
		 */
		if (gameRoom.getRule().contains(Param.SWAP_3)) {
			// 换三张 添加controlroom数据
			System.out.println(gameRoom.getRule().toString());
			ControlRoom.swapArray.put(roomId, new HashMap<String, int[]>());
			Map<String, String> mapSwap = new HashMap<String, String>();
			mapSwap.put("title", "swapPai");

			for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
				this.send_msg_judge(str, JSONHelper.toJson(mapSwap));
			}
		} else {
			// 选缺
			if (gameRoom.getRule().contains(Param.QUE_H)) {
				ControlRoom.queChoosed.put(roomId, new HashMap<String, String>());
				Map<String, String> mapQue = new HashMap<String, String>();
				mapQue.put("title", "chooseQue");
				for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
					mapQue.put("quePai", this.recommend_que(roomId, str));
					this.send_msg_judge(str, JSONHelper.toJson(mapQue));
				}
			} else {
				// 判断是否能选飘
				if (gameRoom.getRule().contains(Param.PIAO_H)) {

					ControlRoom.piaoPlayer.put(roomId, new HashMap<String, String>());

					Map<String, String> mapPiao = new HashMap<>();
					mapPiao.put("title", "choosePiao");

					for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
						this.send_msg_judge(str, JSONHelper.toJson(mapPiao));
					}
				} else {
					// 提示庄家打牌 判断庄家的牌是否能胡 能杠
					if (this.connections.containsKey(logic.playingId)) {
						// 开始出牌
						this.mopaiCaozuo(logic.playingId, roomId, ControlRoom.allLogic.get(roomId).lastPai);
					}
				}

			}

		}

		/**
		 * 新增次数
		 */
		GameAreaNumService areaService = (GameAreaNumService) ControlRoom.getServiceBean("GameAreaNumService");
		for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
			areaService.addNumByGameArea(gameArea, str);
		}

		/**
		 * 排除演示场和金币场
		 */
		if (!gameRoom.getRule().contains("ai") && gameRoom.getType() == 0) {
			this.addPlayerRelation(roomId);
		}

	}

	/**
	 * 推荐选缺
	 */
	private String recommend_que(String roomId, String playerId) {

		List<String> paiList = ControlRoom.allLogic.get(roomId).usermj.get(playerId);
		Integer t_num = 0;
		Integer w_num = 0;
		Integer d_num = 0;
		for (String pai : paiList) {
			if (pai.contains("T")) {
				t_num++;
			} else if (pai.contains("W")) {
				w_num++;
			} else if (pai.contains("D")) {
				d_num++;
			}
		}
		String que_type = "";
		if (w_num <= d_num && w_num <= t_num) {

			que_type = "W";
		} else if (d_num <= w_num && d_num <= t_num) {

			que_type = "D";
		} else {

			que_type = "T";
		}

		return que_type;

	}

	/**
	 * 好友房中游戏自动添加好友
	 */
	private void addPlayerRelation(String roomId) {
		List<String> pList = new ArrayList<>(ControlRoom.gamePlayerPosition.get(roomId).values());
		PlayerRelationService reService = (PlayerRelationService) ControlRoom.getServiceBean("PlayerRelationService");
		for (String p1 : pList) {
			for (String p2 : pList) {
				reService.addRelationByPlayerId(p1, p2);
			}
		}
	}

	/**
	 * 断线重连 / 发牌
	 */
	private void reconnection(String roomId, String playerId, String title) {
		// 获取手牌的数量 xx
		/**
		 * 这里不能发送其他玩家的牌到前端
		 */
		GameRoom gameRoom = ControlRoom.roomRule.get(roomId);
		Logic logic = ControlRoom.allLogic.get(roomId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		List<String> playerList = new ArrayList<String>(logic.usermj.keySet());
		List<String> allPosition = new ArrayList<String>();

		map.put("userList", playerList);
		if (ControlRoom.nextRoom.containsKey(roomId) && ControlRoom.nextRoom.get(roomId).contains(playerId)) {
			//如果玩家已经选择了下一局
			map.put("nextGame", "Y");

			//this.send_msg_judge(JSONHelper.toJson(map));
			//return;
		}
		map.put("rule", gameRoom);
		//庄家
		map.put("bankerPlayer", ControlRoom.getDirection(roomId, ControlRoom.this_banker.get(roomId)));

		map.put("endFlag", ControlRoom.allLogic.get(roomId).endFlag);
		if ((boolean) map.get("endFlag")) {
			/**
			 * 手牌
			 */
			Map<String, List<String>> shouPai = new HashMap<String, List<String>>();
			for (String str : logic.usermj.keySet()) {
				shouPai.put(ControlRoom.getDirection(roomId, str), logic.usermj.get(str));
			}
			map.put("shouPai", shouPai);
		}

		// 已碰，已胡，已打，手中的牌、|*2017-01-02 07:28:14 杠的类型*|
		for (String player : playerList) {

			Map<String, Object> mapSign = new HashMap<String, Object>();
			mapSign.put("pgList", logic.userPG.get(player));
			mapSign.put("pgType", ControlRoom.gangType.get(roomId).get(player));
			mapSign.put("huList", logic.userHu.get(player));
			mapSign.put("chuList", logic.userChu.get(player));
			if (ControlRoom.playerAdditional.containsKey(roomId) && ControlRoom.playerAdditional.get(roomId).get(player) != null) {
				mapSign.put("additional", ControlRoom.playerAdditional.get(roomId).get(player));
				if (mapSign.get("additional").equals("Bai")) {
					mapSign.put("canHuList", ControlRoom.player_room_info.get(roomId).get(player).get("canHuList"));
					mapSign.put("bai_paiList", ControlRoom.player_room_info.get(roomId).get(player).get("paiList"));
				}
			}

			if (!title.equals("faPai") && player.equals(playerId)) {
				mapSign.put("lastMo_value", ControlRoom.player_room_info.get(roomId).get(player).get("lastm_pai"));
				mapSign.put("lastMo_index", ControlRoom.player_room_info.get(roomId).get(player).get("lastm_index"));
			}

			if (gameRoom.getType() == 0) {
				mapSign.put("score", ControlRoom.friendsScore.get(roomId).get(player));
			}

			if (ControlRoom.quePaiMap.containsKey(player)) {
				mapSign.put("queType", ControlRoom.quePaiMap.get(player));
			}
			if (player.equals(playerId)) {
				mapSign.put("paiList", logic.usermj.get(playerId));
			} else
				mapSign.put("paiList", logic.usermj.get(player).size());
			mapSign.put("playerId", this.getPlayerInfo(player));
			map.put(ControlRoom.getDirection(roomId, player), mapSign);
			allPosition.add(ControlRoom.getDirection(roomId, player));
		}
		/**
		 * 判断当前玩家是否有未完成的操作
		 * 换三张、缺、飘
		 */
		map.putAll(this.getNoComplete(roomId, playerId));

		// 是否好友场，发送当前gameNum\
		if (ControlRoom.roomRule.get(roomId).getType() == 0) {
			map.put("gameNum", ControlRoom.friendGameNum.get(roomId));
		}
		map.put("allPosition", allPosition);
		map.put("remainPai", logic.mj.size());
		map.put("selfPosition", ControlRoom.getDirection(roomId, playerId));

		// 判断当前出牌的人
		map.put("playingId", logic.playingId);
		map.put("playingPosition", ControlRoom.getDirection(roomId, logic.playingId));

		if (map.get("playingId").equals(playerId)) {
			//判断上一步是否自己摸牌

			List<String> stepList = ControlRoom.stepRecord.get(roomId);
			JSONObject last_step = JSONObject.fromObject(stepList.get(stepList.size() - 1));
			if (last_step.getString("title").equals("moPai") && last_step.getString("playerId").equals(playerId)) {
				map.put("self_mo_chu", "y");
			}

		}

		String jsonStr = JSONHelper.toJson(map);
		//System.out.println(jsonStr);
		this.send_msg_judge(playerId, jsonStr);

		/**
		 * 判断当前玩家是否是tg状态
		 */
		if (!title.equals("faPai")) {
			if (ControlRoom.player_room_info.get(roomId).get(playerId).containsKey("tg")) {

				this.tgHandle("cancelTg", playerId, roomId);
			}
		}

		/**
		 * 如果 等待当前玩家操作，并且已开始正式打牌
		 */
		if (map.containsKey("moPaiCaoZuo") && !title.equals("faPai") && !(boolean) map.get("endFlag")) {
			this.mopaiCaozuo(playerId, roomId, logic.usermj.get(playerId).get(0));
		}
		if (!(boolean) map.get("endFlag")) {
			if (playerId.equals(map.get("playingId").toString())) {
				this.huHint(playerId, roomId);
			}
		}
	}

	/**
	 * 判断重连的玩家是否需要进行换三张、选缺、选飘、选摆/听、碰、杠、胡
	 */
	private Map<String, Object> getNoComplete(String roomId, String playerId) {

		GameRoom room = ControlRoom.roomRule.get(roomId);
		Map<String, Object> onCompleteMap = new HashMap<String, Object>();
		if (ControlRoom.swapArray.containsKey(roomId) && ControlRoom.swapArray.get(roomId).size() < room.getPlayerNum()) {

			onCompleteMap.put("noComplete", "swap");
			if (!ControlRoom.swapArray.get(roomId).keySet().contains(playerId)) {
				onCompleteMap.put("selfNo", "y");
			}
		} else if (ControlRoom.queChoosed.containsKey(roomId) && ControlRoom.queChoosed.get(roomId).size() < room.getPlayerNum()) {

			onCompleteMap.put("noComplete", "chooseQue");
			if (!ControlRoom.queChoosed.get(roomId).keySet().contains(ControlRoom.getDirection(roomId, playerId))) {
				onCompleteMap.put("quePai", this.recommend_que(roomId, playerId));
				onCompleteMap.put("selfNo", "y");
			}
		} else if (ControlRoom.piaoPlayer.containsKey(roomId) && ControlRoom.piaoPlayer.get(roomId).size() < room.getPlayerNum()) {

			onCompleteMap.put("noComplete", "choosePiao");
			if (!ControlRoom.piaoPlayer.get(roomId).keySet().contains(playerId)) {
				onCompleteMap.put("selfNo", "y");
			}
		} else {
			//System.out.println(ControlRoom.allLogic.get(roomId).playingId.equals(playerId)+"  "+ControlRoom.allLogic.get(roomId).playingId);
			/*if (ControlRoom.allLogic.get(roomId).playingId.equals(playerId)) {*/
			/**
			 * 碰 杠 胡
			 * 1.playingId 是其他玩家
			 * 2.playingId 是自己
			 */
			Logic logic = ControlRoom.allLogic.get(roomId);
			List<String> caoZuoList = new ArrayList<String>();
			boolean caoZuoFlag = false;
			if (ControlRoom.caoZuoNum.get(roomId).get("canHu").size() > 0) {
				//能胡
				caoZuoFlag = true;
				if (ControlRoom.caoZuoNum.get(roomId).get("canHu").contains(playerId)) {
					caoZuoList.add("canHu");
				}
			}

			if (ControlRoom.caoZuoNum.get(roomId).get("canPeng").size() > 0) {
				caoZuoFlag = true;
				if (ControlRoom.caoZuoNum.get(roomId).get("canPeng").contains(playerId)) {
					caoZuoList.add("canPeng");
				}
			} else if (ControlRoom.caoZuoNum.get(roomId).get("canGang").size() > 0) {
				caoZuoFlag = true;
				if (ControlRoom.caoZuoNum.get(roomId).get("canGang").contains(playerId)) {
					caoZuoList.add("canMingGang");
				}
			}
			if (caoZuoList.size() > 0) {

				onCompleteMap.put("noComplete", "caoZuo");
				onCompleteMap.put("caoZuoList", caoZuoList);
				onCompleteMap.put("caoZuoPai", logic.userChu.get(logic.playingId).get(logic.userChu.get(logic.playingId).size() - 1));
			} else {
				if (caoZuoFlag) {
					onCompleteMap.put("noComplete", "otherCaoZuo");
				} else {
					if (ControlRoom.allLogic.get(roomId).playingId.equals(playerId)) {
						onCompleteMap.put("moPaiCaoZuo", "y");
					}
				}
			}

			/*} else {
				onCompleteMap.put("moPaiCaoZuo", "y");
			}*/

		}

		return onCompleteMap;
	}

	/**
	 * 判断新加入玩家相对于玩家的位置
	 */
	@SuppressWarnings("unused")
	private String getPosition(List<String> playerList, String playerId, String addId, String roomId) {
		int addIndex = 0;
		int index = 0;
		String result;
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).equals(playerId)) {
				index = i;
			}
			if (playerList.get(i).equals(addId)) {
				addIndex = i;
			}

		}
		int indexMinus = addIndex - index;
		if (ControlRoom.roomRule.get(roomId).getPlayerNum() == 4) {
			if (indexMinus == 1 || indexMinus == -3) {
				result = "right";
			} else if (indexMinus == 2 || indexMinus == -2) {
				result = "opposite";
			} else if (indexMinus == 3 || indexMinus == -1) {
				result = "left";
			} else {
				result = "self";
			}
		} else {
			if (indexMinus == 1 || indexMinus == -2) {
				result = "right";
			} else if (indexMinus == 2 || indexMinus == -1) {
				result = "left";
			} else {
				result = "selft";
			}
		}
		return result;
	}

	/**
	 * 摸牌的操作
	 * 
	 * 血流的时候不用判断是否能杠
	 * 
	 */
	private void mopaiCaozuo(String playerId, String roomId, String moPai) {

		Map<String, Object> msg = new HashMap<String, Object>();
		List<String> msgContent = new ArrayList<String>();
		Logic logic = ControlRoom.allLogic.get(roomId);
		GameRoom gameRoom = ControlRoom.roomRule.get(roomId);

		// 判断用户是否能胡
		/**
		 * 应该从手牌中移除一张牌来判断是否有能胡的牌，再判断这张牌是否在能胡的牌中
		 */

		/* //System.out.println(playerId+" 摸牌  "+moPai); */
		Integer moPaiIndex = logic.usermj.get(playerId).lastIndexOf(moPai);

		logic.usermj.get(playerId).remove(moPai);
		List<String> hupaiList = new ArrayList<>();
		//如果规则中有听 摆 , 并且不是幺鸡带
		if (!ControlRoom.roomRule.get(roomId).getRule().contains("yaoJiDaiTi") && (ControlRoom.roomRule.get(roomId).getRule().contains(Param.TING_H) || ControlRoom.roomRule.get(roomId).getRule().contains(Param.BAI_H))) {
			//如果当前用户有听，摆
			if (ControlRoom.playerAdditional.containsKey(roomId) && ControlRoom.playerAdditional.get(roomId).containsKey(playerId) && (ControlRoom.playerAdditional.get(roomId).get(playerId).contains("Ting") || ControlRoom.playerAdditional.get(roomId).get(playerId).contains("Bai"))) {
				hupaiList = this.canHu(playerId, roomId);
			}
		} else {
			hupaiList = this.canHu(playerId, roomId);
		}

		boolean isBoolean = true;
		if (hupaiList.contains(moPai)) {
			isBoolean = false;
			msgContent.add("canHu");
			msg.put("pai", moPai);
			ControlRoom.caoZuoNum.get(roomId).get("canHu").add(playerId);
		}else if(hupaiList.contains("All") && ControlRoom.roomRule.get(roomId).getRule().contains("yaoJiDaiTi")){
			isBoolean = false;
			msgContent.add("canHu");
			msg.put("pai", moPai);
			ControlRoom.caoZuoNum.get(roomId).get("canHu").add(playerId);
		}
		logic.usermj.get(playerId).add(moPaiIndex, moPai);// 再将这张牌添加上
		/********* canHu end *********/
		// 摸牌操作 如果玩家选择了听/摆 ，判断是否能吧杠。
		boolean can_gang_additional = true;
		if (ControlRoom.playerAdditional.containsKey(roomId) && ControlRoom.playerAdditional.get(roomId).containsKey(playerId)) {
			String additional_str = ControlRoom.playerAdditional.get(roomId).get(playerId);
			if (additional_str.contains("Ting") || additional_str.contains("Bai")) {
				// 判断用户是否能杠
				List<String> gangPai = logic.canGangPlus(playerId, ControlRoom.roomRule.get(roomId));
				
				// 判断用户是否能巴杠
				boolean isGui = ControlRoom.roomRule.get(roomId).getGameArea().equals("luZhou") ? true : false;
				List<String> baList = ControlRoom.allLogic.get(roomId).canBa(playerId, isGui);
				boolean canGang = false;
				if (baList.size() > 0) {
					canGang = true;
					for (String pai : baList) {
						ControlRoom.gangType.get(roomId).get(playerId).put(pai, Param.GANG_BA);
					}
					gangPai.addAll(baList);
				}
				if (canGang) {
					isBoolean = false;
					// 说明能杠
					ControlRoom.caoZuoNum.get(roomId).get("canGang").add(playerId);
					msgContent.add("canGang");
					msg.put("pai", gangPai);
					if (gangPai.size() > 1) {
						msg.put("gangPaiFlag", "y");
					}
				}
				can_gang_additional = false;
			}
		}
		//can_gang_additional = true && 剩下的牌的数量大于>0
		if (can_gang_additional && ControlRoom.allLogic.get(roomId).mj.size() > 0) {
			//System.out.println(roomId + "房间中的" + playerId + "选择了听/白，摸牌时没有判断是否能杠。");

			/*********** canGang ***********/
			// 判断用户是否能杠
			List<String> gangPai = logic.canGangPlus(playerId, ControlRoom.roomRule.get(roomId));
			boolean canGang = false;
			if (gangPai.size() > 0) {
				canGang = true;
				for (String pai : gangPai) {
					ControlRoom.gangType.get(roomId).get(playerId).put(pai, Param.GANG_AN);
				}
			}

			// 判断用户是否能巴杠
			boolean isGui = ControlRoom.roomRule.get(roomId).getGameArea().equals("luZhou") ? true : false;
			List<String> baList = ControlRoom.allLogic.get(roomId).canBa(playerId, isGui);
			if (baList.size() > 0) {
				canGang = true;
				for (String pai : baList) {
					ControlRoom.gangType.get(roomId).get(playerId).put(pai, Param.GANG_BA);
				}
				gangPai.addAll(baList);
			}
			//判断血流
			if (gameRoom.getGameType() == 1 && ControlRoom.huPlayer.get(roomId).contains(playerId)) {
				List<String> gangTemp = new ArrayList<>(gangPai);
				for (String pai : gangPai) {
					List<String> paiList = new ArrayList<String>(logic.usermj.get(playerId));
					List<String> huList = new ArrayList<String>();
					String gangType = ControlRoom.gangType.get(roomId).get(playerId).get(pai);
					Integer forNum = 0;
					if (gangType.equals(Param.GANG_AN)) {
						forNum = 4;
					} else if (gangType.equals(Param.GANG_BA)) {
						forNum = 1;
					} else if (gangType.equals(Param.GANG_MING)) {
						forNum = 3;
					}
					for (int i = 0; i < forNum; i++) {
						paiList.remove(pai);
					}
					huList = this.canHu(paiList, playerId, roomId);
					if (!(huList.size() == hupaiList.size()) && (huList.containsAll(hupaiList))) {
						gangTemp.remove(pai);
						ControlRoom.gangType.get(roomId).get(playerId).remove(pai);
					}
				}
				gangPai.clear();
				gangPai.addAll(gangTemp);

				if (gangPai.size() > 0) {
					canGang = true;
				} else {
					canGang = false;
				}
			}
			

			if (canGang) {
				isBoolean = false;
				// 说明能杠
				ControlRoom.caoZuoNum.get(roomId).get("canGang").add(playerId);
				msgContent.add("canGang");
				msg.put("pai", gangPai);
				if (gangPai.size() > 1) {
					msg.put("gangPaiFlag", "y");
				}
			}
			/********* canGang end *************/
		}
		
		if (isBoolean) {// 打牌
			msg.put("title", "chuPai");
			//判断当前玩家是否是庄家
			if (ControlRoom.stepRecord.get(roomId).size() == 1) {
				msg.put("zhuangHint", "y");
			}
		} else {
			msg.put("title", "caoZuo");
			msg.put("msgContent", msgContent);
		}
		this.send_msg_judge(playerId, JSONHelper.toJson(msg));
		
		this.sendNextMsg(playerId, roomId);
		/**
		 * 如果是能操作，判断是否掉线
		 
		if (msg.get("title").equals("caoZuo")) {

		}

		/**
		 * 如果是碰，判断是否有打前提示
		 */
		/*
		 * if(msg.get("title").equals("chuPai")){ xxthis.huHint(playerId,
		 * roomId); }
		 */

	}

	/**
	 * 一张牌对自己的的操作判断
	 */
	private boolean otherDaPai(String roomId, String playerId, String pai) {

		if (ControlRoom.roomRule.get(roomId).getGameType() == 0) {
			// 血战
			if (ControlRoom.huPlayer.get(roomId).contains(playerId)) {
				return false;
			}
		}

		List<String> hint = new ArrayList<String>();
		// 摸牌操作 如果玩家选择了听/摆 ，跳过     判断是否能杠。
		if (ControlRoom.playerAdditional.get(roomId) != null && ControlRoom.playerAdditional.get(roomId).get(playerId) != null && ((ControlRoom.playerAdditional.get(roomId).get(playerId).contains("Ting") || ControlRoom.playerAdditional.get(roomId).get(playerId).contains("Bai")))) {
			//System.out.println(roomId + "房间中的" + playerId + "选择了听/白，摸牌时没有判断是否能杠。");
			// 胡
			List<String> huPai = this.canHu(playerId, roomId);
			// HuPaiLogic.canHu(ControlRoom.allLogic.get(roomId).usermj.get(playerId),"");
			
			if (huPai.contains(pai)) {
				hint.add("canHu");
				ControlRoom.caoZuoNum.get(roomId).get("canHu").add(playerId);
			}else if(huPai.contains("All") && ControlRoom.roomRule.get(roomId).getRule().contains("yaoJiDaiTi")){
				hint.add("canHu");
				ControlRoom.caoZuoNum.get(roomId).get("canHu").add(playerId);
			}
			
		} else {
			// 碰 杠
			boolean pengType = false;
			if (ControlRoom.roomRule.get(roomId).getGameType() == 1 && ControlRoom.huPlayer.get(roomId).contains(playerId)) {
				// 如果是血流并且已和牌的玩家中包含当前判断的玩家，则不应该判断牌是否能碰
				pengType = true;
			}

			String result = ControlRoom.allLogic.get(roomId).canPG(playerId, ControlRoom.roomRule.get(roomId), pai, pengType);
			if (result.equals(Param.CAN_G)) {
				if(pai.equals("1T") && ControlRoom.roomRule.get(roomId).getRule().contains("yaoJiDaiTi")){
					
				}else{
					hint.add("canMingGang");
					ControlRoom.caoZuoNum.get(roomId).get("canGang").add(playerId);
					ControlRoom.caoZuoNum.get(roomId).get("canPeng").add(playerId);
					ControlRoom.gangType.get(roomId).get(playerId).put(pai, Param.GANG_MING);
				}
			} else if (result.equals(Param.CAN_P)) {
				if(pai.equals("1T") && ControlRoom.roomRule.get(roomId).getRule().contains("yaoJiDaiTi")){
					
				}else{
					hint.add("canPeng");
					ControlRoom.caoZuoNum.get(roomId).get("canPeng").add(playerId);
				}
			}
			
			String rule_str = ControlRoom.roomRule.get(roomId).getRule();
			//判断不是倒倒胡的自摸胡
			if (!rule_str.contains(Param.BAI_H) && !rule_str.contains("ziMoHu")) {
				// 胡
				List<String> huPai = this.canHu(playerId, roomId);
				// HuPaiLogic.canHu(ControlRoom.allLogic.get(roomId).usermj.get(playerId),"");
				if (huPai.contains(pai)) {
					hint.add("canHu");
					ControlRoom.caoZuoNum.get(roomId).get("canHu").add(playerId);
				}else if(huPai.contains("All") && ControlRoom.roomRule.get(roomId).getRule().contains("yaoJiDaiTi")){
					hint.add("canHu");
					ControlRoom.caoZuoNum.get(roomId).get("canHu").add(playerId);
				}
			}
			
			//如果是万州麻将，屁胡不能胡
			if(ControlRoom.roomRule.get(roomId).getGameArea().equals("WanZhou") && ControlRoom.roomRule.get(roomId).getGameType()==4){
				 Map<String,Integer>  m = CalculateMultiple.getMultiple(ControlRoom.allLogic.get(roomId).usermj.get(playerId), ControlRoom.allLogic.get(roomId).userPG.get(playerId), pai, ControlRoom.benJin.get(roomId), ControlRoom.roomRule.get(roomId).getGameArea(), roomId);
				 System.out.println(m);
			}
		}

		// 飞

		//
		if (hint.size() > 0) {
			Map<String, Object> hintMsg = new HashMap<String, Object>();
			hintMsg.put("title", "caoZuo");
			hintMsg.put("msgContent", hint);
			hintMsg.put("pai", pai);
			this.send_msg_judge(playerId, JSONHelper.toJson(hintMsg));
			return true;
		}
		return false;
	}

	/**
	 * 有玩家出牌后的操作
	 * @return 	true:出牌后整个牌局还剩n>0张牌
	 * 			false:出牌后整个牌局还剩n=0张牌
	 */
	@SuppressWarnings({ "static-access" })
	private boolean chuPai(String playerId, String roomId, String pai) {

		//System.out.println(playerId+"出牌后的操作。。。");
		// 减少玩家手中的牌
		ControlRoom.allLogic.get(roomId).usermj.get(playerId).remove(pai);
		ControlRoom.allLogic.get(roomId).userChu.get(playerId).add(pai);

		// 判断玩家当前手中的牌是否下叫
		this.havaJiao(roomId, playerId);

		// 发送给其他玩家出牌信息
		Map<String, Object> hint = new HashMap<String, Object>();
		hint.put("title", "otherChuPai");
		hint.put("pai", pai);
		hint.put("sex", ControlRoom.playerSex.get(playerId));
		hint.put("position", ControlRoom.getDirection(roomId, playerId));
		// hint.put("paiList",ControlRoom.allLogic.get(roomId).userChu.get(playerId));
		String map_Str = JSONHelper.toJson(hint);
		List<String> playerList = new ArrayList<String>(ControlRoom.allLogic.get(roomId).usermj.keySet());
		for (String otherId : playerList) {

			this.send_msg_judge(otherId, map_Str);
		}

		/**
		 * 如果没有牌了 其他用户只能胡 不能碰 杠
		 */
		if (ControlRoom.allLogic.get(roomId).mj.size() == 0) {
			if (ControlRoom.roomRule.get(roomId).getRule().equals("ai")) {

				this.gameOver(roomId, null);
			}
			return false;
		} else {
			/**
			 * 如果在血战中，已有N-1位玩家和牌，gameover
			 */
			if (ControlRoom.huPlayer.get(roomId).size() == ControlRoom.allLogic.get(roomId).usermj.size() - 1 && ControlRoom.roomRule.get(roomId).getGameType() == 0) {
				return false;
				//如果是倒到胡，或则是撞大胡   ，并且有一个人胡牌，结束游戏
			}else if(ControlRoom.huPlayer.get(roomId).size() ==1 && (ControlRoom.roomRule.get(roomId).getGameType() == 2 || ControlRoom.roomRule.get(roomId).getGameType() == 4)){
				this.gameOver(roomId, null);
			}

			// 判断其他玩家是否能碰，杠，胡
			List<String> palyerList = new ArrayList<String>(ControlRoom.allLogic.get(roomId).usermj.keySet());
			int flag = 0;
			for (String string : palyerList) {
				if (this.connections.containsKey(string)) {
					if (!playerId.equals(string)) {
						/**
						 * 判断是否 是缺牌
						 */
						if (!(ControlRoom.roomRule.get(roomId).getRule().contains(Param.QUE_H) && pai.contains(ControlRoom.quePaiMap.get(string)))) {
							if (this.otherDaPai(roomId, string, pai)) {
								flag++;
							}
						}
					}
				}
			}
			if (flag == 0) {
				// 下个人操作
				this.moPai(this.getNextPlayerId(playerId, roomId), roomId);
			}

			return true;
		}

	}

	/**
	 * 即时结算
	 */
	private void settlementMsg(Map<String, List<Map<String, Object>>> map, String roomId) {
		Map<String, Object> mapMsg = new HashMap<>();
		mapMsg.put("title", "settlementDetail");
		mapMsg.put("value", map);
		for (String playerId : ControlRoom.gamePlayerPosition.get(roomId).values()) {
			this.send_msg_judge(playerId, JSONHelper.toJson(mapMsg));
		}

	}

	/**
	 * 玩家选择相应的操作后（碰/杠/胡/过）
	 */
	private void caoZuoChoose(String playerId, String name, String roomId, String pai) {
		if (name.equals("peng")) {
			/**
			 * 判断是否还存在当前操作
			 */
			if (!ControlRoom.caoZuoNum.get(roomId).get("canPeng").contains(playerId)) {
				return;
			}

			// 判断 没有其他人胡 或者 是不是自己能碰的同时也能胡
			if ((ControlRoom.caoZuoNum.get(roomId).get("canHu").size() == 0 || (ControlRoom.caoZuoNum.get(roomId).get("canHu").size() == 1 && ControlRoom.caoZuoNum.get(roomId).get("canHu").get(0).equals(playerId))) && ControlRoom.caoZuoNum.get(roomId).get("hu").size() == 0) {
				/*
				 * if(ControlRoom.caoZuoNum.get(roomId).get("canHu").size()==0||
				 * ControlRoom
				 * .caoZuoNum.get(roomId).get("canHu").get(0).equals(playerId)){
				 */
				ControlRoom.allLogic.get(roomId).peng(pai, playerId, false);
				this.deleteCaoZuo(roomId, playerId, true);

				ControlRoom.player_room_info.get(roomId).get(playerId).put("lastm_index", -1);

				// 直接发送可以碰的消息到前端
				this.sendPlayerCaoZuo("peng", playerId, roomId, ControlRoom.allLogic.get(roomId).playingId, pai);
				ControlRoom.allLogic.get(roomId).playingId = playerId;
				this.sendNextMsg(playerId, roomId);

				/*
				 * }else{ //向caozuoNum添加数据 List<String> list =
				 * ControlRoom.caoZuoNum.get(roomId).get("peng");
				 * list.add(playerId); }
				 */
			} else {
				// 向caozuoNum添加数据
				List<String> list = ControlRoom.caoZuoNum.get(roomId).get("peng");
				list.add(playerId);
			}
			ControlRoom.caoZuoNum.get(roomId).get("canPeng").remove(playerId);
		} else if (name.equals("gang")) {
			/**
			 * 判断是否还存在当前操作
			 */
			if (!ControlRoom.caoZuoNum.get(roomId).get("canGang").contains(playerId)) {
				return;
			}
			// 判断 是否有其他人能胡/自己能杠的同时也能胡
			//判断别人都不能胡 或者只有自己能胡
			if ((ControlRoom.caoZuoNum.get(roomId).get("canHu").size() == 0 || (ControlRoom.caoZuoNum.get(roomId).get("canHu").size() == 1 && ControlRoom.caoZuoNum.get(roomId).get("canHu").get(0).equals(playerId))) && ControlRoom.caoZuoNum.get(roomId).get("hu").size() == 0) {

				/*
				 * if (ControlRoom.caoZuoNum.get(roomId).get("canHu").size() ==
				 * 0 || ControlRoom.caoZuoNum.get(roomId).get("canHu")
				 * .get(0).equals(playerId)) {
				 */
				boolean isGui = ControlRoom.roomRule.get(roomId).getGameArea().equals("yiBin") ? true : false;
				ControlRoom.allLogic.get(roomId).gang(playerId, pai, ControlRoom.gangType.get(roomId).get(playerId).get(pai), isGui);
				/**
				 * 判断当前地域是否有刮风下雨
				 */
				if (ControlRoom.roomRule.get(roomId).getRule().contains(Param.FY_H)) {
					// 结算
					this.settlementMsg(ControlMultiple.addLedger(playerId, ControlRoom.allLogic.get(roomId).playingId, ControlRoom.gangType.get(roomId).get(playerId).get(pai), roomId, pai), roomId);
				}

				this.deleteCaoZuo(roomId, playerId, true);
				// 直接发送可以的消息到前端
				this.sendPlayerCaoZuo("gang", playerId, roomId, ControlRoom.allLogic.get(roomId).playingId, pai);

				ControlRoom.caoZuoNum.get(roomId).get("canGang").remove(playerId);
				// 发一张牌给玩家
				this.moPai(playerId, roomId);

				/*
				 * } else { // 向caozuoNum添加数据 List<String> list =
				 * ControlRoom.caoZuoNum.get(roomId).get( "gang");
				 * list.add(playerId); }
				 */

			} else {
				// 向caozuoNum添加数据
				List<String> list = ControlRoom.caoZuoNum.get(roomId).get("gang");
				list.add(playerId);
			}
		} else if (name.equals("hu")) {
			/**
			 * 判断是否在canPeng,canGang中还存在其他玩家
			 * 如果存在则清除这些玩家
			 */
			ControlRoom.caoZuoNum.get(roomId).get("canPeng").clear();
			ControlRoom.caoZuoNum.get(roomId).get("canGang").clear();
			ControlRoom.caoZuoNum.get(roomId).get("peng").clear();
			ControlRoom.caoZuoNum.get(roomId).get("gang").clear();

			if (ControlRoom.caoZuoNum.get(roomId).get("canHu").size() == 1) {

				if (ControlRoom.caoZuoNum.get(roomId).get("hu").size() == 0) {
					this.deleteCaoZuo(roomId, playerId, true);
					// 只有一个人
					// 判断是自摸还是点炮胡
					if (playerId.equals(ControlRoom.allLogic.get(roomId).playingId)) {
						// 自摸，从手牌中移除自摸的牌
						ControlRoom.allLogic.get(roomId).usermj.get(playerId).remove(pai);
						this.sendPlayerCaoZuo("ziMo", playerId, roomId, ControlRoom.allLogic.get(roomId).playingId, pai);
					} else {
						this.sendPlayerCaoZuo("hu", playerId, roomId, ControlRoom.allLogic.get(roomId).playingId, pai);
					}

					// 结算
					this.settlementMsg(ControlMultiple.addLedger(playerId, ControlRoom.allLogic.get(roomId).playingId, "hu", roomId, pai), roomId);

					// 第一个胡牌的玩家是庄家
					if (ControlRoom.huPlayer.get(roomId).size() == 1) {
						ControlRoom.bankerMap.put(roomId, playerId);
					}
					this.moPai(this.getNextPlayerId(playerId, roomId), roomId);
					
				} else {
					// 还有多个人能胡，告诉其他用户，可以胡了
					List<String> huList = ControlRoom.caoZuoNum.get(roomId).get("hu");
					huList.add(playerId);
					ControlRoom.caoZuoNum.get(roomId).get("canHu").remove(playerId);
					/**
					 * 如果第一个和牌 是一炮多响 那么庄家为点炮者
					 */
					if (ControlRoom.huPlayer.get(roomId).size() == 0) {
						ControlRoom.bankerMap.put(roomId, ControlRoom.allLogic.get(roomId).playingId);
					}
					this.duoHu(roomId, pai);
				}
			} else {
				ControlRoom.caoZuoNum.get(roomId).get("canHu").remove(playerId);
				ControlRoom.caoZuoNum.get(roomId).get("hu").add(playerId);
				
			}
		} else if (name.equals("guo")) {
			/**
			 * 用户选择过后，应该判断他是否能有相应的操作，如果没有 下一位操作玩家从playing 开始的下一家
			 */
			this.deleteCaoZuo(roomId, playerId, true);
			// 如果操作的玩家是本人 提示 玩家打牌
			if (ControlRoom.allLogic.get(roomId).playingId.equals(playerId)) {
				// 自摸  or 杠
				Map<String, String> msg = new HashMap<String, String>();
				msg.put("title", "chuPai");
				this.send_msg_judge(playerId, JSONHelper.toJson(msg));
				/**
				 * 打前提示
				 */
				/*
				 * this.huHint(playerId, roomId);xx
				 */

			} else {// 判断其他操作是否能够正常进行
				if (ControlRoom.caoZuoNum.get(roomId).get("canHu").size() == 0 && ControlRoom.caoZuoNum.get(roomId).get("hu").size() == 0) {// 没有其他玩家能胡
					if (ControlRoom.caoZuoNum.get(roomId).get("canGang").size() == 0 && ControlRoom.caoZuoNum.get(roomId).get("gang").size() == 0) {// 没有其他人能杠
						//如果没有人可以碰
						if (ControlRoom.caoZuoNum.get(roomId).get("canPeng").size() == 0 && ControlRoom.caoZuoNum.get(roomId).get("peng").size() == 0) {
							// 下个人操作
							this.moPai(this.getNextPlayerId(ControlRoom.allLogic.get(roomId).playingId, roomId), roomId);
						} else {
							if (ControlRoom.caoZuoNum.get(roomId).get("peng").size() == 1 && ControlRoom.caoZuoNum.get(roomId).get("canPeng").size() == 0) {
//								ControlRoom.allLogic.get(roomId).peng(pai, playerId, false);
//								this.deleteCaoZuo(roomId, playerId, true);
//
//								ControlRoom.player_room_info.get(roomId).get(playerId).put("lastm_index", -1);
//
//								// 告诉该用户可以碰
//								this.sendPlayerCaoZuo("peng", ControlRoom.caoZuoNum.get(roomId).get("peng").get(0), ControlRoom.allLogic.get(roomId).playingId, roomId, pai);
//								ControlRoom.allLogic.get(roomId).playingId = playerId;
//								this.sendNextMsg(ControlRoom.caoZuoNum.get(roomId).get("peng").get(0), roomId);
								String player1 = ControlRoom.caoZuoNum.get(roomId).get("peng").get(0);
								ControlRoom.allLogic.get(roomId).peng(pai, player1, false);
								this.deleteCaoZuo(roomId, player1, true);

								ControlRoom.player_room_info.get(roomId).get(player1).put("lastm_index", -1);

								// 告诉该用户可以碰
								this.sendPlayerCaoZuo("peng", player1, roomId, ControlRoom.allLogic.get(roomId).playingId, pai);
								ControlRoom.allLogic.get(roomId).playingId = player1;
								this.sendNextMsg(player1, roomId);
							}
						}
					} else {
						if (ControlRoom.caoZuoNum.get(roomId).get("gang").size() == 1 && ControlRoom.caoZuoNum.get(roomId).get("canGang").size() == 0) {
							// 移除牌
							String player1 = ControlRoom.caoZuoNum.get(roomId).get("gang").get(0);
							boolean isGui = ControlRoom.roomRule.get(roomId).getGameArea().equals("yiBin") ? true : false;
							ControlRoom.allLogic.get(roomId).gang(player1, pai, ControlRoom.gangType.get(roomId).get(player1).get(pai), isGui);

							/**
							 * 判断当前地域是否有刮风下雨
							 */
							if (ControlRoom.roomRule.get(roomId).getRule().contains(Param.FY_H)) {
								// 结算
								this.settlementMsg(ControlMultiple.addLedger(player1, ControlRoom.allLogic.get(roomId).playingId, ControlRoom.gangType.get(roomId).get(player1).get(pai), roomId, pai), roomId);
							}
							this.deleteCaoZuo(roomId, player1, true);

							// 告诉该用户可以杠
							this.sendPlayerCaoZuo("gang", ControlRoom.caoZuoNum.get(roomId).get("gang").get(0), roomId, ControlRoom.allLogic.get(roomId).playingId, pai);

							// 发一张牌给玩家
							this.moPai(ControlRoom.caoZuoNum.get(roomId).get("gang").get(0), roomId);
						}
					}
				} else if (ControlRoom.caoZuoNum.get(roomId).get("canHu").size() == 0) {// 所有能胡的玩家都做出了选择
					// 循环告诉能胡的玩家，可以胡了。根据顺序找出最后一个胡的玩家来确定下一位操作的玩家是谁
					this.duoHu(roomId, pai);

				}
			}

		}

	}

	/**
	 * 多个玩家和牌后的操作
	 */
	private void duoHu(String roomId, String pai) {
		// 循环告诉能胡的玩家，可以胡了。根据顺序找出最后一个胡的玩家来确定下一位操作的玩家是谁
		String playing = ControlRoom.allLogic.get(roomId).playingId;
		Map<String, String> huPlayer = new HashMap<>();

		if (ControlRoom.caoZuoNum.get(roomId).get("hu").size() > 1) {
			/**
			 * 一炮多响
			 */
			List<String> positionList = new ArrayList<String>();
			List<String> sexList = new ArrayList<String>();

			List<Map<String, List<Map<String, Object>>>> listSettlement = new ArrayList<Map<String, List<Map<String, Object>>>>();

			for (String str : ControlRoom.caoZuoNum.get(roomId).get("hu")) {
				// 结算
				listSettlement.add(ControlMultiple.addLedger(str, playing, "hu", roomId, pai));

				huPlayer.put(ControlRoom.getDirection(roomId, str), str);

				if (!ControlRoom.huPlayer.get(roomId).contains(str)) {
					ControlRoom.huPlayer.get(roomId).add(str);
				}
				positionList.add(ControlRoom.getDirection(roomId, str));
				sexList.add(ControlRoom.playerSex.get(str));

			}

			Map<String, List<Map<String, Object>>> settlementResult = new HashMap<>();
			settlementResult.put("addTarget", new ArrayList<Map<String, Object>>());
			settlementResult.put("reduceTarget", new ArrayList<Map<String, Object>>());
			//遍历settlementList 
			for (Map<String, List<Map<String, Object>>> map : listSettlement) {

				settlementResult.get("addTarget").add(map.get("addTarget").get(0));
				if (settlementResult.get("reduceTarget").size() == 0) {
					settlementResult.get("reduceTarget").add(map.get("reduceTarget").get(0));
				} else {
					Integer score = (Integer) settlementResult.get("reduceTarget").get(0).get("score");
					Integer mapScore = (Integer) map.get("reduceTarget").get(0).get("score");
					settlementResult.get("reduceTarget").get(0).put("score", score + mapScore);
				}
				/*if (settlementResult.size() == 0) {

					List<Map<String, Object>> addList = new ArrayList<>();
					addList.addAll(map.get("addTarget"));

					List<Map<String, Object>> reduceList = new ArrayList<>();
					reduceList.addAll(map.get("reduceTarget"));

					settlementResult.put("addTarget", addList);
					settlementResult.put("redecuTarget", reduceList);
				} else {
					settlementResult.get("addTarget").add(map.get("redecuTarget").get(0));
					settlementResult.get("reduceTarget").get(0).put("score", Integer.valueOf((settlementResult.get("reduceTarget").get(0).get("score")).toString()) + Integer.valueOf((map.get("reduceTarget").get(0).get("score").toString())));

				}*/
			}
			this.settlementMsg(settlementResult, roomId);
			Map<String, Object> paoMsg = new HashMap<String, Object>();
			paoMsg.put("title", "duoHu");
			paoMsg.put("playerList", ControlRoom.caoZuoNum.get(roomId).get("hu"));
			paoMsg.put("playerPosition", positionList);
			paoMsg.put("playerSex", sexList);
			paoMsg.put("targetPosition", ControlRoom.getDirection(roomId, playing));
			paoMsg.put("pai", pai);
			String map_Str = JSONHelper.toJson(paoMsg);
			for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
				this.send_msg_judge(str, map_Str);
			}

			ControlRoom.stepRecord.get(roomId).add(ControlRoom.getStepJson(roomId, ControlRoom.caoZuoNum.get(roomId).get("hu").toString(), pai, "duoHu"));

		} else {
			/**
			 * 如果只有一家和牌
			 */
			huPlayer.put(ControlRoom.getDirection(roomId, ControlRoom.caoZuoNum.get(roomId).get("hu").get(0)), ControlRoom.caoZuoNum.get(roomId).get("hu").get(0));
			this.sendPlayerCaoZuo("hu", ControlRoom.caoZuoNum.get(roomId).get("hu").get(0), roomId, ControlRoom.allLogic.get(roomId).playingId, pai);
		}

		/**
		 * 在胡牌之后删除caoZuoNum
		 */
		for (String string : huPlayer.values()) {

			this.deleteCaoZuo(roomId, string, true);
		}

		// 获取当前操作玩家 的位置
		String playingPosition = ControlRoom.getDirection(roomId, playing);

		if (playingPosition.equals("south")) {
			if (huPlayer.containsKey("west")) {
				playing = huPlayer.get("west");
			} else if (huPlayer.containsKey("north")) {
				playing = huPlayer.get("north");
			} else if (huPlayer.containsKey("east")) {
				playing = huPlayer.get("east");
			}
		} else if (playingPosition.equals("east")) {
			if (huPlayer.containsKey("south")) {
				playing = huPlayer.get("south");
			} else if (huPlayer.containsKey("west")) {
				playing = huPlayer.get("west");
			} else if (huPlayer.containsKey("north")) {
				playing = huPlayer.get("north");
			}
		} else if (playingPosition.equals("north")) {
			if (huPlayer.containsKey("east")) {
				playing = huPlayer.get("east");
			} else if (huPlayer.containsKey("south")) {
				playing = huPlayer.get("south");
			} else if (huPlayer.containsKey("west")) {
				playing = huPlayer.get("west");
			}
		} else if (playingPosition.equals("west")) {
			if (huPlayer.containsKey("north")) {
				playing = huPlayer.get("north");
			} else if (huPlayer.containsKey("east")) {
				playing = huPlayer.get("east");
			} else if (huPlayer.containsKey("south")) {
				playing = huPlayer.get("south");
			}
		}

		/*
		 * if(huPlayerList.contains("west")){ playing = huPlayer.get("south");
		 * }else if(huPlayerList.contains("opposite")){ playing =
		 * huPlayer.get("opposite"); }else if(huPlayerList.contains("right")){
		 * playing = huPlayer.get("right"); }
		 */
		
		this.moPai(this.getNextPlayerId(playing, roomId), roomId);
	}

	/**
	 * 删除操作 flag : 是否要删除所有操作
	 */
	private void deleteCaoZuo(String roomId, String playerId, boolean flag) {
		/*
		 * //移除当前玩家的所有操作 Map<String, List<String>> map =
		 * ControlRoom.caoZuoNum.get(roomId); for(Entry<String, List<String>>
		 * entry : map.entrySet()){ entry.getValue().remove(playerId); }
		 */
		// 删除数据
		ControlRoom.caoZuoNum.get(roomId).get("canHu").remove(playerId);
		ControlRoom.caoZuoNum.get(roomId).get("canPeng").remove(playerId);
		ControlRoom.caoZuoNum.get(roomId).get("canGang").remove(playerId);
		if (flag) {
			ControlRoom.caoZuoNum.get(roomId).get("hu").remove(playerId);
			ControlRoom.caoZuoNum.get(roomId).get("peng").remove(playerId);
			ControlRoom.caoZuoNum.get(roomId).get("gang").remove(playerId);
		}
	}

	/**
	 * 重置caoZuoNum
	 */
	private void reset(String roomId) {
		if(ControlRoom.caoZuoNum.get(roomId)!=null){
			if(ControlRoom.caoZuoNum.get(roomId).get("canPeng")!=null){
				ControlRoom.caoZuoNum.get(roomId).get("canPeng").clear();
			}
			if(ControlRoom.caoZuoNum.get(roomId).get("canGang")!=null){
				ControlRoom.caoZuoNum.get(roomId).get("canGang").clear();
			}
			if(ControlRoom.caoZuoNum.get(roomId).get("canHu")!=null){
				ControlRoom.caoZuoNum.get(roomId).get("canHu").clear();
			}
			if(ControlRoom.caoZuoNum.get(roomId).get("peng")!=null){
				ControlRoom.caoZuoNum.get(roomId).get("peng").clear();
			}
			if(ControlRoom.caoZuoNum.get(roomId).get("gang")!=null){
				ControlRoom.caoZuoNum.get(roomId).get("gang").clear();
			}
			if(ControlRoom.caoZuoNum.get(roomId).get("hu")!=null){
				ControlRoom.caoZuoNum.get(roomId).get("hu").clear();
			}
		}
	}

	/**
	 * 告诉其他玩家当前玩家进行的操作
	 */
	private void sendPlayerCaoZuo(String title, String playerId, String roomId, String playingId, String pai) {
		this.reset(roomId);
		ControlRoom.stepRecord.get(roomId).add(ControlRoom.getStepJson(roomId, playerId, pai, title));

		Map<String, Object> hint = new HashMap<String, Object>();
		hint.put("title", "caozuoJudge");

		/**
		 * 移除 出牌区域 中的牌 如果是一炮多响 ?
		 */

		// 如果title=hu 向已和牌人员中添加
		if (title.equals("hu") || title.equals("ziMo")) {
			ControlRoom.allLogic.get(roomId).userHu.get(playerId).add(pai);
			if (!ControlRoom.huPlayer.get(roomId).contains(playerId)) {
				ControlRoom.huPlayer.get(roomId).add(playerId);
			}
		}

		hint.put("name", title);
		if (title.equals("gang")) {
			hint.put("pgType", ControlRoom.gangType.get(roomId).get(playerId).get(pai));
		}

		hint.put("playerId", playerId);
		hint.put("pai", pai);
		hint.put("sex", ControlRoom.playerSex.get(playerId));
		hint.put("targetPosition", ControlRoom.getDirection(roomId, playingId));
		/*
		 * if (title.equals("ziMo")) { hint.put("paiIndex",
		 * ControlRoom.allLogic.get(roomId).usermj.get(playerId)
		 * .lastIndexOf(pai)); }
		 */
		for (String str : ControlRoom.allLogic.get(roomId).usermj.keySet()) {
			if (playerId.equals(str)) {
				hint.put("paiList", ControlRoom.allLogic.get(roomId).usermj.get(playerId));
				hint.put("position", ControlRoom.getDirection(roomId, playerId));
			} else {
				hint.put("paiList", ControlRoom.allLogic.get(roomId).usermj.get(playerId).size());
				hint.put("position", ControlRoom.getDirection(roomId, playerId));
			}
			this.send_msg_judge(str, JSONHelper.toJson(hint));
		}
	}

	/**
	 * 玩家摸牌
	 */
	@SuppressWarnings("static-access")
	private void moPai(final String playerId, final String roomId) {

		// 如果是血战，当还没和牌的人数为1时，结束对局
		// 是否是血战
		boolean gameType = ControlRoom.roomRule.get(roomId).getGameType() == 0 ? true : false;

		if (gameType && ControlRoom.huPlayer.get(roomId).size() == ControlRoom.gamePlayerPosition.get(roomId).size() - 1) {
			this.gameOver(roomId, null);
			return;
		}else if(ControlRoom.huPlayer.get(roomId).size()>0 && ControlRoom.roomRule.get(roomId).getGameType() == 2){//倒倒胡
			gameOver(roomId, playerId);
			return;
		}else if(ControlRoom.huPlayer.get(roomId).size()>0 && ControlRoom.roomRule.get(roomId).getGameType() == 4){//撞大胡
			gameOver(roomId, playerId);
			return;
		}

		if (ControlRoom.allLogic.get(roomId).mj.size() > 0) {
			
			final String pai = ControlRoom.allLogic.get(roomId).moPai(playerId);
			ControlRoom.allLogic.get(roomId).playingId = playerId;
			ControlRoom.stepRecord.get(roomId).add(ControlRoom.getStepJson(roomId, playerId, pai, "moPai"));

			/**
			 * 向palyer_room_info中添加
			 *  玩家上一张摸的牌 玩家上张摸牌的index
			 */
			ControlRoom.player_room_info.get(roomId).get(playerId).put("lastm_pai", pai);
			ControlRoom.player_room_info.get(roomId).get(playerId).put("lastm_index", ControlRoom.allLogic.get(roomId).usermj.get(playerId).lastIndexOf(pai));

			// 找出摸的牌在玩家手中的位置
			Map<String, Object> hint = new HashMap<String, Object>();
			hint.put("title", "moPai");
			hint.put("pai", pai);
			hint.put("paiIndex", ControlRoom.allLogic.get(roomId).usermj.get(playerId).lastIndexOf(pai));
			hint.put("paiList", ControlRoom.allLogic.get(roomId).usermj.get(playerId));
			this.send_msg_judge(playerId, JSONHelper.toJson(hint));
			System.out.println("发送摸牌信息给前端=====》》》》》"+playerId+"摸牌为： ------"+pai);
			/**
			 * 发送给其他玩家当前玩家摸牌的消息 为当前玩家添加待操作牌 不能集成到sendNextMsg中 考虑到碰的情况 碰
			 * 后直接添加待操作牌
			 */
			Map<String, Object> moPaiHint = new HashMap<>();
			moPaiHint.put("title", "moPaiHint");
			moPaiHint.put("position", ControlRoom.getDirection(roomId, playerId));
			String map_Str = JSONHelper.toJson(moPaiHint);
			for (String otherPlayer : ControlRoom.gamePlayerPosition.get(roomId).values()) {

				if (!otherPlayer.equals(playerId)) {
					this.send_msg_judge(otherPlayer, map_Str);
				}
			}

			if (this.connections.containsKey(playerId)) {
				this.mopaiCaozuo(playerId, roomId, pai);
			} else {
				if (ControlRoom.roomRule.get(roomId).getRule().equals("ai")) {
					// 打牌
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						public void run() {

							chuPai(playerId, roomId, pai);
						}
					}, 6000);// 设定指定的时间time,此处为1000毫秒 即ai摸牌1s后出牌
				}
			}
		} else {
			this.gameOver(roomId, null);
			return;
		}
	}

	/**
	 * 告诉玩家现在该谁出牌
	 * 
	 * @param playerId
	 * @param roomId
	 */
	private void sendNextMsg(String playerId, String roomId) {
		Map<String, Object> hint = new HashMap<String, Object>();
		hint.put("title", "nextPlayer");
		hint.put("remainPai", ControlRoom.allLogic.get(roomId).mj.size());
		hint.put("position", ControlRoom.getDirection(roomId, playerId));
		hint.put("playerId", playerId);
		String map_Str = JSONHelper.toJson(hint);
		for (String str : ControlRoom.allLogic.get(roomId).usermj.keySet()) {
			this.send_msg_judge(str, map_Str);
			if (str.equals(playerId)) {
//				if(ControlRoom.playerAdditional.get(roomId).get(playerId) != null && ControlRoom.roomRule.get(roomId).getRule()!=null && !ControlRoom.roomRule.get(roomId).getRule().contains("yaoJiDaiTi") && !ControlRoom.playerAdditional.get(roomId).get(playerId).contains("Ting")){
				this.huHint(playerId, roomId);
//				}
			}
		}
	}

	/**
	 * 结束对局
	 */
	private void gameOver(String roomId, String playerId) {
		Logic logic = ControlRoom.allLogic.get(roomId);
		GameRoom gameRoom = ControlRoom.roomRule.get(roomId);
		/**
		 * 查叫/查花猪/退税
		 */
		if (playerId == null) {
			for (String player : ControlRoom.gamePlayerPosition.get(roomId).values()) {
				//花猪
				if (!ControlRoom.quePaiMap.get(player).equals("") && logic.usermj.get(player).toString().contains(ControlRoom.quePaiMap.get(player))) {

					//是否有刮风下雨
					if (gameRoom.getRule().contains(Param.FY_H)) {
						if (logic.userPGSign.get(player).values().contains(4)) {
							//需要退税给其他玩家
							this.returnGangScore(player, roomId);
						}
					}
					//是否有缺一门
					if (gameRoom.getRule().contains(Param.QUE_H)) {
						//花猪  赔给其他玩家最大topMutiple的金币/积分
						this.checkHua(player, roomId);
					}
					//判断是否有叫
				} else{
					if (this.canHu(player, roomId).size() == 0) {
						if (gameRoom.getRule().contains(Param.FY_H)) {
							if (logic.userPGSign.get(player).values().contains(4)) {
								//需要退税给其他玩家
								this.returnGangScore(player, roomId);
							}
						}
						//没叫  赔叫给有叫未胡的玩家
						this.compensateHua(player, roomId);
					}
				}
			}
			this.update_player_gameInfo(roomId);
		}

		//new FileUtil().string2File(ControlRoom.stepRecord.get(roomId).toString(), "F:/stepRecord_" + roomId + ".txt");
		Map<String, Object> hint = new HashMap<String, Object>();
		hint.put("title", "over");
		hint.put("roomId", roomId);
		ControlRoom.allLogic.get(roomId).endFlag = true;

		/**
		 * 手牌
		 */
		Map<String, List<String>> shouPai = new HashMap<String, List<String>>();
		for (String str : ControlRoom.allLogic.get(roomId).usermj.keySet()) {
			shouPai.put(ControlRoom.getDirection(roomId, str), ControlRoom.allLogic.get(roomId).usermj.get(str));
		}
		hint.put("shouPai", shouPai);

		if (ControlRoom.roomRule.get(roomId).getType() == 0) {// 对局类型 0 好友 1 金币
															  // 2竞技
			LedgerService ledgerService = (LedgerService) ControlRoom.getServiceBean("LedgerService");

			hint.put("type", "friends");
			hint.put("gameNum", ControlRoom.friendGameNum.get(roomId));
			//获取当盘流水
			Map<String, Object> map_ledger = new HashMap<>();
			for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {

				map_ledger.put(ControlRoom.getDirection(roomId, str), ledgerService.getListByPlayer(str, ControlRoom.friendGameNum.get(roomId)));

			}
			hint.put("one_settlement", map_ledger);

			if (ControlRoom.friendGameNum.get(roomId) < ControlRoom.roomRule.get(roomId).getGameNum()) {

				hint.put("hint", "oneOver");
			} else {

				hint.put("hint", "allOver");

				//获取整盘流水
				Map<String, Object> all_settlement = new HashMap<>();
				for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
					List<Ledger> all_ledger = ledgerService.getListByPlayer(str);
					Map<String, Integer> kind_map = new HashMap<>();
					Map<String, Object> all_map = new HashMap<>();
					Integer sum = 0;
					for (Ledger ledger : all_ledger) {
						sum += ledger.getLedgerScore();
						if (kind_map.containsKey(ledger.getLedgerName())) {
							kind_map.put(ledger.getLedgerName(), kind_map.get(ledger.getLedgerName()) + 1);
						} else {
							kind_map.put(ledger.getLedgerName(), 1);
						}
					}
					all_map.put("sum", sum);
					all_map.put("values", kind_map);
					all_settlement.put(ControlRoom.getDirection(roomId, str), all_map);
				}
				all_settlement.put("title", "all_settlement");
				//hint.put("all_settlement", all_settlement);
				String map_Str = JSONHelper.toJson(all_settlement);
				for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
					this.send_msg_judge(str, map_Str);
				}

			}
		} else if (ControlRoom.roomRule.get(roomId).getType() == 1) {

			hint.put("type", "gold");
		} else {

			hint.put("type", "contest");
		}
		String hint_Str = JSONHelper.toJson(hint);
		System.out.println(hint_Str);
		for (String str : ControlRoom.allLogic.get(roomId).usermj.keySet()) {
			if ((playerId != null && str.equals(playerId)) || playerId == null) {
				//System.out.println(hint);
				this.send_msg_judge(str, hint_Str);
			}
		}
		// 清除数据
		// 缺Map
		ControlRoom.queChoosed.remove(roomId);
		ControlRoom.swapArray.remove(roomId);

		/**
		 * 判断是否 是整盘结束
		 */
		if ("allOver".equals(hint.get("hint"))) {

			for (String str : ControlRoom.allLogic.get(roomId).usermj.keySet()) {

				// 删除流水
				((LedgerService) ControlRoom.getServiceBean("LedgerService")).deleteLedger(str);
				((GameRoomService) ControlRoom.getServiceBean("GameRoomService")).deleteRelationByPlayerId(str, roomId);
				if (ControlRoom.quePaiMap.containsKey(str)) {
					ControlRoom.quePaiMap.remove(str);
				}

			}
			((GameRoomService) ControlRoom.getServiceBean("GameRoomService")).deleteGameRoomById(roomId);
			this.clearData(roomId);
		}

	}

	/**
	 * 退税
	 */
	private void returnGangScore(String playerId, String roomId) {

		GameRoom gameRoom = ControlRoom.roomRule.get(roomId);
		PlayerService playerService = (PlayerService) ControlRoom.getServiceBean("PlayerService");
		LedgerService ledgerService = (LedgerService) ControlRoom.getServiceBean("LedgerService");

		Integer gameNum = 0;
		if (ControlRoom.friendGameNum.containsKey(roomId)) {
			gameNum = ControlRoom.friendGameNum.get(roomId);
		}

		for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
			if (str.equals(playerId))
				continue;
			//获取当前玩家刮风下雨list
			String target = ControlRoom.getMjPosition(ControlRoom.getRelativePosition(roomId, str, playerId));
			List<Ledger> ledgerList = ledgerService.getFyList(str, gameNum, target);
			if (ledgerList.size() > 0) {
				Integer score = 0;
				for (Ledger ledger : ledgerList) {
					score += ledger.getLedgerScore() * -1;

				}
				Integer ledgerNum = score / gameRoom.getBaseScore();
				Ledger addLedger = new Ledger();
				Ledger reduceLedger = new Ledger();

				addLedger.setPlayerId(str);
				addLedger.setLedgerType("0");
				if (gameRoom.getType() == 0) {
					addLedger.setGameNum(gameNum);
				}
				addLedger.setLedgerNum(ledgerNum);
				addLedger.setLedgerScore(score);
				addLedger.setTarget(target);
				addLedger.setLedgerName("被退税");
				addLedger.setLedgerDetail("");
				addLedger.setRoomId(roomId);
				ledgerService.addLedger(addLedger);

				reduceLedger.setPlayerId(playerId);
				reduceLedger.setLedgerType("1");
				if (gameRoom.getType() == 0) {
					reduceLedger.setGameNum(gameNum);
				}
				reduceLedger.setLedgerNum(ledgerNum);
				reduceLedger.setLedgerScore(-score);
				reduceLedger.setTarget(ControlRoom.getMjPosition(ControlRoom.getRelativePosition(roomId, playerId, str)));
				reduceLedger.setLedgerName("退税");
				reduceLedger.setLedgerDetail("");
				reduceLedger.setRoomId(roomId);
				ledgerService.addLedger(reduceLedger);

				// 判断当前对局类型 选择结算方式（个人信息中数据的变化）
				if (gameRoom.getType() == 0) {
					// 好友房进行积分结算

					playerService.scoreSettlement(addLedger.getPlayerId(), addLedger.getLedgerScore());
					ControlRoom.friendsScore.get(roomId).put(str, ControlRoom.friendsScore.get(roomId).get(str) + addLedger.getLedgerScore());

					playerService.scoreSettlement(reduceLedger.getPlayerId(), reduceLedger.getLedgerScore());
					ControlRoom.friendsScore.get(roomId).put(playerId, ControlRoom.friendsScore.get(roomId).get(playerId) + reduceLedger.getLedgerScore());

				} else {
					// 判断是金币场还是演示场
					if (!gameRoom.getRule().contains("ai")) {
						// 金币场
						playerService.goldSettlement(addLedger.getPlayerId(), addLedger.getLedgerScore());

						playerService.goldSettlement(reduceLedger.getPlayerId(), reduceLedger.getLedgerScore());

					}
				}
			}
		}

	}

	/**
	 * 赔叫
	 */
	private void compensateHua(String playerId, String roomId) {

		GameRoom gameRoom = ControlRoom.roomRule.get(roomId);
		PlayerService playerService = (PlayerService) ControlRoom.getServiceBean("PlayerService");
		LedgerService ledgerService = (LedgerService) ControlRoom.getServiceBean("LedgerService");
		for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
			if (str.equals(playerId))
				continue;
			List<String> huList = this.canHu(str, roomId);
			if (huList.size() > 0 && !ControlRoom.huPlayer.get(roomId).contains(str)) {
				//获取玩家能胡的最大番数
				List<Object> hudetail = this.getHuMutiple(str, roomId, huList, "detail");

				Integer ledgerNum = 0;
				String ledger_datail = "";
				for (Object detail : hudetail) {
					JSONObject detailObj = JSONObject.fromObject(detail);
					Integer multiple = detailObj.getInt("multiple");
					if (multiple > ledgerNum) {
						ledgerNum = multiple;
						ledger_datail = detailObj.getString("detail");
					}
				}

				Ledger addLedger = new Ledger();
				Ledger reduceLedger = new Ledger();

				addLedger.setPlayerId(str);
				addLedger.setLedgerType("0");
				if (gameRoom.getType() == 0) {
					addLedger.setGameNum(ControlRoom.friendGameNum.get(roomId));
				}
				addLedger.setLedgerNum(ledgerNum);
				addLedger.setLedgerScore(ledgerNum * gameRoom.getBaseScore());
				addLedger.setTarget(ControlRoom.getMjPosition(ControlRoom.getRelativePosition(roomId, str, playerId)));
				addLedger.setLedgerName("查大叫");

				addLedger.setLedgerDetail(ledger_datail);
				addLedger.setRoomId(roomId);
				// 保存到数据库
				ledgerService.addLedger(addLedger);

				reduceLedger.setPlayerId(playerId);
				reduceLedger.setLedgerType("1");
				if (gameRoom.getType() == 0) {
					reduceLedger.setGameNum(ControlRoom.friendGameNum.get(roomId));
				}
				reduceLedger.setLedgerNum(ledgerNum);
				reduceLedger.setLedgerScore(-ledgerNum * gameRoom.getBaseScore());
				reduceLedger.setTarget(ControlRoom.getMjPosition(ControlRoom.getRelativePosition(roomId, playerId, str)));
				reduceLedger.setLedgerName("被查大叫");
				reduceLedger.setLedgerDetail(ledger_datail);
				reduceLedger.setRoomId(roomId);
				// 保存到数据库
				ledgerService.addLedger(reduceLedger);

				// 判断当前对局类型 选择结算方式（个人信息中数据的变化）
				if (gameRoom.getType() == 0) {
					// 好友房进行积分结算

					playerService.scoreSettlement(addLedger.getPlayerId(), addLedger.getLedgerScore());
					ControlRoom.friendsScore.get(roomId).put(str, ControlRoom.friendsScore.get(roomId).get(str) + addLedger.getLedgerScore());

					playerService.scoreSettlement(reduceLedger.getPlayerId(), reduceLedger.getLedgerScore());
					ControlRoom.friendsScore.get(roomId).put(playerId, ControlRoom.friendsScore.get(roomId).get(playerId) + reduceLedger.getLedgerScore());

				} else {
					// 判断是金币场还是演示场
					if (!gameRoom.getRule().contains("ai")) {
						// 金币场
						playerService.goldSettlement(addLedger.getPlayerId(), addLedger.getLedgerScore());

						playerService.goldSettlement(reduceLedger.getPlayerId(), reduceLedger.getLedgerScore());

					}
				}
			}
		}
	}

	/**
	 * 查花猪
	 */
	private void checkHua(String playerId, String roomId) {
		Logic logic = ControlRoom.allLogic.get(roomId);
		GameRoom gameRoom = ControlRoom.roomRule.get(roomId);
		PlayerService playerService = (PlayerService) ControlRoom.getServiceBean("PlayerService");
		LedgerService ledgerService = (LedgerService) ControlRoom.getServiceBean("LedgerService");

		Integer ledgerNum = 1;
		Integer topMutiple = gameRoom.getTopMultiple();
		if (!gameRoom.getGameArea().equals("langZhong") && !gameRoom.getGameArea().equals("guangAn")) {
			for (int i = 0; i < topMutiple; i++) {
				ledgerNum = ledgerNum * 2;
			}
		} else {
			ledgerNum = topMutiple;
		}

		for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
			if (str.equals(playerId))
				continue;
			if (!str.equals(playerId) && logic.usermj.get(str).toString().contains(ControlRoom.quePaiMap.get(str))) {
				Ledger addLedger = new Ledger();
				Ledger reduceLedger = new Ledger();

				addLedger.setPlayerId(str);
				addLedger.setLedgerType("0");
				if (gameRoom.getType() == 0) {
					addLedger.setGameNum(ControlRoom.friendGameNum.get(roomId));
				}
				addLedger.setLedgerNum(ledgerNum);
				addLedger.setLedgerScore(ledgerNum * gameRoom.getBaseScore());
				addLedger.setTarget(ControlRoom.getMjPosition(ControlRoom.getRelativePosition(roomId, str, playerId)));
				addLedger.setLedgerName("查花猪");
				addLedger.setLedgerDetail("");
				addLedger.setRoomId(roomId);
				ledgerService.addLedger(addLedger);

				reduceLedger.setPlayerId(playerId);
				reduceLedger.setLedgerType("1");
				if (gameRoom.getType() == 0) {
					reduceLedger.setGameNum(ControlRoom.friendGameNum.get(roomId));
				}
				reduceLedger.setLedgerNum(ledgerNum);
				reduceLedger.setLedgerScore(-ledgerNum * gameRoom.getBaseScore());
				reduceLedger.setTarget(ControlRoom.getMjPosition(ControlRoom.getRelativePosition(roomId, playerId, str)));
				reduceLedger.setLedgerName("被查花猪");
				reduceLedger.setLedgerDetail("");
				reduceLedger.setRoomId(roomId);
				ledgerService.addLedger(reduceLedger);

				// 判断当前对局类型 选择结算方式（个人信息中数据的变化）
				if (gameRoom.getType() == 0) {
					// 好友房进行积分结算

					playerService.scoreSettlement(addLedger.getPlayerId(), addLedger.getLedgerScore());
					ControlRoom.friendsScore.get(roomId).put(str, ControlRoom.friendsScore.get(roomId).get(str) + addLedger.getLedgerScore());

					playerService.scoreSettlement(reduceLedger.getPlayerId(), reduceLedger.getLedgerScore());
					ControlRoom.friendsScore.get(roomId).put(playerId, ControlRoom.friendsScore.get(roomId).get(playerId) + reduceLedger.getLedgerScore());

				} else {
					// 判断是金币场还是演示场
					if (!gameRoom.getRule().contains("ai")) {
						// 金币场
						playerService.goldSettlement(addLedger.getPlayerId(), addLedger.getLedgerScore());

						playerService.goldSettlement(reduceLedger.getPlayerId(), reduceLedger.getLedgerScore());

					}
				}

			}
		}
	}

	/**
	 * 更新player_gameInfo
	 */
	private void update_player_gameInfo(String roomId) {

		//判断输赢
		Integer type = ControlRoom.roomRule.get(roomId).getType();
		boolean isFirend = type == 0;
		Integer gameNum = isFirend ? ControlRoom.friendGameNum.get(roomId) : 0;

		LedgerService ledgerService = (LedgerService) ControlRoom.getServiceBean("LedgerService");
		PlayerService playerService = (PlayerService) ControlRoom.getServiceBean("PlayerService");

		for (String player : ControlRoom.gamePlayerPosition.get(roomId).values()) {

			PlayerGameInfo playerGameInfo = playerService.getPlayerGameInfo(player);
			playerGameInfo.setGameNum(playerGameInfo.getGameNum() + 1);
			if (ledgerService.isWin(player, gameNum)) {
				playerGameInfo.setWinNum(playerGameInfo.getWinNum() + 1);
				playerGameInfo.setContinuityWin(playerGameInfo.getContinuityWin() + 1);
			} else {
				playerGameInfo.setContinuityWin(0);
			}
			playerService.updatePlayerGameInfo(playerGameInfo);

		}
	}

	/**
	 * 下一位摸牌玩家 血战判断是否结束是在玩家胡牌后判断是否结束
	 */
	private String getNextPlayerId(String playerId, String roomId) {

		String nextPlayerId = ControlRoom.allLogic.get(roomId).getNextPlayer(playerId);
		if (ControlRoom.roomRule.get(roomId).getGameType() == 0) {// 血战
			boolean flag = true;
			while (flag) {
				if (ControlRoom.huPlayer.get(roomId).contains(nextPlayerId)) {
					nextPlayerId = ControlRoom.allLogic.get(roomId).getNextPlayer(nextPlayerId);
				} else {
					flag = false;
				}
			}
		}
		return nextPlayerId;
	}

	/**
	 * 判断是否能和牌
	 * 
	 */
	private List<String> canHu(List<String> paiList, String playerId, String roomId) {
		List<String> huPaiList = new ArrayList<String>();
		String gameArea = ControlRoom.roomRule.get(roomId).getGameArea();
		String rule = ControlRoom.roomRule.get(roomId).getRule();
		//GameRoom gameRoom = ControlRoom.roomRule.get(roomId);
		String paiNumType = Param.PAINUM_THIRTEEN;
		if (gameArea.equals("langZhong")) {// 阆中 乱清 明七对
			if (HuPaiLogic.luanQing(paiList, ControlRoom.quePaiMap.get(playerId), ControlRoom.allLogic.get(roomId).userPG.get(playerId))) {
				String str = String.valueOf(paiList.get(0).charAt(1));
				for (int i = 1; i < 10; i++) {
					huPaiList.add(i + str);
				}
			}
			if (paiList.size() == 10) {// 明七对
				if (HuPaiLogic.qiDuiisHu(FenJie.fenJie(paiList))) {
					huPaiList.add(ControlRoom.allLogic.get(roomId).userPG.get(playerId).get(0));
				}
			}
			huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList, Param.TY_LZ, paiNumType, ControlRoom.quePaiMap.get(playerId)));
			huPaiList.addAll(HuPaiLogic.canHu(paiList, ControlRoom.quePaiMap.get(playerId)));
		} else if (gameArea.equals("yiBin")) {// 宜宾 听用
			String tyType = rule.contains(Param.YB_ONE) ? Param.YB_ONE : Param.YB_TWO;
			huPaiList.addAll(HuPaiLogic.tingYongList(paiList, ControlRoom.quePaiMap.get(playerId), ControlRoom.benJin.get(roomId), tyType));
		} else if (gameArea.equals("luZhou")) {// 泸州 鬼牌
			huPaiList.addAll(HuPaiLogic.guiHuList(paiList, ControlRoom.quePaiMap.get(playerId)));
		} else if (gameArea.equals("guangAn")) {// 广安 中发白
			huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList, Param.TY_ZFB, Param.PAINUM_THIRTEEN, ControlRoom.quePaiMap.get(playerId)));
			huPaiList.addAll(HuPaiLogic.canHu(paiList, ControlRoom.quePaiMap.get(playerId)));
			/******************/
		} else if (gameArea.equals("ziGong") || gameArea.equals("neiJiang")) {// 自贡，内江
			huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList, "", paiNumType, ControlRoom.quePaiMap.get(playerId)));
			huPaiList.addAll(HuPaiLogic.canHu(paiList, ControlRoom.quePaiMap.get(playerId)));
		} else if(gameArea.equals("wanZhou") && rule.contains("yaoJiDaiTi")){
			int[] count = FenJie.fenJie(ControlRoom.allLogic.get(roomId).usermj.get(playerId));
			int laizi = count[0];
			count[0]=0;
			paiList = FenJie.fanFenJie(count);
			System.out.println(playerId+paiList);
			System.out.println("癞子牌个数：====》》》》"+laizi);
			//癞子数
			if(laizi==4){
				huPaiList.addAll(HuPaiLogic.roundNumFour(paiList));
				huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList,4));
			}if(laizi==3){
				huPaiList.addAll(HuPaiLogic.roundNumThree(paiList));
				huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList,3));
			}if(laizi==2){
				huPaiList.addAll(HuPaiLogic.roundNumTwo(paiList));
				huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList,2));
			}if(laizi==1){
				huPaiList.addAll(HuPaiLogic.roundNumOne(paiList));
				huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList,1));
			}else{
				huPaiList.addAll(HuPaiLogic.roundNoNum(paiList));
				huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList,0));
			}
		} else {// 成都，西昌,德阳
			if(gameArea.equals("DaoDaoHu") && !rule.contains("huQiDui")){
				huPaiList.addAll(HuPaiLogic.canHu(paiList, ControlRoom.quePaiMap.get(playerId)));
			}else{
				huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList, "", paiNumType, ControlRoom.quePaiMap.get(playerId)));
				huPaiList.addAll(HuPaiLogic.canHu(paiList, ControlRoom.quePaiMap.get(playerId)));
			}
		}

		// 去重

		return huPaiList;
	}

	/**
	 * 判断是否能和牌 阆中 乱清，明七对 宜宾 听用 泸州 鬼牌 广安 中发白
	 */
	@SuppressWarnings("unused")
	private List<String> canHu(String playerId, String roomId) {
		List<String> huPaiList = new ArrayList<String>();
		List<String> paiList = ControlRoom.allLogic.get(roomId).usermj.get(playerId);

		/**
		 * 如果是选缺，并且手中的缺牌还没有打完，return null
		 */
		if (ControlRoom.roomRule.get(roomId).getRule().contains(Param.QUE_H)) {
			String queType = ControlRoom.quePaiMap.get(playerId);
			if (paiList.toString().contains(queType)) {
				return huPaiList;
			}
		}

		GameRoom gameRoom = ControlRoom.roomRule.get(roomId);
		String paiNumType = Param.PAINUM_THIRTEEN;
		if (gameRoom.getGameArea().equals("langZhong")) {// 阆中 乱清 明七对
			if (HuPaiLogic.luanQing(paiList, ControlRoom.quePaiMap.get(playerId), ControlRoom.allLogic.get(roomId).userPG.get(playerId))) {
				String str = String.valueOf(paiList.get(0).charAt(1));
				for (int i = 1; i < 10; i++) {
					huPaiList.add(i + str);
				}
			}
			if (paiList.size() == 10) {// 明七对
				if (HuPaiLogic.qiDuiisHu(FenJie.fenJie(paiList))) {
					huPaiList.add(ControlRoom.allLogic.get(roomId).userPG.get(playerId).get(0));
				}
			}
			huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList, Param.TY_LZ, paiNumType, ControlRoom.quePaiMap.get(playerId)));
			huPaiList.addAll(HuPaiLogic.canHu(paiList, ControlRoom.quePaiMap.get(playerId)));
		} else if (gameRoom.getGameArea().equals("yiBin")) {// 宜宾 听用
			String tyType = gameRoom.getRule().contains(Param.YB_ONE) ? Param.YB_ONE : Param.YB_TWO;
			huPaiList.addAll(HuPaiLogic.tingYongList(paiList, ControlRoom.quePaiMap.get(playerId), ControlRoom.benJin.get(roomId), tyType));
		} else if (gameRoom.getGameArea().equals("luZhou")) {// 泸州 鬼牌
			huPaiList.addAll(HuPaiLogic.guiHuList(paiList, ControlRoom.quePaiMap.get(playerId)));
		} else if (gameRoom.getGameArea().equals("guangAn")) {// 广安 中发白
			huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList, Param.TY_ZFB, Param.PAINUM_THIRTEEN, ControlRoom.quePaiMap.get(playerId)));
			huPaiList.addAll(HuPaiLogic.canHu(paiList, ControlRoom.quePaiMap.get(playerId)));
			/******************/
		} else if (gameRoom.getGameArea().equals("ziGong") || gameRoom.getGameArea().equals("neiJiang")) {// 自贡，内江
			String paiNum = gameRoom.getRule().contains(Param.PAINUM_SEVEN) ? Param.PAINUM_SEVEN : gameRoom.getRule().contains(Param.PAINUM_TEN) ? Param.PAINUM_TEN : Param.PAINUM_THIRTEEN;
			huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList, "", paiNumType, ControlRoom.quePaiMap.get(playerId)));
			huPaiList.addAll(HuPaiLogic.canHu(paiList, ControlRoom.quePaiMap.get(playerId)));
		}else if(gameRoom.getGameArea().equals("wanZhou") && gameRoom.getRule().contains("yaoJiDaiTi")){
			int[] count;
			int laizi;
			count = FenJie.fenJie(ControlRoom.allLogic.get(roomId).usermj.get(playerId));
			laizi = count[0];
			count[0]=0;
			paiList = FenJie.fanFenJie(count);
			System.out.println(playerId+paiList);
			System.out.println("癞子牌个数：====》》》》"+laizi);
			//癞子数
			if(laizi==4){
				huPaiList.addAll(HuPaiLogic.roundNumFour(paiList));
				huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList,4));
			}if(laizi==3){
				huPaiList.addAll(HuPaiLogic.roundNumThree(paiList));
				huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList,3));
			}if(laizi==2){
				huPaiList.addAll(HuPaiLogic.roundNumTwo(paiList));
				huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList,2));
			}if(laizi==1){
				huPaiList.addAll(HuPaiLogic.roundNumOne(paiList));
				huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList,1));
			}else{
				huPaiList.addAll(HuPaiLogic.roundNoNum(paiList));
				huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList,0));
			}
		}else {// 成都，西昌,德阳
			
			if(gameRoom.getGameArea().equals("DaoDaoHu") && !gameRoom.getRule().contains("huQiDui")){
				huPaiList.addAll(HuPaiLogic.canHu(paiList, ControlRoom.quePaiMap.get(playerId)));
			}else{
				huPaiList.addAll(HuPaiLogic.qiDuiCanHu(paiList, "", paiNumType, ControlRoom.quePaiMap.get(playerId)));
				huPaiList.addAll(HuPaiLogic.canHu(paiList, ControlRoom.quePaiMap.get(playerId)));
			}
			
		}

		// 去重
		return huPaiList;
	}

	/**
	 * 根据玩家Id获取玩家信息
	 */
	private Player getPlayerInfo(String playerId) {
		return ((PlayerService) ContextLoader.getCurrentWebApplicationContext().getBean("PlayerService")).getPlayerById(playerId);
	}

	/**
	 * 获取对局中其他玩家的信息
	 */
	private Map<String, Player> getotherinfoList(String roomId, String playerId) {
		Map<String, Player> playerInfo = new HashMap<String, Player>();

		for (Map.Entry<String, String> entry : ControlRoom.gamePlayerPosition.get(roomId).entrySet()) {
			if (!entry.getValue().equals(playerId))
				playerInfo.put(entry.getKey(), ((PlayerService) ContextLoader.getCurrentWebApplicationContext().getBean("PlayerService")).getPlayerById(entry.getValue()));
		}
		//System.out.println(JSONHelper.toJson(playerInfo) + "playerInfo");
		return playerInfo;
	}

	/**
	 * 玩家加入房间
	 */
	private void addGamePosition(String roomId, String playerId) {

		Map<String, String> gamePosition = ControlRoom.gamePlayerPosition.get(roomId);
		if (gamePosition != null) {
			if (gamePosition.get("south") == null) {
				gamePosition.put("south", playerId);
			} else if (gamePosition.get("east") == null) {
				gamePosition.put("east", playerId);
			} else if (gamePosition.get("north") == null) {
				if (ControlRoom.roomRule.get(roomId).getPlayerNum() == 4) {
					gamePosition.put("north", playerId);
				} else {
					gamePosition.put("west", playerId);
				}
			} else if (gamePosition.get("west") == null) {
				gamePosition.put("west", playerId);
			}
		}

		/**
		 * 判断房间是否满了 并且是金币场
		 */
		if (ControlRoom.roomRule.get(roomId).getType() == 1 && ControlRoom.gamePlayerPosition.get(roomId).size() == ControlRoom.roomRule.get(roomId).getPlayerNum() && !ControlRoom.roomRule.get(roomId).getRule().equals("ai")) {
			// 创建关系
			for (String id : ControlRoom.gamePlayerPosition.get(roomId).values()) {
				// 创建关系
				((GameRoomService) ContextLoader.getCurrentWebApplicationContext().getBean("GameRoomService")).createRelation(id, roomId);
			}

			// 发送对局开始
			this.roomFaPai(roomId);
		}

	}

	/**
	 * 获取房间中的玩家顺序
	 * 
	 */
	private List<String> getPlayerList(String roomId) {
		List<String> list = new ArrayList<>();
		// 庄家
		String bankerPlayerId = ControlRoom.getDirection(roomId, ControlRoom.this_banker.get(roomId));
		if (bankerPlayerId.equals("south")) {

			if (ControlRoom.gamePlayerPosition.get(roomId).get("south") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("south"));
			}
			if (ControlRoom.gamePlayerPosition.get(roomId).get("east") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("east"));
			}
			if (ControlRoom.gamePlayerPosition.get(roomId).get("north") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("north"));
			}
			if (ControlRoom.gamePlayerPosition.get(roomId).get("west") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("west"));
			}
		} else if (bankerPlayerId.equals("east")) {

			if (ControlRoom.gamePlayerPosition.get(roomId).get("east") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("east"));
			}
			if (ControlRoom.gamePlayerPosition.get(roomId).get("north") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("north"));
			}
			if (ControlRoom.gamePlayerPosition.get(roomId).get("west") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("west"));
			}
			if (ControlRoom.gamePlayerPosition.get(roomId).get("south") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("south"));
			}
		} else if (bankerPlayerId.equals("north")) {

			if (ControlRoom.gamePlayerPosition.get(roomId).get("north") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("north"));
			}
			if (ControlRoom.gamePlayerPosition.get(roomId).get("west") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("west"));
			}
			if (ControlRoom.gamePlayerPosition.get(roomId).get("south") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("south"));
			}
			if (ControlRoom.gamePlayerPosition.get(roomId).get("east") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("east"));
			}
		} else if (bankerPlayerId.equals("west")) {

			if (ControlRoom.gamePlayerPosition.get(roomId).get("west") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("west"));
			}
			if (ControlRoom.gamePlayerPosition.get(roomId).get("south") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("south"));
			}
			if (ControlRoom.gamePlayerPosition.get(roomId).get("east") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("east"));
			}
			if (ControlRoom.gamePlayerPosition.get(roomId).get("north") != null) {

				list.add(ControlRoom.gamePlayerPosition.get(roomId).get("north"));
			}
		}
		//System.out.println(bankerPlayerId + "          选庄          " + list);
		return list;
	}

	/**
	 * 和牌提示
	 */
	private void huHint(String playerId, String roomId) {
		
		System.out.println("和牌提示======》》》》》》  "+playerId);
		/**
		 * 判断是否已经选择了听/摆
		 */
		if (ControlRoom.playerAdditional.get(roomId) != null && ControlRoom.playerAdditional.get(roomId).get(playerId) != null && (ControlRoom.playerAdditional.get(roomId).get(playerId).contains("Bai") || ControlRoom.playerAdditional.get(roomId).get(playerId).contains("Ting"))) {

			return;
		}
		if(ControlRoom.roomRule.get(roomId).getRule().contains("yaoJiDaiTi")){
			return;
		}

		List<Object> list = new ArrayList<>();
		Integer paiLen = ControlRoom.allLogic.get(roomId).usermj.get(playerId).size();
		/**
		 * 如果有摆，判断移除的牌是否是已摆玩家要胡的牌
		 */
		List<String> otherHu_list = this.get_otherBaiHu(roomId, playerId);
		for (int i = 0; i < paiLen; i++) {
			String pai = ControlRoom.allLogic.get(roomId).usermj.get(playerId).get(i);
			if (otherHu_list.contains(pai)) {
				continue;
			}
			ControlRoom.allLogic.get(roomId).usermj.get(playerId).remove(i);
			List<String> huList = this.canHu(playerId, roomId);
			if (huList.size() > 0) {// 能胡

				List<Object> huDetail = this.getHuMutiple(playerId, roomId, huList, "ledgerNum");
				Map<String, Object> huhintlistMap = new HashMap<String, Object>();
				huhintlistMap.put("index", i);
				huhintlistMap.put("huHinList", huDetail);
				list.add(huhintlistMap);
			}
			ControlRoom.allLogic.get(roomId).usermj.get(playerId).add(i, pai);
		}

		if (list.size() > 0) {
			Map<String, Object> huHint = new HashMap<String, Object>();
			huHint.put("title", "huHint");
			huHint.put("huHintListMap", list);
			/**
			 * 判断当前地域是否能摆
			 */
			List<String> additional = new ArrayList<String>();// 附加（摆/听）
			if (ControlRoom.roomRule.get(roomId).getRule().contains(Param.BAI_H)) {
				additional.add(Param.BAI_H);
			}
			/**
			 * 判断当前地域是否能听
			 */
			if (ControlRoom.roomRule.get(roomId).getRule().contains(Param.TING_H)) {
				additional.add(Param.TING_H);
			}
			if (additional.size() > 0) {
				huHint.put("additional", additional);
			}

			this.send_msg_judge(playerId, JSONHelper.toJson(huHint));
		}
	}

	/**
	 * 获取牌局中已摆玩家要胡的牌
	 */
	@SuppressWarnings("unchecked")
	private List<String> get_otherBaiHu(String roomId, String playerId) {

		List<String> huList = new ArrayList<>();
		for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
			if (!str.equals(playerId)) {
				if (ControlRoom.player_room_info.get(roomId).containsKey(str) && ControlRoom.player_room_info.get(roomId).get(str).containsKey("canHuList")) {
					huList.addAll((List<String>) ControlRoom.player_room_info.get(roomId).get(str).get("canHuList"));
				}
			}
		}
		return huList;
	}

	/**
	 * 和牌提示
	 */
	private void getHuHint(String playerId, String roomId) {

		List<String> huList = this.canHu(playerId, roomId);
		if (huList.size() > 0) {// 能胡
			List<Object> huDetail = this.getHuMutiple(playerId, roomId, huList, "ledgetNum");

			Map<String, Object> huHintMap = new HashMap<String, Object>();
			huHintMap.put("title", "getHuHint");
			huHintMap.put("huList", huDetail);
			System.out.println(huDetail);
			this.send_msg_judge(playerId, JSONHelper.toJson(huHintMap));

		}

	}

	/**
	 * 获取玩家所有能胡的牌，以及对应的番数
	 */
	private List<Object> getHuMutiple(String playerId, String roomId, List<String> huList, String type) {

		List<Object> huDetail = new ArrayList<>();
		for (String str : huList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pai", str);
			map.put("multiple", ControlMultiple.getMultiple(playerId, roomId, str).get("ledgerNum"));
			if (type.equals("detail")) {
				map.put("detail", ControlMultiple.getMultiple(playerId, roomId, str).get("detail"));
			}
			map.put("paiNum", this.getPaiNum(roomId, str, playerId));
			huDetail.add(map);
		}

		return huDetail;
	}

	/**
	 * 判断玩家手牌是否下叫
	 * 某些地域只有在报听后才能和牌
	 */
	private void havaJiao(String roomId, String playerId) {

		Map<String, Object> jiaoMsg = new HashMap<String, Object>();
		jiaoMsg.put("title", "havaJiao");
		List<String> huList = new ArrayList<>();

		//如果规则中有听 摆
		if (ControlRoom.roomRule.get(roomId).getRule().contains(Param.TING_H) || ControlRoom.roomRule.get(roomId).getRule().contains(Param.BAI_H)) {
			
			if (ControlRoom.playerAdditional.containsKey(roomId) && ControlRoom.playerAdditional.get(roomId).containsKey(playerId) && (ControlRoom.playerAdditional.get(roomId).get(playerId).contains("Ting") || ControlRoom.playerAdditional.get(roomId).get(playerId).contains("Bai"))) {

				huList = this.canHu(playerId, roomId);
			}
		} else {

			huList = this.canHu(playerId, roomId);
		}

		if (huList != null && huList.size() > 0) {
			jiaoMsg.put("flag", "t");
		} else {
			jiaoMsg.put("flag", "f");
		}
		this.send_msg_judge(playerId, JSONHelper.toJson(jiaoMsg));
	}

	/**
	 * 和牌的剩余张数 没有考虑摆牌的情况
	 */
	private Integer getPaiNum(String roomId, String pai, String playerId) {

		List<String> remainPai = new ArrayList<String>();
		remainPai.addAll(ControlRoom.allLogic.get(roomId).mj);
		for (String player : ControlRoom.allLogic.get(roomId).usermj.keySet()) {
			if (!player.equals(playerId)) {
				remainPai.addAll(ControlRoom.allLogic.get(roomId).usermj.get(player));
			}
		}

		Integer paiNum = 0;
		for (String str : remainPai) {
			if (str.equals(pai)) {
				paiNum++;
			}
		}

		return paiNum;
	}

	@SuppressWarnings("static-access")
	private void send_msg_judge(String playerId, String message) {
		if (this.connections.containsKey(playerId)) {
			this.connections.get(playerId).sendMessage(message);
		}
	}

	/**
	 * 发送消息
	 */
	public void sendMessage(String message) {
		try {
			this.session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 连接关闭
	 */
	@SuppressWarnings("static-access")
	@OnClose
	public void onClose(@PathParam("playerId") String playerId) {
		System.out.println(playerId + " 已掉线！");
		/**
		 * 判断玩家是否正在游戏中。 1.如果在游戏中，添加到掉线玩家list中。——>如果掉线玩家所在的对局完成，玩家没有重连。执行正常退出操作。
		 * 2.如果是正常结束（即玩家没有与房间建立联系/或者玩家所在的对局已经结束。），执行正常的退出操作。
		 */

		if (this.connections.containsKey(playerId)) {
			if (this.havaGame(playerId)) {

				//this.disconnectConnection.add(playerId);
			} else {
				this.exitMatching(playerId);
			}
			this.connections.remove(playerId);
			((PlayerService) ControlRoom.getServiceBean("PlayerService")).updatePlayerStatus(playerId, 0);
			//System.out.println(playerId + " 已退出！");

		}
		/*
		 * if (this.connections.containsKey(playerId)) {
		 * this.connections.remove(playerId); ((PlayerService)
		 * ContextLoader.getCurrentWebApplicationContext()
		 * .getBean("PlayerService")).updatePlayerStatus(playerId, 0);
		 * //System.out.println(playerId + " 已退出！");
		 * 
		 * }
		 */
	}

	/**
	 * 发生错误
	 */
	@SuppressWarnings("static-access")
	@OnError
	public void onError(@PathParam("playerId") String playerId, Session session, Throwable error) {
		System.out.println("websocket onerror - " + playerId);
		this.connections.remove(playerId);
		((PlayerService) ControlRoom.getServiceBean("PlayerService")).updatePlayerStatus(playerId, 0);

		error.printStackTrace();
	}

	/**
	 * 判断玩家是否有正在进行的对局
	 */
	private boolean havaGame(String playerId) {
		/**
		 * 获得玩家所对应的roomId
		 */
		String roomId = ((GameRoomService) ControlRoom.getServiceBean("GameRoomService")).isHavaRelation(playerId);
		if (roomId != null) {
			// 判断当前对局的类型
			if (ControlRoom.roomRule.get(roomId).getType() == 0) {
				// 好友房
				// if(gameroom)
				return true;
			} else if (ControlRoom.roomRule.get(roomId).getType() == 1) {
				// 金币房
				if (ControlRoom.allLogic.get(roomId) != null && ControlRoom.allLogic.get(roomId).mj.size() > 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 根据玩家Id获取roomId
	 */
	private String getRoomIdByPlayer(String playerId) {
		String roomId = "";
		for (Entry<String, Map<String, String>> entry : ControlRoom.gamePlayerPosition.entrySet()) {
			if (entry.getValue().values().contains(playerId)) {
				roomId = entry.getKey();
				break;
			}
		}
		return roomId;
	}

	/**
	 * 微信JSSDK权限注入
	 */
	private void getJssdkConfigure(String playerId, String url) {

		Map<String, String> config = Sign.sign(Token_ticket_Thread.jsapi_ticket.getTicket(), url);
		Map<String, Object> msg = new HashMap<>();
		msg.put("title", "sdkConfig");
		msg.put("config", config);
		this.send_msg_judge(playerId, JSONHelper.toJson(msg));
	}

	/**
	 * 解散房间
	 */
	@SuppressWarnings("static-access")
	private void js_room(String playerId, String roomId, String type, String chooseResult) {
		GameRoom gameRoom = ControlRoom.roomRule.get(roomId);
		if (type.equals("apply")) {
			//判断申请人的身份
			if (playerId.equals(gameRoom.getHouseOwnerId())) {
				//解散申请由房主发出，直接解散
				Map<String, String> map = new HashMap<>();
				map.put("title", "js_room");
				map.put("flag", "js_room_js");
				map.put("mark", "owner_start");
				String map_str = JSONHelper.toJson(map);
				for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
					this.send_msg_judge(str, map_str);
				}
				this.js_room_success(roomId);
			} else {
				//解散由其他玩家发出
				ControlRoom.js_roomPlayer.get(roomId).put(playerId, chooseResult);
				Map<String, String> map = new HashMap<>();
				map.put("title", "js_room");
				map.put("flag", "js_room_start");
				for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
					if (!str.equals(playerId)) {
						map.put("position", ControlRoom.getMjPosition(ControlRoom.getRelativePosition(roomId, str, playerId)));
						this.send_msg_judge(str, JSONHelper.toJson(map));
					}
				}
			}
		} else if (type.equals("chooseResult")) {
			if (playerId.equals(gameRoom.getHouseOwnerId())) {
				/**
				 * 房  主 
				 *  如果 房主选择了解散则直接解散  	
				 *  如果 房主选择了拒绝则解散失败	
				 */
				String flag = "";
				if (chooseResult.equals("Y")) {
					flag = "js_room_js";
				} else if (chooseResult.equals("N")) {
					flag = "js_room_fail";
				}
				//解散申请由房主发出，直接解散
				Map<String, String> map = new HashMap<>();
				map.put("title", "js_room");
				map.put("flag", flag);
				map.put("mark", "owner_decision");
				String map_str = JSONHelper.toJson(map);
				for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
					this.send_msg_judge(str, map_str);
				}
				if (flag.equals("js_room_js")) {
					this.js_room_success(roomId);
				}
			} else {
				if (ControlRoom.js_roomPlayer.get(roomId).containsKey(playerId)) {
					ControlRoom.js_roomPlayer.get(roomId).put(playerId, chooseResult);
					Integer playerNum = 0;
					for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
						if (this.connections.containsKey(str)) {
							playerNum++;
						}
					}
					if (ControlRoom.js_roomPlayer.get(roomId).size() >= playerNum) {
						//统计Y/N的人数
						Integer yNum = 0;
						Integer nNum = 0;
						for (Entry<String, String> entry : ControlRoom.js_roomPlayer.get(roomId).entrySet()) {
							if (entry.getValue().equals("Y")) {
								yNum++;
							} else {
								nNum++;
							}
						}
						String flag = "";
						if (yNum > nNum) {
							//解散成功
							flag = "js_room_js";
						} else {
							//解散失败
							flag = "js_room_fail";
						}
						Map<String, String> map = new HashMap<>();
						map.put("title", "js_room");
						map.put("flag", flag);
						map.put("mark", "player_decision");
						String map_str = JSONHelper.toJson(map);
						for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {
							this.send_msg_judge(str, map_str);
						}
						if (flag.equals("js_room_js")) {
							this.js_room_success(roomId);
						}
					}

				}

			}

		}
	}

	/**
	 * 好友房解散成功
	 */
	private void js_room_success(String roomId) {
		for (String str : ControlRoom.gamePlayerPosition.get(roomId).values()) {

			((GameRoomService) ControlRoom.getServiceBean("GameRoomService")).deleteRelationByPlayerId(str, roomId);
			// 删除流水
			((LedgerService) ControlRoom.getServiceBean("LedgerService")).deleteLedger(str);

			if (ControlRoom.quePaiMap.containsKey(str)) {
				ControlRoom.quePaiMap.remove(str);
			}
		}
		this.clearData(roomId);
	}

}
