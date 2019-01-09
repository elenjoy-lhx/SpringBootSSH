package com.lhx.util;

/**
 * @author 林浩旋
 * @date 2015-12-1 上午10:15:29
 */
public class JudgeClient {

		/**   
		 * @author  GAO FENG 
		 * @date  2015-12-1 上午09:58:34 
		 * @params 
		 * @return
		 * @Description 判断客户端设备 
		 * android : 所有android设备
	     * mac os : iphone ipad
	     * windows phone:Nokia等windows系统的手机
	     * MicroMessenger 微信内置浏览器
	*/
	public static boolean  isMobileDevice(String requestHeader){
	
		String text = "MicroMessenger";
		boolean flag = false;
	    if(requestHeader == null){
	        return flag;
	    }
	   if(requestHeader.contains(text)){
		   flag = true;
	   }else{
		   flag = false;
	   }
	    return flag;
	}
}
