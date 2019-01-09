package com.lhx.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 自定义json数据类型转换
 * @author Seven
 * @date 2016-06-22
 *
 */
public class CustomJsonValueProcessor implements JsonValueProcessor {

	SimpleDateFormat sdf;
	
	public CustomJsonValueProcessor(String format) {
		super();
		sdf = new SimpleDateFormat(format);
	}
	
	public static JsonConfig getJsonConfig(String format) {
		if(format == null)
		{
			format = "yyyy-MM-dd";
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new CustomJsonValueProcessor(format));
		return jsonConfig;
	}
	

	public Object processArrayValue(Object paramObject,
			JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	public Object processObjectValue(String paramString, Object paramObject,
			JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	private Object process(Object value) {
		if(value == null) {
			return "";
		} else if (value instanceof Date) {
			return sdf.format(value);
		} else if(value instanceof java.sql.Date) {
			return sdf.format(value);
		} else if(value instanceof Timestamp) {
			return sdf.format(value);
		}
		return value == null ? "" : value.toString();
	}

}
