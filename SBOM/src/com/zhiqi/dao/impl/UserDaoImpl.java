package com.zhiqi.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhiqi.bean.User;
import com.zhiqi.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session currentSession(){
        return sessionFactory.openSession();
    }
	@Override
	public User findUserByUserNameAndLoginPassword(String userName, String loginPassword) {
		Session session = null;
		User user = null;
		String sql = "from User where userName=:uuserName and loginPassword=:uloginPassword";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("uuserName", userName);
			query.setParameter("uloginPassword", loginPassword);
			user=	(User)query.uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return user;
	}
	
	@Override
	public User findUserByUserName(String userName) {
		Session session = null;
		User user = null;
		String sql = "from User as u where u.userName=:uuserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("uuserName", userName);
			user = (User)query.uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return user;
	}
	
	@Override
	public void updateNickName(String userName, String nickName) {
		Session session = null;
		String sql ="update User u set u.nickName = :unickName where u.userName = :uuserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("uuserName", userName);
			query.setParameter("unickName", nickName);
			query.executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	@Override
	public void updatePhoneNum(String userName, String phoneNum) {
		Session session = null;
		String sql ="update User u set u.phoneNum = :uphoneNum where u.userName = :uuserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("uuserName", userName);
			query.setParameter("uphoneNum", phoneNum);
			query.executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	@Override
	public void updateEmail(String userName, String email) {
		Session session = null;
		String sql ="update User u set u.email = :uemail where u.userName = :uuserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("uuserName", userName);
			query.setParameter("uemail", email);
			query.executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	@Override
	public void updateBankCode(String userName, String bankCode) {
		Session session = null;
		String sql ="update User u set u.bankCode = :ubankCode where u.userName = :uuserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("uuserName", userName);
			query.setParameter("ubankCode", bankCode);
			query.executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	@Override
	public void updateAddress(String userName, String address) {
		Session session = null;
		String sql ="update User u set u.address = :uaddress where u.userName = :uuserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("uuserName", userName);
			query.setParameter("uaddress", address);
			query.executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	@Override
	public void updateLoginPasswordByUsername(String userName, String password) {
		Session session = null;
		String sql ="update User u set u.loginPassword = :upassword where u.userName = :uuserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("uuserName", userName);
			query.setParameter("upassword", password);
			query.executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	@Override
	public void updatePayPasswordByUsername(String userName, String password) {
		Session session = null;
		String sql ="update User u set u.payPassword = :upassword where u.userName = :uuserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("uuserName", userName);
			query.setParameter("upassword", password);
			query.executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	@Override
	public void update(User user) {
		Session session=null;
        try {
            session=currentSession();
            Transaction tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
	}
	@Override
	public void add(User user) {
		Session session=null;
        try {
            session=currentSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
	}
	@Override
	public User getIdFindByUserInfo(String id) {
		Session session = null;
		User user = null;
		String sql = "from User where id=:uid";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("uid", id);
			user=	(User)query.uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return user;
	}
	

}
