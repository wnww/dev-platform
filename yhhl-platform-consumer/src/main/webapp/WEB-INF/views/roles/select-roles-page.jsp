<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>
	<%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/import.jsp" %>
<script type="text/javascript">
	
	var userId = "${userId}";
	var isClick = false;

		$(function(){
			$('#dataPageList').datagrid({
				title:'角色列表',
				iconCls:'icon-ok',
				url:'${ctx}/sysManage/roles/getSelectRolesDatas.do?filter_userId='+userId+'&tm='+new Date(),
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
				pageSize:3,
				idField:'roleId',
				columns:[[
				    {field:'ck',checkbox:true},
					{field:'roleId',title:'角色编号',width:100,sortable:true},
					{field:'roleName',title:'角色名称',width:100,sortable:true},
					{field:'remark',title:'备注',width:100}
				]],
				onLoadSuccess:function(data){
					if (data.flag == 2) {
						$.messager.alert('结果', data.msg, 'error');
						return;
					} else if (data.flag == 3){
						$.messager.alert('结果', '您还未登录，请先登录！', 'error', function(){
							document.location.href="${ctx}/sysManage/index.do";
							return;
						});
						return;
					} else if(data.flag!= 1){
						$.messager.alert('结果', '操作失败，请重试', 'error');
						return;
					}
					console.log("data.rows"+data.rows);
					isClick=false;
				    var rowData = data.rows;
				    
				    $.each(rowData, function (idx, val) {
				    	console.log("val.userId="+val.userId);
				    	if (val.userId!=null && val.userId!="") {
				    		
					    	$("#dataPageList").datagrid("checkRow", idx);
				    	}
			    	});
				    isClick=true;
				},
				onSelect:function(rowIndex, rowData){
					 $('#dataPageList').datagrid('unselectRow',rowIndex);
				},
				onCheck:function(rowIndex, rowData){
					
					saveEntity(rowData.roleId);
				},
				onUncheck:function(rowIndex, rowData){
					deleteEntity(rowData.roleId);
				},
				onUnselect:function(rowIndex, rowData){
					//deleteEntity(rowData.roleId);
				},
				onDblClickRow:function(){
					//dataItemTree();
				}
			});
			
		});
		
		// 增加角色
		function saveEntity(roleId){
			if(!isClick){
				return;
			}
			$.ajax({
				type: "post",
				url: "${ctx}/sysManage/roleUser/saveRoleUser.do?roleId="+roleId+"&userId="+userId,
				dataType: "json",
				success: function(data){
					//var result = jQuery.parseJSON(data);
  						if(data.flag==1){
							$.messager.alert('结果', '操作成功', 'info');	
  						}else if(data.flag==2){
							$.messager.alert('结果', data.msg, 'info');	
						}else if (data.flag == 3) {
							$.messager.alert('结果', '您还未登录，请先登录！', 'error', function(){
								document.location.href="${ctx}/sysManage/index.do";
							});
						}else{
							$.messager.alert('结果', '操作失败，请重试', 'error');	
						}
				},
				error:function(messg)  { 
		       	    $.messager.alert('错误提示', '操作失败:'+messg.responseText, 'error');
		       } 
			});
		}
		
		
		//删除，物理删除
		function deleteEntity(roleId){
			
			$.ajax({
				type: "post",
				url: "${ctx}/sysManage/roleUser/delRoleUser.do?userId="+userId+"&roleId="+roleId,
				dataType: "json",
				success: function(data){
					//var result = jQuery.parseJSON(data);
  						if(data.flag==1){
							$.messager.alert('结果', '操作成功', 'info');	
  						}else if(data.flag==2){
							$.messager.alert('结果', data.msg, 'info');	
						}else if (data.flag == 3) {
							$.messager.alert('结果', '您还未登录，请先登录！', 'error', function(){
								document.location.href="${ctx}/sysManage/index.do";
							});
						}else{
							$.messager.alert('结果', '操作失败，请重试', 'error');	
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
	<div id="saveDiv" class="easyui-window" title="模板维护" style="padding:5px;width: 500px;height:400px;"
    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
   		<iframe frameborder="0"  id="saveFrame" height="100%" width="100%" scrolling="No" frameborder="0" ></iframe>
    </div>
</body>
</html>