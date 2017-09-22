package com.yhhl.roleuser.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.roleuser.model.RoleUser;

/**
 * 
 * <br>
 * <b>功能：</b>RoleUserServiceI<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海科技<br>
 */
public interface RoleUserServiceI {

	public final static Logger log= Logger.getLogger(RoleUserServiceI.class);

	RoleUser getById(String id);

	Page<RoleUser> getPage(Map<String,Object> filterMap, Page<RoleUser> page, int pageNo, int pageSize);
	
	RoleUser getByUserIdAndRoleId(String userId, String roleId);
	int deleteByUserIdAndRoleId(String userId, String roleId);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveRoleUser(RoleUser roleUser);
	
	public void updateRoleUser(RoleUser roleUser);
	
	public void deleteById(String id);

}
