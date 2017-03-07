package com.yhhl.roles.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.roles.model.Roles;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>RolesDao<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，国版中心<br>
 */
public interface RolesMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<Roles> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<Roles> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(Roles record) throws DataAccessException;

	int insertSelective(Roles record) throws DataAccessException;

	Roles selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(Roles record) throws DataAccessException;

	int updateByPrimaryKey(Roles record) throws DataAccessException;
}
