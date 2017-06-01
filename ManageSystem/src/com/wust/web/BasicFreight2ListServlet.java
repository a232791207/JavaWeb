package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.BasicFarePage;
import com.wust.factory.BasicFactory;
import com.wust.service.BasicFareService;

public class BasicFreight2ListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BasicFareService service = BasicFactory.getFactory().getInstance(BasicFareService.class);
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		BasicFarePage page = service.getBasicFreightByPage(thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/admin/basicFreightList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
