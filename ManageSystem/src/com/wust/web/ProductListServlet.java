package com.wust.web;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.ProductPage;
import com.wust.factory.BasicFactory;
import com.wust.service.ProductService;

public class ProductListServlet extends HttpServlet {



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		//1.����ClerkService�еķ��������ҵ�ǰ����ҳ��������Ϣ
		ProductPage page = service.formatAndLevelPageProduct("", "", thispage, rowperpage);
		//2.��page��Ϣ����request���У������ص�clerkManage.jsp��
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/storeManager/productManage.jsp").forward(request, response);
	}

	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
