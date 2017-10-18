package com.yhhl.category.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.category.dao.CategoryMapper;
import com.yhhl.category.model.Category;
import com.yhhl.common.IdWorker;

/**
 * 
 * <br>
 * <b>功能：</b>CategoryServiceImpl<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 瀛海互联<br>
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryServiceI {

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 根据parent查询最大的wbs
	 */
	@Override
	public String getMaxWbs(Map<String, Object> filterMap) {
		return categoryMapper.getMaxWbs(filterMap);
	}

	/**
	 * 保存
	 */
	@Override
	public void saveCategory(Category category) {
		category.setCatId(idWorker.buildId());
		if(!category.getParentId().equals("0")){
			Category parent = categoryMapper.selectByPrimaryKey(category.getParentId());
			parent.setSubCount(parent.getSubCount()+1);
			categoryMapper.updateByPrimaryKey(parent);
		}
		categoryMapper.insert(category);
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<Category> getPage(Map<String, Object> filterMap, Page<Category> page, int pageNo, int pageSize) {
		if(pageSize!=0){
			int count = categoryMapper.getCount(filterMap);
			page.setTotalCount(count);
		}
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "wbs asc" };// 排序字段，可以是多个 类似：{ "name desc", "id asc"
										// };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<Category> list = categoryMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	 *
	 * 分页查询的count
	 */
	@Override
	public int getCount(Map<String, Object> filterMap) {
		return categoryMapper.getCount(filterMap);
	}

	/**
	 * 更新
	 */
	@Override
	public void updateCategory(Category category) {
		categoryMapper.updateByPrimaryKey(category);
	}

	/**
	 * 根据ID获取实体对象
	 */
	@Override
	public Category getById(String id) {
		return categoryMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	@Override
	public void deleteById(String id) {
		Category category = categoryMapper.selectByPrimaryKey(id);
		if(category==null){
			return;
		}
		Category parent = categoryMapper.selectByPrimaryKey(category.getParentId());
		if(parent!=null){
			if(parent.getSubCount()>0){
				parent.setSubCount(parent.getSubCount()-1);
			}
			categoryMapper.updateByPrimaryKey(parent);
		}
		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Category> getChildrenByWbs(Map<String, Object> filterMap) {
		return categoryMapper.getChildrenByWbs(filterMap);
	}

	@Override
	public String getByWbs(String wbs) {
		return categoryMapper.getByWbs(wbs);
	}

	
}
