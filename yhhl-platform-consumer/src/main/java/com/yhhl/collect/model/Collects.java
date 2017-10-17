package com.yhhl.collect.model;

import java.math.BigDecimal;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yhhl.common.CustomDateTimeSerializer;
/**
 * 
 * <br>
 * <b>功能：</b>CollectsEntity<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2013，瀛海互联<br>
 */
public class Collects {
	
		private java.lang.String collectId;//   	private java.lang.String prodId;//   	private java.lang.String userId;//   	private long createTime;//
	private String prodName;
	private String imgUrl;	public java.lang.String getCollectId() {	    return this.collectId;	}	public void setCollectId(java.lang.String collectId) {	    this.collectId=collectId;	}	public java.lang.String getProdId() {	    return this.prodId;	}	public void setProdId(java.lang.String prodId) {	    this.prodId=prodId;	}	public java.lang.String getUserId() {	    return this.userId;	}	public void setUserId(java.lang.String userId) {	    this.userId=userId;	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	}

