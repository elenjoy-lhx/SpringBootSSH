package com.lhx.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XssFilter implements Filter{

	private FilterConfig filterConfig = null;
	public void destroy() {
		// TODO Auto-generated method stub
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		XssFilterRequestWrapper xssrequest = new XssFilterRequestWrapper((HttpServletRequest)request);
		chain.doFilter(xssrequest, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		filterConfig = arg0;
	}

}
