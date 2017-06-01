package com.wust.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.factory.BasicFactory;
import com.wust.service.PriceService;

public class BatchUpdatePriceServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//response.setCharacterEncoding("utf-8");
		//response.setContentType("text/html;charset=utf-8");
		PriceService service = BasicFactory.getFactory().getInstance(PriceService.class);
		String area = request.getParameter("area");
		String format = request.getParameter("format");
		String level = request.getParameter("level");
		String price = request.getParameter("price");
		service.batchUpdate(area,format,level,price);
		
		String encode_area = URLEncoder.encode(area, "UTF-8");	
		String encode_level = URLEncoder.encode(level, "UTF-8");
		response.sendRedirect(request.getContextPath()+"/servlet/SelectPriceServlet?thispage=1&area="+encode_area+"&format="+format+"&level="+encode_level+"&price="+price);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}