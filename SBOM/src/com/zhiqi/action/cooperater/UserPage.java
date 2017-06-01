package com.zhiqi.action.cooperater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.Page;
import com.zhiqi.bean.Subordinate;
import com.zhiqi.service.DiamondBillService;
import com.zhiqi.service.SubordinateService;
import com.zhiqi.service.UserService;
import com.zhiqi.utils.DateUtil;

public class UserPage extends ActionSupport implements RequestAware{
	private static final long serialVersionUID = 1L;
	
	private int thispage;
	private String subUserName;
	private String supUserName;
	private short subLevel;
	private Map request;
	private String user;
	
	private UserService userService;
	private DiamondBillService diamondBillService;
	private SubordinateService subordinateService;
	
	public String subUserPage(){
		Page page = subordinateService.getSubUserPage(thispage,10,supUserName);
		completeConsumption(page);
		request.put("page", page);
		request.put("url", "userPage!subUserPage?supUserName="+supUserName);
		if(user.equals("admin")){
			return "adsuccess";
		}else{
			return "cosuccess";
		}
	}
	
	public String levelUserPage(){
		Page page = subordinateService.getLevelUserPage(thispage, 10, subLevel);
		completeConsumption(page);
		request.put("page", page);
		request.put("url", "userPage!levelUserPage?subLevel="+subLevel);
		if(user.equals("admin")){
			return "adsuccess";
		}else{
			return "cosuccess";
		}
	}

	public String userPageById(){
		Page page = subordinateService.getUserPageById(thispage,10,subUserName);
		completeConsumption(page);
		request.put("page", page);
		request.put("url", "userPage!userPageById?subUserName="+subUserName);
		if(user.equals("admin")){
			return "adsuccess";
		}else{
			return "cosuccess";
		}
	}
	
	@Override
	public String execute() throws Exception {
		Page page = subordinateService.getAllUserPage(thispage,10);
		completeConsumption(page);
		request.put("page", page);
		request.put("url", "userPage.action?");
		if(user.equals("admin")){
			return "adsuccess";
		}else{
			return "cosuccess";
		}
	}
	
	public void completeConsumption(Page page){
		String today = DateUtil.currentDate2();
		String month = DateUtil.currentMonth();
		List<Subordinate> users = page.getList();
		List<Subordinate> list2 = new ArrayList<Subordinate>();
		for(Subordinate user : users){
			//获取当级的消费情况
			int todayConsumption = diamondBillService.getDateConsumption(user.getSubUserName(),today,"消费");
			int monthConsumption = diamondBillService.getDateConsumption(user.getSubUserName(),month,"消费");
			
			//获取下级的消费情况
			//当日消费
			int subTodayConsumption = 0;
			//当月消费
			int subMonthConsumption = 0;
			//总消费
			int subSumConsumption = 0;
			//获取下级的下级用户名列表
			List<String> sUsers = subordinateService.getSubUserNameList(user.getSubUserName());
			for(String sUser : sUsers){
				subTodayConsumption += diamondBillService.getDateConsumption(sUser, today, "消费");
				subMonthConsumption += diamondBillService.getDateConsumption(sUser, month, "消费");
				subSumConsumption += subordinateService.getSubordinateByUserName(sUser).getSumConsumption();
				//获取下下级的消费情况
				//当日消费
				int ssubTodayConsumption = 0;
				//当月消费
				int ssubMonthConsumption = 0;
				//总消费
				int ssubSumConsumption = 0;
				List<String> ssUsers = subordinateService.getSubUserNameList(sUser);
				for(String ssUser : ssUsers){
					ssubTodayConsumption += diamondBillService.getDateConsumption(ssUser, today, "消费");
					ssubMonthConsumption += diamondBillService.getDateConsumption(ssUser, month, "消费");
					ssubSumConsumption += subordinateService.getSubordinateByUserName(ssUser).getSumConsumption();
				}
				subTodayConsumption += ssubTodayConsumption;
				subMonthConsumption += ssubMonthConsumption;
				subSumConsumption += ssubSumConsumption;
			}
			user.setTodayConsumption(todayConsumption+subTodayConsumption);
			user.setMonthConsumption(monthConsumption+subMonthConsumption);
			user.setSumConsumption(subordinateService.getSubordinateByUserName(user.getSubUserName()).getSumConsumption()+subSumConsumption);
			list2.add(user);
		}
		page.setList(list2);
	}
	
	
	@Override
	public void setRequest(Map request) {
		this.request=request;
	}



	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public DiamondBillService getDiamondBillService() {
		return diamondBillService;
	}


	public void setDiamondBillService(DiamondBillService diamondBillService) {
		this.diamondBillService = diamondBillService;
	}


	public String getSupUserName() {
		return supUserName;
	}


	public void setSupUserName(String supUserName) {
		this.supUserName = supUserName;
	}


	public String getSubUserName() {
		return subUserName;
	}


	public void setSubUserName(String subUserName) {
		this.subUserName = subUserName;
	}


	public short getSubLevel() {
		return subLevel;
	}


	public void setSubLevel(short subLevel) {
		this.subLevel = subLevel;
	}


	public int getThispage() {
		return thispage;
	}



	public void setThispage(int thispage) {
		this.thispage = thispage;
	}

	public UserService getUserService() {
		return userService;
	}



	public void setUserService(UserService userService) {
		this.userService = userService;
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



	public Map getRequest() {
		return request;
	}
	
}
