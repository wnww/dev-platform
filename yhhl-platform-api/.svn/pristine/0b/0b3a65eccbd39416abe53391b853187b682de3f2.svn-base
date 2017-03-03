package com.yhhl.action.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.action.model.BusinessAction;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>BusinessActionDao<br>
 * <b>作者：</b>www.yhhlkj.tk<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，国版中心<br>
 */
public interface BusinessActionMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<BusinessAction> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<BusinessAction> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(BusinessAction record) throws DataAccessException;

	int insertSelective(BusinessAction record) throws DataAccessException;

	BusinessAction selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(BusinessAction record) throws DataAccessException;

	int updateByPrimaryKey(BusinessAction record) throws DataAccessException;
}
