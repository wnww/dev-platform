package com.yhhl.orderproduct.model;

import java.math.BigDecimal;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yhhl.common.CustomDateTimeSerializer;
/**
 * 
 * <br>
 * <b>功能：</b>OrderProductsEntity<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2013，国版中心<br>
 */
public class OrderProducts {
	
		private java.lang.String orderProdId;//   	private java.lang.String orderId;//   订单ID	private java.lang.String prodId;//   产品ID
	private String stockId;	private java.lang.String prodName;//   产品名称	private int unitPrice;//   售出单价	private int prodNum;//   售出数量
	private String imgUrl;	public java.lang.String getOrderProdId() {	    return this.orderProdId;	}	public void setOrderProdId(java.lang.String orderProdId) {	    this.orderProdId=orderProdId;	}	public java.lang.String getOrderId() {	    return this.orderId;	}	public void setOrderId(java.lang.String orderId) {	    this.orderId=orderId;	}	public java.lang.String getProdId() {	    return this.prodId;	}	public void setProdId(java.lang.String prodId) {	    this.prodId=prodId;	}
		public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public java.lang.String getProdName() {	    return this.prodName;	}	public void setProdName(java.lang.String prodName) {	    this.prodName=prodName;	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getProdNum() {
		return prodNum;
	}
	public void setProdNum(int prodNum) {
		this.prodNum = prodNum;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}	
	
}

