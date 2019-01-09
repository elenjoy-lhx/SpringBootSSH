package com.lhx.util;

import java.util.Date;

public class OrderUtil {

	/**根据userId 和时间戳生成随机的字符串
	 * @param userid
	 * @return
	 */
	public static String creatSn(String userid) {
		String timestamp = new Date().getTime() + "";
		int random = (int)(Math.random() * 1000);
		return timestamp.substring(0, timestamp.length() - 2) + random + userid;
	}
	
	public static String createSn(String mark, String uid) {
		Date now = new Date();
		int randomNum = RandomNumber.rdNum(1, 1000);
		String timestamp = now.getTime() + "";
		return mark + timestamp.substring(0, timestamp.length()-3) + randomNum + uid;
	}

	/**   
		 * @author  GAO FENG 
		 * @date  2015-12-10 上午10:08:44 
		 * @params 
		 * @return
		 * @Description 时间戳  + 3位随机数
	*/
	public static String creatNo() {
		long str = System.currentTimeMillis();
		int random = 0;
		while(random <= 99)
		{
			random = (int)(Math.random() * 1000);
		}
		return String.valueOf(str) + random;
	}
	
	/**
	 * 生成指定位数的随机数
	 * @author Seven
	 * @date 2016-09-23
	 * @param n		长度
	 * @return
	 */
	public static int randNum (int n) {
		int ans = 0;
        while(Math.log10(ans)+1<n) {
            ans = (int)(Math.random()*Math.pow(10, n));
        }
        return ans;
	}
	
	/**
	 * @Description : 生成订单编号
	 * @postscript : xxx(前缀)171123101820(时间)456(随机数)
	 * @author : Seven
	 * @date : 2017年11月23日
	 * @param prefix	前缀
	 * @param rdmMin	随机数最小值
	 * @param rdmMax	随机数最大值
	 * @return
	 */
	public static String createOrdersSn(String prefix, int rdmMin, int rdmMax) {
		Date now = new Date();
		prefix += DateUtil.ymdhms.format(now) + RandomNumber.rdNum(rdmMin, rdmMax);
		return prefix;
	}
}
