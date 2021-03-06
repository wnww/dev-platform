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
     <header style="position: fixed;width:100%;top:0px;">
      <a href="javascript:history.back(-1)" class="back-off fl"><span class="glyphicon glyphicon-menu-left"></span></a>
      <div class="head-mid">
       <h1>我的订单</h1>
      </div>
     </header>
     <div class="zhaieq oredereq" style="position:fixed; width:100%;border: 1px solid #bbb;border-color: #bbbbbb;background-color: #fff;margin-top:40px;">  
      <a href="javascript:;" class="zhaiCur" orderStatus="11">待付款</a>
      <a href="javascript:;" orderStatus="21">待发货</a>
      <a href="javascript:;" orderStatus="41">待收货</a>
      <a href="javascript:;" style="background:none;" orderStatus="51">已完成</a>
      <div class="clearfix"></div>
     </div>
     
     <div id="orderList" style="padding-top: 90px;">
     
     </div>
     
     <div id='ajax_loading' style='height:30px;width:100%;text-align:center;line-height:30px;font-size:12px;display:none;padding-bottom: 20px;'><img src="${frontMobileStaticCtx}/images/loading.gif" style="width:25px;"/> 数据加载中...</div>
     <!--footNav-->
    	<%@ include file="/common/mobilefooter.jsp" %>
    <!--footNav/-->
    </div><!--maincont-->
  </body>
  <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/myOrder.js?v=1.0.1"></script>
  <script type="text/javascript">
  var orderStatus = 11;
    $(document).ready(function(){
    	
    	$(".zhaieq a").click(function(){
    		$(this).addClass("zhaiCur").siblings("a").removeClass("zhaiCur");
    		var zhaiindex=$(this).index();
    		//$(".proinfoList").eq(zhaiindex).fadeIn().siblings(".proinfoList").hide();
    		orderStatus = $(this).attr("orderStatus");
    		$("#orderList").empty();
    		currentPage =1;
    		console.log("this.orderStatus===="+orderStatus);
    		getOrderList(pageSize,orderStatus);
    	});
    	getOrderList(pageSize,orderStatus);
		gotoNextPage(pageSize,orderStatus);
    });
    
    </script>
</html>