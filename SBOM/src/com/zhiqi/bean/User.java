package com.zhiqi.bean;

import java.io.Serializable;

/**
 * 用户
 * @author 张琪
 *
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 游戏ID
	 */
	private String id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 头像
	 */
	private byte[] icon;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 身份证号
	 */
	private String idNum;
	/**
	 * 手机号
	 */
	private String phoneNum;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 登录密码
	 */
	private String loginPassword;
	/**
	 * 支付密码
	 */
	private String payPassword;
	/**
	 * 银行卡号
	 */
	private String bankCode;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 代理的地域
	 */
	private String region ;
	/**
	 * 注册时间
	 */
	private String registTime;
	/**
	 * 最近登录时间
	 */
	private String recentTime;
	/**
	 * 余额
	 */
	private double balance;
	/**
	 * 钻石数量
	 */
	private int diamond;
	/**
	 * 级别   0：普通用户 
	 *  	1：奇大使  
	 *  	2：副馆主
	 *  	3：馆主
	 */
	private short level;
	/**
	 * 自动充值钻石	
	 */
	private int autoRecharge;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String id,String userName, byte[] icon, String nickName, String realName,
			String idNum, String phoneNum, String email, String loginPassword,
			String payPassword, String bankCode, String address,
			String registTime, String recentTime, double balance, int diamond,
			short level, int autoRecharge) {
		this.id = id;
		this.userName = userName;
		this.icon = icon;
		this.nickName = nickName;
		this.realName = realName;
		this.idNum = idNum;
		this.phoneNum = phoneNum;
		this.email = email;
		this.loginPassword = loginPassword;
		this.payPassword = payPassword;
		this.bankCode = bankCode;
		this.address = address;
		this.registTime = registTime;
		this.recentTime = recentTime;
		this.balance = balance;
		this.diamond = diamond;
		this.level = level;
		this.autoRecharge = autoRecharge;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public byte[] getIcon() {
		return icon;
	}
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getDiamond() {
		return diamond;
	}
	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}
	public short getLevel() {
		return level;
	}
	public void setLevel(short level) {
		this.level = level;
	}
	
	public int getAutoRecharge() {
		return autoRecharge;
	}

	public void setAutoRecharge(int autoRecharge) {
		this.autoRecharge = autoRecharge;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
}
