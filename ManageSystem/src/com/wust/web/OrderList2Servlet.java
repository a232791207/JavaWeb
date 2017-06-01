package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.OrderPage;
import com.wust.factory.BasicFactory;
import com.wust.service.OrderService;

public class OrderList2Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		OrderPage page = service.pageOrderByStatement(thispage,rowperpage,"Œ¥…Û∫À");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/treasurer/orderListForTreasurer.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
