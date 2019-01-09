package com.lhx.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianwen
 *
 */
public class Group implements Serializable {
	private List<String> property = new ArrayList<String>();
	private int size = 0;
	
	public int size(){
		return this.size;
	}
	
	public Group add(String property) {
		this.property.add(property);
		this.size++;
		return this;
	}

	public List<String> getProperty() {
		return this.property;
	}
	
}
