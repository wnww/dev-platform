<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/mobilemeta.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<%@ include file="/common/mobileimport.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <title>${websiteTitle}</title>
    <script type="text/javascript">
    	
    </script>
  </head>
  <body>
    <div class="maincont">
     <div class="userName">
      <dl class="names">
       <dd>
        <h3>${loginUser.userName }</h3>
       </dd>
       <div class="clearfix"></div>
      </dl>
      <div class="shouyi">
       <dl>
        <dt>已完成订单</dt>
        <dd id="finishCount">0</dd>
       </dl>
       <dl>
        <dt>未完成订单</dt>
        <dd id="unFinishCount">0</dd>
       </dl>
       <div class="clearfix"></div>
      </div><!--shouyi/-->
     </div><!--userName/-->
     
     <ul class="userNav">
      <li><span class="glyphicon glyphicon-list-alt" style="color:#00ff00"></span><a href="${ctx}/orders/myOrder.do">我的订单</a></li>
      <li><span class="glyphicon glyphicon-star" style="color:#ff6600"></span><a href="${ctx}/collects/index.do">我的收藏</a></li>
      <li><span class="glyphicon glyphicon-map-marker" style="color:#0066ff"></span><a href="${ctx}/address/selectAddress.do">收货地址管理</a></li>
	 </ul><!--userNav/-->
     
     <div class="lrSub" style="margin-bottom: 10px">
       <a href="${ctx}/logout.do">退出登录</a>
     </div>
     
    <!--footNav-->
    	<%@ include file="/common/mobilefooter.jsp" %>
    <!--footNav/-->
    </div><!--maincont-->
  </body>
  <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/userCenter.js?v=1.0.0"></script>
</html>