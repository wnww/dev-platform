package com.yhhl.book.service;

import org.apache.log4j.Logger;
import org.mengyun.tcctransaction.api.TransactionContext;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.book.model.TBook;

/**
 * 
 * <br>
 * <b>功能：</b>TBookServiceI<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
public interface TBookServiceI {

	public final static Logger log= Logger.getLogger(TBookServiceI.class);

	TBook getById(String id);

	Page<TBook> getPage(Map<String,Object> filterMap, Page<TBook> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveTBook(TransactionContext context,TBook tBook);
	
	public void updateTBook(TBook tBook);
	
	public void deleteById(String id);

}
