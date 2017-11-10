package com.yhhl.lottery.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yhhl.common.DateUtils;
import com.yhhl.common.ElevenSelectFive;
import com.yhhl.common.ResultBean;
import com.yhhl.common.WebUtil;
import com.yhhl.lottery.model.Data;
import com.yhhl.lottery.model.Expect;
import com.yhhl.lottery.model.Lottery;

/**
 * 
 * <br>
 * <b>功能：</b>LotteryServiceImpl<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */
//@Lazy(false)
@Service("lotteryScheduleService")
public class LotteryScheduleServiceImpl implements LotteryScheduleServiceI {
	@Autowired
	private LotteryServiceI lotteryService; 
	
	//@Scheduled(cron = "0 */1 09-22  * * ?") // 每天9~22点每  1  分钟执行一次
	@Override
	public void getLotteryAPIData() throws Exception{
		List<Lottery> list = new ArrayList<Lottery>();
		String getUrl = "http://f.apiplus.net/bj11x5.json";
		ResultBean<String> result = WebUtil.executeGet(getUrl);
		if(result.getFlag()!=1){
			return;
		}
		String dt = result.getData();
		Expect expect = JSON.parseObject(dt, Expect.class);
		String codes = null;
		String[] codeAry = new String[5];
		List<String> ids = new ArrayList<String>();
		// 将接收到的数据转换成数据库需要的数据
		for(Data data : expect.getData()){
			Lottery lty = new Lottery();
			codes = data.getOpencode();
			codeAry = codes.split(",");
			lty.setExpectId(data.getExpect());
			lty.setCodes(codes);
			lty.setCode1(Integer.parseInt(codeAry[0]));
			lty.setCode2(Integer.parseInt(codeAry[1]));
			lty.setCode3(Integer.parseInt(codeAry[2]));
			lty.setCode4(Integer.parseInt(codeAry[3]));
			lty.setCode5(Integer.parseInt(codeAry[4]));
			lty.setOpenTime(DateUtils.parse(data.getOpentime()));
			lty.setOpenTimestamp(data.getOpentimestamp());
			lty.setOddCount(ElevenSelectFive.countOdd(codeAry));
			lty.setBigCount(ElevenSelectFive.countBig(codeAry));
			lty.setPrimeCount(ElevenSelectFive.countPrime(codeAry));
			list.add(lty);
			ids.add(data.getExpect());
		}
		
		// 检查数据库中是否包含此次获取到的数据
		Map<String,Object> filterMap = new HashMap<String,Object>();
		filterMap.put("ids", ids);
		List<Lottery> exists = lotteryService.getByIds(filterMap);
		Iterator<Lottery> itr = exists.iterator();
		while(itr.hasNext()){
			Lottery lt = itr.next();
			if(!ids.contains(lt.getExpectId())){
				itr.remove();
			}
		}
		
		Iterator<Lottery> it = list.iterator();
		while(it.hasNext()){
			Lottery lt = it.next();
			if(exists.contains(lt)){
				it.remove(); // 如果数据库中包含
			}
		}
		if(list.isEmpty()){
			return;
		}
		lotteryService.saveLotteryBatch(list);
	}

	
	

	public static void main(String[] args) throws Exception{
		new LotteryScheduleServiceImpl().getLotteryAPIData();
	}
}
