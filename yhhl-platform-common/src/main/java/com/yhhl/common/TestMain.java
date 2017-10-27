package com.yhhl.common;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

	public static void main(String[] args) {
//		List<String> list = new ArrayList<String>();
//		for(int i=0; i<10; i++){
//			list.add("\""+new IdWorker(1, 1).nextId()+"\"");
//		}
//		
//		String str = list.toString().replaceAll("\\[", "").replaceAll("\\]", "");
//		System.out.println(str);
		String prevfix="";
		
		String srcUrl = "/opt/tomcat9/webapps/ROOT/userfiles/imgFiles/20171024/b_2017102421191094193.jpg";
		String tmp = srcUrl.substring(srcUrl.indexOf("b_")+2,srcUrl.lastIndexOf("."));
		String temp = srcUrl.substring(0,srcUrl.lastIndexOf("b_"))+tmp+prevfix;
		String suffix = srcUrl.substring(srcUrl.lastIndexOf("."));
		System.out.println(temp+suffix);
	}

}
