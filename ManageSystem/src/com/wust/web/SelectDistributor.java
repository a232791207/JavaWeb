package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.DistributorPage;
import com.wust.factory.BasicFactory;
import com.wust.service.DistributorService;

public class SelectDistributor extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		DistributorService service = BasicFactory.getFactory().getInstance(DistributorService.class);
		String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		DistributorPage page = service.selectDistributor(name,thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/admin/distributorManage.jsp?name="+name).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		DistributorService service = BasicFactory.getFactory().getInstance(DistributorService.class);
		String name = request.getParameter("name");
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		DistributorPage page = service.selectDistributor(name,thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/admin/distributorManage.jsp?name="+name).forward(request, response);
	}

}
