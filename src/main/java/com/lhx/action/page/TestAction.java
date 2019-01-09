package com.lhx.action.page;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestAction {
	
	private static String findName;
	
	private static JSONObject json;
	
	/**
	 * dear技术人员
	 * 虽然能根据要求找到了，但是该方式还是有瑕疵的，使用递归来寻找确实不是一个妥当的方法
	 * @param args
	 */
	public static void main(String[] args) {
		
		String str = "[{\"path\":\"level0_0/\",\"value\":\"value\",\"children\":[{\"path\":\"level0_0/level1_0/\",\"value\":\"value\",\"children\":[{\"path\":\"level0_0/level1_0/level2_0\",\"value\":\"value\",\"children\":[]},{\"path\":\"level0_0/level1_0/level2_1\",\"value\":\"value\",\"children\":[]},{\"path\":\"level0_0/level1_0/level2_1\",\"value\":\"value\",\"children\":[]}]},{\"path\":\"level0_0/level1_1/\",\"value\":\"value\",\"children\":[{\"path\":\"level0_0/level1_1/level2_0\",\"value\":\"value\",\"children\":[]},{\"path\":\"level0_0/level1_1/level2_1\",\"value\":\"value\",\"children\":[]},{\"path\":\"level0_0/level1_1/level2_1\",\"value\":\"value\",\"children\":[]}]}]},{\"path\":\"level0_1/\",\"value\":\"avaluerg\",\"children\":[]}]";
		JSONArray jsonlist = JSONArray.fromObject(str);
		findName = "level0_0/level1_1/level2_0";
		findJson(jsonlist);
		System.out.println(json);
	}
	/**
	 * 递归寻找
	 * @param jsonlist
	 */
	public static void findJson(JSONArray jsonlist)
	{
		for (int i = 0; i < jsonlist.size(); i++) {
			if(jsonlist.getJSONObject(i).getString("path").equals(findName))
			{
				System.out.println("找到了");
				json = jsonlist.getJSONObject(i);
				
				try {
					System.out.println("结束递归");
					throw new Exception("结束递归");
				} catch (Exception e) {
				}
			}
			//寻找子类
			if(jsonlist.getJSONObject(i).getJSONArray("children").size()>0)
			{
				//调用自己继续寻找
				findJson(jsonlist.getJSONObject(i).getJSONArray("children"));
			}
		}
	}
	
	
}
