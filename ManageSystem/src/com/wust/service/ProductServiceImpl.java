package com.wust.service;

import java.util.List;


import com.wust.dao.ProductDao;
import com.wust.domain.Product;
import com.wust.domain.ProductPage;
import com.wust.factory.BasicFactory;

public class ProductServiceImpl implements ProductService {
	ProductDao dao =BasicFactory.getFactory().getInstance(ProductDao.class);
	@Override
	public Product findNum(String format, String level) {
		Product product = dao.findNum(format,level);
		return product;
	}
	@Override
	public void addProduct(Product product) {
		dao.addProduct(product);
		
	}
	@Override
	public void deleteProduct(int id) {
		dao.deleteProduct(id);
		
	}
	@Override
	public void updateProduct(Product product) {
		dao.updateProduct(product);
		
	}
	@Override
	public Product getProductInfo(int id) {
		Product product = dao.findProductById(id);
		return product;
	}
	@Override
	public ProductPage formatAndLevelPageProduct(String format,String level,int thispage, int rowperpage) {
		ProductPage page = new ProductPage();
		List<Product> list = dao.findFormatAndLevelProductByPage(format,level,(thispage-1)*rowperpage,rowperpage);
		//当前页数据
		page.setList(list);
		//当前页码
		page.setThispage(thispage);
		//每页记录数
		page.setRowperpage(rowperpage);
		//总记录数
		int countrow = dao.getCountRow("%"+format+"%","%"+level+"%");
		page.setCountrow(countrow);
		//总页数
		int countpage = countrow/rowperpage + (countrow%rowperpage==0?0:1);
		page.setCountpage(countpage);
		//首页
		page.setFirstpage(1);
		//尾页
		page.setLastpage(countpage);
		//上一页
		page.setPrepage(thispage==1?1:thispage-1);
		//下一页
		page.setNextpage(thispage==countpage?thispage:thispage+1);
		return page;
	}
	@Override
	public void updateProduct(String format, String level, int number, double volume) {
		dao.updateProduct(format,level,number,volume);
	}
	@Override
	public Product getProductInfo(String format, String level) {
		return dao.findProduct(format,level);
	}

}
