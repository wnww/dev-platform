package com.yhhl.order.model;

import java.util.List;

/**
 * 
 * <br>
 * <b>功能：</b>OrdersEntity<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2013，国版中心<br>
 */
public class Orders {

	private java.lang.String orderId;//
	private java.lang.String ownerUserName;//
	private java.lang.String ownerRealName;//
	private java.lang.String ownerMobile;//
	private long orderAmount;//
	private long expressFee; // 快递费
	private java.lang.String postAddress;//
	private int status;//
	private java.lang.String createUserName;//
	private long createTime;//
	private long modifyTime;//
	private java.lang.String remark;//
	private String userType; // 用户类型
	private List<OrdersVo> orderDetailList;
	public java.lang.String getOrderId() {
		return orderId;
	}

	public void setOrderId(java.lang.String orderId) {
		this.orderId = orderId;
	}

	public java.lang.String getOwnerUserName() {
		return ownerUserName;
	}

	public void setOwnerUserName(java.lang.String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}

	public java.lang.String getOwnerRealName() {
		return ownerRealName;
	}

	public void setOwnerRealName(java.lang.String ownerRealName) {
		this.ownerRealName = ownerRealName;
	}

	public java.lang.String getOwnerMobile() {
		return ownerMobile;
	}

	public void setOwnerMobile(java.lang.String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}

	public long getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(long orderAmount) {
		this.orderAmount = orderAmount;
	}

	public java.lang.String getPostAddress() {
		return postAddress;
	}

	public void setPostAddress(java.lang.String postAddress) {
		this.postAddress = postAddress;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public java.lang.String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(java.lang.String createUserName) {
		this.createUserName = createUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public long getExpressFee() {
		return expressFee;
	}

	public void setExpressFee(long expressFee) {
		this.expressFee = expressFee;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<OrdersVo> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrdersVo> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	
}
