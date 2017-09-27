package com.yhhl.stock.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.stock.model.Stocks;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>StocksDao<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，国版中心<br>
 */
public interface StocksMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<Stocks> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<Stocks> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(Stocks record) throws DataAccessException;

	int insertSelective(Stocks record) throws DataAccessException;

	Stocks selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(Stocks record) throws DataAccessException;

	int updateByPrimaryKey(Stocks record) throws DataAccessException;
}
