package com.yhhl.charts.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhhl.charts.dao.ChartsMapper;
import com.yhhl.charts.model.OrderChartsVo;

@Service("chartsService")
public class ChartsServiceImpl implements ChartsServiceI {

	@Autowired
	private ChartsMapper chartsMapper;
	
	@Override
	public List<OrderChartsVo> buildOrderCharts(Map<String, Object> map) {
		return chartsMapper.buildOrderCharts(map);
	}

}
