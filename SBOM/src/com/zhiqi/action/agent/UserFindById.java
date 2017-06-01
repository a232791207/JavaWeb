package com.zhiqi.action.agent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.User;
import com.zhiqi.service.UserService;

public class UserFindById extends ActionSupport implements SessionAware,RequestAware{
	private static final long serialVersionUID = 1L;
	private Map session;
	private Map request;
	private HttpServletResponse response;
	private String idnum;
	@Autowired
	private UserService userService;
	/**
	 * 检测游戏ID是否存在
	 * @return
	 */
    public String findByUserId(){
    	User user=userService.getIdFindByUserInfo(idnum);
    	System.out.println(idnum);
    	String num;
		if(user==null){
		 request.put("num", "可以使用该游戏ID！");
		}else{
			request.put("num", "不可以使用该游戏ID，您换一个吧！");
		}
	 return "find";
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
