package com.yhhl.common;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DateUtils {

	public static long getNowDateTime() {
		LocalDateTime dateTime = LocalDateTime.now();
		String t = dateTime.toString();
		t = t.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").replaceAll("T", "");
		t = t.substring(0, t.indexOf("."));
		return Long.valueOf(t);
	}

	public static long getTadyDate() {
		LocalDate today = LocalDate.now();
		return Long.valueOf(today.toString().replaceAll("-", ""));
	}
	
	public static String dateTime2YMDHMS(long dateTime){
		String dDate = "";
		String dateTimes = String.valueOf(dateTime);
		//dateTimes = dateTimes.substring(0,8);
		char[] dt = dateTimes.toCharArray();
		for(int i=0; i<dt.length; i++){
			dDate = dDate+dt[i];
			if((i+1)%2==0 && i>2 && i<7){
				dDate = dDate+"-";
			}else if((i+1)%2==0 && i>7 && i<dt.length-1){
				dDate = dDate+":";
			}else if(i==7){
				dDate = dDate+" ";
			}
		}
		return dDate;
	}
	
	public static String dateTime2YMD(long dateTime){
		String dt = dateTime2YMDHMS(dateTime);
		return dt.substring(0,dt.indexOf(" "));
	}
	
	public static String dateTime2Y(long dateTime){
		String dt = dateTime2YMD(dateTime);
		return dt.substring(0, 4);
	}
	
	public static String dateTime2M(long dateTime){
		String dt = dateTime2YMD(dateTime);
		return dt.substring(5, 7);
	}
	
	public static String dateTime2MD(long dateTime){
		String dt = dateTime2YMD(dateTime);
		return dt.substring(5);
	}
	
	public static String dateTime2D(long dateTime){
		String dt = dateTime2YMD(dateTime);
		return dt.substring(8);
	}
	
	public static Date parse(String strDate) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(strDate);
	}
	
	public static void main(String[] args) throws Exception{
//		long dateTime = 20170922181834l;
//		System.out.println(dateTime);
//		System.out.println(dateTime2YMDHMS(dateTime));
//		System.out.println(dateTime2YMD(dateTime));
//		System.out.println(dateTime2Y(dateTime));
//		System.out.println(dateTime2M(dateTime));
//		System.out.println(dateTime2MD(dateTime));
//		System.out.println(dateTime2D(dateTime));
		System.out.println(parse("2017-11-01 14:41:10"));
	}
}
