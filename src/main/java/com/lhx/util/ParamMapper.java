package com.lhx.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianwen
 * 数据库查询, 组合查询条件
 * @see Param
 */
public class ParamMapper implements Serializable {
	public enum GroupMatchMode{and, or};	//分组模式枚举
	private List<GroupMatchMode> mode = new ArrayList<GroupMatchMode>();	//分组模式
	private List<Param> params = new ArrayList<Param>();	//多个条件一组
	private int size = 0;
	
	public List<GroupMatchMode> getMode(){
		return this.mode;
	}
	
	public List<Param> getParam(){
		return this.params;
	}
	
	public int size(){
		return this.size;
	}
	
	/**
	 * 多个条件按AND模式分组
	 * @param params 条件参数
	 * @return
	 */
	public ParamMapper and(Param params){
		this.mode.add(GroupMatchMode.and);
		this.params.add(params);
		this.size++;
		return this;
	}
	
	/**
	 * 多个条件OR模式分组
	 * @param params 条件参数
	 * @return
	 */
	public ParamMapper or(Param params){
		this.mode.add(GroupMatchMode.or);
		this.params.add(params);
		this.size++;
		return this;
	}
	
}
