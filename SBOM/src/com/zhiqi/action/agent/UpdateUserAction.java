package com.zhiqi.action.agent;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.User;
import com.zhiqi.service.UserService;

public class UpdateUserAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	
	private String realName;
	private String idNum;
	private String payPassword;
	private String userName;
	private String nickName;
	private String phoneNum;
	private String email;
	private String bankCode;
	private String address;
	
	private Map session;
	
	UserService userService;
	
	public String nickName(){
		userService.updateNickName(userName,nickName);
		User user = userService.findEncodedUserByUserName(userName);
		session.put("user", user);
		return "success";
	}
	public String phoneNum(){
		userService.updatePhoneNum(userName,phoneNum);
		User user = userService.findEncodedUserByUserName(userName);
		session.put("user", user);
		return "success";
	}
	public String email(){
		userService.updateEmail(userName,email);
		User user = userService.findEncodedUserByUserName(userName);
		session.put("user", user);
		return "success";
	}
	public String bankCode(){
		userService.updateBankCode(userName,bankCode);
		User user = userService.findEncodedUserByUserName(userName);
		session.put("user", user);
		return "success";
	}
	public String address(){
		userService.updateAddress(userName,address);
		User user = userService.findEncodedUserByUserName(userName);
		session.put("user", user);
		return "success";
	}
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public Map getSession() {
		return session;
	}
	@Override
	public String execute() throws Exception {
		User user = userService.findUserByUserName(userName);
		user.setNickName(nickName);
		user.setIdNum(idNum);
		user.setRealName(realName);
		user.setPhoneNum(phoneNum);
		user.setEmail(email);
		user.setBankCode(bankCode);
		user.setAddress(address);
		user.setPayPassword(payPassword);
		userService.update(user);
		return "success";
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Override
	public void setSession(Map session) {
		this.session=session;
	}
	
	
}
