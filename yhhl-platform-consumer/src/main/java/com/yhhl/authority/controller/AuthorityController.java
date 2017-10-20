package com.yhhl.authority.controller;

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

import com.yhhl.authority.model.Authority;
import com.yhhl.authority.service.AuthorityServiceI;
import com.yhhl.common.Constants;
import com.yhhl.common.ResultBean;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.LoginCheck;
import com.yhhl.interceptor.Token;
 
/**
 * 
 * <br>
 * <b>功能：</b>AuthorityController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海科技<br>
 */ 
@Controller
@RequestMapping("/sysManage/authority") 
public class AuthorityController {
	
	private final static Logger log= Logger.getLogger(AuthorityController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private AuthorityServiceI authorityService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("authority/authority-page");
	}
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/showAuthorityPage")
	public ModelAndView showAuthorityPage(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		request.setAttribute("roleId", roleId);
		return new ModelAndView("authority/select-authority-page");
	}
	
	/**
	 * 查询权限列表
	 * @param request
	 * @return
	 */
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/getAuthorityDatas")
	@ResponseBody
	public ResultBean<Authority> getAuthorityDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Authority> dataPage = new Page<Authority>();
		dataPage = authorityService.getPage(filterMap, dataPage, page, rows);
		ResultBean<Authority> result = new ResultBean<Authority>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}
	
	/**
	 * 查询权限列表
	 * @param request
	 * @return
	 */
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/getSelectAuthorityDatas")
	@ResponseBody
	public ResultBean<Authority> getSelectAuthorityDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Authority> dataPage = new Page<Authority>();
		dataPage = authorityService.getSelectPage(filterMap, dataPage, page, rows);
		ResultBean<Authority> result = new ResultBean<Authority>();
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
	@RequestMapping("/initAddAuthority")
	@Token(save = true)
	public ModelAndView initAddAuthority(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			Authority authority = authorityService.getById(id);
			request.setAttribute("authority", authority);
		}
		return new ModelAndView("authority/addAuthority");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/saveAuthority")
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveAuthority(Authority authority, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		if(StringUtil.isNotEmpty(authority.getAuthId())){
			Authority authorityTemp = authorityService.getById(authority.getAuthId());
			// 将页面修改的信息在这里替换
			authorityTemp.setAuthName(authority.getAuthName());
			authorityTemp.setAuthType(authority.getAuthType());
			authorityTemp.setAuthMark(authority.getAuthMark());
			authorityService.updateAuthority(authorityTemp);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");
			return result;
		}
		authorityService.saveAuthority(authority);
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
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/delAuthority")
	@ResponseBody
	public Map<String, Object> delAuthority(HttpServletRequest request,String id){
		Map<String, Object> map = new HashMap<String, Object>();
		authorityService.deleteById(id);
		map.put("flag", "T");
		map.put("msg", "删除成功");
		return map;
	}

}
