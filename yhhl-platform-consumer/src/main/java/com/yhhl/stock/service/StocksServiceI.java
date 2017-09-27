package com.yhhl.stock.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.stock.model.Stocks;

/**
 * 
 * <br>
 * <b>功能：</b>StocksServiceI<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
public interface StocksServiceI {

	public final static Logger log= Logger.getLogger(StocksServiceI.class);

	Stocks getById(String id);

	Page<Stocks> getPage(Map<String,Object> filterMap, Page<Stocks> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveStocks(Stocks stocks);
	
	public void updateStocks(Stocks stocks);
	
	public void deleteById(String id);

}
