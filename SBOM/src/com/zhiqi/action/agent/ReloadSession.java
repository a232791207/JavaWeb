package com.zhiqi.action.agent;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.User;
import com.zhiqi.service.UserService;

public class ReloadSession extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;

	private String userName;
	private UserService userService;
	private Map session;
	
	public void reload(){
		User user = userService.findEncodedUserByUserName(userName);
		session.put("user", user);
	}
	
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Map getSession() {
		return session;
	}


	@Override
	public void setSession(Map session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

}
