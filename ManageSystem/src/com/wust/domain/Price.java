package com.wust.domain;

import java.io.Serializable;
import java.util.Date;

public class Price implements Serializable{
	private String id;
	private String time;
	private String area;
	private String format;
	private String level;
	private double price;
	private String changelim;
	public Price(){
		super();
	}
	public Price(String time, String area, String format, String level,
			double price, String changelim) {
		super();
		this.time = time;
		this.area = area;
		this.format = format;
		this.level = level;
		this.price = price;
		this.changelim = changelim;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getChangelim() {
		return changelim;
	}
	public void setChangelim(String changelim) {
		this.changelim = changelim;
	}
}
