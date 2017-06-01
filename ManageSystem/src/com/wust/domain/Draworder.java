package com.wust.domain;

import java.io.Serializable;

public class Draworder implements Serializable{

	private String id;
	private String time;
	private String distributor;
	private String salesman;
	private String customer;
	private String format;
	private String level;
	private double price;
	private int number;
	private double sumprice;
	private double checkdenomination;
	private double balance;
	
	private String orderid;
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
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getSumprice() {
		return sumprice;
	}
	public void setSumprice(double sumprice) {
		this.sumprice = sumprice;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public double getCheckdenomination() {
		return checkdenomination;
	}
	public void setCheckdenomination(double checkdenomination) {
		this.checkdenomination = checkdenomination;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
