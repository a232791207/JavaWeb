package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.wust.domain.Package;
import com.wust.domain.Product;
import com.wust.factory.BasicFactory;
import com.wust.service.PackageService;
import com.wust.service.ProductService;

public class SelectPackageNumServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		String format = request.getParameter("format");
		String level = request.getParameter("level");
		Product product = service.getProductInfo(format, level);
		response.getWriter().write(product.getBagSlices()+","+product.getVolume()/product.getNum());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
