package com.sanker.entity.game;

import java.io.Serializable;

/**
 * 游戏记录 一局
 * @author 滕洁
 * @date 2016-11-17
 */
public class GameRecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String playerId;//玩家
	
	private String roundId;//对局Id
	
	private String roomId;//房间Id
	
	private String paiList;//牌型
	
	private Integer score;//总流水 
	
	private Integer multiple;//倍数
	
	private String details;//详情

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getPaiList() {
		return paiList;
	}

	public void setPaiList(String paiList) {
		this.paiList = paiList;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getMultiple() {
		return multiple;
	}

	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getRoundId() {
		return roundId;
	}

	public void setRoundId(String roundId) {
		this.roundId = roundId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

}
