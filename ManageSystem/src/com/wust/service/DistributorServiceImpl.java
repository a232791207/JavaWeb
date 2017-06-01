package com.wust.service;

import java.util.List;

import com.wust.dao.DistributorDao;
import com.wust.domain.Distributor;
import com.wust.domain.DistributorPage;
import com.wust.factory.BasicFactory;

public class DistributorServiceImpl implements DistributorService {
	private DistributorDao dao = BasicFactory.getFactory().getInstance(DistributorDao.class);
	@Override
	public DistributorPage pageDistributor(int thispage, int rowperpage) {
		DistributorPage page = new DistributorPage();
		List<Distributor> list = dao.findDistributorByPage((thispage-1)*rowperpage,rowperpage);
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
	public void addDistributor(Distributor distributor) {
		dao.addDistributor(distributor);
	}
	@Override
	public void deleteDistributor(String id) {
		dao.deleteDistributor(id);
	}
	@Override
	public Distributor getDistributorInfo(String id) {
		Distributor distributor = dao.findDistributorById(id);
		return distributor;
	}
	@Override
	public void updateDistributor(Distributor distributor) {
		dao.updateDistributor(distributor);
	}
	@Override
	public List<Object> findDistributorOption() {
		List<Object> list = dao.findDistributorOption();
		return list;
	}
	@Override
	public Distributor findDistributorByName(String name) {
		Distributor distributor = dao.findDistributorByName(name);
		return distributor;
	}
	@Override
	public DistributorPage selectDistributor(String name, int thispage,
			int rowperpage) {
		DistributorPage page = new DistributorPage();
		List<Distributor> list = dao.selectDistributorPage(name,(thispage-1)*rowperpage,rowperpage);
		//��ǰҳ����
		page.setList(list);
		//��ǰҳ��
		page.setThispage(thispage);
		//ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//�ܼ�¼��
		int countrow = dao.getCountSelectDistributor(name);
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

}
