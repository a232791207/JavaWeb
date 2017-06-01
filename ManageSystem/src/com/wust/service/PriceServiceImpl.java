package com.wust.service;

import java.util.List;

import com.wust.dao.PriceDao;
import com.wust.domain.Price;
import com.wust.domain.PricePage;
import com.wust.factory.BasicFactory;

public class PriceServiceImpl implements PriceService {
	PriceDao dao = BasicFactory.getFactory().getInstance(PriceDao.class);
	@Override
	public List<Object> findAreaOption() {
		List<Object> list = dao.findAreaOption();
		return list;
	}
	@Override
	public Price findPrice(String area, String format, String level) {
		Price price = dao.findPrice(area,format,level);
		return price;
	}
	@Override
	public List<Object> findFormatOption(String area) {
		List<Object> list = dao.findFormatOption(area);
		return list;
	}
	@Override
	public List<Object> findLevelOption(String area, String format) {
		List<Object> list = dao.findLevelOption(area,format);
		return list;
	}
	@Override
	public PricePage pagePrice(int thispage, int rowperpage) {
		PricePage page = new PricePage();
		List<Price> list = dao.findPriceByPage((thispage-1)*rowperpage,rowperpage);
		//��ǰҳ����
		page.setList(list);
		//��ǰҳ��
		page.setThispage(thispage);
		//ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//�ܼ�¼��
		int countrow = dao.getCountRow();
		page.setCountrow(countrow);
		//��ҳ��
		int countpage = countrow/rowperpage + (countrow%rowperpage==0?0:1);
		page.setCountpage(countpage);
		//��ҳ
		page.setFirstpage(1);
		//βҳ
		page.setLastpage(countpage);
		//��һҳ
		page.setPrepage(thispage==1?1:thispage-1);
		//��һҳ
		page.setNextpage(thispage==countpage?thispage:thispage+1);
		return page;
	}
	@Override
	public Price getPriceInfo(String id) {
		Price price = dao.getPriceInfo(id);
		return price;
	}
	@Override
	public void updatePrice(Price price) {
		dao.updatePrice(price);
	}
	@Override
	public void addPrice(Price price) {
		dao.addPrice(price);
	}
	@Override
	public void batchUpdate(String area, String format, String level,
			String price) {
		dao.batchUpdate(area,format,level,price);
	}
	@Override
	public Price findLatestPriceByProduct(String area, String format,
			String level, String time) {
		return dao.findLatestPriceByProduct(area, format, level, time);
	}
	@Override
	public void delPriceById(String id) {
		
		dao.delPriceById(id);
	}
	@Override
	public PricePage selectPagePrice(String area, String format, String level,
			int thispage, int rowperpage) {
		PricePage page = new PricePage();
		List<Price> list = dao.SelectPricePage(area,format,level,(thispage-1)*rowperpage,rowperpage);
		//��ǰҳ����
		page.setList(list);
		//��ǰҳ��
		page.setThispage(thispage);
		//ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//�ܼ�¼��
		int countrow = dao.getSelectCountRow(area,format,level);
		page.setCountrow(countrow);
		//��ҳ��
		int countpage = countrow/rowperpage + (countrow%rowperpage==0?0:1);
		page.setCountpage(countpage);
		//��ҳ
		page.setFirstpage(1);
		//βҳ
		page.setLastpage(countpage);
		//��һҳ
		page.setPrepage(thispage==1?1:thispage-1);
		//��һҳ
		page.setNextpage(thispage==countpage?thispage:thispage+1);
		return page;
	}
	@Override
	public void batchUpdateChangelim(String area, String format, String level,
			String changelim) {
		dao.batchUpdateChangelim(area, format, level, changelim);
		
	}
	@Override
	public void batchAdd(List<Price> prices) {
		dao.batchAdd(prices);
		
	}
	@Override
	public void delAllPrice() {
		dao.delAllPrice();
		
	}


}
