<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/mobilemeta.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<%@ include file="/common/mobileimport.jsp" %>
<link href="${frontMobileStaticCtx}/css/filtrate.css" rel="stylesheet" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <title>${websiteTitle}</title>
  </head>
  <body>
    <div class="maincont">
     <header style="position: fixed;top:0px;">
      <a href="javascript:history.back(-1)" class="back-off fl"><span class="glyphicon glyphicon-menu-left"></span></a>
      <div class="head-mid">
       	<form action="#" method="get" class="search">
		      <input type="text" class="seaText fl" />
		      <input type="submit" value="搜索" class="seaSub fr" />
		</form><!--search/-->
      </div>
     </header>
     <section class="job-module" style="position: fixed;top:40px;height:400px;width:100%;overflow-y:auto;"> 
		<dl class="retrie" > 
			<dt>
				<a id="area" href="javascript:;">分类 </a>
				<a id="wage" href="javascript:;">排序</a>
			</dt> 
			<dd class="area"> 
				<ul class="slide downlist" id="category"> 
					
				</ul> 
			</dd> 
			<dd class="wage"> 
				<ul class="slide downlist" id="prodOrder"> 
					<li><a href="javascript:void(0);" onclick="prodOrder('1');">销量由高到低</a></li> 
					<li><a href="javascript:void(0);" onclick="prodOrder('2');">价格从高到低</a></li> 
					<li><a href="javascript:void(0);" onclick="prodOrder('3');">价格从低到高</a></li>
					<li><a href="javascript:void(0);" onclick="prodOrder('4');">商品上最新架</a></li> 
				</ul> 
			</dd> 
		</dl> 
</section> 
     <div class="prolist" id="prodList" style="margin-top: 89px;">
      
     </div><!--prolist/-->
     
     <div id='ajax_loading' style='height:30px;width:100%;text-align:center;line-height:30px;font-size:12px;display:none;padding-bottom: 20px;'><img src="${frontMobileStaticCtx}/images/loading.gif" style="width:25px;"/> 数据加载中...</div>
     <!--footNav-->
    	<%@ include file="/common/mobilefooter.jsp" %>
     <!--footNav/-->
    </div><!--maincont-->
    
  </body>
  <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/prodList.js"></script>
  
</html>