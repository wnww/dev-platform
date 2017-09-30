/**
 * JAVACC DEMO 1.0
 * @copy right cbice company All rights reserved. 
 * @Package com.yhhl.platform.web  
 */
package com.yhhl.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.yhhl.common.LocaleMessage;
import com.yhhl.common.LoginUser;
import com.yhhl.common.MD5Utils;
import com.yhhl.common.ResultBean;
import com.yhhl.core.Page;
import com.yhhl.product.model.Products;
import com.yhhl.product.service.ProductsServiceI;
import com.yhhl.stock.model.Stocks;
import com.yhhl.stock.service.StocksServiceI;
import com.yhhl.user.model.User;
import com.yhhl.user.service.UserServiceI;

/**
 * description:
 * 
 * @author 创建时间：2014-11-17 @Copyright(c)2014:瀛海科技
 */
@Controller
@RequestMapping("/")
public class ProdDetailController {
	
	@Autowired
	private ProductsServiceI productsService;
	@Autowired
	private StocksServiceI stocksService; 

	@RequestMapping("/prodDetail")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("front-page/prod_detail");
		String prodId = request.getParameter("prodId");
		Products prod = productsService.getById(prodId);
		request.setAttribute("prod", prod);
		request.setAttribute("includeHtmlPath", "/userfiles/htmlFiles/"+prodId+".html");
		
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		filterMap.put("prodId", prodId);
		Page<Stocks> dataPage = new Page<Stocks>();
		dataPage = stocksService.getPage(filterMap, dataPage, 1, 0);
		request.setAttribute("stocks", dataPage.getResult());
		return view;
	}
}
