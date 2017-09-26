/**
 * JAVACC DEMO 1.0
 * @copy right cbice company All rights reserved. 
 * @Package com.yhhl.platform.web  
 */
package com.yhhl.front.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yhhl.common.LocaleMessage;
import com.yhhl.common.LoginUser;
import com.yhhl.common.MD5Utils;
import com.yhhl.common.ResultBean;
import com.yhhl.user.model.User;
import com.yhhl.user.service.UserServiceI;

/**
 * description:
 * 
 * @author 创建时间：2014-11-17 @Copyright(c)2014:瀛海科技
 */
@Controller
@RequestMapping("/front")
public class IndexController {

	@Autowired
	private UserServiceI userService;

	@RequestMapping("/index")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("front-page/index");
//		HttpSession session = request.getSession();
//		LoginUser user = (LoginUser) session.getAttribute("loginUser");
		return view;
	}

	@RequestMapping("/login")
	@ResponseBody
	public ResultBean<String> login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResultBean<String> result = new ResultBean<String>();
		HttpSession session = request.getSession();
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		String userName = request.getParameter("userName");
		if(loginUser!=null && loginUser.getUserName().equals(userName)){
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("已经登录");
			return result;
		}
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
		if(loginUser==null){
			loginUser = new LoginUser();
		}
		loginUser.setUserName(user.getName());
		loginUser.setNikeName(user.getName());
		loginUser.setUserPhoto(
				"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505368475834&di=c9816987fdeecdf67bc6c97b88e7740e&imgtype=0&src=http%3A%2F%2Fpic1a.nipic.com%2F2008-12-01%2F200812193221582_2.jpg");
		session.setAttribute("loginUser", loginUser);
		return result;
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("login");
		HttpSession session = request.getSession();
		// 模拟登录成功
		LoginUser user = (LoginUser) session.getAttribute("loginUser");
		if (user != null) {
			session.removeAttribute("loginUser");
		}
		return view;
	}

	@RequestMapping("errorPage")
	public ModelAndView toPage(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("error/invalid");
		String error = request.getParameter("Errorcode");
		String[] params = new String[0];
		if ("ZBXSOFT-00000001".equals(error)) {
			error = LocaleMessage.getMessage(request, "invalid-001", params, "");
		} else if ("ZBXSOFT-00000002".equals(error)) {
			error = LocaleMessage.getMessage(request, "invalid-002", params, "");
		} else {
			error = LocaleMessage.getMessage(request, "invalid-003", params, "");
		}
		view.addObject("Errorcode", error);
		return view;
	}

}
