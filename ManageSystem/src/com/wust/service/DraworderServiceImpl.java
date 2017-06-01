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
		//当前页数据
		page.setList(list);
		//当前页码
		page.setThispage(thispage);
		//每页记录数
		page.setRowperpage(rowperpage);
		//总记录数
		int countrow = dao.getCountRow();
		page.setCountrow(countrow);
		//总页数
		int countpage = countrow/rowperpage + (countrow%rowperpage==0?0:1);
		page.setCountpage(countpage);
		//首页
		page.setFirstpage(1);
		//尾页
		page.setLastpage(countpage);
		//上一页
		page.setPrepage(thispage==1?1:thispage-1);
		//下一页
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
		//当前页数据
		page.setList(list);
		//当前页码
		page.setThispage(thispage);
		//每页记录数
		page.setRowperpage(rowperpage);
		//总记录数
		int countrow = dao.getDeletedCountRow();
		page.setCountrow(countrow);
		//总页数
		int countpage = countrow/rowperpage + (countrow%rowperpage==0?0:1);
		page.setCountpage(countpage);
		//首页
		page.setFirstpage(1);
		//尾页
		page.setLastpage(countpage);
		//上一页
		page.setPrepage(thispage==1?1:thispage-1);
		//下一页
		page.setNextpage(thispage==countpage?thispage:thispage+1);
		return page;
	}

	@Override
	public DraworderPage findDistributorDraworderBypage(String distributor,
			int thispage, int rowperpage) {
		DraworderPage page = new DraworderPage();
		List<Draworder> list = dao.findDistributorDraworderByPage(distributor,(thispage-1)*rowperpage,rowperpage);
		//当前页数据
		page.setList(list);
		//当前页码
		page.setThispage(thispage);
		//每页记录数
		page.setRowperpage(rowperpage);
		//总记录数
		int countrow = dao.getDistributorCountRow(distributor);
		page.setCountrow(countrow);
		//总页数
		int countpage = countrow/rowperpage + (countrow%rowperpage==0?0:1);
		page.setCountpage(countpage);
		//首页
		page.setFirstpage(1);
		//尾页
		page.setLastpage(countpage);
		//上一页
		page.setPrepage(thispage==1?1:thispage-1);
		//下一页
		page.setNextpage(thispage==countpage?thispage:thispage+1);
		return page;
	}

	@Override
	public void addDraworder(List<Draworder> draworders) {
		dao.addDraworder(draworders);
		
	}

	




}
