package com.wust.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.Customer;
import com.wust.util.DaoUtils;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public List<Customer> findCustomerByPage(int from, int count) {
		String sql = "select * from customer limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Customer>(Customer.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow() {
		String sql ="select count(*) from customer";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addCustomer(Customer customer) {
		String sql = "insert into customer values(null,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, customer.getName(), customer.getPhonenumber(), customer.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteCustomer(String id) {
		String sql = "delete from customer where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Customer findCustomerById(String id) {
		String sql = "select * from customer where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Customer>(Customer.class), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateCustomer(Customer customer) {
		String sql = "update customer set phonenumber=?,email=? where id=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, customer.getPhonenumber(), customer.getEmail(),customer.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Customer> selectCustomerPage(String name, int from, int count) {
		String sql = "select * from customer where name like ? limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Customer>(Customer.class),"%"+name+"%",from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountSelectCustomer(String name) {
		String sql ="select count(*) from customer where name like ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler(),"%"+name+"%")).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Object> findCustomerOption() {
		String sql = "select distinct name from customer";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new ColumnListHandler("name"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
