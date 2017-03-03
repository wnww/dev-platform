package com.yhhl.books.service;

import java.util.Map;

import org.apache.log4j.Logger;

import com.yhhl.books.model.Books;
import com.yhhl.core.Page;
import com.yhhl.wsts.common.TransactionContextMain;

/**
 * 
 * <br>
 * <b>功能：</b>BooksServiceI<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
public interface BooksServiceI {

	public final static Logger log= Logger.getLogger(BooksServiceI.class);

	Books getById(String id);

	Page<Books> getPage(Map<String,Object> filterMap, Page<Books> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveBooks(Books books);
	
	public void updateBooks(Books books);
	
	public void deleteById(String id);

}
