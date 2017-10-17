package com.yhhl.collect.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.collect.model.Collects;

/**
 * 
 * <br>
 * <b>功能：</b>CollectsServiceI<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */
public interface CollectsServiceI {

	public final static Logger log= Logger.getLogger(CollectsServiceI.class);

	Collects getById(String id);

	Page<Collects> getPage(Map<String,Object> filterMap, Page<Collects> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveCollects(Collects collects);
	
	public void updateCollects(Collects collects);
	
	public void deleteById(String id);
	
	public void deleteByProperty(Map<String,Object> filterMap);

}
