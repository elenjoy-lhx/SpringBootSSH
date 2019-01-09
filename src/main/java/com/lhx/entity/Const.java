package com.lhx.entity;

/**
 * 常量
 * 
 * @author lhx
 * 
 */
public class Const {

	/**
	 * 官方信息 author：
	 */
	public static final String BASE_PROJECT_NAME = "xcx";							//项目名称
	public static final String BASE_WEB_SITE = "ibyshop.com";
	public static final String Mobile_BASE_WEB_URL = "https://m." + BASE_WEB_SITE;		//手机端
	
	public static final int BASE_SESSION_DEFAULT_TIME = 60*60*24*30;	//缓存默认存放时间  默认一个月
	
	public static final String SESSION_USERID = "userid_"+BASE_PROJECT_NAME; // 保存登陆的userid缓存前缀
	
	public static final String session_key = "session_key_"+BASE_PROJECT_NAME; // 保存授权后的session_key缓存前缀
	
	

}
