package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.CheckDraw;
import com.wust.factory.BasicFactory;
import com.wust.service.CheckDrawService;

public class CheckDrawNumServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String distributor = request.getParameter("distributor");
		String format = request.getParameter("format");
		String level = request.getParameter("level");
		
		int number = Integer.parseInt(request.getParameter("number"));
		CheckDrawService service = BasicFactory.getFactory().getInstance(
				CheckDrawService.class);
		CheckDraw checkDraw = service.getCheckDraw(distributor,format,level);
		int undrawnum = checkDraw.getUndrawnum();
		if (number > undrawnum) {
			response.getWriter().write("开票数量应不超过" + undrawnum);
		} else
			response.getWriter().write("通过");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
