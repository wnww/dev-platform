package com.yhhl.express.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.express.model.Express;

/**
 * 
 * <br>
 * <b>功能：</b>ExpressServiceI<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */
public interface ExpressServiceI {

	public final static Logger log= Logger.getLogger(ExpressServiceI.class);

	Express getById(String id);
	
	Express selectByOrderId(Map<String,Object> map);

	Page<Express> getPage(Map<String,Object> filterMap, Page<Express> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveExpress(Express express);
	
	public void updateExpress(Express express);
	
	public void deleteById(String id);
	
	public String getExpressInfo(String expressComCode,String expressCode);

}
