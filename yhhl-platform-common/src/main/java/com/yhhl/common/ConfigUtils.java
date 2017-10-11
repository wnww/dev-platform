package com.yhhl.common;

import java.util.Properties;

public class ConfigUtils {

	private static Properties configPerties = new Properties();
	
	static {
		try {
			configPerties.load(ConfigUtils.class.getClassLoader().getResourceAsStream("sysconfig.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取字符串类型
	 * @param key
	 * @return
	 */
	public static String getString(String key){
		return (String) configPerties.get(key);
	}
	
	/**
	 * 数字类型
	 * 
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		try {
			String i = (String) configPerties.get(key);
			return Integer.valueOf(i);
		} catch (Exception e) {
			return 0;
		}
	}
}
