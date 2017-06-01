package com.zhiqi.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.zhiqi.bean.DiamondBill;
import com.zhiqi.dao.DiamondBillDao;

public class DiamondBillDaoImpl implements DiamondBillDao {
	private SessionFactory sessionFactory;
	
	public DiamondBillDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session currentSession(){
		return sessionFactory.openSession();
	}

	@Override
	public List<DiamondBill> findDiamondBillPageByTime(int from, int count, String stime, String etime,int state,String userName,String string,String s) {
		Session session = null;
		List<DiamondBill> list = null;
		String sql = "from DiamondBill d where d.time > :stime and d.time < :etime and d.state "+string+" :dstate and d.userName=:duserName and d.comment like :dcomment order by time desc";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("stime", stime);
			query.setParameter("etime", etime);
			query.setParameter("dstate", state);
			query.setParameter("duserName", userName);
			query.setParameter("dcomment", s+"%");
			query.setFirstResult(from);
			query.setMaxResults(count);
			list = (List<DiamondBill>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	@Override
	public int getCountDiamondBillByTime(String stime, String etime,int state,String userName,String string,String s) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from DiamondBill d where d.time > :stime and d.time < :etime and d.state "+string+" :dstate and d.userName=:duserName  and d.comment like :dcomment";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("stime", stime);
			query.setParameter("etime", etime);
			query.setParameter("dstate", state);
			query.setParameter("duserName", userName);
			query.setParameter("dcomment", s+"%");
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
	public List<DiamondBill> findDiamondBillPage(int from, int count,int state,String userName,String string,String s) {
		Session session = null;
		List<DiamondBill> list = null;
		String sql = "from DiamondBill d where d.state "+string+" :dstate and d.userName=:duserName and d.comment like :dcomment order by d.time desc";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("dstate", state);
			query.setParameter("duserName", userName);
			query.setParameter("dcomment", s+"%");
			query.setFirstResult(from);
			query.setMaxResults(count);
			list = (List<DiamondBill>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	@Override
	public int getCountDiamondBill(int state,String userName,String string,String s) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from DiamondBill d where d.state "+string+" :dstate and d.userName=:duserName  and d.comment like :dcomment";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("dstate", state);
			query.setParameter("duserName", userName);
			query.setParameter("dcomment", s+"%");
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
	public int todaysBillNum(String userName,String today) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from DiamondBill d where d.time like :dtime and d.userName = :duserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("dtime", today+"%");
			query.setParameter("duserName", userName);
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
	public void add(DiamondBill diamondBill) {
		Session session = null;
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			session.save(diamondBill);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public List<DiamondBill> getRechargeApply(String stime, String etime, String subUserName) {
		Session session = null;
		List<DiamondBill> list = null;
		String sql = "from DiamondBill d where d.time > :stime , d.time < :etime , d.state = 0 , d.comment like "+"≥‰÷µ%"+" and d.userName=:duserName group by d.time order by d.time desc";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("stime", stime);
			query.setParameter("etime", etime);
			query.setParameter("duserName", subUserName);
			list = (List<DiamondBill>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<DiamondBill> getRechargeApply(String subUserName) {
		Session session = null;
		List<DiamondBill> list = null;
		String sql = "from DiamondBill d where d.state = 0 , d.userName=:duserName and d.comment like "+"≥‰÷µ%"+" group by d.time order by d.time desc";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("duserName", subUserName);
			list = (List<DiamondBill>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	@Override
	public DiamondBill getDiamondBill(String id) {
		Session session = null;
		DiamondBill diamondBill = null;
		String sql = "from DiamondBill as d where d.id=:did";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("did", id);
			diamondBill = (DiamondBill)query.uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return diamondBill;
	}

	@Override
	public void update(DiamondBill diamondBill) {
		Session session=null;
        try {
            session=currentSession();
            Transaction tx = session.beginTransaction();
            session.update(diamondBill);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
	}

	@Override
	public int getApplyingNum(String subUserName) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from DiamondBill d where d.state = 0 and d.userName=:duserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("duserName", subUserName);
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
	public int getDateConsumption(String subUserName, String date, String string) {
		Session session = null;
		int sum = 0;
		String sql = "select sum(d.income) from DiamondBill d where d.time like :dtime and d.comment like :dcomment and d.userName = :duserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("dtime", date+"%");
			query.setParameter("dcomment", string+"%");
			query.setParameter("duserName", subUserName);
			if(query.uniqueResult()==null){
				sum=0;
			}else{
				sum = -((Number)query.uniqueResult()).intValue();
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return sum;
	}
}
