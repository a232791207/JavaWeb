package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.ResultPage;
import com.wust.factory.BasicFactory;
import com.wust.service.ResultService;

public class findDraworderInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ResultService service = BasicFactory.getFactory().getInstance(ResultService.class);
		String distributor = new String(request.getParameter("distributor").getBytes("iso-8859-1"),"utf-8");
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		ResultPage page = service.getResultPageByDistributor(distributor, thispage, rowperpage);
		request.setAttribute("page", page);
		request.setAttribute("distributor", distributor);
		request.getRequestDispatcher("/user/drawer/draworderInfoList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ResultService service = BasicFactory.getFactory().getInstance(ResultService.class);
		String distributor = request.getParameter("distributor");
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		ResultPage page = service.getResultPageByDistributor(distributor, thispage, rowperpage);
		request.setAttribute("page", page);
		request.setAttribute("distributor", distributor);
		request.getRequestDispatcher("/user/drawer/draworderInfoList.jsp").forward(request, response);
	}

}
