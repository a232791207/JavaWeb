package com.wust.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.Fare;
import com.wust.util.DaoUtils;

public class FareDaoImpl implements FareDao {

	@Override
	public List<Fare> findFareByPage(int from, int count) {
		String sql = "select * from fare where status = 0 limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Fare>(Fare.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow() {
		String sql = "select count(*) from fare where status = 0";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addFare(String id, String area, double fare, double num,
			double sumfare) {
		String sql = "insert into fare values (?,?,?,?,?,0)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id,area,fare,num,sumfare);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void delete(String id) {
		String sql = "delete from fare where id = ? and status = 0";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public Fare getFare(String orderid) {
		String sql = "select * from fare where id=? and status = 0";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Fare>(Fare.class), orderid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateFare(Fare fare) {
		String sql = "update fare set area=?,fare=?,num=?,sumfare=? where id=? and status = 0";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, fare.getArea(),fare.getFare(),fare.getNum(),fare.getSumfare(),fare.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public void delete2(String id) {
		String sql = "delete from fare where id = ? and status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Fare> findFreightByPage(int from, int count) {
		String sql = "select * from fare where status = 1 limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Fare>(Fare.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow2() {
		String sql = "select count(*) from fare where status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Fare getFreight(String orderid) {
		String sql = "select * from fare where id=? and status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Fare>(Fare.class), orderid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateFreight(Fare fare) {
		String sql = "update fare set area=?,fare=?,num=?,sumfare=? where id=? and status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, fare.getArea(),fare.getFare(),fare.getNum(),fare.getSumfare(),fare.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addFreight(String orderid, String area, double fare,
			double num, double sumfare) {
		String sql = "insert into fare values (?,?,?,?,?,1)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, orderid,area,fare,num,sumfare);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
