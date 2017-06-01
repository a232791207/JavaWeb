package com.sanker.comms.page;

import javax.servlet.http.HttpServletRequest;


public class PageRequest {
	public PageRequest(HttpServletRequest req) {
		setPageIndex(Integer.parseInt(req.getParameter("page")));
		setPageSize(Integer.parseInt(req.getParameter("rows")));
		setOrderProperty(req.getParameter("sidx"));
		setOrderType(req.getParameter("sord"));
	}
	public PageRequest(){
		
	}
	
	

	private String orderProperty="id";
	public String getOrderProperty() {
		return orderProperty;
	}
	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}

	private String orderType="desc";


	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	private int pageIndex = 1;
	private int pageSize = 20;
	
	public int getStartNumber() {
		return (pageIndex - 1) * pageSize;
	}
}
