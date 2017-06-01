package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Product;
import com.wust.factory.BasicFactory;
import com.wust.service.ProductService;

public class ProductNumServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);		
		String format = request.getParameter("format");
		String level = request.getParameter("level");
		
		Product product = service.findNum(format,level);
		if(product!=null){
			response.getWriter().write(product.getNum()+"");
		}else{
			response.getWriter().write("0");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
