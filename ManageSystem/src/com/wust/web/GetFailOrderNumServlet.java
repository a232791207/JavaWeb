package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Clerk;
import com.wust.factory.BasicFactory;
import com.wust.service.OrderService;

public class GetFailOrderNumServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		if(request.getSession().getAttribute("clerk")!=null){
			Clerk clerk = (Clerk)request.getSession().getAttribute("clerk");
			int num1 = service.getFailOrderNum(clerk.getId());
			int num2 = service.getWaitOrderNum(clerk.getId());
			response.getWriter().write(num1+","+num2);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
