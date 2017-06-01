package com.wust.service;

import java.util.List;



import com.wust.dao.DraworderDao;
import com.wust.domain.Draworder;
import com.wust.domain.DraworderPage;
import com.wust.factory.BasicFactory;

public class DraworderServiceImpl implements DraworderService {
	DraworderDao dao =BasicFactory.getFactory().getInstance(DraworderDao.class);

	@Override
	public DraworderPage pageDraworder(int thispage, int rowperpage) {
		DraworderPage page = new DraworderPage();
		List<Draworder> list = dao.findDraworderByPage((thispage-1)*rowperpage,rowperpage);
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
	public void addDraworder(Draworder draworder) {
		dao.addDraworder(draworder);
		
	}

	@Override
	public void delete(String id,String format,String level,double price) {
		dao.delete(id, format, level,price);
		
	}

	@Override
	public Draworder findDraworder(String id, String format, String level,double price) {
		return dao.findDraworder(id,format,level,price);
	}

	@Override
	public List<Object[]> getDrawDetailsByDist(String distributor) {
		return dao.getDrawDetailsByDist(distributor);
	}

	@Override
	public DraworderPage deletedDraworderList(int thispage, int rowperpage) {
		DraworderPage page = new DraworderPage();
		List<Draworder> list = dao.findDeletedDraworderByPage((thispage-1)*rowperpage,rowperpage);
		//��ǰҳ����
		page.setList(list);
		//��ǰҳ��
		page.setThispage(thispage);
		//ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//�ܼ�¼��
		int countrow = dao.getDeletedCountRow();
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
	public DraworderPage findDistributorDraworderBypage(String distributor,
			int thispage, int rowperpage) {
		DraworderPage page = new DraworderPage();
		List<Draworder> list = dao.findDistributorDraworderByPage(distributor,(thispage-1)*rowperpage,rowperpage);
		//��ǰҳ����
		page.setList(list);
		//��ǰҳ��
		page.setThispage(thispage);
		//ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//�ܼ�¼��
		int countrow = dao.getDistributorCountRow(distributor);
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
	public void addDraworder(List<Draworder> draworders) {
		dao.addDraworder(draworders);
		
	}

	




}
