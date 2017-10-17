package com.yhhl.expressfee.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.expressfee.model.ExpressFee;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>ExpressFeeDao<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，瀛海互联<br>
 */
public interface ExpressFeeMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<ExpressFee> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<ExpressFee> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(ExpressFee record) throws DataAccessException;

	int insertSelective(ExpressFee record) throws DataAccessException;

	ExpressFee selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(ExpressFee record) throws DataAccessException;

	int updateByPrimaryKey(ExpressFee record) throws DataAccessException;
}
