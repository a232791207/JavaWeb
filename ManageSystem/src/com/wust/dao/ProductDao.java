package com.wust.dao;

import java.util.List;


import com.wust.domain.Product;

public interface ProductDao {

	public Product findNum(String format, String level);
	public int getCountRow(String format,String level);
	public Product findProductById(int id);
	/**
	 * 增加产品
	 * @param product
	 * @return
	 */
	public void addProduct(Product product);
	/**
	 * 删除产品
	 * @param id
	 */
	public void deleteProduct(int id);
	/**
	 * 修改产品
	 * @param product
	 */
	public void updateProduct(Product product);
	public List<Product> findFormatAndLevelProductByPage(String format,String level,int from, int count);
	public void updateProduct(String format, String level, int number, double volume);
	public Product findProduct(String format, String level);

}
