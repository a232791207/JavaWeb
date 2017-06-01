package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Customer;
import com.wust.factory.BasicFactory;
import com.wust.service.CustomerService;

public class CustomerInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CustomerService service = BasicFactory.getFactory().getInstance(CustomerService.class);
		String id = request.getParameter("id");
		Customer customer = service.getCustomerInfo(id);
		request.setAttribute("customer", customer);
		request.getRequestDispatcher("/user/admin/updateCustomer.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
