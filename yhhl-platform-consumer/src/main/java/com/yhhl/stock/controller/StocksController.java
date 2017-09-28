package com.yhhl.stock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.yhhl.common.ResultBean;
import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.Token;
import com.yhhl.stock.model.Stocks;
import com.yhhl.stock.service.StocksServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>StocksController<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 12, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */ 
@Controller
@RequestMapping("/stocks") 
public class StocksController {
	
	private final static Logger log= Logger.getLogger(StocksController.class);
	
	// Servrice start
	@Autowired //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private StocksServiceI stocksService; 
	
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("stock/stocks-page");
	}
	
	/**
	 * 查询库存 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getStocksDatas")
	@ResponseBody
	public Map<String, Object> getStocksDatas(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Stocks> dataPage = new Page<Stocks>();
		dataPage = stocksService.getPage(filterMap, dataPage, page, rows);
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", dataPage.getTotalCount());
		mapData.put("rows", dataPage.getResult());
		return mapData;
	}
	
	/**
	 * 查询库存 列表--不分页
	 * @param request
	 * @return
	 */
	@RequestMapping("/getStocksList")
	@ResponseBody
	public List<Stocks> getStocksList(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<Stocks> dataPage = new Page<Stocks>();
		dataPage = stocksService.getPage(filterMap, dataPage, page, rows);
		return dataPage.getResult();
	}
	
	/**
	 * 进入到初始化新增、修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddStocks")
	@Token(save = true)
	public ModelAndView initAddStocks(HttpServletRequest request) {
		String stockId = request.getParameter("stockId");
		String prodId = request.getParameter("prodId");
		if(StringUtil.isNotEmpty(stockId)){
			Stocks stocks = stocksService.getById(stockId);
			request.setAttribute("stocks", stocks);
			request.setAttribute("prodId", stocks.getProdId());
		}else{
			request.setAttribute("prodId", prodId);
		}
		return new ModelAndView("product/addProductsExtend");
	}
	
	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveStocks")
	@Token(remove = true)
	@ResponseBody
	public ResultBean<String> saveStocks(Stocks stocks, HttpServletRequest request) {
		ResultBean<String> result = new ResultBean<String>();
		if(StringUtil.isNotEmpty(stocks.getStockId())){
			Stocks stocksTemp = stocksService.getById(stocks.getStockId());
			// 将页面修改的信息在这里替换
			stocksTemp.setColorsId(stocks.getColorsId());
			stocksTemp.setSpecificationId(stocks.getSpecificationId());
			stocksTemp.setRemainNum(stocks.getRemainNum());
			stocksTemp.setSelledNum(stocks.getSelledNum());
			stocksService.updateStocks(stocksTemp);
			result.setFlag(ResultBean.SUCCESS);
			result.setMsg("修改成功");;
			return result;
		}
		stocksService.saveStocks(stocks);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("保存成功");
		return result;
	}
	
	/**
	* 删除
	*
	* @param request
	* @param id
	*/
	@RequestMapping("/delStocks")
	@ResponseBody
	public ResultBean<String> delStocks(HttpServletRequest request,String id){
		ResultBean<String> result = new ResultBean<String>();
		stocksService.deleteById(id);
		result.setFlag(ResultBean.SUCCESS);
		result.setMsg("删除成功");
		return result;
	}

}
