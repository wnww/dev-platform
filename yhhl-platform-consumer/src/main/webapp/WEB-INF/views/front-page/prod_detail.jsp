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
  	<input type="hidden" id="prodId" name="prodId" value="${prod.prodId}"/>
     <header>
      <a href="javascript:history.back(-1)" class="back-off fl"><span class="glyphicon glyphicon-menu-left"></span></a>
      <div class="head-mid">
       <h1>商品详情</h1>
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
	       <td width="80%" colspan="2">
	        <strong>${prod.prodName }</strong>
	       </td>
	       <td align="right" width="20%">
	        <a href="javascript:;" class="shoucang"><span class="glyphicon glyphicon-star-empty"></span></a>
	       </td>
	   </tr>
       <tr>
	       <th width="25%"><strong class="orange">￥${prod.unitPriceSell}</strong></th>
	       <td width="25%" align="right"><input type="text" class="spinnerExample" /></td>
	       <td width="50%" align="right"><span style="color::#a6a6a6;font-weight: bold;">库存余</span><input type="text" id="showRemainNum" style="width:30px;text-align: center;" readonly="readonly"/></span>&nbsp;<span style="color::#a6a6a6;font-weight: bold;">已售</span><input readonly="readonly" type="text" id="showSelledNum" style="width:30px;text-align: center;"/></td>
       </tr>
     </table>
     <div class="height2"></div>
     <h3 class="proTitle">商品规格</h3>
     <ul class="guige">
      	<c:forEach var="item" items="${stocks}" varStatus="st">
      		<c:if test="${st.index==0}">
      			<li class="guigeCur"><a href="javascript:;">${item.colorsId}&nbsp;${item.specificationId}</a><input type="hidden" id="remainNum" name="remainNum" value="${item.remainNum}"><input type="hidden" id="selledNum" name="selledNum" value="${item.selledNum}"></li>
      		</c:if>
      		<c:if test="${st.index>0}">
      			<li><a href="javascript:;">${item.colorsId}&nbsp;${item.specificationId}</a><input type="hidden" id="remainNum" name="remainNum" value="${item.remainNum}"><input type="hidden" id="selledNum" name="selledNum" value="${item.selledNum}"></li>
      		</c:if>
      	</c:forEach>
      	<div class="clearfix"></div>
     </ul><!--guige/-->
     <div class="height2"></div>
     <div class="zhaieq">
      	<span style="padding-left:10px;height:50px;line-height:50px;text-align:center;font-size:1.8rem;color:#a6a6a6;">商品详情</span>
      <div class="clearfix"></div>
     </div>
     <div class="proinfoList"><!-- 生成的表面页面内容引入 -->
      	<!-- jsp:include page="<%=fileRoot %>" /-->
     </div>
     <div style="margin-bottom: 5px;">
	     <ul class="pronav">
	     	  <li style="width:30%;"><a href="${ctx}/front/index.do">回首页</a></li>
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
   <script>
		$('.spinnerExample').spinner({});
	</script>
  </body>
  <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/prod_detail.js"></script>
</html>