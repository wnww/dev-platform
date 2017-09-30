<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/mobilemeta.jsp" %>
<%@ include file="/common/mobileimport.jsp" %>
<title>晨曦诺言的小店</title>
</head>
<body>
  <div class="maincont">
     <div class="head-top">
      	<div id="sliderA" class="slider">
	      <img src="${frontMobileStaticCtx}/images/image1.jpg" />
	      <img src="${frontMobileStaticCtx}/images/image2.jpg" />
	      <img src="${frontMobileStaticCtx}/images/image3.jpg" />
	      <img src="${frontMobileStaticCtx}/images/image4.jpg" />
	      <img src="${frontMobileStaticCtx}/images/image5.jpg" />
     	</div><!--sliderA/-->
      <dl>
       <dd>
        <ul>
         <li><a href="prolist.html"><strong>34</strong><p>全部商品</p></a></li>
         <li><a href="javascript:;"><span class="glyphicon glyphicon-star-empty"></span><p>收藏本店</p></a></li>
         <li style="background:none;"><a href="javascript:;"><span class="glyphicon glyphicon-picture"></span><p>二维码</p></a></li>
         <div class="clearfix"></div>
        </ul>
       </dd>
       <div class="clearfix"></div>
      </dl>
     </div><!--head-top/-->
     <form action="#" method="get" class="search">
      <input type="text" class="seaText fl" />
      <input type="submit" value="搜索" class="seaSub fr" />
     </form><!--search/-->
     <c:if test="${empty loginUser}">
	     <ul class="reg-login-click">
	      <li><a href="${ctx}/login.do">登录</a></li>
	      <li><a href="reg.html" class="rlbg">注册</a></li>
	      <div class="clearfix"></div>
	     </ul><!--reg-login-click/-->
	 </c:if>
     <!-- ul class="pronav">
      <li><a href="prolist.html">晋恩干红</a></li>
      <li><a href="prolist.html">万能手链</a></li>
      <li><a href="prolist.html">高级手镯</a></li>
      <li><a href="prolist.html">特异戒指</a></li>
      <div class="clearfix"></div>
     </ul-->
     <!--pronav/-->
     <div class="index-pro1" id="prodList">
		
      	
     </div>
     <div id='ajax_loading' style='height:30px;width:100%;text-align:center;line-height:30px;font-size:12px;display:none;padding-bottom: 20px;'><img src="${frontMobileStaticCtx}/images/loading.gif" style="width:25px;"/> 数据加载中...</div>
     <!--index-pro1/-->
     <!-- footNav -->
     <%@ include file="/common/mobilefooter.jsp" %>
     <!--footNav/-->
    </div><!--maincont-->
    <script>
		$(function () {
		 $("#sliderA").excoloSlider();
		});
	</script>
</body>
<script type="text/javascript" src="${frontMobileStaticCtx}/js/src/index.js"></script>
</html>