package com.yhhl.charts.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.yhhl.charts.model.OrderChartsVo;

/**
 * 
 * <br>
 * <b>功能：</b>ChartsDao<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015，瀛海互联<br>
 */
public interface ChartsMapper {

	List<OrderChartsVo> buildOrderCharts(Map<String,Object> map) throws DataAccessException;
}
