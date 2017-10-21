<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/mobilemeta.jsp" %>
<%@ include file="/common/mobileimport.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <title>${websiteTitle}</title>
    <script type="text/javascript">
    var expressFeeData = jQuery.parseJSON('${expressFeeData}');
    </script>
  </head>
  <body>
    <div class="maincont">
     <header>
      <a href="javascript:history.back(-1)" class="back-off fl"><span class="glyphicon glyphicon-menu-left"></span></a>
      <div class="head-mid">
       <h1>购物车</h1>
      </div>
     </header>
     <div class="dingdanlist" onClick="">
      <table id="tbe">
       <tr id="addAddress">
        <td class="dingimg" width="65%" colspan="2">新增收货地址</td>
        <td align="right"><img src="${frontMobileStaticCtx}/images/jian-new.png" /></td>
       </tr>
       <tr id="showtAddress">
	        <td width="75%" colspan="2">
	         <h3 id="realName"></h3>
	         <time id="addressDetail"></time>
	         <input type="hidden" id="ownerRealName"/>
	         <input type="hidden" id="ownerMobile"/>
	         <input type="hidden" id="postAddress"/>
	        </td>
	        <td align="right" width="50%"><a href="${ctx}/address/selectAddress.do?orderId=${order.orderId}" class="hui">切换地址</a></td>
	   </tr>
       <tr><td colspan="3" style="height:10px; background:#efefef;padding:0;"></td></tr>
       <tr>
        <td class="dingimg" width="75%" colspan="2">支付方式</td>
        <td align="right"><span class="hui">微信转账</span></td>
       </tr>
       <tr><td colspan="3" style="height:10px; background:#efefef;padding:0;"></td></tr>
       <tr><td colspan="3" style="height:10px; background:#fff;padding:0;"></td></tr>
       <tr id="orderProdList">
        <td class="dingimg" width="65%" colspan="3"><strong>商品清单</strong></td>
       </tr>
       <tr><td colspan="3" style="height:10px; background:#efefef;padding:0;"></td></tr>
       <tr>
        <td class="dingimg" width="75%" colspan="2">订单金额</td>
        <td align="right"><strong class="orange" id="showOrderAmount">¥0.00</strong><input type="hidden" id="orderAmount" name="orderAmount" /></td>
       </tr>
       <tr>
        <td class="dingimg" width="75%" colspan="2">运费</td>
        <td align="right">
        	<strong class="orange">¥</strong>
        	<strong class="orange" id="expressFee">10</strong>
        	<input type="hidden" name="expressFeehd" id="expressFeehd"/>
        </td>
       </tr>
      </table>
     </div><!--dingdanlist/-->
    </div><!--content/-->
    <div class="height1"></div>
    <div class="gwcpiao">
     <table>
      <tr>
       <th width="10%"><a href="javascript:history.back(-1)"><span class="glyphicon glyphicon-menu-left"></span></a></th>
       <td width="50%">总计：<strong class="orange">¥</strong><strong class="orange" id="orderTotalAmount">0.00</strong></td>
       <td width="40%">
       	   <input type="hidden" name="orderId" id="orderId" value="${order.orderId}"/>
       	   <input type="hidden" name="addrId" id="addrId" value="${addrId}"/>
       	   <a href="javascript:void(0);" onclick="javascript:sumbitOrder();" class="jiesuan">提交订单</a>
       </td>
      </tr>
     </table>
    </div><!--gwcpiao/-->
    </div><!--maincont-->
  </body>
  <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/pay.js?v=1.0.0"></script>
</html>