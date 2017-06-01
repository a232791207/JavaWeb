package com.zhiqi.action.agent;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.service.DiamondBillService;
import com.zhiqi.service.SubordinateService;

public class RechargeApplyNum extends ActionSupport implements ServletResponseAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private String supUserName;
	private DiamondBillService diamondBillService;
	private SubordinateService subordinateService;
	
	public void num(){
		List<String> list = subordinateService.getSubUserNameList(supUserName);
		int num = 0;
		for(String subUserName : list){
			num+=diamondBillService.getApplyingNum(subUserName);
		}
		try {
			response.getWriter().write(num+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	
	public SubordinateService getSubordinateService() {
		return subordinateService;
	}


	public void setSubordinateService(SubordinateService subordinateService) {
		this.subordinateService = subordinateService;
	}


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getSupUserName() {
		return supUserName;
	}

	public void setSupUserName(String supUserName) {
		this.supUserName = supUserName;
	}

	public DiamondBillService getDiamondBillService() {
		return diamondBillService;
	}

	public void setDiamondBillService(DiamondBillService diamondBillService) {
		this.diamondBillService = diamondBillService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
