package com.yhhl.product.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yhhl.core.Page;
import com.yhhl.product.model.Products;

/**
 * 
 * <br>
 * <b>功能：</b>ProductsServiceI<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海科技<br>
 */
public interface ProductsServiceI {

	public final static Logger log= Logger.getLogger(ProductsServiceI.class);

	Products getById(String id);

	Page<Products> getPage(Map<String,Object> filterMap, Page<Products> page, int pageNo, int pageSize);
	
	Page<Products> getFrontPage(Map<String,Object> filterMap, Page<Products> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveProducts(Products products);
	
	public void updateProducts(Products products);
	
	public void deleteById(String id);
	
	List<Products> getList(Map<String,Object> filterMap);

}
