package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Order;
import com.wust.factory.BasicFactory;
import com.wust.service.OrderService;

public class OrderInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		String id = request.getParameter("id");
		String format = request.getParameter("format");
		String level = request.getParameter("level");
		Order order = service.getOrderInfo(id,format,level);
		response.getWriter().write(order.getOrderid()+","+order.getTime()+","+order.getSalesman()+","+
				order.getDistributor()+","+order.getArea()+","+order.getFormat()+","+order.getLevel()+","+order.getPrice()+","+
				order.getDirectinc()+","+order.getFreight()+","+order.getSpecialinc()+","+order.getRealprice()+","+order.getNumber()+","+
				order.getSumprice()+","+order.getIfprofit());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
