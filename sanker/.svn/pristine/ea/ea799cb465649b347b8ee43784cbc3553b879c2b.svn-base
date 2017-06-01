package com.sanker.action.userInfo;


import com.sanker.action.DefaultAction;
import com.sanker.entity.login.UserInfo;
import com.sanker.service.login.UserInfoService;

/**
 * 用户信息action
 * @author 滕洁
 * @date 2016-11-8
 */
public class UserInfoAction extends DefaultAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserInfoService userInfoService;
	
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
	/**
	 * 添加用户
	 */
	public void addUserInfo(){
		UserInfo entity = new UserInfo();
		entity.setUserName(getString("userName"));
		entity.setLoginName(getString("loginName"));
		entity.setLoginPwd(getString("loginPwd"));
		this.userInfoService.addUser(entity);
		
		Write(entity.getUserId());
	}
	
	/**
	 * 
	 */
	
}
