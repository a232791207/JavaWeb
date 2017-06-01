package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wust.domain.Price;
import com.wust.factory.BasicFactory;
import com.wust.service.PriceService;

public class AddPriceServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PriceService service = BasicFactory.getFactory().getInstance(PriceService.class);
			Price price = new Price();
			BeanUtils.populate(price, request.getParameterMap());
			service.addPrice(price);
			response.sendRedirect(request.getContextPath()+"/servlet/PriceListServlet?thispage=1");
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
