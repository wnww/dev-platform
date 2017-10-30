package com.yhhl.weixin.util;

import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;

public class MessageUtil {

	public static String buildTemplateMsg(String orderId,String ownerRealName, String createTime){
		JSONObject json = new JSONObject();
		json.put("touser", "ozol90r0De_-rDP0Hd6qHGzW-g38");
		json.put("template_id", "3aHAiMcqMrvt3Q6RNAmKIwLVrd2tc-kpqy7x4a7vLd4");
		json.put("url", "http://www.yhsoft.top/");
		json.put("topcolor","#FF0000");
		
		JSONObject data = new JSONObject();
		data.put("orderId", buildParam(orderId,"#0000FF"));
		data.put("ownerRealName", buildParam(ownerRealName,"#0000FF"));
		data.put("createTime", buildParam(createTime,"#0000FF"));
		json.put("data", data);
		return json.toJSONString();
	}
	
	private static TreeMap<String, String> buildParam(String value, String color){
		TreeMap<String, String> params = new TreeMap<String, String>();  
        params.put("value", value);  
        params.put("color", color);  
        return params;  
	}
}
