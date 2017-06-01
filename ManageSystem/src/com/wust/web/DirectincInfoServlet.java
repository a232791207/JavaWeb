package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Directinc;
import com.wust.factory.BasicFactory;
import com.wust.service.DirectincService;

public class DirectincInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		DirectincService service = BasicFactory.getFactory().getInstance(DirectincService.class);
		String id = request.getParameter("id");
		Directinc directinc = service.getDirectincInfo(id);
		response.getWriter().write(directinc.getId()+","+directinc.getStime()+","+directinc.getEtime()+","+directinc.getDistributor()+","+directinc.getArea()+","+directinc.getFormat()+","+directinc.getLevel()+","+directinc.getDirectinc());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
