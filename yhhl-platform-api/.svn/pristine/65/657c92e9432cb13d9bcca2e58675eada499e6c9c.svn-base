package com.yhhl.activity.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.activity.model.BusinessActivity;

/**
 * 
 * <br>
 * <b>功能：</b>BusinessActivityServiceI<br>
 * <b>作者：</b>www.yhhlkj.tk<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
public interface BusinessActivityServiceI {

	public final static Logger log= Logger.getLogger(BusinessActivityServiceI.class);

	BusinessActivity getById(String id);

	Page<BusinessActivity> getPage(Map<String,Object> filterMap, Page<BusinessActivity> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveBusinessActivity(BusinessActivity businessActivity);
	
	public void updateBusinessActivity(BusinessActivity businessActivity);
	
	public void deleteById(String id);

}
