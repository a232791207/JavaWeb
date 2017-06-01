package com.wust.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.BasicFare;
import com.wust.util.DaoUtils;

public class BasicFareDaoImpl implements BasicFareDao {

	@Override
	public List<BasicFare> findBasicFareByPage(int from, int count) {
		String sql = "select * from basicfare where status = 0 limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<BasicFare>(BasicFare.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow() {
		String sql = "select count(*) from basicfare where status = 0";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addBasicFare(BasicFare basicFare) {
		String sql = "insert into basicfare values (null,?,?,0)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, basicFare.getArea(),basicFare.getFare());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteBasicFare(String id) {
		String sql = "delete from basicfare where id = ? and status = 0";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public BasicFare getBasicFareInfo(String id) {
		String sql = "select * from basicfare where id = ? and status = 0";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<BasicFare>(BasicFare.class), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateBasicFare(BasicFare basicFare) {
		String sql = "update basicfare set area = ?, fare = ? where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, basicFare.getArea(),basicFare.getFare(),basicFare.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public BasicFare findBasicfByArea(String area) {
		String sql = "select * from basicfare where area = ? and status = 0";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<BasicFare>(BasicFare.class), area);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<BasicFare> findBasicFreightByPage(int from, int count) {
		String sql = "select * from basicfare where status = 1 limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<BasicFare>(BasicFare.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow2() {
		String sql = "select count(*) from basicfare where status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public BasicFare findBasicf2ByArea(String area) {
		String sql = "select * from basicfare where area = ? and status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<BasicFare>(BasicFare.class), area);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addBasicFreight(BasicFare basicFare) {
		String sql = "insert into basicfare values (null,?,?,1)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, basicFare.getArea(),basicFare.getFare());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteBasicFreight(String id) {
		String sql = "delete from basicfare where id = ? and status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public BasicFare getBasicFreightInfo(String id) {
		String sql = "select * from basicfare where id = ? and status = 1";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<BasicFare>(BasicFare.class), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
