package com.wust.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.Product;
import com.wust.domain.StoreRecord;
import com.wust.util.DaoUtils;

public class StoreRecordDaoImpl implements StoreRecordDao {

	@Override
	public void addStoreRecordDao(StoreRecord storeRecord) {
		String sql = "insert into storerecord values(null,?,?,?,?,?,?,?,?,?)";
		try {
			//time,format,level,height,width,thick,num,volume,bagSlices
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,storeRecord.getTime(),storeRecord.getFormat(),storeRecord.getLevel()
					,storeRecord.getHeight(),storeRecord.getWidth(),storeRecord.getThick()
					,storeRecord.getNum(),storeRecord.getVolume(),storeRecord.getBagSlices());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public List<StoreRecord> findFormatAndLevelProductByPage(String format,
			String level, int from, int count) {
		String sql = "select * from storerecord where format like ? AND level like ? limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<StoreRecord>(StoreRecord.class),"%"+format+"%","%"+level+"%",from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow(String format, String level) {
		String sql ="select count(*) from storerecord where format like ? and level like ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler(),format,level)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public StoreRecord getProductInfo(long id) {
		String sql = "select * from storerecord where id=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql,new BeanHandler<StoreRecord>(StoreRecord.class) ,id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	

}
