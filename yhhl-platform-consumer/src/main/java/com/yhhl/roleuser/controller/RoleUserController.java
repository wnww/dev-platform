package com.yhhl.roleuser.controller;

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
import com.yhhl.roleuser.model.RoleUser;
import com.yhhl.roleuser.service.RoleUserServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>RoleUserController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */ 
@Controller
@RequestMapping("/roleUser") 
public class RoleUserController {
	
	private final static Logger log= Logger.getLogger(RoleUserController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private RoleUserServiceI roleUserService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("roleuser/roleUser-page");
	}
	
	/**
	 * 查询用户 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRoleUserDatas")
	@ResponseBody
	public Map<String, Object> getRoleUserDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<RoleUser> dataPage = new Page<RoleUser>();
		dataPage = roleUserService.getPage(filterMap, dataPage, page, rows);
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
	@RequestMapping("/initAddRoleUser")
	@Token(save = true)
	public ModelAndView initAddRoleUser(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			RoleUser roleUser = roleUserService.getById(id);
			request.setAttribute("roleUser", roleUser);
		}
		return new ModelAndView("roleuser/addRoleUser");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveRoleUser")
	@Token(remove = true)
	@ResponseBody
	public Map<String, Object> saveRoleUser(RoleUser roleUser, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(roleUser.getId())){
			RoleUser roleUserTemp = roleUserService.getById(roleUser.getId());
			// 将页面修改的信息在这里替换
			roleUserService.updateRoleUser(roleUserTemp);
			map.put("flag", "T");
			map.put("msg", "修改成功");
			return map;
		}
		roleUserService.saveRoleUser(roleUser);
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
	@RequestMapping("/delRoleUser")
	@ResponseBody
	public Map<String, Object> delRoleUser(HttpServletRequest request,String id){
		Map<String, Object> map = new HashMap<String, Object>();
		roleUserService.deleteById(id);
		map.put("flag", "T");
		map.put("msg", "删除成功");
		return map;
	}

}
