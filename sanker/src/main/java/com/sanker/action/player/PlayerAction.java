package com.sanker.action.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.sanker.action.DefaultAction;
import com.sanker.entity.player.Player;
import com.sanker.entity.player.PlayerGameInfo;
import com.sanker.service.finance.RechargeService;
import com.sanker.service.player.PlayerRelationService;
import com.sanker.service.player.PlayerService;
import com.sanker.service.utils.JSONHelper;

/**
 * 玩家Action
 * @author 滕洁
 * @date 2016-11-21
 */
public class PlayerAction extends DefaultAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PlayerService playerService;
	
	private PlayerRelationService playerRelationService;
	
	private RechargeService rechargeService;

	public RechargeService getRechargeService() {
		return rechargeService;
	}

	public void setRechargeService(RechargeService rechargeService) {
		this.rechargeService = rechargeService;
	}

	public PlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}

	public PlayerRelationService getPlayerRelationService() {
		return playerRelationService;
	}

	public void setPlayerRelationService(PlayerRelationService playerRelationService) {
		this.playerRelationService = playerRelationService;
	}
	
	/**
	 * 个人信息
	 */
	public void getPlayerInfo(){
		Player playerInfo = this.playerService.getPlayerById(getString("playerId"));
		PlayerGameInfo gameInfo = this.playerService.getPlayerGameInfo(getString("playerId"));
		Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("playerInfo", playerInfo);
		infoMap.put("playerGameInfo", gameInfo);
		String json = JSONHelper.toJson(infoMap);
		System.out.println(json);
		Write(json);
	}
	
	
	/**
	 * 获取好友积分榜
	 */
	public void getScoreList(){
		List<Player> playerList = this.playerService.getScoreList(this.getString("playerId"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "scoreList");
		map.put("values", playerList);
		String json = JSONHelper.toJson(map);
		System.out.println(json);
		Write(json);
		
	}
	
	/**
	 * 好友魅力榜
	 */
	public void getCharmList(){
		List<Player> playerList = this.playerService.getCharmList(this.getString("playerId"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "charmList");
		map.put("values", playerList);
		String json = JSONHelper.toJson(map);
		System.out.println(json);
		Write(json);
		
	}
	
	/**
	 * 好友财富榜
	 */
	public void getWealthList(){
		List<Player> playerList = this.playerService.getWealthList(this.getString("playerId"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "wealthList");
		map.put("values", playerList);
		String json = JSONHelper.toJson(map);
		System.out.println(json);
		Write(json);
	}
	
	/**
	 * 修改玩家状态
	 */
	public void updatePlayerStatus(String playerId,Integer status){
		this.playerService.updatePlayerStatus(playerId, status);
	}
	
	
	
	
}
