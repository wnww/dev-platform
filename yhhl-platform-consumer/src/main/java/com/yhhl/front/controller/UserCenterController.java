/**
 * JAVACC DEMO 1.0
 * @copy right cbice company All rights reserved. 
 * @Package com.yhhl.platform.web  
 */
package com.yhhl.front.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yhhl.common.Constants;
import com.yhhl.common.ResultBean;
import com.yhhl.common.SpringWebUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.LoginCheck;
import com.yhhl.order.model.Orders;
import com.yhhl.order.service.OrdersServiceI;

/**
 * description:
 * 
 * @author 创建时间：2014-11-17 @Copyright(c)2014:瀛海科技
 */
@Controller
@RequestMapping("/userCenter")
public class UserCenterController {
	
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private OrdersServiceI ordersService;

	@LoginCheck(frontMustLogin=Constants.TRUE)
	@RequestMapping("/index")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("front-page/user_center");
		return view;
	}
	
	/**
	 * 查询订单数量
	 * @param request
	 * @return
	 */
	@RequestMapping("/getOrdersInfo")
	@LoginCheck(frontMustLogin = Constants.TRUE)
	@ResponseBody
	public ResultBean<Map<String,Integer>> getOrdersInfo(HttpServletRequest request) {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("ownerUserName", SpringWebUtil.getLoginUser().getUserId());
		List<Integer> list = new ArrayList<Integer>();
		// 已完成状态查询
		list.add(Constants.OrderStatus.S51.getStatus());
		list.add(Constants.OrderStatus.S61.getStatus());
		list.add(Constants.OrderStatus.S71.getStatus());
		filterMap.put("multiStatus", list);
		int finishCount = ordersService.getCount(filterMap);
		// 未完成状态查询
		list.clear();
		list.add(Constants.OrderStatus.S11.getStatus());
		list.add(Constants.OrderStatus.S21.getStatus());
		list.add(Constants.OrderStatus.S31.getStatus());
		list.add(Constants.OrderStatus.S41.getStatus());
		filterMap.put("multiStatus", list);
		int unFinishCount = ordersService.getCount(filterMap);
		ResultBean<Map<String,Integer>> result = new ResultBean<Map<String,Integer>>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("finishCount", finishCount);
		map.put("unFinishCount", unFinishCount);
		result.setFlag(ResultBean.SUCCESS);
		result.setData(map);
		return result;
	}
}
