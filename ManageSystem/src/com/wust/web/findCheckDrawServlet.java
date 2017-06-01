package com.wust.web;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.CheckDrawPage;
import com.wust.domain.CheckDraw;
import com.wust.factory.BasicFactory;
import com.wust.service.CheckDrawService;

public class findCheckDrawServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CheckDrawService service = BasicFactory.getFactory().getInstance(CheckDrawService.class);
		String distributor = new String(request.getParameter("distributor").getBytes("iso-8859-1"),"utf-8");
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		CheckDrawPage page = service.getCheckDrawByDistributorPage(distributor, thispage, rowperpage);
		request.setAttribute("page", page);
		request.setAttribute("distributor", distributor);
		request.getRequestDispatcher("/user/drawer/checkdrawList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CheckDrawService service = BasicFactory.getFactory().getInstance(CheckDrawService.class);
		String distributor = request.getParameter("distributor");
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		CheckDrawPage page = service.getCheckDrawByDistributorPage(distributor, thispage, rowperpage);
		request.setAttribute("page", page);
		request.setAttribute("distributor", distributor);
		request.getRequestDispatcher("/user/drawer/checkdrawList.jsp").forward(request, response);
	}

}
