package com.wust.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.Clerk;
import com.wust.util.DaoUtils;

public class ClerkDaoImpl implements ClerkDao {

	@Override
	public List<Clerk> findClerkByPage(int from, int count) {
		String sql = "select * from clerk limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Clerk>(Clerk.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow() {
		String sql ="select count(*) from clerk";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Clerk findClerkById(String id) {
		String sql = "select * from clerk where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Clerk>(Clerk.class), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateClerk(Clerk clerk) {
		String sql = "update clerk set password=?,gender=?,age=?,type=?,phonenumber=?,address=? where id=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, clerk.getPassword(),clerk.getGender(),clerk.getAge(),clerk.getType(),clerk.getPhonenumber(),clerk.getAddress(),clerk.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteClerk(String id) {
		String sql = "delete from clerk where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addClerk(Clerk clerk) {
		String sql = "insert into clerk values(?,?,?,?,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,clerk.getId(), clerk.getPassword(), clerk.getName(), clerk.getType(), clerk.getGender(), clerk.getAge(), clerk.getPhonenumber(), clerk.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Clerk findClerkByIdPasTyp(String id, String password, String type) {
		String sql = "select * from clerk where id=? and password=? and type=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Clerk>(Clerk.class), id, password, type);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updatePassword(String id, String password) {
		String sql = "update clerk set password=? where id=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, password, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Object> findSalesmanOption() {
		String sql = "select distinct name from clerk where type = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new ColumnListHandler("name"), "ÒµÎñÔ±");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Clerk> selectClerk(String name, String type, int from,
			int count) {
		String sql = "select * from clerk where 1 = ? ";
		List<Object> list = new ArrayList<Object>();
		list.add("1");
		if(!name.equals("")){
			sql = sql + "and name like ? ";
			list.add("%"+name+"%");
		}
		if(!type.equals("")){
			sql = sql + "and type = ? ";
			list.add(type);
		}
		sql = sql + "limit ?,?";
		list.add(from);
		list.add(count);
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Clerk>(Clerk.class),list.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountSelectedClerk(String name, String type) {
		String sql = "select count(*) from clerk where 1 = ? ";
		List<String> list = new ArrayList<String>();
		list.add("1");
		if(!name.equals("")){
			sql = sql + "and name like ? ";
			list.add("%"+name+"%");
		}
		if(!type.equals("")){
			sql = sql + "and type = ? ";
			list.add(type);
		}
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler(),list.toArray())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
