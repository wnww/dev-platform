package com.yhhl.roles.model;

import java.beans.Transient;
import java.math.BigDecimal;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yhhl.common.CustomDateTimeSerializer;
/**
 * 
 * <br>
 * <b>功能：</b>RolesEntity<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2013，瀛海科技<br>
 */
public class Roles {
	
		private java.lang.String roleId;//   	private java.lang.String roleName;//   	private java.lang.String remark;//
	private java.lang.String userId;	public java.lang.String getRoleId() {	    return this.roleId;	}	public void setRoleId(java.lang.String roleId) {	    this.roleId=roleId;	}	public java.lang.String getRoleName() {	    return this.roleName;	}	public void setRoleName(java.lang.String roleName) {	    this.roleName=roleName;	}	public java.lang.String getRemark() {	    return this.remark;	}	public void setRemark(java.lang.String remark) {	    this.remark=remark;	}
	public java.lang.String getUserId() {
		return userId;
	}
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
}

