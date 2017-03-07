package com.yhhl.roleauth.controller;

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
import com.yhhl.roleauth.model.RoleAuth;
import com.yhhl.roleauth.service.RoleAuthServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>RoleAuthController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */ 
@Controller
@RequestMapping("/roleAuth") 
public class RoleAuthController {
	
	private final static Logger log= Logger.getLogger(RoleAuthController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private RoleAuthServiceI roleAuthService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("roleauth/roleAuth-page");
	}
	
	/**
	 * 查询用户 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRoleAuthDatas")
	@ResponseBody
	public Map<String, Object> getRoleAuthDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<RoleAuth> dataPage = new Page<RoleAuth>();
		dataPage = roleAuthService.getPage(filterMap, dataPage, page, rows);
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
	@RequestMapping("/initAddRoleAuth")
	@Token(save = true)
	public ModelAndView initAddRoleAuth(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			RoleAuth roleAuth = roleAuthService.getById(id);
			request.setAttribute("roleAuth", roleAuth);
		}
		return new ModelAndView("roleauth/addRoleAuth");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveRoleAuth")
	@Token(remove = true)
	@ResponseBody
	public Map<String, Object> saveRoleAuth(RoleAuth roleAuth, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(roleAuth.getId())){
			RoleAuth roleAuthTemp = roleAuthService.getById(roleAuth.getId());
			// 将页面修改的信息在这里替换
			roleAuthService.updateRoleAuth(roleAuthTemp);
			map.put("flag", "T");
			map.put("msg", "修改成功");
			return map;
		}
		roleAuthService.saveRoleAuth(roleAuth);
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
	@RequestMapping("/delRoleAuth")
	@ResponseBody
	public Map<String, Object> delRoleAuth(HttpServletRequest request,String id){
		Map<String, Object> map = new HashMap<String, Object>();
		roleAuthService.deleteById(id);
		map.put("flag", "T");
		map.put("msg", "删除成功");
		return map;
	}

}
