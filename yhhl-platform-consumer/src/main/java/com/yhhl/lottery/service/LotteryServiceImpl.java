package com.yhhl.lottery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.lottery.dao.LotteryMapper;
import com.yhhl.lottery.model.Lottery;
import com.yhhl.common.IdWorker;

/**
 * 
 * <br>
 * <b>功能：</b>LotteryServiceImpl<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */
@Service("lotteryService")
public class LotteryServiceImpl implements LotteryServiceI {

	@Autowired
	private LotteryMapper lotteryMapper;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 保存
	 */
	@Override
	public void saveLottery(Lottery lottery){
		lottery.setExpectId(idWorker.buildId());
		lotteryMapper.insert(lottery);
	}
	
	

	@Override
	public void saveLotteryBatch(List<Lottery> lottery) {
		lotteryMapper.insertBatch(lottery);
	}

	

	@Override
	public List<Lottery> getByIds(Map<String, Object> filterMap) {
		return lotteryMapper.getByIds(filterMap);
	}



	/**
	 * 分页查询
	 */
	@Override
	public Page<Lottery> getPage(Map<String, Object> filterMap, Page<Lottery> page, int pageNo, int pageSize) {
		int count = lotteryMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "", "" };//排序字段，可以是多个 类似：{ "name  desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Lottery> list = lotteryMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	*
	* 分页查询的count
	*/
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return lotteryMapper.getCount(filterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	public void updateLottery(Lottery lottery) {
		lotteryMapper.updateByPrimaryKey(lottery);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public Lottery getById(String id) {
		return lotteryMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		lotteryMapper.deleteByPrimaryKey(id);
	}

}
