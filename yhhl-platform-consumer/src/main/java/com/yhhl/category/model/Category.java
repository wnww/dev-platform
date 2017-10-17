package com.yhhl.category.model;

import java.math.BigDecimal;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yhhl.common.CustomDateTimeSerializer;
/**
 * 
 * <br>
 * <b>功能：</b>CategoryEntity<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2013，瀛海互联<br>
 */
public class Category {
	
	
	private String state; // 作为treegrid的状态判断是开启还是关闭
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
