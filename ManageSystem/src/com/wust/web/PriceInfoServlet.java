package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Order;
import com.wust.domain.Price;
import com.wust.factory.BasicFactory;
import com.wust.service.OrderService;
import com.wust.service.PriceService;

public class PriceInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PriceService service = BasicFactory.getFactory().getInstance(PriceService.class);
		String id = request.getParameter("id");
		Price price = service.getPriceInfo(id);
		response.getWriter().write(price.getId()+","+price.getTime()+","+price.getArea()+","+price.getFormat()+","+price.getLevel()+","+price.getPrice()+","+price.getChangelim());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
