package com.lhx.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lhx.common.QueryParam;
import com.lhx.dao.BaseDao;
import com.lhx.util.StringUtils;

/**
 * DAO层操作基类
 * @author hp
 *
 * @param <T> 操作实体
 * @param <PK> 实体主键类型
 */
@SuppressWarnings("unchecked")
@Transactional
@Repository(value="baseDao")//Repository 数据层面的自动spring自动注册 林浩旋
public class BaseDaoImpl <T, PK extends Serializable> implements BaseDao<T, PK> {
	private static final int BATCH_UPDATE_SIZE = 20;
	private Class<T> entityClass;
	
	@Autowired
	protected SessionFactory sessionFactory;
	

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void setClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public BaseDaoImpl() {
		this.entityClass = null;
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}
	
	
	
	/**
	 * 
	 * @param params
	 *            参数对，String，Object
	 * @param cache
	 *            是否使用缓存
	 * @return
	 */
	public int getTotalCount(QueryParam params, boolean cache) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		
		if(params != null){
			int length = params.getLength();
			for (int i = 0; i < length; i++) {
				String key = params.getKey(i);
				if(key == null || key.isEmpty()){
					continue;
				}
				char spe = key.charAt(0);
				switch (spe) {
				case '!':
					criteria.add(Restrictions.ne(key.substring(1), params.getValue(i))); 
					break;
				case '=':
					criteria.add(Restrictions.eq(key.substring(1), params.getValue(i))); 
					break;
				case '>':
					if(params.getKey(i).charAt(1) == '='){
						criteria.add(Restrictions.ge(key.substring(2), params.getValue(i))); 
					}else{
						criteria.add(Restrictions.gt(key.substring(1), params.getValue(i))); 
					}
					break;
				case '<':
					if(params.getKey(i).charAt(1) == '='){
						criteria.add(Restrictions.le(key.substring(2), params.getValue(i))); 
					}else{
						criteria.add(Restrictions.lt(key.substring(1), params.getValue(i))); 
					}
					break;
				case '~':
					criteria.add(Restrictions.like(key.substring(1), String.valueOf(params.getValue(i)), MatchMode.ANYWHERE)); 
					break;
				default:
					criteria.add(Restrictions.eq(key, params.getValue(i))); 
					break;
				}
			}
		}
		return (Integer) criteria.setProjection(Projections.rowCount())
				.setCacheable(cache)
				.uniqueResult();
	}
	
	/**
	 * 林浩旋 2015年2月3日 16:07:19
	 * @param params
	 *            参数对，String，Object
	 * @param start
	 *            分页开始
	 * @param limit
	 *            分页长度
	 * @param orderbyparam
	 *            排序参数
	 * @param orderby
	 *            排序类型，desc，asc
	 * @param cache
	 *            是否加缓存
	 * @return
	 */
	public List<T> getList(QueryParam params, int start, int limit, String orderbyparam, String orderby, boolean cache) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		limit = limit > 0 ? limit : 1000000;
		if(params!=null)
		{
			int length = params.getLength();
			for (int i = 0; i < length; i++) {
				String key = params.getKey(i);
				if(key == null || key.isEmpty()){
					continue;
				}
				char spe = key.charAt(0);
				switch (spe) {
				case '!':
					criteria.add(Restrictions.ne(key.substring(1), params.getValue(i))); 
					break;
				case '=':
					criteria.add(Restrictions.eq(key.substring(1), params.getValue(i))); 
					break;
				case '>':
					if(params.getKey(i).charAt(1) == '='){
						criteria.add(Restrictions.ge(key.substring(2), params.getValue(i))); 
					}else{
						criteria.add(Restrictions.gt(key.substring(1), params.getValue(i))); 
					}
					break;
				case '<':
					if(params.getKey(i).charAt(1) == '='){
						criteria.add(Restrictions.le(key.substring(2), params.getValue(i))); 
					}else{
						criteria.add(Restrictions.lt(key.substring(1), params.getValue(i))); 
					}
					break;
				case '~':
					criteria.add(Restrictions.like(key.substring(1), String.valueOf(params.getValue(i)), MatchMode.ANYWHERE)); 
					break;
				default:
					criteria.add(Restrictions.eq(key, params.getValue(i))); 
					break;
				}
			}
		}
		if (StringUtils.isNotEmpty(orderby) && StringUtils.isNotEmpty(orderbyparam)) {
			String[] ps = org.springframework.util.StringUtils.tokenizeToStringArray(orderbyparam, ",");
			String[] ods = org.springframework.util.StringUtils.tokenizeToStringArray(orderby, ",");
			if(ps != null && ods != null && ps.length == ods.length){
				for(int i = 0; i < ps.length; i++){
					if("desc".equalsIgnoreCase(ods[i])){
						criteria.addOrder(Order.desc(ps[i]));
					}else{
						criteria.addOrder(Order.asc(ps[i]));
					}
				}
			}
		}
		criteria.setCacheable(cache);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		return criteria.list();
	}
	

	
	

	//***************************************
	//*************更新后方法*****************
	//***************************************
	
	/**
	 * 数据库新增
	 * @param entity 新增的实体
	 */
	public PK save(T entity) {
		Assert.notNull(entity, "entity is required");
		return (PK) getSession().save(entity);
	}

	/**
	 * 数据库更新，必须为持久化实体
	 * @param entity 更新的实体
	 */
	public void update(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().update(entity);
	}
	
	/**
	 * 数据库删除，必须为持久化实体
	 * @param entity 删除的实体
	 */
	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
	}
	
	/**
	 * 数据库删除
	 * @param ids 删除的主键集合
	 */
	public void delete(PK... ids){
		Assert.notEmpty(ids, "ids must not be empty");
		System.out.println(sessionFactory.getClassMetadata(entityClass));
		String pk = sessionFactory.getClassMetadata(entityClass).getIdentifierPropertyName();
		String hql = "DELETE FROM " + entityClass.getName() + " WHERE " + pk + " IN (:ids)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		query.executeUpdate();
	}
	
	/**
	 * 数据库删除
	 * @param hql ：where后的sql语句
	 */
	public void delete(String hql){
		String hqlstr = "DELETE FROM " + entityClass.getName() + " WHERE " + hql;
		Query query = getSession().createQuery(hqlstr);
		query.executeUpdate();
	}
	
	/**
	 * 数据库查询
	 * @param id 查询的主键
	 * @return
	 */
	public T get(PK id){
		Assert.notNull(id, "id is required");
		String pk = sessionFactory.getClassMetadata(entityClass).getIdentifierPropertyName();
		return get(pk, id, false);
	}
	
	/**
	 * 数据库查询
	 * @param propertyName 对象属性
	 * @param value 忏悔值
	 * @return
	 */
	public T get(String propertyName, Object value, boolean cache) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		Object o = getSession().createQuery(hql).setParameter(0, value).setCacheable(cache).uniqueResult();
		if(o == null)
		{
			return null;
		}
		return (T) o;
	}
	
	
	
	/**
	 * 数据库查询
	 * @param id 查询的主键
	 * @return
	 */
	public T load(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().load(entityClass, id);
	}
	
	/**
	 * 数据库查询
	 * @param id 查询的主键集合
	 * @return
	 */
	public List<T> get(boolean cache, PK... ids){
		Assert.notEmpty(ids, "ids must not be empty");
		String pk = sessionFactory.getClassMetadata(entityClass).getIdentifierPropertyName();
		String hql = "FROM " + entityClass.getName() + " WHERE " + pk + " IN (:ids)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		query.setCacheable(cache);
		return query.list();
	}
	
	/**
	 * 新增或更新
	 * @param entity 更新的实体
	 */
	public void saveOrUpdate(T entity){
		Assert.notNull(entity, "entity is required");
		getSession().saveOrUpdate(entity);
	}
	
	/**
	 * 批量新增
	 * @param entitys 新增的实体
	 */
	public void saveBatch(List<T> entitys){
		if(entitys != null && entitys.size() > 0){
			for(int i = 0; i < entitys.size(); i++){
				save(entitys.get(i));
				if(i % BATCH_UPDATE_SIZE == 0){
					getSession().flush();
					getSession().clear();
				}
			}
		}
	}
	
	/**
	 * 批量更新
	 * @param entitys 更新的实体
	 */
	public void updateBatch(List<T> entitys){
		if(entitys != null && entitys.size() > 0){
			for(int i = 0; i < entitys.size(); i++){
				update(entitys.get(i));
				if(i % BATCH_UPDATE_SIZE == 0){
					getSession().flush();
					getSession().clear();
				}
			}
		}
	}
	
	/**
	 * 批量删除
	 * @param entitys 删除的实体
	 */
	public void deleteBatch(List<T> entitys){
		if(entitys != null && entitys.size() > 0){
			for(int i = 0; i < entitys.size(); i++){
				delete(entitys.get(i));
				if(i % BATCH_UPDATE_SIZE == 0){
					getSession().flush();
					getSession().clear();
				}
			}
		}
	}
	
	/**
	 * 批量新增或更新
	 * @param entitys 更新的实体
	 */
	public void saveOrUpdateBatch(List<T> entitys){
		if(entitys != null && entitys.size() > 0){
			for(int i = 0; i < entitys.size(); i++){
				saveOrUpdate(entitys.get(i));
				if(i % BATCH_UPDATE_SIZE == 0){
					getSession().flush();
					getSession().clear();
				}
			}
		}
	}
	
	/**
	 * 数据库更新
	 * @param hql HQL语句
	 * @param params HQL语句参数，用点位符？表示
	 * @return
	 */
	public int update(String hql, Object... params){
		Query query = getSession().createQuery(hql);
		if(params != null && params.length > 0){
			for(int i = 0; i < params.length; i++){
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}
	
	/**
	 * 数据库查询
	 * @param sql SQL语句
	 * @param params SQL语句参数，用点位符？表示
	 * @return
	 */
	public List<Map<String, Object>> getSql(boolean cache, Integer pageNo, Integer pageSize, String sql, Object... params){
		Query query = getSession().createSQLQuery(sql);
		if(params != null && params.length > 0){
			for(int i = 0; i < params.length; i++){
				query.setParameter(i, params[i]);
			}
		}
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		query.setCacheable(cache);
		if(pageNo==null || pageSize==null){
			return query.list();
		}else{
			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		}
	}
	
	/**
	 * 数据库查询
	 * @param pageNo 页码
	 * @param pageSize 页大小
	 * @param criteria Hibernate查询对象
	 * @return
	 */
	public List<T> getList(boolean cache, Integer pageNo, Integer pageSize, DetachedCriteria criteria){
		if(pageNo == null || pageSize == null){
			return criteria.getExecutableCriteria(getSession())
				.setCacheable(cache)
				.list();
		}else{
			return criteria.getExecutableCriteria(getSession())
				.setCacheable(cache)
				.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize)
				.list();
		}
	}
	
	/**
	 * 数据库查询
	 * @param pageNo 页码
	 * @param pageSize 页大小
	 * @param criteria Hibernate查询对象
	 * @return
	 */
	 public List<Map<String, Object>> getListByAglie(boolean cache, Integer pageNo, Integer pageSize, DetachedCriteria criteria) {
		 criteria.setResultTransformer(DetachedCriteria.ALIAS_TO_ENTITY_MAP);
		 if (pageNo == null || pageSize == null) {
			return criteria.getExecutableCriteria(getSession()).setCacheable(cache).list();
		} else {
			return criteria.getExecutableCriteria(getSession()).setCacheable(cache)
					.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
		}
	}
	
	/**
	 * 数据库查询
	 * @param pageNo 页码
	 * @param pageSize 页大小
	 * @param hql HQL语句
	 * @param params HQL语句参数，用点位符？表示
	 * @return
	 */
	public List<T> getList(boolean cache, Integer pageNo, Integer pageSize, String hql, Object... params){
		Query query = getSession().createQuery(hql);
		query.setCacheable(cache);
		if(params != null && params.length > 0){
			for(int i = 0; i < params.length; i++){
				query.setParameter(i, params[i]);
			}
		}
		if(pageNo==null || pageSize==null){
			return query.list();
		}else{
			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		}
	}
	
	
	
	
	/**
	 * 数据库行统计
	 * @param hql hql HQL语句
	 * @param params HQL语句参数，用点位符？表示
	 * @return
	 */
	public int getCount(boolean cache, String hql, Object... params){
		Query query = getSession().createQuery(hql);
		if(params != null && params.length > 0){
			for(int i = 0; i < params.length; i++){
				query.setParameter(i, params[i]);
			}
		}
		query.setCacheable(cache);
		Object result = query.uniqueResult();
		if(result != null) {
			return Integer.parseInt(result.toString());
		} else {
			return 0;
		}
		
	}
	
	/**
	 * 刷新缓存并执行数据库更新操作
	 */
	public void flush(){
		getSession().flush();
	}
	
	/**
	 * 清除缓存
	 */
	public void clear(){
		getSession().clear();
	}
	
	public Object merge(T entity) {
		// TODO Auto-generated method stub
		return this.merge(entity);
	}
	
}
