package com.yhhl.charts.model;

public class Series {

	private String name;
	private String type;
	private long[] data;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long[] getData() {
		return data;
	}
	public void setData(long[] data) {
		this.data = data;
	}
}
