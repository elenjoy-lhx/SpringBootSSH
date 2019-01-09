package com.lhx.util;

import java.util.Random;

/**
 * author yyf
 * 
 * 2015-12-16 下午3:31:20
 * 
 */
public class RandomNumber {
	/**
	 * 随机指定范围内N个不重复的数 最简单最基本的方法
	 * 
	 * @param min
	 *            指定范围最小值
	 * @param max
	 *            指定范围最大值
	 * @param n
	 *            随机数个数
	 */
	public static int[] randomCommon(int min, int max, int n) {
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		while (count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if (num == result[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result[count] = num;
				count++;
			}
		}
		return result;
	}
	
	/**
	 * @Description : 返回一个区间内的随机整数
	 * @postscript : 
	 * @author : Seven
	 * @date : 2017年11月8日
	 * @param min	最小值
	 * @param max	最大值
	 * @return
	 */
	public static int rdNum(int min, int max) {
		return new Random().nextInt(max) % (max-min+1) + min;
	}
}
