package com.zhiqi.bean;

import java.io.Serializable;

/**
 * 邮箱
 * @author 张琪
 *
 */
public class Mail implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户名：与User类中的userName对应
	 */
	private String userName;
	/**
	 * 编号：时间+username+num；
	 */
	private String id;
	/**
	 * 时间
	 */
	private String time;
	/**
	 * 状态  	0：未读
	 * 		1：已读
	 */
	private boolean state;
	/**
	 * 主题
	 */
	private String theme;
	/**
	 * 内容
	 */
	private byte[] Content;
	
	public Mail() {
		// TODO Auto-generated constructor stub
	}
	
	public Mail(String userName, String id, String time, boolean state,
			String theme, byte[] content) {
		this.userName = userName;
		this.id = id;
		this.time = time;
		this.state = state;
		this.theme = theme;
		Content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public byte[] getContent() {
		return Content;
	}
	public void setContent(byte[] content) {
		Content = content;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
