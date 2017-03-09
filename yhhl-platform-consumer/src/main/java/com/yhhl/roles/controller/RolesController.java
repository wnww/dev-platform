package com.yhhl.roles.controller;

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
import com.yhhl.roles.model.Roles;
import com.yhhl.roles.service.RolesServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>RolesController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */ 
@Controller
@RequestMapping("/roles") 
public class RolesController {
	
	private final static Logger log= Logger.getLogger(RolesController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private RolesServiceI rolesService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("roles/roles-page");
	}
	
	@RequestMapping("/showRolesPage")
	public ModelAndView showRolesPage(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		request.setAttribute("userId", userId);
		return new ModelAndView("roles/select-roles-page");
	}
	
	/**
	 * 查询用户 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRolesDatas")
	@ResponseBody
	public Map<String, Object> getRolesDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Roles> dataPage = new Page<Roles>();
		dataPage = rolesService.getPage(filterMap, dataPage, page, rows);
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", dataPage.getTotalCount());
		mapData.put("rows", dataPage.getResult());
		return mapData;
	}
	
	/**
	 * 查询用户 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSelectRolesDatas")
	@ResponseBody
	public Map<String, Object> getSelectRolesDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Roles> dataPage = new Page<Roles>();
		dataPage = rolesService.getSelectPage(filterMap, dataPage, page, rows);
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
	@RequestMapping("/initAddRoles")
	@Token(save = true)
	public ModelAndView initAddRoles(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			Roles roles = rolesService.getById(id);
			request.setAttribute("roles", roles);
		}
		return new ModelAndView("roles/addRoles");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveRoles")
	@Token(remove = true)
	@ResponseBody
	public Map<String, Object> saveRoles(Roles roles, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(roles.getRoleId())){
			Roles rolesTemp = rolesService.getById(roles.getRoleId());
			// 将页面修改的信息在这里替换
			rolesTemp.setRoleName(roles.getRoleName());
			rolesTemp.setRemark(roles.getRemark());
			rolesService.updateRoles(rolesTemp);
			map.put("flag", "T");
			map.put("msg", "修改成功");
			return map;
		}
		rolesService.saveRoles(roles);
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
	@RequestMapping("/delRoles")
	@ResponseBody
	public Map<String, Object> delRoles(HttpServletRequest request,String id){
		Map<String, Object> map = new HashMap<String, Object>();
		rolesService.deleteById(id);
		map.put("flag", "T");
		map.put("msg", "删除成功");
		return map;
	}

}
