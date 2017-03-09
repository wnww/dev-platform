package com.yhhl.roleuser.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.roleuser.dao.RoleUserMapper;
import com.yhhl.roleuser.model.RoleUser;

/**
 * 
 * <br>
 * <b>功能：</b>RoleUserServiceImpl<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
@Service("roleUserService")
public class RoleUserServiceImpl implements RoleUserServiceI {

	private RoleUserMapper roleUserMapper;

	public RoleUserMapper getRoleUserMapper() {
		return roleUserMapper;
	}

	@Autowired
	public void setRoleUserMapper(RoleUserMapper roleUserMapper) {
		this.roleUserMapper = roleUserMapper;
	}

	/**
	 * 保存
	 */
	@Override
	public void saveRoleUser(RoleUser roleUser){
				roleUser.setId(UUID.randomUUID().toString().replace("-", ""));
				roleUserMapper.insert(roleUser);
	}
	
	@Override
	public RoleUser getByUserIdAndRoleId(String userId,String roleId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		return roleUserMapper.getByUserIdAndRoleId(map);
	}
	

	@Override
	public int deleteByUserIdAndRoleId(String userId, String roleId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		return roleUserMapper.deleteByUserIdAndRoleId(map);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<RoleUser> getPage(Map<String, Object> filterMap, Page<RoleUser> page, int pageNo, int pageSize) {
		int count = roleUserMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "", "" };//排序字段，可以是多个 类似：{ "name  desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<RoleUser> list = roleUserMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	*
	* 分页查询的count
	*/
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return roleUserMapper.getCount(filterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	public void updateRoleUser(RoleUser roleUser) {
		roleUserMapper.updateByPrimaryKey(roleUser);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public RoleUser getById(String id) {
		return roleUserMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		roleUserMapper.deleteByPrimaryKey(id);
	}

}
