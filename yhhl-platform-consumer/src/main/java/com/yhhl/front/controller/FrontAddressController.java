package com.yhhl.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.yhhl.address.model.Address;
import com.yhhl.address.service.AddressServiceI;
import com.yhhl.common.Constants;
import com.yhhl.common.ResultBean;
import com.yhhl.common.SpringWebUtil;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.LoginCheck;
import com.yhhl.interceptor.Token;

/**
 * 
 * <br>
 * <b>功能：</b>AddressController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
@Controller
@RequestMapping("/address")
public class FrontAddressController {

	private final static Logger log = Logger.getLogger(FrontAddressController.class);

	// Servrice start
	@Autowired // 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private AddressServiceI addressService;

	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@LoginCheck(frontMustLogin=Constants.TRUE)
	@RequestMapping("/selectAddress")
	public ModelAndView index(HttpServletRequest request) {
		request.setAttribute("orderId", request.getParameter("orderId"));
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Address> dataPage = new Page<Address>();
		filterMap.put("createUserId",SpringWebUtil.getLoginUser().getUserId());
		dataPage = addressService.getPage(filterMap, dataPage, 1, 0);
		request.setAttribute("addressList", dataPage.getResult());
		return new ModelAndView("front-page/address_page");
	}

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @return
	 */
	@LoginCheck(frontMustLogin=Constants.TRUE)
	@RequestMapping("/getAddressDatas")
	@ResponseBody
	public ResultBean<Address> getAddressDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Address> dataPage = new Page<Address>();
		filterMap.put("createUserId",SpringWebUtil.getLoginUser().getUserId());
		if(StringUtil.isNotEmpty(request.getParameter("addrId"))){
			filterMap.put("addrId",request.getParameter("addrId"));
		}
		dataPage = addressService.getPage(filterMap, dataPage, page, rows);
		ResultBean<Address> result = new ResultBean<Address>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}
	
	@RequestMapping("/checkHasAddress")
	@LoginCheck(frontMustLogin=Constants.TRUE)
	@ResponseBody
	public ResultBean<Integer> checkHasAddress(HttpServletRequest request) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		filterMap.put("createUserId",SpringWebUtil.getLoginUser().getUserId());
		int count = addressService.getCount(filterMap);
		ResultBean<Integer> result = new ResultBean<Integer>();
		result.setFlag(ResultBean.SUCCESS);
		result.setData(count);
		return result;
	}

	/**
	 * 进入到初始化新增、修改页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddAddress")
	@LoginCheck(frontMustLogin=Constants.TRUE)
	@Token(save = true)
	public ModelAndView initAddAddress(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (StringUtil.isNotEmpty(id)) {
			Address address = addressService.getById(id);
			request.setAttribute("address", address);
		}
		if(StringUtil.isNotEmpty(request.getParameter("orderId"))){
			request.setAttribute("orderId", request.getParameter("orderId"));
		}
		return new ModelAndView("front-page/add_address");
	}

	/**
	 * 新增、修改
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveAddress")
	@LoginCheck(frontMustLogin=Constants.TRUE)
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveAddress(Address address, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		if (StringUtil.isNotEmpty(address.getAddrId())) {
			Address addressTemp = addressService.getById(address.getAddrId());
			// 将页面修改的信息在这里替换
			addressTemp.setRealName(address.getRealName());
			addressTemp.setProvince(address.getProvince());
			addressTemp.setCity(address.getCity());
			addressTemp.setAddress(address.getAddress());
			addressTemp.setMobile(address.getMobile());
			addressTemp.setDefaultAdd(address.getDefaultAdd());
			addressService.updateAddress(addressTemp);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");
			return result;
		}
		address.setCreateUserId(SpringWebUtil.getLoginUser().getUserId());
		address.setStatus(Integer.parseInt(Constants.TRUE));
		addressService.saveAddress(address);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("保存成功");
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
	public ResultBean<String> delAddress(HttpServletRequest request, String id) {
		ResultBean<String> result = new ResultBean<String>();
		addressService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("删除成功");
		return result;
	}

}
