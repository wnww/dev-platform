package com.yhhl.book.service;

import java.util.List;
import java.util.Map;

import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.yhhl.common.SearchPageUtil;
import com.yhhl.core.Page;
import com.yhhl.book.dao.TBookMapper;
import com.yhhl.book.model.TBook;

/**
 * 
 * <br>
 * <b>功能：</b>TBookServiceImpl<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 2, 2013 <br>
 * <b>版权所有：<b>版权所有(C) 2015 国版中心<br>
 */
@Service("tBookService")
public class TBookServiceImpl implements TBookServiceI {

	@Autowired
	private TBookMapper tBookMapper;

	/**
	 * 保存
	 */
	@Compensable(confirmMethod = "confirmSaveTBook", cancelMethod = "cancelSaveTBook")
	@Transactional
	public void saveTBook(TransactionContext context,TBook tBook) {
		String globalTxId = UUID.nameUUIDFromBytes(context.getXid().getGlobalTransactionId()).toString();
		tBook.setId(globalTxId);
		tBook.setTransactionXid(globalTxId);
		tBookMapper.insert(tBook);
	}

	@Transactional
	public void confirmSaveTBook(TransactionContext context, TBook tBook) {
		TBook book = tBookMapper.selectByPrimaryKey(tBook.getId());
		book.setBookName(book.getBookName()+"-确认操作");
		tBookMapper.updateByPrimaryKey(book);
	}

	@Transactional
	public void cancelSaveTBook(TransactionContext context, TBook tBook) {
		TBook book = tBookMapper.selectByPrimaryKey(tBook.getId());
		book.setBookName(book.getBookName()+"-取消操作");
		tBookMapper.updateByPrimaryKey(book);
	}

	/**
	 * 分页查询
	 */
	public Page<TBook> getPage(Map<String, Object> filterMap, Page<TBook> page,
			int pageNo, int pageSize) {
		int count = tBookMapper.getCount(filterMap);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		SearchPageUtil searchPageUtil = new SearchPageUtil();
		String order[] = { "", "" };// 排序字段，可以是多个 类似：{ "name  desc", "id asc" };
		searchPageUtil.setOrderBys(order);
		searchPageUtil.setPage(page);
		searchPageUtil.setObject(filterMap);
		List<TBook> list = tBookMapper.getPage(searchPageUtil);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * 分页查询的count
	 */
	public int getCount(Map<String, Object> filterMap) {
		return tBookMapper.getCount(filterMap);
	}

	/**
	 * 更新
	 */
	public void updateTBook(TBook tBook) {
		tBookMapper.updateByPrimaryKey(tBook);
	}

	/**
	 * 根据ID获取实体对象
	 */
	public TBook getById(String id) {
		return tBookMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除信息
	 */
	public void deleteById(String id) {
		tBookMapper.deleteByPrimaryKey(id);
	}

}
