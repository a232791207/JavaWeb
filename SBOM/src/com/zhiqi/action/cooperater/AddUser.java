package com.zhiqi.action.cooperater;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.Subordinate;
import com.zhiqi.bean.User;
import com.zhiqi.service.SubordinateService;
import com.zhiqi.service.UserService;
import com.zhiqi.utils.DateUtil;

public class AddUser extends ActionSupport implements RequestAware{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String userName;
	private String loginPassword;
	private short level;
	private String supUserName;
	private short supLevel;
	private String user;
	
	private Map request;
	private UserService userService;
	private SubordinateService subordinateService;

	public String execute() throws Exception {
		if(userService.findUserByUserName(userName)==null){
			String today = DateUtil.currentTime();
			User user = new User();
			user.setId(id);
			user.setUserName(userName);
			user.setLevel(level);
			user.setLoginPassword(loginPassword);
			user.setRecentTime(today);
			user.setRegistTime(today);
			userService.add(user);
			Subordinate subordinate = new Subordinate();
			subordinate.setSupUserName(supUserName);
			subordinate.setSubUserName(userName);
			subordinate.setRegistTime(today);
			subordinate.setRecentTime(today);
			subordinate.setSupLevel(supLevel);
			subordinate.setSubLevel(level);
			subordinateService.add(subordinate);
			request.put("info", "注册成功！");
		}else{
			request.put("info", "注册失败，用户名已存在！");
		}
		if(user.equals("admin")){
			return "adsuccess";
		}else{
			return "cosuccess";
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public String getSupUserName() {
		return supUserName;
	}

	public void setSupUserName(String supUserName) {
		this.supUserName = supUserName;
	}

	public short getSupLevel() {
		return supLevel;
	}

	public void setSupLevel(short supLevel) {
		this.supLevel = supLevel;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SubordinateService getSubordinateService() {
		return subordinateService;
	}

	public void setSubordinateService(SubordinateService subordinateService) {
		this.subordinateService = subordinateService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void setRequest(Map request) {
		this.request=request;
	}
	
	
}
