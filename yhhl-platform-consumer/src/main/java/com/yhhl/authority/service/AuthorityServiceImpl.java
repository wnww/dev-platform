package com.yhhl.authority.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.authority.dao.AuthorityMapper;
import com.yhhl.authority.model.Authority;

/**
 * 
 * <br>
 * <b>功能：</b>AuthorityServiceImpl<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityServiceI {

	private AuthorityMapper authorityMapper;

	public AuthorityMapper getAuthorityMapper() {
		return authorityMapper;
	}

	@Autowired
	public void setAuthorityMapper(AuthorityMapper authorityMapper) {
		this.authorityMapper = authorityMapper;
	}

	/**
	 * 保存
	 */
	@Override
	public void saveAuthority(Authority authority){
				authority.setAuthId(UUID.randomUUID().toString().replace("-", ""));
				authorityMapper.insert(authority);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<Authority> getPage(Map<String, Object> filterMap, Page<Authority> page, int pageNo, int pageSize) {
		int count = authorityMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "", "" };//排序字段，可以是多个 类似：{ "name  desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Authority> list = authorityMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	*
	* 分页查询的count
	*/
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return authorityMapper.getCount(filterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	public void updateAuthority(Authority authority) {
		authorityMapper.updateByPrimaryKey(authority);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public Authority getById(String id) {
		return authorityMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		authorityMapper.deleteByPrimaryKey(id);
	}

}
