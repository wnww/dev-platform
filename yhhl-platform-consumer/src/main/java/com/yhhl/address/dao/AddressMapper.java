package com.yhhl.address.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.address.model.Address;

//import com.base.dao.BaseDao;
/**
 * 
 * <br>
 * <b>功能：</b>AddressDao<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，国版中心<br>
 */
public interface AddressMapper {
	
	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<Address> getPage(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<Address> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(Address record) throws DataAccessException;

	int insertSelective(Address record) throws DataAccessException;

	Address selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(Address record) throws DataAccessException;

	int updateByPrimaryKey(Address record) throws DataAccessException;
}
