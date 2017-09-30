<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <head>
    <meta charset="utf-8">
    <title>瀛海Soft</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/import.jsp" %>
<script type="text/javascript">
	var inputForm = '#inputForm';
	$(function(){
		$(inputForm).form({
		    success:function(data){
		    	var result = jQuery.parseJSON(data);
		    	if(result.flag==1){
		    		document.location.href="${ctx}/sysManage/index.do";
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
		
		
		$(".QRcode").on("click",function(){
			$(".QRcode-layout").removeClass("hide");

		});
		$(".QRcode-layout-close").on("click",function(){
			$(".QRcode-layout").addClass("hide");
		});

		$.extend($.fn.validatebox.defaults.tipOptions, {
			onShow: function() {
				$(this).tooltip("tip").css({backgroundColor:"#ff7e00", border: "none",color: "#fff"});
			}
		})

		/*布局部分*/
		$('#theme-login-layout').layout({
			fit:true/*布局框架全屏*/
		});
		
		$('#userName').textbox({    
			prompt:'用户名',
			required:true,
			missingMessage:"请输入用户名"
		});
		$('#password').textbox({    
			type:"Password",
			prompt:'密码',
			required:true,
			missingMessage:"请输入密码"
		});
	});
	
	function doSubmit(){
		var flag = $(inputForm).form('validate');
		if(flag){
			$(inputForm).submit();
		}
	}
</script>
</head>

<body class="theme-login-layout">
	  	<div class="theme-login-header"></div>
		<div id="theme-login-form">
			<!--  div class="QRcode"></div>
			<div class="QRcode-layout hide">
				<div class="QRcode-layout-close"></div>
				<div class="QRcode-header">
					<span>请使用微信进行扫码登录</span>
				</div>
				<div class="QRcode-content"><img src="${ctx}/images/QRcode-demo.png" width="215"></div>
			</div-->

            <form id="inputForm" name="inputForm" class="theme-login-form" action="${ctx}/sysManage/login.do" method="post">  
            <dl>
	            <dt><img src="${ctx}/images/logo.png"></dt>
			 	<dd><input id="userName" name="userName" class="theme-login-text" style="width:100%;"/></dd>
	            <dd><input id="password" name="password" class="theme-login-text" style="width:100%;"/></dd>
	            <dd><a href="javascript:void(0);" class="easyui-linkbutton button-default" iconCls="icon-ok" style="width:100%;height:32px" onclick="doSubmit();">提交</a></dd>
            </dl>
            </form>
        </div>
  
  
  </body>
</html>