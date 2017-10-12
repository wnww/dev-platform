package com.yhhl.address.model;

import java.math.BigDecimal;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yhhl.common.CustomDateTimeSerializer;
/**
 * 
 * <br>
 * <b>功能：</b>AddressEntity<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2013，国版中心<br>
 */
public class Address {
	
		private java.lang.String addrId;//   	private java.lang.String createUserId;//   地址所属人	private java.lang.String realName;//   	private java.lang.String province;//   省	private java.lang.String city;//   市
	private String mobile;	private java.lang.String address;//   详细地址	private int status;//   1:启用；0:未启用	private int defaultAdd;//   是否为默认地址	private java.lang.String space1;//   	private java.lang.String space2;//   	private java.lang.String space3;//   	public java.lang.String getAddrId() {	    return this.addrId;	}	public void setAddrId(java.lang.String addrId) {	    this.addrId=addrId;	}	public java.lang.String getCreateUserId() {	    return this.createUserId;	}	public void setCreateUserId(java.lang.String createUserId) {	    this.createUserId=createUserId;	}	public java.lang.String getRealName() {	    return this.realName;	}	public void setRealName(java.lang.String realName) {	    this.realName=realName;	}	public java.lang.String getProvince() {	    return this.province;	}	public void setProvince(java.lang.String province) {	    this.province=province;	}	public java.lang.String getCity() {	    return this.city;	}	public void setCity(java.lang.String city) {	    this.city=city;	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public java.lang.String getAddress() {	    return this.address;	}	public void setAddress(java.lang.String address) {	    this.address=address;	}	public int getStatus() {	    return this.status;	}	public void setStatus(int status) {	    this.status=status;	}	public int getDefaultAdd() {	    return this.defaultAdd;	}	public void setDefaultAdd(int defaultAdd) {	    this.defaultAdd=defaultAdd;	}	public java.lang.String getSpace1() {	    return this.space1;	}	public void setSpace1(java.lang.String space1) {	    this.space1=space1;	}	public java.lang.String getSpace2() {	    return this.space2;	}	public void setSpace2(java.lang.String space2) {	    this.space2=space2;	}	public java.lang.String getSpace3() {	    return this.space3;	}	public void setSpace3(java.lang.String space3) {	    this.space3=space3;	}
}

