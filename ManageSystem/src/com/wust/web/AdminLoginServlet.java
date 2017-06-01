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
		//1.�����û������룬����service�еķ��������û�
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Admin admin = service.isAdmin(username,password);
		//2.����Ҳ�������ʾ�û����������
		if(admin==null){
			request.setAttribute("msg", "�û����������!");
			request.getRequestDispatcher("/user/admin/adminLogin.jsp").forward(request, response);
		}else{
			//3.����ҵ���˵���û�������ȷ����¼�û�����ת������Ա����
			request.getSession().setAttribute("admin", admin);
			response.sendRedirect(request.getContextPath()+"/user/admin/adminManage.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
