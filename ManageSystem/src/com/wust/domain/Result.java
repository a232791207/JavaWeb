package com.wust.domain;

import java.io.Serializable;

public class Result implements Serializable{
	private int id;
	private String distributor;
	private String salesman;
	private String sumsprice;
	private String sumdprice;
	private String sumrprice;
	private String sumiprice;
	private String sumpprice;
	private String sumrinterest;
	private String sumaprice;
	private String result;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getSumsprice() {
		return sumsprice;
	}
	public void setSumsprice(String sumsprice) {
		this.sumsprice = sumsprice;
	}
	public String getSumdprice() {
		return sumdprice;
	}
	public void setSumdprice(String sumdprice) {
		this.sumdprice = sumdprice;
	}
	public String getSumrprice() {
		return sumrprice;
	}
	public void setSumrprice(String sumrprice) {
		this.sumrprice = sumrprice;
	}
	public String getSumiprice() {
		return sumiprice;
	}
	public void setSumiprice(String sumiprice) {
		this.sumiprice = sumiprice;
	}
	public String getSumpprice() {
		return sumpprice;
	}
	public void setSumpprice(String sumpprice) {
		this.sumpprice = sumpprice;
	}
	public String getSumrinterest() {
		return sumrinterest;
	}
	public void setSumrinterest(String sumrinterest) {
		this.sumrinterest = sumrinterest;
	}
	public String getSumaprice() {
		return sumaprice;
	}
	public void setSumaprice(String sumaprice) {
		this.sumaprice = sumaprice;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
