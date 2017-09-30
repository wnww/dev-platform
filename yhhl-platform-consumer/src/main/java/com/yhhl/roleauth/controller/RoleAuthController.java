package com.yhhl.roleauth.controller;

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

import com.yhhl.common.ResultBean;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.roleauth.model.RoleAuth;
import com.yhhl.roleauth.service.RoleAuthServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>RoleAuthController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海科技<br>
 */ 
@Controller
@RequestMapping("/sysManage/roleAuth") 
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
	public ResultBean<RoleAuth> getRoleAuthDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<RoleAuth> dataPage = new Page<RoleAuth>();
		dataPage = roleAuthService.getPage(filterMap, dataPage, page, rows);
		ResultBean<RoleAuth> result = new ResultBean<RoleAuth>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}
	
	/**
	 * 进入到初始化新增、修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddRoleAuth")
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
	@ResponseBody
	public ResultBean<String> saveRoleAuth(RoleAuth roleAuth, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		RoleAuth ru = roleAuthService.getByAuthIdAndRoleId(roleAuth.getAuthId(),roleAuth.getRoleId());
		if(ru==null){
			roleAuthService.saveRoleAuth(roleAuth);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("保存成功");
			
		}else{
			result.setFlag(ResultBean.FAIL);
			result.setMsg("已经拥有该角色！");
		}
		return result;
	}
	
	/**
	* 删除
	*
	* @param request
	* @param id
	*/
	@RequestMapping("/delRoleAuth")
	@ResponseBody
	public ResultBean<String> delRoleAuth(HttpServletRequest request,String authId,String roleId){
		ResultBean<String> result = new ResultBean<String>();
		int row = roleAuthService.deleteByAuthIdAndRoleId(authId,roleId);
		if(row >= 1){
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("删除成功");
		}else{
			result.setFlag(ResultBean.FAIL);
			result.setMsg("删除失败");
		}
		return result;
	}

}
