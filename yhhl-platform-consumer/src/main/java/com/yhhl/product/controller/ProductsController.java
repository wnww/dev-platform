package com.yhhl.product.controller;

import java.util.HashMap;
import java.util.List;
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
import com.yhhl.common.FreemarkerUtil;
import com.yhhl.common.ResultBean;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.LoginCheck;
import com.yhhl.interceptor.Token;
import com.yhhl.product.model.Products;
import com.yhhl.product.service.ProductsServiceI;

import freemarker.template.Configuration;
 
/**
 * 
 * <br>
 * <b>功能：</b>ProductsController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海科技<br>
 */ 
@Controller
@RequestMapping("/sysManage/products") 
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
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("product/products-page");
	}
	
	/**
	 * 查询产品列表
	 * @param request
	 * @return
	 */
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/getProductsDatas")
	@ResponseBody
	public ResultBean<Products> getProductsDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Products> dataPage = new Page<Products>();
		dataPage = productsService.getPage(filterMap, dataPage, page, rows);
		
		ResultBean<Products> result = new ResultBean<Products>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}
	
	/**
	 * 后台根据商品名AJAX查询调用
	 * @param request
	 * @return
	 */
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/getProductsByProdName")
	@ResponseBody
	public ResultBean<Products> getProductsByProdName(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Products> dataPage = new Page<Products>();
		dataPage = productsService.getByProdName(filterMap, dataPage, page, rows);
		ResultBean<Products> result = new ResultBean<Products>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
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
			productsTemp.setProdName(products.getProdName());
			productsTemp.setUnitPriceCost(products.getUnitPriceCost());
			productsTemp.setUnitPriceSell(products.getUnitPriceSell());
			//productsTemp.setBrands(products.getBrands());
			//productsTemp.setUnit(products.getUnit());
			//productsTemp.setMark(products.getMark());
			productsTemp.setImgUrl(products.getImgUrl());
			productsTemp.setModifyTime(time);
			productsTemp.setType(products.getType());
			productsTemp.setProdCome(products.getProdCome());
			productsTemp.setProdComeUrl(products.getProdComeUrl());
			productsService.updateProducts(productsTemp);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");
			return result;
		}
		
		products.setCreateTime(time);
		products.setModifyTime(time);
		productsService.saveProducts(products);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("保存成功");
		return result;
	}
	
	/**
	 * 进入到初始化新增、修改 详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddProductsDetail")
	@Token(save = true)
	public ModelAndView initAddProductsDetail(HttpServletRequest request) {
		String prodId =  request.getParameter("prodId");
		Products product = productsService.getById(prodId);
		request.setAttribute("item", product);
		request.setAttribute("prodId", prodId);
		return new ModelAndView("product/addProductsDetail");
	}
	
	/**
	 * 进入到初始化新增、修改 详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddProductsExtend")
	@Token(save = true)
	public ModelAndView initAddProductsExtend(HttpServletRequest request) {
		String prodId =  request.getParameter("prodId");
		Products product = productsService.getById(prodId);
		request.setAttribute("products", product);
		return new ModelAndView("product/addProductsExtend");
	}
	
	/**
	 * 新增、修改详情
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveProductsDetail")
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveProductsDetail(HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		String prodId = request.getParameter("prodId");
		String remark = request.getParameter("remark");
		long time = DateUtils.getNowDateTime();
		Products products = productsService.getById(prodId);
		products.setRemark(remark);
		products.setModifyTime(time);
		productsService.updateProducts(products);
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
	@RequestMapping("/delProducts")
	@ResponseBody
	public ResultBean<String> delProducts(HttpServletRequest request,String id){
		ResultBean<String> result = new ResultBean<String>();
		productsService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("修改成功");
		return result;
	}
	
	/**
	* 设置推荐
	*
	* @param request
	* @param id
	*/
	@RequestMapping("/recommend")
	@ResponseBody
	public ResultBean<String> recommend(HttpServletRequest request,String id,String recommend){
		ResultBean<String> result = new ResultBean<String>();
		Map<String,Object> filterMap = new HashMap<String,Object>();
		filterMap.put("recommend", Constants.TRUE);
		int count = productsService.getCount(filterMap);
		if(count>=5 && recommend.equals(Constants.TRUE)){
			result.setFlag(ResultBean.FAIL);
			result.setMsg("推荐商品不能超过5个");
			return result;
		}
		Products product = productsService.getById(id);
		if(!recommend.equals(product.getRecommend())){
			product.setRecommend(recommend);
			productsService.updateProducts(product);
		}
		result.setFlag(ResultBean.SUCCESS);
		if(recommend.equals(Constants.TRUE)){
			result.setMsg("推荐成功");
		}else{
			result.setMsg("取消推荐成功");
		}
		return result;
	}
	
	/**
	 * 生成静态页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/publishProdDetails")
	@ResponseBody
	public ResultBean<String> publisProductsDetail(HttpServletRequest request){
		ResultBean<String> result = new ResultBean<String>();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		Map<String,Object> map = new HashMap<String,Object>();
		startDate = startDate.replaceAll("-", "")+"000000";
		endDate = endDate.replaceAll("-", "")+"235959";
		map.put("startDate", Long.parseLong(startDate));
		map.put("endDate", Long.parseLong(endDate));
		List<Products> list = productsService.getList(map);
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		for(Products prod : list){
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("remark", prod.getRemark());
			FreemarkerUtil.createStaticPage(cfg, request, prod.getProdId(), data, "WEB-INF/views/templates", "prodDetail.ftl");
			data = null;
		}
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("生成成功！");
		return result;
	}

}
