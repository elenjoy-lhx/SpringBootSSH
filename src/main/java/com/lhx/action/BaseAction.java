package com.lhx.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.lhx.entity.Const;
import com.lhx.service.CacheService;
import com.lhx.service.UserService;
import com.lhx.util.StringUtils;

import net.sf.json.JSONObject;


public class BaseAction  {
	/**
	 * 林浩旋
	 */
	
	@Autowired
	private HttpServletRequest request;
	 
	@Autowired
	private HttpServletResponse response;
	
	
	
	private static final long serialVersionUID = 3092950839900117867L;
	
	protected Integer pn;	//页码
	protected Integer ds;	//数据条数

	protected UserService userService;
	@Autowired
	protected CacheService cacheService;
//	protected BaseService baseService;
	
	
	
	
	
	/**
	 * 
	 *  ==========================================系统公用==================================================
	 */
	/**
	 * 根据请求返回对应结果类型（APP适用）
	 * @author Seven	time:2015-05-20 16:48:00
	 * @param callback app 传递过来的参数
	 * @param requestType 哪里发来的请求
	 * @param status 返回app成功状态
	 * @param appParam APP端请求的返回数据
	 * @param param web端请求的返回数据
	 * @param type web端请求的返回数据类型
	 * @return
	 */
	public String returnType(String callback, String requestType, String status, String appParam, String param,String type){
		if(StringUtils.isNotEmpty(requestType) && requestType.equals("app")) {
			if(StringUtils.isEmpty(appParam)) {
				return ajaxJson((callback + "({\"result\":\"" + status + "\",\"vo\":\"\"})"));
			} else {
				return ajaxJson((callback + "({\"result\":\"" + status + "\",\"vo\":" + appParam + "})"));
			}
		} else {
			if(StringUtils.isEmpty(type)) {
				return param;
			} else {
				return ajax(param, type);
			}
		}
	}
	
	/**
	 * 返回给前端的数据格式
	 * @param result	默认0为程序执行正常
	 * @param msg	状态描述
	 * @param json	需要返回的数据      不需要返回传null
	 * @return
	 */
	public String getReturn(int code,String msg,String json)
	{
		JSONObject jo = new JSONObject();
		if(json!=null&&!json.equals(""))
		{
			jo.put("result", code);
			jo.put("msg", msg);
			jo.put("json", json);
		}
		else
		{
			jo.put("result", code);
			jo.put("msg", msg);
			jo.put("json", "");
		}
		return ajaxJson(jo.toString());
	}
	

	// AJAX输出，返回null
	public String ajax(String content, String type) {
		try {
			HttpServletResponse response = getResponse();
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	
	// 根据字符串输出JSON，返回null
	public String ajaxJson(String jsonString) {
		return ajax(jsonString, "application/json");
	}
	
	// AJAX输出HTML，返回null
	public String ajaxHtml(String html) {
		return ajax(html, "text/html");
	}
	
	// 根据字符串输出JSON，返回null
	public String ajaxJson(int code, String msg, Map<String, Object> res) {
		JSONObject result = new JSONObject();
		if(res != null){
			result = JSONObject.fromObject(res);
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("result", code);
		jsonObject.put("msg", msg);
		jsonObject.put("json", result.toString().replaceAll(":null", ":\"\""));
		return ajax(jsonObject.toString(), "application/json");
	}
	
	
	/**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

	// Attribute
	public void setAttribute(String name, Object value) {

		getRequest().setAttribute(name, value);
	}

	// Attribute
	public Object getAttribute(String name) {
		return getRequest().getAttribute(name);
	}

	public void removeSession(String name) {
		cacheService.del(CacheService.CACHE_SESSION + getStatusid() + "_"
				+ name);
	}

	public Object getSession(String name) {

		return cacheService.get(CacheService.CACHE_SESSION + getStatusid()
				+ "_" + name);
	}

	
	protected String cookieGet(String key) {
		Cookie[] cookies = getRequest().getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public String clearCookie(String key) throws IOException {
		Cookie[] cookies = getRequest().getCookies();
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.removeAttribute(key);
		Cookie cookie = new Cookie(key, null);
		cookie.setPath("/");
		cookie.setDomain(Const.BASE_WEB_SITE);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		getResponse().sendRedirect(Const.Mobile_BASE_WEB_URL + "/m/regtemp!login.jsp");
		return null;
	}

	public String getStatusid() {
		String statusid = cookieGet("cookie1");
		if (statusid == null) {
			statusid = (String) getAttribute("cookie1");
		}
		return statusid;
	}

	// Session
	public boolean setSession(String name, Object value) {
		if (value != null) {
			return cacheService.set(CacheService.CACHE_SESSION + getStatusid() + "_"
					+ name, value, CacheService.BASE_SESSION_DEFAULT_TIME);
		}
		return false;
	}
	
	// Session
	public boolean setSession(String name, Object value, int sessionTime) {
		if (value != null) {
			return cacheService.set(CacheService.CACHE_SESSION + getStatusid() + "_"
					+ name, value, sessionTime);
		}
		return false;
	}
	
	
	// Response
	public HttpServletResponse getResponse() {
		return response;
	}

	// Request
	public HttpServletRequest getRequest() {
		return request;
	}





	public Integer getPn() {
		return pn;
	}

	public void setPn(Integer pn) {
		this.pn = pn;
	}

	public Integer getDs() {
		return ds;
	}

	public void setDs(Integer ds) {
		this.ds = ds;
	}

	



}
