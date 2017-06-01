package com.zhiqi.bean;

import java.io.Serializable;

public class Notice implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String content;
	private String time;
	private short state;
	
	public Notice() {
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
