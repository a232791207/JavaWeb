package com.zhiqi.bean;

import java.io.Serializable;

public class NewsInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String comment;
	private short state;
	public NewsInfo() {
	}
	public NewsInfo(int id, String comment, short state) {
		this.id = id;
		this.comment = comment;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public short getState() {
		return state;
	}
	public void setState(short state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
