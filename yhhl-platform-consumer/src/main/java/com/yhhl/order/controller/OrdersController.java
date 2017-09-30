package com.yhhl.order.controller;

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

import com.yhhl.common.Constants;
import com.yhhl.common.DateUtils;
import com.yhhl.common.ResultBean;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.Token;
import com.yhhl.order.model.Orders;
import com.yhhl.order.service.OrdersServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>OrdersController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */ 
@Controller
@RequestMapping("/sysManage/orders") 
public class OrdersController {
	
	private final static Logger log= Logger.getLogger(OrdersController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private OrdersServiceI ordersService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("order/orders-page");
	}
	
	/**
	 * 查询用户 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getOrdersDatas")
	@ResponseBody
	public ResultBean<Orders> getOrdersDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Orders> dataPage = new Page<Orders>();
		dataPage = ordersService.getPage(filterMap, dataPage, page, rows);
		ResultBean<Orders> result = new ResultBean<Orders>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}
	
	/**
	 * 进入到初始化新增、修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddOrders")
	@Token(save = true)
	public ModelAndView initAddOrders(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			Orders orders = ordersService.getById(id);
			request.setAttribute("orders", orders);
		}
		return new ModelAndView("order/addOrders");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveOrders")
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveOrders(Orders orders, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		long time = DateUtils.getNowDateTime();
		if(StringUtil.isNotEmpty(orders.getOrderId())){
			Orders ordersTemp = ordersService.getById(orders.getOrderId());
			// 将页面修改的信息在这里替换
			ordersTemp.setModifyTime(time);
			ordersService.updateOrders(ordersTemp);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");
			return result;
		}
		orders.setStatus(Constants.OrderStatus.S11.getStatus());
		orders.setCreateTime(time);
		orders.setModifyTime(time);
		ordersService.saveOrders(orders);
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
	@RequestMapping("/delOrders")
	@ResponseBody
	public ResultBean<String> delOrders(HttpServletRequest request,String id){
		ResultBean<String> result = new ResultBean<String>();
		ordersService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("删除成功");
		return result;
	}

}
