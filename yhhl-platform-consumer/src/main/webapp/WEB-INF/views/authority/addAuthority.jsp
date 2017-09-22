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

<div class="easyui-panel" title="" style="width:100%; max-width:650px;padding:20px 150px 20px 20px;">
	<form action="${ctx}/authority/saveAuthority.do" id="inputForm" name="inputForm" method="post">
		<input type="hidden" name="token" id="token" value="${token}"/>
		<input type="hidden" name="authId" id="authId" value="${authority.authId}"/>
		<div style="margin-bottom:20px">
	        <label class="label-top">权限名称</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="authName" value="${authority.authName }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">权限类型</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="authType" value="${authority.authType }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">权限资源路径</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="authMark" value="${authority.authMark }" style="width:100%;" data-options="required:true">
	    </div>
	    <div>
	        <a href="javascript:void(0);" class="easyui-linkbutton button-default" iconCls="icon-ok" style="width:100%;height:32px" onclick="doSubmit();">提交</a>
	    </div>
    </form>
</div>
</body>
</html>