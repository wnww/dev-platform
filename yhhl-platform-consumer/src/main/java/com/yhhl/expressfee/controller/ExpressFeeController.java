package com.yhhl.expressfee.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.Token;
import com.yhhl.platform.service.CacheOperateI;
import com.yhhl.common.ResultBean;
import com.yhhl.expressfee.model.ExpressFee;
import com.yhhl.expressfee.service.ExpressFeeServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>ExpressFeeController<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */ 
@Controller
@RequestMapping("/sysManage/expressFee") 
public class ExpressFeeController {
	
	private final static Logger log= Logger.getLogger(ExpressFeeController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private ExpressFeeServiceI expressFeeService;
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private CacheOperateI cacheOperate; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
	       return new ModelAndView("expressfee/expressFee-page");
	}
	
	/**
	 * 查询列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getExpressFeeDatas")
	@ResponseBody
	public ResultBean<ExpressFee> getExpressFeeDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<ExpressFee> dataPage = new Page<ExpressFee>();
		dataPage = expressFeeService.getPage(filterMap, dataPage, page, rows);
		ResultBean<ExpressFee> result = new ResultBean<ExpressFee>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}
	
	/**
	 * 进入到初始化新增、修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddExpressFee")
	@Token(save = true)
	public ModelAndView initAddExpressFee(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			ExpressFee expressFee = expressFeeService.getById(id);
			request.setAttribute("expressFee", expressFee);
		}else{
			request.setAttribute("expressFee", new ExpressFee());
		}
		return new ModelAndView("expressfee/addExpressFee");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveExpressFee")
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveExpressFee(ExpressFee expressFee, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		if(StringUtil.isNotEmpty(expressFee.getExpressFeeId())){
			ExpressFee expressFeeTemp = expressFeeService.getById(expressFee.getExpressFeeId());
			// 将页面修改的信息在这里替换
			expressFeeTemp.setProvince(expressFee.getProvince());
			expressFeeTemp.setCity(expressFee.getCity());
			expressFeeTemp.setFee(expressFee.getFee());
			expressFeeService.updateExpressFee(expressFeeTemp);
			cacheOperate.cacheExpressFee(request.getSession().getServletContext());
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");
			return result;
		}
		expressFeeService.saveExpressFee(expressFee);
		cacheOperate.cacheExpressFee(request.getSession().getServletContext());
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
	@RequestMapping("/delExpressFee")
	@ResponseBody
	public ResultBean<String> delExpressFee(HttpServletRequest request,String id){
		ResultBean<String> result = new ResultBean<String>();
		expressFeeService.deleteById(id);
		cacheOperate.cacheExpressFee(request.getSession().getServletContext());
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("删除成功");
		return result;
	}

}
