package com.yhhl.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.yhhl.common.Constants;
import com.yhhl.common.ResultBean;

/**
 * 判断是否登录拦截器
 * @author 胡金海
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private final static Logger log= Logger.getLogger(LoginInterceptor.class);
	public static List dataList = new ArrayList();
	
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	Object obj = request.getSession().getAttribute("loginUser");
    	if(obj!=null){ // 如果已经登录，直接返回True
			return true;
		}
		if (handler instanceof HandlerMethod) {//方法级拦截
			Method method = ((HandlerMethod) handler).getMethod();
			LoginCheck loginCheck = method.getAnnotation(LoginCheck.class);
			if (loginCheck != null) {
				// 前台登录判断
				if(loginCheck.frontMustLogin().equals(Constants.TRUE)){
					if(method.getReturnType().isAssignableFrom(ModelAndView.class)){
						log.error(">>>未登录，请先登录<<<");
						response.sendRedirect(request.getContextPath()+"/login.do");
					}else{
						this.ajaxNoLoginBuild(response);
					}
					return false;
					// 后台登录判断
				}else if(loginCheck.backMustLogin().equals(Constants.TRUE)){
					if(method.getReturnType().isAssignableFrom(ModelAndView.class)){
						log.error(">>>未登录，请先登录<<<");
						response.sendRedirect(request.getContextPath()+"/sysManage/index.do");
					}else{
						this.ajaxNoLoginBuild(response);
					}
					return false;
				}else{
					return true;
				}
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void ajaxNoLoginBuild(HttpServletResponse response) throws IOException{
    	response.setContentType("text/html; charset=UTF-8");
		log.error(">>>未登录，请先登录<<<");
		ResultBean<String> rb = new ResultBean<String>();
		rb.setFlag(ResultBean.NO_LOGIN);
		rb.setMsg("未登录，请先登录！");
		rb.setRows(dataList);
		response.getWriter().write(JSONObject.toJSON(rb).toString());
    }

}