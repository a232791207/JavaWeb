package com.zhiqi.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.zhiqi.bean.NewsInfo;
import com.zhiqi.dao.NewsInfoDao;

public class NewsInfoDaoImpl implements NewsInfoDao {
	private SessionFactory sessionFactory;
	
	public NewsInfoDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session currentSession(){
        return sessionFactory.openSession();
    }
	@Override
	public void update(NewsInfo newsInfo) {
		Session session = null;
		try{
			session=currentSession(); 
			Transaction tx = session.beginTransaction();
			session.update(newsInfo);
			tx.commit();
		} catch (HibernateException e) {
	        e.printStackTrace();
	    }finally{
	        session.close();
	    }
	}
	@Override
	public NewsInfo getNewsInfo(int id) {
		Session session = null;
		NewsInfo newsInfo = null;
		String sql = "from NewsInfo as n where n.id=:nid";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("nid", id);
			newsInfo = (NewsInfo)query.uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return newsInfo;
	}
	@Override
	public NewsInfo getByState(int id,short state) {
		Session session = null;
		NewsInfo newsInfo = null;
		String sql = "from NewsInfo where id=:uid and state=:ustate";
		try {
			session=currentSession();
			Query query=session.createQuery(sql);
			query.setParameter("uid", id);
			query.setParameter("ustate", state);
			newsInfo=(NewsInfo) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return newsInfo;
	}
	
}
