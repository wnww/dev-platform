package com.yhhl.roles.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.roles.dao.RolesMapper;
import com.yhhl.roles.model.Roles;

/**
 * 
 * <br>
 * <b>功能：</b>RolesServiceImpl<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海科技<br>
 */
@Service("rolesService")
public class RolesServiceImpl implements RolesServiceI {

	private RolesMapper rolesMapper;

	public RolesMapper getRolesMapper() {
		return rolesMapper;
	}

	@Autowired
	public void setRolesMapper(RolesMapper rolesMapper) {
		this.rolesMapper = rolesMapper;
	}

	/**
	 * 保存
	 */
	public void saveRoles(Roles roles) {
		roles.setRoleId(UUID.randomUUID().toString().replace("-", ""));
		rolesMapper.insert(roles);
	}

	/**
	 * 分页查询
	 */
	public Page<Roles> getPage(Map<String, Object> filterMap, Page<Roles> page, int pageNo, int pageSize) {
		int count = rolesMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "role_name asc" };// 排序字段，可以是多个 类似：{ "name desc", "id
												// asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Roles> list = rolesMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	public Page<Roles> getSelectPage(Map<String, Object> filterMap, Page<Roles> page, int pageNo, int pageSize) {
		int count = rolesMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "role_name asc" };// 排序字段，可以是多个 类似：{ "name desc", "id
												// asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Roles> list = rolesMapper.getSelectPage(searchPageUtil);
		page.setResult(list);
		return page;
	}
	
	

	@Override
	public List<Roles> getUserRole(Map<String, Object> map) {
		return rolesMapper.getUserRole(map);
	}

	/**
	 *
	 * 分页查询的count
	 */
	public int getCount(Map<String, Object> filterMap) {
		return rolesMapper.getCount(filterMap);
	}

	/**
	 * 更新
	 */
	public void updateRoles(Roles roles) {
		rolesMapper.updateByPrimaryKey(roles);
	}

	/**
	 * 根据ID获取实体对象
	 */
	public Roles getById(String id) {
		return rolesMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	public void deleteById(String id) {
		rolesMapper.deleteByPrimaryKey(id);
	}

}
