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
			Page page = diamondBillService.findDiamondBillPageByTime(thispage, 10, stime, etime, 0, subUserName, ">","��ֵ");
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
			//ͨ��id��ȡ�����ܵ��˵�
			DiamondBill diamondBill = diamondBillService.getDiamondBill(id);
			//��ȡ������ʯ���˵���Ϣ
			User user = userService.findUserByUserName(supUserName);
			//��ȡ������ʯ���˵���Ϣ
			User user2 = userService.findUserByUserName(diamondBill.getUserName());
			//��ȡ������ʯ���˵����
			int sellDiamond = user.getDiamond();
			//��ȡ��Ҫ�������ʯ��
			int buyDiamond = diamondBill.getIncome();
			//��ֵ��ʯ
			//�ж���ʯ����Ƿ��㹻��γ���
			if(sellDiamond<buyDiamond){   //��ʯ����
				if(user.getAutoRecharge()>0){	//�ж��Ƿ����Զ���ֵ
					if(sellDiamond+user.getAutoRecharge()<buyDiamond){	//����Զ���ֵ����Ȼ�������򲻽����Զ���ֵ����ʾ�û�
						request.put("info", "����ʯ���㣬�޷����㱾�γ�ֵ���룡����������Զ���ֵ��ȣ���");
					}else{	//�ж��ֽ�����Ƿ����㱾�γ�ֵ
						//�ֽ���㣬�Զ���ֵ��ʯ��������ʯ
						if(user.getBalance()-user.getAutoRecharge()/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel())>0){
							//����ʯ
							//Ϊ�������ɣ��Զ���ֵ����ʯ�˵�
							DiamondBill diamondBill2 = new DiamondBill();
							//����diamondBill2��ID
							int n = diamondBillService.todaysBillNum(supUserName,DateUtil.currentDate2())+1;
							String s = "";
							if(n<=9){
								s="00"+n;
							}else if(n<=99){
								s="0"+n;
							}else{
								s=""+n;
							}
							//��װdiamondBill2��bean
							String id = "D"+supUserName+"A"+today+s;
							diamondBill2.setId(id);
							diamondBill2.setUserName(supUserName);
							diamondBill2.setTime(time);
							diamondBill2.setComment("����"+user.getAutoRecharge()+"����ʯ(�Զ�)");
							diamondBill2.setIncome(user.getAutoRecharge());
							diamondBill2.setBalance(user.getDiamond()+user.getAutoRecharge());
							diamondBill2.setState(1);
							//��diamondBill�����һ����¼
							diamondBillService.add(diamondBill2);
							
							//Ϊ�������ɣ��Զ���ֵ���ֽ��˵�
							CashBill cashBill = new CashBill();
							//����CashBill��ID
							n = cashBillService.todaysBillNum(supUserName,DateUtil.currentDate2())+1;
							if(n<=9){
								s="00"+n;
							}else if(n<=99){
								s="0"+n;
							}else{
								s=""+n;
							}
							//��װcashBill��bean
							cashBill.setId("C"+supUserName+"A"+today+s);
							cashBill.setUserName(supUserName);
							cashBill.setTime(time);
							cashBill.setComment("����"+user.getAutoRecharge()+"����ʯ(�Զ�)");
							cashBill.setIncome(-user.getAutoRecharge()/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel()));
							cashBill.setBalance(user.getBalance()-user.getAutoRecharge()/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel()));
							cashBill.setState(1);
							//��cashBill���в���һ����¼
							cashBillService.add(cashBill);
							
							//����ʯ
							//Ϊ�������ɳ�����ʯ�˵�
							DiamondBill diamondBill3 = new DiamondBill();
							//����diamondBill3��ID
							int n2 = diamondBillService.todaysBillNum(supUserName,DateUtil.currentDate2())+1;
							String s2 = "";
							if(n2<=9){
								s2="00"+n2;
							}else if(n2<=99){
								s2="0"+n2;
							}else{
								s2=""+n2;
							}
							//��װdiamondBill3��bean
							String id2 = "D"+supUserName+"A"+today+s2;
							diamondBill3.setId(id2);
							diamondBill3.setUserName(supUserName);
							diamondBill3.setTime(time);
							diamondBill3.setComment("����"+buyDiamond+"����ʯ");
							diamondBill3.setIncome(-buyDiamond);
							diamondBill3.setBalance(user.getDiamond()-buyDiamond);
							diamondBill3.setState(1);
							//��diamondBill�����һ����¼
							diamondBillService.add(diamondBill3);
							
							//Ϊ��Ҹ�����ʯ�˵���Ϣ
							//������ʯ����ͨ������state�ĳ�1
							diamondBill.setState(1);
							diamondBill.setBalance(user2.getDiamond()+buyDiamond);
							//�޸ĺ���˵���������ݿ�
							diamondBillService.update(diamondBill);
							
							//�����������
							//1.��������ʯ���
							user.setDiamond(user.getDiamond()+user.getAutoRecharge()-buyDiamond);
							//2.�������ֽ����
							user.setBalance(user.getBalance()-user.getAutoRecharge()/DiscountUtil.getDiamondPrice()*DiscountUtil.getDiscount(user.getLevel()));
							//3.�����û���Ϣ
							userService.update(user);
							
							//����������
							//1.��������ʯ���
							user2.setDiamond(user2.getDiamond()+buyDiamond);
							//2.�����û���Ϣ
							userService.update(user2);
							//��ʾ�û�ʹ���Զ�������ʯ�����۵���ʯ
							request.put("info", "����ʯ���㣬ϵͳΪ���Զ���ֵ��"+user.getAutoRecharge()+"����ʯ�����㱾�β�����");
							
						}else{	//��ʯ���㣬���Զ���ֵ�������ֽ���������ʾ�û�
							request.put("info", "����ʯ���㣬ϵͳ�Զ���ֵʧ�ܣ����ֽ����㣩");
						}
					}
				}else{ //��ʯ���㣬��û���Զ���ֵ����ʾ�û��ֶ���ֵ
					request.put("info", "����ʯ���㣬�޷����㱾�γ�ֵ���룡�����鿪ͨ�Զ���ֵ��������Զ���ֵ��ȣ���");
				}
			}else{	//��ʯ�����㣬������ʯ
				//����ʯ
				//Ϊ�������ɳ�����ʯ�˵�
				DiamondBill diamondBill3 = new DiamondBill();
				//����diamondBill3��ID
				int n2 = diamondBillService.todaysBillNum(supUserName,DateUtil.currentDate2())+1;
				String s2 = "";
				if(n2<=9){
					s2="00"+n2;
				}else if(n2<=99){
					s2="0"+n2;
				}else{
					s2=""+n2;
				}
				//��װdiamondBill3��bean
				String id2 = "D"+supUserName+"A"+today+s2;
				diamondBill3.setId(id2);
				diamondBill3.setUserName(supUserName);
				diamondBill3.setTime(time);
				diamondBill3.setComment("����"+buyDiamond+"����ʯ");
				diamondBill3.setIncome(-buyDiamond);
				diamondBill3.setBalance(user.getDiamond()-buyDiamond);
				diamondBill3.setState(1);
				//��diamondBill�����һ����¼
				diamondBillService.add(diamondBill3);
				
				//Ϊ��Ҹ�����ʯ�˵���Ϣ
				//������ʯ����ͨ������state�ĳ�1
				diamondBill.setState(1);
				diamondBill.setBalance(user2.getDiamond()+buyDiamond);
				//�޸ĺ���˵���������ݿ�
				diamondBillService.update(diamondBill);
				
				//�����������
				//1.��������ʯ���
				user.setDiamond(user.getDiamond()-buyDiamond);
				//3.�����û���Ϣ
				userService.update(user);
				
				//����������
				//1.��������ʯ���
				user2.setDiamond(user2.getDiamond()+buyDiamond);
				//2.�����û���Ϣ
				userService.update(user2);
				//��ʾ�û�ʹ���Զ�������ʯ�����۵���ʯ
				request.put("info", "�����ɹ����ѽ��ܱ��ι�������");
			}
			
			//���Ҳ�����ʣ�๺������
			List<String> list = subordinateService.getSubUserNameList(supUserName);
			List list2 = new ArrayList();
			int maxCountPage = 0;
			Page maxpage = new Page();
			for(String subUserName : list){
				Page page = diamondBillService.findDiamondBillPageByTime(thispage, 10, stime, etime, 0, subUserName,"=","��ֵ");
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
		request.put("info", "�����ɹ����Ѿܾ����ι�������");
		List<String> list = subordinateService.getSubUserNameList(supUserName);
		List list2 = new ArrayList();
		int maxCountPage = 0;
		Page maxpage = new Page();
		for(String subUserName : list){
			Page page = diamondBillService.findDiamondBillPageByTime(thispage, 10, stime, etime, 0, subUserName,"=","��ֵ");
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
			Page page = diamondBillService.findDiamondBillPageByTime(thispage, 10, stime, etime, 0, subUserName,"=","��ֵ");
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
