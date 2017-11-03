package com.yhhl.lottery.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yhhl.core.Page;
import com.yhhl.lottery.model.Lottery;

/**
 * 
 * <br>
 * <b>功能：</b>LotteryServiceI<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */
public interface LotteryServiceI {

	public final static Logger log= Logger.getLogger(LotteryServiceI.class);

	Lottery getById(String id);

	Page<Lottery> getPage(Map<String,Object> filterMap, Page<Lottery> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveLottery(Lottery lottery);
	
	public void saveLotteryBatch(List<Lottery> lottery);
	
	public void updateLottery(Lottery lottery);
	
	public void deleteById(String id);
	
	public List<Lottery> getByIds(Map<String, Object> filterMap);

}
