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
			//获取下单时间
			String s = request.getParameter("time");
			//将下单时间转换为Date数据
			Date date = sdf2.parse(s);
			//生成订单号头部
			String id_head = sdf.format(date);
			//生成订单号尾部
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
				response.getWriter().write("新增成功！三秒后返回...");
				response.setHeader("refresh","3;url="+request.getContextPath()+"/user/salesman/addOrder.jsp");
			}
			else{
				response.getWriter().print("此单已经买过该产品了,请修改相应信息！！");
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
