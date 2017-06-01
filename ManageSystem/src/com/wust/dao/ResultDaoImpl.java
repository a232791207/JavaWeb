package com.wust.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.CheckDraw;
import com.wust.domain.Draworder;
import com.wust.domain.Order;
import com.wust.domain.Payment;
import com.wust.domain.Result;
import com.wust.util.DaoUtils;

public class ResultDaoImpl implements ResultDao{

	@Override
	public List<Result> findResultByPage(int from, int count) {
		String sql = "select * from result limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Result>(Result.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow() {
		String sql = "select count(*) from result";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Result getResultByDisAndSal(String distributor, String salesman) {
		String sql = "select * from result where distributor=? and salesman=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Result>(Result.class),distributor,salesman);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addResult(Order order) {
		String sql = "insert into result values (null,?,?,?,0,0,0,0,0,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, order.getDistributor(),order.getSalesman(),order.getSumprice(),order.getSumprice(),order.getSumprice());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateResult(Order order) {
		String sql = "update result set sumsprice = sumsprice + ?, sumaprice = sumaprice + ?, result = result + ? where distributor = ? and salesman = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,order.getSumprice(),order.getSumprice(),order.getSumprice(),order.getDistributor(),order.getSalesman());
			Result result = getResultByDisAndSal(order.getDistributor(), order.getSalesman());
			//都为0，就把这条记录给删除
			if(result.getSumaprice().trim().equalsIgnoreCase("0")&&result.getSumdprice().trim().equalsIgnoreCase("0")&&result.getSumiprice().trim().equalsIgnoreCase("0")&&result.getSumpprice().trim().equalsIgnoreCase("0")&&result.getSumrinterest().trim().equalsIgnoreCase("0")&&result.getSumrprice().trim().equalsIgnoreCase("0")&&result.getSumsprice().trim().equalsIgnoreCase("0")&&result.getResult().trim().equalsIgnoreCase("0")){
				sql="delete from result where distributor=? and salesman=?";
				runner.update(sql,order.getDistributor(), order.getSalesman());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void payUpdateResult(Payment payment) {
		String sql = "update result set sumrprice = sumrprice + ?, sumiprice = sumiprice + ?, sumaprice = sumaprice + ?, result = sumaprice - sumrprice where distributor = ? and salesman = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,payment.getMoney(),payment.getInterest(),payment.getInterest(),payment.getDistributor(),payment.getSalesman());
			//都为0，就把这条记录给删除
			Result result = getResultByDisAndSal(payment.getDistributor(), payment.getSalesman());
			if(result.getSumaprice().trim().equalsIgnoreCase("0")&&result.getSumdprice().trim().equalsIgnoreCase("0")&&result.getSumiprice().trim().equalsIgnoreCase("0")&&result.getSumpprice().trim().equalsIgnoreCase("0")&&result.getSumrinterest().trim().equalsIgnoreCase("0")&&result.getSumrprice().trim().equalsIgnoreCase("0")&&result.getSumsprice().trim().equalsIgnoreCase("0")&&result.getResult().trim().equalsIgnoreCase("0")){
				sql="delete from result where distributor=? and salesman=?";
				runner.update(sql,payment.getDistributor(),payment.getSalesman());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addInterest(String distributor, String salesman, String money) {
		String sql = "update result set sumrinterest = sumrinterest + ?, sumaprice = sumaprice - ?, result = result - ? where distributor = ? and salesman = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,money,money,money,distributor,salesman);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addPayback(String distributor, String salesman, String money) {
		String sql = "update result set sumpprice = sumpprice + ?, sumaprice = sumaprice - ?, result = result - ? where distributor = ? and salesman = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,money,money,money,distributor,salesman);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Result> findDistributorsByName(String name) {
		String sql = "select * from result where distributor = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Result>(Result.class),name);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "delete from result where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public void updateResultBynum(String distributor, String salesman,
			double sumprice) {
		String sql = "update result set sumdprice = sumdprice + ? where salesman = ? and distributor = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,sumprice,salesman,distributor);
			Result result = getResultByDisAndSal(distributor, salesman);
			//都为0，就把这条记录给删除
			if(result.getSumaprice().trim().equalsIgnoreCase("0")&&result.getSumdprice().trim().equalsIgnoreCase("0")&&result.getSumiprice().trim().equalsIgnoreCase("0")&&result.getSumpprice().trim().equalsIgnoreCase("0")&&result.getSumrinterest().trim().equalsIgnoreCase("0")&&result.getSumrprice().trim().equalsIgnoreCase("0")&&result.getSumsprice().trim().equalsIgnoreCase("0")&&result.getResult().trim().equalsIgnoreCase("0")){
				sql="delete from result where distributor=? and salesman=?";
				runner.update(sql,distributor,salesman);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public List<Result> getResultPageByDistributor(String distributor,
			int from, int count) {
		String sql = "select * from result where 1 = 1";
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
			return runner.query(sql, new BeanListHandler<Result>(Result.class),list.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRowWithDistributor(String distributor) {
		String sql = "select count(*) from payment where 1 = ?";
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
	public void updateResultByNum(List<Draworder> draworders) {
		Iterator<Draworder> iterator= draworders.iterator();
		String sql = "update result set sumdprice = sumdprice + ? where salesman = ? and distributor = ?";
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		while(iterator.hasNext()){
			Draworder draworder = iterator.next();
			try {
				runner.update(sql,draworder.getSumprice(),draworder.getSalesman(),draworder.getDistributor());
				Result result = getResultByDisAndSal(draworder.getDistributor(), draworder.getSalesman());
				//都为0，就把这条记录给删除
				if(result.getSumaprice().trim().equalsIgnoreCase("0")&&result.getSumdprice().trim().equalsIgnoreCase("0")&&result.getSumiprice().trim().equalsIgnoreCase("0")&&result.getSumpprice().trim().equalsIgnoreCase("0")&&result.getSumrinterest().trim().equalsIgnoreCase("0")&&result.getSumrprice().trim().equalsIgnoreCase("0")&&result.getSumsprice().trim().equalsIgnoreCase("0")&&result.getResult().trim().equalsIgnoreCase("0")){
					sql="delete from result where distributor=? and salesman=?";
					runner.update(sql,draworder.getDistributor(), draworder.getSalesman());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		
	}


}
