<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- css import -->
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css?v=1.0.0" />
<!-- js import -->
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js?v=1.0.0"></script>
<!-- 扩展easyui验证 -->
<script type="text/javascript" src="${ctx}/js/jquery-validation/jquery.validate.min.js?v=1.0.0"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/messages_cn.js?v=1.0.0"></script>