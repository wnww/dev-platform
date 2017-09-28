<%@ page contentType="text/html;charset=UTF-8" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>后台管理系统</title>
        <%@ include file="/common/meta.jsp" %>
        <%@ include file="/common/import.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <script type="text/javascript">
		$(document).ready(function(){
			/*布局部分*/
			$('#master-layout').layout({
				fit:true/*布局框架全屏*/
			});
			
			 /*右侧菜单控制部分*/
            var left_control_status=true;
            var left_control_panel=$("#master-layout").layout("panel",'west');
            $(".left-control-switch").on("click",function(){
                if(left_control_status){
                    left_control_panel.panel('resize',{width:70});
                    left_control_status=false;
                    $(".theme-left-normal").hide();
                    $(".theme-left-minimal").show();
                }else{
                    left_control_panel.panel('resize',{width:200});
                    left_control_status=true;
                    $(".theme-left-normal").show();
                    $(".theme-left-minimal").hide();
                }
                $("#master-layout").layout('resize', {width:'100%'})
            });
            /*右侧菜单控制结束*/
            
            var p = $('body').layout('panel', 'west').panel({
                onCollapse: function(){
                    //alert('collapse');
                }
            });
            //主tab面板
            $('#control').tabs({
                onSelect: function(){
                    var tab = $('#control').tabs('getSelected');
                    //alert(tab.iframe);						
                },
                tools: [{
                    iconCls: 'icon-help',
                    handler: function(){
                        help();
                    }
                }]
            });
            var content = '<iframe scrolling="auto" frameborder="0"  src="${ctx}/common/about.jsp" style="width:100%;height:100%;"></iframe>';
            
            $('#control').tabs('add', {
                title: "用户中心",
                content: content,
                closable: false
            });
            tabCloseEven();
			
		});
        	//关闭进度条
			function closes(){
				$("#Loading").fadeOut("normal",function(){
					$(this).remove();
				});
			}
			var pc;
			$.parser.onComplete = function(content){
				if(pc) clearTimeout(pc);
				pc = setTimeout(closes, 1000);
			};

            var maxWindow = 10;
            
            function addTab(title, url){
                var tabslength = $('#control').tabs('tabs').length;
                if (tabslength < maxWindow) {
                    if ($('#control').tabs('exists', title)) {
                        $.messager.confirm('<span style="color:#0000FF; ">请&nbsp;确&nbsp;认？</span>', '确定要刷新标签页： <font color=red>' + title + '</font> 吗?', function(flag){
                            if (flag) {
                                var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
                                var tab = $('#control').tabs('getTab', title);
                                $('#control').tabs('update', {
                                    tab: tab,
                                    options: {
                                        title: title,
                                        content: content,
                                        closable: true
                                    }
                                });
                                $('#control').tabs('select', title);
                            }
                        });
                        //$('#control').tabs('close',title);
                    }
                    else {
                        var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
                        $('#control').tabs('add', {
                            title: title,
                            content: content,
                            closable: true
                        });
                    }
                }
                else {
                    $.messager.alert('信息提示', "您打开窗口太多了,请关闭不用的窗口!", 'info', function(){
                        return false;
                    });
                }
                
                $(".tabs-inner").dblclick(function(){
                    var ti = $(this).children("tabs-title").text();
                    if (ti != '用户中心') {
                        $('#control').tabs('close', ti);
                    }else{
                    	$('#control').tabs('', ti);
                    }
                });
                
                $(".tabs-inner").bind('contextmenu', function(e){
                    $('#mm').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                    var subtitle = $(this).children("span").text();
                    $('#control').data("currtab", subtitle);
                    return false;
                });
            }
            
            function select(){
                $('#leftmenu').accordion('select', 'Title1');
            }
            
            function help(){
                $.messager.alert('信息提示', "右键tab页，使用更多功能!", 'info');
            }
            
            /**关闭tab标签页*/
            function closeTab(){
                var tab = parent.$('#control').tabs('getSelected');
                var tname = tab.panel('options').title;
                parent.$('#control').tabs('close', tname)
            }
       
		/**有父窗口则在父窗口打开*/
		if (self != top) {
			top.location = self.location;
		}
		//修改密码
		function updatePwd(){
			// 进入增加页面
			$('#saveFrame').html('');			
			var url = '${ctx}/common/update-pwd.action';				
			$('#saveFrame').attr("title",'');
			$('#saveFrame').attr("src",url);
			$('#saveDiv').window('open');			
		}
		//关闭密码
		function closePasWin(){
			$('#saveDiv').window('close');		
		}
	
		function logout(){
			$.messager.confirm('提示', '您确定工作已经保存，并退出该系统?', function(r){
				if (r){					
					location.href = '${ctx}/logout.do?go=<%=basePath %>${ctx}';
				}
		   });
		}
	</script>

</head>
<body id="master-layout">
   	<div id='Loading' style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#F0F0F0;text-align:center;padding-top:10%;">
           <div>
           	<img alt="" src="${ctx}/images/loading.gif" width="30%" height="50%">
           </div>
           <div style="font-size: 18px; color: #299AE6; font-weight: bold;">
           	页面加载中,请稍等......
           </div>
    </div>
	    
	<div id="master-layout">
        <div data-options="region:'north',border:false,bodyCls:'theme-header-layout'">
        	<div class="theme-navigate">
                <div class="left">
                    <a href="javascript:void(0);" class="easyui-linkbutton left-control-switch"><i class="fa fa-bars fa-lg"></i></a>
                    <a href="javascript:void(0);" class="easyui-menubutton theme-navigate-user-button" data-options="menu:'.theme-navigate-user-panel'">${loginUser.userName }</a>
                    <!--a href="javascript:void(0);" class="easyui-linkbutton">新建</a>
                    <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#mm1',hasDownArrow:false">文件</a>
                    <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#mm2',hasDownArrow:false">编辑</a>
                    <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#mm3',hasDownArrow:false">消息<span class="badge color-default">15</span></a-->

                    <!--div id="mm1" class="theme-navigate-menu-panel">
                    	<div>新建</div>
                        <div>打开</div>
                        <div>
                        	<span>打开最近文件</span>
                            <div>
                                <div>1 index.html</div>
                                <div>2 calendar-custom.html</div>
                                <div>3 combo-animation.html</div>
                                <div>4 datebox-restrict.html</div>
                                <div>5 datetimespinner-icon.html</div>
                                <div>6 filebox-button-align.html</div>
                                <div>7 menubutton-alignment.html</div>
                                <div>8 messager-interactive.html</div>
                                <div>9 propertygrid-group-format.html</div>
                                <div class="menu-sep"></div>
                                <div>启动时重新打开文件</div>
                            </div>
                        </div>
                        <div>关闭</div>
                        <div>全部关闭</div>
                        <div class="menu-sep"></div>
                        <div data-options="disabled:true,iconCls:'icon-save'">保存</div>
                        <div>另存为</div>
                        <div data-options="disabled:true">保存为全部</div>
                        <div class="menu-sep"></div>
                        <div>
                            <span>导入</span>
                            <div>
                                <div>XML 到模板</div>
                                <div>表格式数据</div>
                                <div data-options="disabled:true">Word 文档</div>
                                <div data-options="disabled:true">Excel 文档</div>
                            </div>
                        </div>
                        <div>
                            <span>导出</span>
                            <div>
                                <div>表格</div>
                            </div>
                        </div>
                        <div class="menu-sep"></div>
                        <div>退出</div>
                    </div-->
                    
                    <!-- div id="mm2" class="theme-navigate-menu-panel">
                        <div>撤销</div>
                        <div data-options="disabled:true">重做</div>
                        <div class="menu-sep"></div>
                        <div>剪切</div>
                        <div>复制</div>
                        <div data-options="disabled:true">粘贴</div>
                        <div data-options="disabled:true">选择性粘贴</div>
                        <div data-options="disabled:true">清除</div>
                        <div class="menu-sep"></div>
                        <div>全选</div>
                        <div>选择父标签</div>
                        <div>选择子标签</div>
                        <div class="menu-sep"></div>
                        <div>查找和替换</div>
                        <div>查找所选</div>
                        <div>查找下一个</div>
                        <div class="menu-sep"></div>
                        <div>快捷键</div>
                        <div>首选项</div>
                    </div-->
                    
                    <!--div id="mm3" class="theme-navigate-menu-panel" style="width:180px;">
                        <div>产品消息<span class="badge color-success">5</span></div>
                        <div>安全消息<span class="badge color-important">10</span></div>
                        <div>服务消息</div>
                        <div class="menu-sep"></div>
                        <div>查看历史消息</div>
                        <div class="menu-sep"></div>
                        <div>清除消息提示</div>
                    </div-->
                    
                    
                    <div class="theme-navigate-user-panel">
                       <dl>
                       		<dd>
                            	<img src="${loginUser.userPhoto }" width="86" height="86">
                                <b class="badge-prompt">${loginUser.userName }<!-- i class="badge color-important">10</i--></b>
                                <span>${loginUser.nikeName }</span>
                            </dd>
                            <dt>
                            	<a class="theme-navigate-user-modify">修改资料</a>
                                <a class="theme-navigate-user-logout" href="javascript:void(0);" onclick="logout();">注销</a>
                            </dt>
                       </dl>
                    </div>
                </div>
                <div class="right">
                    <a href="javascript:void(0);" class="easyui-menubutton theme-navigate-more-button" data-options="menu:'#more',hasDownArrow:false"></a>
                    <div id="more" class="theme-navigate-more-panel">
                    	<div>联系我们</div>
                        <div>参与改进计划</div>
                        <div>检测更新</div>
                        <div>关于</div>
                    </div>
                </div>
            </div>
        
        </div>

        <!--开始左侧菜单-->
        <div data-options="region:'west',border:false,bodyCls:'theme-left-layout'" style="width:200px;">


            <!--正常菜单--> 
            <div class="theme-left-normal">

                <!--start class="easyui-layout"-->
                <div class="easyui-layout" data-options="border:false,fit:true"> 

                    <!--start region:'center'-->
                    <div data-options="region:'center',border:false">

                        <!--start easyui-accordion--> 
                        <div class="easyui-accordion" data-options="border:false,fit:true">
                        	<div title="产品管理">   
                                <ul class="easyui-datalist" data-options="border:false,fit:true">
                                    <li><a href="javascript:void(0);" onclick="addTab('产品管理','${ctx}/products/index.do')">产品管理</a></li>
                                </ul>      
                            </div>   
                            <div title="订单管理">   
                                <ul class="easyui-datalist" data-options="border:false,fit:true">
                                    <li><a href="javascript:void(0);" onclick="addTab('订单管理','${ctx}/orders/index.do')">订单管理</a></li>
                                </ul>      
                            </div>   
                            <div title="系统管理"> 
                                <ul class="easyui-datalist" data-options="border:false,fit:true">
                                    <li><a href="javascript:void(0);" onclick="addTab('字典管理','${ctx}/dicts/index.do')">字典管理</a></li>
                                    <li><a href="javascript:void(0);" onclick="addTab('用户管理','${ctx}/user/index.do')">用户管理</a></li>
                                    <li><a href="javascript:void(0);" onclick="addTab('角色管理','${ctx}/roles/index.do')">角色管理</a></li>
                                    <li><a href="javascript:void(0);" onclick="addTab('权限资源管理','${ctx}/authority/index.do')">权限资源管理</a></li>
                                </ul>  
                            </div>   
                            <div title="图表测试">   
                                <ul class="easyui-datalist" data-options="border:false,fit:true">
                                    <li><a href="javascript:void(0);" onclick="addTab('Echarts测试','${ctx}/charts/index.do')">Echarts测试</a></li>
                                </ul>      
                            </div>

                        </div>
                        <!--end easyui-accordion--> 

                    </div>
                    <!--end region:'center'-->
                </div>  
                <!--end class="easyui-layout"-->

            </div>
            <!--最小化菜单--> 
            <div class="theme-left-minimal">
                <ul class="easyui-datalist" data-options="border:false,fit:true">
                    <li><i class="fa fa-home fa-2x"></i><p>系统管理</p></li>
                    <li><i class="fa fa-book fa-2x"></i><p>产品管理</p></li>
                    <li><i class="fa fa-pencil fa-2x"></i><p>订单管理</p></li>
                    <li><i class="fa fa-cog fa-2x"></i><p>图表测试</p></li>
                    <li><a class="left-control-switch"><i class="fa fa-chevron-right fa-2x"></i><p>打开</p></a></li>
                </ul>
            </div>

        </div>
        <!--结束左侧菜单--> 

        <div data-options="region:'center',border:false" id="control" style="padding:5px; background:#fff;">
             
        </div>
    </div>
    	
        <script type="text/javascript">
            
            function tabCloseEven(){
                //关闭当前
                $('#mm-tabclose').click(function(){
                    var tt = $('#control').data("currtab");
                    $('#control').tabs('select', tt);
                    if (tt != '用户中心') {
                        $('#control').tabs('close', tt);
                    }
                });
                //全部关闭
                $('#mm-tabcloseall').click(function(){
                    var tt = $('#control').data("currtab");
                    $('#control').tabs('select', tt);
                    
                    $('.tabs-inner span').each(function(i, n){
                        var t = $(n).text();
                        if (t != '用户中心') {
                            $('#control').tabs('close', t);
                        }
                    });
                });
                //关闭除当前之外的TAB
                $('#mm-tabcloseother').click(function(){
                    var tt = $('#control').data("currtab");
                    $('#control').tabs('select', tt);
                    
                    $('.tabs-inner span').each(function(i, n){
                        var t = $(n).text();
                        if (t != tt && t != '用户中心') {
                            $('#control').tabs('close', t);
                        }
                    });
                });
                //关闭当前右侧的TAB
                $('#mm-tabcloseright').click(function(){
                    var tt = $('#control').data("currtab");
                    $('#control').tabs('select', tt);
                    
                    var nextall = $('.tabs-selected').nextAll();
                    if (nextall.length == 0) {
                        $.messager.alert('信息提示', "到头了，后边没有啦~~", 'info', function(){
                            return false;
                        });
                    }
                    nextall.each(function(i, n){
                        var t = $('a:eq(0) span', $(n)).text();
                        if (t != '用户中心') {
                            $('#control').tabs('close', t);
                        }
                    });
                    return false;
                });
                //关闭当前左侧的TAB
                $('#mm-tabcloseleft').click(function(){
                    var tt = $('#control').data("currtab");
                    $('#control').tabs('select', tt);
                    
                    var prevall = $('.tabs-selected').prevAll();
                    if (prevall.length <= 1) {
                        $.messager.alert('信息提示', "【用户中心】不能关了啊~", 'info', function(){
                            return false;
                        });
                    }
                    prevall.each(function(i, n){
                        var t = $('a:eq(0) span', $(n)).text();
                        if (t != '用户中心') {
                            $('#control').tabs('close', t);
                        }
                    });
                    return false;
                });
            }
            
        </script>
        <div id="mm" class="easyui-menu" style="width:150px;">
            <div id="mm-tabclose">  关闭  </div>
            <div id="mm-tabcloseall"> 全部关闭  </div>
            <div id="mm-tabcloseother"> 除此之外全部关闭  </div>
            <div class="menu-sep"> </div>
            <div id="mm-tabcloseright"> 当前页右侧全部关闭  </div>
            <div id="mm-tabcloseleft"> 当前页左侧全部关闭  </div>
        </div>
    </body>
</html>
