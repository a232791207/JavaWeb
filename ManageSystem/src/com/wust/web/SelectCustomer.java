package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.CustomerPage;
import com.wust.factory.BasicFactory;
import com.wust.service.CustomerService;

public class SelectCustomer extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		CustomerService service = BasicFactory.getFactory().getInstance(CustomerService.class);
		String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		CustomerPage page = service.selectCustomer(name,thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/admin/customerManage.jsp?name="+name).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		CustomerService service = BasicFactory.getFactory().getInstance(CustomerService.class);
		String name = request.getParameter("name");
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		CustomerPage page = service.selectCustomer(name,thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/admin/customerManage.jsp?name="+name).forward(request, response);
	}

}
