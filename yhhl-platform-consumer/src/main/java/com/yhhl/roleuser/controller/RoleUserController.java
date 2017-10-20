package com.yhhl.roleuser.controller;

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

import com.yhhl.common.Constants;
import com.yhhl.common.ResultBean;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.LoginCheck;
import com.yhhl.roleuser.model.RoleUser;
import com.yhhl.roleuser.service.RoleUserServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>RoleUserController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海科技<br>
 */ 
@Controller
@RequestMapping("/sysManage/roleUser") 
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
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("roleuser/roleUser-page");
	}
	
	/**
	 * 查询用户 列表
	 * @param request
	 * @return
	 */
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/getRoleUserDatas")
	@ResponseBody
	public ResultBean<RoleUser> getRoleUserDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<RoleUser> dataPage = new Page<RoleUser>();
		dataPage = roleUserService.getPage(filterMap, dataPage, page, rows);
		ResultBean<RoleUser> result = new ResultBean<RoleUser>();
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
	@RequestMapping("/initAddRoleUser")
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
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/saveRoleUser")
	@ResponseBody
	public ResultBean<String> saveRoleUser(RoleUser roleUser, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		RoleUser ru = roleUserService.getByUserIdAndRoleId(roleUser.getUserId(),roleUser.getRoleId());
		if(ru==null){
			roleUserService.saveRoleUser(roleUser);
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
	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/delRoleUser")
	@ResponseBody
	public ResultBean<String> delRoleUser(HttpServletRequest request,String userId,String roleId){
		ResultBean<String> result = new ResultBean<String>();
		int row = roleUserService.deleteByUserIdAndRoleId(userId,roleId);
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
