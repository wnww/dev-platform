package com.yhhl.lottery.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.Token;
import com.yhhl.common.ResultBean;
import com.yhhl.lottery.model.Lottery;
import com.yhhl.lottery.service.LotteryServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>LotteryController<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */ 
@Controller
@RequestMapping("/lottery") 
public class LotteryController {
	
	private final static Logger log= Logger.getLogger(LotteryController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private LotteryServiceI lotteryService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
	       return new ModelAndView("lottery/lottery-page");
	}
	
	/**
	 * 查询列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getLotteryDatas")
	@ResponseBody
	public ResultBean<Lottery> getLotteryDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Lottery> dataPage = new Page<Lottery>();
		dataPage = lotteryService.getPage(filterMap, dataPage, page, rows);
		ResultBean<Lottery> result = new ResultBean<Lottery>();
		result.setTotal(dataPage.getTotalCount());
		result.setRows(dataPage.getResult());
		return result;
	}
	
	/**
	 * 进入到初始化新增、修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddLottery")
	@Token(save = true)
	public ModelAndView initAddLottery(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			Lottery lottery = lotteryService.getById(id);
			request.setAttribute("lottery", lottery);
		}
		return new ModelAndView("lottery/addLottery");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveLottery")
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveLottery(Lottery lottery, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		if(StringUtil.isNotEmpty(lottery.getExpectId())){
			Lottery lotteryTemp = lotteryService.getById(lottery.getExpectId());
			// 将页面修改的信息在这里替换
			lotteryService.updateLottery(lotteryTemp);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");
			return result;
		}
		lotteryService.saveLottery(lottery);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("保存成功");;
		return result;
	}
	
	/**
	* 删除
	*
	* @param request
	* @param id
	*/
	@RequestMapping("/delLottery")
	@ResponseBody
	public ResultBean<String> delLottery(HttpServletRequest request,String id){
		ResultBean<String> result = new ResultBean<String>();
		lotteryService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("删除成功");
		return result;
	}

}
