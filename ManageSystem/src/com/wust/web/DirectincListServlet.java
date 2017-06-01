package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.DirectincPage;
import com.wust.factory.BasicFactory;
import com.wust.service.DirectincService;

public class DirectincListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DirectincService service = BasicFactory.getFactory().getInstance(DirectincService.class);
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		DirectincPage page = service.pageDirectinc(thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/treasurer/directincList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
