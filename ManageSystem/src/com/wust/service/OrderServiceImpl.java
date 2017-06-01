package com.wust.service;

import java.util.List;

import com.wust.dao.OrderDao;
import com.wust.domain.Order;
import com.wust.domain.OrderPage;
import com.wust.factory.BasicFactory;

public class OrderServiceImpl implements OrderService {
	OrderDao dao = BasicFactory.getFactory().getInstance(OrderDao.class);
	@Override
	public void addOrder(Order order) {
		dao.addOrder(order);
	}
	@Override
	public OrderPage pageOrderBySalesman(int thispage, int rowperpage, String salesman) {
		OrderPage page = new OrderPage();
		List<Order> list = dao.findOrderByPageWithSalesman((thispage-1)*rowperpage,rowperpage,salesman);
		page.setList(list);
		//当前页码
		page.setThispage(thispage);
		//每页记录数
		page.setRowperpage(rowperpage);
		//总记录数
		int countrow = dao.getCountRowWithSalesman(salesman);
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
	public OrderPage pageOrderByStatement(int thispage, int rowperpage, String statement) {
		OrderPage page = new OrderPage();
		List<Order> list = dao.findOrderByPageWithStatement((thispage-1)*rowperpage,rowperpage, statement);
		page.setList(list);
		//当前页码
		page.setThispage(thispage);
		//每页记录数
		page.setRowperpage(rowperpage);
		//总记录数
		int countrow = dao.getCountRowWithStatement(statement);
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
	public OrderPage pageOrderByRevStatement(int thispage, int rowperpage,
			String statement) {
		OrderPage page = new OrderPage();
		List<Order> list = dao.findOrderByPageWithRevStatement((thispage-1)*rowperpage,rowperpage, statement);
		page.setList(list);
		//当前页码
		page.setThispage(thispage);
		//每页记录数
		page.setRowperpage(rowperpage);
		//总记录数
		int countrow = dao.getCountRowWithRevStatement(statement);
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
	public void updateOrder(Order order,String format,String level) {
		dao.updateOrder(order,format,level);
	}
	@Override
	public void updateState(String id, String format,String level,String state) {
		dao.updateState(id,format,level,state);
		
	}
	@Override
	public int getFailOrderNum(String name) {
		
		return dao.getFailOrderNum(name);
	}
	@Override
	public int getWaitOrderNum(String name) {
		// TODO Auto-generated method stub
		return dao.getWaitOrderNum(name);
	}
	@Override
	public int getWaitOrderSumnum() {
		return dao.getWaitOrderSumnum();
	}
	@Override
	public int todayNum(String time) {
		return dao.todayNum(time);
	}
	@Override
	public void deleteOrder(String id,String format,String level) {
		dao.deleteOrder(id,format,level);
		
	}
	@Override
	public OrderPage deletedOrderList(int thispage, int rowperpage) {
		OrderPage page = new OrderPage();
		List<Order> list = dao.findDeletedOrderByPage((thispage-1)*rowperpage,rowperpage);
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
	public Order getOrderInfo(String id, String format, String level) {

		return dao.findOrder(id,format,level);
	}
	@Override
	public void addOrder(List<Order> orders) {
		dao.addOrder(orders);
		
	}
	@Override
	public List<Order> getOrdersById(String id) {
		return dao.getOrdersById(id);
	}

}
