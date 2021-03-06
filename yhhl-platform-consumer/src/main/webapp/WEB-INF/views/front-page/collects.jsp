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
  </head>
  <body>
    <div class="maincont">
     <header>
      <a href="javascript:history.back(-1)" class="back-off fl"><span class="glyphicon glyphicon-menu-left"></span></a>
      <div class="head-mid">
       <h1>我的收藏</h1>
      </div>
     </header>
     
     <table class="shoucangtab">
      <tr>
       <td width="65%"><span class="hui">收藏栏共有：<strong class="orange" id="showCollectCount">0</strong>件商品</span></td>
       <td width="35%" align="center" style="background:#fff url(${frontMobileStaticCtx}/images/xian.jpg) left center no-repeat;"><a href="javascript:void(0);" onclick="deleteCollect('');" class="orange">全部删除</a></td>
      </tr>
     </table>
     
     <div id="prodList">
     
     </div>
     <!--dingdanlist/--> 

     <div id='ajax_loading' style='height:30px;width:100%;text-align:center;line-height:30px;font-size:12px;display:none;padding-bottom: 20px;'><img src="${frontMobileStaticCtx}/images/loading.gif" style="width:25px;"/> 数据加载中...</div>
     <!--footNav-->
    	<%@ include file="/common/mobilefooter.jsp" %>
     <!--footNav/-->
     
    </div><!--maincont-->
  </body>
  <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/collects.js?v=1.0.0"></script>
</html>