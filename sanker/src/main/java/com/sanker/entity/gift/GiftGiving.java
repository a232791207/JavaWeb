package com.sanker.entity.gift;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 滕洁
 * @date 2016-11-16
 */
public class GiftGiving implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String senderId;
	
	private String receiverId;
	
	private String giftName;
	
	private Integer giftNum;
	
	private Date givingTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public Integer getGiftNum() {
		return giftNum;
	}

	public void setGiftNum(Integer giftNum) {
		this.giftNum = giftNum;
	}

	public Date getGivingTime() {
		return givingTime;
	}

	public void setGivingTime(Date givingTime) {
		this.givingTime = givingTime;
	}

}
