package com.zhiqi.action.agent;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.service.UserService;

public class UpdatePasswordAction extends ActionSupport implements RequestAware{
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String oldPassword;
	private String password;
	private String password2;

	private Map request;
	private UserService userService;
	
	public String loginPassword(){
		if(oldPassword.equals("")){
			request.put("info", "ԭ���벻��Ϊ�գ�");
		}else if(password.equals("")){
			request.put("info", "�����벻��Ϊ�գ�");
		}else if(!password.equals(password2)){
			request.put("info", "��������������벻һ�£�");
		}else if(!userService.findUserByUserName(userName).getLoginPassword().equals(oldPassword)){
			request.put("info", "ԭ���벻��ȷ��");
		}else if(password.length()>11){
			request.put("info", "�����볬��11λ��");
		}else{
			userService.updateLoginPasswordByUsername(userName,password);
			request.put("info", "�޸ĳɹ������μ��������룡");
		}
		return "login";
	}
	
	public String payPassword(){
		if(oldPassword.equals("")){
			request.put("info", "ԭ���벻��Ϊ�գ�");
		}else if(password.equals("")){
			request.put("info", "�����벻��Ϊ�գ�");
		}else if(!password.equals(password2)){
			request.put("info", "��������������벻һ�£�");
		}else if(!userService.findUserByUserName(userName).getPayPassword().equals(oldPassword)){
			request.put("info", "ԭ���벻��ȷ��");
		}else if(password.length()>6){
			request.put("info", "�����볬��6λ��");
		}else{
			userService.updatePayPasswordByUsername(userName,password);
			request.put("info", "�޸ĳɹ������μ��������룡");
		}
		return "pay";
	}
	
	@Override
	public void setRequest(Map request) {
		this.request=request;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map getRequest() {
		return request;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
