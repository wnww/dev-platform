<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>
	<%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/import.jsp" %>
<script type="text/javascript">
		
		var roleId = "${roleId}";
		var isClick = false;
		
		$(function(){
			$('#dataPageList').datagrid({
				title:'',
				iconCls:'icon-ok',
				url:'${ctx }/authority/getSelectAuthorityDatas.do?filter_roleId='+roleId+'&t='+new Date(),
				nowrap: false,
				striped: true,
				collapsible:false,				
				fitColumns: true,
				pagination:true,
				singleSelect:false,
				checkOnSelect:false,
				rownumbers:true,
				remoteSort: false,
				pageList:[3,5,10,50],
				idField:'authId',
				columns:[[
					{field:'ck',checkbox:true,width:'5%'},
					{field:'authName',title:'权限名称',width:'20%',sortable:true},
					{field:'authType',title:'权限类型',width:'15%',sortable:true},
					{field:'authMark',title:'权限路径',width:'48%'}
				]],
				onLoadSuccess:function(data){
					isClick=false;
				    var rowData = data.rows;
				    $.each(rowData, function (idx, val) {
				    	if (val.roleId!=null && val.roleId!="") {
					    	$("#dataPageList").datagrid("checkRow", idx);
				    	}
			    	});
				    isClick=true;
				},
				onSelect:function(rowIndex, rowData){
					 $('#dataPageList').datagrid('unselectRow',rowIndex);
				},
				onCheck:function(rowIndex, rowData){
					saveEntity(rowData.authId);
				},
				onUncheck:function(rowIndex, rowData){
					deleteEntity(rowData.authId);
				},
				onUnselect:function(rowIndex, rowData){
					//deleteEntity(rowData.roleId);
				},
				onDblClickRow:function(){
					//dataItemTree();
				}
			});		
		});
		
		
		function saveEntity(authId){
			if(!isClick){
				return;
			}
			$.ajax({
				type: "post",
				url: "${ctx}/roleAuth/saveRoleAuth.do?roleId="+roleId+"&authId="+authId,
				dataType: "json",
				success: function(data){
					//var result = jQuery.parseJSON(data);
  						if(data.flag=='T'){
						$.messager.alert('结果', '操作成功', 'info');	
  						}else{
  							$.messager.alert('结果', '操作失败，'+data.msg, 'error');
  							return false;
  						}
				},
				error:function(messg)  { 
		       	    $.messager.alert('错误提示', '操作失败:'+messg.responseText, 'error');
		       } 
			});
		}
		
		//删除，物理删除
		function deleteEntity(authId){					
			$.ajax({
				type: "post",
				url: "${ctx}/roleAuth/delRoleAuth.do?authId="+authId+"&roleId="+roleId,
				dataType: "json",
				success: function(data){
					//var result = jQuery.parseJSON(data);
  						if(data.flag=='T'){
							$.messager.alert('结果', '操作成功', 'info');	
  						}else{
  							$.messager.alert('结果', '操作失败,'+data.msg, 'error');	
  							return false;
  						}
				},
				error:function(messg)  { 
		       	    $.messager.alert('错误提示', '操作失败:'+messg.responseText, 'error');
		       } 
			});
		}
		
		
	</script>
</script>
</head>
<body>

	<table id="dataPageList"></table>

	<!-- 添加窗口 -->
	<div id="saveDiv" class="easyui-window" title="" style="padding:5px;width: 650px;height:450px;"
    	iconCls="icon-add" closed="true" maximizable="false" minimizable="false" collapsible="false">
   		<iframe frameborder="0"  id="saveFrame" height="100%" width="100%" scrolling="No" frameborder="0" ></iframe>
    </div>
    
</body>
</html>