package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.ClerkPage;
import com.wust.factory.BasicFactory;
import com.wust.service.ClerkService;

public class SelectClerk extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ClerkService service = BasicFactory.getFactory().getInstance(ClerkService.class);
		String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		String type = new String(request.getParameter("type").getBytes("iso-8859-1"),"utf-8");
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		ClerkPage page = service.selectClerk(name,type,thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/admin/clerkManage.jsp?name="+name+"&type="+type).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ClerkService service = BasicFactory.getFactory().getInstance(ClerkService.class);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		ClerkPage page = service.selectClerk(name,type,thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/admin/clerkManage.jsp?name="+name+"&type="+type).forward(request, response);
	}

}
