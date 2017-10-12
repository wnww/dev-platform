package com.yhhl.address.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.Token;
import com.yhhl.common.ResultBean;
import com.yhhl.address.model.Address;
import com.yhhl.address.service.AddressServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>AddressController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */ 
@Controller
@RequestMapping("/sysManage/address") 
public class AddressController {
	
	private final static Logger log= Logger.getLogger(AddressController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private AddressServiceI addressService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
	       return new ModelAndView("address/address-page");
	}
	
	/**
	 * 查询列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAddressDatas")
	@ResponseBody
	public ResultBean<Address> getAddressDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Address> dataPage = new Page<Address>();
		dataPage = addressService.getPage(filterMap, dataPage, page, rows);
		ResultBean<Address> result = new ResultBean<Address>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}
	
	/**
	 * 进入到初始化新增、修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddAddress")
	@Token(save = true)
	public ModelAndView initAddAddress(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			Address address = addressService.getById(id);
			request.setAttribute("address", address);
		}
		return new ModelAndView("address/addAddress");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveAddress")
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveAddress(Address address, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		if(StringUtil.isNotEmpty(address.getAddrId())){
			Address addressTemp = addressService.getById(address.getAddrId());
			// 将页面修改的信息在这里替换
			addressService.updateAddress(addressTemp);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");
			return result;
		}
		addressService.saveAddress(address);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("保存成功");;
		return result;
	}
	
	/**
	* 删除
	*
	* @param request
	* @param id
	*/
	@RequestMapping("/delAddress")
	@ResponseBody
	public ResultBean<String> delAddress(HttpServletRequest request,String id){
		ResultBean<String> result = new ResultBean<String>();
		addressService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("删除成功");
		return result;
	}

}
