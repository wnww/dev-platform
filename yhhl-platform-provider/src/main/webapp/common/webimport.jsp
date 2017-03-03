<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- css import -->
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css" />
<!-- js import -->
<script type="text/javascript" src="${ctx}/js/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
<!-- 扩展easyui验证 -->
<script type="text/javascript" src="${ctx}/js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/messages_cn.js"></script>