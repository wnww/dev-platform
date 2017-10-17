package com.yhhl.collect.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.collect.model.Collects;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>CollectsDao<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，瀛海互联<br>
 */
public interface CollectsMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<Collects> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<Collects> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;
	
	int deleteByProperty(Map<String, Object> filterMap);

	int insert(Collects record) throws DataAccessException;

	int insertSelective(Collects record) throws DataAccessException;

	Collects selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(Collects record) throws DataAccessException;

	int updateByPrimaryKey(Collects record) throws DataAccessException;
}
