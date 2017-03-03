package com.yhhl.common;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils {

	/**
	 * @Description:Object对象转String
	 * @param object
	 * @return String
	 * @throws
	 * @author lihao
	 */
	public static String objectToString(Object object) {
		String str = "";
		if (!isEmpty(object)) {
			str = String.valueOf(object);
		}
		return str;
	}

	/**
	 * @Description:判断Object是否为空
	 * @param object
	 * @return boolean
	 * @throws
	 * @author lihao
	 */
	public static boolean isEmpty(Object object) {
		if (object == null || object.equals("") || object.equals("null"))
			return true;
		return false;
	}

	/**
	 * @Description::判断字符串是否为空
	 * @param str
	 * @return boolean
	 * @throws
	 * @author lihao
	 */
	public static boolean isEmpty(String str) {
		return isEmpty((Object) str);
	}

	/**
	 * @Description:根据pattern把double数据转换成String
	 * @param d
	 * @param pattern
	 *            例：pattern = "0.000"，把double保留三位小位且四舍五入
	 * @return String
	 * @throws
	 * @author lihao
	 */
	public static String doubleToString(double d, String pattern) {
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(d);
	}

	/**
	 * 判断字符串是否是全数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args){
		System.out.println(isNumeric("11231231231313123"));
	}
}
