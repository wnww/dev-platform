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
        <style type="text/css">
            h3 {
                width: 95%;
                border: 1px solid #eee;
                margin: 2px;
                margin-left: 2px;
                height: 20px;
                font-size: 12px;
                font-weight: normal;
                line-height: 20px;
                vertical-align: center;
                text-indent: 5px;
                cursor: pointer;
            }
			.icon-theme{
				background:url('${ctx}/widgets/jquery-easyui/extend/images/theme2.png') no-repeat;
			}
			
			.tree-node-selected {
				    background: none repeat scroll 0 0 #FFFFFF;
			}

        </style>
        <script type="text/javascript">
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
            $(function(){
                var p = $('body').layout('panel', 'west').panel({
                    onCollapse: function(){
                        //alert('collapse');
                    }
                });

                //主tab面板
                $('#main').tabs({
                    onSelect: function(){
                        var tab = $('#main').tabs('getSelected');
                        //alert(tab.iframe);						
                    },
                    tools: [{
                        iconCls: 'icon-help',
                        handler: function(){
                            help();
                        }
                    }]
                });
                var content = '<iframe scrolling="auto" frameborder="0"  src="${ctx}/common/about.html" style="width:100%;height:100%;"></iframe>';
                $('#main').tabs('add', {
                    title: "关于",
                    content: content,
                    closable: false
                });
                tabCloseEven();
            });
            
            function addTab(title, url){
                var tabslength = $('#main').tabs('tabs').length;
                if (tabslength < maxWindow) {
                    if ($('#main').tabs('exists', title)) {
                        $.messager.confirm('确认', '确定要刷新标签页： <font color=red>' + title + '</font> 吗?', function(flag){
                            if (flag) {
                                var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
                                var tab = $('#main').tabs('getTab', title);
                                $('#main').tabs('update', {
                                    tab: tab,
                                    options: {
                                        title: title,
                                        content: content,
                                        closable: true
                                    }
                                });
                                $('#main').tabs('select', title);
                            }
                        });
                        //$('#main').tabs('close',title);
                    }
                    else {
                        var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
                        $('#main').tabs('add', {
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
                    $('#main').data("currtab", subtitle);
                    return false;
                });
            }
            
            function select(){
                $('#leftmenu').accordion('select', 'Title1');
            }
            
            function help(){
                $.messager.alert('信息提示', "双击关闭tab页!", 'info');
            }
            
            /**关闭tab标签页*/
            function closeTab(){
                var tab = parent.$('#main').tabs('getSelected');
                var tname = tab.panel('options').title;
                parent.$('#main').tabs('close', tname)
            }
        </script>
        
	
	<!-- 2013年1月10日20:23:37修改 -->
	<script type="text/javascript">
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
					location.href = '${ctx}/logout?go=<%=basePath %>${ctx}';
				}
		   });
		}
	</script>

    </head>
    <body class="easyui-layout">
        <div id='Loading' style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#DDDDDB;text-align:center;padding-top: 20%;">
            <h1>
                <font color="#15428B">
                 	页面初始化中，请等待....
                </font>
            </h1>
        </div>

        <!-- 2013年1月10日20:23:05 修改 -->
        	<!-- 添加窗口 -->
	<div id="saveDiv" class="easyui-window" title="修改密码" style="padding:5px;width: 500px;height:250px;"
    	iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
   		<iframe frameborder="0"  id="saveFrame" height="100%" width="100%" scrolling="No" frameborder="0" ></iframe>
    </div>

        <div id="top" region="north" border="false"
			style="height: 66px; background: #ff8F22; color: #000000;">
			<span style="float:left padding: 4px" >
				<img src="${ctx }/images/logo.png"/>
			</span>
			<span style="float: right; padding: 4px;"> 
				<font color="#ddeeff">欢迎您：${loginUser.userCname}|</font>
				<!-- <a href="#" onclick="updatePwd();"><font color="#ddeeff">修改密码</font></a><font color="#ddeeff">|</font> -->
				<a href="#" onclick="logout();"><font color="#ddeeff">退出系统</font> </a> &nbsp;
			</span>
		</div>
		
        <div region="west" split="true" title="功能扩展菜单" style="width:210px;padding:0px;">
            <div id="leftmenu" class="easyui-accordion" fit="true" border="false">
            	<div title="富文本编辑器" iconCls="icon-undo">
					<h3 onclick="addTab('富文本编辑器','${ctx}/imageOpt/index.do')">富文本编辑器</h3>
				</div>
				 <div title="用户管理-Demo" iconCls="icon-undo">
					<h3 onclick="addTab('用户管理','${ctx}/user/index.do')">用户管理-Demo</h3>
				</div>
				<div title="测试" iconCls="icon-undo">
					<h3 onclick="addTab('产品管理','${ctx}/signInOut/index.do')">测试</h3>
				</div>
            </div>
        </div>
        <div id="ccc" region="center" title="" border="false">
            <div id="main">
            </div>
        </div>
        <script type="text/javascript">
            var bodyh = $(window).height();
            var toph = $('#top').height();
            var mainh = bodyh - toph;
            $('#main').height(mainh);
            
            function tabCloseEven(){
                //关闭当前
                $('#mm-tabclose').click(function(){
                    var tt = $('#main').data("currtab");
                    $('#main').tabs('select', tt);
                    if (tt != '关于') {
                        $('#main').tabs('close', tt);
                    }
                });
                //全部关闭
                $('#mm-tabcloseall').click(function(){
                    var tt = $('#main').data("currtab");
                    $('#main').tabs('select', tt);
                    
                    $('.tabs-inner span').each(function(i, n){
                        var t = $(n).text();
                        if (t != '关于') {
                            $('#main').tabs('close', t);
                        }
                    });
                });
                //关闭除当前之外的TAB
                $('#mm-tabcloseother').click(function(){
                    var tt = $('#main').data("currtab");
                    $('#main').tabs('select', tt);
                    
                    $('.tabs-inner span').each(function(i, n){
                        var t = $(n).text();
                        if (t != tt && t != '关于') {
                            $('#main').tabs('close', t);
                        }
                    });
                });
                //关闭当前右侧的TAB
                $('#mm-tabcloseright').click(function(){
                    var tt = $('#main').data("currtab");
                    $('#main').tabs('select', tt);
                    
                    var nextall = $('.tabs-selected').nextAll();
                    if (nextall.length == 0) {
                        $.messager.alert('信息提示', "到头了，后边没有啦~~", 'info', function(){
                            return false;
                        });
                    }
                    nextall.each(function(i, n){
                        var t = $('a:eq(0) span', $(n)).text();
                        if (t != '关于') {
                            $('#main').tabs('close', t);
                        }
                    });
                    return false;
                });
                //关闭当前左侧的TAB
                $('#mm-tabcloseleft').click(function(){
                    var tt = $('#main').data("currtab");
                    $('#main').tabs('select', tt);
                    
                    var prevall = $('.tabs-selected').prevAll();
                    if (prevall.length <= 1) {
                        $.messager.alert('信息提示', "【关于】不能关了啊~", 'info', function(){
                            return false;
                        });
                    }
                    prevall.each(function(i, n){
                        var t = $('a:eq(0) span', $(n)).text();
                        if (t != '关于') {
                            $('#main').tabs('close', t);
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
	</div>
    </body>
</html>
