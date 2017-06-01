package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.ClerkPage;
import com.wust.factory.BasicFactory;
import com.wust.service.AdminService;
import com.wust.service.ClerkService;

public class ClerkListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ClerkService service = BasicFactory.getFactory().getInstance(ClerkService.class);
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		//1.调用ClerkService中的方法，查找当前所需页的所有信息
		ClerkPage page = service.pageClerk(thispage,rowperpage);
		//2.将page信息存入request域中，并带回到clerkManage.jsp中
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/admin/clerkManage.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
