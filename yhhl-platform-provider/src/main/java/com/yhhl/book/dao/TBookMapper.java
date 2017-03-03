package com.yhhl.book.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.book.model.TBook;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>TBookDao<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，国版中心<br>
 */
public interface TBookMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<TBook> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<TBook> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(TBook record) throws DataAccessException;

	int insertSelective(TBook record) throws DataAccessException;

	TBook selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(TBook record) throws DataAccessException;

	int updateByPrimaryKey(TBook record) throws DataAccessException;
}
