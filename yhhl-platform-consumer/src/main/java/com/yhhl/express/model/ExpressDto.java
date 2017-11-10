package com.yhhl.express.model;

import java.util.List;

public class ExpressDto {

	private String success;
	private String reason;
	private int status;
	private List<Data> data;
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
	
}
