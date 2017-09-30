package com.yhhl.cart.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.cart.dao.CartsMapper;
import com.yhhl.cart.model.Carts;
import com.yhhl.common.IdWorker;

/**
 * 
 * <br>
 * <b>功能：</b>CartsServiceImpl<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
@Service("cartsService")
public class CartsServiceImpl implements CartsServiceI {

	@Autowired
	private CartsMapper cartsMapper;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 保存
	 */
	@Override
	public void saveCarts(Carts carts){
		carts.setCartId(String.valueOf(idWorker.nextId()));
		cartsMapper.insert(carts);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<Carts> getPage(Map<String, Object> filterMap, Page<Carts> page, int pageNo, int pageSize) {
		int count = cartsMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "", "" };//排序字段，可以是多个 类似：{ "name  desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Carts> list = cartsMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	*
	* 分页查询的count
	*/
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return cartsMapper.getCount(filterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	public void updateCarts(Carts carts) {
		cartsMapper.updateByPrimaryKey(carts);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public Carts getById(String id) {
		return cartsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		cartsMapper.deleteByPrimaryKey(id);
	}

}
