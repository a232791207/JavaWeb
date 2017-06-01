package com.zhiqi.action.agent;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.User;
import com.zhiqi.service.UserService;

public class AutoRecharge extends ActionSupport implements ServletResponseAware,SessionAware{
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private int autoRecharge;
	private UserService userService;
	private HttpServletResponse response;
	private Map session;
	
	public void changeAutoRecharge(){
		User user = userService.findUserByUserName(userName);
		user.setAutoRecharge(autoRecharge);
		userService.update(user);
		user = userService.findEncodedUserByUserName(userName);
		session.put("user", user);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	@Override
	public void setSession(Map session) {
		this.session=session;
	}
	
	
}
