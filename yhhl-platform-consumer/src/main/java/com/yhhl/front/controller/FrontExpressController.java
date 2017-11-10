/**
 * JAVACC DEMO 1.0
 * @copy right cbice company All rights reserved. 
 * @Package com.yhhl.platform.web  
 */
package com.yhhl.front.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yhhl.common.Constants;
import com.yhhl.common.ResultBean;
import com.yhhl.common.SpringWebUtil;
import com.yhhl.common.StringUtil;
import com.yhhl.common.WebUtil;
import com.yhhl.express.model.Express;
import com.yhhl.express.model.ExpressDto;
import com.yhhl.express.service.ExpressServiceI;
import com.yhhl.interceptor.LoginCheck;

/**
 * description:
 * 
 * @author 创建时间：2014-11-17 @Copyright(c)2014:瀛海科技
 */
@Controller
@RequestMapping("/express")
public class FrontExpressController {
	
	@Autowired
	private ExpressServiceI expressService;

	@RequestMapping("/index")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("front-page/express");
		String orderId = request.getParameter("orderId");
		if(StringUtil.isEmpty(orderId)){
			request.setAttribute("expressInfo", "订单不存在！");
			return view;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId", orderId);
		Express express = expressService.selectByOrderId(map);
		if(express==null){
			String expressInfo = "<center style='padding-top:50px;'><img src='"+SpringWebUtil.getContextPath()+"/front-mobile-static/images/noexpress.jpg'/></center>";
			request.setAttribute("expressInfo", expressInfo);
			return view;
		}
		String expressInfo = expressService.getExpressInfo(express.getExpressComCode(),express.getExpressCode());
		request.setAttribute("expressInfo", expressInfo);
		return view;
	}
	
	
	/**
	 * 查询列表
	 * 
	 * @param request
	 * @return
	 */
	@LoginCheck(frontMustLogin=Constants.TRUE)
	@RequestMapping("/getExpressInfoByAPI")
	@ResponseBody
	public ResultBean<String> getExpressInfoByAPI(HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		String orderId = request.getParameter("orderId");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId", orderId);
		Express express = expressService.selectByOrderId(map);
		if(express==null){
			result.setFlag(ResultBean.FAIL);
			result.setMsg("还没有发货信息");
			return result;
		}
		StringBuffer expressSearchUrl = new StringBuffer("http://api.kuaidi.com/openapi.html?id=");
		//&order=[desc|asc]
		expressSearchUrl.append("&com=").append(express.getExpressComCode()); //快递公司代码
		expressSearchUrl.append("&nu=").append(express.getExpressCode()); // 快递单号
		expressSearchUrl.append("&show=0");// 0表示返回json； 1表示返回xml
		expressSearchUrl.append("&muti=0"); //0:返回多行完整的信息， 1:只返回一行信息。 不填默认返回多行。 
		expressSearchUrl.append("&order=desc"); // desc：按时间由新到旧排列，asc：按时间由旧到新排列。不填默认由新到旧排列（大小写不敏感）
		try {
			result = WebUtil.executeGet(expressSearchUrl.toString());
			if(result.getFlag() == ResultBean.SUCCESS){
				String suc = "\"success\":true";
				if(result.getData().indexOf(suc)>=0){
					ExpressDto expressDto = JSON.parseObject(result.getData(), ExpressDto.class);
					System.out.println(result.getFlag()+"  "+result.getMsg()+"   "+expressDto.getSuccess()+"  "+expressDto.getReason());
				}else{
					System.out.println();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
