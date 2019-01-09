package com.lhx.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianwen
 * 数据库查询，排序对象的封装
 */
public class Order implements Serializable {
	public static enum OrderMode {ASC, DESC}	//排序的枚举
	private List<String> property = new ArrayList<String>();	//要排序的字段
	private List<OrderMode> mode = new ArrayList<OrderMode>(); 	//排序的模式
	private int size = 0;
	
	public List<String> getProperty(){
		return this.property;
	}
	
	public List<OrderMode> getMode(){
		return this.mode;
	}
	
	public int size(){
		return this.size;
	}
	
	/**
	 * 按字段升序排列
	 * @param property 排序的字段
	 * @return
	 */
	public Order asc(String property){
		this.property.add(property);
		this.mode.add(OrderMode.ASC);
		this.size++;
		return this;
	}
	
	/**
	 * 按字段降序排列
	 * @param property 要排序的字段
	 * @return
	 */
	public Order desc(String property){
		this.property.add(property);
		this.mode.add(OrderMode.DESC);
		this.size++;
		return this;
	}
	
}
