package com.wust.web;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.factory.BasicFactory;
import com.wust.service.CheckDrawService;
import com.wust.service.DistributorService;
import com.wust.service.PriceService;
import com.wust.service.ProductService;

public class CheckDrawFormatServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String distributor = request.getParameter("distributor");
		String format = request.getParameter("format");
		CheckDrawService checkDrawService = BasicFactory.getFactory().getInstance(CheckDrawService.class);
		if(checkDrawService.findLevelOption(distributor,format)!=null){
			List<Object> list = checkDrawService.findLevelOption(distributor,format);
			String data="";
			for(int i = 0; i < list.size(); i++)  
	        {
				if(i==list.size()-1){
					data=data+list.get(i).toString();
				}else{
					data=data+list.get(i).toString()+",";
				}
	        }
			response.getWriter().write(data);
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
