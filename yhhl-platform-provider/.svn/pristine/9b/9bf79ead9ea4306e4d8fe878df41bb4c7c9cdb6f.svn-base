package com.yhhl.webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.yhhl.books.model.Books;
import com.yhhl.books.service.BooksServiceI;
import com.yhhl.dubbo.service.WsBookServiceI;
import com.yhhl.wsts.common.TransactionContextMain;
import com.yhhl.wsts.common.dto.MapTransactionDTO;
import com.yhhl.wsts.server.service.TransactionServiceSupport;
import com.yhhl.wsts.server.utils.SpringContextUtils;

@Service("wsBookService")
public class WsBookService implements WsBookServiceI {

	@Autowired
	private BooksServiceI booksService;
	
	private TransactionServiceSupport transactionServiceSupport;
	
//	public BooksServiceI getBooksService() {
//		return (BooksServiceI) SpringContextUtils.getBean("booksService");
//	}
	
	// 注入
	@Autowired
	public void setTransactionServiceSupport(TransactionServiceSupport transactionServiceSupport) {
		this.transactionServiceSupport = transactionServiceSupport;
	}

	@SuppressWarnings("rawtypes")
	public MapTransactionDTO addBooks(TransactionContextMain contextMain, final Books books) {
		return (MapTransactionDTO) transactionServiceSupport.doInWebServiceTransaction(contextMain, new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				booksService.saveBooks(books);
				return null;
			}

		}, MapTransactionDTO.class);
	}
}
