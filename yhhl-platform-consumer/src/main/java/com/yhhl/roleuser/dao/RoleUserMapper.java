package com.yhhl.roleuser.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.roleuser.model.RoleUser;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>RoleUserDao<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，国版中心<br>
 */
public interface RoleUserMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<RoleUser> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<RoleUser> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;
	
	RoleUser getByUserIdAndRoleId(Map<String, String> map);
	int deleteByUserIdAndRoleId(Map<String, String> map);

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(RoleUser record) throws DataAccessException;

	int insertSelective(RoleUser record) throws DataAccessException;

	RoleUser selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(RoleUser record) throws DataAccessException;

	int updateByPrimaryKey(RoleUser record) throws DataAccessException;
}
