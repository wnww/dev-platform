package com.yhhl.category.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.category.model.Category;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>CategoryDao<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，瀛海互联<br>
 */
public interface CategoryMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<Category> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<Category> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(Category record) throws DataAccessException;

	int insertSelective(Category record) throws DataAccessException;

	Category selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(Category record) throws DataAccessException;

	int updateByPrimaryKey(Category record) throws DataAccessException;
	
	String getMaxWbs(Map<String,Object> filterMap) throws DataAccessException;
}
