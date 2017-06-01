package com.wust.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.factory.BasicFactory;
import com.wust.service.CustomerService;

public class SelectCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		CustomerService service = BasicFactory.getFactory().getInstance(CustomerService.class);	
		List<Object> list = service.findCustomerOption();
		String data="";
		for(int i = 0; i < list.size(); i++)  
        {
			if(i==list.size()-1){
				data=data+list.get(i).toString();
			}else{
				data=data+list.get(i).toString()+",";
			}
        }
		response.getWriter().write(data);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
