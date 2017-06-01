package com.sanker.entity.login;
/**
 * 登录用户
 * @author 滕洁
 * @date 2016-10-30
 */
public class UserInfo {
	
	/**
	 * 用户Id
	 */
	private String userId;
	
	/**
	 * 用户姓名
	 */
	private String userName;
	
	/**
	 * 登录名
	 */
	private String loginName;
	
	/**
	 * 密码
	 */
	private String loginPwd;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	

}
