package com.wust.web;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.factory.BasicFactory;
import com.wust.service.OrderService;

public class CheckOrderIdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String orderid = request.getParameter("orderid");
		String format = request.getParameter("format");
		String level = request.getParameter("level");
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);	
		if(service.getOrderInfo(orderid,format,level)==null){
			response.getWriter().write("该订单不存在");
		}
		else
			response.getWriter().write("通过");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
