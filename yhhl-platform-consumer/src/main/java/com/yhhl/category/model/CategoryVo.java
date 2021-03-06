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
public class CategoryVo {
	
		private java.lang.String catId;//   	private java.lang.String parentId;//   	private java.lang.String wbs;//   	private java.lang.String catName;//   	private int subCount;//   	private java.lang.String id;//  
	private String text;	private String state; // 作为treegrid的状态判断是开启还是关闭	public java.lang.String getCatId() {	    return this.catId;	}	public void setCatId(java.lang.String catId) {	    this.catId=catId;	}	public java.lang.String getParentId() {	    return this.parentId;	}	public void setParentId(java.lang.String parentId) {	    this.parentId=parentId;	}	public java.lang.String getWbs() {	    return this.wbs;	}	public void setWbs(java.lang.String wbs) {	    this.wbs=wbs;	}	public java.lang.String getCatName() {	    return this.catName;	}	public void setCatName(java.lang.String catName) {	    this.catName=catName;	}	public int getSubCount() {	    return this.subCount;	}	public void setSubCount(int subCount) {	    this.subCount=subCount;	}	
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}

