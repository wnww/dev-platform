/**
 * JAVACC DEMO 1.0
 * @copy right cbice company All rights reserved. 
 * @Package com.yhhl.platform.web  
 */
package com.yhhl.platform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yhhl.common.Constants;
import com.yhhl.common.LocaleMessage;
import com.yhhl.common.LoginUser;
import com.yhhl.common.MD5Utils;
import com.yhhl.common.RandomUtils;
import com.yhhl.common.ResultBean;
import com.yhhl.common.Sha1Util;
import com.yhhl.common.WebUtil;
import com.yhhl.interceptor.LoginCheck;
import com.yhhl.roles.model.Roles;
import com.yhhl.roles.service.RolesServiceI;
import com.yhhl.user.model.User;
import com.yhhl.user.service.UserServiceI;
import com.yhhl.weixin.util.AccessTokenUtil;
import com.yhhl.weixin.util.MessageUtil;

/**
 * description:
 * 
 * @author 创建时间：2014-11-17 @Copyright(c)2014:瀛海科技
 */
@Controller
@RequestMapping("/sysManage")
public class MainAction {

	private final static Logger log= Logger.getLogger(MainAction.class);
	
	@Autowired
	private UserServiceI userService;
	@Autowired 
	private RolesServiceI rolesService; 

	@LoginCheck(backMustLogin=Constants.TRUE)
	@RequestMapping("/index")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("main");
		return view;
	}
	
	@RequestMapping("/initLogin")
	public ModelAndView initLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("login");
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
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", user.getId());
		List<Roles> roleList = rolesService.getUserRole(map);
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<roleList.size(); i++){
			Roles role = roleList.get(i);
			if(i>0){
				sb.append(",");
			}
			sb.append(role.getRoleName());
		}
		loginUser.setUserId(user.getId());
		loginUser.setUserName(user.getName());
		loginUser.setNikeName(user.getName());
		loginUser.setUserPhoto(
				"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505368475834&di=c9816987fdeecdf67bc6c97b88e7740e&imgtype=0&src=http%3A%2F%2Fpic1a.nipic.com%2F2008-12-01%2F200812193221582_2.jpg");
		loginUser.setUserRole(sb.toString());
		
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
	
	@RequestMapping("/getToken")
	@ResponseBody
	public String getToken(HttpServletRequest request){
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		log.debug("signature:"+ signature);
		log.debug("timestamp:"+ timestamp);
		log.debug("nonce:"+ nonce);
		log.debug("echostr:"+ echostr);
		
		SortedSet<String> set = new TreeSet<>();
		set.add(AccessTokenUtil.getTestToke());
		set.add(timestamp);
		set.add(nonce);
		StringBuffer sb = new StringBuffer();
		for(String str : set){
			sb.append(str);
		}
		try {
			String sign = Sha1Util.getSha1(sb.toString());
			log.debug("sign============"+sign);
			if(sign.equals(signature)){
				return echostr;
			}else{
				log.debug("签名验证失败=============signature="+signature+"|timestamp="+timestamp+"|nonce="+nonce+"|echostr="+echostr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AccessTokenUtil.getTestToke();
	}

}
