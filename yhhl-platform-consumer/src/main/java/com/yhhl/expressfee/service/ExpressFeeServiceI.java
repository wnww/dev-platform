package com.yhhl.expressfee.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.expressfee.model.ExpressFee;

/**
 * 
 * <br>
 * <b>功能：</b>ExpressFeeServiceI<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */
public interface ExpressFeeServiceI {

	public final static Logger log= Logger.getLogger(ExpressFeeServiceI.class);

	ExpressFee getById(String id);

	Page<ExpressFee> getPage(Map<String,Object> filterMap, Page<ExpressFee> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveExpressFee(ExpressFee expressFee);
	
	public void updateExpressFee(ExpressFee expressFee);
	
	public void deleteById(String id);

}
