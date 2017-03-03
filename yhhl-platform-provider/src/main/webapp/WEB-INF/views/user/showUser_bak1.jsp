<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>
	<%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/import.jsp" %>
<script type="text/javascript">

	function addUser(){
		window.location.href="${ctx}/user/initAddUser.do";
	}
	
		$(function(){
				
		});
		
		function refresh(){
			var url = '${ctx}/template/refresh.action';
			$.ajax({
				type: "post",
				data: "",
				dataType: "",
				url: url,
				success: function(data, textStatus){
					alert("OK");
				},
				error: function(messg){
					alert("error");
				}
			});
		}
		
		function saveStorageType(){
			$('#saveFrame').html('');			
			var url = '${ctx}/user/initAddUser.do';				
			$('#saveFrame').attr("title",'');
			$('#saveFrame').attr("src",url);
			$('#saveDiv').window('open');
		}
		
		// 进入修改页面
		function editStorage(){
			var node = getSelected();		
			if (node){	
				var url = '${ctx}/user/initAddUser.do?id='+node.id;
				$('#saveFrame').attr("title","修改"+node.name);
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
							url: "${ctx}/user/delUser.do?id="+node.id,
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

	<div id="page-inner">


				<div class="row">
					<div class="col-md-12">
						<h1 class="page-header">
							Dashboard <small>Summary of your App</small>
						</h1>
					</div>
				</div>
				<!-- /. ROW  -->

				<div class="row">
					<div class="col-md-3 col-sm-12 col-xs-12">
						<div
							class="panel panel-primary text-center no-boder bg-color-green">
							<div class="panel-body">
								<i class="fa fa-bar-chart-o fa-5x"></i>
								<h3>8,457</h3>
							</div>
							<div class="panel-footer back-footer-green">Daily Visits</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-12 col-xs-12">
						<div
							class="panel panel-primary text-center no-boder bg-color-blue">
							<div class="panel-body">
								<i class="fa fa-shopping-cart fa-5x"></i>
								<h3>52,160</h3>
							</div>
							<div class="panel-footer back-footer-blue">Sales</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-12 col-xs-12">
						<div class="panel panel-primary text-center no-boder bg-color-red">
							<div class="panel-body">
								<i class="fa fa fa-comments fa-5x"></i>
								<h3>15,823</h3>
							</div>
							<div class="panel-footer back-footer-red">Comments</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-12 col-xs-12">
						<div
							class="panel panel-primary text-center no-boder bg-color-brown">
							<div class="panel-body">
								<i class="fa fa-users fa-5x"></i>
								<h3>36,752</h3>
							</div>
							<div class="panel-footer back-footer-brown">No. of Visits</div>
						</div>
					</div>
				</div>


				<div class="row">


					<div class="col-md-9 col-sm-12 col-xs-12">
						<div class="panel panel-default">
							<div class="panel-heading">Bar Chart Example</div>
							<div class="panel-body">
								<div id="morris-bar-chart"></div>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-12 col-xs-12">
						<div class="panel panel-default">
							<div class="panel-heading">Donut Chart Example</div>
							<div class="panel-body">
								<div id="morris-donut-chart"></div>
							</div>
						</div>
					</div>

				</div>
				<!-- /. ROW  -->

				<div class="row">
					<div class="col-md-4 col-sm-12 col-xs-12">
						<div class="panel panel-default">
							<div class="panel-heading">Tasks Panel</div>
							<div class="panel-body">
								<div class="list-group">

									<a href="#" class="list-group-item"> <span class="badge">7
											minutes ago</span> <i class="fa fa-fw fa-comment"></i> Commented on
										a post
									</a> <a href="#" class="list-group-item"> <span class="badge">16
											minutes ago</span> <i class="fa fa-fw fa-truck"></i> Order 392
										shipped
									</a> <a href="#" class="list-group-item"> <span class="badge">36
											minutes ago</span> <i class="fa fa-fw fa-globe"></i> Invoice 653 has
										paid
									</a> <a href="#" class="list-group-item"> <span class="badge">1
											hour ago</span> <i class="fa fa-fw fa-user"></i> A new user has been
										added
									</a> <a href="#" class="list-group-item"> <span class="badge">1.23
											hour ago</span> <i class="fa fa-fw fa-user"></i> A new user has
										added
									</a> <a href="#" class="list-group-item"> <span class="badge">yesterday</span>
										<i class="fa fa-fw fa-globe"></i> Saved the world
									</a>
								</div>
								<div class="text-right">
									<a href="#">More Tasks <i class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-8 col-sm-12 col-xs-12">

						<div class="panel panel-default">
							<div class="panel-heading">Responsive Table Example</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>S No.</th>
												<th>First Name</th>
												<th>Last Name</th>
												<th>User Name</th>
												<th>Email ID.</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>John</td>
												<td>Doe</td>
												<td>John15482</td>
												<td>name@site.com</td>
											</tr>
											<tr>
												<td>2</td>
												<td>Kimsila</td>
												<td>Marriye</td>
												<td>Kim1425</td>
												<td>name@site.com</td>
											</tr>
											<tr>
												<td>3</td>
												<td>Rossye</td>
												<td>Nermal</td>
												<td>Rossy1245</td>
												<td>name@site.com</td>
											</tr>
											<tr>
												<td>4</td>
												<td>Richard</td>
												<td>Orieal</td>
												<td>Rich5685</td>
												<td>name@site.com</td>
											</tr>
											<tr>
												<td>5</td>
												<td>Jacob</td>
												<td>Hielsar</td>
												<td>Jac4587</td>
												<td>name@site.com</td>
											</tr>
											<tr>
												<td>6</td>
												<td>Wrapel</td>
												<td>Dere</td>
												<td>Wrap4585</td>
												<td>name@site.com</td>
											</tr>

										</tbody>
									</table>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
</body>
</html>