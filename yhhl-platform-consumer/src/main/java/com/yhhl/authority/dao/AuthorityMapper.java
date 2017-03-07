package com.yhhl.authority.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.authority.model.Authority;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>AuthorityDao<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，国版中心<br>
 */
public interface AuthorityMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<Authority> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<Authority> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(Authority record) throws DataAccessException;

	int insertSelective(Authority record) throws DataAccessException;

	Authority selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(Authority record) throws DataAccessException;

	int updateByPrimaryKey(Authority record) throws DataAccessException;
}
