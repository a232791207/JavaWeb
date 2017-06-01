package com.wust.dao;

import java.sql.SQLException;

import java.util.Iterator;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.Draworder;
import com.wust.util.DaoUtils;

public class DraworderDaoImpl implements DraworderDao {

	@Override
	public int getCountRow() {
		String sql ="select count(*) from draworder where status=1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Draworder> findDraworderByPage(int from, int count) {
		String sql = "select * from draworder  where status=1 limit ?,?" ;
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Draworder>(Draworder.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addDraworder(Draworder draworder) {
		String sql = "insert into draworder values(?,?,?,?,?,?,?,?,?,?,?,?,?,1)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,draworder.getId(),draworder.getTime(),draworder.getDistributor(),draworder.getSalesman(),
					draworder.getCustomer(),draworder.getFormat(),draworder.getLevel(),draworder.getPrice(),draworder.getNumber(),draworder.getSumprice(),draworder.getCheckdenomination(),draworder.getBalance(),draworder.getOrderid());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public void delete(String id,String format,String level,double price) {
		String sql = "update draworder set status=status+1 where id=? and format=? and level=? and price=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id,format,level,price);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public Draworder findDraworder(String id, String format, String level,double price) {
		String sql = "select * from draworder where id = ? and format=? and level=? and price = ? and status=1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Draworder>(Draworder.class), id,format,level,price);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Object[]> getDrawDetailsByDist(String distributor) {
		String sql = "select customer,sum(sumprice) from draworder where distributor = ? and status = 1 group by customer";
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
		String sql ="select count(*) from draworder where status>1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Draworder> findDeletedDraworderByPage(int from, int count) {
		String sql = "select * from draworder  where status>1 limit ?,?" ;
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Draworder>(Draworder.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Draworder> findDistributorDraworderByPage(String distributor,
			int from, int count) {
		String sql = "select * from draworder  where status=1 and distributor=? limit ?,?" ;
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Draworder>(Draworder.class),distributor,from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getDistributorCountRow(String distributor) {
		String sql ="select count(*) from draworder where distributor=? and status=1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler(),distributor)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addDraworder(List<Draworder> draworders) {
		Iterator<Draworder> iterator = draworders.iterator();
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		String sql = "insert into draworder values(?,?,?,?,?,?,?,?,?,?,?,?,?,1)";
		while(iterator.hasNext()){
			Draworder draworder = iterator.next();
			try {
				runner.update(sql,draworder.getId(),draworder.getTime(),draworder.getDistributor(),draworder.getSalesman(),
						draworder.getCustomer(),draworder.getFormat(),draworder.getLevel(),draworder.getPrice(),draworder.getNumber(),draworder.getSumprice(),draworder.getCheckdenomination(),draworder.getBalance(),draworder.getOrderid());
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		
		
	}

}
