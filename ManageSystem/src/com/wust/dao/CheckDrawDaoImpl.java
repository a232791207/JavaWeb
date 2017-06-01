package com.wust.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.CheckDraw;
import com.wust.domain.Draworder;
import com.wust.util.DaoUtils;

public class CheckDrawDaoImpl implements CheckDrawDao {

	@Override
	public List<CheckDraw> findCheckDrawByPage(int from, int count) {
		String sql = "select * from checkdraw limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<CheckDraw>(CheckDraw.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow() {
		String sql = "select count(*) from checkdraw";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}



	@Override
	public List<CheckDraw> getCheckDrawByDistributorPage(String distributor,int from,
			int count) {
		String sql = "select * from checkdraw where distributor like ? limit ?,?";
		String sqlall = "select * from checkdraw limit ?,?";
		if(!distributor.equals("")&&distributor!=null){
			try {
				QueryRunner runner = new QueryRunner(DaoUtils.getSource());
				return runner.query(sql, new BeanListHandler<CheckDraw>(CheckDraw.class),"%"+distributor+"%",from,count);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		else{
			try {
				QueryRunner runner = new QueryRunner(DaoUtils.getSource());
				return runner.query(sqlall, new BeanListHandler<CheckDraw>(CheckDraw.class),from,count);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
	}

	@Override
	public void updateCheckDrawByDrawnum(String distributor, String format,
			String level, int number) {
		String sql = "update checkdraw set drawnum=drawnum+?,undrawnum=undrawnum-? where distributor=? and format=? and level=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,number,number,distributor,format,level);
			CheckDraw checkDraw = getCheckDraw(distributor, format, level);
			if(checkDraw.getDrawnum()==0&&checkDraw.getUndrawnum()==0&&checkDraw.getNumber()==0){
				sql="delete from checkdraw where distributor=? and format=? and level=?";
				runner.update(sql,distributor,format,level);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public void deleteCheckDraw(String distributor, String format, String level) {
		
		
	}

	@Override
	public CheckDraw getCheckDraw(String distributor, String format,
			String level) {
		String sql = "select * from checkdraw where distributor like ? and format like ? and level like ?";
		try{
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<CheckDraw>(CheckDraw.class),distributor, format,level);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addCheckDraw(String distributor, String format, String level,
			int number) {
		String sql = "insert into checkdraw values (?,?,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,distributor,format,level,number,0,number);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateCheckDrawNum(String distributor, String format,
			String level, int number) {
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		String sql = "update checkdraw set number=number+?,undrawnum=undrawnum+? where distributor=? and format=? and level=?";
		try {
			
			runner.update(sql,number,number,distributor,format,level);
			CheckDraw checkDraw = getCheckDraw(distributor, format, level);
			if(checkDraw.getDrawnum()==0&&checkDraw.getUndrawnum()==0&&checkDraw.getNumber()==0){
				sql="delete from checkdraw where distributor=? and format=? and level=?";
				runner.update(sql,distributor,format,level);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRowWithDistributor(String distributor) {
		String sql = "select count(*) from checkdraw where 1 = ?";
		List<Object> list = new ArrayList<Object>();
		list.add("1");
		if(!distributor.equals("")&&distributor!=null){
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
	public List<Object> findLevelOption(String distrbutor,String format) {
		String sql = "select distinct level from checkdraw where distributor = ? and format = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new ColumnListHandler("level"), distrbutor,format);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateCheckDrawByDrawnum(List<Draworder> draworders) {
		Iterator<Draworder> iterator= draworders.iterator();
		String sql = "update checkdraw set drawnum=drawnum+?,undrawnum=undrawnum-? where distributor=? and format=? and level=?";
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		while(iterator.hasNext()){
			Draworder draworder = iterator.next();
			try {
				runner.update(sql,draworder.getNumber(),draworder.getNumber(),draworder.getDistributor(),draworder.getFormat(),draworder.getLevel());
				CheckDraw checkDraw = getCheckDraw(draworder.getDistributor(),draworder.getFormat(),draworder.getLevel());
				if(checkDraw.getDrawnum()==0&&checkDraw.getUndrawnum()==0&&checkDraw.getNumber()==0){
					sql="delete from checkdraw where distributor=? and format=? and level=?";
					runner.update(sql,draworder.getDistributor(),draworder.getFormat(),draworder.getLevel());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		
	}

	
}
