package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Clerk;
import com.wust.factory.BasicFactory;
import com.wust.service.ClerkService;

public class updatePasswordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ClerkService service = BasicFactory.getFactory().getInstance(ClerkService.class);
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String type = request.getParameter("type");
		Clerk clerk = service.isClerk(id,password,type);
		if(clerk==null){
			request.setAttribute("msg", "¾ÉÃÜÂë´íÎó!");
			request.getRequestDispatcher("/updatePassword.jsp").forward(request, response);
		}else{
			service.updatePassword(id,password2);
			request.setAttribute("msg", "ÐÞ¸Ä³É¹¦!");
			request.getRequestDispatcher("/updatePassword.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
