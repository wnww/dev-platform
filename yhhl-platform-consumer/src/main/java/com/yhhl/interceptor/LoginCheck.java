package com.yhhl.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yhhl.common.Constants;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginCheck {
	
	/**
	 * 前台方法是否需要登录判断
	 * @return
	 */
	public String frontMustLogin() default Constants.FALSE;
	
	/**
	 * 后台方法是否需要登录判断
	 * @return
	 */
	public String backMustLogin() default Constants.FALSE;
	
	
}
