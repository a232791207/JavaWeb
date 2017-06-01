package com.wust.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wust.domain.Clerk;
import com.wust.factory.BasicFactory;
import com.wust.service.ClerkService;

public class UpdateClerkServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ClerkService service = BasicFactory.getFactory().getInstance(ClerkService.class);

		try {
			//1.将需要修改的员工信息封装成bean
			Clerk clerk = new Clerk();
			BeanUtils.populate(clerk, request.getParameterMap());
			//2.调用ClerkService中的方法修改员工信息
			service.updateClerk(clerk);
			//3.重定向回员工管理界面
			response.sendRedirect(request.getContextPath()+"/servlet/ClerkListServlet?thispage=1");
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
