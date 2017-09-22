package com.yhhl.roleauth.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.roleauth.model.RoleAuth;
import com.yhhl.roleuser.model.RoleUser;

/**
 * 
 * <br>
 * <b>功能：</b>RoleAuthServiceI<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海科技<br>
 */
public interface RoleAuthServiceI {

	public final static Logger log= Logger.getLogger(RoleAuthServiceI.class);

	RoleAuth getById(String id);

	Page<RoleAuth> getPage(Map<String,Object> filterMap, Page<RoleAuth> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	RoleAuth getByAuthIdAndRoleId(String authId, String roleId);
	int deleteByAuthIdAndRoleId(String authId, String roleId);
	
	public void saveRoleAuth(RoleAuth roleAuth);
	
	public void updateRoleAuth(RoleAuth roleAuth);
	
	public void deleteById(String id);

}
