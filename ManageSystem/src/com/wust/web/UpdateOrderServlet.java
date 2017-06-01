package com.wust.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wust.domain.Clerk;
import com.wust.domain.Order;
import com.wust.factory.BasicFactory;
import com.wust.service.OrderService;

public class UpdateOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String format = request.getParameter("oldformat");
			String level = request.getParameter("oldlevel");
			OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
			Order order = new Order();
			BeanUtils.populate(order, request.getParameterMap());
			order.setBags(Integer.parseInt(request.getParameter("package")));
			service.updateOrder(order,format,level);
			response.getWriter().write("ÐÞ¸Ä³É¹¦£¡");
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
