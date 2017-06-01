package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;

import com.wust.util.DaoUtils;

public class ClearDataToTest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String[] tables={"orders","result","checkdraw","fare","payment","draworder"};
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		String sql = "truncate table ";
		try {
			for (int i = 0; i < tables.length; i++) {
				runner.update(sql+tables[i]);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		response.getWriter().print("³É¹¦Çå³ý£¡");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
