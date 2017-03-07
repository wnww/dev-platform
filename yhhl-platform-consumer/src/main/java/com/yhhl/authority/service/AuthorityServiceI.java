package com.yhhl.authority.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.authority.model.Authority;

/**
 * 
 * <br>
 * <b>功能：</b>AuthorityServiceI<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
public interface AuthorityServiceI {

	public final static Logger log= Logger.getLogger(AuthorityServiceI.class);

	Authority getById(String id);

	Page<Authority> getPage(Map<String,Object> filterMap, Page<Authority> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveAuthority(Authority authority);
	
	public void updateAuthority(Authority authority);
	
	public void deleteById(String id);

}
