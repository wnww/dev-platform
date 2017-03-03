package com.yhhl.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.user.model.User;

public interface UserMapper {

	int getCount(Map<String, Object> filterMap) throws DataAccessException;
	
	List<User> getAll(SearchPageUtil searchPageUtil) throws DataAccessException;

	List<User> findPageByParams(Map<String, Object> filterMap) throws DataAccessException;

	int deleteByPrimaryKey(String id) throws DataAccessException;

	int insert(User record) throws DataAccessException;

	int insertSelective(User record) throws DataAccessException;

	User selectByPrimaryKey(String id) throws DataAccessException;

	int updateByPrimaryKeySelective(User record) throws DataAccessException;

	int updateByPrimaryKey(User record) throws DataAccessException;


}