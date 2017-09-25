package com.yhhl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.yhhl.common.ConfigUtils;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
        // 前台手机页面静态路径
    	application.setAttribute("frontMobileStaticCtx", application.getContextPath()+"/"+ConfigUtils.getString("frontMobileStaticCtx"));
    	// 前台PC页面静态路径
    	application.setAttribute("frontPcStaticCtx", application.getContextPath()+"/"+ConfigUtils.getString("frontPcStaticCtx"));
	}

}
