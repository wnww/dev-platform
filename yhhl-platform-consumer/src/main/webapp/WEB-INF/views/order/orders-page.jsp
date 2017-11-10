<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>
	<%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/import.jsp" %>
    <script type="text/javascript" src="${ctx}/js/WdatePicker/WdatePicker.js?v=1.0.0"></script>
    <script type="text/javascript" src="${ctx}/js/money.js?v=1.0.0"></script>
    <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/util.js?v=1.0.0"></script>
<script type="text/javascript">

		$(function(){
			$('#dataPageList').datagrid({
			    title:'订单列表',
				iconCls:'icon-ok',
				url:'${ctx}/sysManage/orders/getOrdersDatas.do?t='+new Date(),
				nowrap: false,
				striped: true,
				collapsible:true,
				fitColumns: true,
				pagination:true,
				singleSelect:true,
				rownumbers:true,
				remoteSort: false,
				pageList:[3,5,10,50],
				idField:'orderId',
				columns:[[
					{field:'orderId',title:'订单编号',width:130,sortable:true},
					{field:'ownerRealName',title:'用户姓名',width:100,sortable:true},
					{field:'ownerMobile',title:'联系电话',width:100,sortable:true},
					{field:'orderAmount',title:'商品总价',width:60,formatter:function(value,rec){
						return moneyFormatterNoY(value/100);
					}},
					{field:'expressFee',title:'快递费用',width:60,formatter:function(value,rec){
						return moneyFormatterNoY(value/100);
					}},
					{field:'totalAmount',title:'订单总额',width:70,formatter:function(value,rec){
						return moneyFormatterNoY((rec.orderAmount+rec.expressFee)/100);
					}},
					{field:'statusValue',title:'订单状态',width:70,sortable:true},
					{field:'createTime',title:'创建时间',width:100,sortable:true,
						formatter:function(value,rec){
							return dateFormat(value);
						}
					},
					{field:'button',title:'操作',width:100,align:'center',
						formatter:function(value,rec){
							var btn = '<a class="button-edit button-default l-btn l-btn-small" onclick="showProdItemData(\''+rec.orderId+'\')" href="javascript:void(0)">';
							btn += '<span class="l-btn-left">';
							btn += '<span class="l-btn-text">添加订单商品</span>';
							btn += '</span>';
							btn += '</a>';
							return btn;
						}
					}
				]],
				toolbar:[{
					text:'增加',
					iconCls:'icon-add',
					handler:function(){
						saveEntity();
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
					text:'增加快递单号',
					iconCls:'icon-edit',
					handler:function(){
						addExpressCode();
					}
				},'-',{
					text:'物流查询',
					iconCls:'icon-edit',
					handler:function(){
						showExpressInfo();
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
					//dataItemTree();
				},
				onLoadSuccess : function(data){
					if(data.flag == 1){
						// 获取订单总金额，订单总售出商品数量，订单总数量
						getTotalOrderAmountAndSellNum();
					}else if (data.flag == 2) {
						$.messager.alert('结果', data.msg, 'error');
					} else if (data.flag == 3){
						$.messager.alert('结果', '您还未登录，请先登录！', 'error', function(){
							document.location.href="${ctx}/sysManage/index.do";
						});
					} else if(data.flag!= 1){
						$.messager.alert('结果', '操作失败，请重试', 'error');
					}
					
				}
			});
			// 状态值
			// 获取规格选项列表并回显
			$('#filter_status').combobox({
			    url:'${ctx}/sysManage/orders/getOrderStatus.do',
			    valueField:'status',
			    textField:'value'
			});
		});
		
		function getTotalOrderAmountAndSellNum(){
			var filter_startDate = $('#filter_startDate').val();
			var filter_endDate = $('#filter_endDate').val();
			var filter_status = $('#filter_status').val();
			var total_url = "${ctx}/sysManage/orders/getOrderTotalAmountAndTotalSellNum.do?filter_startDate="+filter_startDate+"&filter_endDate="+filter_endDate+"&t="+new Date();
			if(filter_status!=0){
				total_url = total_url+"&filter_status="+filter_status;
			}
			var filter_ownerRealName = $('#filter_ownerRealName').val();
			if(filter_ownerRealName!=""){
				total_url = total_url+"&filter_ownerRealName="+filter_ownerRealName;
			}
			$.ajax({
				type: "post",
				url: total_url,
				dataType: "json",
				success: function(data){
					if(data.flag==1 && data.data){
						if($("#showTotal")){
							$("#showTotal").remove();
						}
						//var showContent = $("panel-tool").eq(1).html();
						var total = "<span id='showTotal' style='color:#0000FF;text-align=center;padding-right:30px;font-weight:bold;'>订单总金额：<span style='font-size:18px'>"+moneyFormatterNoY(data.data.orderAmount/100)+"</span> 元 | 订单总购买量：<span style='font-size:18px'>"+data.data.prodNum+"</span></span>";
						$(".panel-tool").eq(1).prepend(total);
					}else if(data.flag==2){
						$.messager.alert('结果', data.msg, 'info');	
					}else if (data.flag == 3) {
						$.messager.alert('结果', '您还未登录，请先登录！', 'error', function(){
							document.location.href="${ctx}/sysManage/index.do";
						});
					}else{
						//$.messager.alert('结果', '操作失败，请重试', 'error');	
						if($("#showTotal")){
							$("#showTotal").remove();
						}
					}
				},
				error:function(messg)  { 
		       	    $.messager.alert('错误提示', '操作失败:'+messg.responseText, 'error');
		       } 
			});
		}
		
		function saveEntity(){
			$('#saveFrame').html('');			
			var url = '${ctx}/sysManage/orders/initAddOrders.do';				
			$('#saveFrame').attr("title",'');
			$('#saveFrame').attr("src",url);
			$("#saveDiv").window({title:"新增-订单",iconCls:'icon-add',height:"550px",width:"650px",left:"50px",top:"30px"});
			$('#saveDiv').window('open');
		}
		
		// 进入修改页面
		function editEntity(){
			var node = getSelected();		
			if (node){	
				var url = '${ctx}/sysManage/orders/initAddOrders.do?id='+node.orderId;
				//$('#saveFrame').attr("title","修改"+node.orderId+"-");
				$('#saveFrame').attr("src",url);
				$("#saveDiv").window({title:"修改订单-"+node.orderId+"-"+node.ownerRealName,iconCls:'icon-edit',height:"550px",width:"650px",left:"50px",top:"30px"});
				$('#saveDiv').window('open');
			}
		}
		
		//删除，物理删除
		function deleteEntity(){					
			var node = getSelected();	
			if(node){
		    	$.messager.confirm('确认','您确定要删除：<font color=red>'+node.orderId+'</font> ？',function(r){
		        	if(r){
						$.ajax({
							type: "post",
							url: "${ctx}/sysManage/orders/delOrders.do?id="+node.orderId,
							dataType: "json",
							success: function(data){
	    						if(data.flag==1){
	    							$.messager.confirm('提交结果', '操作成功', function(){
	    				    			winReload();// 刷新列表
	    							});
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
        queryParams.filter_ownerRealName = $('#filter_ownerRealName').val();
        console.log($('#filter_status').combobox('getValue'));
    	queryParams.filter_status = $('#filter_status').combobox('getValue');
        queryParams.filter_startDate = $('#filter_startDate').val();
        queryParams.filter_endDate = $('#filter_endDate').val();
        $('#dataPageList').datagrid("reload");
   }
   
   //清空查询条件   
    function clearForm(){   
      	$('#dataPageList'). datagrid('clearSelections');  
	    $('#queryForm')[0].reset();
	    $('#filter_status').combobox("setValue",0);
    }
   
   function addExpressCode(){
	   var node = getSelected();		
		if (node){	
			var url = '${ctx}/sysManage/express/initAddExpress.do?orderId='+node.orderId;
			//$('#saveFrame').attr("title","修改"+node.orderId+"-");
			$('#saveFrame').attr("src",url);
			$("#saveDiv").window({title:"维护快递信息-"+node.orderId+"-"+node.ownerRealName,iconCls:'icon-edit',height:"300px",width:"400px",left:"50px",top:"30px"});
			$('#saveDiv').window('open');
		}
   }
   // 显示订单物流信息
   function showExpressInfo(){
	    var node = getSelected();		
		if (node){	
			var url = '${ctx}/sysManage/express/index.do?orderId='+node.orderId;
			//$('#saveFrame').attr("title","修改"+node.orderId+"-");
			$('#saveFrame').attr("src",url);
			$("#saveDiv").window({title:"订单号："+node.orderId+" 的物流信息",iconCls:'icon-edit',height:"500px",width:"600px",left:"50px",top:"30px"});
			$('#saveDiv').window('open');
		}
   }
   
///////////////////////////////////////////////////////////////////////    
    //订单商品列表		
	function showProdItemData(orderId){
		$('#dataItemPageList').datagrid({
			title:'订单商品列表-订单号【'+orderId+'】',
			iconCls:'icon-ok',
			url:'${ctx}/sysManage/orderProducts/getOrderProductsDatas.do?filter_orderId='+orderId+'&t='+new Date(),
			nowrap: false,
			striped: true,
			collapsible:false,				
			fitColumns: true,
			pagination:true,
			singleSelect:true,
			rownumbers:true,
			remoteSort: false,
			pageList:[3,5,10,50],
			idField:'orderProdId',
			columns:[[
				{field:'prodId',title:'商品编号',width:100,sortable:true},
				{field:'prodName',title:'商品名称',width:100,sortable:true},
				{field:'unitPrice',title:'出售单价',width:100,formatter:function(unitPrice1,rowData,index){
					return moneyFormatterNoY(rowData.unitPrice/100);
				}},
				{field:'prodNum',title:'售出数量',width:100,sortable:true},
				{field:'chushouDanjia',title:'出售总价',width:100,formatter:function(unitPrice1,rowData,index){
					return moneyFormatterNoY(rowData.unitPrice/100*rowData.prodNum);
				}}
			]],
			toolbar:[{
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					saveOrderProduct(orderId);
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					deleteOrderProductEntity();
				}
			},'-',{
				text:'刷新',
				iconCls:'icon-reload',
				handler:function(){
					$('#dataItemPageList').datagrid('reload');
				}
			}
			],
			onDblClickRow:function(){
				
			},
			onLoadSuccess : function(data){
				if (data.flag == 2) {
					$.messager.alert('结果', data.msg, 'error');
				} else if (data.flag == 3){
					$.messager.alert('结果', '您还未登录，请先登录！', 'error', function(){
						document.location.href="${ctx}/sysManage/index.do";
					});
				} else if(data.flag!= 1){
					$.messager.alert('结果', '操作失败，请重试', 'error');
				}
			}
		});
	}
	
	// 判断是否选中一条记录
	function getSelectedOrderProd(){
		var selected = $('#dataItemPageList').datagrid('getSelected');
		if (selected){
			return selected;
		}else{
			$.messager.alert('提示', '请选择要操作的数据', 'info');
		}
	}
	
	// 增加订单商品
	function saveOrderProduct(id){
		$('#saveFrame').html('');			
		var url = '${ctx}/sysManage/orderProducts/initAddOrderProducts.do?orderId='+id;				
		$('#saveFrame').attr("title",'');
		$('#saveFrame').attr("src",url);
		$("#saveDiv").window({title:"添加-订单商品",iconCls:'icon-add',height:"550px",width:"650px",left:"50px",top:"30px"});
		$('#saveDiv').window('open');
	}
	
	// 修改订单商品
	function editOrderProductEntity(){
		var node = getSelectedOrderProd();
		if(node){
			$('#saveFrame').html('');
			var url = '${ctx}/sysManage/orderProducts/initUpdateOrderProducts.do?id='+node.orderProdId;				
			$('#saveFrame').attr("title",'');
			$('#saveFrame').attr("src",url);
			$("#saveDiv").window({title:"添加-订单商品",iconCls:'icon-add',height:"550px",width:"650px",left:"50px",top:"30px"});
			$('#saveDiv').window('open');
		}
	}
	
	// 刷新列表
	function orderProductReload(){
		$('#dataItemPageList').datagrid('reload');
	}
	
	function deleteOrderProductEntity(){
		var node = getSelectedOrderProd();
		if(node){
	    	$.messager.confirm('确认','您确定要删除：<font color=red>'+node.prodName+'</font> ？',function(r){
	        	if(r){
					$.ajax({
						type: "post",
						url: "${ctx}/sysManage/orderProducts/delOrderProducts.do?id="+node.orderProdId,
						dataType: "json",
						success: function(data){
    						if(data.flag==1){
    							$.messager.confirm('提交结果', '操作成功', function(){
    								orderProductReload();// 刷新列表
    							});
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
	       });		             		
	    }	
	}
	
	
	</script>
</script>
</head>
<body>

	<div id="" class="easyui-panel" title="查询条件" collapsible="true" style="padding:5px;">
	    <form id="queryForm" name="queryForm">
		    <center style="line-height:22spx;padding:5px;">
			         姓名：
			       <span class="textbox easyui-fluid" style="width: 100px; height: 30px;">
			         <input type="text" id="filter_ownerRealName" name="filter_ownerRealName" size="20" class="textbox-text validatebox-text textbox-prompt" style="margin: 0px 0px 0px 0px; padding-top: 0px; padding-bottom: 0px; padding-left:3px; height: 30px; line-height: 30px; width: 100px;" autocomplete="off" />
			      </span>&nbsp;&nbsp;&nbsp;&nbsp;
			         状态：
			       <span class="textbox easyui-fluid" style="width: 100px; height: 30px;">
			         <input class="easyui-combobox " type="text" id="filter_status" name="filter_status" style="width:100%;"/>
			      </span>&nbsp;&nbsp;&nbsp;&nbsp;
			   开始日期： <span class="textbox easyui-fluid"
					style="width: 120px; height: 30px;"><input type="text" id="filter_startDate" name="filter_startDate"
					class="textbox-text validatebox-text textbox-prompt Wdate"
					style="margin: 0px 0px 0px 0px; padding-top: 0px; padding-bottom: 0px; padding-left: 3px; height: 30px; line-height: 30px; width: 100px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></span>
				&nbsp;&nbsp;结束日期： <span class="textbox easyui-fluid"
					style="width: 120px; height: 30px;"><input
					type="text" id="filter_endDate" name="filter_endDate" class="textbox-text validatebox-text textbox-prompt Wdate"
					style="margin: 0px 0px 0px 0px; padding-top: 0px; padding-bottom: 0px; padding-left: 3px; height: 30px; line-height: 30px; width: 100px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></span>
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
    <!-- 订单商品列表 -->
    <table id="dataItemPageList"></table>
</body>
</html>