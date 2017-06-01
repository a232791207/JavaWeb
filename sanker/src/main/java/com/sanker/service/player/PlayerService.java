package com.sanker.service.player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.sanker.entity.player.Player;
import com.sanker.entity.player.PlayerGameInfo;
import com.sanker.entity.player.PlayerRelation;
import com.sanker.service.core.BaseService;
import com.sanker.service.logic.ControlRoom;

/**
 * 玩家service
 * @author 滕洁
 * @date 2016-11-16
 */
public class PlayerService extends BaseService {

	/**
	 * 根据微信账号查询玩家
	 */
	public Player isBind(String winXinCode) {

		String hql = "from Player p where p.winXinCode = ?";
		try {
			Player player = (Player) this.getSession().createQuery(hql).setString(0, winXinCode).uniqueResult();
			if (player != null)
				return player;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 微信
	 * 用户是否注册到数据库
	 */
	public String getPlayerIdByWeixinCode(String weiXinCode) {

		try {
			return (String) this.getSession().createSQLQuery("SELECT id FROM player WHERE weiXinCode = ? LIMIT 1").setString(0, weiXinCode).uniqueResult();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 新建
	 */
	public Player addPlayer(Player player) {

		try {

			this.getSession().save(player);
			this.addPlayer_gameArea(player.getId());
    		this.addPlayerGameInfo(player.getId());
			return player;
		} catch (Exception e) {
			logger.error("error_addplayer!");
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 修改数据（更新）
	 */
	public Player updatePlayer(Player player) {

		try {
			this.getSession().update(player);
			return player;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 根据状态查询玩家
	 */
	@SuppressWarnings("unchecked")
	public List<Player> getPlayerListByStatus(String status) {
		try {
			List<Player> playerList = this.getSession().createQuery("from Player p where p.status = ?").list();
			return playerList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据Id查询玩家
	 */
	public Player getPlayerById(String id) {
		try {
			return (Player) this.getSession().createQuery("from Player p where p.id = ?").setString(0, id).uniqueResult();
		} catch (Exception e) {
			logger.error("error_getPlayerById!");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 修改用户状态
	 */
	public boolean updatePlayerStatus(String playerId, Integer status) {

		try {
			this.getSession().createSQLQuery("UPDATE player Set status = ? WHERE id = ?").setInteger(0, status).setString(1, playerId).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据Id查询昵称
	 */
	public String getNickNameById(String id) {

		try {
			return (String) this.getSession().createSQLQuery("SELECT weiXinNickName  FROM player WHERE id = ? LIMIT 1").setString(0, id).uniqueResult();
		} catch (Exception e) {
			return id;
		}

	}

	/**
	 * 查询玩家性别
	 */
	public char getSexByPlayerId(String playerId) {

		return (char) this.getSession().createSQLQuery("SELECT sex FROM player WHERE id = ? LIMIT 1").setString(0, playerId).uniqueResult();
	}

	/**
	 * 进行金币结算
	 */
	public boolean goldSettlement(String playerId, Integer score) {
		Integer resultNum = 0;
		try {
			this.getSession().createSQLQuery("update player set gold = (SELECT gold FROM (SELECT gold FROM " + "player WHERE id = ?) AS c) + ?  where id = ?")
					.setString(0, playerId).setInteger(1, score).setString(2, playerId).executeUpdate();
			if (score < 0) {
				resultNum = this.getSession().createSQLQuery("UPDATE player SET gold = 0 WHERE id = ? AND gold < 0").setString(0, playerId).executeUpdate();
			}
			if (resultNum > 0) {
				return false;
			}

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 进行积分结算
	 */
	public Integer scoreSettlement(String playerId, Integer score) {

		Integer resultNum = 0;
		try {
			resultNum = this.getSession().createSQLQuery("update player set score = (SELECT score FROM (SELECT score FROM " + "player WHERE id = ?) AS c) + ?   where id = ?")
					.setString(0, playerId).setInteger(1, score).setString(2, playerId).executeUpdate();
			return resultNum;
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * 新增玩家——地域关联
	 */
	public void addPlayer_gameArea(String playerId) {

		try {
			this.getSession().createSQLQuery("insert into player_gameArea(playerId) values(?)").setString(0, playerId).executeUpdate();
		} catch (Exception e) {
			System.out.println("sqlError_add player_gameArea");
			e.printStackTrace();
		}

	}

	/**
	 * 玩家所完地域+1
	 */
	public void updateGameAreaNum(String playerId, String gameArea) {

		this.getSession()
				.createSQLQuery(
						"UPDATE player_gameArea SET " + gameArea + " = (SELECT * FROM " + "(SELECT " + gameArea + " FROM player_gameArea WHERE playerId = '" + playerId
								+ "') AS c)+1 WHERE playerId = '" + playerId + "' limit 1").executeUpdate();
	}

	/**
	 * 获取玩家所玩地域排序
	 */
	public List<String> getGameAreaList(String playerId, String city) {

		try {
			Object obj = this.getSession().createSQLQuery("select * from player_gameArea where playerId = ? limit 1").setString(0, playerId).uniqueResult();
			List<String> gameAreaList = new ArrayList<String>();
			/**
			 * default 3成都  4自贡 5宜宾  6广安  7阆中  8泸州  9内江  10西昌  11德阳 12 血战 13 血流 14倒到胡
			 * 
			 * 排序方式   1.第一次登录/所有玩法都没有玩过 根据玩家所在Ip位置排序
			 * 			2.上一次所玩区域>次数>默认
			 */
			Map<String, Integer> map = new LinkedHashMap<String, Integer>();
			map.put("chengDu", (Integer) JSONArray.fromObject(obj).get(3));
			map.put("ziGong", (Integer) JSONArray.fromObject(obj).get(4));
			map.put("yiBin", (Integer) JSONArray.fromObject(obj).get(5));
			map.put("guangAn", (Integer) JSONArray.fromObject(obj).get(6));
			map.put("langZhong", (Integer) JSONArray.fromObject(obj).get(7));
			map.put("luZhou", (Integer) JSONArray.fromObject(obj).get(8));
			map.put("neiJiang", (Integer) JSONArray.fromObject(obj).get(9));
			map.put("xiChang", (Integer) JSONArray.fromObject(obj).get(10));
			map.put("deYang", (Integer) JSONArray.fromObject(obj).get(11));
			map.put("XueZhanDaoDi", (Integer) JSONArray.fromObject(obj).get(12));
			map.put("XueLiuChengHe", (Integer) JSONArray.fromObject(obj).get(13));
			map.put("DaoDaoHu", (Integer) JSONArray.fromObject(obj).get(14));
			map.put("wanZhou", (Integer) JSONArray.fromObject(obj).get(14));
			//根据次数排序
			gameAreaList = ControlRoom.sortMapByValue(map);
			/**
			 * 判断是否是需要当前ip介入
			 */
			if (!ControlRoom.noAllHavaElement(new ArrayList<Integer>(map.values()), 0)) {
				String gameArea = "";
				if (city.equals("成都")) {
					gameArea = "chengDu";
				} else if (city.equals("南充")) {
					gameArea = "langZhong";
				} else if (city.equals("泸州")) {
					gameArea = "luZhou";
				} else if (city.equals("内江")) {
					gameArea = "neiJiang";
				} else if (city.equals("自贡")) {
					gameArea = "ziGong";
				} else if (city.equals("宜宾")) {
					gameArea = "yiBin";
				} else if (city.equals("广安")) {
					gameArea = "guangAn";
				} else if (city.equals("西昌")) {
					gameArea = "xiChang";
				} else if (city.equals("德阳")) {
					gameArea = "deYang";
				} else if (city.equals("血战到底")) {
					gameArea = "XueZhanDaoDi";
				} else if (city.equals("血流成河")) {
					gameArea = "XueLiuChengHe";
				} else if (city.equals("倒倒胡")) {
					gameArea = "DaoDaoHu";
				} else if (city.equals("万州")) {
					gameArea = "DaoDaoHu";
				} else {
					gameArea = "other";
				}
				if (!gameArea.equals("other")) {
					gameAreaList.remove(gameArea);
					gameAreaList.add(0, gameArea);
				}
			}

			JSONArray areaArray = JSONArray.fromObject(obj);
			/*if(!gameArea.equals("other")){
				gameAreaList.add(gameArea);
			}*/
			if (!areaArray.get(2).toString().equals("null")) {//上一次地域不为空
				gameAreaList.remove(areaArray.get(2));
				gameAreaList.add(0, areaArray.get(2).toString());
			}

			return gameAreaList;

		} catch (Exception e) {
			e.printStackTrace();
			return ControlRoom.gameAreaList;
		}

	}

	/**
	 * 查询好友积分榜
	 */
	@SuppressWarnings("unchecked")
	public List<Player> getScoreList(String playerId) {

		try {

			List<Object[]> list = this.getSession()
					.createSQLQuery("SELECT p.*,r.* FROM player p INNER JOIN player_relation  r ON p.id = r.friendId WHERE r.playerId = ? ORDER BY p.score DESC")
					.addEntity("p", Player.class).addEntity("r", PlayerRelation.class).setString(0, playerId).list();
			List<Player> playerList = new ArrayList<Player>();
			Player player = null;
			PlayerRelation playerRelation = null;
			for (Object[] obj : list) {
				player = (Player) obj[0];
				playerRelation = (PlayerRelation) obj[1];
				player.setLikeFlag(playerRelation.getLikeFlag());
				playerList.add(player);
			}
			return playerList;
		} catch (Exception e) {
			System.out.println("error_getScoreList!");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询好友魅力榜
	 */
	@SuppressWarnings("unchecked")
	public List<Player> getCharmList(String playerId) {

		try {
			List<Object[]> list = this.getSession()
					.createSQLQuery("SELECT p.*,r.* FROM player p INNER JOIN player_relation  r ON p.id = r.friendId WHERE r.playerId = ? ORDER BY p.charm DESC")
					.addEntity("p", Player.class).addEntity("r", PlayerRelation.class).setString(0, playerId).list();
			List<Player> playerList = new ArrayList<Player>();
			Player player = null;
			PlayerRelation playerRelation = null;
			for (Object[] obj : list) {
				player = (Player) obj[0];
				playerRelation = (PlayerRelation) obj[1];
				player.setLikeFlag(playerRelation.getLikeFlag());
				playerList.add(player);
			}
			return playerList;
		} catch (Exception e) {
			System.out.println("error_getCharmList!");
			e.printStackTrace();
			return null;
		}

	}
	

	/**
	 * 查询好友财富榜
	 */
	@SuppressWarnings("unchecked")
	public List<Player> getWealthList(String playerId) {

		try {
			List<Object[]> list = this.getSession()
					.createSQLQuery("SELECT p.*,r.* FROM player p INNER JOIN player_relation  r ON p.id = r.friendId WHERE r.playerId = ? ORDER BY p.diamonds DESC")
					.addEntity("p", Player.class).addEntity("r", PlayerRelation.class).setString(0, playerId).list();
			List<Player> playerList = new ArrayList<Player>();
			Player player = null;
			PlayerRelation playerRelation = null;
			for (Object[] obj : list) {
				player = (Player) obj[0];
				playerRelation = (PlayerRelation) obj[1];
				player.setLikeFlag(playerRelation.getLikeFlag());
				playerList.add(player);
			}
			return playerList;
		} catch (Exception e) {
			System.out.println("error_getWealthList!");
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 查询游戏中信息
	 */
	public PlayerGameInfo getPlayerGameInfo(String playerId) {

		try {
			return (PlayerGameInfo) this.getSession().createQuery("from PlayerGameInfo where playerId = ? ").setString(0, playerId).uniqueResult();
		} catch (Exception e) {
			System.out.println("error_getPlayerGameInfo!");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 领取点赞
	 */
	public boolean receiveLike(String playerId) {

		try {
			this.getSession()
					.createSQLQuery(
							"UPDATE player SET gold = (SELECT * FROM(SELECT gold FROM player WHERE id = '" + playerId
									+ "') AS g1)+200 ,charm = (SELECT * FROM(SELECT charm FROM player WHERE id = '" + playerId + "') AS c1)+1 WHERE id = '" + playerId + "'")
					.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("error_receiveLike!");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取玩家gold
	 */
	public Integer getGoldByPlayerId(String playerId) {

		try {
			Integer gold = (Integer) this.getSession().createSQLQuery("select gold from player where id = ?").setString(0, playerId).uniqueResult();
			return gold;
		} catch (Exception e) {
			System.out.println("error_getGoldByPlayerId!");
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 更新游戏中数据
	 */
	public void updatePlayerGameInfo(PlayerGameInfo entity) {

		this.getSession().update(entity);
	}
	
	/**
	 * 添加玩家游戏中数据
	 */
	public void addPlayerGameInfo(String playerId){
		PlayerGameInfo entity = new PlayerGameInfo();
		entity.setPlayerId(playerId);
		entity.setContinuityWin(0);
		entity.setGameNum(0);
		entity.setMatchT1(0);
		entity.setMatchT2(0);
		entity.setMatchT3(0);
		entity.setMaxMultiple(0);
		entity.setMaxMultipleStr("");
		entity.setTrophy1(0);
		entity.setTrophy2(0);
		entity.setTrophy3(0);
		entity.setWinNum(0);
		
		this.getSession().save(entity);
	}
	
	/**
	 * 获取玩家openId
	 */
	public String get_openId(String playerId){
		
		return this.getSession().createSQLQuery("select weiXinCode from player where id = ?").setString(0, playerId).uniqueResult().toString();
	}
	
	/**
	 * 获取玩家砖石数量
	 */
	public Integer get_diamondNum(String playerId){
		
		return Integer.valueOf(this.getSession().createSQLQuery("select diamonds from player where id = ?").setString(0, playerId).uniqueResult().toString());
		
	}
	
	/**
	 * 修改玩家砖石数量 和金币数量
	 */
	public void update_diamond_gold(String type,String playerId,Integer num){
		
		 if("gold".equals(type)){
			 this.getSession().createSQLQuery("UPDATE player SET gold = (SELECT gold FROM (SELECT gold FROM player WHERE id = ?) AS g)+? WHERE id = ?").setString(0, playerId).setInteger(1, num).setString(2, playerId).executeUpdate();
		 }else if("diamond".equals(type)){
			 this.getSession().createSQLQuery("UPDATE player SET diamonds = (SELECT diamonds FROM (SELECT diamonds FROM player WHERE id = ?) AS d)+? WHERE id = ?").setString(0, playerId).setInteger(1, num).setString(2, playerId).executeUpdate();
		 }
	}
	
	/**
	 * 根据好友状态查询好友列表
	 */
	@SuppressWarnings("unchecked")
	public List<Player> get_friendListByStatus(String playerId,Integer status){
		
		String sql = "SELECT p.* FROM player AS p INNER JOIN player_relation AS r ON p.id = r.friendId WHERE r.playerId = ? AND p.status = ?";
		return this.getSession().createSQLQuery(sql).addEntity(Player.class).setString(0, playerId).setInteger(1,status).list();
	}
	
	/**
	 * 获取 玩家最大番数
	 */
	public Integer get_maxMultipleByPlayerId(String playerId){
		
		try {
			Integer multiple = (Integer) this.getSession().createSQLQuery("SELECT maxMultiple FROM player_gameinfo WHERE playerId = ?").setString(0, playerId).uniqueResult();
			return multiple;
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("获取 玩家最大番数_error");
			return 0;
		} 
	}
	
	/**
	 * 更新 玩家最大番数
	 */
	public void update_maxMultiple(String playerId,Integer multiple){
		
		try {
			this.getSession().createSQLQuery("UPDATE  player_gameinfo SET maxMultiple = ? WHERE playerId=?").setInteger(0, multiple).setString(1, playerId).executeUpdate();
		} catch (Exception e) {
			logger.error("update maxMultiple _ error");
		} 
	}
}
