package com.zhiqi.service.impl;

import java.util.List;

import com.zhiqi.bean.DiamondBill;
import com.zhiqi.bean.Page;
import com.zhiqi.dao.DiamondBillDao;
import com.zhiqi.service.DiamondBillService;
import com.zhiqi.utils.DateUtil;

public class DiamondBillServiceImpl implements DiamondBillService {
	private DiamondBillDao diamondBillDao;

	public DiamondBillDao getDiamondBillDao() {
		return diamondBillDao;
	}

	public void setDiamondBillDao(DiamondBillDao diamondBillDao) {
		this.diamondBillDao = diamondBillDao;
	}

	@Override
	public Page findDiamondBillPageByTime(int thispage, int rowperpage, String stime, String etime, int state, String userName,String string,String s) {
		Page page = new Page();
		List<DiamondBill> list = null;
		int countrow = 0;
		if(!stime.equals("")&&!etime.equals("")){//�����������ʼʱ��ͽ���ʱ�䲻Ϊ��
			etime = DateUtil.anotherDay(etime);
			list  = diamondBillDao.findDiamondBillPageByTime((thispage-1)*rowperpage,rowperpage,stime,etime,state,userName,string,s);
			countrow = diamondBillDao.getCountDiamondBillByTime(stime,etime,state,userName,string,s);
		}else{//�����������ʼʱ��ͽ���ʱ��Ϊ��
			list = diamondBillDao.findDiamondBillPage((thispage-1)*rowperpage, rowperpage,state,userName,string,s);
			countrow = diamondBillDao.getCountDiamondBill(state,userName,string,s);
		}
		//��ǰҳ����
		page.setList(list);
		//��ǰҳ��
		page.setThispage(thispage);
		//ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//�ܼ�¼��
		
		page.setCountrow(countrow);
		//��ҳ��
		int countpage = countrow/rowperpage + (countrow%rowperpage==0?0:1);
		page.setCountpage(countpage);
		//��ҳ
		page.setFirstpage(1);
		//βҳ
		page.setLastpage(countpage);
		//��һҳ
		page.setPrepage(thispage==1?1:thispage-1);
		//��һҳ
		page.setNextpage(thispage==countpage?thispage:thispage+1);
		return page;
	}

	@Override
	public int todaysBillNum(String userName, String today) {
		return diamondBillDao.todaysBillNum(userName,today);
	}

	@Override
	public void add(DiamondBill diamondBill) {
		diamondBillDao.add(diamondBill);
	}

	@Override
	public List<DiamondBill> getRechargeApply(String stime, String etime, String subUserName) {
		List<DiamondBill> list = null;
		if(!stime.equals("")&&!etime.equals("")){//�����������ʼʱ��ͽ���ʱ�䲻Ϊ��
			etime = DateUtil.anotherDay(etime);
			list  = diamondBillDao.getRechargeApply(stime,etime,subUserName);
		}else{//�����������ʼʱ��ͽ���ʱ��Ϊ��
			list = diamondBillDao.getRechargeApply(subUserName);
		}
		return list;
	}

	@Override
	public DiamondBill getDiamondBill(String id) {
		return diamondBillDao.getDiamondBill(id);
	}

	@Override
	public void update(DiamondBill diamondBill) {
		// TODO Auto-generated method stub
		diamondBillDao.update(diamondBill);
	}

	@Override
	public int getApplyingNum(String subUserName) {
		return diamondBillDao.getApplyingNum(subUserName);
	}

	@Override
	public int getDateConsumption(String subUserName, String date, String string) {
		return diamondBillDao.getDateConsumption(subUserName,date,string);
	}

	
}
