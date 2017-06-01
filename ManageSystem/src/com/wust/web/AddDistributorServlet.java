package com.wust.web;



import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wust.domain.Distributor;
import com.wust.factory.BasicFactory;
import com.wust.service.DistributorService;

public class AddDistributorServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DistributorService service = BasicFactory.getFactory().getInstance(DistributorService.class);
		
		try {	
			//1.���ύ�ľ�������Ϣ����װ��bean
			Distributor distributor = new Distributor();
			BeanUtils.populate(distributor, request.getParameterMap());
			//2.����service�е���Ӿ����̵ķ���������װ�õľ�����bean���
			service.addDistributor(distributor);
			//3.�����ض���ؾ����̹������
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
