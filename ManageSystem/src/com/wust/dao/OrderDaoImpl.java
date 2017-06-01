package com.wust.dao;


import java.sql.SQLException;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.Order;
import com.wust.util.DaoUtils;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void addOrder(Order order) {
		String sql = "insert into orders values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, order.getOrderid(),order.getTime(),order.getSalesman(),order.getDistributor(),order.getArea(),order.getFormat(),order.getLevel(),order.getPrice(),order.getDirectinc(),order.getFreight(),order.getSpecialinc(),order.getRealprice(),order.getNumber(),order.getSumprice(),order.getIfprofit(),order.getBags(),order.getVolume(),order.getFare(),order.getFreight2(),"Œ¥…Û∫À");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Order> findOrderByPageWithSalesman(int from, int count, String salesman) {
		String sql = "select * from orders where salesman =? and status = 1 limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Order>(Order.class),salesman, from, count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRowWithSalesman(String salesman) {
		String sql ="select count(*) from orders where salesman=? and status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler(),salesman)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Order> findOrderByPageWithStatement(int from, int count, String statement) {
		String sql = "select * from orders where statement = ? and status = 1 limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Order>(Order.class),statement, from, count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRowWithStatement(String statement) {
		String sql = "select count(*) from orders where statement= ? and status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler(),statement)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Order> findOrderByPageWithRevStatement(int from, int count,
			String statement) {
		String sql = "select * from orders where statement != ? and status = 1 limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Order>(Order.class),statement, from, count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRowWithRevStatement(String statement) {
		String sql = "select count(*) from orders where statement != ? and status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler(),statement)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Order findOrder(String id,String format,String level) {
		String sql = "select * from orders where orderid = ? and format=? and level=? and status=1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Order>(Order.class), id,format,level);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateOrder(Order order,String format,String level) {
		String sql = "update orders set time = ?, salesman = ?, distributor = ?, area = ? , format = ?, level = ?, price = ?, directinc = ?, freight = ?, specialinc = ?, realprice = ?, number = ?, sumprice = ?, ifprofit = ?, statement = ?,bags=?,volume=?,fare=?,freight2=? where orderid = ? and format=?  and level=? and status=1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,order.getTime(),order.getSalesman(),
					order.getDistributor(),order.getArea(),order.getFormat(),
					order.getLevel(),order.getPrice(),order.getDirectinc(),order.getFreight(),
					order.getSpecialinc(),order.getRealprice(),order.getNumber(),
					order.getSumprice(),order.getIfprofit(),"Œ¥…Û∫À",order.getBags(),order.getVolume(),order.getFare(),order.getFreight2(),
					order.getOrderid(),format,level);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateState(String id,String format,String level, String state) {
		String sql = "update orders set statement = ? where orderid = ? and format=? and level=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, state, id,format,level);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getFailOrderNum(String name) {
		String sql = "select count(*) from orders where statement = '…Û∫ÀŒ¥Õ®π˝'  and salesman = ? and status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler(),name)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getWaitOrderNum(String name) {
		String sql = "select count(*) from orders where statement = 'Œ¥…Û∫À'  and salesman = ? and status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler(),name)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getWaitOrderSumnum() {
		String sql = "select count(*) from orders where statement = 'Œ¥…Û∫À' and status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int todayNum(String time) {
		String sql = "select count(*) from orders where time = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler(), time)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteOrder(String id,String format,String level) {
		String sql1 = "select count(*) from orders where orderid = ? and format=? and level=? and status<1";
		int num;
		String sql = "update orders set status=status-? where orderid = ? and format=? and level=? and status=1";
		
		try { 
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			num=((Long)runner.query(sql1, new ScalarHandler(),id,format,level)).intValue();
			runner.update(sql, num+1,id,format,level);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public int getDeletedCountRow() {
		String sql = "select count(*) from orders where status < 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Order> findDeletedOrderByPage(int from, int count) {
		String sql = "select * from orders where status < 1 limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Order>(Order.class), from, count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addOrder(List<Order> orders) {
		Iterator<Order> iterator = orders.iterator();
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		String sql = "insert into orders values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1)";
		while(iterator.hasNext()){
			Order order = iterator.next();
			try {
				runner.update(sql, order.getOrderid(),order.getTime(),order.getSalesman(),order.getDistributor(),order.getArea(),order.getFormat(),order.getLevel(),order.getPrice(),order.getDirectinc(),order.getFreight(),order.getSpecialinc(),order.getRealprice(),order.getNumber(),order.getSumprice(),order.getIfprofit(),order.getBags(),order.getVolume(),order.getFare(),order.getFreight2(),"Œ¥…Û∫À");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		
	}

	@Override
	public List<Order> getOrdersById(String id) {
		String sql = "select * from orders where orderid =? and status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Order>(Order.class),id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


}
