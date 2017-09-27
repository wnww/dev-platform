package com.yhhl.dict.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.dict.model.Dicts;

/**
 * 
 * <br>
 * <b>功能：</b>DictsServiceI<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
public interface DictsServiceI {

	public final static Logger log= Logger.getLogger(DictsServiceI.class);

	Dicts getById(String id);

	Page<Dicts> getPage(Map<String,Object> filterMap, Page<Dicts> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveDicts(Dicts dicts);
	
	public void updateDicts(Dicts dicts);
	
	public void deleteById(String id);

}
