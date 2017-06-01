package com.zhiqi.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhiqi.bean.Cooperater;
import com.zhiqi.bean.SuperUser;
import com.zhiqi.dao.CooperaterDao;

@Repository
public class CooperaterDaoImpl implements CooperaterDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public CooperaterDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session currentSession(){
        return sessionFactory.openSession();
    }
	@Override
	public Cooperater findByUserName(String userName, String loginPassword) {
		Session session = null;
		Cooperater coo = null;
		String sql = "from Cooperater where userName=:uuserName and loginPassword=:uloginPassword";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("uuserName", userName);
			query.setParameter("uloginPassword", loginPassword);
			coo = (Cooperater)query.uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return coo;
	}

}
