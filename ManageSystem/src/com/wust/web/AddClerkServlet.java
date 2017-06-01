package com.wust.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wust.domain.Clerk;
import com.wust.factory.BasicFactory;
import com.wust.service.ClerkService;

public class AddClerkServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ClerkService service = BasicFactory.getFactory().getInstance(ClerkService.class);
		
		try {	
			//1.���ύ��Ա����Ϣ����װ��bean
			Clerk clerk = new Clerk();
			BeanUtils.populate(clerk, request.getParameterMap());
			//2.����service�е����Ա���ķ���������װ�õ�Ա��bean���
			Clerk clerk1 = service.getClerkInfo(clerk.getId());
			if(clerk1!=null){
				request.setAttribute("msg", "Ա��id�Ѵ��ڣ�");
				request.getRequestDispatcher("/user/admin/addClerk.jsp").forward(request, response);
			}else{
				service.addClerk(clerk);
				//3.�����ض����Ա���������
				response.sendRedirect(request.getContextPath()+"/servlet/ClerkListServlet?thispage=1");
			}
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
