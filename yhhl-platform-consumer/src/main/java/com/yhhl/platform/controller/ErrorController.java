/**
 * JAVACC DEMO 1.0
 * @copy right cbice company All rights reserved. 
 * @Package com.yhhl.platform.web  
 */
package com.yhhl.platform.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * description:
 * 
 * @author 创建时间：2014-11-17 @Copyright(c)2014:瀛海科技
 */
@Controller
@RequestMapping("/")
public class ErrorController {

	@RequestMapping("/error")
	public ModelAndView toPage(HttpServletRequest request,String errorCode) {
		ModelAndView view = new ModelAndView("front-page/error");
		view.addObject("errorCode", errorCode);
		return view;
	}
}
