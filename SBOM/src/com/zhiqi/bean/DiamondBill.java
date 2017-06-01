package com.zhiqi.bean;

import java.io.Serializable;

public class DiamondBill implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户名：对应User类的userName
	 */
	private String userName;
	/**
	 * 流水号
	 */
	private String id;
	/**
	 * 时间
	 */
	private String time;
	/**
	 * 备注
	 */
	private String comment;
	/**
	 * 收入
	 */
	private int income;
	/**
	 * 余额
	 */
	private int balance;
	/**
	 * 充值状态
	 */
	private int state;
	
	public DiamondBill() {
	}
	public DiamondBill(String userName, String id, String time, String comment,
			int income, int balance, int state) {
		this.userName = userName;
		this.id = id;
		this.time = time;
		this.comment = comment;
		this.income = income;
		this.balance = balance;
		this.state = state;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
