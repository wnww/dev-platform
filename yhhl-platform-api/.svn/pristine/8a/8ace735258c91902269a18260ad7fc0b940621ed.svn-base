package com.yhhl.activity.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.activity.model.BusinessActivity;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>BusinessActivityDao<br>
 * <b>作者：</b>www.yhhlkj.tk<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，国版中心<br>
 */
public interface BusinessActivityMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<BusinessActivity> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<BusinessActivity> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(BusinessActivity record) throws DataAccessException;

	int insertSelective(BusinessActivity record) throws DataAccessException;

	BusinessActivity selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(BusinessActivity record) throws DataAccessException;

	int updateByPrimaryKey(BusinessActivity record) throws DataAccessException;
}
