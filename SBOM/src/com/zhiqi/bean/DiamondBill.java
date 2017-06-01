package com.zhiqi.bean;

import java.io.Serializable;

public class DiamondBill implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * �û�������ӦUser���userName
	 */
	private String userName;
	/**
	 * ��ˮ��
	 */
	private String id;
	/**
	 * ʱ��
	 */
	private String time;
	/**
	 * ��ע
	 */
	private String comment;
	/**
	 * ����
	 */
	private int income;
	/**
	 * ���
	 */
	private int balance;
	/**
	 * ��ֵ״̬
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
