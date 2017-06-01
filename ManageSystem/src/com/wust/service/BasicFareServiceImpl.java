package com.wust.service;

import java.util.List;

import com.wust.dao.BasicFareDao;
import com.wust.domain.BasicFarePage;
import com.wust.domain.BasicFare;
import com.wust.factory.BasicFactory;

public class BasicFareServiceImpl implements BasicFareService {
	BasicFareDao dao = BasicFactory.getFactory().getInstance(BasicFareDao.class);
	@Override
	public BasicFarePage getBasicFareByPage(int thispage, int rowperpage) {
		BasicFarePage page = new BasicFarePage();
		List<BasicFare> list = dao.findBasicFareByPage((thispage-1)*rowperpage,rowperpage);
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
	public void addBasicFare(BasicFare basicFare) {
		dao.addBasicFare(basicFare);
	}
	@Override
	public void deleteBasicFare(String id) {
		dao.deleteBasicFare(id);
	}
	@Override
	public BasicFare getBasicFareInfo(String id) {
		return dao.getBasicFareInfo(id);
	}
	@Override
	public void updateBasicFare(BasicFare basicFare) {
		dao.updateBasicFare(basicFare);
	}
	@Override
	public BasicFare findBasicfByArea(String area) {
		return dao.findBasicfByArea(area);
	}
	@Override
	public BasicFarePage getBasicFreightByPage(int thispage, int rowperpage) {
		BasicFarePage page = new BasicFarePage();
		List<BasicFare> list = dao.findBasicFreightByPage((thispage-1)*rowperpage,rowperpage);
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
	public BasicFare findBasicf2ByArea(String area) {
		return dao.findBasicf2ByArea(area);
	}
	@Override
	public void addBasicFreight(BasicFare basicFare) {
		dao.addBasicFreight(basicFare);
		
	}
	@Override
	public void deleteBasicFreight(String id) {
		dao.deleteBasicFreight(id);
	}
	@Override
	public BasicFare getBasicFreightInfo(String id) {
		return dao.getBasicFreightInfo(id);
	}

}
