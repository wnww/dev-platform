/**
 * JAVACC DEMO 1.0
 * @copy right cbice company All rights reserved. 
 * @Package com.yhhl.platform.web  
 */
package com.yhhl.platform.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.yhhl.common.Constants;
import com.yhhl.interceptor.LoginCheck;
import com.yhhl.order.service.OrdersServiceI;
import com.yhhl.product.service.ProductsServiceI;

/**
 * description:
 * 
 * @author 创建时间：2014-11-17 @Copyright(c)2014:瀛海科技
 */
@Controller
@RequestMapping("/sysManage/about")
public class AboutController {
	
	@Autowired 
	private OrdersServiceI ordersService;
	@Autowired
	private ProductsServiceI productsService;

	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/getOrderCounts")
	@ResponseBody
	public ModelAndView toPage(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("about");
		Map<String,Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		filterMap.clear();
		int orderTotalCount= ordersService.getCount(filterMap);
		request.setAttribute("orderTotalCount", orderTotalCount);
		
		int prodTotalCount  = productsService.getCount(filterMap);
		request.setAttribute("prodTotalCount", prodTotalCount);
		
		filterMap.put("status", 11);
		int obligationCount= ordersService.getCount(filterMap);// 待付款数量
		request.setAttribute("obligationCount", obligationCount);// 待付款数量
		
		filterMap.clear();
		filterMap.put("status", 21);
		int waitSendCount= ordersService.getCount(filterMap);// 待发货数量
		request.setAttribute("waitSendCount", waitSendCount);// 待发货数量
		
		filterMap.clear();
		filterMap.put("status", 41);
		int waitReceiveCount= ordersService.getCount(filterMap);// 待收货数量
		request.setAttribute("waitReceiveCount", waitReceiveCount);// 待收货数量
		return view;
	}
}
