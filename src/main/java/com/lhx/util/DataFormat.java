package com.lhx.util;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Random;


public class DataFormat {
	private static final DecimalFormat df = new DecimalFormat("######0.00");
	
	/**
	 * @author GAO FENG
	 * @since 2015-7-9 上午11:12:16
	 * @see  double数据格式化 保留两位小数
	 * @params 
	 * @return 
	 */
	public static String toTwoDecimal(double data){
		
		return df.format(data);
	}
	
	
	/**
	 * @author GAO FENG
	 * @since 2015-7-9 上午11:12:19
	 * @see  随机生成字符串   字母+时间戳
	 * @params 
	 * @return 
	 */
	public static String RandomOrderCode(){
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		long dateString = Calendar.getInstance().getTimeInMillis();
		Random random = new Random();
		String res = String.valueOf(str.charAt(random.nextInt(str.length()-1))) + String.valueOf(str.charAt(random.nextInt(str.length()-1))) + String.valueOf(dateString).substring(2);
		return res;
	}
}
