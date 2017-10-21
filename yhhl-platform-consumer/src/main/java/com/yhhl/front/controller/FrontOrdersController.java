package com.yhhl.front.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.yhhl.cart.model.Carts;
import com.yhhl.cart.service.CartsServiceI;
import com.yhhl.common.Constants;
import com.yhhl.common.DateUtils;
import com.yhhl.common.IdWorker;
import com.yhhl.common.ResultBean;
import com.yhhl.common.SpringWebUtil;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.LoginCheck;
import com.yhhl.order.model.Orders;
import com.yhhl.order.model.OrdersVo;
import com.yhhl.order.service.OrdersServiceI;
import com.yhhl.orderproduct.model.OrderProducts;
import com.yhhl.orderproduct.service.OrderProductsServiceI;
import com.yhhl.product.model.Products;
import com.yhhl.product.service.ProductsServiceI;
import com.yhhl.stock.service.StocksServiceI;

/**
 * 
 * <br>
 * <b>功能：</b>OrdersController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
@Controller
@RequestMapping("/orders")
public class FrontOrdersController {

	private final static Logger log = Logger.getLogger(FrontOrdersController.class);

	@Autowired // 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private OrdersServiceI ordersService;
	@Autowired // 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private CartsServiceI cartsService;
	@Autowired
	private OrderProductsServiceI orderProductsService;
	@Autowired
	private StocksServiceI stocksService;
	@Autowired
	private ProductsServiceI productsService;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 进入待支付页面
	 * 
	 * @return
	 */
	@RequestMapping("/pay")
	@LoginCheck(frontMustLogin = Constants.TRUE)
	public ModelAndView index(@RequestParam(value = "orderId") String orderId, HttpServletRequest request) {
		Orders order = ordersService.getById(orderId);
		String addrId = request.getParameter("addrId");
		if (StringUtil.isEmpty(addrId)) {
			request.setAttribute("addrId", "");
		} else {
			request.setAttribute("addrId", addrId);
		}
		request.setAttribute("order", order);
		return new ModelAndView("front-page/pay");
	}

	/**
	 * 进入我的订单列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/myOrder")
	@LoginCheck(frontMustLogin = Constants.TRUE)
	public ModelAndView myOrder(HttpServletRequest request) {
		request.setAttribute("orderStatus", request.getParameter("orderStatus"));
		return new ModelAndView("front-page/my_orders");
	}

	/**
	 * 查询 我的订单 列表（前台用户中心使用）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMyOrdersDatas")
	@LoginCheck(frontMustLogin = Constants.TRUE)
	@ResponseBody
	public ResultBean<Orders> getMyOrdersDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		ResultBean<Orders> result = new ResultBean<Orders>();
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Orders> dataPage = new Page<Orders>();
		filterMap.put("ownerUserName", SpringWebUtil.getLoginUser().getUserId());
		dataPage = ordersService.getPage(filterMap, dataPage, page, rows);
		if (CollectionUtils.isEmpty(dataPage.getResult())) {
			result.setTotal(dataPage.getTotalCount());
			result.setRows(dataPage.getResult());
			return result;
		}
		List<String> ids = new ArrayList<String>();
		for (Orders order : dataPage.getResult()) {
			ids.add(order.getOrderId());
		}
		filterMap.clear();
		filterMap.put("ids", ids);
		List<OrdersVo> orderVos = ordersService.getMyOrderVoList(filterMap);
		List<Orders> orders = this.putTogether(dataPage.getResult(), orderVos);
		result.setTotal(dataPage.getTotalCount());
		result.setRows(orders);
		return result;
	}

	private List<Orders> putTogether(List<Orders> orders, List<OrdersVo> orderVos) {
		for (Orders order : orders) {
			List<OrdersVo> orderDetailList = new ArrayList<OrdersVo>();
			for (OrdersVo ordersVo : orderVos) {
				if (order.getOrderId().equals(ordersVo.getOrderId())) {
					orderDetailList.add(ordersVo);
				}
			}
			order.setOrderDetailList(orderDetailList);
		}
		return orders;
	}

	/**
	 * 查询 订单 列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getOrdersDatas")
	@LoginCheck(frontMustLogin = Constants.TRUE)
	@ResponseBody
	public ResultBean<Orders> getOrdersDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Orders> dataPage = new Page<Orders>();
		filterMap.put("ownerUserName", SpringWebUtil.getLoginUser().getUserId());
		dataPage = ordersService.getPage(filterMap, dataPage, page, rows);
		ResultBean<Orders> result = new ResultBean<Orders>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}

	/**
	 * 查询 订单商品 列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getOrderProductsDatas")
	@LoginCheck(frontMustLogin = Constants.TRUE)
	@ResponseBody
	public ResultBean<OrderProducts> getOrderProductsDatas(HttpServletRequest request,
			@RequestParam(value = "orderId") String orderId) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		filterMap.put("orderId", orderId);
		List<OrderProducts> list = orderProductsService.getByOrderId(filterMap);
		ResultBean<OrderProducts> result = new ResultBean<OrderProducts>();
		result.setRows(list);
		return result;
	}

	/**
	 * 根据购物车下单
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveOrdersByCarts")
	@LoginCheck(frontMustLogin = Constants.TRUE)
	@ResponseBody
	public ResultBean<String> saveOrdersByCarts(@RequestParam(value = "carts") String carts,
			HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		long time = DateUtils.getNowDateTime();
		if (StringUtil.isEmpty(carts)) {
			result.setFlag(ResultBean.FAIL);
			result.setMsg("没有选择任何商品，请在购物车中选择商品后再结算");
			return result;
		}
		List<String> idList = new ArrayList<String>();
		if (carts.indexOf(",") > 0) {
			String[] ids = carts.split(",");
			idList = Arrays.asList(ids);
		} else {
			idList.add(carts);
		}

		List<Carts> cartList = cartsService.getByCartIds(idList);
		if (cartList.size() == 0) {
			result.setFlag(ResultBean.FAIL);
			result.setMsg("没有选择任何商品，请在购物车中选择商品后再结算");
			return result;
		}
		// 构造订单主表数据
		Orders orders = new Orders();
		orders.setCreateTime(time);
		orders.setModifyTime(time);
		orders.setOrderId(idWorker.buildId());
		orders.setCreateUserName(SpringWebUtil.getLoginUser().getUserId());
		orders.setOwnerUserName(SpringWebUtil.getLoginUser().getUserId());
		orders.setStatus(Constants.OrderStatus.S11.getStatus());
		// 构造订单子表数据
		long totalAmount = 0l;
		List<OrderProducts> opList = new ArrayList<OrderProducts>();
		for (Carts cs : cartList) {
			OrderProducts op = new OrderProducts();
			op.setOrderId(orders.getOrderId());
			op.setOrderProdId(idWorker.buildId());
			op.setProdId(cs.getProdId());
			op.setProdName(cs.getProdName());
			op.setProdNum(cs.getBuyNum());
			op.setUnitPrice(cs.getUnitPrice());
			op.setStockId(cs.getStockId());
			opList.add(op);
			totalAmount = totalAmount + (cs.getUnitPrice() * cs.getBuyNum());
		}
		orders.setOrderAmount(totalAmount);
		ordersService.saveOrderAndOrderProduct(orders, opList, idList);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("保存成功");
		result.setData(orders.getOrderId());
		return result;
	}

	/**
	 * 直接下单购买
	 * @param prodId 产品ID
	 * @param stockId 库存ID
	 * @param buyNum 购买数量
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveOrdersDirect")
	@LoginCheck(frontMustLogin = Constants.TRUE)
	@ResponseBody
	public ResultBean<String> saveOrdersDirect(@RequestParam(value = "prodId") String prodId,
			@RequestParam(value = "stockId") String stockId, @RequestParam(value = "buyNum") int buyNum,
			HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		long time = DateUtils.getNowDateTime();
		// 构造订单主表数据
		Orders orders = new Orders();
		orders.setCreateTime(time);
		orders.setModifyTime(time);
		orders.setOrderId(idWorker.buildId());
		orders.setCreateUserName(SpringWebUtil.getLoginUser().getUserId());
		orders.setOwnerUserName(SpringWebUtil.getLoginUser().getUserId());
		orders.setStatus(Constants.OrderStatus.S11.getStatus());
		Products product = productsService.getById(prodId);
		orders.setOrderAmount(product.getUnitPriceSell()*buyNum);
		// 构造订单子表，订单商品数据
		OrderProducts op = new OrderProducts();
		op.setOrderId(orders.getOrderId());
		op.setOrderProdId(idWorker.buildId());
		op.setProdId(prodId);
		op.setProdName(product.getProdName());
		op.setProdNum(buyNum);
		op.setUnitPrice(product.getUnitPriceSell());
		op.setStockId(stockId);
		List<OrderProducts> opList = new ArrayList<OrderProducts>();
		opList.add(op);
		ordersService.saveOrderAndOrderProduct(orders, opList, null);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("保存成功");
		result.setData(orders.getOrderId());
		return result;
	}
	
	/**
	 * 更新订单（更新订单，姓名，电话，地址，运费等）
	 *
	 * @param request
	 * @param id
	 */
	@RequestMapping("/updateOrder")
	@LoginCheck(frontMustLogin=Constants.TRUE)
	@ResponseBody
	public ResultBean<String> updateOrder(HttpServletRequest request, @RequestParam(value="orderId") String orderId) {
		ResultBean<String> result = new ResultBean<String>();
		Orders order = ordersService.getById(orderId);
		if(order==null){
			result.setFlag(ResultBean.FAIL);
			result.setMsg("找不到该订单");
		}
		long time = DateUtils.getNowDateTime();
		order.setModifyTime(time);
		order.setOwnerRealName(request.getParameter("ownerRealName"));
		order.setOwnerMobile(request.getParameter("ownerMobile"));
		order.setPostAddress(request.getParameter("postAddress"));
		if(StringUtil.isNotEmpty(request.getParameter("expressFee"))){
			order.setExpressFee(Long.parseLong(request.getParameter("expressFee")));
		}else{
			order.setExpressFee(0l);
		}
		ordersService.updateOrders(order);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("订单提交成功");
		return result;
	}

	/**
	 * 删除
	 *
	 * @param request
	 * @param id
	 */
	@RequestMapping("/delOrders")
	@LoginCheck(frontMustLogin=Constants.TRUE)
	@ResponseBody
	public ResultBean<String> delOrders(HttpServletRequest request, String id) {
		ResultBean<String> result = new ResultBean<String>();
		ordersService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("删除成功");
		return result;
	}

}
