<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<title>订单维护</title>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/import.jsp" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	var inputForm = '#inputForm';
	$(function(){
		$(inputForm).form({
		    success:function(data){
		    	var result = jQuery.parseJSON(data);
		    	if(result.flag==1){
		    		$.messager.confirm('提交结果', '操作成功', function(){
		    			parent.colseAdd();// 关闭添加窗口
		    			parent.winReload();// 刷新列表
					});
		    	}else if(result.flag==2){
		    		$.messager.alert('提交结果', result.msg, 'info');
		    	}else if (result.flag == 3) {
					$.messager.alert('结果', '您还未登录，请先登录！', 'error', function(){
						document.location.href="${ctx}/sysManage/index.do";
					});
				}else{
		    		$.messager.alert('提交结果', '操作失败:'+result.msg, 'error');
		    	}        
		    },
		    error:function(messg)  { 
	       	    $.messager.alert('错误提示', messg.responseText, 'error');
	       }  
		});
		
		// 获取订单所有状态
		$('#filter_status').combobox({
		    url:'${ctx}/sysManage/orders/getOrderStatus.do',
		    valueField:'status',
		    textField:'value'
		});
	});
	
	function doSubmit(){
		var flag = $(inputForm).form('validate');
		if(flag){
			var inputValue = (Number($("#orderAmountTemp").val())*100).toFixed(0);
    		inputValue = parseInt(inputValue);
    		$("#orderAmount").val(inputValue);
    		
    		var expressFee = (Number($("#expressFeeTemp").val())*100).toFixed(0);
    		expressFee = parseInt(expressFee);
    		$("#expressFee").val(expressFee);
			$(inputForm).submit();
		}
	}
</script>
</head>
<body>
<div class="easyui-panel" title="订单信息维护" style="width:100%; height:480px; max-width:630px;padding:20px 150px 20px 20px;">
	<form action="${ctx}/sysManage/orders/saveOrders.do" id="inputForm" name="inputForm" method="post">
		<input type="hidden" name="token" id="token" value="${token}"/>
		<input type="hidden" name="orderId" id="orderId" value="${orders.orderId}"/>
		<div style="margin-bottom:20px">
	        <label class="label-top">购买人姓名</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="ownerRealName" value="${orders.ownerRealName }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">订单商品总价</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" id="orderAmountTemp" name="orderAmountTemp" value='<fmt:formatNumber value="${orders.orderAmount/100}" type="currency" pattern="0.00"/>' style="width:100%;" data-options="required:true">
	        <input type="hidden" name="orderAmount" id="orderAmount"/>
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">快递费用</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" id="expressFeeTemp" name="expressFeeTemp" value='<fmt:formatNumber value="${orders.expressFee/100}" type="currency" pattern="0.00"/>' style="width:100%;" data-options="required:true">
	        <input type="hidden" name="expressFee" id="expressFee"/>
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">购买人电话</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="ownerMobile" value="${orders.ownerMobile }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">订单状态</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" id="filter_status" name="status" value="${orders.status }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">邮寄地址</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="postAddress" value="${orders.postAddress }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">备注/描述</label>
	        <textarea class="easyui-textarea theme-textbox-radius" name="remark" style="width:100%;" rows="5" data-options="required:true">${orders.remark }</textarea>
	    </div>
		<div>
	        <a href="javascript:void(0);" class="easyui-linkbutton button-default" iconCls="icon-ok" style="width:100%;height:32px" onclick="doSubmit();">提交</a>
	    </div>
	    <div style="height:20px">
	    	&nbsp;
	    </div>
    </form>
</div>

</body>
</html>