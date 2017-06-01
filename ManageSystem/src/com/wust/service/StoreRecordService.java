package com.wust.service;

import com.wust.domain.StoreRecord;
import com.wust.domain.StoreRecordPage;

public interface StoreRecordService {
	public void addStoreRecordDao(StoreRecord storeRecord);
	public StoreRecordPage formatAndLevelPageStoreRecord(String format,String level,int thispage, int rowperpage);
	public StoreRecord getProductInfo(long id);
}
