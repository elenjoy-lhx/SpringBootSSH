package com.lhx.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.lhx.common.QueryParam;

/**
 * DAO层操作基类
 * @author hp
 *
 * @param <T> 操作实体
 * @param <PK> 实体主键类型
 */

public interface BaseDao <T, PK extends Serializable>  {
	
	void setClass(Class<T> entityClass);
	/**
	 * 	 * @param params
	 *            参数对，String，Object
	 * @param cache
	 *            是否使用缓存
	 * @return
	 */
	int getTotalCount(QueryParam params, boolean cache);
	
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
	List<T> getList(QueryParam params, int start, int limit, String orderbyparam, String orderby, boolean cache);
	


	
	
	
	//***************************************
	//*************更新后方法*****************
	//***************************************
	
	/**
	 * 数据库新增
	 * @param entity 新增的实体
	 */
	PK save(T entity);
	/**
	 * 数据库更新，必须为持久化实体
	 * @param entity 更新的实体
	 */
	void update(T entity);
	/**
	 * 数据库删除，必须为持久化实体
	 * @param entity 删除的实体
	 */
	void delete(T entity);
	/**
	 * 数据库删除
	 * @param ids 删除的主键集合
	 */
	void delete(PK... ids);
	/**
	 * 数据库删除
	 * @param hql 填where后面的条件（不包含where关键字）
	 */
	void delete(String hql);
	/**
	 * 数据库查询
	 * @param id 查询的主键
	 * @return
	 */
	T get(PK id);
	/**
	 * 数据库查询
	 * @param propertyName 对象属性
	 * @param value 属性值
	 * @return
	 */
	T get(String propertyName, Object value, boolean cache);

	/**
	 * 数据库查询
	 * @param id 查询的主键
	 * @return
	 */
	T load(PK id);
	/**
	 * 数据库查询
	 * @param id 查询的主键集合
	 * @return
	 */
	List<T> get(boolean cache, PK... ids);
	/**
	 * 新增或更新
	 * @param entity 更新的实体
	 */
	void saveOrUpdate(T entity);
	/**
	 * 批量新增
	 * @param entitys 新增的实体
	 */
	void saveBatch(List<T> entitys);
	/**
	 * 批量更新
	 * @param entitys 更新的实体
	 */
	void updateBatch(List<T> entitys);
	/**
	 * 批量删除
	 * @param entitys 删除的实体
	 */
	void deleteBatch(List<T> entitys);
	/**
	 * 批量新增或更新
	 * @param entitys 更新的实体
	 */
	void saveOrUpdateBatch(List<T> entitys);
	/**
	 * 数据库更新
	 * @param hql HQL语句
	 * @param params HQL语句参数，用点位符？表示
	 * @return
	 */
	int update(String hql, Object... params);
	/**
	 * 数据库查询
	 * @param sql SQL语句
	 * @param params SQL语句参数，用点位符？表示
	 * @return
	 */
	List<Map<String, Object>> getSql(boolean cache, Integer pageNo, Integer pageSize, String sql, Object... params);
	/**
	 * 数据库查询
	 * @param pageNo 页码
	 * @param pageSize 页大小
	 * @param criteria Hibernate查询对象
	 * @return
	 */
	List<T> getList(boolean cache, Integer pageNo, Integer pageSize, DetachedCriteria criteria);
	/**
	 * 数据库查询
	 * @param pageNo 页码
	 * @param pageSize 页大小
	 * @param criteria Hibernate查询对象
	 * @return
	 */
	List<Map<String, Object>> getListByAglie(boolean cache, Integer pageNo, Integer pageSize, DetachedCriteria criteria);
	/**
	 * 数据库查询
	 * @param pageNo 页码
	 * @param pageSize 页大小
	 * @param hql HQL语句
	 * @param params HQL语句参数，用点位符？表示
	 * @return
	 */
	List<T> getList(boolean cache, Integer pageNo, Integer pageSize, String hql, Object... params);
	
	/**
	 * 数据库行统计
	 * @param hql hql HQL语句
	 * @param params HQL语句参数，用点位符？表示
	 * @return
	 */
	int getCount(boolean cache, String hql, Object... params);
	/**
	 * 刷新缓存并执行数据库更新操作
	 */
	void flush();
	/**
	 * 清除缓存
	 */
	void clear();
	
	Object merge(T entity);
}
