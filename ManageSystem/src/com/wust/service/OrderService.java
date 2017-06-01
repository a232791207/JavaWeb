package com.wust.service;

import java.util.List;

import com.wust.domain.Order;
import com.wust.domain.OrderPage;

public interface OrderService {

	public void addOrder(Order order);
	public void addOrder(List<Order>orders);

	public OrderPage pageOrderBySalesman(int thispage, int rowperpage, String salesman);

	public OrderPage pageOrderByStatement(int thispage, int rowperpage, String statement);


	public OrderPage pageOrderByRevStatement(int thispage, int rowperpage, String statement);

	public Order getOrderInfo(String id, String format, String level);

	public void updateOrder(Order order,String format,String level);

	public void updateState(String id, String format,String level,String state);

	public int getFailOrderNum(String name);

	public int getWaitOrderNum(String name);

	public int getWaitOrderSumnum();

	public int todayNum(String time);

	public void deleteOrder(String id,String format,String level);

	public OrderPage deletedOrderList(int thispage, int rowperpage);
	public List<Order> getOrdersById(String id);


}
