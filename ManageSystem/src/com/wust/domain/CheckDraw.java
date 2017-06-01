package com.wust.domain;

import java.io.Serializable;

public class CheckDraw implements Serializable {
	private String distributor;
	private String format;
	private String level;
	private int number;
	private int drawnum;
	private int undrawnum;
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getDrawnum() {
		return drawnum;
	}
	public void setDrawnum(int drawnum) {
		this.drawnum = drawnum;
	}
	public int getUndrawnum() {
		return undrawnum;
	}
	public void setUndrawnum(int undrawnum) {
		this.undrawnum = undrawnum;
	}
}
