<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>
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
		    	var result = "";
		    	try{
		    		result = jQuery.parseJSON(data);
		    	}catch(e){
		    		$.messager.alert('错误提示', messg.responseText, 'error');
		    	}
		    	if(result.flag=='T'){
		    		$.messager.confirm('提交结果', result.msg, function(){
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
	
	function hasContent() {
        return UE.getEditor('editor').hasContents();
    }
	
	function getContent(){
		if(hasContent()==false){
			alert("请输入详情");
			return false;
		}
		$("#content").val(UE.getEditor('editor').getContent());
		return $("#content").val();
	}
	
	function setEditContent(content){
		 UE.getEditor('editor').ready(function(){
		 	UE.getEditor('editor').execCommand('insertHtml', content);
		 });
	}
	
	
	
	
</script>
</head>
<body>
<div id="tip"> </div>
<table class="datagrid-body" >
	<tr>
		<td>
			<script id="editor" type="text/plain" style="width:800px; height:300px;"></script>
			<textarea rows="15" cols="30" id="content" name="content" hidden="hidden"></textarea>
		</td>
	</tr>
	<tr>
		<td>
			<input type="button" name="btn" value="显示" onclick="alt1();">
		</td>
	</tr>
</table>

<!-- JiaThis Button BEGIN -->
<div class="jiathis_style_32x32">
	<a class="jiathis_button_qzone"></a>
	<a class="jiathis_button_tsina"></a>
	<a class="jiathis_button_tqq"></a>
	<a class="jiathis_button_weixin"></a>
	<a class="jiathis_button_renren"></a>
	<a href="http://www.jiathis.com/share?uid=2087010" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
	<a class="jiathis_counter_style"></a>
</div>
<script type="text/javascript">
var jiathis_config = {data_track_clickback:'true'};
</script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=2087010" charset="utf-8"></script>
<!-- JiaThis Button END -->
</body>
<script>
function setContent(content){
	$("#content").val(content);
	setEditContent(content);
}
function alt1(){
	alert(getContent());
}
</script>
</html>