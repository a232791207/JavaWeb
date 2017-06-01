package com.zhiqi.action.agent;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.Subordinate;
import com.zhiqi.bean.User;
import com.zhiqi.service.SubordinateService;
import com.zhiqi.service.UserService;
import com.zhiqi.utils.DateUtil;

public class UserLoginAction extends ActionSupport implements SessionAware,RequestAware{
	private static final long serialVersionUID = 1L;
	private Map session;
	private Map request;
	private String idnum;
	private String userName;
	private String loginPassword;
	private User user=null;
	@Autowired
	private UserService userService;
	private SubordinateService subordinateService;

	@Override
	public String execute() throws Exception {
		user = userService.findEncodedUserByUserNameAndLoginPassword(userName, loginPassword);
		if(user == null){
			request.put("info", "”√ªß√˚√‹¬Î¥ÌŒÛ£°");
			return "false";
		}else{
			User user2 = userService.findUserByUserName(userName);
			String time = DateUtil.currentTime();
			String today = DateUtil.currentDate2();
			user2.setRecentTime(time);
			userService.update(user2);
			Subordinate subordinate = subordinateService.getSubordinateByUserName(user.getUserName());
			if(subordinate!=null){
				if(subordinate.getRecentTime().compareTo(today)<0){
					subordinate.setTodayConsumption(0);
				}
				subordinate.setRecentTime(time);
				subordinateService.update(subordinate);
			}
			session.put("user", user);
			if(user2.getRealName()!=null&&!user2.getRealName().equals("")){
				return "success";
			}else{
				return "complete";
			}
		}
	}
	
	
	public SubordinateService getSubordinateService() {
		return subordinateService;
	}


	public void setSubordinateService(SubordinateService subordinateService) {
		this.subordinateService = subordinateService;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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

	@Override
	public void setSession(Map session) {
		this.session=session;
	}

	@Override
	public void setRequest(Map request) {
		this.request=request;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}



	
}
