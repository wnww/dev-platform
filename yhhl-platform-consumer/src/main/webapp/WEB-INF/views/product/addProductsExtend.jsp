<%@ page contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
<title>产品添加</title>
    <%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/import.jsp" %>
<script type="text/javascript">
	var inputForm = '#inputForm'; 
	$(document).ready(function(){
		$(inputForm).form({
		    success:function(data){
		    	var result = jQuery.parseJSON(data);
		    	if(result.flag==1){
		    		$.messager.confirm('提交结果', '操作成功', function(){
		    			parent.colseAdd();// 关闭添加窗口
		    			parent.productExtendReload();
					});
		    	}else if(result.flag==2){
		    		$.messager.alert('提交结果', result.msg, 'info');
		    	}else{
		    		$.messager.alert('提交结果', '操作失败:'+result.msg, 'error');
		    	}        
		    },
		    error:function(messg)  { 
	       	    $.messager.alert('错误提示', messg.responseText, 'error');
	       }  
		});
		
		// 获取规格选项列表并回显
		$('#specificationId').combobox({
		    url:'${ctx}/dicts/getDictsList.do?page=1&rows=0&filter_dictTypeName=规格',
		    valueField:'dictId',
		    textField:'dictValue'
		});
		
		// 获取规格选项列表并回显
		$('#colorsId').combobox({
		    url:'${ctx}/dicts/getDictsList.do?page=1&rows=0&filter_dictTypeName=颜色',
		    valueField:'dictId',
		    textField:'dictValue'
		});
	});
	
	
	function doSubmit(){
		$(inputForm).submit();
	}
	
	
		

</script>
</head>
<body>	
<div id="tip" style="height:100%"> 
		<form id="inputForm" name="inputForm" method="post" action="${ctx}/stocks/saveStocks.do">
			<input type="hidden" name="token" id="token" value="${token}"/>
			<input type="hidden" name="stockId" id="stockId" value="${stocks.stockId}"/>
			<div style="margin-bottom:20px">
		        <label class="label-top">商品编号</label>
		        <input class="easyui-textbox theme-textbox-radius" type="text" name="prodId" value="${prodId}" style="width:100%;" data-options="required:true">
		    </div>
		    <div style="margin-bottom:20px">
		        <label class="label-top">商品规格</label>
		        <input class="easyui-combobox " type="text" id="specificationId" name="specificationId" value="${stocks.specificationId}" style="width:100%;" data-options="required:true"/>
		    </div>
		    <div style="margin-bottom:20px">
		        <label class="label-top">商品颜色</label>
		        <input class="easyui-textbox theme-textbox-radius" type="text" id="colorsId" name="colorsId" value="${stocks.colorsId }" style="width:100%;" data-options="required:true">
		    </div>
		    <div style="margin-bottom:20px">
		        <label class="label-top">商品库存</label>
		        <input class="easyui-textbox theme-textbox-radius" type="text" id="remainNum" name="remainNum" value="${stocks.remainNum }" style="width:100%;" data-options="required:true">
		    </div>
		    <div style="margin-bottom:20px">
		        <label class="label-top">已售数量</label>
		        <input class="easyui-textbox theme-textbox-radius" type="text" id="selledNum" name="selledNum" value="${stocks.selledNum }" style="width:100%;" data-options="required:true">
		    </div>
		    <div>
		        <a href="javascript:void(0);" class="easyui-linkbutton button-default" iconCls="icon-ok" style="width:100%;height:32px" onclick="doSubmit();">提交</a>
		    </div>
		    <div style="height:20px">
		    	&nbsp;
		    </div>
		</form>
</div>
<script language="javascript">

</script>
</body>
</html>

