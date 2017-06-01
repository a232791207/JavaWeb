package com.wust.dao;

import java.util.List;

import com.wust.domain.Order;

public interface OrderDao {

	public void addOrder(Order order);

	public List<Order> findOrderByPageWithSalesman(int from, int count, String salesman);

	public int getCountRowWithSalesman(String salesman);

	public List<Order> findOrderByPageWithStatement(int from, int count, String statement);

	public int getCountRowWithStatement(String statement);

	public List<Order> findOrderByPageWithRevStatement(int from, int count,
			String statement);

	public int getCountRowWithRevStatement(String statement);

	public Order findOrder(String id, String format, String level);

	public void updateOrder(Order order,String format,String level);

	public void updateState(String id,String format,String level, String state);

	public int getFailOrderNum(String name);

	public int getWaitOrderNum(String name);

	public int getWaitOrderSumnum();

	public int todayNum(String time);

	public void deleteOrder(String id,String format,String level);

	public int getDeletedCountRow();

	public List<Order> findDeletedOrderByPage(int from, int count);

	public void addOrder(List<Order> orders);

	public List<Order> getOrdersById(String id);


}
