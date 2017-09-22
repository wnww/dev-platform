package com.yhhl.charts.model;

public class ChartsData {

	private String title;
	private String tooltip;
	private String[] xAxis;
	private String[] yAxis;

	private Series series;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String[] getxAxis() {
		return xAxis;
	}

	public void setxAxis(String[] xAxis) {
		this.xAxis = xAxis;
	}

	public String[] getyAxis() {
		return yAxis;
	}

	public void setyAxis(String[] yAxis) {
		this.yAxis = yAxis;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

}
