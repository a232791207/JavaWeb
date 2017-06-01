package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.BasicFare;
import com.wust.factory.BasicFactory;
import com.wust.service.BasicFareService;

public class GetBasicFareServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BasicFareService service = BasicFactory.getFactory().getInstance(BasicFareService.class);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String area = request.getParameter("area");
		BasicFare basicFare = service.findBasicfByArea(area);
		response.getWriter().write(basicFare.getFare()+"");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
