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

import com.yhhl.cart.model.Carts;
import com.yhhl.cart.service.CartsServiceI;
import com.yhhl.common.Constants;
import com.yhhl.common.DateUtils;
import com.yhhl.common.LoginUser;
import com.yhhl.common.ResultBean;
import com.yhhl.common.SpringWebUtil;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.LoginCheck;
import com.yhhl.interceptor.Token;
import com.yhhl.product.model.Products;
import com.yhhl.product.service.ProductsServiceI;

/**
 * 
 * <br>
 * <b>功能：</b>CartsController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
@Controller
@RequestMapping("/carts")
public class FrontCartsController {

	private final static Logger log = Logger.getLogger(FrontCartsController.class);

	// Servrice start
	@Autowired // 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private CartsServiceI cartsService;
	@Autowired
	private ProductsServiceI productsService;

	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	@LoginCheck(frontMustLogin = Constants.TRUE)
	public ModelAndView index() {
		return new ModelAndView("front-page/cart");
	}

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCartsDatas")
	@LoginCheck(frontMustLogin = Constants.TRUE)
	@ResponseBody
	public ResultBean<Carts> getCartsDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Carts> dataPage = new Page<Carts>();
		LoginUser loginUser = SpringWebUtil.getLoginUser();
		filterMap.put("createUserId", loginUser.getUserId());
		dataPage = cartsService.getPage(filterMap, dataPage, page, rows);
		ResultBean<Carts> result = new ResultBean<Carts>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}

	/**
	 * 进入到初始化新增、修改页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddCarts")
	@Token(save = true)
	public ModelAndView initAddCarts(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (StringUtil.isNotEmpty(id)) {
			Carts carts = cartsService.getById(id);
			request.setAttribute("carts", carts);
		}
		return new ModelAndView("cart/addCarts");
	}

	@RequestMapping("/updateCartBuyNum")
	@LoginCheck(frontMustLogin = Constants.TRUE)
	@ResponseBody
	public ResultBean<String> updateCartBuyNum(@RequestParam(value = "cartId") String cartId,
			@RequestParam(value = "buyNum") int buyNum, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		long time = DateUtils.getNowDateTime();
		Carts cartsTemp = cartsService.getById(cartId);
		// 将页面修改的信息在这里替换
		if(cartsTemp==null){
			result.setFlag(ResultBean.FAIL);
			result.setMsg("购物车不存在该商品！");
			return result;
		}
		cartsTemp.setModifyTime(time);
		cartsTemp.setBuyNum(buyNum);
		cartsService.updateCarts(cartsTemp);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("修改成功");
		return result;
	}

	/**
	 * 新增、修改
	 * 
	 * @param user
	 * @param request
	 * @return
	 */

	@RequestMapping("/saveCarts")
	@LoginCheck(frontMustLogin = Constants.TRUE)
	@ResponseBody
	public ResultBean<String> saveCarts(Carts carts, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		long time = DateUtils.getNowDateTime();
		Carts cart = cartsService.getByProdIdAndStockId(carts.getProdId(), carts.getStockId());
		if (cart != null) { // 已经添加过购物车，下次再次添加时直接修改数量
			cart.setBuyNum(cart.getBuyNum() + carts.getBuyNum());
			cart.setModifyTime(time);
			cartsService.updateCarts(cart);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("添加成功");
			return result;
		}
		// 第一次添加购物车
		Products prod = productsService.getById(carts.getProdId());
		carts.setProdName(prod.getProdName());
		carts.setUnitPrice(prod.getUnitPriceSell());
		LoginUser loginUser = SpringWebUtil.getLoginUser();
		carts.setCreateUserId(loginUser.getUserId());
		carts.setCreateTime(time);
		carts.setModifyTime(time);
		cartsService.saveCarts(carts);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("保存成功");
		;
		return result;
	}

	/**
	 * 删除
	 *
	 * @param request
	 * @param id
	 */
	@RequestMapping("/delCarts")
	@ResponseBody
	public ResultBean<String> delCarts(HttpServletRequest request, String id) {
		ResultBean<String> result = new ResultBean<String>();
		cartsService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("删除成功");
		return result;
	}

}
