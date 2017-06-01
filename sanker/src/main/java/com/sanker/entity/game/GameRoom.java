package com.sanker.entity.game;

import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.annotations.Expose;

/**
 * 房间
 * @author 滕洁
 * @date 2016-11-16
 */
public class GameRoom implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Expose
	private String id;
	@Expose
	private Integer roomId; //房间ID
	@Expose
	private String houseOwnerId;
	@Expose
	private String rule;
	@Expose
	private Integer gameNum;
	@Expose
	private Integer playerNum;
	@Expose
	private Integer type;//类型：（0:好友，1:金币，2:竞技）
	@Expose
	private Integer gameType;//对局类型（0:血战、1:血流 2：倒到胡 4: 撞大胡）
	@Expose
	private String gameArea;//所选区域
	
	private Timestamp createTime;
	@Expose
	private Integer baseScore;
	
	private Integer goldInterval;
	@Expose
	private Integer topMultiple;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getHouseOwnerId() {
		return houseOwnerId;
	}

	public void setHouseOwnerId(String houseOwnerId) {
		this.houseOwnerId = houseOwnerId;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public Integer getGameNum() {
		return gameNum;
	}

	public void setGameNum(Integer gameNum) {
		this.gameNum = gameNum;
	}

	public Integer getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(Integer playerNum) {
		this.playerNum = playerNum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getGameType() {
		return gameType;
	}

	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}

	public String getGameArea() {
		return gameArea;
	}

	public void setGameArea(String gameArea) {
		this.gameArea = gameArea;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getBaseScore() {
		return baseScore;
	}

	public void setBaseScore(Integer baseScore) {
		this.baseScore = baseScore;
	}

	public Integer getGoldInterval() {
		return goldInterval;
	}

	public void setGoldInterval(Integer goldInterval) {
		this.goldInterval = goldInterval;
	}

	public Integer getTopMultiple() {
		return topMultiple;
	}

	public void setTopMultiple(Integer topMultiple) {
		this.topMultiple = topMultiple;
	}

}
