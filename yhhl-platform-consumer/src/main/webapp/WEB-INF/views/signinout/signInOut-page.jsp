<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>
	<%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/import.jsp" %>
<script type="text/javascript">

	function addUser(){
		window.location.href="${ctx}/sysManage/signInOut/initAddSignInOut.do";
	}
	
		$(function(){
			$('#dataPageList').datagrid({
				title:'模板列表',
				iconCls:'icon-ok',
				url:'${ctx}/sysManage/signInOut/getSignInOutDatas.do?t='+new Date(),
				nowrap: false,
				striped: true,
				collapsible:false,				
				fitColumns: true,
				pagination:true,
				singleSelect:true,
				rownumbers:true,
				remoteSort: false,
				pageList:[3,5,10,50],
				idField:'id',
				columns:[[
					{field:'signType',title:'类型',width:100,sortable:true},
					{field:'signDate',title:'日期',width:100,sortable:true}
				]],
				toolbar:[{
					text:'增加',
					iconCls:'icon-add',
					handler:function(){
						saveStorageType();
					}
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						deleteCate();
					}
				},'-',{
					text:'修改',
					iconCls:'icon-edit',
					handler:function(){
						editStorage();
					}
				},'-',{
					text:'刷新',
					iconCls:'icon-reload',
					handler:function(){
						$('#dataPageList').datagrid('reload');
					}
				}
				],
				onDblClickRow:function(){
					dataItemTree();
				}
			});		
		});
		
		function saveStorageType(){
			$('#saveFrame').html('');			
			var url = '${ctx}/sysManage/signInOut/initAddSignInOut.do';				
			$('#saveFrame').attr("title",'');
			$('#saveFrame').attr("src",url);
			$('#saveDiv').window('open');
		}
		
		// 进入修改页面
		function editStorage(){
			var node = getSelected();		
			if (node){	
				var url = '${ctx}/sysManage/signInOut/initAddSignInOut.do?id='+node.signId;
				$('#saveFrame').attr("title","修改"+node.signType);
				$('#saveFrame').attr("src",url);
				$('#saveDiv').window('open');
			}
		}
		
		//删除，物理删除
		function deleteCate(){					
			var node = getSelected();	
			if(node){
		    	$.messager.confirm('确认','您确定要删除:<font color=red>'+node.name+'</font> ？',function(r){
		        	if(r){
						$.ajax({
							type: "post",
							url: "${ctx}/sysManage/signInOut/delSignInOut.do?id="+node.signId,
							dataType: "json",
							success: function(data){
								var result = jQuery.parseJSON(data);
	    						if(data.flag=='T'){
									$.messager.alert('结果', '操作成功', 'info');	
								    winReload();
	    						}else if(data.flag=='H'){
	    							$.messager.alert('结果', result.msg, 'info');	
	    						}else{
	    							$.messager.alert('结果', '操作失败，请重试', 'error');	
	    						}
							},
							error:function(messg)  { 
					       	    $.messager.alert('错误提示', '操作失败:'+messg.responseText, 'error');
					       } 
						});
		          	}
		       });		             		
		    }	
		}
		
		// 判断是否选中一条记录
		function getSelected(){
			var selected = $('#dataPageList').datagrid('getSelected');
			if (selected){
				return selected;
			}else{
				$.messager.alert('提示', '请选择要操作的数据', 'info');
			}
		}
		
		// 点击取消按钮，关闭添加窗口
		function colseAdd(){
			$('#saveDiv').window('close');
		}
		
		// 刷新列表
		function winReload(){
			$('#dataPageList').datagrid('reload');
		}

	//查询
    function searchList(){					
	    	var queryParams = $('#dataPageList').datagrid('options').queryParams;
			$('#dataPageList').datagrid('options').pageNumber = 1;
			$('#dataPageList').datagrid('getPager').pagination({pageNumber: 1});
	    	//查询条件放到queryParams中：格式filter_params       
	        queryParams.filter_name = $('#filter_name').val();
	        $('#dataPageList').datagrid("reload");
   }
   
   //清空查询条件   
    function clearForm(){   
      	$('#dataPageList'). datagrid('clearSelections');  
	    $('#queryForm')[0].reset();  
    }
	</script>
</script>
</head>
<body>

	<div id="" class="easyui-panel" title="查询条件" collapsible="true" style="padding:5px;">
	    <form id="queryForm" name="queryForm">
		    <center style="line-height:22spx;padding:5px;">
			         姓名：<input type="text" id="filter_name" name="filter_name" size="20" />
			    <a href="javascript:void(0);" onclick="searchList();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			    <a href="javascript:void(0);" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search">清空</a>
		    </center>
		</form>
	</div>

	<table id="dataPageList"></table>

	<!-- 添加窗口 -->
	<div id="saveDiv" class="easyui-window" title="模板维护" style="padding:5px;width: 350px;height:230px;"
    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
   		<iframe frameborder="0"  id="saveFrame" height="100%" width="100%" scrolling="No" frameborder="0" ></iframe>
    </div>

</body>
</html>