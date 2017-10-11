package com.yhhl.stock.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhhl.common.IdWorker;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.stock.dao.StocksMapper;
import com.yhhl.stock.model.Stocks;

/**
 * 
 * <br>
 * <b>功能：</b>StocksServiceImpl<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
@Service("stocksService")
public class StocksServiceImpl implements StocksServiceI {

	@Autowired
	private StocksMapper stocksMapper;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 保存
	 */
	@Override
	public void saveStocks(Stocks stocks) {
		stocks.setStockId(idWorker.buildId());
		stocksMapper.insert(stocks);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<Stocks> getPage(Map<String, Object> filterMap, Page<Stocks> page, int pageNo, int pageSize) {
		int count = stocksMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "sk.stock_id desc"};// 排序字段，可以是多个 类似：{ "name desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Stocks> list = stocksMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	 *
	 * 分页查询的count
	 */
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return stocksMapper.getCount(filterMap);
	}

	/**
	 * 更新
	 */
	@Override
	public void updateStocks(Stocks stocks) {
		stocksMapper.updateByPrimaryKey(stocks);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public Stocks getById(String id) {
		return stocksMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		stocksMapper.deleteByPrimaryKey(id);
	}

}
