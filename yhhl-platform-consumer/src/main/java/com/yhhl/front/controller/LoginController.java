package com.yhhl.front.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yhhl.common.LoginUser;
import com.yhhl.common.MD5Utils;
import com.yhhl.common.ResultBean;
import com.yhhl.common.SpringWebUtil;
import com.yhhl.roles.model.Roles;
import com.yhhl.roles.service.RolesServiceI;
import com.yhhl.user.model.User;
import com.yhhl.user.service.UserServiceI;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private UserServiceI userService;
	@Autowired 
	private RolesServiceI rolesService; 

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("front-page/login");
		return view;
	}
	
	@RequestMapping("/submitLogin")
	@ResponseBody
	public ResultBean<String> submitLogin(HttpServletRequest request, HttpServletResponse response) {
		ResultBean<String> result = new ResultBean<String>();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
			result.setFlag(ResultBean.FAIL);
			result.setMsg("手机号或密码不能为空！");
			return result;
		}
		password = MD5Utils.MD5(password);
		User user = userService.getByUserNameAndPwd(userName, password);
		if (user == null) {
			result.setFlag(ResultBean.FAIL);
			result.setMsg("手机号或密码不正确！");
			return result;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", user.getId());
		List<Roles> roleList = rolesService.getUserRole(map);
		if(roleList.size()>1){
			result.setFlag(ResultBean.FAIL);
			result.setMsg("账户异常！");
			return result;
		}
		
		LoginUser loginUser = new LoginUser();
		loginUser.setUserId(user.getId());
		loginUser.setUserName(user.getName());
		loginUser.setNikeName(user.getName());
		loginUser.setUserRole(roleList.get(0).getRoleName());
		SpringWebUtil.setSessionAttribute("loginUser", loginUser);
		result.setFlag(ResultBean.SUCCESS);
		return result;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		if(SpringWebUtil.getLoginUser()!=null){
			SpringWebUtil.removeSessionAttribute("loginUser");
		}
		ModelAndView view = new ModelAndView("front-page/index");
		return view;
	}
	
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("front-page/register");
		return view;
	}
	
	@RequestMapping("/submitRegister")
	@ResponseBody
	public ResultBean<String> submitRegister(HttpServletRequest request, HttpServletResponse response) {
		ResultBean<String> result = new ResultBean<String>();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if(StringUtils.isEmpty(userName)){
			result.setFlag(ResultBean.FAIL);
			result.setMsg("手机号不能为空！");
			return result;
		}
		if(StringUtils.isEmpty(password)){
			result.setFlag(ResultBean.FAIL);
			result.setMsg("密码不能为空！");
			return result;
		}
		User temp = userService.getByUserNameAndPwd(userName, null);
		if(temp!=null){
			result.setFlag(ResultBean.FAIL);
			result.setMsg("用户名已经存在，不能重复注册");
			return result;
		}
		User user = new User();
		user.setName(userName);
		user.setCreateTime(new Date());
		user.setPwd(password);
		userService.registerFromFront(user);
		LoginUser loginUser = new LoginUser();
		loginUser.setUserId(user.getId());
		loginUser.setUserName(user.getName());
		loginUser.setNikeName(user.getName());
		SpringWebUtil.setSessionAttribute("loginUser", loginUser);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("注册成功");
		return result;
	}
}
