package com.yhhl.books.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.books.model.Books;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>BooksDao<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，国版中心<br>
 */
public interface BooksMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<Books> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<Books> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(Books record) throws DataAccessException;

	int insertSelective(Books record) throws DataAccessException;

	Books selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(Books record) throws DataAccessException;

	int updateByPrimaryKey(Books record) throws DataAccessException;
}
