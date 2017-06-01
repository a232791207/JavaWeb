package com.wust.service;



import com.wust.domain.Product;
import com.wust.domain.ProductPage;

public interface ProductService {

	/**
	 * �����ṩ�ĵ�ǰ��Ҫ���ҳ���Լ�ÿҳ����Ҫ������������ProductDao�еķ�������Ա��
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
