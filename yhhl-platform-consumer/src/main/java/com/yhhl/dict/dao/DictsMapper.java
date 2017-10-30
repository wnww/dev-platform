package com.yhhl.dict.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.dict.model.Dicts;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>DictsDao<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，国版中心<br>
 */
public interface DictsMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<Dicts> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<Dicts> selectByDictTypeName(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(Dicts record) throws DataAccessException;

	int insertSelective(Dicts record) throws DataAccessException;

	Dicts selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(Dicts record) throws DataAccessException;

	int updateByPrimaryKey(Dicts record) throws DataAccessException;
}
