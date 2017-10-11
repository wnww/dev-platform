/**
  * <br>JAVACC DEMO 1.0<br>
  * @copy right zbx company All rights reserved.<br>
  * <br>
  * @Package com.yhhl.platform.internationalization
*/
package com.yhhl.common;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
  * description: 获取国际化信息
  *             使用：在jsp页面中的使用引入spring标签
  *                   <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
  *                   使用标签进行国际化<spring:message code="parentId"/>//parentId为配置文件中的key值
  *                  在类中 LocaleMessage.getMessage(request,"配置文件中的key值", 值参数, "默认值")
  * @author Hou DaYu 创建时间：上午11:47:07
  */
public class LocaleMessage {

	/**
	 * 获取国际化信息
	 * @param msgKey  key message_*.properties文件中的key
	 * @param params  key中{0}、{1}等对应的值
	 * @param defaultMessage 对应的默认值
	 * @return 返回对应的国际化信息
	 */
	public static String getMessage(HttpServletRequest request, String msgKey, String[] params, String defaultMessage) {
		WebApplicationContext requestContext = RequestContextUtils.getWebApplicationContext(request);
		Locale myLocale = RequestContextUtils.getLocale(request);
		String msg = requestContext.getMessage(msgKey, params, defaultMessage, myLocale);
		return StringUtil.defaultString(msg, defaultMessage);
	}

}
