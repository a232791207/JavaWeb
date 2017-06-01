package com.wust.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Order;
import com.wust.factory.BasicFactory;
import com.wust.service.OrderService;

public class GetOrderInfById extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
		String id = request.getParameter("id");
		List<Order> list = service.getOrdersById(id);
		String detial = "规格\t\t等级\t\t件数/片数\n";
		for(int i=0; i<list.size(); i++){
			detial=detial+list.get(i).getFormat()+"\t"+list.get(i).getLevel()+"\t\t"+list.get(i).getBags()+"/"+list.get(i).getNumber()+"\n";
		}
		response.getWriter().write(list.get(0).getTime()+","+detial);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
