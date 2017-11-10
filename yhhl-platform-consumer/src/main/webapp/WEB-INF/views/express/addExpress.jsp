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
		
		// 获取快递公司名称
		$('#expressComCode').combobox({
		    url:'${ctx}/common/expressCompany.json',
		    valueField:'express_com_code',
		    textField:'express_com_name'
		});
	});
	
	function doSubmit(){
		var flag = $(inputForm).form('validate');
		if(flag){
			var expressComName = $('#expressComCode').combobox('getText');
			$("#expressComName").val(expressComName);
			$(inputForm).submit();
		}
	}
</script>
</head>
<body>
<div class="easyui-panel" title="快递信息维护" style="width:100%; height:480px; max-width:630px;padding:20px 150px 20px 20px;">
	<form action="${ctx}/sysManage/express/saveExpress.do" id="inputForm" name="inputForm" method="post">
		<input type="hidden" name="token" id="token" value="${token}"/>
		<input type="hidden" name="expressId" id="expressId" value="${express.expressId}"/>
		<input type="hidden" name="orderId" id="orderId" value="${express.orderId}"/>
		<div style="margin-bottom:20px">
	        <label class="label-top">快递公司名称</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" id="expressComCode" name="expressComCode" value="${express.expressComCode }" style="width:100%;" data-options="required:true">
	        <input type="hidden" name="expressComName" id="expressComName" value="${express.expressComName}"/>
	    </div>
		<div style="margin-bottom:20px">
	        <label class="label-top">快递单号</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="expressCode" value="${express.expressCode }" style="width:100%;" data-options="required:true">
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