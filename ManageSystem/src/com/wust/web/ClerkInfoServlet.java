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
		//1.����ClerkService�еķ�������ָ��Ա��id��Ա����Ϣ
		String id = request.getParameter("id");
		Clerk clerk = service.getClerkInfo(id);
		//2.��Ա����Ϣ�浽request���в�����ת����updateClerk.jspҳ��չʾ��Ϣ
		request.setAttribute("clerk", clerk);
		request.getRequestDispatcher("/user/admin/updateClerk.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
