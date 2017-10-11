package com.yhhl.common;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		for(int i=0; i<10; i++){
			list.add("\""+new IdWorker(1, 1).nextId()+"\"");
		}
		
		String str = list.toString().replaceAll("\\[", "").replaceAll("\\]", "");
		System.out.println(str);
		
	}

}
