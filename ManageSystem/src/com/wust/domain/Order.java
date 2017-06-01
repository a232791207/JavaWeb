package com.wust.domain;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	private String orderid;
	
	private String time;
	private String salesman;
	private String distributor;
	private String area;
	private String format;
	private String level;
	private double price;
	private String directinc;
	private double freight;  //一票制运费
	private String specialinc;
	private double realprice;
	private int number;
	private int bags;//包数
	private double volume;//立方数
	private String fare;//调度费
	private String freight2;
	private String sumprice;
	private String ifprofit;
	private String statement;
	public int getBags() {
		return bags;
	}
	public void setBags(int bags) {
		this.bags = bags;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDirectinc() {
		return directinc;
	}
	public void setDirectinc(String directinc) {
		this.directinc = directinc;
	}
	public double getFreight() {
		return freight;
	}
	public void setFreight(double freight) {
		this.freight = freight;
	}
	public String getSpecialinc() {
		return specialinc;
	}
	public void setSpecialinc(String specialinc) {
		this.specialinc = specialinc;
	}
	public double getRealprice() {
		return realprice;
	}
	public void setRealprice(double realprice) {
		this.realprice = realprice;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getSumprice() {
		return sumprice;
	}
	public void setSumprice(String sumprice) {
		this.sumprice = sumprice;
	}
	public String getIfprofit() {
		return ifprofit;
	}
	public void setIfprofit(String ifprofit) {
		this.ifprofit = ifprofit;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public String getFreight2() {
		return freight2;
	}
	public void setFreight2(String freight2) {
		this.freight2 = freight2;
	}

}
