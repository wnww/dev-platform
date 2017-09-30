package com.yhhl.cart.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.cart.model.Carts;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>CartsDao<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，国版中心<br>
 */
public interface CartsMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<Carts> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<Carts> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(Carts record) throws DataAccessException;

	int insertSelective(Carts record) throws DataAccessException;

	Carts selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(Carts record) throws DataAccessException;

	int updateByPrimaryKey(Carts record) throws DataAccessException;
}
