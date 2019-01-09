package com.lhx.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

public class StringUtils {
	
	 public static final String EMPTY = "";

	public StringUtils() {
		super();
	}
	/**
	 * 字符串不存在  返回true
	 * @param cs
	 * @return
	 */
	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}
	/**
	 * 字符串存在  返回true
	 * @param cs
	 * @return
	 */
	public static boolean isNotEmpty(CharSequence cs) {
		return !StringUtils.isEmpty(cs);
	}

	// 传入的CharSequence是String的接口，同样StringBuffer这些也是，可适用这里。Sequence的英语是序列的意思。
	public static boolean isBlank(CharSequence cs) {
		// 标记字符长度，
		int strLen;
		// 字符串不存在或者长度为0
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			// 判断空格，回车，换行等，如果有一个不是上述字符，就返回false
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	// 这个是isNotBlank()
	public static boolean isNotBlank(CharSequence cs) {
		return !StringUtils.isBlank(cs);
	}
	
	/**
     * <p>Gets the substring before the first occurrence of a separator.
     * The separator is not returned.</p>
     *
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * A <code>null</code> separator will return the input string.</p>
     *
     * <pre>
     * StringUtils.substringBefore(null, *)      = null
     * StringUtils.substringBefore("", *)        = ""
     * StringUtils.substringBefore("abc", "a")   = ""
     * StringUtils.substringBefore("abcba", "b") = "a"
     * StringUtils.substringBefore("abc", "c")   = "ab"
     * StringUtils.substringBefore("abc", "d")   = "abc"
     * StringUtils.substringBefore("abc", "")    = ""
     * StringUtils.substringBefore("abc", null)  = "abc"
     * </pre>
     *
     * @param str  the String to get a substring from, may be null
     * @param separator  the String to search for, may be null
     * @return the substring before the first occurrence of the separator,
     *  <code>null</code> if null String input
     * @since 2.0
     */
    public static String substringBefore(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }
    
    /**
     * <p>Gets the substring after the first occurrence of a separator.
     * The separator is not returned.</p>
     *
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * A <code>null</code> separator will return the empty string if the
     * input string is not <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.substringAfter(null, *)      = null
     * StringUtils.substringAfter("", *)        = ""
     * StringUtils.substringAfter(*, null)      = ""
     * StringUtils.substringAfter("abc", "a")   = "bc"
     * StringUtils.substringAfter("abcba", "b") = "cba"
     * StringUtils.substringAfter("abc", "c")   = ""
     * StringUtils.substringAfter("abc", "d")   = ""
     * StringUtils.substringAfter("abc", "")    = "abc"
     * </pre>
     *
     * @param str  the String to get a substring from, may be null
     * @param separator  the String to search for, may be null
     * @return the substring after the first occurrence of the separator,
     *  <code>null</code> if null String input
     * @since 2.0
     */
    public static String substringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }
    
    /**
     * double 
     * @param num
     * @return
     */
    public static String numberReplace(double num)
	{
		if(num+"".length()>5)
		{
			return (num+"").substring(0, 5);
		}
		return num+"";
	}
    
    /**
     * 判断字符串是否为纯数字
     * @author Seven	time:2015-03-27
     * @param str
     * @return
     */
    public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
    
    /**
     * 判断字符串是否为纯字母
     * @author Seven	time:2015-04-7
     * @param str
     * @return
     */
    public static boolean isEnglish(String charaString) {
        return charaString.matches("^[a-zA-Z]*");
    }

    /**
	 * 截取我的分享前30个字符,返回截取长度
	 * @param content
	 * @return
	 */
	public static int subByLength(String content,int length,int index){
		int imgIndex = content.indexOf("<img",index); 
		if(imgIndex != -1){
			/*if(imgIndex  == length){
				length = content.indexOf(">",imgIndex) + 1;
			}*/
			if(imgIndex < length){
				int i = length - imgIndex;
				index = content.indexOf(">",imgIndex);
				length = index + i;
				if(length >= content.length()){
					return content.length();
				}
				return subByLength(content,length,index);
			}
		} else {
			if(content.length() <= length){
				length =  content.length();
			}
		}
		return length;
	}
	/**
	 * 获取文章详情内容中的第一张图片
	 * 
	 * @param HTML
	 * @return
	 */

	public static String imageNum(String htmlStr) {
		String img = "";
		Pattern p_image;
		Matcher m_image;
		List pics = new ArrayList();
		String regEx_img = "<img.*src=(.*?)[^>]*?>";
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find()) {
			img = img + "," + m_image.group();
			Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)")
					.matcher(img);
			while (m.find()) {
				pics.add(m.group(1));
			}
		}
		if (pics != null && pics.size() > 0) {
			return (String) pics.get(0);
		} else {
			return null;
		}
	}
	
	public static String rateToString(double rate)
	{
		Double amount = rate;
		BigDecimal bigAmount = new BigDecimal(String.valueOf(amount));
		bigAmount = bigAmount.multiply(new BigDecimal("100"));
		String transAmt = String.valueOf(bigAmount.intValue());
		return transAmt;
	}
	
	/**
	 * 判断是否是合法的手机号 跟前端app.js isphone方法同步
	 * date: 2016-4-7下午4:25:36
	 * description:
	 * return 验证通过 返回 true 
	 */
	public static boolean isPhone(String phone){
		if (isEmpty(phone)){
			return false;
		}
		//香港的号码85269993123
		//21-29、31、34-37、39开头的号码为固定电话号码，51-56、59、6、90-98开头的号码为移动电话号码
		Matcher reg=Pattern.compile("^1[0-9]{10}$").matcher(phone);
		Matcher testphone=Pattern.compile("^932[0-9]{8}$").matcher(phone);
		Matcher reg2=Pattern.compile("^[5|6|8|9][0-9]{10}$").matcher(phone);
		if (phone.length()==11 && (reg.matches() || testphone.matches() || reg2.matches())){
			return true;
		}
		return false;
	}
	
	public static String getFileEndWith(String contentType){
		return contentType.substring(contentType.indexOf("/")+1, contentType.length());
	}
	
	/**
	 * 返回结果 
	 * @param code
	 * @param msg
	 * @param res
	 * @return
	 */
	public static String getResJson(int code , String msg , Map<String , Object> res){
		JSONObject result = new JSONObject();
		if(res != null){
			result = JSONObject.fromObject(res);
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("code", code);
		jsonObject.put("msg", msg);
		jsonObject.put("res", result.toString().replaceAll(":null", ":\"\""));
		return jsonObject.toString();
	}
	
	/**
	 * 如果字符串为空则返回空字符，不返回null
	 * @author Seven
	 * @date 2016年10月10日
	 * @param cs
	 * @return
	 */
	public static CharSequence emptyGetNullChar(CharSequence cs) {
		if(cs == null || cs.length() == 0){
			return "";
		} else {
			return cs;
		}
	}
	
	public static void main(String[] args) {
		String s = "现在刚忙完健康体验中心的事回家，第一件事就是翻开直信通看！哇塞！获得38个赞！实力指数973.5！月涵，您真棒！您终于找到了一个适合您的方式来进行您的直销事业！做好您的健康管理！……月涵！加油！<br><img class=\"face24\" width=\"24\" height=\"24\" src=\"/web/img/face/1.png\"><img class=\"face24\" width=\"24\" height=\"24\" src=\"/web/img/face/1.png\"><img class=\"face24\" width=\"24\" height=\"24\" src=\"/web/img/face/1.png\">";
		int i = subByLength(s, 100,0);
		System.out.println(s.substring(0,101));
	}
	
	/**
	 * 生成随机字符串
	 * @author Seven
	 * @date 2016-09-23
	 * @param length	要生成的字符串长度
	 * @return
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	}
	/**
	 * 用户名为空 返回 手机号码前三位********
	 * @author: wangfucheng
	 * @date: 2016年10月9日
	 * @param name
	 * @param phone
	 * @return
	 */
	public static String isNameToPhone(String name, String phone){
		if(isEmpty(name)){
			return phone.substring(0,3)+"********";
		}
		return name;
	}
	/**
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	public static Map<String, List<Map<String, Object>>> sortMapByKey(Map<String, List<Map<String, Object>>> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, List<Map<String, Object>>> sortMap = new TreeMap<String, List<Map<String, Object>>>(
				new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}
	
	/**
	 * java去除字符串中的空格、回车、换行符、制表符
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
