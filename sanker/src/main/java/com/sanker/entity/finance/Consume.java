package com.sanker.entity.finance;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 消费记录
 * @author 滕洁
 * @date 2016-11-16
 */
public class Consume implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String playerId;
	
	private double amount; 
	
	private String purpose;//用途
	
	private Timestamp consumeTime;

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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Timestamp getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(Timestamp consumeTime) {
		this.consumeTime = consumeTime;
	}

	

}
