package com.zhiqi.action.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.Page;
import com.zhiqi.bean.Subordinate;
import com.zhiqi.service.DiamondBillService;
import com.zhiqi.service.SubordinateService;
import com.zhiqi.utils.DateUtil;

public class SubordinatePage extends ActionSupport implements RequestAware{
	private static final long serialVersionUID = 1L;
	
	private String subUserName;
	private String supUserName;
	private int thispage;
	private int days;
	private short subLevel;

	private SubordinateService subordinateService;
	private DiamondBillService diamondBillService;
	private Map request;
	
	public String subordinateByID(){
		String today = DateUtil.currentDate2();
		String month = DateUtil.currentMonth();
		List<Subordinate> list = subordinateService.getSubordinateByID(subUserName,supUserName,subLevel);
		List<Subordinate> list2 = new ArrayList<Subordinate>();
		for(Subordinate s : list){
			int todayConsumption = diamondBillService.getDateConsumption(s.getSubUserName(),today,"消费");
			int monthConsumption = diamondBillService.getDateConsumption(s.getSubUserName(),month,"消费");
			s.setTodayConsumption(todayConsumption);
			s.setMonthConsumption(monthConsumption);
			list2.add(s);
		}
		list=list2;
		request.put("list", list);

		if(subLevel==0){
			return "subordinatelist";
		}else{
			return "subordinatelist2";
		}
	}
	
	public String noConsumption(){
		String today = DateUtil.currentDate2();
		String month = DateUtil.currentMonth();
		List<Subordinate> list = subordinateService.getNoConsumption(supUserName,subLevel);
		List<Subordinate> list2 = new ArrayList<Subordinate>();
		for(Subordinate s : list){
			int todayConsumption = diamondBillService.getDateConsumption(s.getSubUserName(),today,"消费");
			int monthConsumption = diamondBillService.getDateConsumption(s.getSubUserName(),month,"消费");
			s.setTodayConsumption(todayConsumption);
			s.setMonthConsumption(monthConsumption);
			list2.add(s);
		}
		list=list2;
		request.put("list", list);

		if(subLevel==0){
			return "subordinatelist";
		}else{
			return "subordinatelist2";
		}
	}

	@Override
	public String execute() throws Exception {
		String date = null;
		String today = DateUtil.currentDate2();
		String month = DateUtil.currentMonth();
		if(days==0){
			date=DateUtil.anotherDay(today);
		}else{
			date=DateUtil.xDaysAgo(today, days);
		}
		Page page = subordinateService.getSubordinatePage(thispage,10,supUserName,date,subLevel);
		
		//*********************************
		//查询DiamondBill钻石账单，找到各个用户当月消费情况，当日消费情况（总消费是在DiamondBill钻石账单生成记录的时候改变）
		//*********************************
		List<Subordinate> list = page.getList();
		List<Subordinate> list2 = new ArrayList<Subordinate>();
		for(Subordinate s : list){
			//获取下级的消费情况
			int todayConsumption = diamondBillService.getDateConsumption(s.getSubUserName(),today,"消费");
			int monthConsumption = diamondBillService.getDateConsumption(s.getSubUserName(),month,"消费");
			
			//获取下下级的消费情况
			//当日消费
			int subTodayConsumption = 0;
			//当月消费
			int subMonthConsumption = 0;
			//总消费
			int subSumConsumption = 0;
			//获取下级的下级用户名列表
			List<String> ssubUserList = subordinateService.getSubUserNameList(s.getSubUserName());
			for(String ssubUser : ssubUserList){
				subTodayConsumption += diamondBillService.getDateConsumption(ssubUser, today, "消费");
				subMonthConsumption += diamondBillService.getDateConsumption(ssubUser, month, "消费");
				subSumConsumption += subordinateService.getSubordinateByUserName(ssubUser).getSumConsumption();
			}
			s.setTodayConsumption(todayConsumption+subTodayConsumption);
			s.setMonthConsumption(monthConsumption+subMonthConsumption);
			s.setSumConsumption(subordinateService.getSubordinateByUserName(s.getSubUserName()).getSumConsumption()+subSumConsumption);
			list2.add(s);
		}
		page.setList(list2);
		
		//*********************************
		
		request.put("page", page);
		request.put("days", days);
		if(subLevel==0){
			return "success";
		}else{
			return "successes";
		}
	}
	
	
	public short getSubLevel() {
		return subLevel;
	}

	public void setSubLevel(short subLevel) {
		this.subLevel = subLevel;
	}

	public DiamondBillService getDiamondBillService() {
		return diamondBillService;
	}

	public void setDiamondBillService(DiamondBillService diamondBillService) {
		this.diamondBillService = diamondBillService;
	}

	public String getSubUserName() {
		return subUserName;
	}

	public void setSubUserName(String subUserName) {
		this.subUserName = subUserName;
	}

	public int getThispage() {
		return thispage;
	}

	public void setThispage(int thispage) {
		this.thispage = thispage;
	}

	public void setRequest(Map request) {
		this.request=request;
	}

	public String getSupUserName() {
		return supUserName;
	}

	public void setSupUserName(String supUserName) {
		this.supUserName = supUserName;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
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
