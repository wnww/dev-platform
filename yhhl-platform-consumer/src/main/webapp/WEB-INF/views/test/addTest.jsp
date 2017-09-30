<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/import.jsp" %>
<script type="text/javascript">
	var inputForm = '#inputForm';
	$(function(){
		$(inputForm).form({
		    success:function(data){
		    	var result = jQuery.parseJSON(data);
		    	if(result.flag=='T'){
		    		$.messager.confirm('提交结果', '操作成功', function(){
						parent.colseAdd();// 关闭添加窗口
		    			parent.winReload();// 刷新列表
					});
		    	}else if(result.flag=='H'){
		    		$.messager.alert('提交结果', result.msg, 'info');
		    	}else{
		    		$.messager.alert('提交结果', '操作失败:'+result.msg, 'error');
		    	}        
		    },
		    error:function(messg)  { 
	       	    $.messager.alert('错误提示', messg.responseText, 'error');
	       }  
		});
		$('#prodName').focus();
	});
	
	function doSubmit(){
		var flag = $(inputForm).form('validate');
		if(flag){
			$(inputForm).submit();
		}
	}
</script>
</head>
<body>
<div id="tip"> </div>

<form action="${ctx}/sysManage/test/saveTest.do" id="inputForm" name="inputForm">
<input type="hidden" name="token" id="token" value="${token}"/>
<input type="hidden" name="id" id="id" value="${test.id}"/>
<table class="datagrid-body" >
	<tr>
		<td class="datagrid-header">用户名</td>
		<td><input type="text" name="name" value="${test.name }"></td>
	</tr>
	<tr>
		<td class="datagrid-header">年龄</td>
		<td><input type="text" name="age" value="${test.age }"></td>
	</tr>
	<tr>
		<td colspan="2"><a href="javascript:void(0);" class="easyui-linkbutton" id="bt1" iconCls="icon-save" onclick="doSubmit();">提交</a></td>
	</tr>
</table>
</form>
</body>
</html>