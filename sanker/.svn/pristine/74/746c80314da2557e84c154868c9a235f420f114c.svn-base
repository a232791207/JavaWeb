package com.sanker.action.game;

import java.util.Date;

import com.sanker.action.DefaultAction;
import com.sanker.entity.game.GameRoom;
import com.sanker.service.game.GameRoomService;
import com.sanker.service.utils.JSONHelper;

/**
 * 房间
 * @author 滕洁
 * @date 2016-11-29
 */
public class GameRoomAction extends DefaultAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GameRoomService gameRoomService;
	
	public GameRoomService getGameRoomService() {
		return gameRoomService;
	}

	public void setGameRoomService(GameRoomService gameRoomService) {
		this.gameRoomService = gameRoomService;
	}

	/**
	 * 创建好友房间
	 */
	public void addGameRoom(){
		
		GameRoom gameRoom = new GameRoom();
		gameRoom.setHouseOwnerId(getString("houseOwnerId"));
		gameRoom.setType(getInteger("type"));
		gameRoom.setGameType(getInteger("gameType"));
		gameRoom.setGameArea(getString("area"));
		gameRoom.setGameNum(getInteger("gameNum"));
		gameRoom.setRule(getString("rule"));
		gameRoom.setPlayerNum(getInteger("playerNum"));
		Date date = new Date();
		gameRoom.setCreateTime(new java.sql.Timestamp(date.getTime()));
		
		String id = this.gameRoomService.addGameRoom(gameRoom).getId();
		System.out.println(id);
		Write(id);
		
		
		
	}
	
	
	
	/**
	 * 根据Id查询
	 */
	public void getGameRoomById(){
		String json = JSONHelper.toJson(this.gameRoomService.getGameRoomById(getString("roomId")));
		System.out.println(json);
		Write(json);
		
	}
	
	

}
