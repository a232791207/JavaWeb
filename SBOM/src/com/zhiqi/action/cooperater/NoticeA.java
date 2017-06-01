package com.zhiqi.action.cooperater;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.Notice;
import com.zhiqi.bean.Page;
import com.zhiqi.service.NoticeService;
import com.zhiqi.utils.DateUtil;

public class NoticeA extends ActionSupport implements RequestAware, ServletResponseAware{
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String content;
	private Map request;
	private int thispage;
	private int id;
	private short state;
	private String user;
	private HttpServletResponse response;
	
	private NoticeService noticeService;
	
	public String noticeBriefInfo(){
		Page page = noticeService.getNoticePageByState(1, 3, (short)1);
		List<Notice> list = new ArrayList<Notice>();
		list = page.getList();
		String title[] = new String[3];
		String time[] = new String[3];
		String content[] = new String[3];
		String msg = "";
		for(int i = 0 ; i < list.size() ; i++){
			title[i] = list.get(i).getTitle();
			String c = list.get(i).getContent().replaceAll("<br /><br />", " ");
			if(c.length()>=30){
				content[i] = c.substring(0, 30)+"...";
			}else{
				content[i] = c;
			}
			time[i] = DateUtil.xTimeAgo(list.get(i).getTime());
			msg = msg + title[i] + "," + content[i] + "," + time[i] + ",";
		}
		try {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String noticeInfo(){
		Notice notice = noticeService.getNotice(id);
		String content = notice.getContent();
		content = content.replaceAll("<br /><br />", "\n");
		notice.setContent(content);
		request.put("notice", notice);
		if(user.equals("cooperater")){
			return "conoticeinfo";
		}else if(user.equals("admin")){
			return "adnoticeinfo";
		}else{
			return "agnoticeinfo";
		}
	}
	
	public String noticePage(){
		Page page = noticeService.getNoticePageByState(thispage,10,state);
		request.put("page", page);
		request.put("state", state);
		if(user.equals("admin")){
			return "adnoticepage";
		}else{
			return "conoticepage";
		}
	}
	
	public String noticePage2(){
		Page page = noticeService.getNoticePageByState(thispage,10,state);
		request.put("page", page);
		request.put("state", state);
		if(user.equals("cooperater")){
			return "conoticepage2";
		}else if(user.equals("admin")){
			return "adnoticepage2";
		}else{
			return "agnoticepage2";
		}
	}

	public String upload(){
		String time = DateUtil.currentTime();
		Notice notice = new Notice();
		notice.setTitle(title);
		content = content.replaceAll("\n", "<br />");
		content = content.replaceAll("\r", "<br />");
		notice.setContent(content);
		System.out.println(content);
		notice.setTime(time);
		if(user.equals("admin")){
			notice.setState((short) 1);
		}else{
			notice.setState((short) 0);
		}
		noticeService.add(notice);
		if(user.equals("admin")){
			request.put("info", "公告已发布!");
			return "adupload";
		}else{
			request.put("info", "公告申请已提交!");
			return "coupload";
		}
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getThispage() {
		return thispage;
	}

	public void setThispage(int thispage) {
		this.thispage = thispage;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	@Override
	public void setRequest(Map request) {
		this.request=request;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}
