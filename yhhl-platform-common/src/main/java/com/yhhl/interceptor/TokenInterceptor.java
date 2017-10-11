package com.yhhl.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.yhhl.InitServlet;
import com.yhhl.common.ResultBean;
import com.yhhl.common.StringUtil;

/**
 * 重复提交拦截器
 * @author 胡金海
 *
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (handler instanceof HandlerMethod) {//方法级拦截
			Method method = ((HandlerMethod) handler).getMethod();
			Token annotation = method.getAnnotation(Token.class);
			if (annotation != null) {
				boolean needSaveSession = annotation.save();
				if (needSaveSession) {//加入token
					HttpSession session = request.getSession(true);
					if (null != session)
						session.setAttribute("token", UUID.randomUUID().toString());
				}
				boolean needRemoveSession = annotation.remove();
				if (needRemoveSession) {//移出token
					if (isRepeatSubmit(request)) {
						response.setContentType("text/html; charset=UTF-8");
						ResultBean<String> rb = new ResultBean<String>();
						rb.setFlag(ResultBean.REPEAT);
						rb.setMsg("请刷新页面再提交！");
						rb.setRows(InitServlet.dataList);
						response.getWriter().write(JSONObject.toJSON(rb).toString());
						return false;
					}
					request.getSession(false).removeAttribute("token");
				}
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

	/**
	 * 判断是否重复提交
	 */
	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = 
				String.valueOf(request.getSession().getAttribute("token"));
		if (StringUtil.isEmpty(serverToken)) {
			return true;
		}
		String clinetToken = request.getParameter("token");
		if (StringUtil.isEmpty(clinetToken)) {
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}
}