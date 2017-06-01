package com.wust.dao;

import java.util.List;

import com.wust.domain.Payment;

public interface PaymentDao {

	public List<Payment> findPaymentByPage(int from, int count);

	public int getCountRow();

	public void addPayment(Payment payment);

	public void delete(int id);

	public Payment getPaymentById(int id);

	public List<Payment> getPaymentPageByDistributor(String distributor, int from,
			int count);

	public int getCountRowWithDistributor(String distributor);

	public List<Object[]> getPayDetailsByDist(String distributor);

	public int getDeletedCountRow();

	public List<Payment> findDeletedPaymentByPage(int from, int count);

}
