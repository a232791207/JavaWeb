package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wust.domain.Clerk;
import com.wust.domain.Distributor;
import com.wust.factory.BasicFactory;
import com.wust.service.ClerkService;
import com.wust.service.DistributorService;

public class UpdateDistributorServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		DistributorService service = BasicFactory.getFactory().getInstance(DistributorService.class);
		try {
			//1.将需要修改的经销商信息封装成bean
			Distributor distributor = new Distributor();
			BeanUtils.populate(distributor, request.getParameterMap());
			//2.调用DistributorService中的方法修改经销商信息
			service.updateDistributor(distributor);
			//3.重定向回经销商管理界面
			response.sendRedirect(request.getContextPath()+"/servlet/DistributorListServlet?thispage=1");
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
