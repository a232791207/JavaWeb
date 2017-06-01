package com.zhiqi.action.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.CashBill;
import com.zhiqi.bean.DiamondBill;
import com.zhiqi.bean.Page;
import com.zhiqi.bean.User;
import com.zhiqi.service.CashBillService;
import com.zhiqi.service.DiamondBillService;
import com.zhiqi.service.SubordinateService;
import com.zhiqi.service.UserService;
import com.zhiqi.utils.DateUtil;
import com.zhiqi.utils.DiscountUtil;

public class RechargeApply extends ActionSupport implements RequestAware{
	private static final long serialVersionUID = 1L;
	
	private String stime;
	private String etime;
	private String supUserName;
	private int thispage;
	private String id;
	private Map request;
	private DiamondBillService diamondBillService;
	private SubordinateService subordinateService;
	private CashBillService cashBillService;
	private UserService userService;
	
	public String applyed(){
		List<String> list = subordinateService.getSubUserNameList(supUserName);
		List list2 = new ArrayList();
		int maxCountPage = 0;
		Page maxpage = new Page();
		for(String subUserName : list){
			Page page = diamondBillService.findDiamondBillPageByTime(thispage, 10, stime, etime, 0, subUserName, ">","充值");
			if(page.getCountpage()>=maxCountPage){
				maxCountPage=page.getCountpage();
				maxpage=page;
			}
			for(Object diamondBill : page.getList()){
				list2.add(diamondBill);
			}
		}
		maxpage.setList(list2);
		request.put("page", maxpage);
		return "applyed";
	}
	
	public String accept(){
		String time = DateUtil.currentTime();
		String today = DateUtil.currentDate();
		synchronized (this.getClass()) {
			System.out.println(id);
			//通过id获取被接受的账单
			DiamondBill diamondBill = diamondBillService.getDiamondBill(id);
			//获取出售钻石的人的信息
			User user = userService.findUserByUserName(supUserName);
			//获取购买钻石的人的信息
			User user2 = userService.findUserByUserName(diamondBill.getUserName());
			//获取出售钻石的人的余额
			int sellDiamond = user.getDiamond();
			//获取需要购买的钻石数
			int buyDiamond = diamondBill.getIncome();
			//充值钻石
			//判断钻石余额是否足够这次出售
			if(sellDiamond<buyDiamond){   //钻石余额不足
				if(user.getAutoRecharge()>0){	//判断是否有自动充值
					if(sellDiamond+user.getAutoRecharge()<buyDiamond){	//如果自动充值后仍然不够，则不进行自动充值，提示用户
						request.put("info", "您钻石余额不足，无法满足本次充值申请！（建议提高自动充值额度！）");
					}else{	//判断现金余额是否满足本次充值
						//现金充足，自动充值钻石，出售钻石
						if(user.getBalance()-user.getAutoRecharge()/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel())>0){
							//买钻石
							//为卖家生成（自动充值）钻石账单
							DiamondBill diamondBill2 = new DiamondBill();
							//生成diamondBill2的ID
							int n = diamondBillService.todaysBillNum(supUserName,DateUtil.currentDate2())+1;
							String s = "";
							if(n<=9){
								s="00"+n;
							}else if(n<=99){
								s="0"+n;
							}else{
								s=""+n;
							}
							//封装diamondBill2的bean
							String id = "D"+supUserName+"A"+today+s;
							diamondBill2.setId(id);
							diamondBill2.setUserName(supUserName);
							diamondBill2.setTime(time);
							diamondBill2.setComment("购买"+user.getAutoRecharge()+"颗钻石(自动)");
							diamondBill2.setIncome(user.getAutoRecharge());
							diamondBill2.setBalance(user.getDiamond()+user.getAutoRecharge());
							diamondBill2.setState(1);
							//向diamondBill表插入一条记录
							diamondBillService.add(diamondBill2);
							
							//为卖家生成（自动充值）现金账单
							CashBill cashBill = new CashBill();
							//生成CashBill的ID
							n = cashBillService.todaysBillNum(supUserName,DateUtil.currentDate2())+1;
							if(n<=9){
								s="00"+n;
							}else if(n<=99){
								s="0"+n;
							}else{
								s=""+n;
							}
							//封装cashBill的bean
							cashBill.setId("C"+supUserName+"A"+today+s);
							cashBill.setUserName(supUserName);
							cashBill.setTime(time);
							cashBill.setComment("购买"+user.getAutoRecharge()+"颗钻石(自动)");
							cashBill.setIncome(-user.getAutoRecharge()/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel()));
							cashBill.setBalance(user.getBalance()-user.getAutoRecharge()/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel()));
							cashBill.setState(1);
							//向cashBill表中插入一条记录
							cashBillService.add(cashBill);
							
							//卖钻石
							//为卖家生成出售钻石账单
							DiamondBill diamondBill3 = new DiamondBill();
							//生成diamondBill3的ID
							int n2 = diamondBillService.todaysBillNum(supUserName,DateUtil.currentDate2())+1;
							String s2 = "";
							if(n2<=9){
								s2="00"+n2;
							}else if(n2<=99){
								s2="0"+n2;
							}else{
								s2=""+n2;
							}
							//封装diamondBill3的bean
							String id2 = "D"+supUserName+"A"+today+s2;
							diamondBill3.setId(id2);
							diamondBill3.setUserName(supUserName);
							diamondBill3.setTime(time);
							diamondBill3.setComment("出售"+buyDiamond+"颗钻石");
							diamondBill3.setIncome(-buyDiamond);
							diamondBill3.setBalance(user.getDiamond()-buyDiamond);
							diamondBill3.setState(1);
							//向diamondBill表插入一条记录
							diamondBillService.add(diamondBill3);
							
							//为买家更新钻石账单信息
							//购买钻石申请通过，将state改成1
							diamondBill.setState(1);
							diamondBill.setBalance(user2.getDiamond()+buyDiamond);
							//修改后的账单表存入数据库
							diamondBillService.update(diamondBill);
							
							//更新卖家余额
							//1.设置新钻石余额
							user.setDiamond(user.getDiamond()+user.getAutoRecharge()-buyDiamond);
							//2.设置新现金余额
							user.setBalance(user.getBalance()-user.getAutoRecharge()/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel()));
							//3.更新用户信息
							userService.update(user);
							
							//更新买家余额
							//1.设置新钻石余额
							user2.setDiamond(user2.getDiamond()+buyDiamond);
							//2.更新用户信息
							userService.update(user2);
							//提示用户使用自动购买钻石，出售的钻石
							request.put("info", "您钻石余额不足，系统为您自动充值了"+user.getAutoRecharge()+"颗钻石来满足本次操作！");
							
						}else{	//钻石余额不足，有自动充值，但是现金余额不够，提示用户
							request.put("info", "您钻石余额不足，系统自动充值失败！（现金余额不足）");
						}
					}
				}else{ //钻石余额不足，又没有自动充值，提示用户手动充值
					request.put("info", "您钻石余额不足，无法满足本次充值申请！（建议开通自动充值或者提高自动充值额度！）");
				}
			}else{	//钻石余额充足，出售钻石
				//卖钻石
				//为卖家生成出售钻石账单
				DiamondBill diamondBill3 = new DiamondBill();
				//生成diamondBill3的ID
				int n2 = diamondBillService.todaysBillNum(supUserName,DateUtil.currentDate2())+1;
				String s2 = "";
				if(n2<=9){
					s2="00"+n2;
				}else if(n2<=99){
					s2="0"+n2;
				}else{
					s2=""+n2;
				}
				//封装diamondBill3的bean
				String id2 = "D"+supUserName+"A"+today+s2;
				diamondBill3.setId(id2);
				diamondBill3.setUserName(supUserName);
				diamondBill3.setTime(time);
				diamondBill3.setComment("出售"+buyDiamond+"颗钻石");
				diamondBill3.setIncome(-buyDiamond);
				diamondBill3.setBalance(user.getDiamond()-buyDiamond);
				diamondBill3.setState(1);
				//向diamondBill表插入一条记录
				diamondBillService.add(diamondBill3);
				
				//为买家更新钻石账单信息
				//购买钻石申请通过，将state改成1
				diamondBill.setState(1);
				diamondBill.setBalance(user2.getDiamond()+buyDiamond);
				//修改后的账单表存入数据库
				diamondBillService.update(diamondBill);
				
				//更新卖家余额
				//1.设置新钻石余额
				user.setDiamond(user.getDiamond()-buyDiamond);
				//3.更新用户信息
				userService.update(user);
				
				//更新买家余额
				//1.设置新钻石余额
				user2.setDiamond(user2.getDiamond()+buyDiamond);
				//2.更新用户信息
				userService.update(user2);
				//提示用户使用自动购买钻石，出售的钻石
				request.put("info", "操作成功！已接受本次购买请求！");
			}
			
			//查找操作后剩余购买请求
			List<String> list = subordinateService.getSubUserNameList(supUserName);
			List list2 = new ArrayList();
			int maxCountPage = 0;
			Page maxpage = new Page();
			for(String subUserName : list){
				Page page = diamondBillService.findDiamondBillPageByTime(thispage, 10, stime, etime, 0, subUserName,"=","充值");
				if(page.getCountpage()>=maxCountPage){
					maxCountPage=page.getCountpage();
					maxpage=page;
				}
				for(Object diamondBill2 : page.getList()){
					list2.add(diamondBill2);
				}
			}
			maxpage.setList(list2);
			request.put("page", maxpage);
			
			return "success";
		}
	}
	
	public String decline(){
		DiamondBill diamondBill = diamondBillService.getDiamondBill(id);
		diamondBill.setState(2);
		diamondBillService.update(diamondBill);
		request.put("info", "操作成功！已拒绝本次购买请求！");
		List<String> list = subordinateService.getSubUserNameList(supUserName);
		List list2 = new ArrayList();
		int maxCountPage = 0;
		Page maxpage = new Page();
		for(String subUserName : list){
			Page page = diamondBillService.findDiamondBillPageByTime(thispage, 10, stime, etime, 0, subUserName,"=","充值");
			if(page.getCountpage()>=maxCountPage){
				maxCountPage=page.getCountpage();
				maxpage=page;
			}
			for(Object diamondBill2 : page.getList()){
				list2.add(diamondBill2);
			}
		}
		maxpage.setList(list2);
		request.put("page", maxpage);
		return "success";
	}
	
	@Override
	public String execute() throws Exception {
		List<String> list = subordinateService.getSubUserNameList(supUserName);
		List list2 = new ArrayList();
		int maxCountPage = 0;
		Page maxpage = new Page();
		for(String subUserName : list){
			Page page = diamondBillService.findDiamondBillPageByTime(thispage, 10, stime, etime, 0, subUserName,"=","充值");
			if(page.getCountpage()>=maxCountPage){
				maxCountPage=page.getCountpage();
				maxpage=page;
			}
			for(Object diamondBill : page.getList()){
				list2.add(diamondBill);
			}
		}
		maxpage.setList(list2);
		request.put("page", maxpage);
		return "success";
	}
	
	@Override
	public void setRequest(Map request) {
		this.request = request;
	}

	
	public CashBillService getCashBillService() {
		return cashBillService;
	}

	public void setCashBillService(CashBillService cashBillService) {
		this.cashBillService = cashBillService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getThispage() {
		return thispage;
	}

	public void setThispage(int thispage) {
		this.thispage = thispage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSupUserName() {
		return supUserName;
	}

	public void setSupUserName(String supUserName) {
		this.supUserName = supUserName;
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

	public DiamondBillService getDiamondBillService() {
		return diamondBillService;
	}

	public void setDiamondBillService(DiamondBillService diamondBillService) {
		this.diamondBillService = diamondBillService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map getRequest() {
		return request;
	}

	public SubordinateService getSubordinateService() {
		return subordinateService;
	}

	public void setSubordinateService(SubordinateService subordinateService) {
		this.subordinateService = subordinateService;
	}
	

}
