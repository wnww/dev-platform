<%@ page contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
<title>产品添加</title>
    <%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/import.jsp" %>
	<link href="${ctx}/css/ueditor.min.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctx}/js/zh-cn.js"></script>
<script type="text/javascript">
	var inputForm = '#inputForm'; 
	$(document).ready(function(){
		//实例化编辑器
    	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    	var ue = UE.getEditor('editor');
		
		$(inputForm).form({
		    success:function(data){
		    	var result = jQuery.parseJSON(data);
		    	if(result.flag==1){
		    		$.messager.confirm('提交结果', '操作成功', function(){
		    			parent.closeTab();// 关闭添加窗口
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
			var content = UE.getEditor('editor').getContent();
			$("#remark").val(content);
			$(inputForm).submit();
		}
	}
	
	
		

</script>
</head>
<body>	
<div id="tip" style="height:100%"> 
		<form id="inputForm" name="inputForm" method="post" action="${ctx}/sysManage/products/saveProductsDetail.do">
			<input type="hidden" name="token" id="token" value="${token}"/>
			<table class="datagrid-body" id="table" >
			<tr>
				<td class="datagrid-header">产品编码<font color=red>*</font></td>
			</tr>
			<tr>
				<td>
					<input id="prodId" name="prodId" value="${prodId}" readonly="readonly"
					class="easyui-validatebox" data-options="required:true"
					 size="40"/>
				</td>
			</tr>
			<tr>
				<td class="datagrid-header">产品描述,<span style='color:#ff0000'>上传的图片请在线下先切好尺寸，尽量不要在编辑器里调尺寸，否则可能会影响到手机端的显示问题。</span></td>
			</tr>
			<tr>
				<td style="height:380px;">
					<script id="editor" type="text/plain" style="width:800px; height:300px;"></script>
					<textarea rows="15" cols="30" id="remark" name="remark" hidden="hidden">${item.remark}</textarea>
				</td>
			</tr>
			<tr>
				<td >&nbsp;</td>
			</tr>					
			<tr>
				<td >	
					<a href="javascript:void(0);" class="easyui-linkbutton" id="bt1" iconCls="icon-save" onclick="doSubmit();">提交</a>
				</td>
			</tr>
			<tr>
				<td >&nbsp;</td>
			</tr>	
			</table>
		</form>
</div>
<script language="javascript">
 
var remarkContent = $("#remark").val();
function setContent(){
	if(remarkContent!=null && remarkContent!=""){
		 UE.getEditor('editor').ready(function(){
		 	UE.getEditor('editor').execCommand('insertHtml', $("#remark").val());
		 });
	}
}
setContent();


</script>
</body>
</html>

