package com.wust.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import com.wust.dao.AdminDaoImpl;
import com.wust.dao.ClerkDaoImpl;
import com.wust.dao.DistributorDaoImpl;
import com.wust.dao.PriceDaoImpl;
import com.wust.dao.ProductDaoImpl;
import com.wust.domain.Admin;
import com.wust.domain.Clerk;
import com.wust.domain.Distributor;
import com.wust.domain.Price;
import com.wust.domain.Product;
import com.wust.factory.BasicFactory;
import com.wust.service.CheckDrawService;
import com.wust.util.DaoUtils;

public class QueryRunnerTest {
	@Test
	public void testFindAdminByUSAndPAS(){
		Admin admin = null;
		String username = "admin";
		String password = "admin";
		AdminDaoImpl dao = new AdminDaoImpl();
		admin = dao.findAdminByUSAndPAS(username, password);
		System.out.println(admin.getUsername());
	}
	
	@Test
	public void testGetCountRow(){
		Double str=1.234567;
		System.out.println(Double.parseDouble(String.format("%.3f", str)));
		System.out.println();
	}
	
	@Test
	public void testFindClerkByPage(){
		int thispage=1;
		int rowperpage=5;
		ClerkDaoImpl dao = new ClerkDaoImpl();
		List<Clerk> list = dao.findClerkByPage(thispage, rowperpage);
		System.out.println(list);
	}
	
	@Test
	public void findDistributorByPage(){
		int thispage=1;
		int rowperpage=5;
		DistributorDaoImpl dao = new DistributorDaoImpl();
		List<Distributor> list = dao.findDistributorByPage(thispage, rowperpage);
		System.out.println(list);
	}
	
	@Test
	public void testFindClerkByIdPasTyp(){
		String id = "131";
		String password = "131";
		String type = "业务员";
		ClerkDaoImpl dao = new ClerkDaoImpl();
		Clerk clerk = dao.findClerkByIdPasTyp(id, password, type);
		System.out.println(clerk.getId());
	}
	
	@Test
	public void testFindAreaOption(){
		PriceDaoImpl dao = new PriceDaoImpl();
		List<Object> list = dao.findAreaOption();
		System.out.println(list);
	}
	@Test
	public void testFindPrice(){
		String area = "湖北";
		String format = "长";
		String level = "1";
		String sql = "select * from price where area=? and format=? and level=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			Price price = runner.query(sql, new BeanHandler<Price>(Price.class), area,format,level);
			System.out.println(price.getPrice());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Test
	public void testFindNum(){
		String sql = "select customer,sum(money) from payment where distributor = '郑金骅' and status = 1 group by customer";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			List<Object[]> list = runner.query(sql, new ArrayListHandler());
			System.out.println(list.get(0)[0]+""+list.get(0)[1]);
			System.out.println(list.get(1)[0]+""+list.get(1)[1]);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
