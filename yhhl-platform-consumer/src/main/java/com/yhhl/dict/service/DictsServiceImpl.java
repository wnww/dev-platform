package com.yhhl.dict.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhhl.common.IdWorker;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.dict.dao.DictsMapper;
import com.yhhl.dict.model.Dicts;

/**
 * 
 * <br>
 * <b>功能：</b>DictsServiceImpl<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
@Service("dictsService")
public class DictsServiceImpl implements DictsServiceI {

	@Autowired
	private DictsMapper dictsMapper;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 保存
	 */
	@Override
	public void saveDicts(Dicts dicts) {
		dicts.setDictId(idWorker.buildId());
		dictsMapper.insert(dicts);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<Dicts> getPage(Map<String, Object> filterMap, Page<Dicts> page, int pageNo, int pageSize) {
		if(pageSize!=0){
			int count = dictsMapper.getCount(filterMap);
			page.setTotalCount(count);
		}
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "dict_type_name asc", "dict_id desc" };// 排序字段，可以是多个 类似：{ "name desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Dicts> list = dictsMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	 *
	 * 分页查询的count
	 */
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return dictsMapper.getCount(filterMap);
	}

	/**
	 * 更新
	 */
	@Override
	public void updateDicts(Dicts dicts) {
		dictsMapper.updateByPrimaryKey(dicts);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public Dicts getById(String id) {
		return dictsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		dictsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Dicts> selectByDictTypeName(Map<String, Object> filterMap) {
		return dictsMapper.selectByDictTypeName(filterMap);
	}
	

}
