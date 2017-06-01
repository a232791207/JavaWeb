package com.zhiqi.action.cooperater;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CooperaterLogin extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	private Map session;
	@Override
	public void setSession(Map session) {
	  this.session=session;
	}

	@Override
	public String execute() throws Exception {
		session.put("cooperater", null);
		return "success";
	}

}
