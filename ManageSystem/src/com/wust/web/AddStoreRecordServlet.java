package com.wust.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wust.domain.Product;
import com.wust.domain.StoreRecord;
import com.wust.factory.BasicFactory;
import com.wust.service.ProductService;
import com.wust.service.StoreRecordService;

public class AddStoreRecordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		StoreRecordService service = BasicFactory.getFactory().getInstance(StoreRecordService.class);
		ProductService productService=BasicFactory.getFactory().getInstance(ProductService.class);
		
		try {	
				StoreRecord storeRecord = new StoreRecord();
				BeanUtils.populate(storeRecord, request.getParameterMap());
				
				//�޸���Ϣ��product
				Product product=productService.getProductInfo(storeRecord.getFormat(), storeRecord.getLevel());
				if(product!=null){
					//���в�Ʒ���
					product.setNum(product.getNum()+storeRecord.getNum());
					product.setVolume(product.getVolume()+storeRecord.getVolume());
					productService.updateProduct(product);
				}
				else{
					//�²�Ʒ���
					Product product2 = new Product(storeRecord);
					productService.addProduct(product2);
					
				}
				service.addStoreRecordDao(storeRecord);
				response.sendRedirect(request.getContextPath()+"/servlet/FindStoreRecordServlet?thispage=1&format=&level=");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
