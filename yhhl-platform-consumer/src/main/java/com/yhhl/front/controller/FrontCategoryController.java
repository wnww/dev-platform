package com.yhhl.front.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.yhhl.category.model.Category;
import com.yhhl.category.model.CategoryVo;
import com.yhhl.category.service.CategoryServiceI;
import com.yhhl.common.ResultBean;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.Token;

/**
 * 
 * <br>
 * <b>功能：</b>CategoryController<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */
@Controller
@RequestMapping("/category")
public class FrontCategoryController {

	private final static Logger log = Logger.getLogger(FrontCategoryController.class);

	// Servrice start
	@Autowired // 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private CategoryServiceI categoryService;

	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("category/category-page");
	}

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCategoryDatas")
	@ResponseBody
	public ResultBean<Category> getCategoryDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Category> dataPage = new Page<Category>();
		dataPage = categoryService.getPage(filterMap, dataPage, page, rows);
		ResultBean<Category> result = new ResultBean<Category>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}

}
