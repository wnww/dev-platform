package com.yhhl.address.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yhhl.address.dao.AddressMapper;
import com.yhhl.address.model.Address;
import com.yhhl.common.Constants;
import com.yhhl.common.IdWorker;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;

/**
 * 
 * <br>
 * <b>功能：</b>AddressServiceImpl<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
@Service("addressService")
public class AddressServiceImpl implements AddressServiceI {

	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 保存
	 */
	@Override
	public void saveAddress(Address address){
		address.setAddrId(idWorker.buildId());
		if(address.getDefaultAdd()==0){ // 不设置为默认则保存后直接返回
			addressMapper.insert(address);
			return;
		}
		Map<String,Object> filterMap = new HashMap<String,Object>();
		filterMap.put("createUserId", address.getCreateUserId());
		filterMap.put("defaultAdd", Constants.TRUE);
		Page<Address> page = new Page<Address>();
		page = this.getPage(filterMap, page, 1, 0);
		if(CollectionUtils.isEmpty(page.getResult())){// 目前没有默认则保存后返回
			addressMapper.insert(address);
			return;
		}
		// 如果已经有默认的，先取消默认再保存
		for(Address addr : page.getResult()){
			addr.setDefaultAdd(Integer.parseInt(Constants.FALSE));
			addressMapper.updateByPrimaryKey(addr);
		}
		addressMapper.insert(address);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<Address> getPage(Map<String, Object> filterMap, Page<Address> page, int pageNo, int pageSize) {
		int count = addressMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "default_add desc","addr_id desc"};//排序字段，可以是多个 类似：{ "name  desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Address> list = addressMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	*
	* 分页查询的count
	*/
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return addressMapper.getCount(filterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	public void updateAddress(Address address) {
		if(address.getDefaultAdd()==0){// 如果不设置默认则直接更新
			addressMapper.updateByPrimaryKey(address);
			return;
		}
		Map<String,Object> filterMap = new HashMap<String,Object>();
		filterMap.put("createUserId", address.getCreateUserId());
		filterMap.put("defaultAdd", Constants.TRUE);
		Page<Address> page = new Page<Address>();
		page = this.getPage(filterMap, page, 1, 0);
		if(CollectionUtils.isEmpty(page.getResult())){// 目前没有默认则保存后返回
			addressMapper.updateByPrimaryKey(address);
			return;
		}
		// 如果已经有默认的，先取消默认再保存
		for(Address addr : page.getResult()){
			addr.setDefaultAdd(Integer.parseInt(Constants.FALSE));
			addressMapper.updateByPrimaryKey(addr);
		}
		addressMapper.updateByPrimaryKey(address);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public Address getById(String id) {
		return addressMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		addressMapper.deleteByPrimaryKey(id);
	}

}
