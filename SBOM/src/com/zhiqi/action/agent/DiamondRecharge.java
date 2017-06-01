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
			if(user.getLevel()==1||user.getLevel()==3||(subordinate.getSupLevel()==2)&&(subordinate.getSubLevel()==2)){//���ʹ�͹���,�Լ�����������ĸ�������ֵ��ʯ
				if(user.getBalance()-diamondNum/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel())>0){
					//����diamondBill��ID
					int n = diamondBillService.todaysBillNum(userName,DateUtil.currentDate2())+1;
					String s = "";
					if(n<=9){
						s="00"+n;
					}else if(n<=99){
						s="0"+n;
					}else{
						s=""+n;
					}
					//��װdiamondBill��bean
					String id = "D"+userName+"A"+today+s;
					diamondBill.setId(id);
					diamondBill.setUserName(userName);
					diamondBill.setTime(time);
					diamondBill.setComment("��ֵ"+diamondNum+"����ʯ(�ֶ�)");
					diamondBill.setIncome(diamondNum);
					diamondBill.setBalance(user.getDiamond()+diamondNum);
					diamondBill.setState(1);
					//��diamondBill�����һ����¼
					diamondBillService.add(diamondBill);
					
					//����CashBill��ID
					n = cashBillService.todaysBillNum(userName,DateUtil.currentDate2())+1;
					if(n<=9){
						s="00"+n;
					}else if(n<=99){
						s="0"+n;
					}else{
						s=""+n;
					}
					//��װcashBill��bean
					cashBill.setId("C"+userName+"A"+today+s);
					cashBill.setUserName(userName);
					cashBill.setTime(time);
					cashBill.setComment("����"+diamondNum+"����ʯ(�ֶ�)");
					cashBill.setIncome(-diamondNum/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel()));
					cashBill.setBalance(user.getBalance()-diamondNum/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel()));
					cashBill.setState(1);
					//��cashBill���в���һ����¼
					cashBillService.add(cashBill);
					//����User���е�����
					//1.��������ʯ���
					user.setDiamond(user.getDiamond()+diamondNum);
					//2.�������ֽ����
					user.setBalance(user.getBalance()-diamondNum/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel()));
					//3.�����û���Ϣ
					userService.update(user);
					user = userService.findEncodedUserByUserName(userName);
					session.put("user", user);
					request.put("info", "��ֵ�ɹ���");
				}else{
					request.put("info", "��ֵʧ�ܣ����㣡");
				}
			}else{//PT�û��͸�������ֵ��ʯ
				//����diamondBill��ID
				int n = diamondBillService.todaysBillNum(userName,DateUtil.currentDate2())+1;
				String s = "";
				if(n<=9){
					s="00"+n;
				}else if(n<=99){
					s="0"+n;
				}else{
					s=""+n;
				}
				//��װdiamondBill��bean
				String id = "D"+userName+"A"+today+s;
				diamondBill.setId(id);
				diamondBill.setUserName(userName);
				diamondBill.setTime(time);
				diamondBill.setComment("��ֵ"+diamondNum+"����ʯ(�ֶ�)");
				diamondBill.setIncome(diamondNum);
				diamondBill.setBalance(user.getDiamond()+diamondNum);
				diamondBill.setState(0);
				//��diamondBill�����һ����¼
				diamondBillService.add(diamondBill);
				request.put("info", "��ֵ�������ύ����ȴ�ȷ�ϣ�");
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
