package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.factory.BasicFactory;
import com.wust.service.DirectincService;

public class DeleteDirectincServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DirectincService service = BasicFactory.getFactory().getInstance(DirectincService.class);
		String id = request.getParameter("id");
		service.delDirectincById(id);
		response.sendRedirect(request.getContextPath()+"/servlet/DirectincListServlet?thispage=1");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
