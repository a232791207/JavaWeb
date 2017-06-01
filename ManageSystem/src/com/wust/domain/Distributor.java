package com.wust.domain;

import java.io.Serializable;

public class Distributor implements Serializable{
	private String id;
	private String name;
	private String area;
	private String salesman;
	private String phonenumber;
	private int creditlines;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public int getCreditlines() {
		return creditlines;
	}
	public void setCreditlines(int creditlines) {
		this.creditlines = creditlines;
	}
}
