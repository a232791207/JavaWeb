package com.zhiqi.bean;
/**
 * 管理员
 * @author 1
 *
 */
public class SuperUser {

	// 登陆名称
	private String userName;
	// 登陆密码
	private String loginPassword;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	
	
}
