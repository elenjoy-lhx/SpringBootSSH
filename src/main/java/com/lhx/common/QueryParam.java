package com.lhx.common;

import java.io.Serializable;

public class QueryParam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6401893603325942443L;

	private String[] keys;
	private Object[] values;
	private int length;

	public QueryParam(int initSize) {
		keys = new String[initSize+1];
		values = new Object[initSize+1];
		length = 0;
	}

	public QueryParam() {
		// TODO Auto-generated constructor stub
	}

	public void setSize(int size) {
		keys = new String[size];
		values = new Object[size];
		length = 0;
	}

	public QueryParam add(String key, Object value) {

		if (key == null) {
			return this;
		}
		if (value == null) {
			return this;
		}
		if (key.equals("_admin_")) {
			return this;
		}
		// 如果长度大于原始长度
		keys[length] = key;
		values[length] = value;
		++length;
		return this;
	}

	public Object getValue(int index) {
		return values[index];
	}

	public String getKey(int index) {
		return keys[index];
	}

	public int getLength() {
		return length;
	}
}
