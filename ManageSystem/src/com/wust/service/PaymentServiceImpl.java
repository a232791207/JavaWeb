package com.wust.service;

import java.util.List;

import com.wust.dao.PaymentDao;
import com.wust.domain.Payment;
import com.wust.domain.PaymentPage;
import com.wust.factory.BasicFactory;

public class PaymentServiceImpl implements PaymentService {
	PaymentDao dao = BasicFactory.getFactory().getInstance(PaymentDao.class);
	@Override
	public PaymentPage getPaymentByPage(int thispage, int rowperpage) {
		PaymentPage page = new PaymentPage();
		List<Payment> list = dao.findPaymentByPage((thispage-1)*rowperpage,rowperpage);
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
	public void addPayment(Payment payment) {
		dao.addPayment(payment);
	}
	@Override
	public void deletePayment(int id) {
		dao.delete(id);
		
	}
	@Override
	public Payment getPaymentById(int id) {
		return dao.getPaymentById(id);
	}
	@Override
	public PaymentPage getPaymentPageByDistributor(String distributor,
			int thispage, int rowperpage) {
		PaymentPage page = new PaymentPage();
		List<Payment> list = dao.getPaymentPageByDistributor(distributor, (thispage-1)*rowperpage, rowperpage);
		//��ǰҳ����
		page.setList(list);
		//��ǰҳ��
		page.setThispage(thispage);
		//ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//�ܼ�¼��
		int countrow = dao.getCountRowWithDistributor(distributor);
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
	public List<Object[]> getPayDetailsByDist(String distributor) {
		return dao.getPayDetailsByDist(distributor);
	}
	@Override
	public PaymentPage getDeletedPaymentByPage(int thispage, int rowperpage) {
		PaymentPage page = new PaymentPage();
		List<Payment> list = dao.findDeletedPaymentByPage((thispage-1)*rowperpage,rowperpage);
		//��ǰҳ����
		page.setList(list);
		//��ǰҳ��
		page.setThispage(thispage);
		//ÿҳ��¼��
		page.setRowperpage(rowperpage);
		//�ܼ�¼��
		int countrow = dao.getDeletedCountRow();
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

}
