package com.yhhl.common;

import org.apache.commons.lang.ArrayUtils;

public class ElevenSelectFive {

	private final static int[] numbers = {1,2,3,4,5,6,7,8,9,10,11}; // 全部11个数字
	private final static int[] odd = {1,3,5,7,9,11}; // 奇数
	private final static int[] even = {2,4,6,8,10}; // 偶数
	private final static int[] prime = {1,2,3,5,7,11}; // 质数/素数
	private final static int[] composite = {4,6,8,9,10}; // 合数
	private final static int[] big = {6,7,8,9,10,11}; // 大数
	private final static int[] small = {1,2,3,4,5}; // 小数
	
	
	
	public static void main(String[] args) {
		int[] result = new int[5];
		int[] temp = new int[11];
		int i=0;
		for(int num : numbers){
			if(num%2!=0 && ArrayUtils.contains(big, num)){
				temp[i] = num;
				i++;
			}
		}
		
		for(int t : temp){
			System.out.print(t);
			System.out.print(",");
		}

	}
	
	public static int countOdd(String[] codeAry){
		int count = 0;
		for(int i=0; i< codeAry.length; i++){
			if(ArrayUtils.contains(odd, Integer.parseInt(codeAry[i]))){
				count++;
			}
		}
		return count;
	}
	
	public static int countBig(String[] codeAry){
		int count = 0;
		for(int i=0; i< codeAry.length; i++){
			if(ArrayUtils.contains(big, Integer.parseInt(codeAry[i]))){
				count++;
			}
		}
		return count;
	}
	
	public static int countPrime(String[] codeAry){
		int count = 0;
		for(int i=0; i< codeAry.length; i++){
			if(ArrayUtils.contains(prime, Integer.parseInt(codeAry[i]))){
				count++;
			}
		}
		return count;
	}

}
