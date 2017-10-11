package com.yhhl.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

import com.yhhl.common.IdWorker;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.product.dao.ProductsMapper;
import com.yhhl.product.model.Products;

/**
 * 
 * <br>
 * <b>功能：</b>ProductsServiceImpl<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海科技<br>
 */
@Service("productsService")
public class ProductsServiceImpl implements ProductsServiceI {

	@Autowired
	private ProductsMapper productsMapper;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 保存
	 */
	@Override
	public void saveProducts(Products products) {
		products.setProdId(idWorker.buildId());
		productsMapper.insert(products);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<Products> getPage(Map<String, Object> filterMap, Page<Products> page, int pageNo, int pageSize) {
		int count = productsMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "modify_time desc", "create_time desc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Products> list = productsMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}
	
	@Override
	public Page<Products> getFrontPage(Map<String, Object> filterMap, Page<Products> page, int pageNo, int pageSize) {
		int count = productsMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "p.modify_time desc", "p.create_time desc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Products> list = productsMapper.getFrontPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	 *
	 * 分页查询的count
	 */
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return productsMapper.getCount(filterMap);
	}

	@Override
	public List<Products> getList(Map<String, Object> filterMap) {
		return productsMapper.getList(filterMap);
	}

	/**
	 * 更新
	 */
	@Override
	public void updateProducts(Products products) {
		productsMapper.updateByPrimaryKey(products);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public Products getById(String id) {
		return productsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		productsMapper.deleteByPrimaryKey(id);
	}

}
