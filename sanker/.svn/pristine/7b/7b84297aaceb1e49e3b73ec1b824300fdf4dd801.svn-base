package com.sanker.service.core;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.sanker.service.utils.Page;

public class HibernateEntityDao<T> extends HibernateDaoSupport {
	@SuppressWarnings("unused")
	private  String defaultPageQL;

	protected Class<T> entityClass;// DAO所管理的Entity类型.

	/**
	 * 在构造函数中将泛型T.class赋给entityClass.
	 */
	@SuppressWarnings("unchecked")
	public HibernateEntityDao() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
		defaultPageQL="FROM "+getEntityClass().getName();
	}

	/**
	 * 取得entityClass.JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果。
	 */
	protected Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * 获取全部对象
	 */
	public List<T> getAll() {
		return getAll(getEntityClass());
	}
	/**
	 * 获取全部对象.
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> getAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}
	/**
	 * 根据ID获取对象.
	 */
	public T get(Serializable id) {
		return get(getEntityClass(), id);
	}
	/**
	 * 根据ID获取对象. 实际调用Hibernate的session.get()方法返回实体或其proxy对象. 如果对象不存在，返回null.
	 */
	@SuppressWarnings("hiding")
	public <T> T get(Class<T> entityClass, Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}
	public T load(Serializable id){
		return (T) this.getHibernateTemplate().load(getEntityClass(), id);
	}
	/**
	 * 取得Entity的Criteria.
	 */
	public Criteria createCriteria(Criterion... criterions) {
		return createCriteria(getEntityClass(), criterions);
	}

	/**
	 * 取得Entity的Criteria,带排序参数.
	 */
	public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {
		return createCriteria(getEntityClass(), orderBy, isAsc, criterions);
	}

	/**
	 * 创建Criteria对象.
	 *
	 * @param criterions 可变的Restrictions条件列表,见{@link #createQuery(String,Object...)}
	 */
	@SuppressWarnings("hiding")
	public <T> Criteria createCriteria(Class<T> entityClass, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 创建Criteria对象，带排序字段与升降序字段.
	 */
	@SuppressWarnings("hiding")
	public <T> Criteria createCriteria(Class<T> entityClass, String orderBy, boolean isAsc, Criterion... criterions) {
		Assert.hasText(orderBy);

		Criteria criteria = createCriteria(entityClass, criterions);

		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));

		return criteria;
	}

	/**
	 * 保存对象.
	 */
	public  T save(T o) {
		checkBeforeSave(o);
		getHibernateTemplate().merge(o);
		return o;
	}
	protected void checkBeforeSave(T o){
		
	}
	/**
	 * 根据ID移除对象.
	 */
	public void removeById(Serializable id) {
		checkBeforeremoveById(id);
		removeById(getEntityClass(), id);
	}
	protected void checkBeforeremoveById(Serializable id){
		
	}
	public void removeByIds(Serializable[] ids){
		checkBeforeremoveByIds(ids);
		StringBuilder deleteHql=new StringBuilder("delete from ")
		.append(this.getEntityClass().getName()).append(" where id in(");
		for(int i=0;i<ids.length;i++){
			deleteHql.append(ids[i]);
			if(i!=ids.length-1)
				deleteHql.append(",");
		}
		
		deleteHql.append(")");
		
		logger.info("delete hql:"+deleteHql.toString());		
		this.getHibernateTemplate().bulkUpdate(deleteHql.toString());
	}
	protected void checkBeforeremoveByIds(Serializable[] ids){
		
	}
	/**
	 * 删除对象.
	 */
	public void remove(Object o) {
		getHibernateTemplate().delete(o);
	}

	/**
	 * 根据ID删除对象.
	 */
	@SuppressWarnings("hiding")
	public <T> void removeById(Class<T> entityClass, Serializable id) {
		remove(get(entityClass, id));
	}
	public void flush() {
		getHibernateTemplate().flush();
	}

	public void clear() {
		getHibernateTemplate().clear();
	}

	/**
	 * 创建Query对象. 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * 留意可以连续设置,如下：
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 调用方式如下：
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 *
	 * @param values 可变参数.
	 */
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		if(values==null||values.length==0)
			return query;
		
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}
	
	/**
	 * 分页查询函数，使用hql.
	 *
	 * @param startNo 行号,从0开始.
	 */
	@SuppressWarnings("rawtypes")
	public Page pagedQuery(String hql, int startNo, int pageSize, Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(startNo >= 0, "startNo should start from 0");
		// Count查询
		String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
		List countlist = getHibernateTemplate().find(countQueryString, values);
		long totalCount = (Long) countlist.get(0);

		if (totalCount < 1)
			return new Page();

		Query query = createQuery(hql, values);
		List list = query.setFirstResult(startNo).setMaxResults(pageSize).list();

		return new Page(startNo, totalCount, pageSize, list);
	}

	/**
	 * 分页查询函数，使用已设好查询条件与排序的<code>Criteria</code>.
	 *
	 * @param startNo 行号,从0开始.
	 * @return 含总记录数和当前页数据的Page对象.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page pagedQuery(Criteria criteria, int startNo, int pageSize) {

		Assert.notNull(criteria);
		Assert.isTrue(startNo >= 0, "startNo should start from 0");
		CriteriaImpl impl = (CriteriaImpl) criteria;

		// 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
		Projection projection = impl.getProjection();
		List<CriteriaImpl.OrderEntry> orderEntries;
		try {
			orderEntries = (List) BeanUtils.forceGetProperty(impl, "orderEntries");
			BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		// 执行查询
		long totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();

		// 将之前的Projection和OrderBy条件重新设回去
		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}

		try {
			BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		// 返回分页对象
		if (totalCount < 1)
			return new Page();

		
		List list = criteria.setFirstResult(startNo).setMaxResults(pageSize).list();
		return new Page(startNo, totalCount, pageSize, list);
	}

	/**
	 * 分页查询函数，根据entityClass和查询条件参数创建默认的<code>Criteria</code>.
	 *
	 * @param startNo 行号,从0开始.
	 * @return 含总记录数和当前页数据的Page对象.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page pagedQuery(Class entityClass, int startNo, int pageSize, Criterion... criterions) {
		Criteria criteria = createCriteria(entityClass, criterions);
		return pagedQuery(criteria, startNo, pageSize);
	}

	/**
	 * 分页查询函数，根据entityClass和查询条件参数,排序参数创建默认的<code>Criteria</code>.
	 *
	 * @param startNo 行号,从0开始.
	 * @return 含总记录数和当前页数据的Page对象.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page pagedQuery(Class entityClass, int startNo, int pageSize, String orderBy, boolean isAsc,
						   Criterion... criterions) {
		Criteria criteria = createCriteria(entityClass, orderBy, isAsc, criterions);
		return pagedQuery(criteria, startNo, pageSize);
	}
	
	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	@SuppressWarnings({ "hiding", "unchecked" })
	public <T> T getByEager(Serializable id,String... eagerProperties){
		String hql="FROM "+this.getEntityClass().getName()+" AS a ";
		for(String p:eagerProperties){
			hql+="left join fetch a."+p+" "; 
		}
		hql+="WHERE a.id="+id;
		List<T> list=this.getHibernateTemplate().find(hql);
		return (T)(list.size()>0?list.get(0):null);
	}
	
	@SuppressWarnings("rawtypes")
	public Class getIdType(Class entityClass){
		Class idClass = null;
		String idName = getSessionFactory().getClassMetadata(entityClass).getIdentifierPropertyName();
		try {
		    Field field = entityClass.getDeclaredField(idName);
		    idClass = field.getType();
		} catch (SecurityException e) {
		    
		} catch (NoSuchFieldException e) {
		    
		}
		return idClass;
	}

}
