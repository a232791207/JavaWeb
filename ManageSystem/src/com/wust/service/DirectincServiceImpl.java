package com.wust.service;

import java.util.List;

import com.wust.dao.DirectincDao;
import com.wust.domain.Directinc;
import com.wust.domain.DirectincPage;
import com.wust.factory.BasicFactory;

public class DirectincServiceImpl implements DirectincService {
	DirectincDao dao = BasicFactory.getFactory().getInstance(DirectincDao.class);
	@Override
	public Directinc findDirectinc(String time, String distributor, String area, String format, String level) {
		Directinc directinc = dao.findDirectinc(time,distributor,area,format,level);
		return directinc;
	}
	@Override
	public DirectincPage pageDirectinc(int thispage, int rowperpage) {
		DirectincPage page = new DirectincPage();
		List<Directinc> list = dao.findDirectincByPage((thispage-1)*rowperpage,rowperpage);
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
	public Directinc getDirectincInfo(String id) {
		Directinc directinc = dao.findDirectincById(id);
		return directinc;
	}
	@Override
	public void updateDirectinc(Directinc directinc) {
		dao.updateDirectinc(directinc);
	}
	@Override
	public void delDirectincById(String id) {
		dao.delDirectincById(id);
	}
	@Override
	public void addDirectinc(Directinc directinc) {
		dao.addDirectinc(directinc);
	}
}
