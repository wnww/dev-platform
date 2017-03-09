package com.yhhl.authority.controller;

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
import com.yhhl.authority.model.Authority;
import com.yhhl.authority.service.AuthorityServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>AuthorityController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */ 
@Controller
@RequestMapping("/authority") 
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
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("authority/authority-page");
	}
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
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
	@RequestMapping("/getAuthorityDatas")
	@ResponseBody
	public Map<String, Object> getAuthorityDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Authority> dataPage = new Page<Authority>();
		dataPage = authorityService.getPage(filterMap, dataPage, page, rows);
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", dataPage.getTotalCount());
		mapData.put("rows", dataPage.getResult());
		return mapData;
	}
	
	/**
	 * 查询权限列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSelectAuthorityDatas")
	@ResponseBody
	public Map<String, Object> getSelectAuthorityDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Authority> dataPage = new Page<Authority>();
		dataPage = authorityService.getSelectPage(filterMap, dataPage, page, rows);
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
	@RequestMapping("/saveAuthority")
	@Token(remove = true)
	@ResponseBody
	public Map<String, Object> saveAuthority(Authority authority, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(authority.getAuthId())){
			Authority authorityTemp = authorityService.getById(authority.getAuthId());
			// 将页面修改的信息在这里替换
			authorityTemp.setAuthName(authority.getAuthName());
			authorityTemp.setAuthType(authority.getAuthType());
			authorityTemp.setAuthMark(authority.getAuthMark());
			authorityService.updateAuthority(authorityTemp);
			map.put("flag", "T");
			map.put("msg", "修改成功");
			return map;
		}
		authorityService.saveAuthority(authority);
		map.put("flag", "T");
		map.put("msg", "保存成功");
		return map;
	}
	
	/**
	* 删除
	*
	* @param request
	* @param id
	*/
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
