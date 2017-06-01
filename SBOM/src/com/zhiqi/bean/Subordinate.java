package com.zhiqi.bean;

import java.io.Serializable;

/**
 * 下级
 * @author 张琪
 *
 */
public class Subordinate implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 上级的用户名
	 */
	private String supUserName;
	/**
	 * 下级的用户名
	 */
	private String subUserName;
	/**
	 * 下级的昵称
	 */
	private String nickName;
	/**
	 * 下级手机号
	 */
	private String phoneNum;
	/**
	 * 注册时间
	 */
	private String registTime;
	/**
	 * 上次登录时间
	 */
	private String recentTime;
	/**
	 * 今日消费金额
	 */
	private int todayConsumption;
	/**
	 * 当月消费金额
	 */
	private int monthConsumption;
	/**
	 * 总消费金额
	 */
	private int sumConsumption;
	/**
	 * 邀请人数
	 */
	private int inviteNum;
	/**
	 * 上级等级
	 */
	private short supLevel;
	/**
	 * 下级等级
	 */
	private short subLevel;
	
	public Subordinate() {
		// TODO Auto-generated constructor stub
	}
	public Subordinate(String supUserName, String subUserName, String nickName, String phoneNum,
			String registTime, String recentTime,int todayConsumption,int monthConsumption, int sumConsumption,
			int inviteNum, short supLevel, short subLevel) {
		this.supUserName = supUserName;
		this.subUserName = subUserName;
		this.nickName = nickName;
		this.phoneNum = phoneNum;
		this.registTime = registTime;
		this.recentTime = recentTime;
		this.todayConsumption = todayConsumption;
		this.monthConsumption = monthConsumption;
		this.sumConsumption = sumConsumption;
		this.inviteNum = inviteNum;
		this.supLevel = supLevel;
		this.subLevel = subLevel;
	}
	
	public short getSupLevel() {
		return supLevel;
	}
	public void setSupLevel(short supLevel) {
		this.supLevel = supLevel;
	}
	public short getSubLevel() {
		return subLevel;
	}
	public void setSubLevel(short subLevel) {
		this.subLevel = subLevel;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getMonthConsumption() {
		return monthConsumption;
	}
	public void setMonthConsumption(int monthConsumption) {
		this.monthConsumption = monthConsumption;
	}
	public String getSupUserName() {
		return supUserName;
	}

	public void setSupUserName(String supUserName) {
		this.supUserName = supUserName;
	}

	public String getSubUserName() {
		return subUserName;
	}

	public void setSubUserName(String subUserName) {
		this.subUserName = subUserName;
	}

	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	public String getRecentTime() {
		return recentTime;
	}
	public void setRecentTime(String recentTime) {
		this.recentTime = recentTime;
	}
	
	public int getTodayConsumption() {
		return todayConsumption;
	}
	public void setTodayConsumption(int todayConsumption) {
		this.todayConsumption = todayConsumption;
	}
	public int getSumConsumption() {
		return sumConsumption;
	}
	public void setSumConsumption(int sumConsumption) {
		this.sumConsumption = sumConsumption;
	}
	public int getInviteNum() {
		return inviteNum;
	}
	public void setInviteNum(int inviteNum) {
		this.inviteNum = inviteNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
