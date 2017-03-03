package com.yhhl.common;

import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName JsonUtil
 * @Description:Json工具类
 * @author lihao
 * @date 2013-4-23
 */
public class JsonUtils {

	/**
	 * @Description:对象转换Json
	 * @param object
	 * @return JSONObject
	 * @throws
	 * @author lihao
	 */
	public static JSONObject convertObjectToJson(Object object) {
		return JSONObject.fromObject(object);
	}

	/**
	 * @Description:Map转换Json
	 * @param map
	 * @return JSONObject
	 * @throws
	 * @author lihao
	 */
	public static JSONObject convertMapToJson(Map<?, ?> map) {
		JSONObject jsonObject = null;
		if (map != null) {
			jsonObject = convertObjectToJson(map);
		}
		return jsonObject;
	}
	
	/**
	 * @Description:List转JSONArray
	 * @param list
	 * @return
	 * JSONArray
	 * @throws 
	 * @author lihao
	 */
	public static JSONArray convertListToJson(List<Object> list){
		JSONArray jsonArray = null;
		if(list!=null&&list.size()>0){
			jsonArray = new JSONArray();
			JSONObject jsonObject = null;
			for(int i=0;i<list.size();i++){
				jsonObject = convertObjectToJson(list.get(i));
				jsonArray.add(jsonObject);
			}
		}
		return jsonArray;
	}
	
}
