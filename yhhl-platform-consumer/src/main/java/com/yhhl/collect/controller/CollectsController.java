package com.yhhl.collect.controller;

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

import com.yhhl.collect.model.Collects;
import com.yhhl.collect.service.CollectsServiceI;
import com.yhhl.common.Constants;
import com.yhhl.common.DateUtils;
import com.yhhl.common.ResultBean;
import com.yhhl.common.SpringWebUtil;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.LoginCheck;
import com.yhhl.interceptor.Token;
 
/**
 * 
 * <br>
 * <b>功能：</b>CollectsController<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */ 
@Controller
@RequestMapping("/sysManage/collects") 
public class CollectsController {
	
	private final static Logger log= Logger.getLogger(CollectsController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private CollectsServiceI collectsService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/index")
	public ModelAndView index() {
	       return new ModelAndView("collect/collects-page");
	}
	
	/**
	 * 查询列表
	 * @param request
	 * @return
	 */
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/getCollectsDatas")
	@ResponseBody
	public ResultBean<Collects> getCollectsDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Collects> dataPage = new Page<Collects>();
		dataPage = collectsService.getPage(filterMap, dataPage, page, rows);
		ResultBean<Collects> result = new ResultBean<Collects>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}
	
	/**
	 * 进入到初始化新增、修改页面
	 * @param request
	 * @return
	 */
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/initAddCollects")
	@Token(save = true)
	public ModelAndView initAddCollects(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			Collects collects = collectsService.getById(id);
			request.setAttribute("collects", collects);
		}
		return new ModelAndView("collect/addCollects");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/saveCollects")
	@ResponseBody
	public ResultBean<String> saveCollects(Collects collects, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		Map<String,Object> filterMap = new HashMap<String,Object>();
		filterMap.put("prodId", collects.getProdId());
		filterMap.put("userId", SpringWebUtil.getLoginUser().getUserId());
		int count = collectsService.getCount(filterMap);
		if(count>0){
			result.setFlag(ResultBean.FAIL);
			result.setMsg("已经收藏，不需要重复收藏。");;
			return result;
		}
		collects.setUserId(SpringWebUtil.getLoginUser().getUserId());
		collects.setCreateTime(DateUtils.getNowDateTime());
		collectsService.saveCollects(collects);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("收藏成功");
		return result;
	}
	
	/**
	* 删除
	*
	* @param request
	* @param id
	*/
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/delCollects")
	@ResponseBody
	public ResultBean<String> delCollects(HttpServletRequest request,String id){
		ResultBean<String> result = new ResultBean<String>();
		collectsService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("删除成功");
		return result;
	}

}
