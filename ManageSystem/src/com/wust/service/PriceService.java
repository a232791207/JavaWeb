package com.wust.service;

import java.util.List;

import com.wust.domain.Price;
import com.wust.domain.PricePage;


public interface PriceService {

	public List<Object> findAreaOption();

	public Price findPrice(String area, String format, String level);

	public List<Object> findFormatOption(String area);

	public List<Object> findLevelOption(String area, String format);

	public PricePage pagePrice(int thispage, int rowperpage);

	public Price getPriceInfo(String id);

	public void updatePrice(Price price);

	public void addPrice(Price price);

	public void batchUpdate(String area, String format, String level,
			String price);
	public Price findLatestPriceByProduct(String area,String format,String level,String time);

	public void delPriceById(String id);

	public PricePage selectPagePrice(String area, String format, String level,
			int thispage, int rowperpage);

	public void batchUpdateChangelim(String area, String format, String level,
			String changelim);

	public void batchAdd(List<Price> prices);

	public void delAllPrice();
}
