package com.yhhl.user.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhhl.book.dao.TBookMapper;
import com.yhhl.book.model.TBook;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.user.dao.UserMapper;
import com.yhhl.user.model.User;


@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private UserMapper userMapper;

	private TBookMapper tBookMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public TBookMapper getTBookMapper() {
		return tBookMapper;
	}

	@Autowired
	public void setTBookMapper(TBookMapper tBookMapper) {
		this.tBookMapper = tBookMapper;
	}

	/**
	 * 保存
	 */
	public void saveUser(User user){
		TBook book = new TBook();
		if(user.getId()==null || "".equals(user.getId())){
			user.setId(UUID.randomUUID().toString().replace("-", ""));
			book.setId(UUID.randomUUID().toString().replace("-", ""));
		}else{
			book.setId(UUID.randomUUID().toString().replace("-", ""));
		}
		String temp = UUID.randomUUID().toString();
		book.setBookName(user.getName()+temp.substring(0,temp.indexOf("-")));
		
		userMapper.insert(user);
		tBookMapper.insert(book);
	}

	public Page<User> getAll(Map<String, Object> filterMap, Page<User> page, int pageNo, int pageSize) {
		int count = userMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "name  desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<User> list = userMapper.getAll(searchPageUtil);
		page.setResult(list);
		return page;
	}

	public int getCount(Map<String, Object> filterMap) {
		return userMapper.getCount(filterMap);
	}
	
	/**
	 * 更新用户信息
	 */
	public void updateUser(User user) {
		userMapper.updateByPrimaryKey(user);
	}

	/**
	 * 分页列表
	 */
	public Page<User> getByPage(Page<User> pageUser, Map<String, Object> filterMap, int pageNo, int pageSize) {
		Page<User> dataPage = new Page<User>(pageSize);
		dataPage.setPageNo(pageNo);
		
		if(pageUser!=null){
			dataPage = pageUser;
		}		
		//分页所需条件
		filterMap.put("offset", (dataPage.getPageNo() - 1)* dataPage.getPageSize() + 1);
		filterMap.put("limit", dataPage.getPageNo()* dataPage.getPageSize());
		if(!(dataPage.getTotalCount()>0)){
			dataPage.setTotalCount(userMapper.getCount(filterMap));
		}
		dataPage.setResult(userMapper.findPageByParams(filterMap));
		return dataPage;
	}

	/**
	 * 根据用户ID获取用户对象
	 */
	public User getById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除用户信息
	 */
	public void deleteById(String id) {
		userMapper.deleteByPrimaryKey(id);
	}

}
