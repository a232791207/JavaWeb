package com.wust.web;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Product;
import com.wust.factory.BasicFactory;
import com.wust.service.ProductService;

public class ProductInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		//1.����ClerkService�еķ�������ָ��Ա��id��Ա����Ϣ
		int id = Integer.parseInt(request.getParameter("id"));
		Product product = service.getProductInfo(id);
		//2.��Ա����Ϣ�浽request���в�����ת����updateClerk.jspҳ��չʾ��Ϣ
		response.getWriter().write(product.getId()+","+product.getFormat()+","+product.getLevel()+","+product.getThick()+","+product.getNum()+","+product.getVolume()+","+product.getBagSlices());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
