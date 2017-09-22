package com.yhhl.product.controller;

import java.util.HashMap;
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

import com.yhhl.common.DateUtils;
import com.yhhl.common.ResultBean;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.Token;
import com.yhhl.product.model.Products;
import com.yhhl.product.service.ProductsServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>ProductsController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海科技<br>
 */ 
@Controller
@RequestMapping("/products") 
public class ProductsController {
	
	private final static Logger log= Logger.getLogger(ProductsController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private ProductsServiceI productsService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("product/products-page");
	}
	
	/**
	 * 查询产品列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getProductsDatas")
	@ResponseBody
	public Map<String, Object> getProductsDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Products> dataPage = new Page<Products>();
		dataPage = productsService.getPage(filterMap, dataPage, page, rows);
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", dataPage.getTotalCount());
		mapData.put("rows", dataPage.getResult());
		return mapData;
	}
	
	/**
	 * 进入到初始化新增、修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddProducts")
	@Token(save = true)
	public ModelAndView initAddProducts(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			Products products = productsService.getById(id);
			request.setAttribute("products", products);
		}
		return new ModelAndView("product/addProducts");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveProducts")
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveProducts(Products products, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		long time = DateUtils.getNowDateTime();
		if(StringUtil.isNotEmpty(products.getProdId())){
			Products productsTemp = productsService.getById(products.getProdId());
			// 将页面修改的信息在这里替换
			productsTemp.setUnitPriceCost(products.getUnitPriceCost());
			productsTemp.setImgUrl(products.getImgUrl());
			productsTemp.setModifyTime(time);
			productsService.updateProducts(productsTemp);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");
			return result;
		}
		
		products.setCreateTime(time);
		products.setModifyTime(time);
		productsService.saveProducts(products);
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
	@RequestMapping("/delProducts")
	@ResponseBody
	public ResultBean<String> delProducts(HttpServletRequest request,String id){
		ResultBean<String> result = new ResultBean<String>();
		productsService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("修改成功");
		return result;
	}

}
