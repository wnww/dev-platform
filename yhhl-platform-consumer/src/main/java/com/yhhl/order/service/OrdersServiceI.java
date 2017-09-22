package com.yhhl.order.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.order.model.Orders;

/**
 * 
 * <br>
 * <b>功能：</b>OrdersServiceI<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
public interface OrdersServiceI {

	public final static Logger log= Logger.getLogger(OrdersServiceI.class);

	Orders getById(String id);

	Page<Orders> getPage(Map<String,Object> filterMap, Page<Orders> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveOrders(Orders orders);
	
	public void updateOrders(Orders orders);
	
	public void deleteById(String id);

}
