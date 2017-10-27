/**
 * JAVACC DEMO 1.0
 * @copy right cbice company All rights reserved. 
 * @Package com.yhhl.platform.web  
 */
package com.yhhl.charts.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.yhhl.charts.model.ChartsData;
import com.yhhl.charts.model.OrderChartsVo;
import com.yhhl.charts.model.Series;
import com.yhhl.charts.service.ChartsServiceI;
import com.yhhl.common.Constants;
import com.yhhl.common.DateUtils;
import com.yhhl.common.ResultBean;
import com.yhhl.interceptor.LoginCheck;

/**
 * description:  
 * @author Hou Dayu 创建时间：2014-11-17  
 * @Copyright(c)2014:瀛海科技
 */
@Controller
@RequestMapping("/sysManage/charts")
public class ChartsController {
	
	@Autowired
	private ChartsServiceI chartsService;

	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/index")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("charts/order");
		return view;
	}
	
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/orderStatistics")
	@ResponseBody
	public ResultBean orderStatistics(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		String cycle = filterMap.get("cycle")==null?"D":String.valueOf(filterMap.get("cycle"));
		ResultBean result = new ResultBean();
		List<OrderChartsVo> list = chartsService.buildOrderCharts(filterMap);
		ChartsData data = new ChartsData();
		Series seriesAmount = new Series("订单金额","bar");
		Series seriesBuyNum = new Series("订单购买数量","bar");
		List<Series> datas = new ArrayList<Series>();
		String[] xAxis = new String[list.size()];
		double[] amount = new double[list.size()];
		double[] buyNum = new double[list.size()];
		
		for(int i=0; i<list.size(); i++){
			OrderChartsVo vo = list.get(i);
			if(cycle.equalsIgnoreCase("D")){
				xAxis[i] = DateUtils.dateTime2D(vo.getCreateTime());
			}else if(cycle.equalsIgnoreCase("M")){
				xAxis[i] = DateUtils.dateTime2M(vo.getCreateTime());
			}else if(cycle.equalsIgnoreCase("Y")){
				xAxis[i] = DateUtils.dateTime2Y(vo.getCreateTime());
			}else if(cycle.equalsIgnoreCase("MD")){
				xAxis[i] = DateUtils.dateTime2MD(vo.getCreateTime());
			}else{
				xAxis[i] = DateUtils.dateTime2YMD(vo.getCreateTime());
			}
			BigDecimal b = new BigDecimal(vo.getOrderAmount()/100);
			amount[i] = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();;
			buyNum[i] = vo.getBuyNum();
		}
		data.setxAxis(xAxis);
		seriesAmount.setData(amount);
		seriesBuyNum.setData(buyNum);
		datas.add(seriesAmount);
		datas.add(seriesBuyNum);
		result.setData(data);
		result.setRows(datas);
		return result;
	}
}
