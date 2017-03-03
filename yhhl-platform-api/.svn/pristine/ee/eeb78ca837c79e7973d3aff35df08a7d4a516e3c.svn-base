package com.yhhl.action.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.action.model.BusinessAction;

/**
 * 
 * <br>
 * <b>功能：</b>BusinessActionServiceI<br>
 * <b>作者：</b>www.yhhlkj.tk<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
public interface BusinessActionServiceI {

	public final static Logger log= Logger.getLogger(BusinessActionServiceI.class);

	BusinessAction getById(String id);

	Page<BusinessAction> getPage(Map<String,Object> filterMap, Page<BusinessAction> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveBusinessAction(BusinessAction businessAction);
	
	public void updateBusinessAction(BusinessAction businessAction);
	
	public void deleteById(String id);

}
