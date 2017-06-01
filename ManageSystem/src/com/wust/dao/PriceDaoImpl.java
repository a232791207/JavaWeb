package com.wust.dao;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.Price;
import com.wust.util.DaoUtils;

public class PriceDaoImpl implements PriceDao {

	@Override
	public List<Object> findAreaOption() {
		String sql = "select distinct area from price";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new ColumnListHandler("area"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Price findPrice(String area, String format, String level) {
		String sql = "select * from price where area=? and format=? and level=? order by time desc";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Price>(Price.class), area,format,level);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Object> findFormatOption(String area) {
		String sql = "select distinct format from price where area = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new ColumnListHandler("format"), area);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Object> findLevelOption(String area, String format) {
		String sql = "select distinct level from price where area = ? and format = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new ColumnListHandler("level"), area, format);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Price> findPriceByPage(int from, int count) {
		String sql = "select * from price limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Price>(Price.class),from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow() {
		String sql = "select count(*) from price";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Price getPriceInfo(String id) {
		String sql = "select * from price where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Price>(Price.class),id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updatePrice(Price price) {
		String sql = "update price set time = ?, area = ?, format=?,level=?,price=?,changelim=? where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, price.getTime(),price.getArea(),price.getFormat(),price.getLevel(),price.getPrice(),price.getChangelim(),price.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addPrice(Price price) {
		String sql = "insert into price values (?,?,?,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,null,price.getTime(),price.getArea(),price.getFormat(),price.getLevel(),price.getPrice(),price.getChangelim());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void batchUpdate(String area, String format, String level,
			String price) {
		String sql = "update price set price=? where 1=1";
		List<String> list = new ArrayList<String>();
		list.add(price);
		if(!area.equals("")){
			sql = sql + " and area = ?";
			list.add(area);
		}
		if(!format.equals("")){
			sql = sql + " and format = ?";
			list.add(format);
		}
		if(!level.equals("")){
			sql = sql + " and level = ?";
			list.add(level);
		}
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, list.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Price findLatestPriceByProduct(String area,String format,String level,String time) {
		
		String sql = "select * from price where area=? and format=? and level=? and time<? order by time DESC";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Price>(Price.class), area,format,level,time);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void delPriceById(String id) {
		String sql = "delete from price where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Price> SelectPricePage(String area, String format,
			String level, int from, int count) {
		String sql = "select * from price where 1 = ? ";
		List<Object> list = new ArrayList<Object>();
		list.add("1");
		if(!area.equals("")){
			sql = sql + "and area like ? ";
			list.add("%"+area+"%");
		}
		if(!format.equals("")){
			sql = sql + "and format like ? ";
			list.add("%"+format+"%");
		}
		if(!level.equals("")){
			sql = sql + "and level like ? ";
			list.add("%"+level+"%");
		}
		sql = sql + "limit ?,?";
		list.add(from);
		list.add(count);
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Price>(Price.class),list.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getSelectCountRow(String area, String format, String level) {
		String sql = "select count(*) from price where 1 = ? ";
		List<String> list = new ArrayList<String>();
		list.add("1");
		if(!area.equals("")){
			sql = sql + "and area like ? ";
			list.add("%"+area+"%");
		}
		if(!format.equals("")){
			sql = sql + "and format like ? ";
			list.add("%"+format+"%");
		}
		if(!level.equals("")){
			sql = sql + "and level like ? ";
			list.add("%"+level+"%");
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
	public void batchUpdateChangelim(String area, String format, String level,
			String changelim) {
		String sql = "update price set changelim=? where 1=1";
		List<String> list = new ArrayList<String>();
		list.add(changelim);
		if(!area.equals("")){
			sql = sql + " and area = ?";
			list.add(area);
		}
		if(!format.equals("")){
			sql = sql + " and format = ?";
			list.add(format);
		}
		if(!level.equals("")){
			sql = sql + " and level = ?";
			list.add(level);
		}
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, list.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void batchAdd(List<Price> prices) {
		Iterator<Price> iterator = prices.iterator();
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		while(iterator.hasNext()){
			Price price =iterator.next();
			String sql = "insert into price values (?,?,?,?,?,?,?)";
			try {
				if(getPrice(price)==null){
					runner.update(sql,null,price.getTime(),price.getArea(),price.getFormat(),price.getLevel(),price.getPrice(),price.getChangelim());	
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			/*System.out.println("时间:"+price.getTime()+"规格:"+price.getFormat()+"等级:"+price.getLevel()
					+"地区:"+price.getArea()+"变化:"+price.getChangelim());*/
		}
		
	}
	public Price getPrice(Price price){
		String sql = "select * from price where time = ? and area = ? and format=? and level=? and price=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Price>(Price.class),price.getTime(),price.getArea(),price.getFormat()
					,price.getLevel(),price.getPrice());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void delAllPrice() {
		String sql = "truncate table price";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

}
