package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Testmain {

	public static void main(String[] args) {
		String path = "F:\\aaa.html";
		try {
			FileReader fr = new FileReader(new File(path));
			BufferedReader br = new BufferedReader(fr);
			String str = "";
			while((str = br.readLine())!=null){
				str = str.substring(3, str.length());
				System.out.println(str);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
