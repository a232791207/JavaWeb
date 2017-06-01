package com.wust.service;


import java.util.List;


import com.wust.dao.StoreRecordDao;
import com.wust.domain.StoreRecord;
import com.wust.domain.StoreRecordPage;
import com.wust.factory.BasicFactory;

public class StoreRecordServiceImpl implements StoreRecordService {
	StoreRecordDao dao = BasicFactory.getFactory().getInstance(StoreRecordDao.class);
	@Override
	public void addStoreRecordDao(StoreRecord storeRecord) {
		dao.addStoreRecordDao(storeRecord);

	}
	@Override
	public StoreRecordPage formatAndLevelPageStoreRecord(String format,
			String level, int thispage, int rowperpage) {
		StoreRecordPage page = new StoreRecordPage();
		List<StoreRecord> list = dao.findFormatAndLevelProductByPage(format,level,(thispage-1)*rowperpage,rowperpage);
		//��ǰҳ����
		page.setList(list);
		//��ǰҳ��
		page.setThispage(thispage);
		//ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//�ܼ�¼��
		int countrow = dao.getCountRow("%"+format+"%","%"+level+"%");
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
	public StoreRecord getProductInfo(long id) {
		// TODO Auto-generated method stub
		return dao.getProductInfo(id);
	}
	
	
}
