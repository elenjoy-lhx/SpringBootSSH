package com.lhx.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class ObtainIPUtil {

	
	/**
	 * 获取请求者的外网IP-方法1
	 * @author Seven
	 */
	public static String getIpAddress() throws Exception {
		String extranetIp = null;
			URL url = new URL("http://www.ip138.com/ip2city.asp");
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2.15) Gecko/20110303 Firefox/3.6.15");
			conn.setRequestProperty("Content-Type", "text/html");
			conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.contains("您的IP地址是")) {
					// System.out.println(line);
					int start = line.indexOf('[') + 1;
					int end = line.indexOf(']');
					extranetIp = line.substring(start, end);
				}
			}
			br.close();
		return extranetIp;
	}
	
	/**
	 * 获取请求者的外网IP-方法2
	 * @author tw
	 */
	public static String getRemoteIp(HttpServletRequest request){
		//说明：按这种方法不一定100%准
		String ip = request.getHeader("x-forwarded-for");      
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	    	ip = request.getHeader("Proxy-Client-IP");      
	    }   
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
            ip = request.getHeader("WL-Proxy-Client-IP");     
        }   
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
            ip = request.getHeader("X-Real-IP");     
        }		
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
            ip = request.getRemoteAddr();      
        } 
		if (ip != null && ip.indexOf(",") != -1) {  
			 ip = ip.split(",")[0];
		} 
		if("0:0:0:0:0:0:0:1".equals(ip)){
			ip = "127.0.0.1";
		}
        return ip;
	}
	
	/**
	 * 获取本机IP地址
	 * @author Seven
	 * @date 2016-09-23
	 * @return
	 */
	public static String getLocalIp() {
		InetAddress ia=null;
		String localip = null;
		try {
			ia = InetAddress.getLocalHost();
			
//			String localname=ia.getHostName();
//			System.out.println("本机名称是："+ localname);
			
			localip=ia.getHostAddress();
			System.out.println("-----------IP是："+localip);
			return localip;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return localip;
		}
	}
	

	public static String getRealIp() throws SocketException {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP

		Enumeration<NetworkInterface> netInterfaces = NetworkInterface
				.getNetworkInterfaces();
		InetAddress ip = null;
		boolean finded = false;// 是否找到外网IP
		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip.getHostAddress();
				}
			}
		}

		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}
	
}
