package com.lhx.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianwen
 * 数据库查询，条件参数的封装
 *
 */
public class Param implements Serializable {
	//匹配模式
	public enum MatchMode{andEq, andNe, andGt, andGe, andLt, andLe, andLike, andNull, andNotNull, 
							orEq, orNe, orGt, orGe, orLt, orLe, orLike, orNull, orNotNull};
	private List<String> property = new ArrayList<String>();	//要查询的字段
	private List<Object> value = new ArrayList<Object>();		//匹配的值
	private List<MatchMode> mode = new ArrayList<MatchMode>();	//匹配的模式
	private int size = 0;
	
	public List<String> getProperty(){
		return this.property;
	}
	
	public List<Object> getValue(){
		return this.value;
	}
	
	public List<MatchMode> getMode(){
		return this.mode;
	}
	
	public int size(){
		return this.size;
	}
	
	/**
	 * property mode value
	 * @param property 查询的字段
	 * @param value 对应的值 
	 * @param mode 匹配模式
	 * @return 
	 */
	public Param add(String property, Object value, MatchMode mode){
		this.property.add(property);
		this.value.add(value);
		this.mode.add(mode);
		this.size++;
		return this;
	}
	
	/**
	 * AND property=value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param andEq(String property, Object value){
		return add(property, value, MatchMode.andEq);
	}
	
	/**
	 * OR property=value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param orEq(String property, Object value){
		return add(property, value, MatchMode.orEq);
	}
	
	/**
	 * AND property <> value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param andNe(String property, Object value){
		return add(property, value, MatchMode.andNe);
	}
	
	/**
	 * OR property <> value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param orNe(String property, Object value){
		return add(property, value, MatchMode.orNe);
	}
	
	/**
	 * and property > value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param andGt(String property, Object value){
		return add(property, value, MatchMode.andGt);
	}
	
	/**
	 * OR property < value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param orGt(String property, Object value){
		return add(property, value, MatchMode.orGt);
	}
	
	/**
	 * AND property >= value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param andGe(String property, Object value){
		return add(property, value, MatchMode.andGe);
	}
	
	/**
	 * OR property >= value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param orGe(String property, Object value){
		return add(property, value, MatchMode.orGe);
	}
	
	/**
	 * AND property < value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param andLt(String property, Object value){
		return add(property, value, MatchMode.andLt);
	}
	
	/**
	 * OR property < value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param orLt(String property, Object value){
		return add(property, value, MatchMode.orLt);
	}
	
	/**
	 * AND property <= value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param andLe(String property, Object value){
		return add(property, value, MatchMode.andLe);
	}
	
	/**
	 * OR property <= value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param orLe(String property, Object value){
		return add(property, value, MatchMode.orLe);
	}
	
	/**
	 * AND property LIKE value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param andLike(String property, Object value){
		return add(property, value, MatchMode.andLike);
	}
	
	/**
	 * OR property LIKE value
	 * @param property 查询的字段
	 * @param value 对应的值
	 * @return
	 */
	public Param orLike(String property, Object value){
		return add(property, value, MatchMode.orLike);
	}
	
	/**
	 * AND property IS NULL
	 * @param property 查询的字段
	 * @return
	 */
	public Param andNull(String property){
		return add(property, null, MatchMode.andNull);
	}
	
	/**
	 * OR property IS NULL
	 * @param property 查询的字段
	 * @return
	 */
	public Param orNull(String property){
		return add(property, null, MatchMode.orNull);
	}
	
	/**
	 * AND property IS NOT NULL
	 * @param property 查询的字段
	 * @return
	 */
	public Param andNotNull(String property){
		return add(property, null, MatchMode.andNotNull);
	}
	
	/**
	 * OR property IS NOT NULL
	 * @param property 查询的字段
	 * @return
	 */
	public Param orNotNull(String property){
		return add(property, null, MatchMode.orNotNull);
	}
}
