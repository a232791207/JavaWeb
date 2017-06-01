package com.wust.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wust.domain.Order;
import com.wust.factory.BasicFactory;
import com.wust.service.OrderService;

public class AddOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			//��ȡ�µ�ʱ��
			String s = request.getParameter("time");
			//���µ�ʱ��ת��ΪDate����
			Date date = sdf2.parse(s);
			//���ɶ�����ͷ��
			String id_head = sdf.format(date);
			//���ɶ�����β��
			String id_end;
			String time = sdf2.format(date);
			int count = service.todayNum(time)+1;
			if(count<=9){
				id_end = "0" + ((Integer)count).toString();
			}else{
				id_end = ((Integer)count).toString();
			}*/
			
			Order order = new Order();
			BeanUtils.populate(order, request.getParameterMap());
			//order.setOrderid(id_head+id_end);
			order.setBags(Integer.parseInt(request.getParameter("package")));
			String fare1 = request.getParameter("fare");
			String fare = order.getFare();
			Order order_d = service.getOrderInfo(order.getOrderid(), order.getFormat(), order.getLevel());
			if(order_d==null){
				service.addOrder(order);
				response.getWriter().write("�����ɹ�������󷵻�...");
				response.setHeader("refresh","3;url="+request.getContextPath()+"/user/salesman/addOrder.jsp");
			}
			else{
				response.getWriter().print("�˵��Ѿ�����ò�Ʒ��,���޸���Ӧ��Ϣ����");
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
