<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/mobilemeta.jsp" %>
<%@ include file="/common/mobileimport.jsp" %>
<title>${websiteTitle}</title>
</head>
<body>
  <div class="maincont">
     <div class="head-top">
      	<div id="sliderA" class="slider">
	      <img src="${ctx}/userfiles/imgFiles/20170928/2017092815521893134.jpg"/>
	      <img src="${ctx}/userfiles/imgFiles/20170926/2017092616262334430.jpg"/>
     	</div><!--sliderA/-->
      <dl>
       <dd>
        <ul style="margin-top: 30px">
         <li><a href="${ctx}/prodList.do"><strong id="totalCount">0</strong><p>全部商品</p></a></li>
         <li><a href="javascript:void(0);" id="aboutMyMall"><span class="glyphicon glyphicon-heart"></span><p>关于本店</p></a></li>
         <li style="background:none;"><a href="javascript:void(0);" id="secondCode"><span class="glyphicon glyphicon-qrcode"></span><p>二维码</p></a></li>
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
	      <li><a href="${ctx}/register.do" class="rlbg">注册</a></li>
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
		 
		});
	</script>
	
	<!-- 模态框（Modal） 关于本店 -->
<div class="modal fade" id="myModal" style="margin-top: 50%" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					关于本店
				</h4>
			</div>
			<div class="modal-body">
				暂无介绍
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 模态框（Modal） 二维码 -->
<div class="modal fade" id="mySecondCodeModal" style="margin-top: 50%;margin-left:auto;margin-right: auto; width:200px;height:200px" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<img alt="" src="${frontMobileStaticCtx}/images/jf2.jpg">
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</body>
<script type="text/javascript" src="${frontMobileStaticCtx}/js/src/index.js"></script>
</html>