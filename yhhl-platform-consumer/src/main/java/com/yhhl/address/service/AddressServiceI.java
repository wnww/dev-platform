package com.yhhl.address.service;

import org.apache.log4j.Logger;

import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.address.model.Address;

/**
 * 
 * <br>
 * <b>功能：</b>AddressServiceI<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
public interface AddressServiceI {

	public final static Logger log= Logger.getLogger(AddressServiceI.class);

	Address getById(String id);

	Page<Address> getPage(Map<String,Object> filterMap, Page<Address> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveAddress(Address address);
	
	public void updateAddress(Address address);
	
	public void deleteById(String id);

}
