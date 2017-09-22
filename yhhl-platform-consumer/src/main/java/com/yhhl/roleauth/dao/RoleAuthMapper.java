package com.yhhl.roleauth.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.roleauth.model.RoleAuth;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>RoleAuthDao<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，瀛海科技<br>
 */
public interface RoleAuthMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<RoleAuth> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<RoleAuth> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	RoleAuth getByAuthIdAndRoleId(Map<String, String> map);
	int deleteByAuthIdAndRoleId(Map<String, String> map);
	
	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(RoleAuth record) throws DataAccessException;

	int insertSelective(RoleAuth record) throws DataAccessException;

	RoleAuth selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(RoleAuth record) throws DataAccessException;

	int updateByPrimaryKey(RoleAuth record) throws DataAccessException;
}
