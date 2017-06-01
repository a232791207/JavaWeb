package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


import com.wust.domain.Product;
import com.wust.factory.BasicFactory;
import com.wust.service.ProductService;


public class UpdateProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);

		try {
			//1.����Ҫ�޸ĵ�Ա����Ϣ��װ��bean
			Product product = new Product();
			BeanUtils.populate(product, request.getParameterMap());
			//2.����ClerkService�еķ����޸�Ա����Ϣ
			service.updateProduct(product);
			//3.�ض����Ա���������
			response.sendRedirect(request.getContextPath()+"/servlet/ProductListServlet?thispage=1");
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
