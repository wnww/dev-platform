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
import com.yhhl.common.LoginUser;
import com.yhhl.common.ResultBean;
import com.yhhl.common.SpringWebUtil;

/**
 * 判断是否登录拦截器
 * @author 胡金海
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private final static Logger log= Logger.getLogger(LoginInterceptor.class);
	public static List dataList = new ArrayList();
	
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	LoginUser loginUser = SpringWebUtil.getLoginUser();
    	
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
						this.ajaxNoLoginBuild(response,ResultBean.NO_LOGIN,"未登录，请先登录");
					}
					return false;
					// 后台登录判断
				}else if(loginCheck.backMustLogin().equals(Constants.TRUE)){
					if(loginUser!=null){
			    		if(loginUser.getUserRole().indexOf("前台普通用户")>=0){
			    			if(method.getReturnType().isAssignableFrom(ModelAndView.class)){
				    			log.error(">>>前台用户不能访问后台资源，重新定位到前台首页<<<");
								response.sendRedirect(request.getContextPath()+"/index.do?flag=2");
								return false;
			    			}else{
			    				this.ajaxNoLoginBuild(response, ResultBean.NO_PERMISSION,"无权访问！");
			    				return false;
			    			}
			    		}else{
			    			return true;
			    		}
					}
					if(method.getReturnType().isAssignableFrom(ModelAndView.class)){
						log.error(">>>未登录，请先登录<<<");
						response.sendRedirect(request.getContextPath()+"/sysManage/index.do");
					}else{
						this.ajaxNoLoginBuild(response,ResultBean.NO_LOGIN,"未登录，请先登录");
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
	private void ajaxNoLoginBuild(HttpServletResponse response,int flag,String msg) throws IOException{
    	response.setContentType("text/html; charset=UTF-8");
		log.error(">>>"+msg+"<<<");
		ResultBean<String> rb = new ResultBean<String>();
		rb.setFlag(flag);
		rb.setMsg(msg);
		rb.setRows(dataList);
		response.getWriter().write(JSONObject.toJSON(rb).toString());
    }

}