package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.StoreRecordPage;
import com.wust.factory.BasicFactory;
import com.wust.service.StoreRecordService;

public class FindStoreRecordServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		StoreRecordService service = BasicFactory.getFactory().getInstance(StoreRecordService.class);
		int thispage=1;
		if(request.getParameter("thispage")!=null){
			thispage= Integer.parseInt(request.getParameter("thispage"));
		}
		int rowperpage = 10;
		String level = new String(request.getParameter("level").getBytes("iso-8859-1"),"utf-8");		
		String format = request.getParameter("format");
		StoreRecordPage page=null;
		page=service.formatAndLevelPageStoreRecord(format, level, thispage, rowperpage);
		request.setAttribute("format", format);
		request.setAttribute("level", level);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/storeManager/storerecordList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		StoreRecordService service = BasicFactory.getFactory().getInstance(StoreRecordService.class);
		int thispage=1;
		if(request.getParameter("thispage")!=null){
			thispage= Integer.parseInt(request.getParameter("thispage"));
		}
		int rowperpage = 10;
		String level = request.getParameter("level");		
		String format = request.getParameter("format");
		StoreRecordPage page=null;
		page = service.formatAndLevelPageStoreRecord(format, level, thispage, rowperpage);
		request.setAttribute("format", format);
		request.setAttribute("level", level);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/user/storeManager/storerecordList.jsp").forward(request, response);
	}
}
