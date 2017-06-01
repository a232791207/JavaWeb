package com.sanker.entity.finance;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 充值记录
 * @author 滕洁
 * @date 2016-11-16
 */
public class Recharge implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String playerId;
	
	private double moneySum;
	
	private Timestamp rechargeTime;

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

	public double getMoneySum() {
		return moneySum;
	}

	public void setMoneySum(double moneySum) {
		this.moneySum = moneySum;
	}

	public Timestamp getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(Timestamp rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	

}
