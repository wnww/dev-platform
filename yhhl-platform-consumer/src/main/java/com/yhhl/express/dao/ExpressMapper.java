package com.yhhl.express.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.express.model.Express;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>ExpressDao<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，瀛海互联<br>
 */
public interface ExpressMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<Express> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<Express> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(Express record) throws DataAccessException;

	int insertSelective(Express record) throws DataAccessException;

	Express selectByPrimaryKey(String id) throws DataAccessException;
	
	Express selectByOrderId(Map<String,Object> map) throws DataAccessException;

	int updateByPrimaryKeySelective(Express record) throws DataAccessException;

	int updateByPrimaryKey(Express record) throws DataAccessException;
}
