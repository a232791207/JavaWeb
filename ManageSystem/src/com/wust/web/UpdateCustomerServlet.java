package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wust.domain.Customer;
import com.wust.factory.BasicFactory;
import com.wust.service.CustomerService;

public class UpdateCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		CustomerService service = BasicFactory.getFactory().getInstance(CustomerService.class);
		try {
			Customer customer = new Customer();
			BeanUtils.populate(customer, request.getParameterMap());
			service.updateCustomer(customer);
			response.sendRedirect(request.getContextPath()+"/servlet/CustomerListServlet?thispage=1");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
