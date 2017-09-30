package com.yhhl.cart.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.cart.model.Carts;

/**
 * 
 * <br>
 * <b>功能：</b>CartsServiceI<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
public interface CartsServiceI {

	public final static Logger log= Logger.getLogger(CartsServiceI.class);

	Carts getById(String id);

	Page<Carts> getPage(Map<String,Object> filterMap, Page<Carts> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveCarts(Carts carts);
	
	public void updateCarts(Carts carts);
	
	public void deleteById(String id);

}
