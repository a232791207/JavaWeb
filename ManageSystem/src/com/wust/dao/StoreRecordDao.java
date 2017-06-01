package com.wust.dao;

import java.util.List;

import com.wust.domain.StoreRecord;

public interface StoreRecordDao {
	public void addStoreRecordDao(StoreRecord storeRecord);

	public List<StoreRecord> findFormatAndLevelProductByPage(String format,
			String level, int i, int rowperpage);

	public int getCountRow(String string, String string2);

	public StoreRecord getProductInfo(long id);
}
