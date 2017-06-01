package com.zhiqi.bean;

import java.io.Serializable;

/**
 * ����
 * @author ����
 *
 */
public class Mail implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * �û�������User���е�userName��Ӧ
	 */
	private String userName;
	/**
	 * ��ţ�ʱ��+username+num��
	 */
	private String id;
	/**
	 * ʱ��
	 */
	private String time;
	/**
	 * ״̬  	0��δ��
	 * 		1���Ѷ�
	 */
	private boolean state;
	/**
	 * ����
	 */
	private String theme;
	/**
	 * ����
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
