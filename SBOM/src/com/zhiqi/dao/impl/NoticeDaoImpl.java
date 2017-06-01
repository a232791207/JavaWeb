package com.zhiqi.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhiqi.bean.Notice;
import com.zhiqi.dao.NoticeDao;

public class NoticeDaoImpl implements NoticeDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public NoticeDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session currentSession(){
        return sessionFactory.openSession();
    }
	@Override
	public void add(Notice notice) {
		Session session = null;
		try{
			session=currentSession();
			Transaction tx = session.beginTransaction();
			session.save(notice);
			tx.commit();
		} catch (HibernateException e) {
	        e.printStackTrace();
	    }finally{
	        session.close();
	    }
	}
	@Override
	public List<Notice> getNoticePageByState(int from, int count, short state) {
		Session session = null;
		List<Notice> list = null;
		String sql = "from Notice n where n.state = :nstate order by n.time desc";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("nstate", state);
			query.setFirstResult(from);
			query.setMaxResults(count);
			list = (List<Notice>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	@Override
	public int getNoticeCountByState(short state) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from Notice n where n.state = :nstate";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("nstate", state);
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
	public Notice getNotice(int id) {
		Session session = null;
		Notice notice = null;
		String sql = "from Notice n where n.id = :nid";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("nid", id);
			notice = (Notice)query.uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return notice;
	}
	@Override
	public void update(Notice notice) {
		Session session = null;
		try{
			session=currentSession();
			Transaction tx = session.beginTransaction();
			session.update(notice);
			tx.commit();
		} catch (HibernateException e) {
	        e.printStackTrace();
	    }finally{
	        session.close();
	    }
	}

}
