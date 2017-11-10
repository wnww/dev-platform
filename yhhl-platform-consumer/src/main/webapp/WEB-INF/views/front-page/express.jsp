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
    <style type="text/css">
	
		ul{
			font-size:12px;	color:#555;	max-width:410px; margin-bottom: 50px;margin-top: 60px;
		}
		ul li{
			list-style:none;
			padding-left: 20px;min-height: 20px;line-height: 20px;;border-bottom: none;clear: both;overflow: hidden;margin-bottom: 15px;position: relative;
		}
		.jiantou {
			background: url('${frontMobileStaticCtx}/images/jtou_gray.png') no-repeat 3px 0;	display: block;	position: absolute;	width: 10px; height: 15px; left: -10px;
		}
		.content_time {
			float: left; border: #eeeeee 1px solid; position: relative; border-radius: 3px; padding: 10px;	max-width:410px;margin-left:10px;
		}
		.time_active {
			background: url('${frontMobileStaticCtx}/images/time_active.png') no-repeat 0 2px; width: 6px; height: 55px;	left: 5px;	position: absolute;
		}
		.time_normal {
			background: url('${frontMobileStaticCtx}/images/time_normal.png') no-repeat 0 2px; width: 6px; height: 55px;	left: 5px;	position: absolute;
		}
  </style>
  </head>
  <body>
    <div class="maincont">
     <header style="position: fixed;padding-top:0px;margin-top:0px;z-index: 1000">
      <a href="javascript:history.back(-1)" class="back-off fl"><span class="glyphicon glyphicon-menu-left"></span></a>
      <div class="head-mid">
       <h1>物流信息</h1>
      </div>
     </header>
     
     
     <div id="expressList">
     	${expressInfo }
     </div>
     <!--dingdanlist/--> 

     <!--footNav-->
    	<%@ include file="/common/mobilefooter.jsp" %>
     <!--footNav/-->
     
    </div><!--maincont-->
  </body>
</html>