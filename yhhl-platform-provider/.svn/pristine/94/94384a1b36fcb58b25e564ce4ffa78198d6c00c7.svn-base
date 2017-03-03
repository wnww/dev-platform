package com.yhhl.webservice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhhl.user.model.User;
import com.yhhl.webservice.service.WsUserService;
import com.yhhl.wsts.common.dto.MapTransactionDTO;
import com.yhhl.wsts.common.dto.VoidTransactionDTO;

@Controller
@RequestMapping("/wsUser")
public class WsUserController {

//	private WsUserService wsUserService;
//
//	public WsUserController() {
//		if (this.wsUserService == null) {
//			this.wsUserService = new WsUserService();
//		}
//	}
//
//	@RequestMapping("/wsAddUser.do")
//	@ResponseBody
//	public Map<String, Object> wsAddUser(HttpServletRequest request, HttpServletResponse response) {
//		User user = new User();
//		// String userId = new Date().getTime()+"";
//		user.setId(request.getParameter("id"));
//		user.setName(request.getParameter("name"));
//		user.setPwd(request.getParameter("pwd"));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//		try {
//			user.setModifydatetime(sdf.parse(request.getParameter("modifydatetime")));
//			user.setCreatedatetime(sdf.parse(request.getParameter("createdatetime")));
//		} catch (ParseException e) {
//			user.setModifydatetime(new Date());
//			user.setCreatedatetime(new Date());
//			//e.printStackTrace();
//		}
//		//MapTransactionDTO transactonDTO = wsUserService.addUser(user);
//		Map<String, Object> map = new HashMap<String, Object>();
//	//	map.put("uid", transactonDTO.getTransactionInfo());
//		return map;
//	}

//	@RequestMapping("/wsUpdateUser.do")
//	@ResponseBody
//	public Map<String, Object> wsUpdateUser(HttpServletRequest request, HttpServletResponse response) {
//		User user = wsUserService.getUserById(request.getParameter("id"));
//		String uid = "";
//		if (user != null) {
//			user.setName(request.getParameter("name"));
//			user.setPwd(request.getParameter("pwd"));
//			//VoidTransactionDTO transactonDTO = wsUserService.editUser(user);
//		//	uid = transactonDTO.getTransactionInfo();
//		}
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("uid", uid);
//		return map;
//	}
}
