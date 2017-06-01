package com.zhiqi.action.agent;

import java.text.DecimalFormat;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.Page;
import com.zhiqi.service.CashBillService;
import com.zhiqi.service.DiamondBillService;

public class BillAction extends ActionSupport implements RequestAware{

	private static final long serialVersionUID = 1L;
	
	private CashBillService cashBillService;
	private DiamondBillService diamondBillService;
	
	private String userName;
	private int cthispage;
	private int dthispage;
	private String stime;
	private String etime;
	
	private Map request;
	
	public String cashBillPageAction(){
		Page cashBillPage = cashBillService.findCashBillPageByTime(cthispage, 10, stime, etime, userName);
		request.put("cashBillPage", cashBillPage);
		return "cashbillpage";
	}
	
	public String diamondBillPageAction(){
		Page diamondBillPage = diamondBillService.findDiamondBillPageByTime(dthispage, 10, stime, etime,1, userName,"=","");
		request.put("diamondBillPage", diamondBillPage);
		return "diamondbillpage";
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	public CashBillService getCashBillService() {
		return cashBillService;
	}

	public void setCashBillService(CashBillService cashBillService) {
		this.cashBillService = cashBillService;
	}

	public DiamondBillService getDiamondBillService() {
		return diamondBillService;
	}

	public void setDiamondBillService(DiamondBillService diamondBillService) {
		this.diamondBillService = diamondBillService;
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

	
	public int getCthispage() {
		return cthispage;
	}



	public void setCthispage(int cthispage) {
		this.cthispage = cthispage;
	}



	public int getDthispage() {
		return dthispage;
	}



	public void setDthispage(int dthispage) {
		this.dthispage = dthispage;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public void setRequest(Map request) {
		this.request = request;
	}



	public Map getRequest() {
		return request;
	}
	
	
	
}
