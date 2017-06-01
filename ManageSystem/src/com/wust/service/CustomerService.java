package com.wust.service;

import java.util.List;

import com.wust.domain.Customer;
import com.wust.domain.CustomerPage;

public interface CustomerService {

	public CustomerPage pageCustomer(int thispage, int rowperpage);

	public void addCustomer(Customer customer);

	public void deleteCustomer(String id);

	public Customer getCustomerInfo(String id);

	public void updateCustomer(Customer customer);

	public CustomerPage selectCustomer(String name, int thispage, int rowperpage);

	public List<Object> findCustomerOption();

}
