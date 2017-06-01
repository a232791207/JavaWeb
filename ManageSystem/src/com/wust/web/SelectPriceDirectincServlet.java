package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Directinc;
import com.wust.domain.Price;
import com.wust.factory.BasicFactory;
import com.wust.service.DirectincService;
import com.wust.service.PriceService;

public class SelectPriceDirectincServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PriceService service = BasicFactory.getFactory().getInstance(PriceService.class);
		DirectincService service2 = BasicFactory.getFactory().getInstance(DirectincService.class);
		String time = request.getParameter("time");
		String distributor = request.getParameter("distributor");
		String area = request.getParameter("area");
		String format = request.getParameter("format");
		String level = request.getParameter("level");
		Price price = service.findPrice(area,format,level);
		Directinc directinc = service2.findDirectinc(time,distributor,area,format,level);
		response.getWriter().write(price.getPrice()+","+(directinc==null?0:directinc.getDirectinc()));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
