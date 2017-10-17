<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>
	<%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/import.jsp" %>
<script type="text/javascript">
		$(function(){
			$('#dataPageList').treegrid({
				title:'列表',
				iconCls:'tree-file',
				url:'${ctx}/sysManage/category/getCategoryTree.do?filter_parentId=0&t='+new Date(),
				loadMsg:'数据载入中，请稍后……',
				rownumbers: true,
				animate:true,
				nowrap: false,
				collapsible:true,				
				fitColumns: true,
				idField: 'catId',
                treeField:'catName',
				columns:[[
					{field:'catName',title:'类别名称',width:"50%"},
					{field:'wbs',title:'WBS码',width:"45%"}
				]],
				toolbar:[{
					text:'增加',
					iconCls:'icon-add',
					handler:function(){
						saveEntity();
					}
				},'-',{
					text:'增加子类别',
					iconCls:'icon-add',
					handler:function(){
						saveSubEntity();
					}
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						deleteEntity();
					}
				},'-',{
					text:'修改',
					iconCls:'icon-edit',
					handler:function(){
						editEntity();
					}
				},'-',{
					text:'刷新',
					iconCls:'icon-reload',
					handler:function(){
						winReload();
					}
				}
				],
				onBeforeLoad:function(row,param){
					if (row){
						$(this).treegrid('options').url = '${ctx}/sysManage/category/getCategoryTree.do?filter_parentId='+row.catId;
					} 
				}
			});		
		});
		
		function saveEntity(){
			$('#saveFrame').html('');			
			var url = '${ctx}/sysManage/category/initAddCategory.do';				
			$('#saveFrame').attr("title",'');
			$('#saveFrame').attr("src",url);
			$("#saveDiv").window({title:"新增-类别",iconCls:'icon-add',height:"550px",width:"650px",left:"50px",top:"30px"});
			$('#saveDiv').window('open');
		}
		
		function saveSubEntity(){
			var node = getSelected();		
			if (node){	
				$('#saveFrame').html('');			
				var url = '${ctx}/sysManage/category/initAddCategory.do?parentId='+node.catId;				
				$('#saveFrame').attr("title",'');
				$('#saveFrame').attr("src",url);
				$("#saveDiv").window({title:"新增-子类别",iconCls:'icon-add',height:"550px",width:"650px",left:"50px",top:"30px"});
				$('#saveDiv').window('open');
			}
		}
		
		// 进入修改页面
		function editEntity(){
			var node = getSelected();		
			if (node){	
				var url = '${ctx}/sysManage/category/initAddCategory.do?id='+node.catId;
				$('#saveFrame').attr("title","修改"+node.catName);
				$('#saveFrame').attr("src",url);
				$("#saveDiv").window({title:"修改-"+node.catName,iconCls:'icon-edit',height:"550px",width:"650px",left:"50px",top:"30px"});
				$('#saveDiv').window('open');
			}
		}
		
		//删除，物理删除
		function deleteEntity(){					
			var node = getSelected();	
			if(node){
		    	$.messager.confirm('确认','您确定要删除：<font color=red>'+node.catName+'</font> ？',function(r){
		        	if(r){
						$.ajax({
							type: "post",
							url: "${ctx}/sysManage/category/delCategory.do?id="+node.catId,
							dataType: "json",
							success: function(data){
	    						if(data.flag==1){
	    							$.messager.confirm('提交结果', '操作成功', function(){
	    				    			winReload();// 刷新列表
	    							});
	    						}else if(data.flag==2 || data.flag==0){
	    							$.messager.alert('结果', data.msg, 'error');	
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
		       });		             		
		    }	
		}
		
		// 判断是否选中一条记录
		function getSelected(){
			var selected = $('#dataPageList').treegrid('getSelected');
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
			$("#dataPageList").treegrid('options').url = '${ctx}/sysManage/category/getCategoryTree.do?filter_parentId=0';
			$('#dataPageList').treegrid('reload');
		}

	//查询
    function searchList(){					
	    	var queryParams = $('#dataPageList').treegrid('options').queryParams;
			$('#dataPageList').treegrid('options').pageNumber = 1;
			$('#dataPageList').treegrid('getPager').pagination({pageNumber: 1});
	    	//查询条件放到queryParams中：格式filter_params       
	        queryParams.filter_dictTypeName = $('#filter_dictTypeName').val();
	        $('#dataPageList').treegrid("reload");
   }
   
   //清空查询条件   
    function clearForm(){   
      	$('#dataPageList'). treegrid('clearSelections');  
	    $('#queryForm')[0].reset();  
    }
   
	</script>
</script>
</head>
<body>

	<div id="" class="easyui-treegrid" title="查询条件" collapsible="true" style="padding:5px;">
	    <form id="queryForm" name="queryForm">
		    <center style="line-height:22spx;padding:5px;">
			         字典类型：
			       <span class="textbox easyui-fluid" style="width: 300px; height: 30px;">
			         <input type="text" id="filter_dictTypeName" name="filter_dictTypeName" size="20" class="textbox-text validatebox-text textbox-prompt" style="margin: 0px 0px 0px 0px; padding-top: 0px; padding-bottom: 0px; padding-left:3px; height: 30px; line-height: 30px; width: 300px;" autocomplete="off" />
			      </span>
			    <a href="javascript:void(0);" onclick="searchList();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			    <a href="javascript:void(0);" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
		    </center>
		</form>
	</div>

	<table id="dataPageList"></table>

	<!-- 添加窗口 -->
	<div id="saveDiv" class="easyui-window" title="用户信息" modal="false" style="padding:5px;width: 500px;height:230px;"
    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
   		<iframe frameborder="0"  id="saveFrame" height="100%" width="100%" scrolling="No" frameborder="0" ></iframe>
    </div>
    <!-- 商品列表 -->
    <table id="dataItemPageList"></table>
</body>
</html>