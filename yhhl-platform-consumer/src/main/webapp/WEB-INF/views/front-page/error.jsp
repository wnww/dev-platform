<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/mobilemeta.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<%@ include file="/common/mobileimport.jsp" %>
  <title>${websiteTitle}</title>
  </head>
  <body>
    <div class="maincont" style="text-align: center">
    	<c:if test="${errorCode==404 }">
			<div>
				<img alt="页面找不到了" src="${frontMobileStaticCtx}/images/404.png">
			</div>
		</c:if>
		<c:if test="${errorCode==500 }">
			<div>
				<img alt="页面找不到了" src="${frontMobileStaticCtx}/images/500.jpg">
			</div>
		</c:if>
     <!--footNav-->
    	<%@ include file="/common/mobilefooter.jsp" %>
     <!--footNav/-->
     
    </div><!--maincont-->
  </body>
</html>