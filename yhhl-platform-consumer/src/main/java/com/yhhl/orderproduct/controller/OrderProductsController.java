package com.yhhl.orderproduct.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.yhhl.common.DateUtils;
import com.yhhl.common.ResultBean;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.Token;
import com.yhhl.orderproduct.model.OrderProducts;
import com.yhhl.orderproduct.service.OrderProductsServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>OrderProductsController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */ 
@Controller
@RequestMapping("/orderProducts") 
public class OrderProductsController {
	
	private final static Logger log= Logger.getLogger(OrderProductsController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private OrderProductsServiceI orderProductsService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("orderproduct/orderProducts-page");
	}
	
	/**
	 * 查询用户 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getOrderProductsDatas")
	@ResponseBody
	public Map<String, Object> getOrderProductsDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<OrderProducts> dataPage = new Page<OrderProducts>();
		dataPage = orderProductsService.getPage(filterMap, dataPage, page, rows);
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", dataPage.getTotalCount());
		mapData.put("rows", dataPage.getResult());
		return mapData;
	}
	
	/**
	 * 进入到初始化新增
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddOrderProducts")
	@Token(save = true)
	public ModelAndView initAddOrderProducts(HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		request.setAttribute("orderId", orderId);
		return new ModelAndView("orderproduct/addOrderProducts");
	}
	
	@RequestMapping("/initUpdateOrderProducts")
	@Token(save = true)
	public ModelAndView initUpdateOrderProducts(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			OrderProducts orderProducts = orderProductsService.getById(id);
			request.setAttribute("orderProducts", orderProducts);
		}
		return new ModelAndView("orderproduct/updateOrderProducts");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveOrderProducts")
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveOrderProducts(OrderProducts orderProducts, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		if(StringUtil.isNotEmpty(orderProducts.getOrderProdId())){
			OrderProducts orderProductsTemp = orderProductsService.getById(orderProducts.getOrderProdId());
			// 将页面修改的信息在这里替换
			orderProductsService.updateOrderProducts(orderProductsTemp);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");;
			return result;
		}
		orderProductsService.saveOrderProducts(orderProducts);
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
	@RequestMapping("/delOrderProducts")
	@ResponseBody
	public Map<String, Object> delOrderProducts(HttpServletRequest request,String id){
		Map<String, Object> map = new HashMap<String, Object>();
		orderProductsService.deleteById(id);
		map.put("flag", "T");
		map.put("msg", "删除成功");
		return map;
	}

}
