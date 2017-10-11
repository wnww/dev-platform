package com.yhhl.orderproduct.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yhhl.core.Page;
import com.yhhl.orderproduct.model.OrderProducts;

/**
 * 
 * <br>
 * <b>功能：</b>OrderProductsServiceI<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
public interface OrderProductsServiceI {

	public final static Logger log= Logger.getLogger(OrderProductsServiceI.class);

	OrderProducts getById(String id);

	Page<OrderProducts> getPage(Map<String,Object> filterMap, Page<OrderProducts> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveOrderProducts(OrderProducts orderProducts);
	
	public void updateOrderProducts(OrderProducts orderProducts);
	
	public void deleteById(String id);

	public List<OrderProducts> getByOrderId(Map<String,Object> map);
}
