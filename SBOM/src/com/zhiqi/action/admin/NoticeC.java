package com.zhiqi.action.admin;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.Notice;
import com.zhiqi.bean.Page;
import com.zhiqi.service.NoticeService;

public class NoticeC extends ActionSupport implements RequestAware{
	private static final long serialVersionUID = 1L;

	private short state;
	private int id;
	private NoticeService noticeService;
	private Map request;
	

	public String execute() throws Exception {
		Notice notice = noticeService.getNotice(id);
		notice.setState(state);
		noticeService.update(notice);

		Page page = noticeService.getNoticePageByState(1,10,state);
		request.put("page", page);
		request.put("state", state);
		return "success";
	}

	public String add(){
		
		return "";
	}
	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public NoticeService getNoticeService() {
		return noticeService;
	}
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void setRequest(Map request) {
		this.request = request;
	}
	
	
}
