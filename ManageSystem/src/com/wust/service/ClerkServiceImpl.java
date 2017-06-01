package com.wust.service;

import java.util.List;

import com.wust.dao.ClerkDao;
import com.wust.domain.Clerk;
import com.wust.domain.ClerkPage;
import com.wust.factory.BasicFactory;

public class ClerkServiceImpl implements ClerkService {
	private ClerkDao dao = BasicFactory.getFactory().getInstance(ClerkDao.class);
	@Override
	public ClerkPage pageClerk(int thispage, int rowperpage) {
		ClerkPage page = new ClerkPage();
		List<Clerk> list = dao.findClerkByPage((thispage-1)*rowperpage,rowperpage);
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
	public Clerk getClerkInfo(String id) {
		Clerk clerk = dao.findClerkById(id);
		return clerk;
	}

	@Override
	public void updateClerk(Clerk clerk) {
		dao.updateClerk(clerk);
		
	}

	@Override
	public void deleteClerk(String id) {
		dao.deleteClerk(id);
	}

	@Override
	public void addClerk(Clerk clerk) {
		dao.addClerk(clerk);
	}

	@Override
	public Clerk isClerk(String id, String password, String type) {
		Clerk clerk = dao.findClerkByIdPasTyp(id, password, type);
		return clerk;
	}

	@Override
	public void updatePassword(String id, String password) {
		dao.updatePassword(id,password);
	}

	@Override
	public List<Object> findSalesmanOption() {
		List<Object> list = dao.findSalesmanOption();
		return list;
	}

	@Override
	public ClerkPage selectClerk(String name, String type, int thispage,
			int rowperpage) {
		ClerkPage page = new ClerkPage();
		List<Clerk> list = dao.selectClerk(name,type,(thispage-1)*rowperpage,rowperpage);
		//��ǰҳ����
		page.setList(list);
		//��ǰҳ��
		page.setThispage(thispage);
		//ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//�ܼ�¼��
		int countrow = dao.getCountSelectedClerk(name,type);
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
