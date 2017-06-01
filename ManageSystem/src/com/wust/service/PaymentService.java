package com.wust.service;

import java.util.List;

import com.wust.domain.Payment;
import com.wust.domain.PaymentPage;

public interface PaymentService {

	public PaymentPage getPaymentByPage(int thispage, int rowperpage);

	public void addPayment(Payment payment);
	public void deletePayment(int id);

	public Payment getPaymentById(int id);

	public PaymentPage getPaymentPageByDistributor(String distributor,
			int thispage, int rowperpage);

	public List<Object[]> getPayDetailsByDist(String distributor);

	public PaymentPage getDeletedPaymentByPage(int thispage, int rowperpage);


}
