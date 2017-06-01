package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.factory.BasicFactory;
import com.wust.service.ClerkService;

public class DeleteClerkServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ClerkService service = BasicFactory.getFactory().getInstance(ClerkService.class);
		//1.根据传进来的员工id，调用ClerkService中的删除员工的方法
		String id = request.getParameter("id");
		service.deleteClerk(id);
		//2.重定向回员工管理界面
		response.sendRedirect(request.getContextPath()+"/servlet/ClerkListServlet?thispage=1");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
