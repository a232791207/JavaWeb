package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.OrderPage;
import com.wust.factory.BasicFactory;
import com.wust.service.OrderService;

public class OrderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		if(request.getAttribute("error")!=null){
			request.setAttribute("error", request.getAttribute("error"));
		}
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		String name = request.getParameter("salesman");
		String salesman = new String(name.getBytes("ISO-8859-1"),"utf-8");
		int rowperpage = 10;
		OrderPage page = service.pageOrderBySalesman(thispage,rowperpage,salesman);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/salesman/orderListForSalesman.jsp").forward(request, response);	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
