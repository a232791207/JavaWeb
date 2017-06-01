package com.wust.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.factory.BasicFactory;
import com.wust.service.DraworderService;
import com.wust.service.PaymentService;

public class SelectDetailsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PaymentService service1 = BasicFactory.getFactory().getInstance(PaymentService.class);
		DraworderService service2 = BasicFactory.getFactory().getInstance(DraworderService.class);
		String distributor = new String(request.getParameter("distributor").getBytes("iso-8859-1"),"utf-8");
		List<Object[]> list1 = service1.getPayDetailsByDist(distributor);
		List<Object[]> list2 = service2.getDrawDetailsByDist(distributor);
		int flag = 0;
		for(int j=0; j<list2.size(); j++){
			flag=0;
			for(int i=0; i<list1.size(); i++){
				if(list1.get(i)[0].equals(list2.get(j)[0])){
					flag = 1;
					Object [] objects = new Object[3];
					objects[0]=list1.get(i)[0];
					objects[1]=list1.get(i)[1];
					objects[2]=list2.get(j)[1];
					list1.set(i, objects);
					break;
				}
			}
			if(flag==0){
				Object [] objects = new Object[3];
				objects[0]=list2.get(j)[0];
				objects[2]=list2.get(j)[1];
				objects[1]=0;
				list1.add(objects);
			}
		}
		for(int i=0; i<list1.size(); i++){
			if(list1.get(i).length==2){
				Object [] objects = new Object[3];
				objects[0]=list1.get(i)[0];
				objects[1]=list1.get(i)[1];
				objects[2]=0;
				list1.set(i, objects);
			}
		}
		request.setAttribute("list", list1);
		request.getRequestDispatcher("/user/treasurer/details.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
