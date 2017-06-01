package com.zhiqi.action.agent;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.CashBill;
import com.zhiqi.bean.DiamondBill;
import com.zhiqi.bean.Subordinate;
import com.zhiqi.bean.User;
import com.zhiqi.service.CashBillService;
import com.zhiqi.service.DiamondBillService;
import com.zhiqi.service.SubordinateService;
import com.zhiqi.service.UserService;
import com.zhiqi.utils.DateUtil;
import com.zhiqi.utils.DiscountUtil;

public class DiamondRecharge extends ActionSupport implements SessionAware,RequestAware{
	private static final long serialVersionUID = 1L;
	
	private int diamondNum;
	private String userName;
	private DiamondBillService diamondBillService;
	private CashBillService cashBillService;
	private SubordinateService subordinateService;
	private UserService userService;
	private Map session;
	private Map request;
	
	public String userRecharge(){
		synchronized (this.getClass()) {
			User user = userService.findUserByUserName(userName);
			Subordinate subordinate = subordinateService.getSubordinateByUserName(userName);
			String time = DateUtil.currentTime();
			String today = DateUtil.currentDate();
			DiamondBill diamondBill = new DiamondBill();
			CashBill cashBill = new CashBill();
			if(user.getLevel()==1||user.getLevel()==3||(subordinate.getSupLevel()==2)&&(subordinate.getSubLevel()==2)){//奇大使和馆主,以及副馆主邀请的副馆主充值钻石
				if(user.getBalance()-diamondNum/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel())>0){
					//生成diamondBill的ID
					int n = diamondBillService.todaysBillNum(userName,DateUtil.currentDate2())+1;
					String s = "";
					if(n<=9){
						s="00"+n;
					}else if(n<=99){
						s="0"+n;
					}else{
						s=""+n;
					}
					//封装diamondBill的bean
					String id = "D"+userName+"A"+today+s;
					diamondBill.setId(id);
					diamondBill.setUserName(userName);
					diamondBill.setTime(time);
					diamondBill.setComment("充值"+diamondNum+"颗钻石(手动)");
					diamondBill.setIncome(diamondNum);
					diamondBill.setBalance(user.getDiamond()+diamondNum);
					diamondBill.setState(1);
					//向diamondBill表插入一条记录
					diamondBillService.add(diamondBill);
					
					//生成CashBill的ID
					n = cashBillService.todaysBillNum(userName,DateUtil.currentDate2())+1;
					if(n<=9){
						s="00"+n;
					}else if(n<=99){
						s="0"+n;
					}else{
						s=""+n;
					}
					//封装cashBill的bean
					cashBill.setId("C"+userName+"A"+today+s);
					cashBill.setUserName(userName);
					cashBill.setTime(time);
					cashBill.setComment("购买"+diamondNum+"颗钻石(手动)");
					cashBill.setIncome(-diamondNum/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel()));
					cashBill.setBalance(user.getBalance()-diamondNum/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel()));
					cashBill.setState(1);
					//向cashBill表中插入一条记录
					cashBillService.add(cashBill);
					//更新User表中的类容
					//1.设置新钻石余额
					user.setDiamond(user.getDiamond()+diamondNum);
					//2.设置新现金余额
					user.setBalance(user.getBalance()-diamondNum/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel()));
					//3.更新用户信息
					userService.update(user);
					user = userService.findEncodedUserByUserName(userName);
					session.put("user", user);
					request.put("info", "充值成功！");
				}else{
					request.put("info", "充值失败！余额不足！");
				}
			}else{//PT用户和副馆主充值钻石
				//生成diamondBill的ID
				int n = diamondBillService.todaysBillNum(userName,DateUtil.currentDate2())+1;
				String s = "";
				if(n<=9){
					s="00"+n;
				}else if(n<=99){
					s="0"+n;
				}else{
					s=""+n;
				}
				//封装diamondBill的bean
				String id = "D"+userName+"A"+today+s;
				diamondBill.setId(id);
				diamondBill.setUserName(userName);
				diamondBill.setTime(time);
				diamondBill.setComment("充值"+diamondNum+"颗钻石(手动)");
				diamondBill.setIncome(diamondNum);
				diamondBill.setBalance(user.getDiamond()+diamondNum);
				diamondBill.setState(0);
				//向diamondBill表插入一条记录
				diamondBillService.add(diamondBill);
				request.put("info", "充值请求已提交，请等待确认！");
			}
			return "userrecharge";
		}
	}

	public SubordinateService getSubordinateService() {
		return subordinateService;
	}

	public void setSubordinateService(SubordinateService subordinateService) {
		this.subordinateService = subordinateService;
	}

	public int getDiamondNum() {
		return diamondNum;
	}

	public void setDiamondNum(int diamondNum) {
		this.diamondNum = diamondNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public DiamondBillService getDiamondBillService() {
		return diamondBillService;
	}

	public void setDiamondBillService(DiamondBillService diamondBillService) {
		this.diamondBillService = diamondBillService;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void setSession(Map session) {
		this.session=session;
	}

	public Map getSession() {
		return session;
	}

	@Override
	public void setRequest(Map request) {
		this.request=request;
	}

	public Map getRequest() {
		return request;
	}
	
}
