package com.wust.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.wust.factory.BasicFactory;
import com.wust.service.PriceService;
import com.wust.util.DaoUtils;

public class SelectLevel2Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String sql = "select distinct level from level";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			List<Object> list =runner.query(sql, new ColumnListHandler("level"));
			String data="";
			if(list!=null){
				for(int i = 0; i < list.size(); i++)  
		        {
					if(i==list.size()-1){
						data=data+list.get(i).toString();
					}else{
						data=data+list.get(i).toString()+",";
					}
		        }
			}
			response.getWriter().write(data);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
