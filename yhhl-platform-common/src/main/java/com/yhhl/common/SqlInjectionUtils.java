/****
 * @Title: SqlInjectionUtils.java
 * @Description:  
 * @author xiongxzh  
 * @date 2016年5月18日下午2:19:22
 * @version V1.0    
 ***/
package com.yhhl.common;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/****
 * @Title: SqlInjectionUtils.java
 * @Description:  
 * @author xiongxzh  
 * @date 2016年5月18日下午2:19:22
 * @version V1.0    
 ***/
public class SqlInjectionUtils {
	
	private static final String sqlInjectionReg="(exec|and|delete|insert|select|union|update|chr|mid|master|truncate|char|declare)(\\s+|\\++)";
	
	/****
	 * 
	 */
	private static final Logger LOG = LogManager
			.getLogger(SqlInjectionUtils.class);
	
	private final static Pattern pattern=Pattern.compile(sqlInjectionReg,java.util.regex.Pattern.CASE_INSENSITIVE);
	
	/****
	 * 
	 * @param value
	 * @return
	 */
	public static boolean sqlInjectionParametersCheck(final HttpServletRequest request) throws Exception
	{
		if(request==null)
		{
			return true;
		}
		Enumeration<String> enumeration= request.getParameterNames();
		Map map=request.getParameterMap();
		Set<Object> set=map.keySet();
		Iterator ir=set.iterator();
		while (ir.hasNext()) {
			String key=(String)ir.next(); 
			String value=request.getParameter(key).toString(); 
			java.util.regex.Matcher matcher = pattern.matcher(value);
			if(matcher.find())
			{
				LOG.error(request.getRequestURI().toString()+"Param:"+key+"Value:"+value+"参数含有非法字符");
				throw new Exception("parameter "+key+"存在非法的请求参数");
			}
		} 
		
		return true;
	}
	
	/****
	 * 
	 * @param value
	 * @return
	 */
	public static boolean sqlInjectionParametersCheck(final HttpServletRequest request,String isExcludeInjectionFields) throws Exception
	{
		if(request==null)
		{
			return true;
		}
		Enumeration<String> enumeration= request.getParameterNames();
		Map map=request.getParameterMap();
		Set<Object> set=map.keySet();
		Iterator ir=set.iterator();
		if(Constants.FALSE.equals(isExcludeInjectionFields)){
			return true;
		}
		while (ir.hasNext()) {
			String key=(String)ir.next(); 
			String value=request.getParameter(key).toString(); 
			java.util.regex.Matcher matcher = pattern.matcher(value);
			if(matcher.find())
			{
				LOG.error(request.getRequestURI().toString()+"Param:"+key+"Value:"+value+"参数含有非法字符");
				throw new Exception("parameter "+key+"存在非法的请求参数");
			}
		} 
		
		return true;
	}
	
	/****
	 * 
	 * @param value
	 * @return
	 */
	public static boolean sqlInjectionParametersCheck(final HttpServletRequest request,String isExcludeInjectionFields,String[] excludeFields) throws Exception
	{
		if(request==null)
		{
			return true;
		}
		List<String> exFields=new ArrayList<>();
		if(excludeFields!=null && excludeFields.length>0)
		{
			for (String field : excludeFields) {
				if(!exFields.contains(field.toLowerCase()))
				{
					exFields.add(field.toLowerCase());
				}
			}
		}
		Enumeration<String> enumeration= request.getParameterNames();
		Map map=request.getParameterMap();
		Set<Object> set=map.keySet();
		Iterator ir=set.iterator();
		if(Constants.FALSE.equals(isExcludeInjectionFields)){
			return true;
		}
		while (ir.hasNext()) {
			String key=(String)ir.next(); 
			if(exFields.contains(key.toLowerCase()))
			{
				continue;
			}
			String value=request.getParameter(key).toString(); 
			java.util.regex.Matcher matcher = pattern.matcher(value);
			if(matcher.find())
			{
				LOG.error(request.getRequestURI().toString()+"Param:"+key+"Value:"+value+"参数含有非法字符");
				throw new Exception("parameter "+key+"存在非法的请求参数");
			}
		} 
		
		return true;
	}
	/****
	 * 
	 * @param value
	 * @return
	 */
	public static boolean sqlInjectionParametersCheck(final Map<String, Object> parameters) throws Exception
	{
		if(parameters==null || parameters.size()==0)
		{
			return true;
		} 
		for (String  key : parameters.keySet()) {
			Object value=parameters.get(key);
			if(value==null|| key.equalsIgnoreCase("querystr")) //临时处理，忽略querystr 参数
			{
				continue;
			}
			String val=String.valueOf(value);
			java.util.regex.Matcher matcher = pattern.matcher(val);
			if(matcher.find())
			{
				throw new Exception("parameter "+key+"存在非法的请求参数");
			}
		} 
		return true;
	}
}
