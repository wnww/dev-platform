package com.yhhl.dict.controller;

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

import com.yhhl.common.ResultBean;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.dict.model.Dicts;
import com.yhhl.dict.service.DictsServiceI;
import com.yhhl.interceptor.Token;
import com.yhhl.product.model.Products;
 
/**
 * 
 * <br>
 * <b>功能：</b>DictsController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */ 
@Controller
@RequestMapping("/sysManage/dicts") 
public class DictsController {
	
	private final static Logger log= Logger.getLogger(DictsController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private DictsServiceI dictsService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("dict/dicts-page");
	}
	
	/**
	 * 查询字典列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDictsDatas")
	@ResponseBody
	public ResultBean<Dicts> getDictsDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Dicts> dataPage = new Page<Dicts>();
		dataPage = dictsService.getPage(filterMap, dataPage, page, rows);
		ResultBean<Dicts> result = new ResultBean<Dicts>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}
	
	/**
	 * 查询字典列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDictsList")
	@ResponseBody
	public List<Dicts> getDictsList(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Dicts> dataPage = new Page<Dicts>();
		dataPage = dictsService.getPage(filterMap, dataPage, page, rows);
		return dataPage.getResult();
	}
	
	/**
	 * 进入到初始化新增、修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddDicts")
	@Token(save = true)
	public ModelAndView initAddDicts(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			Dicts dicts = dictsService.getById(id);
			request.setAttribute("dicts", dicts);
		}
		return new ModelAndView("dict/addDicts");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveDicts")
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveDicts(Dicts dicts, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		if(StringUtil.isNotEmpty(dicts.getDictId())){
			Dicts dictsTemp = dictsService.getById(dicts.getDictId());
			// 将页面修改的信息在这里替换
			dictsTemp.setDictTypeName(dicts.getDictTypeName());
			dictsTemp.setDictValue(dicts.getDictValue());
			dictsService.updateDicts(dictsTemp);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");
			return result;
		}
		dictsService.saveDicts(dicts);
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
	@RequestMapping("/delDicts")
	@ResponseBody
	public ResultBean<String> delDicts(HttpServletRequest request,String id){
		ResultBean<String> result = new ResultBean<String>();
		dictsService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("删除成功");;
		return result;
	}

}
