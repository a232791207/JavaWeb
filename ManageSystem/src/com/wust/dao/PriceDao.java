package com.wust.dao;

import java.util.List;


import com.wust.domain.Price;


public interface PriceDao {

	public List<Object> findAreaOption();

	public Price findPrice(String area, String format, String level);

	public List<Object> findFormatOption(String area);

	public List<Object> findLevelOption(String area, String format);

	public List<Price> findPriceByPage(int from, int count);

	public int getCountRow();

	public Price getPriceInfo(String id);

	public void updatePrice(Price price);

	public void addPrice(Price price);

	public void batchUpdate(String area, String format, String level,
			String price);
	public Price findLatestPriceByProduct(String area,String format,String level,String time);

	public void delPriceById(String id);

	public List<Price> SelectPricePage(String area, String format,
			String level, int from, int count);

	public int getSelectCountRow(String area, String format, String level);

	public void batchUpdateChangelim(String area, String format, String level,
			String changelim);

	public void batchAdd(List<Price> prices);

	public void delAllPrice();

}
