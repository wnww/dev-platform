package com.yhhl.express.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.common.StringUtil;
import com.yhhl.common.WebUtil;
import com.yhhl.core.Page;
import com.yhhl.express.dao.ExpressMapper;
import com.yhhl.express.model.Express;
import com.yhhl.common.IdWorker;
import com.yhhl.common.ResultBean;

/**
 * 
 * <br>
 * <b>功能：</b>ExpressServiceImpl<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */
@Service("expressService")
public class ExpressServiceImpl implements ExpressServiceI {

	@Autowired
	private ExpressMapper expressMapper;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 保存
	 */
	@Override
	public void saveExpress(Express express){
				express.setExpressId(idWorker.buildId());
				expressMapper.insert(express);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<Express> getPage(Map<String, Object> filterMap, Page<Express> page, int pageNo, int pageSize) {
		int count = expressMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "", "" };//排序字段，可以是多个 类似：{ "name  desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Express> list = expressMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	*
	* 分页查询的count
	*/
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return expressMapper.getCount(filterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	public void updateExpress(Express express) {
		expressMapper.updateByPrimaryKey(express);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public Express getById(String id) {
		return expressMapper.selectByPrimaryKey(id);
	}
	
	

	@Override
	public Express selectByOrderId(Map<String, Object> map) {
		return expressMapper.selectByOrderId(map);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		expressMapper.deleteByPrimaryKey(id);
	}

	@Override
	public String getExpressInfo(String expressComCode,String expressCode) {
		String url = "http://www.kuaidi.com/all/[expressComCode]/[expressCode].html";
		try {
			url = url.replace("[expressComCode]", expressComCode);
			url = url.replace("[expressCode]", expressCode);
			ResultBean<String> result = WebUtil.executeGet(url);
			String html = new String(result.getData().getBytes("ISO-8859-1"),"UTF-8");
			String startTag = "<ul class=\"timeline a_timeline js_data\" style=\"margin-left: 0;float:left;\">";
			String endTag = "</ul>";
			html = html.substring(html.indexOf(startTag));
			html = html.substring(0, html.indexOf(endTag)+5);
			html = html.replace(" orange", "");
			int count = StringUtil.countMatches(html, "class=\"time_active\"");
			if(count>0){
				html = html.replaceAll("class=\"time_active\"", "class=\"time_normal\"");
				html = html.replaceFirst("class=\"time_normal\"", "class=\"time_active\"");
			}
			return html;
		} catch (Exception e) {
			e.printStackTrace();
			return "未获取到信息";
		}
		
	}

}
