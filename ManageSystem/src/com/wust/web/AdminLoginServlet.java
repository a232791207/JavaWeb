package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Admin;
import com.wust.factory.BasicFactory;
import com.wust.service.AdminService;


public class AdminLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		AdminService service = BasicFactory.getFactory().getInstance(AdminService.class);
		//1.根据用户名密码，调用service中的方法查找用户
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Admin admin = service.isAdmin(username,password);
		//2.如果找不到，提示用户名密码错误
		if(admin==null){
			request.setAttribute("msg", "用户名密码错误!");
			request.getRequestDispatcher("/user/admin/adminLogin.jsp").forward(request, response);
		}else{
			//3.如果找到，说明用户密码正确，登录用户并跳转到管理员界面
			request.getSession().setAttribute("admin", admin);
			response.sendRedirect(request.getContextPath()+"/user/admin/adminManage.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
