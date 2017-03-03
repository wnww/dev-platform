package com.yhhl.book.model;

/**
 * 
 * <br>
 * <b>功能：</b>TBookEntity<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2013，国版中心<br>
 */
public class TBook implements java.io.Serializable{
	
	private static final long serialVersionUID = 1969435447076482122L;	private java.lang.String id;//   	private java.lang.String bookName;//   	private java.lang.String transactionXid;//   	public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getBookName() {	    return this.bookName;	}	public void setBookName(java.lang.String bookName) {	    this.bookName=bookName;	}	public java.lang.String getTransactionXid() {	    return this.transactionXid;	}	public void setTransactionXid(java.lang.String transactionXid) {	    this.transactionXid=transactionXid;	}
}

