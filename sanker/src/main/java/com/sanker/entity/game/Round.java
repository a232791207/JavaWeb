package com.sanker.entity.game;

import java.io.Serializable;
import java.util.Date;

/**
 * 回合 一盘
 * @author 滕洁
 * @date 2016-11-16
 */
public class Round implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String roomId;
	
	private String playerId;
	
	private Integer score;//流水
	
	private String paiList;//最大番数牌型
	
	private String title;//称号
	
	/*private List<GameRecord> gameRecord;*/
	
	private Date startTime;
	
	private Date endTime;
	
	private String details;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/*public List<GameRecord> getGameRecord() {
		return gameRecord;
	}

	public void setGameRecord(List<GameRecord> gameRecord) {
		this.gameRecord = gameRecord;
	}*/

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getPaiList() {
		return paiList;
	}

	public void setPaiList(String paiList) {
		this.paiList = paiList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
