package com.zhiqi.action.cooperater;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.User;
import com.zhiqi.service.UserService;

public class UserInfo extends ActionSupport implements RequestAware{
	
	private String userName;
	private UserService userService;
	private Map request;
	
	@Override
	public String execute() throws Exception {
		User user = userService.findUserByUserName(userName);
		request.put("user", user);
		return "success";
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;
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

	public Map getRequest() {
		return request;
	}

	
}
