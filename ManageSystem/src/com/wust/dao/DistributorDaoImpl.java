package com.wust.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.Distributor;
import com.wust.util.DaoUtils;

public class DistributorDaoImpl implements DistributorDao {

	@Override
	public List<Distributor> findDistributorByPage(int from, int count) {
		String sql = "select * from distributor limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Distributor>(Distributor.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow() {
		String sql ="select count(*) from distributor";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addDistributor(Distributor distributor) {
		String sql = "insert into distributor values(null,?,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, distributor.getName(), distributor.getArea(), distributor.getSalesman(), distributor.getPhonenumber(), distributor.getCreditlines());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteDistributor(String id) {
		String sql = "delete from distributor where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Distributor findDistributorById(String id) {
		String sql = "select * from distributor where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Distributor>(Distributor.class), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateDistributor(Distributor distributor) {
		String sql = "update distributor set area = ?, salesman = ?, phonenumber=?,creditlines=? where id=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,distributor.getArea(), distributor.getSalesman(), distributor.getPhonenumber(), distributor.getCreditlines(),distributor.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Object> findDistributorOption() {
		String sql = "select distinct name from distributor";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new ColumnListHandler("name"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Distributor findDistributorByName(String name) {
		String sql = "select * from distributor where name = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Distributor>(Distributor.class), name);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Distributor> selectDistributorPage(String name, int from,
			int count) {
		String sql = "select * from distributor where name like ? limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Distributor>(Distributor.class),"%"+name+"%",from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountSelectDistributor(String name) {
		String sql ="select count(*) from distributor where name like ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler(),"%"+name+"%")).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
