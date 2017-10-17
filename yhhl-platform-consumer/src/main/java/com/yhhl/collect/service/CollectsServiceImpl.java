package com.yhhl.collect.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.collect.dao.CollectsMapper;
import com.yhhl.collect.model.Collects;
import com.yhhl.common.IdWorker;

/**
 * 
 * <br>
 * <b>功能：</b>CollectsServiceImpl<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */
@Service("collectsService")
public class CollectsServiceImpl implements CollectsServiceI {

	@Autowired
	private CollectsMapper collectsMapper;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 保存
	 */
	@Override
	public void saveCollects(Collects collects){
		collects.setCollectId(idWorker.buildId());
		collectsMapper.insert(collects);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<Collects> getPage(Map<String, Object> filterMap, Page<Collects> page, int pageNo, int pageSize) {
		int count = collectsMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "cs.create_time desc"};//排序字段，可以是多个 类似：{ "name  desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Collects> list = collectsMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	*
	* 分页查询的count
	*/
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return collectsMapper.getCount(filterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	public void updateCollects(Collects collects) {
		collectsMapper.updateByPrimaryKey(collects);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public Collects getById(String id) {
		return collectsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		collectsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteByProperty(Map<String, Object> filterMap) {
		collectsMapper.deleteByProperty(filterMap);
	}

}
