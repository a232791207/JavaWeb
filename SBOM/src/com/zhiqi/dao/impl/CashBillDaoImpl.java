package com.zhiqi.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.zhiqi.bean.CashBill;
import com.zhiqi.dao.CashBillDao;

public class CashBillDaoImpl implements CashBillDao {
	private SessionFactory sessionFactory;
	
	public CashBillDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session currentSession(){
        return sessionFactory.openSession();
    }
	@Override
	public List<CashBill> findCashBillPageByTime(int from, int count, String stime, String etime,String userName) {
		Session session = null;
		List<CashBill> list = null;
		String sql = "from CashBill c where c.time > :stime and c.time < :etime and c.userName = :cuserName order by time desc";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("stime", stime);
			query.setParameter("etime", etime);
			query.setParameter("cuserName", userName);
			query.setFirstResult(from);
			query.setMaxResults(count);
			list = (List<CashBill>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	@Override
	public int getCountCashBillByTime(String stime, String etime,String userName) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from CashBill c where c.time > :stime and c.time < :etime and c.userName = :cuserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("stime", stime);
			query.setParameter("etime", etime);
			query.setParameter("cuserName", userName);
			count = Integer.parseInt(query.list().get(0).toString());
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return count;
	}
	@Override
	public List<CashBill> findCashBillPage(int from, int count,String userName) {
		Session session = null;
		List<CashBill> list = null;
		String sql = "from CashBill c where c.userName = :cuserName order by time desc";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("cuserName", userName);
			query.setFirstResult(from);
			query.setMaxResults(count);
			list = (List<CashBill>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	@Override
	public int getCountCashBill(String userName) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from CashBill c where c.userName = :cuserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("cuserName", userName);
			count = Integer.parseInt(query.list().get(0).toString());
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return count;
	}
	@Override
	public void add(CashBill cashBill) {
		Session session=null;
        try {
            session=currentSession();
            Transaction tx = session.beginTransaction();
            session.save(cashBill);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
	}
	@Override
	public int todaysBillNum(String userName, String currentDate2) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from CashBill c where c.time like :ctime";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ctime", currentDate2+"%");
			count = Integer.parseInt(query.list().get(0).toString());
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return count;
	}
}
