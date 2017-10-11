package com.yhhl.orderproduct.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhhl.common.IdWorker;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.orderproduct.dao.OrderProductsMapper;
import com.yhhl.orderproduct.model.OrderProducts;

/**
 * 
 * <br>
 * <b>功能：</b>OrderProductsServiceImpl<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
@Service("orderProductsService")
public class OrderProductsServiceImpl implements OrderProductsServiceI {

	@Autowired
	private OrderProductsMapper orderProductsMapper;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 保存
	 */
	@Override
	public void saveOrderProducts(OrderProducts orderProducts) {
		orderProducts.setOrderProdId(idWorker.buildId());
		orderProductsMapper.insert(orderProducts);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<OrderProducts> getPage(Map<String, Object> filterMap, Page<OrderProducts> page, int pageNo,
			int pageSize) {
		int count = orderProductsMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "order_prod_id desc"};// 排序字段，可以是多个 类似：{ "name desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<OrderProducts> list = orderProductsMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}
	
	/**
	 * 根据订单ID查询订单商品
	 */
	@Override
	public List<OrderProducts> getByOrderId(Map<String, Object> map) {
		return orderProductsMapper.getByOrderId(map);
	}

	/**
	 *
	 * 分页查询的count
	 */
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return orderProductsMapper.getCount(filterMap);
	}

	/**
	 * 更新
	 */
	@Override
	public void updateOrderProducts(OrderProducts orderProducts) {
		orderProductsMapper.updateByPrimaryKey(orderProducts);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public OrderProducts getById(String id) {
		return orderProductsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		orderProductsMapper.deleteByPrimaryKey(id);
	}

}
