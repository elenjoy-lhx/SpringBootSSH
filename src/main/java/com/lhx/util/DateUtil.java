package com.lhx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

	public static final long oneHourLength = 60 * 60 * 1000;
	public static final long oneDayLength = oneHourLength * 24;
	public static final long fiveDayLength = oneDayLength * 5;
	public static final int oneDaySecond = 60 * 60 * 24;
	public static final int oneHourSecond = 60 * 60;
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	

	/** yyyy-MM-dd HH:mm:ss **/
	public static final SimpleDateFormat y_m_d_H_m_s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** yyyy-MM-dd HH:mm **/
	public static final SimpleDateFormat y_m_d_H_m = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/** yyyy-MM-dd **/
	public static final SimpleDateFormat y_m_d = new SimpleDateFormat("yyyy-MM-dd");
	/** yyyy-MM **/
	public static final SimpleDateFormat y_m = new SimpleDateFormat("yyyy-MM");
	
	/** yyyyMM **/
	public static final SimpleDateFormat ym = new SimpleDateFormat("yyyyMM");
	/** yyyyMMdd **/
	public static final SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
	/** yyyyMMddHHmm **/
	public static final SimpleDateFormat ymdhm = new SimpleDateFormat("yyyyMMddHHmm");
	/** yyyyMMddHHmmss **/
	public static final SimpleDateFormat ymdhms = new SimpleDateFormat("yyyyMMddHHmmss");
	/** HHmmss **/
	public static final SimpleDateFormat hms = new SimpleDateFormat("HHmmss");
	
	/** MM **/
	public static final SimpleDateFormat m = new SimpleDateFormat("MM");
	/** dd **/
	public static final SimpleDateFormat d = new SimpleDateFormat("dd");
	/** HH:mm **/
	public static final SimpleDateFormat hm = new SimpleDateFormat("HH:mm");
	
	/**
	 * 时间格式化，传入指定格式
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String formatDate(Date date, String format){
		if(date == null){
			date = new Date();
		}
		if(format == null){
			format = "yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(format).format(date);
	}
	/**
	 * 判断这个时间是不是在同一天
	 * 
	 * @param timestamp
	 * @return
	 */
	public static boolean isSameDay(long timestamp, long othertimestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String day1 = sdf.format(new Date(timestamp));
		String day2 = sdf.format(new Date(othertimestamp));
		return day1.equals(day2);
	}

	/**
	 * 判断这个时间是不是在同一天
	 * 
	 * @param timestamp
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String day1 = sdf.format(date1);
		String day2 = sdf.format(date2);
		return day1.equals(day2);
	}

	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static Date getSpecifiedDayAfter(Date startdate, int num) {
		Date date = startdate;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, num);
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(cal.getTime()));
		return cal.getTime();
	}

	public static Long[] getbeginAndEnd(Date date) {
		Long[] re = new Long[2];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		re[0] = calendar.getTimeInMillis();
		// Date start = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.SECOND, -1);
		re[1] = calendar.getTimeInMillis();
		return re;
	}

	// 返回一年后时间
	public static Date getLastForOneYear(Date date) {

		if (date == null) {
			date = new Date();
		}

		Calendar c = Calendar.getInstance();

		c.setTime(date);
		c.add(Calendar.YEAR, 1);
		return c.getTime();

	}

	/**
	 * 得到now天之后num天的时间 返回格式为yyyy-MM-dd 24:00:00
	 * 
	 * @param num
	 * @return
	 */
	public static String getLastDate(Date now, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DATE, num);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 得到now天之后num天的时间 返回格式为Date
	 * 
	 * @param num
	 * @return
	 */
	public static Date getAfterNumDate(Date now, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DATE, num);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 判断两个日期是不是同一个星期
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeek(Date date1, Date date2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int week1 = calendar.get(Calendar.WEEK_OF_YEAR);
		int year1 = calendar.get(Calendar.YEAR);
		calendar.setTime(date2);
		int week2 = calendar.get(Calendar.WEEK_OF_YEAR);
		int year2 = calendar.get(Calendar.YEAR);
		if (year1 == year2 && week1 == week2) {
			return true;
		}
		return false;
	}

	/**
	 * 判断两个日期是不是同一个月份
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameMonth(Date date1, Date date2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int month1 = calendar.get(Calendar.MONTH);
		int year1 = calendar.get(Calendar.YEAR);
		calendar.setTime(date2);
		int month2 = calendar.get(Calendar.MONTH);
		int year2 = calendar.get(Calendar.YEAR);
		if (year1 == year2 && month1 == month2) {
			return true;
		}
		return false;
	}

	public static long getNextMoneyDateTimestamp(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		if (week > 2) {
			cal.add(Calendar.DAY_OF_MONTH, -(week - 2) + 7);
		} else {
			cal.add(Calendar.DAY_OF_MONTH, 2 - week + 7);
		}
		return (cal.getTime().getTime() - date.getTime()) / 1000;
	}

	public static long getNextDateTimestamp(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return (cal.getTime().getTime() - date.getTime()) / 1000;
	}

	public static long getNextMonthTimestamp(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return (cal.getTime().getTime() - date.getTime()) / 1000;
	}

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int i = MonthBetween(sdf.parse("2016-01-21 12:20:58"), sdf.parse("2016-01-21 12:01:08"));
		System.out.println(i);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) {
		long time1 = smdate.getTime();
		long time2 = bdate.getTime();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/**
	 * 计算两个日期之间相差的月数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int MonthBetween(Date smdate, Date bdate) {
		Calendar scalendar = Calendar.getInstance();
		Calendar bcalendar = Calendar.getInstance();
		scalendar.setTime(smdate);
		bcalendar.setTime(bdate);
		int month = scalendar.get(Calendar.MONTH) - bcalendar.get(Calendar.MONTH);
		return Math.abs(month);
	}

	/**
	 * 在指定的日期上添加天数
	 */
	public static Date addDay(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}
	
	public static Date addMonth(Date date, int month){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	/**
	 * 比较两个日期
	 * 
	 * @param smdate
	 *            小的日期
	 * @param bdate
	 *            大的日期
	 * @return
	 * @throws ParseException 
	 */
	public static int isSameYear(Date smdate, Date bdate) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(smdate);
		int year1 = calendar.get(Calendar.YEAR);
		calendar.setTime(bdate);
		int year2 = calendar.get(Calendar.YEAR);
		if (year1 == year2) {
			boolean sameDay = isSameDay(smdate, bdate);
			if (sameDay) {
				return 0;// 同一天
			} else {
				SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd");
				String sdate = sdFormat.format(smdate);
				String bsdate = sdFormat.format(bdate);
				Date smdate2 = sdFormat.parse(sdate);
				Date bdate2=sdFormat.parse(bsdate);
				long time1 = smdate2.getTime();
				long time2 = bdate2.getTime();
				long between_days = (time2 - time1) / (1000 * 3600 * 24);
				int days = Integer.valueOf(String.valueOf(between_days));
				if (days == 1) {
					return 1; // 昨天
				} else if (days == 2) {
					return 2; // 前天
				} else {
					return 3;// 当年
				}
			}
		} else {
			return 4;// 不是同一年
		}
	}

	/**
	 * 返回指定格式的日期字符串
	 * 
	 * @param operTime
	 * @return
	 * @throws ParseException 
	 */
	public static String getFormatDate(Date operTime) throws ParseException{
		Date nowDate = new Date();
		long now = nowDate.getTime();
		int sameDay = isSameYear(operTime,nowDate);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Long time = now - operTime.getTime();
		String result = "";
		// 同一天
		if (sameDay == 0) {
			if (time / (1000 * 60) < 10) {
				result = "刚刚";
			} else if (time / (1000 * 60) > 10
					&& time / (1000 * 60) < 60) {
				result = time / (1000 * 60) + "分钟前";
			} else if (time / (1000 * 60 * 60) < 24
					&& time / (1000 * 60 * 60) > 0) {
				result = time / (1000 * 60 * 60)+ "小时前";
			}
			// 昨天
		} else if (sameDay == 1) {
			result = "昨天" + sdf.format(operTime);
			// 前天
		} else if (sameDay == 2) {
			result = "前天" + sdf.format(operTime);
			// 当年
		} else if (sameDay == 3) {
			SimpleDateFormat sdfformat = new SimpleDateFormat(
					"MM-dd HH:mm");
			result = sdfformat.format(operTime);
			// 不在当年
		} else {
			SimpleDateFormat sdfFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			result = sdfFormat.format(operTime);
		}
		return result;
	}
	
	
	
	public static int getMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}
	/**
	 * 是否当天
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		int year1 = calendar1.get(Calendar.YEAR);
		int month1 = calendar1.get(Calendar.MONTH) + 1;
		int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(new Date());
		int year2 = calendar2.get(Calendar.YEAR);
		int month2 = calendar2.get(Calendar.MONTH) + 1;
		int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
		return year1 == year2 && month1 == month2 && day1 == day2;
	}

	
	/**
	 * 比较时间
	 * @param src 当前时间
	 * @param dis 比较的时间
	 * @return
	 */
	public static String compareDate(Date src, Date dis){
		if(src == null || dis == null){
			return "";
		}
		boolean sameYear = DateUtil.formatDate(src, "yyyy").equals(DateUtil.formatDate(dis, "yyyy"));
		if(!sameYear){
			if(isSameDay(addDay(dis, 1), src)){
				return "昨天" + DateUtil.formatDate(dis, "HH:mm"); 
			}else if(isSameDay(addDay(dis, 2), src)){
				return "前天" + DateUtil.formatDate(dis, "HH:mm");
			}else{
				return formatDate(dis, "yy-MM-dd");
			}
		}else{
			boolean sameMonth = DateUtil.formatDate(src, "yyyyMM").equals(DateUtil.formatDate(dis, "yyyyMM"));
			if(!sameMonth){
				if(isSameDay(addDay(dis, 1), src)){
					return "昨天" + DateUtil.formatDate(dis, "HH:mm"); 
				}else if(isSameDay(addDay(dis, 2), src)){
					return "前天" + DateUtil.formatDate(dis, "HH:mm");
				}else{
					return DateUtil.formatDate(dis, "MM-dd");
				}
			}else{
				String day1 = DateUtil.formatDate(src, "yyyyMMdd");
				String day2 = DateUtil.formatDate(dis, "yyyyMMdd");
				if(!day1.equals(day2)){
					int diff = Integer.parseInt(day1) - Integer.parseInt(day2);
					if(diff == 1){
						return "昨天" + DateUtil.formatDate(dis, "HH:mm");
					}else if(diff == 2){
						return "前天" + DateUtil.formatDate(dis, "HH:mm");
					}else{
						return DateUtil.formatDate(dis, "MM-dd");
					}
				}else{
					long time = src.getTime() - dis.getTime();
					if (time > 0) {
						if (time / (1000 * 60 * 60) >= 1) {
							return time / (1000 * 60 * 60) + "小时前";
						} else if(time / (1000 * 60) >= 10){
							return time / (1000 * 60) + "分钟前";
						} else {
							return "刚刚";
						}
					}else{
						return "";
					}
				}
			}
		}
	}

	/**
	 * 获取当前时间前一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 获取当前时间前N天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getlastDay(Date date,int number) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 0-number);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 时间差转换显示
	 * @author Seven
	 * @date 2016年10月10日
	 * @param firstTime		较大时间
	 * @param secondTime	较小时间
	 * @return
	 */
	public static String transferShow(long firstTime,long secondTime) {
		long tsTime = firstTime - secondTime;
		String tipMsg = "";
		if(tsTime < oneHourLength) {
			tipMsg = "刚刚";
		} else if(tsTime < oneDayLength) {
			tipMsg = tsTime/oneHourLength + "小时前";
		} else if(tsTime <= fiveDayLength) {
			tipMsg = tsTime/oneDayLength + "天前";
		} else {
			tipMsg = y_m_d_H_m.format(secondTime);
		}
		return tipMsg;
	}
}
