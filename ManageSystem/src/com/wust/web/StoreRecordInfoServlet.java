package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Product;
import com.wust.domain.StoreRecord;
import com.wust.factory.BasicFactory;
import com.wust.service.ProductService;
import com.wust.service.StoreRecordService;

public class StoreRecordInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		StoreRecordService service = BasicFactory.getFactory().getInstance(StoreRecordService.class);
		//1.����ClerkService�еķ�������ָ��Ա��id��Ա����Ϣ
		
		long id = Long.parseLong(request.getParameter("id"));
		StoreRecord storeRecord = service.getProductInfo(id);
		//2.��Ա����Ϣ�浽request���в�����ת����updateClerk.jspҳ��չʾ��Ϣ
		response.getWriter().write(storeRecord.getFormat()+","+storeRecord.getLevel()+
				","+storeRecord.getHeight()+","+storeRecord.getWidth()+","+
				storeRecord.getThick()+","+storeRecord.getVolume()+","+
				storeRecord.getBagSlices());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
