package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.domain.Distributor;
import com.wust.domain.Price;
import com.wust.factory.BasicFactory;
import com.wust.service.DistributorService;
import com.wust.service.OrderService;
import com.wust.service.PriceService;

public class CheckDrawPriceServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		double check_price = Double.parseDouble(request.getParameter("price"));
		String str_distributor = request.getParameter("distributor");
		String format = request.getParameter("format");
		String level = request.getParameter("level");
		PriceService service = BasicFactory.getFactory().getInstance(PriceService.class);
		DistributorService distributorService = BasicFactory.getFactory().getInstance(DistributorService.class);
		Distributor distributor = distributorService.findDistributorByName(str_distributor);
		Price price = service.findPrice(distributor.getArea(), format, level);
		
		if(price!=null){
			double min = price.getPrice() - Double.parseDouble(price.getChangelim());
			double max = price.getPrice() + Double.parseDouble(price.getChangelim());
			if (check_price <= max && check_price >= min) {
				response.getWriter().write("ͨ��");
			} else {
				String str_min=min+"00";
				String str_max=max+"00";
				//��ȡ��С�������λ
				str_min = str_min.substring(0, str_min.indexOf(".")+3);
				str_max = str_max.substring(0, str_max.indexOf(".")+3);
				response.getWriter().write("�۸�Ӧ��" + str_min + "~" + str_max + "��Χ֮��!");
			}
		}
		else{
			response.getWriter().write("���������ڵ�����"+distributor.getArea()+"��û�м۸�!");
		}
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
