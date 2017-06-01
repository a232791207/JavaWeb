package com.wust.dao;

import java.util.List;

import com.wust.domain.Customer;

public interface CustomerDao {

	public List<Customer> findCustomerByPage(int from, int count);

	public int getCountRow();

	public void addCustomer(Customer customer);

	public void deleteCustomer(String id);

	public Customer findCustomerById(String id);

	public void updateCustomer(Customer customer);

	public List<Customer> selectCustomerPage(String name, int from, int count);

	public int getCountSelectCustomer(String name);

	public List<Object> findCustomerOption();

}
