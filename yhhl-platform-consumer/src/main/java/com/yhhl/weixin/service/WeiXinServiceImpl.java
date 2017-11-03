package com.yhhl.weixin.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.yhhl.weixin.util.AccessTokenUtil;

@Lazy(false)
@Service("weiXinService")
public class WeiXinServiceImpl implements WeiXinServiceI {

	@Scheduled(cron = "0 0 * * * ?") // 每  1 小时执行一次
	@Override
	public void weiXinTokenSchedule() {
		AccessTokenUtil.obtainAccessToken();
	}

}
