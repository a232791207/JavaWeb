package com.wust.service;

import java.util.List;

import com.wust.dao.ResultDao;
import com.wust.domain.Draworder;
import com.wust.domain.Order;
import com.wust.domain.Payment;
import com.wust.domain.Result;
import com.wust.domain.ResultPage;
import com.wust.factory.BasicFactory;

public class ResultServiceImpl implements ResultService {
	ResultDao dao = BasicFactory.getFactory().getInstance(ResultDao.class);
	@Override
	public ResultPage getResultByPage(int thispage, int rowperpage) {
		ResultPage page = new ResultPage();
		List<Result> list = dao.findResultByPage((thispage-1)*rowperpage,rowperpage);
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
	public Result getResultByDisAndSal(String distributor, String salesman) {
		Result result = dao.getResultByDisAndSal(distributor,salesman);
		return result;
	}
	@Override
	public void addResult(Order order) {
		dao.addResult(order);
	}
	@Override
	public void updateResult(Order order) {
		dao.updateResult(order);
	}
	@Override
	public void addInterest(String distributor, String salesman, String money) {
		dao.addInterest(distributor,salesman,money);
		
	}
	@Override
	public void addPayback(String distributor, String salesman, String money) {
		dao.addPayback(distributor,salesman,money);
		
	}
	@Override
	public void payUpdateResult(Payment payment) {
		dao.payUpdateResult(payment);
		
	}
	@Override
	public List<Result> findDistributorsByName(String name) {
		List<Result> list = dao.findDistributorsByName(name);
		return list;
	}
	@Override
	public void deleteResult(int id) {
		dao.delete(id);
		
	}
	@Override
	public void updateResultByNum(String distributor, String salesman,
			double sumprice) {
		dao.updateResultBynum(distributor, salesman,sumprice);
		
	}
	@Override
	public ResultPage getResultPageByDistributor(String distributor,
			int thispage, int rowperpage) {
		ResultPage page = new ResultPage();
		List<Result> list = dao.getResultPageByDistributor(distributor, (thispage-1)*rowperpage, rowperpage);
		//当前页数据
		page.setList(list);
		//当前页码
		page.setThispage(thispage);
		//每页记录数
		page.setRowperpage(rowperpage);
		//总记录数
		int countrow = dao.getCountRowWithDistributor(distributor);
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
	public void updateResultByNum(List<Draworder> draworders) {
		dao.updateResultByNum(draworders);
		
	}

}
