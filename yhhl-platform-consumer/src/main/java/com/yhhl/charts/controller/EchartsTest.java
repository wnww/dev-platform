/**
 * JAVACC DEMO 1.0
 * @copy right cbice company All rights reserved. 
 * @Package com.yhhl.platform.web  
 */
package com.yhhl.charts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yhhl.common.LocaleMessage;
import com.yhhl.common.LoginUser;

/**
 * description:  
 * @author Hou Dayu 创建时间：2014-11-17  
 * @Copyright(c)2014:瀛海科技
 */
@Controller
@RequestMapping("/charts")
public class EchartsTest {

	@RequestMapping("/index")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("charts/index");
		return view;
	}
	
	
}
