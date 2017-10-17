package com.yhhl.expressfee.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.expressfee.dao.ExpressFeeMapper;
import com.yhhl.expressfee.model.ExpressFee;
import com.yhhl.common.IdWorker;

/**
 * 
 * <br>
 * <b>功能：</b>ExpressFeeServiceImpl<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */
@Service("expressFeeService")
public class ExpressFeeServiceImpl implements ExpressFeeServiceI {

	@Autowired
	private ExpressFeeMapper expressFeeMapper;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 保存
	 */
	@Override
	public void saveExpressFee(ExpressFee expressFee){
				expressFee.setExpressFeeId(idWorker.buildId());
				expressFeeMapper.insert(expressFee);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<ExpressFee> getPage(Map<String, Object> filterMap, Page<ExpressFee> page, int pageNo, int pageSize) {
		if(pageSize!=0){
			int count = expressFeeMapper.getCount(filterMap);
			page.setTotalCount(count);
		}
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "express_fee_id desc"};//排序字段，可以是多个 类似：{ "name  desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<ExpressFee> list = expressFeeMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	*
	* 分页查询的count
	*/
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return expressFeeMapper.getCount(filterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	public void updateExpressFee(ExpressFee expressFee) {
		expressFeeMapper.updateByPrimaryKey(expressFee);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public ExpressFee getById(String id) {
		return expressFeeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		expressFeeMapper.deleteByPrimaryKey(id);
	}

}
