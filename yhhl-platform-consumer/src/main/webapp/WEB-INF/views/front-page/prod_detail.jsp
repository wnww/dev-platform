<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/mobilemeta.jsp" %>
<%@ include file="/common/mobileimport.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String fileRoot = (String)request.getAttribute("includeHtmlPath");
 %>
<title>晨曦诺言的小店</title>
</head>
<body>
  <div class="maincont">
     <header>
      <a href="javascript:history.back(-1)" class="back-off fl"><span class="glyphicon glyphicon-menu-left"></span></a>
      <div class="head-mid">
       <h1>产品详情</h1>
      </div>
     </header>
     <div id="sliderA" class="slider">
     <c:set value="${fn:split(prod.imgUrl, ',')}" var="imgUrls" />
     <c:forEach var="imgUrl" items="${imgUrls}">
     	<img src="${ctx}${imgUrl }" />
     </c:forEach>
     </div><!--sliderA/-->
     <table class="jia-len">
      <tr>
       <th><strong class="orange">￥${prod.unitPriceSell}</strong></th>
       <td>
        <input type="text" class="spinnerExample" />
       </td>
      </tr>
      <tr>
       <td>
        <strong>${prod.prodName }</strong>
       </td>
       <td align="right">
        <a href="javascript:;" class="shoucang"><span class="glyphicon glyphicon-star-empty"></span></a>
       </td>
      </tr>
     </table>
     <div class="height2"></div>
     <h3 class="proTitle">商品规格</h3>
     <ul class="guige">
      <li class="guigeCur"><a href="javascript:;">50ML</a></li>
      <li><a href="javascript:;">100ML</a></li>
      <li><a href="javascript:;">150ML</a></li>
      <li><a href="javascript:;">200ML</a></li>
      <li><a href="javascript:;">300ML</a></li>
      <div class="clearfix"></div>
     </ul><!--guige/-->
     <div class="height2"></div>
     <div class="zhaieq">
      	<span style="padding-left:10px;height:50px;line-height:50px;text-align:center;font-size:1.8rem;color:#a6a6a6;">商品详情</span>
      <div class="clearfix"></div>
     </div>
     <div class="proinfoList"><!-- 生成的表面页面内容引入 -->
      	<jsp:include page="<%=fileRoot %>" />
     </div>
     <div style="margin-bottom: 5px;">
	     <ul class="pronav">
	     	  <li style="width:30%;"><a href="index.html">回首页</a></li>
		      <li style="width:30%"><a href="prolist.html">加入购物车</a></li>
		      <li style="width:30%"><a href="prolist.html">直接购买</a></li>
		      <div class="clearfix"></div>
	     </ul>
     </div>
    </div>
    <script>
		$(function () {
		 $("#sliderA").excoloSlider();
		});
	</script>
    <script src="js/jquery.spinner.js"></script>
   <script>
		$('.spinnerExample').spinner({});
	</script>
  </body>
</html>