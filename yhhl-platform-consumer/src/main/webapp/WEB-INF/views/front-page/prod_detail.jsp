<%@page import="com.yhhl.product.model.Products"%>
<%@ page import="com.yhhl.weixin.util.AccessTokenUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
Products prod = (Products)request.getAttribute("prod");
String strBackUrl = "http://www.yhsoft.top/prodDetail.do?prodId="+prod.getProdId();
String signature = AccessTokenUtil.getSignature(strBackUrl);
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/mobilemeta.jsp" %>
<%@ include file="/common/mobileimport.jsp" %>
<c:set var="weiXinAppID" value="<%=AccessTokenUtil.getAppId() %>"/>
<c:set var="timestamp" value="<%=AccessTokenUtil.getTimestamp() %>"/>
<c:set var="nonceStr" value="<%=AccessTokenUtil.getNonceStr() %>"/>
<c:set var="signature" value="<%=signature %>"/>
<c:set var="strBackUrl" value="<%=strBackUrl %>"/>

<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String fileRoot = (String)request.getAttribute("includeHtmlPath");
 %>
<title>${websiteTitle}</title>
<style type="text/css">
.slider .slide-dragcontainer .slide-container .slide-wrapper img{
	width:80% !important;
	margin-left: 10% !important;
}
</style>
<script type="text/javascript">
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: '${weiXinAppID}', // 必填，公众号的唯一标识
	    timestamp: '${timestamp}', // 必填，生成签名的时间戳
	    nonceStr: '${nonceStr}', // 必填，生成签名的随机串
	    signature: '${signature}',// 必填，签名，见附录1
	    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	wx.error(function(res){
	    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
	    console.log("JSSDK错误信息："+res.errMsg);
	});
	
	var showImgUrl = getFirstImg(ctx,'${prod.imgUrl}');
	wx.ready(function () {  
		wx.onMenuShareTimeline({
		    title: '${prod.prodName}', // 分享标题
		    link: '${strBackUrl}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		    imgUrl: showImgUrl, // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		        alertMsg("分享成功");
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
		wx.onMenuShareAppMessage({
		    title: '${prod.prodName}', // 分享标题
		    desc: '${prod.prodName}', // 分享描述
		    link: '${strBackUrl}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		    imgUrl: showImgUrl, // 分享图标
		    type: 'link', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    	//alertMsg("分享成功");
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
	});
</script>
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
	       <td width="70%">
	        <strong>${prod.prodName }</strong>
	       </td>
	       <td align="right" width="30%" style="padding-right:15px;">
	        <a href="javascript:void(0);" id="showCang" class="shoucang"><span id="start" class="glyphicon glyphicon-star-empty"></span></a>
	       </td>
	   </tr>
       <tr>
	       <th><strong class="orange">￥<fmt:formatNumber value="${prod.unitPriceSell/100}" type="currency" pattern="0.00"/></strong></th>
	       <td align="right" style="padding-right:15px;"><input type="text" class="spinnerExample" /></td>
       </tr>
       <tr>
       		<td align="right" colspan="2" style="padding-right:15px;">
	       		<span style="color::#a6a6a6;font-weight: bold;">库存余</span>
	       			<input type="text" id="showRemainNum" style="width:30px;text-align: center;" readonly="readonly"/>
	       			&nbsp;
	       		<span style="color::#a6a6a6;font-weight: bold;">已售</span>
	       			<input readonly="readonly" type="text" id="showSelledNum" style="width:30px;text-align: center;"/>
	       			<input type="hidden" name="token" id="token" value="${token}"/>
	       			<input type="hidden" name="selectStockId" id="selectStockId"/>
	      </td>
       </tr>
     </table>
     <!-- div class="height2"></div-->
     <h3 class="proTitle">商品规格</h3>
     <ul class="guige">
      	<c:forEach var="item" items="${stocks}" varStatus="st">
      		<!-- 初始化页面第一个默认选中 -->
      		<c:if test="${st.index==0}">
      			<li class="guigeCur">
	      			<a href="javascript:;">${item.colorsId}&nbsp;${item.specificationId}</a>
	      			<input type="hidden" id="remainNum" name="remainNum" value="${item.remainNum}">
	      			<input type="hidden" id="selledNum" name="selledNum" value="${item.selledNum}">
	      			<input type="hidden" id="stockId" name="stockId" value="${item.stockId}">
      			</li>
      		</c:if>
      		<!-- 初始化页面，其它不选中 -->
      		<c:if test="${st.index>0}">
      			<li>
      				<a href="javascript:;">${item.colorsId}&nbsp;${item.specificationId}</a>
      				<input type="hidden" id="remainNum" name="remainNum" value="${item.remainNum}">
      				<input type="hidden" id="selledNum" name="selledNum" value="${item.selledNum}">
      				<input type="hidden" id="stockId" name="stockId" value="${item.stockId}">
      			</li>
      		</c:if>
      	</c:forEach>
      	<div class="clearfix"></div>
     </ul><!--guige/-->
     <!--div class="height2"></div-->
     <div style="margin-bottom: 5px;">
	     <ul class="pronav">
		      <li style="width:50%"><a href="javascript:void(0);" onclick="saveCarts();">加入购物车</a></li>
		      <li style="width:50%"><a href="javascript:void(0);" onclick="saveOrders();">直接购买</a></li>
		      <div class="clearfix"></div>
	     </ul>
     </div>
     <div class="zhaieq">
      	<span style="padding-left:10px;height:50px;line-height:50px;text-align:center;font-size:1.8rem;color:#a6a6a6;">商品详情</span>
      <div class="clearfix"></div>
     </div>
     <div class="proinfoList"><!-- 生成的表面页面内容引入 -->
      	<jsp:include page="<%=fileRoot %>" />
     </div>
     
     <!-- footNav -->
     <%@ include file="/common/mobilefooter.jsp" %>
     <!--footNav/-->
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
  <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/prod_detail.js?v=1.0.0"></script>
</html>