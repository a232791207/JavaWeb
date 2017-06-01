package com.zhiqi.action.admin;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class AdminLogin extends ActionSupport implements SessionAware{

	private Map session;
	@Override
	public void setSession(Map session) {
	  this.session=session;
	}
	@Override
	public String execute() throws Exception {
		session.put("superUser", null);
		return "success";
	}

}
