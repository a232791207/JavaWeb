package com.wust.dao;

import java.util.List;

import com.wust.domain.Draworder;
import com.wust.domain.Order;
import com.wust.domain.Payment;
import com.wust.domain.Result;

public interface ResultDao {

	public List<Result> findResultByPage(int from, int count);

	public int getCountRow();

	public Result getResultByDisAndSal(String distributor, String salesman);

	public void addResult(Order order);

	public void updateResult(Order order);
	
	public void addInterest(String distributor, String salesman, String money);

	public void addPayback(String distributor, String salesman, String money);

	public void payUpdateResult(Payment payment);

	public List<Result> findDistributorsByName(String name);

	public void delete(int id);

	public void updateResultBynum(String distributor, String salesman,
			double sumprice);

	public List<Result> getResultPageByDistributor(String distributor, int from,
			int count);

	public int getCountRowWithDistributor(String distributor);

	public void updateResultByNum(List<Draworder> draworders);
}
