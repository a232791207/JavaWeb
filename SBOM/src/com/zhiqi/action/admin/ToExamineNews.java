package com.zhiqi.action.admin;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.NewsInfo;
import com.zhiqi.service.NewsInfoService;

public class ToExamineNews extends ActionSupport implements SessionAware{

	private Map session;
	private int id;
	private String suggest;
	private int num;
	NewsInfo news=null;
	@Autowired
   private NewsInfoService newsInfoService;
	/**
	 * 查询正在审核的新闻
	 * @return
	 */
	public String toExamine(){

		try {
			news=newsInfoService.getByState(2,(short)0);
			
			if(news==null){
				num=0;
			}else{
				num=1;
			}
			session.put("news", news);
			session.put("num", num);
		}catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			
		}
		return "news";
	}
	/**
	 * 处理正在审核的新闻
	 * @return
	 */
	public String upNews(){
		NewsInfo news=new NewsInfo();
		NewsInfo news2=newsInfoService.getByState(2,(short)0);
		if(news2==null){
			num=0;
		}else{
			num=1;
		}
		news.setId(2);
		news.setComment(suggest);
		news.setState((short)1);
		newsInfoService.update(news);
		session.put("num", num);
		return "upnews";
	}
	/**
	 * 查询正在审核的广告
	 * @return
	 */
	public String toExamineAdvertisement(){
		news=newsInfoService.getByState(6,(short)0);
		if(news==null){
			num=0;
		}else{
			num=1;
		}
		session.put("news", news);
		session.put("num", num);
		return "advertisement";
	}

	/**
	 * 处理正在审核的新闻
	 * @return
	 */
	public String upAdvertisement(){
		NewsInfo news=new NewsInfo();
		NewsInfo news2=newsInfoService.getByState(6,(short)0);
		if(news2==null){
			num=0;
		}else{
			num=1;
		}
		news.setId(6);
		news.setComment(suggest);
		news.setState((short)1);
		newsInfoService.update(news);
		session.put("num", num);
		return "upAdvertisement";
	}
	@Override
	public void setSession(Map session) {
		this.session=session;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	
}
