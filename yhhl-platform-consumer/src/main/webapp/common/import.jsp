
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- css import -->
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/easyui/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/easyui/themes/icon.css" />
<link href="${ctx}/js/jquery-validation/milk.css" type="text/css" rel="stylesheet" />
<!-- js import -->
<script type="text/javascript" src="${ctx}/js/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<!-- 扩展easyui验证 -->
<script type="text/javascript" src="${ctx}/js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/js/easyui/extendvalidate.js"></script>