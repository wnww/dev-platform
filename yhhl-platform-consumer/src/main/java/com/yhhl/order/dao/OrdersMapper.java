package com.yhhl.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.order.model.Orders;
import com.yhhl.order.model.OrdersVo;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>OrdersDao<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，国版中心<br>
 */
public interface OrdersMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<Orders> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;
	
	List<OrdersVo> getMyOrderVoList(Map<String,Object> map) throws DataAccessException;

	List<Orders> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(Orders record) throws DataAccessException;

	int insertSelective(Orders record) throws DataAccessException;

	Orders selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(Orders record) throws DataAccessException;

	int updateByPrimaryKey(Orders record) throws DataAccessException;
}
