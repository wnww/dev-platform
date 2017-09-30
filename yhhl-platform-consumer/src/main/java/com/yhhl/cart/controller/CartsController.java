package com.yhhl.cart.controller;

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
import com.yhhl.common.ResultBean;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.Token;
 
/**
 * 
 * <br>
 * <b>功能：</b>CartsController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */ 
@Controller
@RequestMapping("/sysManage/carts") 
public class CartsController {
	
	private final static Logger log= Logger.getLogger(CartsController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private CartsServiceI cartsService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
	       return new ModelAndView("cart/carts-page");
	}
	
	/**
	 * 查询列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCartsDatas")
	@ResponseBody
	public ResultBean<Carts> getCartsDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Carts> dataPage = new Page<Carts>();
		dataPage = cartsService.getPage(filterMap, dataPage, page, rows);
		ResultBean<Carts> result = new ResultBean<Carts>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}
	
	/**
	 * 进入到初始化新增、修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddCarts")
	@Token(save = true)
	public ModelAndView initAddCarts(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			Carts carts = cartsService.getById(id);
			request.setAttribute("carts", carts);
		}
		return new ModelAndView("cart/addCarts");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/saveCarts")
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveCarts(Carts carts, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		if(StringUtil.isNotEmpty(carts.getCartId())){
			Carts cartsTemp = cartsService.getById(carts.getCartId());
			// 将页面修改的信息在这里替换
			cartsService.updateCarts(cartsTemp);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");
			return result;
		}
		cartsService.saveCarts(carts);
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
	@RequestMapping("/delCarts")
	@ResponseBody
	public ResultBean<String> delCarts(HttpServletRequest request,String id){
		ResultBean<String> result = new ResultBean<String>();
		cartsService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("删除成功");
		return result;
	}

}
