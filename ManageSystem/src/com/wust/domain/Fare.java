package com.wust.domain;

import java.io.Serializable;

public class Fare implements Serializable {
	private String id;
	private String area;
	private double fare;
	private double num;
	private double sumfare;
	private int status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double d) {
		this.num = d;
	}
	public double getSumfare() {
		return sumfare;
	}
	public void setSumfare(double sumfare) {
		this.sumfare = sumfare;
	}
}
