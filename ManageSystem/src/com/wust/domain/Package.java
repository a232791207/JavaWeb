package com.wust.domain;

import java.io.Serializable;

public class Package implements Serializable{
	private String id;
	private String format;
	private String size;
	private int number;
	private double thick;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getThick() {
		return thick;
	}
	public void setThick(double thick) {
		this.thick = thick;
	}
}
