package com.wust.service;

import java.util.List;

import com.wust.domain.Draworder;
import com.wust.domain.Order;
import com.wust.domain.Payment;
import com.wust.domain.Result;
import com.wust.domain.ResultPage;

public interface ResultService {

	public ResultPage getResultByPage(int thispage, int rowperpage);

	public Result getResultByDisAndSal(String distributor, String salesman);

	public void addResult(Order order);

	public void updateResult(Order order);
	

	public void addInterest(String distributor, String salesman, String money);

	public void addPayback(String distributor, String salesman, String money);

	public void payUpdateResult(Payment payment);

	public List<Result> findDistributorsByName(String name);

	public void deleteResult(int id);

	public void updateResultByNum(String distributor, String salesman,
			double sumprice);

	public ResultPage getResultPageByDistributor(String distributor,
			int thispage, int rowperpage);

	public void updateResultByNum(List<Draworder> draworders);

}
