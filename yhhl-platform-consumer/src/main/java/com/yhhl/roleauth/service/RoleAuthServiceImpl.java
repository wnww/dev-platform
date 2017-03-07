package com.yhhl.roleauth.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.roleauth.dao.RoleAuthMapper;
import com.yhhl.roleauth.model.RoleAuth;

/**
 * 
 * <br>
 * <b>功能：</b>RoleAuthServiceImpl<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
@Service("roleAuthService")
public class RoleAuthServiceImpl implements RoleAuthServiceI {

	private RoleAuthMapper roleAuthMapper;

	public RoleAuthMapper getRoleAuthMapper() {
		return roleAuthMapper;
	}

	@Autowired
	public void setRoleAuthMapper(RoleAuthMapper roleAuthMapper) {
		this.roleAuthMapper = roleAuthMapper;
	}

	/**
	 * 保存
	 */
	@Override
	public void saveRoleAuth(RoleAuth roleAuth){
				roleAuth.setId(UUID.randomUUID().toString().replace("-", ""));
				roleAuthMapper.insert(roleAuth);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<RoleAuth> getPage(Map<String, Object> filterMap, Page<RoleAuth> page, int pageNo, int pageSize) {
		int count = roleAuthMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "", "" };//排序字段，可以是多个 类似：{ "name  desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<RoleAuth> list = roleAuthMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	*
	* 分页查询的count
	*/
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return roleAuthMapper.getCount(filterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	public void updateRoleAuth(RoleAuth roleAuth) {
		roleAuthMapper.updateByPrimaryKey(roleAuth);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public RoleAuth getById(String id) {
		return roleAuthMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		roleAuthMapper.deleteByPrimaryKey(id);
	}

}
