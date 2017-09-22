package com.yhhl.order.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhhl.common.IdWorker;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.order.dao.OrdersMapper;
import com.yhhl.order.model.Orders;

/**
 * 
 * <br>
 * <b>功能：</b>OrdersServiceImpl<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersServiceI {

	@Autowired
	private OrdersMapper ordersMapper;

	@Autowired
	private IdWorker idWorker;
	
	/**
	 * 保存
	 */
	@Override
	public void saveOrders(Orders orders) {
		orders.setOrderId(String.valueOf(idWorker.nextId()));
		ordersMapper.insert(orders);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<Orders> getPage(Map<String, Object> filterMap, Page<Orders> page, int pageNo, int pageSize) {
		int count = ordersMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "modify_time desc" };// 排序字段，可以是多个 类似：{ "name desc",
												// "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Orders> list = ordersMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	 *
	 * 分页查询的count
	 */
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return ordersMapper.getCount(filterMap);
	}

	/**
	 * 更新
	 */
	@Override
	public void updateOrders(Orders orders) {
		ordersMapper.updateByPrimaryKey(orders);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public Orders getById(String id) {
		return ordersMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		ordersMapper.deleteByPrimaryKey(id);
	}

}
