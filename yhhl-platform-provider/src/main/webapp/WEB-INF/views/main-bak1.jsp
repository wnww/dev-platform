<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Free Bootstrap Admin Template : Dream</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/import.jsp"%>
<style type="text/css">
.noActive{
	border-left: 1px;
	border-top: 1px;
	border-right: 1px;
	border-color: #ff0000;
	background:#bbbbbb;
	border-radius:2px;
}
</style>
<script language="javascript">
function addTab(id,title, url,refresh){
	var maxWindow = 1;
    var tabslength = $('#tabs').children("li").length;
    if (tabslength < maxWindow) {
        if ($("#a"+id).length>0) {
        	if(refresh=="Y"){
	            if(confirm('确定要刷新标签页：' + title + ' 吗?')){
	               	$('#tabs').children("li").each(function(){
		                   	if($(this).attr("id")=="a"+id){
		                   		$(this).removeClass("noActive");
		                   		$(this).addClass("active");
		                   	}else{
		                   		$(this).removeClass("active");
		                   		$(this).addClass("noActive");
		                   	}
	                   });
	               	$('#tabContents').children("div").each(function(){
	                   	if($(this).attr("id")==id){
	                   		$(this).remove();
	                   		var content = '<div class="tab-pane fade active in" id="'+id+'"><iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:700px;"></iframe></div>';
	                   		$("#tabContents").append(content);
	                        
	                        $('#tabContents').children("div").each(function(){
	                        	if($(this).attr("id")!=id){
	                        		$(this).removeClass("tab-pane fade active in");
	                           		$(this).addClass("tab-pane fade");
	                        	}else{
	                        		$(this).removeClass("tab-pane fade");
	                           		$(this).addClass("tab-pane fade active in");
	                        	}
	                        });
	                   	}else{
	                   		$(this).removeClass("tab-pane fade active in");
	                   		$(this).addClass("tab-pane fade");
	                   	}
	                   });
	            };
        	}else{
        		$('#tabs').children("li").each(function(){
                	if($(this).attr("class")=="active"){
                		$(this).attr("class","noActive");
                	}
                });
        		$('#tabContents').children("div").each(function(){
                	if($(this).attr("id")!=id){
                		$(this).removeClass("tab-pane fade active in");
                   		$(this).addClass("tab-pane fade");
                	}else{
                		$(this).removeClass("tab-pane fade");
                   		$(this).addClass("tab-pane fade active in");
                	}
                });
        	}
            //$('#main').tabs('close',title);
        }else {
        	$('#tabs').children("li").each(function(){
            	if($(this).attr("class")=="active"){
            		$(this).attr("class","noActive");
            	}
            });
        	var tabs = '<li class="active" id="a'+id+'" onclick="addTab(\''+id+'\',\''+title+'\',\''+url+'\',\'N\');"><a href="#" data-toggle="tab" >'+title+'</a></li>';
            var content = '<div class="tab-pane fade active in" id="'+id+'"><iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:700px;"></iframe></div>';
            $('#tabs').append(tabs);
            $("#tabContents").append(content);
            
            $('#tabContents').children("div").each(function(){
            	if($(this).attr("id")!=id){
            		$(this).removeClass("tab-pane fade active in");
               		$(this).addClass("tab-pane fade");
            	}else{
            		$(this).removeClass("tab-pane fade");
               		$(this).addClass("tab-pane fade active in");
            	}
            });
            
        }
    }else {
    	var msg = '<div id="msg" class="alert alert-warning"><a href="#" class="close" data-dismiss="alert"></a><strong>警告！</strong>您的网络连接有问题。';
    	$("#msg").alert();
    }
    
    $(".tabs-inner").dblclick(function(){
        var ti = $(this).children("span").text();
        if (ti != '简绍') {
            $('#main').tabs('close', ti);
        }
    });
    
    $(".tabs-inner").bind('contextmenu', function(e){
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
        var subtitle = $(this).children("span").text();
        $('#tabs').data("currtab", subtitle);
        return false;
    });
}
</script>
</head>

<body>
	<div id="wrapper">
		<!-- TOP Start  -->
		<%@ include file="/common/top.jsp"%>
		<!-- TOP End -->
		<!-- Left Start  -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">
					<li><a class="active-menu" href="javascript:void(0);" onclick="addTab('idUser','用户管理','${ctx}/user/index.do','Y')"><i
							class="fa fa-dashboard"></i> 测试</a></li>
							<li><a class="active-menu" href="javascript:void(0);" onclick="addTab('idPic','富文本编辑器','${ctx}/imageOpt/index.do','Y')"><i
							class="fa fa-dashboard"></i> 测试2</a></li>
					<li><a class="active-menu" href="index.html"><i
							class="fa fa-dashboard"></i> Dashboard</a></li>
					<li><a href="ui-elements.html"><i class="fa fa-desktop"></i>
							UI Elements</a></li>
					<li><a href="chart.html"><i class="fa fa-bar-chart-o"></i>
							Charts</a></li>
					<li><a href="tab-panel.html"><i class="fa fa-qrcode"></i>
							Tabs & Panels</a></li>

					<li><a href="table.html"><i class="fa fa-table"></i>
							Responsive Tables</a></li>
					<li><a href="form.html"><i class="fa fa-edit"></i> Forms </a>
					</li>


					<li><a href="#"><i class="fa fa-sitemap"></i> Multi-Level
							Dropdown<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="#">Second Level Link</a></li>
							<li><a href="#">Second Level Link</a></li>
							<li><a href="#">Second Level Link<span class="fa arrow"></span></a>
								<ul class="nav nav-third-level">
									<li><a href="#">Third Level Link</a></li>
									<li><a href="#">Third Level Link</a></li>
									<li><a href="#">Third Level Link</a></li>

								</ul></li>
						</ul></li>
					<li><a href="empty.html"><i class="fa fa-fw fa-file"></i>
							Empty Page</a></li>
				</ul>

			</div>
		</nav>
		<!-- Left End  -->
		<!-- Right Start -->
		<div id="page-wrapper">
			<ul class="nav nav-tabs" id="tabs">
			
			</ul>
			<div class="tab-content" id="tabContents">
				
			</div>
		</div>
		<div style="background-color:#FFFFFF">
			<!-- Footer Start  -->
				<footer>
					<p>
						All right reserved. Template by: <a href="http://webthemez.com">WebThemez</a>
					</p>
				</footer>
				<!-- Footer End -->
		</div>

		<!-- Right end  -->
	</div>
	<!-- /. WRAPPER  -->
	


</body>

</html>
