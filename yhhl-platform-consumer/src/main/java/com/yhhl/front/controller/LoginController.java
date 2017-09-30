package com.yhhl.front.controller;

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
import com.yhhl.user.model.User;
import com.yhhl.user.service.UserServiceI;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private UserServiceI userService;

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
			result.setMsg("用户名或密码不能为空！");
			return result;
		}
		password = MD5Utils.MD5(password);
		User user = userService.getByUserNameAndPwd(userName, password);
		if (user == null) {
			result.setFlag(ResultBean.FAIL);
			result.setMsg("用户名或密码不正确！");
			return result;
		}
		LoginUser loginUser = new LoginUser();
		loginUser.setUserName(user.getName());
		loginUser.setNikeName(user.getName());
		request.getSession().setAttribute("loginUser", loginUser);
		result.setFlag(ResultBean.SUCCESS);
		return result;
	}
	
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("front-page/login");
		return view;
	}
	
	@RequestMapping("/submitRegister")
	public ModelAndView submitRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("front-page/login");
		return view;
	}
}
