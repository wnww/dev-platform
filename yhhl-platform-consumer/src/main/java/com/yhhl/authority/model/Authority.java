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
 * <b>版权所有：<b>版权所有(C) 2013，国版中心<br>
 */
public class Authority {
	
	
	private java.lang.String roleId;
	public java.lang.String getRoleId() {
		return roleId;
	}
	public void setRoleId(java.lang.String roleId) {
		this.roleId = roleId;
	}
}
