package com.wust.web;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wust.factory.BasicFactory;
import com.wust.service.CheckDrawService;

public class CheckDrawHasOrderedServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String distributor = request.getParameter("distributor");
		String format = request.getParameter("format");
		String level = request.getParameter("level");
		CheckDrawService service = BasicFactory.getFactory().getInstance(
				CheckDrawService.class);
		if(service.getCheckDraw(distributor,format,level)==null){
			response.getWriter().write("请先去下单!");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
