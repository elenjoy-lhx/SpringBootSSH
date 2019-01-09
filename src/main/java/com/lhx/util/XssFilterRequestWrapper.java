package com.lhx.util;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.util.HtmlUtils;

public class XssFilterRequestWrapper extends HttpServletRequestWrapper{

	HttpServletRequest orgRequest = null;  
	
	public XssFilterRequestWrapper(HttpServletRequest request) {
		super(request);
		orgRequest = request;
	}

	public Map<String,String[]> getParameterMap(){
		Map<String,String[]> map = super.getParameterMap();
		if(map != null){
			for(String key : map.keySet()){
				String[] value = map.get(key);
				for(int i = 0; i < value.length; i++){
					String v = HtmlUtils.htmlUnescape(value[i].toString());
					value[i] = HtmlUtils.htmlEscape(v);
				}
			}
		}
		return map;
	}
	
	public String getParameter(String name){
		String value = super.getParameter(name);
		if(value != null){
			value = HtmlUtils.htmlEscape(value);
		}
		return value;
	}
	
	public String getHeader(String name){
		String value = super.getHeader(name);
		if(value != null){
			value = HtmlUtils.htmlEscape(value);
		}
		return value;
	}
	public HttpServletRequest getOrgRequest() {
		return orgRequest;
	}

	public void setOrgRequest(HttpServletRequest orgRequest) {
		this.orgRequest = orgRequest;
	}
	
	public static void main(String[] args) {
		/*String s = HtmlUtils.htmlEscape("<div>hello world</div><p>&nbsp;</p>");  
		System.out.println(s);  */
		String s2 = HtmlUtils.htmlUnescape("<div>hello world</div><p>&nbsp;</p>");  
		String s = HtmlUtils.htmlEscape(s2);  
	}
}
