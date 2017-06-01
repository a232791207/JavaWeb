package com.wust.dao;



import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.Directinc;
import com.wust.util.DaoUtils;

public class DirectincDaoImpl implements DirectincDao{

	@Override
	public Directinc findDirectinc(String time, String distributor, String area, String format, String level) {
		String sql ="select * from directinc where distributor=? and area=? and format=? and level=?";
		try {
			Directinc directinc = null;
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			List<Directinc> list = runner.query(sql, new BeanListHandler<Directinc>(Directinc.class), distributor, area, format, level);
			for(int i = 0 ; i < list.size() ; i++){
				String stime = list.get(i).getStime();
				String etime = list.get(i).getEtime();			
				if(time.compareTo(stime)>=0 && time.compareTo(etime)<=0){
					directinc = list.get(i);
				}
			}		
			return directinc;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Directinc> findDirectincByPage(int from, int count) {
		String sql = "select * from directinc limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Directinc>(Directinc.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow() {
		String sql = "select count(*) from directinc";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Directinc findDirectincById(String id) {
		String sql = "select * from directinc where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Directinc>(Directinc.class),id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateDirectinc(Directinc directinc) {
		String sql = "update directinc set stime = ?, etime = ?, distributor = ?, area = ?, format = ?, level = ?, directinc = ? where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, directinc.getEtime(),directinc.getStime(),directinc.getDistributor(),directinc.getArea(),directinc.getFormat(),directinc.getLevel(),directinc.getDirectinc(),directinc.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void delDirectincById(String id) {
		String sql = "delete from directinc where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addDirectinc(Directinc directinc) {
		String sql = "insert into directinc values (?,?,?,?,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, null,directinc.getStime(),directinc.getEtime(),directinc.getDistributor(),directinc.getArea(),directinc.getFormat(),directinc.getLevel(),directinc.getDirectinc());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
