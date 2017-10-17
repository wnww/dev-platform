<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<title>订单维护</title>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/import.jsp" %>
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
		
	});
	
	function doSubmit(){
		var flag = $(inputForm).form('validate');
		if(flag){
			var inputValue = (Number($("#orderAmountTemp").val())*100).toFixed(0);
    		inputValue = parseInt(inputValue);
    		$("#orderAmount").val(inputValue);
			$(inputForm).submit();
		}
	}
</script>
</head>
<body>
<div class="easyui-panel" title="类别维护" style="width:100%; height:480px; max-width:630px;padding:20px 150px 20px 20px;">
	<form action="${ctx}/sysManage/category/saveCategory.do" id="inputForm" name="inputForm" method="post">
		<input type="hidden" name="token" id="token" value="${token}"/>
		<input type="hidden" name="catId" id="catId" value="${category.catId}"/>
		<input type="hidden" name="parentId" id="parentId" value="${category.parentId}"/>
		<div style="margin-bottom:20px">
	        <label class="label-top">类别名</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="catName" value="${category.catName }" style="width:100%;" data-options="required:true">
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