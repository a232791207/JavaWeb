package com.wust.service;

import java.util.List;

import com.wust.dao.CustomerDao;
import com.wust.domain.Customer;
import com.wust.domain.CustomerPage;
import com.wust.factory.BasicFactory;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao dao = BasicFactory.getFactory().getInstance(CustomerDao.class);
	@Override
	public CustomerPage pageCustomer(int thispage, int rowperpage) {
		CustomerPage page = new CustomerPage();
		List<Customer> list = dao.findCustomerByPage((thispage-1)*rowperpage,rowperpage);
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
	public void addCustomer(Customer customer) {
		dao.addCustomer(customer);
	}
	@Override
	public void deleteCustomer(String id) {
		dao.deleteCustomer(id);
	}
	@Override
	public Customer getCustomerInfo(String id) {
		Customer customer = dao.findCustomerById(id);
		return customer;
	}
	@Override
	public void updateCustomer(Customer customer) {
		dao.updateCustomer(customer);
	}
	@Override
	public CustomerPage selectCustomer(String name, int thispage, int rowperpage) {
		CustomerPage page = new CustomerPage();
		List<Customer> list = dao.selectCustomerPage(name,(thispage-1)*rowperpage,rowperpage);
		//当前页数据
		page.setList(list);
		//当前页码
		page.setThispage(thispage);
		//每页记录数
		page.setRowperpage(rowperpage);
		//总记录数
		int countrow = dao.getCountSelectCustomer(name);
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
	public List<Object> findCustomerOption() {
		List<Object> list = dao.findCustomerOption();
		return list;
	}

}
