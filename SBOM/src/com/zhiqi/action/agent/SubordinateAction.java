package com.zhiqi.action.agent;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.service.DiamondBillService;
import com.zhiqi.service.SubordinateService;
import com.zhiqi.utils.DateUtil;

public class SubordinateAction extends ActionSupport implements ServletResponseAware{

	private static final long serialVersionUID = 1L;

	private String supUserName;
	private HttpServletResponse response;
	private SubordinateService subordinateService;
	private DiamondBillService diamondBillService;

	public void overview(){
		int newIncrease = subordinateService.getNewIncrease(supUserName);
		int sumNum = subordinateService.getSumNum(supUserName);
		int todayActive = subordinateService.getTodayActive(supUserName);
		int todayConsumption = 0;
		String today = DateUtil.currentDate2();
		List<String> list = subordinateService.getSubUserNameList(supUserName);
		for(String subUserName : list){
			todayConsumption += diamondBillService.getDateConsumption(subUserName, today, "ฯ๛ทั");
		}
		try {
			response.getWriter().write(newIncrease+","+sumNum+","+todayActive+","+todayConsumption);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getSupUserName() {
		return supUserName;
	}

	public void setSupUserName(String supUserName) {
		this.supUserName = supUserName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public DiamondBillService getDiamondBillService() {
		return diamondBillService;
	}

	public void setDiamondBillService(DiamondBillService diamondBillService) {
		this.diamondBillService = diamondBillService;
	}

	public SubordinateService getSubordinateService() {
		return subordinateService;
	}

	public void setSubordinateService(SubordinateService subordinateService) {
		this.subordinateService = subordinateService;
	}
	
}
