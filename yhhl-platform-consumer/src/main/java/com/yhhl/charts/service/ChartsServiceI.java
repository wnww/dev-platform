package com.yhhl.charts.service;

import java.util.List;
import java.util.Map;

import com.yhhl.charts.model.OrderChartsVo;

public interface ChartsServiceI {

	public List<OrderChartsVo> buildOrderCharts(Map<String,Object> map);
}
