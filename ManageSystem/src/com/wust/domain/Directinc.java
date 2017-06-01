package com.wust.domain;

import java.io.Serializable;

public class Directinc implements Serializable{
	private String id;
	private String stime;
	private String etime;
	private String distributor;
	private String area;
	private String format;
	private String level;
	private String directinc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public String getDirectinc() {
		return directinc;
	}
	public void setDirectinc(String directinc) {
		this.directinc = directinc;
	}
}
