package com.zhiqi.action.agent;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.Cooperater;
import com.zhiqi.bean.Subordinate;
import com.zhiqi.bean.SuperUser;
import com.zhiqi.bean.User;
import com.zhiqi.service.CooperaterService;
import com.zhiqi.service.SubordinateService;
import com.zhiqi.service.SuperUserService;
import com.zhiqi.service.UserService;
import com.zhiqi.utils.DateUtil;

public class LoginAllAction extends ActionSupport implements SessionAware,RequestAware{
	private static final long serialVersionUID = 1L;
	private Map session;
	private Map request;
	private String userName;
	private String loginPassword;
	private int leveles;
	@Autowired
	private UserService userService;
	private SuperUserService superUserService;
	private CooperaterService cooperaterService;
	private SubordinateService subordinateService;
	/**
	 * 通用的登录方法
	 * @return
	 */
	public String login() throws Exception {

		if (leveles == 0) {
			SuperUser superUser = superUserService.findByUserName(userName, loginPassword);
			if (superUser == null) {
				request.put("info", "用户名密码错误！");
				return "false";
			} else {
				session.put("superUser", superUser);
				return "super";
			}

		} /*else if (leveles == 1) {
			User user = userService.findEncodedUserByUserNameAndLoginPassword(userName, loginPassword);
			if (user == null) {
				request.put("info", "用户名密码错误！");
				return "false";
			} else {
				String time = DateUtil.currentTime();
				String today = DateUtil.currentDate2();
				user.setRecentTime(time);
				userService.update(user);
				Subordinate subordinate = subordinateService.getSubordinateByUserName(user.getUserName());
				if (subordinate != null) {
					if (subordinate.getRecentTime().compareTo(today) < 0) {
						subordinate.setTodayConsumption(0);
					}
					subordinate.setRecentTime(time);
					subordinateService.update(subordinate);
				}
				session.put("user", user);
				if (user.getRealName() != null && !user.getRealName().equals("")) {
					return "success";
				} else {
					return "complete";
				}
			}
		} */else if (leveles == 2) {
			Cooperater cooperater = cooperaterService.findByUserName(userName, loginPassword);
			if (cooperater == null) {
				request.put("info", "用户名密码错误！");
				return "false";
			} else {
				session.put("cooperater", cooperater);
				return "cooperater";
			}

		} else {
			request.put("info", "请选择登陆类型！");
			return "false";
		}

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




	public int getLeveles() {
		return leveles;
	}


	public void setLeveles(int leveles) {
		this.leveles = leveles;
	}


	@Override
	public void setRequest(Map request) {
	this.request=request;
		
	}


	@Override
	public void setSession(Map session) {
		this.session=session;
		
	}


	public SuperUserService getSuperUserService() {
		return superUserService;
	}


	public void setSuperUserService(SuperUserService superUserService) {
		this.superUserService = superUserService;
	}


	public CooperaterService getCooperaterService() {
		return cooperaterService;
	}


	public void setCooperaterService(CooperaterService cooperaterService) {
		this.cooperaterService = cooperaterService;
	}


	public SubordinateService getSubordinateService() {
		return subordinateService;
	}


	public void setSubordinateService(SubordinateService subordinateService) {
		this.subordinateService = subordinateService;
	}

	
}
