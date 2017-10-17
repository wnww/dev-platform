package com.yhhl.platform.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhhl.core.Page;
import com.yhhl.expressfee.model.ExpressFee;
import com.yhhl.expressfee.service.ExpressFeeServiceI;

@Component("cacheOperate")
public class CacheOperateImpl implements CacheOperateI{

	@Autowired
	private ExpressFeeServiceI expressFeeService;
	
	@Override
	public void cacheExpressFee(ServletContext application) {
    	Map<String,Object> filterMap = new HashMap<String,Object>();
    	Page<ExpressFee> page = new Page<ExpressFee>();
    	page = expressFeeService.getPage(filterMap, page, 1, 0);
    	application.setAttribute("expressFeeData", JSONArray.toJSON(page.getResult()));
	}

	
}
