package com.wust.service;



import com.wust.domain.Product;
import com.wust.domain.ProductPage;

public interface ProductService {

	/**
	 * 根据提供的当前需要查的页码以及每页所需要的行数，调用ProductDao中的方法查找员工
	 * @param thispage
	 * @param rowperpage
	 * @return
	 */
	public ProductPage formatAndLevelPageProduct(String format,String level,int thispage, int rowperpage);
	public Product findNum(String format, String level);
	public void addProduct(Product product);
	public void deleteProduct(int id);
	public void updateProduct(Product product);
	public Product getProductInfo(int id);
	public void updateProduct(String format, String level, int number, double volume);
	public Product getProductInfo(String format, String level);


}
