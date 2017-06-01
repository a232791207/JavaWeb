package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.BasicFare;
import com.wust.factory.BasicFactory;
import com.wust.service.BasicFareService;

public class CheckBasicFare extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		BasicFareService service = BasicFactory.getFactory().getInstance(BasicFareService.class);
		String area = request.getParameter("area");
		if(service.findBasicfByArea(area)==null){
			response.getWriter().write(area+"���޵��ȷ�");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}