package com.yhhl.common;

import java.util.Random; 

/**
 * 随机数、随即字符串工具类
 * @author hujinhai
 */
public class RandomUtils { 
        public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        public static final String numberChar = "0123456789"; 

        public static Random r = new Random();
        
        public static String getRandom(int len){
        	long num = Math.abs(r.nextInt() % System.currentTimeMillis());
            String s = String.valueOf(num);
            for(int i = 0; i < len-s.length(); i++){
                s = "0" + s;
            }
            if(s.startsWith("0")){
            	 return getRandom(len);
            }
            
            return s;
        }
        
        public static String buildPassword(){
        	Random rd = new Random();
        	  String n="";
        	  int getNum;
        	  do {
        	   getNum = Math.abs(rd.nextInt())%10 + 48;//产生数字0-9的随机数
        	   //getNum = Math.abs(rd.nextInt())%26 + 97;//产生字母a-z的随机数
        	   char num1 = (char)getNum;
        	   String dn = Character.toString(num1);
        	   n += dn;
        	  } while (n.length()<6);
        	  return n;
        }
        
        /** 
         * 生成随即密码 
         * @param pwd_len 生成的密码的总长度 
         * @return  密码的字符串 
         */  
        public static String genRandomNum(int pwd_len){  
         //35是因为数组是从0开始的，26个字母+10个数字  
         final int  maxNum = 36;  
         int i;  //生成的随机数  
         int count = 0; //生成的密码的长度  
         char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',  
           'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',  
           'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };  
           
         StringBuffer pwd = new StringBuffer("");  
         Random r = new Random();  
         while(count < pwd_len){  
          //生成随机数，取绝对值，防止生成负数，  
            
          i = Math.abs(r.nextInt(maxNum));  //生成的数最大为36-1  
            
          if (i >= 0 && i < str.length) {  
           pwd.append(str[i]);  
           count ++;  
          }  
         }  
           
         return pwd.toString();  
        }  
        
        /** 
         * 返回一个定长的随机字符串(只包含大小写字母、数字) 
         * 
         * @param length 随机字符串长度 
         * @return 随机字符串 
         */ 
        public static String generateString(int length) { 
                StringBuffer sb = new StringBuffer(); 
                Random random = new Random(); 
                for (int i = 0; i < length; i++) { 
                        sb.append(allChar.charAt(random.nextInt(allChar.length()))); 
                } 
                return sb.toString(); 
        } 

        /** 
         * 返回一个定长的随机纯字母字符串(只包含大小写字母) 
         * 
         * @param length 随机字符串长度 
         * @return 随机字符串 
         */ 
        public static String generateMixString(int length) { 
                StringBuffer sb = new StringBuffer(); 
                Random random = new Random(); 
                for (int i = 0; i < length; i++) { 
                        sb.append(allChar.charAt(random.nextInt(letterChar.length()))); 
                } 
                return sb.toString(); 
        } 

        /** 
         * 返回一个定长的随机纯大写字母字符串(只包含大小写字母) 
         * 
         * @param length 随机字符串长度 
         * @return 随机字符串 
         */ 
        public static String generateLowerString(int length) { 
                return generateMixString(length).toLowerCase(); 
        } 

        /** 
         * 返回一个定长的随机纯大写字母字符串(只包含大小写字母) 
         * 
         * @param length 随机字符串长度 
         * @return 随机字符串 
         */ 
        public static String generateUpperString(int length) { 
                return generateMixString(length).toUpperCase(); 
        } 

        /** 
         * 生成一个定长的纯0字符串 
         * 
         * @param length 字符串长度 
         * @return 纯0字符串 
         */ 
        public static String generateZeroString(int length) { 
                StringBuffer sb = new StringBuffer(); 
                for (int i = 0; i < length; i++) { 
                        sb.append('0'); 
                } 
                return sb.toString(); 
        } 

        /** 
         * 根据数字生成一个定长的字符串，长度不够前面补0 
         * 
         * @param num             数字 
         * @param fixdlenth 字符串长度 
         * @return 定长的字符串 
         */ 
        public static String toFixdLengthString(long num, int fixdlenth) { 
                StringBuffer sb = new StringBuffer(); 
                String strNum = String.valueOf(num); 
                if (fixdlenth - strNum.length() >= 0) { 
                        sb.append(generateZeroString(fixdlenth - strNum.length())); 
                } else { 
                        throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！"); 
                } 
                sb.append(strNum); 
                return sb.toString(); 
        } 

        /** 
         * 根据数字生成一个定长的字符串，长度不够前面补0 
         * 
         * @param num             数字 
         * @param fixdlenth 字符串长度 
         * @return 定长的字符串 
         */ 
        public static String toFixdLengthString(int num, int fixdlenth) { 
                StringBuffer sb = new StringBuffer(); 
                String strNum = String.valueOf(num); 
                if (fixdlenth - strNum.length() >= 0) { 
                        sb.append(generateZeroString(fixdlenth - strNum.length())); 
                } else { 
                        throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！"); 
                } 
                sb.append(strNum); 
                return sb.toString(); 
        } 
        
        
        /**
    	 * 生成固定长度的随机字符
    	 * @param len
    	 * @return
    	 */
    	public static String generateRandomChar(Integer len) {
    		// 生成字母A-Z,a-z 之间的随机字符

    		StringBuffer sb = new StringBuffer();
    		for (Integer i = 0; i < len; i++) {
    			int intRand = (int) (Math.random() * 52);
    			char base = (intRand < 26) ? 'A' : 'a';
    			char c = (char) (base + intRand % 26);
    			sb.append(c);
    		}
    		return sb.toString();
    	}
    	/**
    	 * 生成固定长度的随机字符和数字
    	 * @param len
    	 * @return
    	 */
    	public static String generateRandomCharAndNumber(Integer len) {
    		StringBuffer sb = new StringBuffer();
    		for (Integer i = 0; i < len; i++) {
    			int intRand = (int) (Math.random() * 52);
    			int numValue = (int) (Math.random() * 10);
    			char base = (intRand < 26) ? 'A' : 'a';
    			char c = (char) (base + intRand % 26);
    			if(numValue%2==0){
    				sb.append(c);
    			}else{
    				sb.append(numValue);
    			}
    		}
    		return sb.toString();
    	}

    	public static String getCharAndNumr(Integer length) {
    		StringBuffer sb = new StringBuffer();
    		Random random = new Random();
    		for (int i = 0; i < length; i++) {
    			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
    			if ("char".equalsIgnoreCase(charOrNum)){// 字符串
    				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
    				sb.append((char) (choice + random.nextInt(26)));
    			} else if ("num".equalsIgnoreCase(charOrNum)){// 数字
    				sb.append(String.valueOf(random.nextInt(10)));
    			}
    		}
    		return sb.toString();
    	}

        public static void main(String[] args) { 
        	
                //System.out.println(generateString(15)); 
                //System.out.println(generateMixString(15)); 
                System.out.println(generateLowerString(15)); 
                System.out.println(generateUpperString(15)); 
                //System.out.println(generateZeroString(15)); 
                //System.out.println(toFixdLengthString(123, 15)); 
                //System.out.println(toFixdLengthString(123L, 15)); 
                //
                //System.out.println(generateRandomChar(15));
                //System.out.println(generateRandomCharAndNumber(15));
                //System.out.println(getCharAndNumr(15));
                
                System.out.println(getRandom(2));
        		
        } 
}