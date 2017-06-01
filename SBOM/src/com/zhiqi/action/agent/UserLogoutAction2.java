package com.zhiqi.action.agent;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class UserLogoutAction2 extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	
	private Map session;
	
	@Override
	public String execute() throws Exception {
		session.put("user", null);
		return "success";
	}

	@Override
	public void setSession(Map session) {
		this.session=session;
	}
}
