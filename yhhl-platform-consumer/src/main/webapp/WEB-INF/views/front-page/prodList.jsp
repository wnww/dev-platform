<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/mobilemeta.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<%@ include file="/common/mobileimport.jsp" %>
<link href="${frontMobileStaticCtx}/css/filtrate.css?v=1.0.0" rel="stylesheet" />
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
		      <input type="text" id="keyWords" name="keyWords" class="seaText fl" />
		      <input type="button" id="searchBtn" name="searchBtn" value="搜索" class="seaSub fr" />
		</form><!--search/-->
      </div>
     </header>
     <dl class="topmenu">
		<dt>
			<div class="selectlist">
				<div class="select_textdiv">
					<input id="category" type="hidden" value="">
					<p class="s_text">分类</p><span class="down"><img src="http://www.jsdaima.com/Upload/1482902489/down2.png"></span>
				</div>
				<div class="select_textul" style="width: 1304px;">
					<ul class="select_first_ul">
						<li><p>服装</p></li>
						<li><p>鞋帽</p></li>
					</ul>
				</div>
			</div>	
		</dt>
		<dt>
			<div class="selectlist">
				<div class="select_textdiv">
					<input id="prodOrder" type="hidden" value="">
					<p class="s_text">排序</p><span class="down"><img src="http://www.jsdaima.com/Upload/1482902489/down2.png"></span>
				</div>
				<div class="select_textul" style="width: 1304px;">
					<ul class="select_first_ul">
						<li><p wbs="sellCount" catName="销量由高到低">销量由高到低</p></li>
						<li><p wbs="priceHigh2Low" catName="价格从高到低">价格从高到低</p></li>
						<li><p wbs="priceLow2High" catName="价格从低到高">价格从低到高</p></li>
						<li><p wbs="newProd" catName="商品上最新架">商品上最新架</p></li>
					</ul>
				</div>
			</div>	
		</dt>
	</dl>
     <div class="prolist" id="prodList" style="margin-top: 70px;">
      
     </div><!--prolist/-->
     
     <div id='ajax_loading' style='height:30px;width:100%;text-align:center;line-height:30px;font-size:12px;display:none;padding-bottom: 20px;'><img src="${frontMobileStaticCtx}/images/loading.gif" style="width:25px;"/> 数据加载中...</div>
     <!--footNav-->
    	<%@ include file="/common/mobilefooter.jsp" %>
     <!--footNav/-->
    </div><!--maincont-->
    
  </body>
  <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/prodList.js?v=1.0.0"></script>
  
</html>