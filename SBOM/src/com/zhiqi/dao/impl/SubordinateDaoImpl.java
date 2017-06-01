package com.zhiqi.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.zhiqi.bean.Subordinate;
import com.zhiqi.dao.SubordinateDao;

public class SubordinateDaoImpl implements SubordinateDao {
	
private SessionFactory sessionFactory;
	
	public SubordinateDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session currentSession(){
        return sessionFactory.openSession();
    }
	@Override
	public int getSumNum(String supUserName) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from Subordinate s where s.supUserName = :ssupUserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssupUserName", supUserName);
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
	public int getTodayActive(String supUserName, String today) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from Subordinate s where s.supUserName = :ssupUserName and s.recentTime like :srecentTime";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssupUserName", supUserName);
			query.setParameter("srecentTime", today+"%");
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
	public int getNewIncrease(String supUserName, String today) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from Subordinate s where s.supUserName = :ssupUserName and s.registTime like :sregistTime";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssupUserName", supUserName);
			query.setParameter("sregistTime", today);
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
	public List<Subordinate> findDiamondBillPageByTime(int from, int count, String supUserName, String date,short subLevel) {
		Session session = null;
		List<Subordinate> list = null;
		String sql = "from Subordinate s where s.recentTime < :date and s.supUserName = :ssupUserName and s.subLevel=:ssubLevel";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssupUserName", supUserName);
			query.setParameter("date", date);
			query.setParameter("ssubLevel", subLevel);
			query.setFirstResult(from);
			query.setMaxResults(count);
			list = (List<Subordinate>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	@Override
	public int getCountDiamondBillByTime(String supUserName, String date) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from Subordinate s where s.recentTime < :date and s.supUserName = :ssupUserName ";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssupUserName", supUserName);
			query.setParameter("date", date);
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
	public List<Subordinate> getSubordinateByID(String subUserName,String supUserName,short subLevel) {
		Session session = null;
		List<Subordinate> list = null;
		String sql = "from Subordinate s where s.subUserName like :ssubUserName and s.supUserName = :ssupUserName and s.subLevel=:ssubLevel";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssubUserName", "%"+subUserName+"%");
			query.setParameter("ssupUserName", supUserName);
			query.setParameter("ssubLevel", subLevel);
			list = (List<Subordinate>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<Subordinate> getNoConsumption(String supUserName,short subLevel) {
		Session session = null;
		List<Subordinate> list = null;
		String sql = "from Subordinate s where s.sumConsumption = 0 and s.supUserName = :ssupUserName and s.subLevel=:ssubLevel";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssupUserName", supUserName);
			query.setParameter("ssubLevel", subLevel);
			list = (List<Subordinate>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<String> getSubUserNameList(String supUserName) {
		Session session = null;
		List<String> list = null;
		String sql = "select s.subUserName from Subordinate s where s.supUserName = :ssupUserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssupUserName", supUserName);
			list = (List<String>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	@Override
	public void delete(String subUserName) {
		Session session=null;
        try {
            session=currentSession();
            Transaction tx = session.beginTransaction();
            Subordinate mb = (Subordinate)session.get(Subordinate.class, subUserName);
            session.delete(mb);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
	}
	@Override
	public Subordinate getSubordinateByUserName(String subUserName) {
		Session session = null;
		Subordinate subordinate = null;
		String sql = "from Subordinate s where s.subUserName = :ssubUserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssubUserName", subUserName);
			subordinate = (Subordinate)query.uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return subordinate;
	}
	@Override
	public void update(Subordinate subordinate) {
		Session session = null;
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			session.update(subordinate);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	@Override
	public void add(Subordinate subordinate) {
		Session session = null;
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			session.save(subordinate);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	@Override
	public List<Subordinate> getAllUserPage(int from, int count) {
		Session session = null;
		List<Subordinate> list = null;
		String sql = "from Subordinate s order by s.subLevel desc";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setFirstResult(from);
			query.setMaxResults(count);
			list = (List<Subordinate>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	@Override
	public int getAllUserCount() {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from Subordinate";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
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
	public List<Subordinate> getSubUserPage(int from, int count, String supUserName) {
		Session session = null;
		List<Subordinate> list = null;
		String sql = "from Subordinate s where s.supUserName = :ssupUserName order by s.subLevel desc";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssupUserName", supUserName);
			query.setFirstResult(from);
			query.setMaxResults(count);
			list = (List<Subordinate>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	@Override
	public int getSubUserCount(String supUserName) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from Subordinate s where s.supUserName = :ssupUserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssupUserName", supUserName);
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
	public int getLevelUserCount(short subLevel) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from Subordinate s where s.subLevel = :ssubLevel";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssubLevel", subLevel);
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
	public List<Subordinate> getLevelUserPage(int from, int count, short subLevel) {
		Session session = null;
		List<Subordinate> list = null;
		String sql = "from Subordinate s where s.subLevel = :ssubLevel order by s.subLevel desc";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssubLevel", subLevel);
			query.setFirstResult(from);
			query.setMaxResults(count);
			list = (List<Subordinate>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<Subordinate> getUserPageById(int from, int count, String subUserName) {
		Session session = null;
		List<Subordinate> list = null;
		String sql = "from Subordinate s where s.subUserName like :ssubUserName order by s.subLevel desc";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssubUserName", "%"+subUserName+"%");
			query.setFirstResult(from);
			query.setMaxResults(count);
			list = (List<Subordinate>)query.list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	@Override
	public int getUserCountById(String subUserName) {
		Session session = null;
		int count = 0;
		String sql = "select count(*) from Subordinate s where s.subUserName like :ssubUserName";
		try{
			session = currentSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("ssubUserName", "%"+subUserName+"%");
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
