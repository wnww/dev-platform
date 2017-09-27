package com.yhhl.stock.model;

import java.math.BigDecimal;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yhhl.common.CustomDateTimeSerializer;
/**
 * 
 * <br>
 * <b>功能：</b>StocksEntity<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2013，国版中心<br>
 */
public class Stocks {
	
		private java.lang.String stockId;//   	private java.lang.String prodId;//   产品ID	private java.lang.String colorsId;//   颜色ID	private java.lang.String specificationId;//   规格ID	private java.lang.Integer remainNum;//   剩余库存	private java.lang.Integer selledNum;//   已售出数量	private java.lang.Integer space1;//   备用1	private java.lang.Integer space2;//   备用2	public java.lang.String getStockId() {	    return this.stockId;	}	public void setStockId(java.lang.String stockId) {	    this.stockId=stockId;	}	public java.lang.String getProdId() {	    return this.prodId;	}	public void setProdId(java.lang.String prodId) {	    this.prodId=prodId;	}	public java.lang.String getColorsId() {	    return this.colorsId;	}	public void setColorsId(java.lang.String colorsId) {	    this.colorsId=colorsId;	}	public java.lang.String getSpecificationId() {	    return this.specificationId;	}	public void setSpecificationId(java.lang.String specificationId) {	    this.specificationId=specificationId;	}	public java.lang.Integer getRemainNum() {	    return this.remainNum;	}	public void setRemainNum(java.lang.Integer remainNum) {	    this.remainNum=remainNum;	}	public java.lang.Integer getSelledNum() {	    return this.selledNum;	}	public void setSelledNum(java.lang.Integer selledNum) {	    this.selledNum=selledNum;	}	public java.lang.Integer getSpace1() {	    return this.space1;	}	public void setSpace1(java.lang.Integer space1) {	    this.space1=space1;	}	public java.lang.Integer getSpace2() {	    return this.space2;	}	public void setSpace2(java.lang.Integer space2) {	    this.space2=space2;	}
}

