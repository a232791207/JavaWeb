package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.CustomerPage;
import com.wust.factory.BasicFactory;
import com.wust.service.CustomerService;

public class CustomerListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CustomerService service = BasicFactory.getFactory().getInstance(CustomerService.class);
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		CustomerPage page = service.pageCustomer(thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/admin/customerManage.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
