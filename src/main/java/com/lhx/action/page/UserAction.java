package com.lhx.action.page;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhx.action.BaseAction;
import com.lhx.entity.User;
import com.lhx.entity.UserAdmin;
import com.lhx.service.CacheService;
import com.lhx.service.UserAdminService;
import com.lhx.service.UserService;

import net.sf.json.JSONObject;

/**
 * 注意: 这个地方使用的是@Controller，而不是@RestController，用的是spring MVC逻辑，
 * 在spring MVC中，需要在spring mvc配置的视图解析器中指定视图文件位置，spring boot使用
 * freemarker等于将视图地址默认在src/main/resources/templates下了
 */

@Controller
@RequestMapping("user")
public class UserAction extends BaseAction {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAdminService userAdminService;
	
	@Autowired
	private CacheService cacheService;
	
	@RequestMapping("test.jsp")
	public String test(Map<String, Object> map,String age)
	{
		User u = userService.getTest();
		System.out.println(u.getName()+"22");
		User u2 = userService.get(3);
		System.out.println(u2.getName()+"33");
		UserAdmin u1 = userAdminService.get(2);
		System.out.println(u1.getName());
		
		List<User> u3 = userService.getList(null, 0, 0, null, null, false);
		for (int i = 0; i < u3.size(); i++) {
			System.out.println("========="+u3.get(i).getName());
		}
		
		User u4 = userService.getOne(false, "from User where id=?", 1);
		System.out.println("u4:"+u4.getName());
		
		System.out.println("age:"+age);
		JSONObject jo = new JSONObject();
		jo.put("name", "林大大");
		jo.put("sex", "男");
		map.put("hello", jo);
		
		boolean bl = cacheService.set("youname", "林浩旋", 60*60);
		System.out.println("存入缓存："+bl);
		cacheService.del("youname","youname1");
		String youname = (String) cacheService.get("youname");
		System.out.println(youname);
		setAttribute("attrTest", "ok");
		
//		removeSession("login_session");
		Object o = getSession("login_session");
		System.out.println("用户缓存测试："+o);
		boolean bl1 = setSession("login_session", "该用户已登陆名：林大大");
		System.out.println("存储缓存结果："+bl1);
		return "user/test";
	}
	
	
	@RequestMapping("hi.jsp")
    public UserAdmin hi()
    {
		User u = userService.getTest();
		System.out.println(u.getName()+"22");
		User u2 = userService.get(3);
		System.out.println(u2.getName()+"33");
		UserAdmin u1 = userAdminService.get(2);
		System.out.println(u1.getName());
    	return u1;
    }
	
	@RequestMapping(value = "testStr.jsp", method = { RequestMethod.GET }, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String testStr()
	{
		JSONObject jo = new JSONObject();
		jo.put("name", "浩旋");
		jo.put("sex", "男");
		return jo.toString();
	}
	@RequestMapping("testJson")
	@ResponseBody
	public String testJson()
	{
		JSONObject jo = new JSONObject();
		jo.put("name", "浩旋");
		jo.put("sex", "男");
		return getReturn(0, "success", jo.toString());
	}
}
