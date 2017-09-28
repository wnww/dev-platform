package com.yhhl.product.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.product.model.Products;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>ProductsDao<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，瀛海科技<br>
 */
public interface ProductsMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<Products> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;
	
	List<Products> getFrontPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<Products> getList(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(Products record) throws DataAccessException;

	int insertSelective(Products record) throws DataAccessException;

	Products selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(Products record) throws DataAccessException;

	int updateByPrimaryKey(Products record) throws DataAccessException;
}
