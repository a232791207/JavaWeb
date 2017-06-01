package com.zhiqi.action.agent;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.Page;
import com.zhiqi.service.SubordinateService;
import com.zhiqi.utils.DateUtil;

public class RemoveBinding extends ActionSupport implements RequestAware{
	private static final long serialVersionUID = 1L;
	
	private String subUserName;
	private int thispage;
	private int days;
	private String supUserName;
	private SubordinateService subordinateService;
	private Map request;
	private short subLevel;
	
	public String remove(){
		subordinateService.delete(subUserName);
		String date = DateUtil.currentDate2();
		if(days==0){
			date=DateUtil.anotherDay(date);
		}else{
			date=DateUtil.xDaysAgo(date, days);
		}
		Page page = subordinateService.getSubordinatePage(thispage,10,supUserName,date,subLevel);
		request.put("page", page);
		request.put("days", days);
		return "remove";
	}

	public String getSubUserName() {
		return subUserName;
	}

	public void setSubUserName(String subUserName) {
		this.subUserName = subUserName;
	}

	public SubordinateService getSubordinateService() {
		return subordinateService;
	}

	public void setSubordinateService(SubordinateService subordinateService) {
		this.subordinateService = subordinateService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getThispage() {
		return thispage;
	}

	public void setThispage(int thispage) {
		this.thispage = thispage;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getSupUserName() {
		return supUserName;
	}

	public void setSupUserName(String supUserName) {
		this.supUserName = supUserName;
	}

	@Override
	public void setRequest(Map request) {
		this.request=request;
	}

	public short getSubLevel() {
		return subLevel;
	}

	public void setSubLevel(short subLevel) {
		this.subLevel = subLevel;
	}
	
	
}
