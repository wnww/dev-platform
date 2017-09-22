package com.yhhl.roles.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.roles.model.Roles;

/**
 * 
 * <br>
 * <b>功能：</b>RolesServiceI<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海科技<br>
 */
public interface RolesServiceI {

	public final static Logger log= Logger.getLogger(RolesServiceI.class);

	Roles getById(String id);

	Page<Roles> getPage(Map<String,Object> filterMap, Page<Roles> page, int pageNo, int pageSize);
	Page<Roles> getSelectPage(Map<String,Object> filterMap, Page<Roles> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveRoles(Roles roles);
	
	public void updateRoles(Roles roles);
	
	public void deleteById(String id);

}
