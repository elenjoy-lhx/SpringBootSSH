package com.lhx.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhx.common.QueryParam;
import com.lhx.dao.BaseDao;
import com.lhx.service.BaseService;
import com.lhx.util.Group;
import com.lhx.util.Order;
import com.lhx.util.Order.OrderMode;
import com.lhx.util.PageSet;
import com.lhx.util.Param;
import com.lhx.util.Param.MatchMode;
import com.lhx.util.ParamMapper;
import com.lhx.util.ParamMapper.GroupMatchMode;

/**
 * SERVICE层操作基类
 * @author hp
 *
 * @param <T> 操作实体
 * @param <PK> 实体主键类型
 */
@Transactional
@SuppressWarnings("unchecked")
//@Service(value = "baseServiceImpl")
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {
	
	
	private BaseDao<T, PK> baseDao;
	private Class<T> entityClass;
	
	
	public BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}
	
	@Autowired	//注入baseDao的实现类，也只能注册实现类
	public void setBaseDao(BaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			entityClass = (Class)parameterizedType[0];
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
		return baseDao.getTotalCount(params, cache);
	}
	
	/**
	 * 林浩旋 2017年2月3日 16:07:19
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
		return baseDao.getList(params, start, limit, orderbyparam, orderby, cache);
	}
	
	
	
	//***************************************
	//*************更新后方法*****************
	//***************************************
	
	/**
	 * 数据库新增
	 * @param entity 新增的实体
	 */
	public PK save(T entity) {
		return baseDao.save(entity);
	}
	
	/**
	 * 数据库更新，必须为持久化实体
	 * @param entity 更新的实体
	 */
	public void update(T entity) {
		baseDao.update(entity);
	}
	
	/**
	 * 数据库删除，必须为持久化实体
	 * @param entity 删除的实体
	 */
	public void delete(T entity) {
		baseDao.delete(entity);
	}
	
	/**
	 * 数据库删除
	 * @param ids 删除的主键集合
	 */
	public void delete(PK... ids){
		baseDao.delete(ids);
	}
	/**
	 * 数据库删除
	 * @param hql 填where后面的条件（不包含where关键字）
	 */
	public void delete(String hql){
		baseDao.delete(hql);
	}
	/**
	 * 数据库查询
	 * @param id 查询的主键
	 * @return
	 */
	public T get(PK id){
		return baseDao.get(id);
	}
	
	/**
	 * 数据库查询
	 * @param propertyName 对象属性
	 * @param value 忏悔值
	 * @return
	 */
	public T get(String propertyName, Object value, boolean cache) {
		return baseDao.get(propertyName, value, cache);
	}
	

	
	/**
	 * 数据库查询
	 * @param id 查询的主键
	 * @return
	 */
	public T load(PK id) {
		return baseDao.load(id);
	}
	
	/**
	 * 数据库查询
	 * @param id 查询的主键集合
	 * @return
	 */
	public List<T> get(boolean cache, PK... ids){
		return baseDao.get(cache, ids);
	}
	
	/**
	 * 新增或更新
	 * @param entity 更新的实体
	 */
	public void saveOrUpdate(T entity){
		baseDao.saveOrUpdate(entity);
	}
	
	/**
	 * 批量新增
	 * @param entitys 新增的实体
	 */
	public void saveBatch(List<T> entitys){
		baseDao.saveBatch(entitys);
	}
	
	/**
	 * 批量更新
	 * @param entitys 更新的实体
	 */
	public void updateBatch(List<T> entitys){
		baseDao.updateBatch(entitys);
	}
	
	/**
	 * 批量删除
	 * @param entitys 删除的实体
	 */
	public void deleteBatch(List<T> entitys){
		baseDao.deleteBatch(entitys);
	}
	
	/**
	 * 批量新增或更新
	 * @param entitys 更新的实体
	 */
	public void saveOrUpdateBatch(List<T> entitys){
		baseDao.saveOrUpdateBatch(entitys);
	}
	
	/**
	 * 数据库更新
	 * @param hql HQL语句
	 * @param params HQL语句参数，用点位符？表示
	 * @return
	 */
	public int update(String hql, Object... params){
		return baseDao.update(hql, params);
	}
	
	/**
	 * 数据库查询
	 * @param sql SQL语句
	 * @param params SQL语句参数，用点位符？表示
	 * @return
	 */
	public List<Map<String, Object>> getSql(boolean cache, Integer pageNo, Integer pageSize, String sql, Object... params){
		return baseDao.getSql(cache, pageNo, pageSize, sql, params);
	}
	
	
	
	/**
	 * 数据库查询
	 * @param pageNo 页码
	 * @param pageSize 页大小
	 * @param hql HQL语句
	 * @param params HQL语句参数，用点位符？表示
	 * @return
	 */
	public PageSet<T> getPage(boolean cache, Integer pageNo, Integer pageSize, String hql, Object... params){
		PageSet<T> pager = null;
		if(pageNo == null || pageSize == null){
			pager = new PageSet<T>(pageNo, pageSize, 0);
			List<T> datas = baseDao.getList(cache, pageNo, pageSize, hql, params);
			pager.setItems(datas);
		}else{
			int index = hql.toLowerCase().indexOf("from");
			int total = baseDao.getCount(cache, "SELECT COUNT(*) " + hql.substring(index), params);
			pager = new PageSet<T>(pageNo, pageSize, total);
			if(total > 0){
				List<T> datas = baseDao.getList(cache, pager.getCurrentPage(), pager.getPageOfSize(), hql, params);
				pager.setItems(datas);
			}
		}
		return pager;
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
		return baseDao.getList(cache, pageNo, pageSize, hql, params);
	}
	

	

	
	/**
	 * 数据库查询
	 * @param hql HQL语句
	 * @param params HQL语句参数，用点位符？表示
	 * @return
	 */
	public T getOne(boolean cache, String hql, Object... params){
		List<T> entitys = baseDao.getList(cache, 1, 1, hql, params);
		return entitys != null && entitys.size() > 0 ? entitys.get(0) : null;
	}
	
	
	
	
	/**
	 * 数据库行统计
	 * @param hql hql HQL语句
	 * @param params HQL语句参数，用点位符？表示
	 * @return
	 */
	public int getCount(boolean cache, String hql, Object... params){
		int index = hql.toLowerCase().indexOf("from");
		return baseDao.getCount(cache, "SELECT COUNT(*) " + hql.substring(index), params);
	}
	/**
	 * 刷新缓存并执行数据库更新操作
	 */
	
	public void flush(){
		baseDao.flush();
	}
	
	/**
	 * 清除缓存
	 */
	public void clear(){
		baseDao.clear();
	}
	
	public Object merge(T entity) {
		return baseDao.merge(entity);
	}
	
	/**
	 * 数据库查询<br/>
	 * <h1>查询条件组装：WHERE (x=1 OR y=2) OR (h!=3) OR (g>4)</h1>
	 * new com.lhx.util.QueryParam()<br/>
	 * 	.and(new Param().andEq("x", 1).orEq("y", 2))<br/>
	 * 	.or(new Param().andNe("h", 3))<br/>
	 * 	.or(new Param().orGt("g", 4));<br/>
	 * <h1>分组：GROUP BY userId,cid</h1>
	 * Group group = new Group().add("userId").add("cid");
	 * <h1>排序：ORDER BY createDate DESC, OperTime ASC</h1>
	 * Order order = new Order().desc("createDate").asc("operTime");
	 * @param pageNo 页码
	 * @param pageSize 页大小
	 * @param queryParam 查询参数new QueryParam().and(new Param().andEq("x", 1).orEq("y", 2));
	 * @param group 分组new Group().add("userId").add("cid");
	 * @param order 排序new Order().desc("createDate").asc("operTime");
	 * @param function 指定查询字段cid, userId，聚合函数sum(money),max(total)
	 * @return
	 */
	public List<Object> getListForObject(boolean cache, Integer pageNo, Integer pageSize, ParamMapper queryParam, Group group, Order order, String function){
		List<Object> values = new ArrayList<Object>();
		String hql = parseQueryParam(queryParam, group, order, values);
		if(function != null && function.length() > 0){
			return (List<Object>) getList(cache, pageNo, pageSize, "SELECT " + function + hql, values.toArray());
		}else{
			return (List<Object>) getList(cache, pageNo, pageSize, hql, values.toArray());
		}
	}
	
	/**
	 * 数据库查询<br/>
	 * <h1>查询条件组装：WHERE (x=1 OR y=2) OR (h!=3) OR (g>4)</h1>
	 * new com.lhx.util.QueryParam()<br/>
	 * 	.and(new Param().andEq("x", 1).orEq("y", 2))<br/>
	 * 	.or(new Param().andNe("h", 3))<br/>
	 * 	.or(new Param().orGt("g", 4));<br/>
	 * <h1>分组：GROUP BY userId,cid</h1>
	 * Group group = new Group().add("userId").add("cid");
	 * <h1>排序：ORDER BY createDate DESC, OperTime ASC</h1>
	 * Order order = new Order().desc("createDate").asc("operTime");
	 * @param pageNo 页码
	 * @param pageSize 页大小
	 * @param queryParam 查询参数new QueryParam().and(new Param().andEq("x", 1).orEq("y", 2));
	 * @param group 分组new Group().add("userId").add("cid");
	 * @param order 排序new Order().desc("createDate").asc("operTime");
	 * @return
	 */
	public List<T> getList(boolean cache, Integer pageNo, Integer pageSize, ParamMapper queryParam, Group group, Order order){
		List<Object> values = new ArrayList<Object>();
		String hql = parseQueryParam(queryParam, group, order, values);
		return getList(cache, pageNo, pageSize, hql, values.toArray());
	}
	
	/**
	 * 数据库行统计
	 * @param queryParam 查询条件
	 * @return
	 */
	public int getCount(boolean cache, ParamMapper queryParam){
		List<Object> values = new ArrayList<Object>();
		String hql = parseQueryParam(queryParam, null, null, values);
		return baseDao.getCount(cache, "SELECT COUNT(*) " + hql, values.toArray());
	} 
	
	/**
	 * 解析查询条件为HQL语句
	 * @param queryParam 查询条件
	 * @return
	 */
	private String parseQueryParam(ParamMapper queryParam, Group group, Order order, List<Object> values){
		StringBuilder sbd = new StringBuilder();
		if(queryParam != null && queryParam.size() > 0){
			int index = 0;
			for(int i = 0; i < queryParam.size(); i++){
				String keyparis = parseParam(queryParam.getMode().get(i), queryParam.getParam().get(i), index, values);
				if(keyparis != null && keyparis.length() > 0){
					if(sbd.length() == 0){
						sbd.append(" WHERE ");
					}
					sbd.append(keyparis);
					index++;
				}
			}
		}
		if(group != null && group.size() > 0){
			for(int i = 0; i < group.size(); i++){
				String property = group.getProperty().get(i);
				if(i > 0){
					sbd.append(",").append(property);
				}else{
					sbd.append(" GROUP BY ").append(property);
				}
			}
		}
		if(order != null && order.size() > 0){
			for(int i = 0; i < order.size(); i++){
				String property = order.getProperty().get(i);
				OrderMode mode = order.getMode().get(i);
				if(i > 0){
					sbd.append(",").append(property).append(" ").append(mode);
				}else{
					sbd.append(" ORDER BY ").append(property).append(" ").append(mode);
				}
			}
		}
		return " FROM "+ entityClass.getSimpleName() +sbd.toString();
	}
	
	private String parseParam(GroupMatchMode gmode, Param params, int index, List<Object> values){
		String keyparis = "";
		if(params != null && params.size() > 0){
			if(index > 0){
				if(gmode == GroupMatchMode.or){
					keyparis = " OR ";
				}else{
					keyparis = " AND ";
				}
			}
			keyparis += "(";
			for(int i = 0; i < params.size(); i++){
				MatchMode mode = params.getMode().get(i);
				String property = params.getProperty().get(i);
				Object value = params.getValue().get(i);
				if(i > 0){
					String exp = mode.toString();
					if(exp.startsWith("or")){
						keyparis += " OR ";
					}else{
						keyparis += " AND ";
					}
				}
				switch (mode) {
				case andEq:
				case orEq:
					keyparis += property + "=?";
					values.add(value);
					break;
				case andNe:
				case orNe:
					keyparis += property + "!=?";
					values.add(value);
					break;
				case andGt:
				case orGt:
					keyparis += property + ">?";	
					values.add(value);
					break;
				case andGe:
				case orGe:
					keyparis += property + ">=?";
					values.add(value);
					break;
				case andLt:
				case orLt:
					keyparis += property + "<?";
					values.add(value);
					break;
				case andLe:
				case orLe:
					keyparis += property + "<=?";
					values.add(value);
					break;
				case andLike:
				case orLike:
					keyparis += property + " LIKE ?";
					value = "%" + value + "%";
					values.add(value);
					break;
				case andNull:
				case orNull:
					keyparis += property + " IS NULL";
					break;
				case andNotNull:
				case orNotNull:
					keyparis += property + " IS NOT NULL";
					break;
				}
			}
			keyparis += ")";
		}
		return keyparis;
	}
	
}
