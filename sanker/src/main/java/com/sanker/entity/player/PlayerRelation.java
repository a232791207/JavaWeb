package com.sanker.entity.player;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 玩家关系
 * @author 滕洁
 * @date 2016-11-21
 */
public class PlayerRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String playerId;

	private String friendId;

	private Integer likeFlag;//是否点赞 0:f  1:t
	
	private Timestamp dateTime;

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

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public Integer getLikeFlag() {
		return likeFlag;
	}

	public void setLikeFlag(Integer likeFlag) {
		this.likeFlag = likeFlag;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

}
