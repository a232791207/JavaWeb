package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Clerk;
import com.wust.factory.BasicFactory;
import com.wust.service.ClerkService;

public class ClerkInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ClerkService service = BasicFactory.getFactory().getInstance(ClerkService.class);
		//1.调用ClerkService中的方法查找指定员工id的员工信息
		String id = request.getParameter("id");
		Clerk clerk = service.getClerkInfo(id);
		//2.将员工信息存到request域中并请求转发到updateClerk.jsp页面展示信息
		request.setAttribute("clerk", clerk);
		request.getRequestDispatcher("/user/admin/updateClerk.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
