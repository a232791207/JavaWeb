package com.wust.web;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.PricePage;
import com.wust.factory.BasicFactory;
import com.wust.service.PriceService;

public class SelectPriceServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PriceService service = BasicFactory.getFactory().getInstance(PriceService.class);
		
		String area = new String(request.getParameter("area").getBytes("iso-8859-1"),"utf-8");
		String decode_area = URLDecoder.decode(area, "UTF-8"); 
		String format = new String(request.getParameter("format").getBytes("iso-8859-1"),"utf-8");
		String level = new String(request.getParameter("level").getBytes("iso-8859-1"),"utf-8");
		String decode_level = URLDecoder.decode(level, "UTF-8"); 
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		PricePage page = service.selectPagePrice(decode_area,format,decode_level,thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/treasurer/priceList.jsp?area="+decode_area+"&format="+format+"&level="+decode_level).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PriceService service = BasicFactory.getFactory().getInstance(PriceService.class);
		String area = request.getParameter("area");
		String format = request.getParameter("format");
		String level = request.getParameter("level");
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 10;
		PricePage page = service.selectPagePrice(area,format,level,thispage,rowperpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/treasurer/priceList.jsp?area="+area+"&format="+format+"&level="+level).forward(request, response);
	}
}
