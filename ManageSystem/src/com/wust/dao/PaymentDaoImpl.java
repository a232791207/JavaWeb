package com.wust.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.Payment;
import com.wust.util.DaoUtils;

public class PaymentDaoImpl implements PaymentDao {

	@Override
	public List<Payment> findPaymentByPage(int from, int count) {
		String sql = "select * from payment where status =1 limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Payment>(Payment.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow() {
		String sql = "select count(*) from payment where status =1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addPayment(Payment payment) {
		String sql = "insert into payment values (null,?,?,?,?,?,?,?,?,1)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, payment.getTime(),payment.getCustomer(),payment.getDistributor(),payment.getSalesman(),payment.getMoney(),payment.getType(),payment.getOrderid(),payment.getInterest());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "update payment set status=0 where id=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Payment getPaymentById(int id) {
		String sql = "select * from payment where id = ? and status=1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Payment>(Payment.class),id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Payment> getPaymentPageByDistributor(String distributor,
			int from, int count) {
		String sql = "select * from payment where 1 = 1 and status=1";
		List<Object> list = new ArrayList<Object>();
		if(!distributor.equals("")){
			sql = sql + " and distributor like ? ";
			list.add("%"+distributor+"%");
		}
		sql = sql + " limit ?,?";
		list.add(from);
		list.add(count);
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Payment>(Payment.class),list.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRowWithDistributor(String distributor) {
		String sql = "select count(*) from payment where 1 = ? and status=1";
		List<Object> list = new ArrayList<Object>();
		list.add("1");
		if(!distributor.equals("")){
			sql = sql + " and distributor like ? ";
			list.add("%"+distributor+"%");
		}
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler(),list.toArray())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Object[]> getPayDetailsByDist(String distributor) {
		String sql = "select customer,sum(money) from payment where distributor = ? and status = 1 group by customer";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new ArrayListHandler(),distributor);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getDeletedCountRow() {
		String sql = "select count(*) from payment where status =0";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Payment> findDeletedPaymentByPage(int from, int count) {
		String sql = "select * from payment where status =0 limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Payment>(Payment.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
