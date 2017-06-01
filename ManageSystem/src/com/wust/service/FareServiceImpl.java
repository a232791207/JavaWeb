package com.wust.service;

import java.util.List;

import com.wust.dao.FareDao;
import com.wust.domain.Fare;
import com.wust.domain.FarePage;
import com.wust.factory.BasicFactory;

public class FareServiceImpl implements FareService {
	FareDao dao = BasicFactory.getFactory().getInstance(FareDao.class);
	@Override
	public FarePage getFareByPage(int thispage, int rowperpage) {
		FarePage page = new FarePage();
		List<Fare> list = dao.findFareByPage((thispage-1)*rowperpage,rowperpage);
		//��ǰҳ����
		page.setList(list);
		//��ǰҳ��
		page.setThispage(thispage);
		//ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//�ܼ�¼��
		int countrow = dao.getCountRow();
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
	public void addFare(String id, String area, double fare, double num,
			double sumfare) {
		dao.addFare(id,area,fare,num,sumfare);
	}
	@Override
	public void deleteFare(String id) {
		dao.delete(id);
		
	}
	@Override
	public Fare getFare(String orderid) {
		return dao.getFare(orderid);
	}
	@Override
	public void updateFare(Fare fare) {
		dao.updateFare(fare);
		
	}
	@Override
	public void deleteFreight(String id) {
		dao.delete2(id);
		
	}
	@Override
	public FarePage getFreightByPage(int thispage, int rowperpage) {
		FarePage page = new FarePage();
		List<Fare> list = dao.findFreightByPage((thispage-1)*rowperpage,rowperpage);
		//��ǰҳ����
		page.setList(list);
		//��ǰҳ��
		page.setThispage(thispage);
		//ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//�ܼ�¼��
		int countrow = dao.getCountRow2();
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
	public Fare getFreight(String orderid) {
		return dao.getFreight(orderid);
	}
	@Override
	public void updateFreight(Fare fare) {
		dao.updateFreight(fare);
	}
	@Override
	public void addFreight(String orderid, String area, double fare,
			double Num, double sumfare) {
		dao.addFreight(orderid,area,fare,Num,sumfare);
	}

}
