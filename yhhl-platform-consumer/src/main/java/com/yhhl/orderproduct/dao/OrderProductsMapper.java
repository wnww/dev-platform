package com.yhhl.orderproduct.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.orderproduct.model.OrderProducts;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>OrderProductsDao<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，国版中心<br>
 */
public interface OrderProductsMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<OrderProducts> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<OrderProducts> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(OrderProducts record) throws DataAccessException;

	int insertSelective(OrderProducts record) throws DataAccessException;

	OrderProducts selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(OrderProducts record) throws DataAccessException;

	int updateByPrimaryKey(OrderProducts record) throws DataAccessException;
}
