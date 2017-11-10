package com.yhhl.express.controller;

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
import com.yhhl.common.DateUtils;
import com.yhhl.common.ResultBean;
import com.yhhl.express.model.Express;
import com.yhhl.express.service.ExpressServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>ExpressController<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */ 
@Controller
@RequestMapping("/sysManage/express") 
public class ExpressController {
	
	private final static Logger log= Logger.getLogger(ExpressController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private ExpressServiceI expressService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
	       return new ModelAndView("express/express-page");
	}
	
	/**
	 * 查询列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getExpressDatas")
	@ResponseBody
	public ResultBean<Express> getExpressDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Express> dataPage = new Page<Express>();
		dataPage = expressService.getPage(filterMap, dataPage, page, rows);
		ResultBean<Express> result = new ResultBean<Express>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}
	
	/**
	 * 进入到初始化新增、修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddExpress")
	@Token(save = true)
	public ModelAndView initAddExpress(HttpServletRequest request) {
		if(StringUtil.isNotEmpty(request.getParameter("orderId"))){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("orderId", request.getParameter("orderId"));
			Express et = expressService.selectByOrderId(map);
			if(et==null){
				Express express = new Express();
				express.setOrderId(request.getParameter("orderId"));
				request.setAttribute("express", express);
			}else{
				request.setAttribute("express", et);
			}
			
			
		}
		return new ModelAndView("express/addExpress");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveExpress")
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveExpress(Express express, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		if(StringUtil.isNotEmpty(express.getExpressId())){
			Express expressTemp = expressService.getById(express.getExpressId());
			// 将页面修改的信息在这里替换
			expressTemp.setExpressCode(express.getExpressCode());
			expressTemp.setExpressComCode(express.getExpressComCode());
			expressTemp.setExpressComName(express.getExpressComName());
			expressService.updateExpress(expressTemp);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");
			return result;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId", express.getOrderId());
		Express et = expressService.selectByOrderId(map);
		if(et!=null){
			result.setFlag(ResultBean.FAIL);
			result.setMsg("已经添加过快递信息，不能重复添加");
			return result;
		}
		express.setSendTime(DateUtils.getNowDateTime());
		expressService.saveExpress(express);
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
	@RequestMapping("/delExpress")
	@ResponseBody
	public ResultBean<String> delExpress(HttpServletRequest request,String id){
		ResultBean<String> result = new ResultBean<String>();
		expressService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("删除成功");
		return result;
	}

}
