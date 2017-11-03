package com.yhhl.lottery.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.lottery.model.Lottery;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>LotteryDao<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，瀛海互联<br>
 */
public interface LotteryMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<Lottery> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<Lottery> getByIds(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(Lottery record) throws DataAccessException;
	
	int insertBatch(List<Lottery> record) throws DataAccessException;

	int insertSelective(Lottery record) throws DataAccessException;

	Lottery selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(Lottery record) throws DataAccessException;

	int updateByPrimaryKey(Lottery record) throws DataAccessException;
}
