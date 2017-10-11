<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/mobilemeta.jsp" %>
<%@ include file="/common/mobileimport.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <title>${websiteTitle}</title>
  </head>
  <body>
    <div class="maincont">
     <header>
      <a href="javascript:history.back(-1)" class="back-off fl"><span class="glyphicon glyphicon-menu-left"></span></a>
      <div class="head-mid">
       <h1>购物车</h1>
      </div>
     </header>
     <table class="shoucangtab">
      <tr>
       <td width="75%"><span class="hui">购物车共有：<strong class="orange" id="totalCount">0</strong>件商品</span></td>
       <td width="25%" align="center" style="background:#fff url(${frontMobileStaticCtx}/images/xian.jpg) left center no-repeat;">
        <span class="glyphicon glyphicon-shopping-cart" style="font-size:2rem;color:#666;"></span>
       </td>
      </tr>
     </table>
     <table class="shoucangtab">
      <tr>
       	<td>
       		<div>
       			<div style="float: left;">
       				<a href="javascript:void(0);" onclick="selectAll();" class="jiesuan" style="width:36px; font-size:12px;height:26px;line-height: 28px;">全选</a>
       			</div>
       			<div style="float: left; margin-left: 10px;">
       				<a href="javascript:void(0);" onclick="unSelectAll();" class="jiesuan" style="width:60px; font-size:12px;height:26px;line-height: 28px;">取消全选</a>
       			</div>
	       		<div style="float: left; margin-left: 10px;">
	       			<a href="javascript:;" class="jiesuan" style="width:36px; font-size:12px;height:26px;line-height: 28px;">删除</a>
	       		</div>
	       	</div>
       	</td>
       	
      </tr>
     </table>
     
     <!--dingdanlist-->
		<div id="dataList">
		
		</div>
     <!--dingdanlist/-->
     <div class="height1"></div>
     <div class="gwcpiao">
     <table>
      <tr>
       <th width="10%"><a href="javascript:history.back(-1)"><span class="glyphicon glyphicon-menu-left"></span></a></th>
       <td width="50%">总计：<strong class="orange">¥</strong><strong class="orange" id="showTotalMoney">0.00</strong>
       <input type="hidden" id="totalMoney" name="totalMoney"></td>
       <td width="40%"><a href="javascript:void(0);" id="jiesuan" class="jiesuan">结算</a></td>
      </tr>
     </table>
    </div><!--gwcpiao/-->
    </div><!--maincont-->
   
  </body>
  <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/cart.js"></script>
  
</html>