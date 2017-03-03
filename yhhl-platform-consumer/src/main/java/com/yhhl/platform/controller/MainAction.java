/**
 * JAVACC DEMO 1.0
 * @copy right cbice company All rights reserved. 
 * @Package com.yhhl.platform.web  
 */
package com.yhhl.platform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yhhl.common.LocaleMessage;

/**
 * description:  
 * @author Hou Dayu 创建时间：2014-11-17  
 * @Copyright(c)2014:国版中心
 */
@Controller
@RequestMapping("/")
public class MainAction {

	@RequestMapping("/index")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("main");

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
