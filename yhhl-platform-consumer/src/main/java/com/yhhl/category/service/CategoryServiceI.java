package com.yhhl.category.service;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

import com.yhhl.core.Page;
import com.yhhl.category.model.Category;

/**
 * 
 * <br>
 * <b>功能：</b>CategoryServiceI<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */
public interface CategoryServiceI {

	public final static Logger log= Logger.getLogger(CategoryServiceI.class);

	Category getById(String id);

	Page<Category> getPage(Map<String,Object> filterMap, Page<Category> page, int pageNo, int pageSize);
	
	int getCount(Map<String, Object> filterMap);
	
	public void saveCategory(Category category);
	
	public void updateCategory(Category category);
	
	public void deleteById(String id);
	
	public String getMaxWbs(Map<String,Object> filterMap);

	public List<Category> getChildrenByWbs(Map<String,Object> filterMap);
	
	public String getByWbs(String wbs);
}
