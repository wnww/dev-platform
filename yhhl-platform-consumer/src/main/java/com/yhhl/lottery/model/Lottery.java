package com.yhhl.lottery.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yhhl.common.CustomDateTimeSerializer;
/**
 * 
 * <br>
 * <b>功能：</b>LotteryEntity<br>
 * <b>作者：</b>www.yhsoft.top<br>
 * <b>日期：</b> June 15, 2015 <br>
 * <b>版权所有：<b>版权所有(C) 2013，瀛海互联<br>
 */
public class Lottery {
	
		private String expectId;//   存期号	private String codes;//   全部中奖号码	private int code1;//   	private int code2;//   	private int code3;//   	private int code4;//   	private int code5;//   	private int code1Lost;//   	private int code2Lost;//   	private int code3Lost;//   	private int code4Lost;//   	private int code5Lost;//   	private int oddCount;//   	private int bigCount;//   	private int primeCount;//   	private Date openTime;//   	private long openTimestamp;//   	private int space1;//   	private int space2;//   	private int space3;//   	private String space4;//   	private String space5;//   	public String getExpectId() {	    return this.expectId;	}	public void setExpectId(String expectId) {	    this.expectId=expectId;	}	public String getCodes() {	    return this.codes;	}	public void setCodes(String codes) {	    this.codes=codes;	}	public int getCode1() {	    return this.code1;	}	public void setCode1(int code1) {	    this.code1=code1;	}	public int getCode2() {	    return this.code2;	}	public void setCode2(int code2) {	    this.code2=code2;	}	public int getCode3() {	    return this.code3;	}	public void setCode3(int code3) {	    this.code3=code3;	}	public int getCode4() {	    return this.code4;	}	public void setCode4(int code4) {	    this.code4=code4;	}	public int getCode5() {	    return this.code5;	}	public void setCode5(int code5) {	    this.code5=code5;	}	public int getCode1Lost() {	    return this.code1Lost;	}	public void setCode1Lost(int code1Lost) {	    this.code1Lost=code1Lost;	}	public int getCode2Lost() {	    return this.code2Lost;	}	public void setCode2Lost(int code2Lost) {	    this.code2Lost=code2Lost;	}	public int getCode3Lost() {	    return this.code3Lost;	}	public void setCode3Lost(int code3Lost) {	    this.code3Lost=code3Lost;	}	public int getCode4Lost() {	    return this.code4Lost;	}	public void setCode4Lost(int code4Lost) {	    this.code4Lost=code4Lost;	}	public int getCode5Lost() {	    return this.code5Lost;	}	public void setCode5Lost(int code5Lost) {	    this.code5Lost=code5Lost;	}	public int getOddCount() {	    return this.oddCount;	}	public void setOddCount(int oddCount) {	    this.oddCount=oddCount;	}	public int getBigCount() {	    return this.bigCount;	}	public void setBigCount(int bigCount) {	    this.bigCount=bigCount;	}	public int getPrimeCount() {	    return this.primeCount;	}	public void setPrimeCount(int primeCount) {	    this.primeCount=primeCount;	}	@JsonSerialize(using = CustomDateTimeSerializer.class)	public Date getOpenTime() {	    return this.openTime;	}	public void setOpenTime(Date openTime) {	    this.openTime=openTime;	}	public long getOpenTimestamp() {	    return this.openTimestamp;	}	public void setOpenTimestamp(long openTimestamp) {	    this.openTimestamp=openTimestamp;	}	public int getSpace1() {	    return this.space1;	}	public void setSpace1(int space1) {	    this.space1=space1;	}	public int getSpace2() {	    return this.space2;	}	public void setSpace2(int space2) {	    this.space2=space2;	}	public int getSpace3() {	    return this.space3;	}	public void setSpace3(int space3) {	    this.space3=space3;	}	public String getSpace4() {	    return this.space4;	}	public void setSpace4(String space4) {	    this.space4=space4;	}	public String getSpace5() {	    return this.space5;	}	public void setSpace5(String space5) {	    this.space5=space5;	}
	
	@Override
	public int hashCode() {
		Lottery lty = (Lottery) this;
		System.out.println("Hash的原内容：" + lty.getExpectId());
		return expectId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Lottery) {
			Lottery lty = (Lottery) obj;
            System.out.println("equal"+ lty.getExpectId());
            return (expectId.equals(lty.getExpectId()));
        }
        return super.equals(obj);
	}
	
	
}

