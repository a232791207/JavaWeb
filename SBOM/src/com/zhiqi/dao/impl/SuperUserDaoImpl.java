package com.zhiqi.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhiqi.bean.SuperUser;

import com.zhiqi.dao.SuperUserDao;
@Repository
public class SuperUserDaoImpl implements SuperUserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public SuperUserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session currentSession(){
        return sessionFactory.openSession();
    }
	@Override
	public SuperUser findByUserName(String userName, String loginPassword) {
		Session session = null;
		SuperUser user = null;
		String sql = "from SuperUser  where userName=:uuserName and loginPassword=:uloginPassword";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("uuserName", userName);
			query.setParameter("uloginPassword", loginPassword);
			user = (SuperUser)query.uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return user;
	}

}
