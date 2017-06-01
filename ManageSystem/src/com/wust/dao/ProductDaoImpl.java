package com.wust.dao;



import java.util.List;


import org.apache.commons.dbutils.QueryRunner;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wust.domain.Product;
import com.wust.util.DaoUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public Product findNum(String format, String level) {
		String sql = "select * from product where format=? and level=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Product>(Product.class), format, level);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addProduct(Product product) {
		
		String sql = "insert into product values(null,?,?,?,?,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,product.getFormat(),product.getLevel(),product.getHeight(),product.getWidth(),product.getThick(),
					product.getNum(),product.getVolume(),product.getBagSlices());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteProduct(int id) {
		
		String sql = "delete from product where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateProduct(Product product) {
		
		String sql = "update product set format=?,level=?,thick=?,num=?,volume=?,bagSlices=? where id=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, product.getFormat(),product.getLevel(),product.getThick(),product.getNum(),
					product.getVolume(),product.getBagSlices(),product.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int getCountRow(String format,String level) {
		String sql ="select count(*) from product where format like ? and level like ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long)runner.query(sql, new ScalarHandler(),format,level)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Product findProductById(int id) {
		String sql = "select * from product where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Product>(Product.class), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}



	@Override
	public List<Product> findFormatAndLevelProductByPage(String format,
			String level, int from, int count) {
		String sql = "select * from product where format like ? AND level like ? limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Product>(Product.class),"%"+format+"%","%"+level+"%",from,count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateProduct(String format, String level, int number, double volume) {
		String sql = "update product set num = num-?, volume = volume-?  where format=? and level=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, number, volume, format, level);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Product findProduct(String format, String level) {
		String sql = "select * from product where format = ? and level=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Product>(Product.class), format,level);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	

}
