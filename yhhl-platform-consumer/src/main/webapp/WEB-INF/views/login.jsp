<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <head>
    <meta charset="utf-8">
    <title>国版中心平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
  <div class="easyui-panel" title="" style="max-width:650px; margin-left: 200px;margin-top: 200px; padding-right:260px;">
	<form action="${ctx}/login.do" id="inputForm" name="inputForm" method="post">
	<input type="hidden" name="token" id="token" value="${token}"/>
	<input type="hidden" name="id" id="id" value="${user.id}"/>
	
	<div style="margin-bottom:20px">
        <label class="label-top">用户名</label>
        <input class="easyui-textbox theme-textbox-radius" type="text" name="name" value="${user.name }" style="width:100%;" data-options="required:true">
    </div>
    <div style="margin-bottom:20px">
        <label class="label-top">密码</label>
        <input class="easyui-textbox theme-textbox-radius" type="password" name="pwd" value="${user.pwd }" style="width:100%;" data-options="required:true">
    </div>
	<div>
        <a href="javascript:void(0);" class="easyui-linkbutton button-default" iconCls="icon-ok" style="width:100%;height:32px" onclick="doSubmit();">提交</a>
    </div>
</div>
  </body>
</html>