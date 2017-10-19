package com;

import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class Testmain {

	public static void main(String[] args) {
		// String path = "F:\\aaa.html";
		// try {
		// FileReader fr = new FileReader(new File(path));
		// BufferedReader br = new BufferedReader(fr);
		// String str = "";
		// while((str = br.readLine())!=null){
		// str = str.substring(3, str.length());
		// System.out.println(str);
		// }
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// String str = "001-002-003";
		// String[] ary = str.split("-");
		// for(String a : ary){
		// System.out.println(a);
		// }

		// String str = "016";
		// System.out.println(Integer.parseInt(str));
		String str = "牛仔裤 男";
		Result result = ToAnalysis.parse(str);
		List<Term> terms = result.getTerms();
		for(Term tm : terms){
			System.out.println(tm.getName()+"|"+tm.getNatureStr()+"|"+tm.getRealName());
		}
	}

}
