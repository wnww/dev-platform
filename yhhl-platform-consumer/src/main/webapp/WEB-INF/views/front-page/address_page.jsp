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
    <script type="text/javascript">
    	function selectAddress(addrId,orderId){
    		document.location.href="${ctx}/orders/pay.do?orderId="+orderId+"&addrId="+addrId;
    	}
    	
    	function deleteAddr(addrId){
    		$.ajax({
    			type : "post",
    			url : "${ctx}/address/delAddress.do?id="+addrId,
    			dataType : "json",
    			success : function(data) {
    				if (data.flag == 1) {
    					alertMsg(data.msg,"/address/selectAddress.do");
    				} else if (data.flag == 3) {
    					alertMsg(data.msg,"/login.do");
    				} else {
    					alertMsg(data.msg);
    				}
    			},
    			error : function(messg) {
    				alertMsg("删除地址失败："+messg.responseText);
    			}
    		});
    	}
    </script>
  </head>
  <body>
    <div class="maincont">
     <header>
      <a href="javascript:history.back(-1)" class="back-off fl"><span class="glyphicon glyphicon-menu-left"></span></a>
      <div class="head-mid">
       <h1>收货地址</h1>
      </div>
     </header>
     <table class="shoucangtab">
      <tr>
       <td><a href="${ctx}/address/initAddAddress.do?orderId=${orderId}" class="hui"><strong class="">+</strong> 新增收货地址</a></td>
      </tr>
     </table>
     <input type="hidden" id="orderId" name="orderId" value="${orderId}"/>
     <c:forEach var="item" items="${addressList}">
     	<c:if test="${empty orderId}">
     		<div class="dingdanlist">
     	</c:if>
     	<c:if test="${not empty orderId}">
     		<div class="dingdanlist" onclick="javascript:selectAddress('${item.addrId}','${orderId}');">
     	</c:if>
	      <table>
	       <tr>
	        <td width="60%">
	         <h3>${item.realName} ${item.mobile}</h3>
	         <time>${item.province}${item.city}${item.address}</time>
	        </td>
	        <td align="right">
	        	<time><a href="${ctx}/address/initAddAddress.do?id=${item.addrId}&orderId=${orderId}" class="hui"><c:if test="${item.defaultAdd==1}"><span style="border: 1px solid #f60;border-color: #ff3800;border-radius:3px;background: #ffeecc;color:#f60;padding: 2px 5px; font-size: 12px;">默认</span></c:if> 修改地址</a></time>
	        	<time><a href="javascript:void(0);" onclick="javascript:deleteAddr('${item.addrId}')" class="hui">删除地址</a></time>
	        </td>
	       </tr>
	      </table>
	     </div>
     </c:forEach>
     <!--dingdanlist/-->
     
    <!--footNav-->
    <%@ include file="/common/mobilefooter.jsp" %>
    <!--footNav/-->
    </div><!--maincont-->
  </body>
  
</html>