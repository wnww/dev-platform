package com.yhhl.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhhl.common.IdWorker;
import com.yhhl.common.MD5Utils;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.roleuser.dao.RoleUserMapper;
import com.yhhl.roleuser.model.RoleUser;
import com.yhhl.user.dao.UserMapper;
import com.yhhl.user.model.User;


@Service("userService")
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleUserMapper roleUserMapper;
	
	@Autowired
	private IdWorker idWorker;
	
	

	@Override
	public void registerFromFront(User user) {
		if(StringUtil.isEmpty(user.getId())){
			user.setId(idWorker.buildId());
		}
		try{
			user.setPwd(MD5Utils.MD5(user.getPwd()));
			userMapper.insert(user);
			RoleUser roleUser = new RoleUser();
			roleUser.setId(idWorker.buildId());
			// 前台普通用户角色
			roleUser.setRoleId("c9985e7c752e4862ac52a0e45ce02b51");
			roleUser.setUserId(user.getId());
			roleUserMapper.insert(roleUser);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * 保存
	 */
	public void saveUser(User user) {
		user.setId(idWorker.buildId());
		// 准备远程调用参数
		try{
			user.setPwd(MD5Utils.MD5(user.getPwd()));
			userMapper.insert(user);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void confirmSaveUser(User user) {
		user.setName(user.getName()+"-确认操作");
		userMapper.updateByPrimaryKey(user);
	}

	public void concelSaveUser(User user) {
		user.setName(user.getName()+"-取消操作");
		userMapper.updateByPrimaryKey(user);
		
	}
	
	@Override
	public User getByUserNameAndPwd(String userName, String pwd) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", userName);
		map.put("pwd", pwd);
		return userMapper.login(map);
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
