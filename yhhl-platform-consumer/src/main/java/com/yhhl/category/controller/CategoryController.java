package com.yhhl.category.controller;

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
@RequestMapping("/sysManage/category")
public class CategoryController {

	private final static Logger log = Logger.getLogger(CategoryController.class);

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
		for (Category cat : dataPage.getResult()) {
			if (cat.getSubCount() > 0) {
				cat.setState("closed");
			} else {
				cat.setState("opened");
			}
		}
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}

	@RequestMapping("/getCategoryTree")
	@ResponseBody
	public List<Category> getCategoryTree(HttpServletRequest request) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Category> dataPage = new Page<Category>();
		dataPage = categoryService.getPage(filterMap, dataPage, 1, 0);
		List<Category> list = new ArrayList<Category>(dataPage.getResult());
		for (Category cat : list) {
			if (cat.getSubCount() > 0) {
				cat.setState("closed");
			} else {
				cat.setState("opened");
			}
		}
		return list;
	}
	
	@RequestMapping("/getCategoryCombTree")
	@ResponseBody
	public List<CategoryVo> getCategoryCombTree(HttpServletRequest request) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Category> dataPage = new Page<Category>();
		dataPage = categoryService.getPage(filterMap, dataPage, 1, 0);
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		try {
			for (Category cat : dataPage.getResult()) {
				CategoryVo vo = new CategoryVo();
				BeanUtils.copyProperties(vo, cat);
				vo.setId(cat.getWbs());
				vo.setText(cat.getCatName());
				if (vo.getSubCount() > 0) {
					vo.setState("closed");
				} else {
					vo.setState("opened");
				}
				list.add(vo);
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 进入到初始化新增、修改页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddCategory")
	@Token(save = true)
	public ModelAndView initAddCategory(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (StringUtil.isNotEmpty(id)) {
			Category category = categoryService.getById(id);
			request.setAttribute("category", category);
			return new ModelAndView("category/addCategory");
		}
		Category cate = new Category();
		if (StringUtils.isNotEmpty(request.getParameter("parentId"))) {
			cate.setParentId(request.getParameter("parentId"));
		} else {
			cate.setParentId("0");
		}
		request.setAttribute("category", cate);
		return new ModelAndView("category/addCategory");
	}

	/**
	 * 新增、修改
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveCategory")
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveCategory(Category category, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		if (StringUtil.isNotEmpty(category.getCatId())) {
			Category categoryTemp = categoryService.getById(category.getCatId());
			// 将页面修改的信息在这里替换
			categoryTemp.setCatName(category.getCatName());
			categoryService.updateCategory(categoryTemp);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");
			return result;
		}
		String wbs = this.computeWbs(category.getParentId());
		category.setWbs(wbs);
		categoryService.saveCategory(category);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("保存成功");
		return result;
	}

	private String computeWbs(String parentId) {
		String resultWbs = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		String wbs = categoryService.getMaxWbs(map);
		if (StringUtil.isEmpty(wbs)) {
			if(parentId.equals("0")){
				resultWbs = "001";
			}else{
				Category parent = categoryService.getById(parentId);
				return parent.getWbs()+"-001";
			}
		} else {
			String[] ary = wbs.split("-");
			String temp = ary[ary.length - 1];
			int ws = 0;
			ws = Integer.parseInt(temp);
			ws++;
			temp = String.valueOf(ws);
			switch (temp.length()) {
			case 1:
				resultWbs = "00" + temp;
				break;
			case 2:
				resultWbs = "0" + temp;
				break;
			case 3:
				resultWbs = temp;
				break;
			default:
				resultWbs = temp;
				break;
			}
			if(ary.length>1){
				StringBuffer sb = new StringBuffer();
				for(int i=0; i<ary.length-1;i++){
					sb.append(ary[i]).append("-");
				}
				sb.append(resultWbs);
				resultWbs = sb.toString();
			}
		}
		return resultWbs;
	}

	/**
	 * 删除
	 *
	 * @param request
	 * @param id
	 */
	@RequestMapping("/delCategory")
	@ResponseBody
	public ResultBean<String> delCategory(HttpServletRequest request, String id) {
		ResultBean<String> result = new ResultBean<String>();
		// 先检测是否包含子元素
		Map<String,Object> filterMap = new HashMap<String,Object>();
		filterMap.put("parentId", id);
		int count = categoryService.getCount(filterMap);
		if(count>0){
			result.setFlag(ResultBean.FAIL);
			result.setMsg("含有子类别，不能删除，如需要删除，请先删除子类别！");
			return result;
		}
		categoryService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("删除成功");
		return result;
	}

}
