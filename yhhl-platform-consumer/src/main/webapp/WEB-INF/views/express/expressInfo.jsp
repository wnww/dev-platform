<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<title>订单维护</title>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/import.jsp" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style type="text/css">
	
		ul{
			font-size:12px;	color:#555;	width:540px; margin-bottom: 50px;margin-top: 0px;
		}
		ul li{
			list-style:none;
			padding-left: 20px;min-height: 20px;line-height: 20px;;border-bottom: none;clear: both;overflow: hidden;margin-bottom: 15px;position: relative;
		}
		.jiantou {
			background: url('${frontMobileStaticCtx}/images/jtou_gray.png') no-repeat 3px 0;display: block;	position: absolute;	width: 10px; height: 15px; left: -10px;
		}
		.content_time {
			float: left; border: #eeeeee 1px solid; position: relative; border-radius: 3px; padding: 10px; width:470px; margin-left:10px;
		}
		.time_active {
			background: url('${frontMobileStaticCtx}/images/time_active.png') no-repeat 0 2px; width: 6px; height: 55px;	left: 5px;	position: absolute;
		}
		.time_normal {
			background: url('${frontMobileStaticCtx}/images/time_normal.png') no-repeat 0 2px; width: 6px; height: 55px;	left: 5px;	position: absolute;
		}
  </style>
<script type="text/javascript">
	
</script>
</head>
<body>
<div class="easyui-panel" title="" style="width:100%; height:100%; padding:20px 150px 20px 20px;">
	${expressInfo }
</div>

</body>
</html>