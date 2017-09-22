package com.yhhl.common;

/**
 * 结果返回类
 * @author goldocean
 *
 * @param <T>
 */
public class ResultBean<T> implements java.io.Serializable {

	private static final long serialVersionUID = -6563244825405442373L;

	public static final int SUCCESS = 1;

	public static final int FAIL = 0;

	public static final int NO_PERMISSION = 2;

	private String msg = "success";

	private int flag = SUCCESS;

	private T data;

	public ResultBean() {
		super();
	}

	public ResultBean(T data) {
		super();
		this.data = data;
	}

	public ResultBean(Throwable e) {
		super();
		this.msg = e.toString();
		this.flag = FAIL;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	
}
