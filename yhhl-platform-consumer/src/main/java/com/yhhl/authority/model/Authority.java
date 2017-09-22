package com.yhhl.authority.model;

import java.math.BigDecimal;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yhhl.common.CustomDateTimeSerializer;
/**
 * 
 * <br>
 * <b>功能：</b>AuthorityEntity<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2013，瀛海科技<br>
 */
public class Authority {
	
		private java.lang.String authId;//   	private java.lang.String authName;//   	private java.lang.String authType;//   	private java.lang.String authMark;//   作为路径，权限分组使用
	private java.lang.String roleId;	public java.lang.String getAuthId() {	    return this.authId;	}	public void setAuthId(java.lang.String authId) {	    this.authId=authId;	}	public java.lang.String getAuthName() {	    return this.authName;	}	public void setAuthName(java.lang.String authName) {	    this.authName=authName;	}	public java.lang.String getAuthType() {	    return this.authType;	}	public void setAuthType(java.lang.String authType) {	    this.authType=authType;	}	public java.lang.String getAuthMark() {	    return this.authMark;	}	public void setAuthMark(java.lang.String authMark) {	    this.authMark=authMark;	}
	public java.lang.String getRoleId() {
		return roleId;
	}
	public void setRoleId(java.lang.String roleId) {
		this.roleId = roleId;
	}
}

