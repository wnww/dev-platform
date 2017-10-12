package com.yhhl.order.model;

/**
 * 
 * <br>
 * <b>功能：</b>OrdersEntity<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2013，国版中心<br>
 */
public class OrdersVo {

	private java.lang.String orderId;//
	private String OrderProdId;
	private String prodId;
	private String stockId;
	private String prodName;
	private String prodNum;
	private long orderAmount;//订单金额
	private long expressFee; // 快递费
	private java.lang.String ownerUserName;// 订单所属用户ID
	private int status;//
	private long createTime;//
	private String imgUrl;
	public java.lang.String getOrderId() {
		return orderId;
	}
	public void setOrderId(java.lang.String orderId) {
		this.orderId = orderId;
	}
	public String getOrderProdId() {
		return OrderProdId;
	}
	public void setOrderProdId(String orderProdId) {
		OrderProdId = orderProdId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdNum() {
		return prodNum;
	}
	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}
	public long getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(long orderAmount) {
		this.orderAmount = orderAmount;
	}
	public long getExpressFee() {
		return expressFee;
	}
	public void setExpressFee(long expressFee) {
		this.expressFee = expressFee;
	}
	public java.lang.String getOwnerUserName() {
		return ownerUserName;
	}
	public void setOwnerUserName(java.lang.String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
