package com.yhhl.product.model;

/**
 * 
 * <br>
 * <b>功能：</b>ProductsEntity<br>
 * <b>作者：</b>www.cbice.com<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2013，瀛海科技<br>
 */
public class Products {
	
		private java.lang.String prodId;//   	private java.lang.String prodCode;//   	private java.lang.String prodName;//   	private int unitPriceCost;//   进货单价
	private long expressFee; // 进货快递费，单件分摊	private int unitPriceSell;//   	private java.lang.String imgUrl;//   	private java.lang.String remark;//   	private java.lang.String brands;// 品牌	private java.lang.String unit;// 单位	private java.lang.String specification;// 规格	private java.lang.String properties;// 属性
	private String colors;	private java.lang.String mark;// 标签 	private java.lang.String type;// 类型	private String stockSituation;// 库存数量
	private String sellNum; // 已售数量	private long createTime;//  创建时间	private long modifyTime;//  修改时间	public java.lang.String getProdId() {	    return this.prodId;	}	public void setProdId(java.lang.String prodId) {	    this.prodId=prodId;	}	public java.lang.String getProdCode() {	    return this.prodCode;	}	public void setProdCode(java.lang.String prodCode) {	    this.prodCode=prodCode;	}	public java.lang.String getProdName() {	    return this.prodName;	}	public void setProdName(java.lang.String prodName) {	    this.prodName=prodName;	}	public int getUnitPriceCost() {	    return this.unitPriceCost;	}	public void setUnitPriceCost(int unitPriceCost) {	    this.unitPriceCost=unitPriceCost;	}	public int getUnitPriceSell() {	    return this.unitPriceSell;	}	public void setUnitPriceSell(int unitPriceSell) {	    this.unitPriceSell=unitPriceSell;	}	public java.lang.String getImgUrl() {	    return this.imgUrl;	}	public void setImgUrl(java.lang.String imgUrl) {	    this.imgUrl=imgUrl;	}	public java.lang.String getRemark() {	    return this.remark;	}	public void setRemark(java.lang.String remark) {	    this.remark=remark;	}	public java.lang.String getBrands() {	    return this.brands;	}	public void setBrands(java.lang.String brands) {	    this.brands=brands;	}	public java.lang.String getUnit() {	    return this.unit;	}	public void setUnit(java.lang.String unit) {	    this.unit=unit;	}	public java.lang.String getSpecification() {	    return this.specification;	}	public void setSpecification(java.lang.String specification) {	    this.specification=specification;	}	public java.lang.String getProperties() {	    return this.properties;	}	public void setProperties(java.lang.String properties) {	    this.properties=properties;	}	public java.lang.String getMark() {	    return this.mark;	}	public void setMark(java.lang.String mark) {	    this.mark=mark;	}	public java.lang.String getType() {	    return this.type;	}	public void setType(java.lang.String type) {	    this.type=type;	}
	
	public String getStockSituation() {
		return stockSituation;
	}
	public void setStockSituation(String stockSituation) {
		this.stockSituation = stockSituation;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getColors() {
		return colors;
	}
	public void setColors(String colors) {
		this.colors = colors;
	}
	public String getSellNum() {
		return sellNum;
	}
	public void setSellNum(String sellNum) {
		this.sellNum = sellNum;
	}
	public long getExpressFee() {
		return expressFee;
	}
	public void setExpressFee(long expressFee) {
		this.expressFee = expressFee;
	}
	
}

