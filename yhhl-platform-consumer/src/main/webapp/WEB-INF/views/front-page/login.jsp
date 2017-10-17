<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/mobilemeta.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<%@ include file="/common/mobileimport.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<title>${websiteTitle}</title>
<link href="https://cdn.bootcss.com/bootstrap-validator/0.4.2/css/bootstrapValidator.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/bootstrap-validator/0.4.2/js/bootstrapValidator.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#inputForm').bootstrapValidator({
	         message: 'This value is not valid',
	         feedbackIcons: {
	             valid: 'glyphicon glyphicon-ok',
	             invalid: 'glyphicon glyphicon-remove',
	             validating: 'glyphicon glyphicon-refresh'
	         },
	         fields: {
	        	 userName: {
	                 message: '手机号验证',
	                 validators: {
	                     notEmpty: {
	                         message: '手机号不能为空'
	                     },
	                     regexp: {
	                         regexp:/^1[3|4|5|8][0-9]\d{4,8}$/,
	                         message: '请输入正确的手机号'
	                     }
	                 }
	             },
	             password: {
	            	 validators: {
	                     notEmpty: {
	                         message: '密码不能为空'
	                     },
	                     stringLength: {
	                         min: 6,
	                         max: 18,
	                         message: '密码的长度在6~18个字符之间'
	                     },
	                     regexp: {
	                    	 regexp: /^[0-9a-zA_Z]+$/,
	                         message: '密码只能是字母、数字'
	                     }
	                 }
	             }
	         }
	     });
	

    	$("#inputForm").submit(function(ev){
    		ev.preventDefault();
    	});
    	
    	$("#btnLogin").on("click", function(){
    		var bootstrapValidator = $("#inputForm").data('bootstrapValidator');
    		bootstrapValidator.validate();
    		if(bootstrapValidator.isValid()){
    			login();
    		}else{
    		 	return;
    		}
    	});
    	
});


function login(){
	var userName = $("#userName").val();
	var password = $("#password").val();
	$.ajax({
		type : "post",
		url : "${ctx}/submitLogin.do",
		data : "userName="+userName+"&password="+password,
		dataType : "json",
		success : function(data) {
			if (data.flag == 1) {
				$.confirm({
				    title: '提示!',
				    content: '登录成功!',
				    buttons: {
				        OK: function () {
				            document.location.href="${ctx}/index.do";
				        }
				    }
				});
			} else if (data.flag == 2) {
				alertMsg(data.msg);
			} else {
				alertMsg(data.msg);
			}
		},
		error : function(messg) {
			alertMsg(messg.responseText);
		}
	});
}

</script>
</head>
<body>

<div class="maincont">
     <header>
      <a href="javascript:history.back(-1)" class="back-off fl"><span class="glyphicon glyphicon-menu-left"></span></a>
      <div class="head-mid">
       <h1>登录</h1>
      </div>
     </header>
     <div class="head-top">
      <img src="${frontMobileStaticCtx}/images/head.jpg" />
     </div><!--head-top/-->
     <form id="inputForm" name="inputForm" method="post" class="reg-login">
     		  <span id="tt"></span>
		      <h3 style="padding-left:10px;">还没有本店的账号？点此<a class="orange" href="${ctx}/register.do">注册</a></h3>
		      	<div class="lrSub" id="showError">
		      		
	       		</div>
	       		<div class="form-group" style="margin-left: 10px;margin-right: 10px;">
	       			<input type="text" class="form-control" id="userName" name="userName" placeholder="输入手机号码" />
	       		</div>
	       		<div class="form-group" style="margin-left: 10px;margin-right: 10px;">
	       			<input type="password" class="form-control" id="password" name="password" placeholder="输入密码" />
	       		</div>
	       		<div class="lrSub">
		      		<div class="form-group">
		       			<input type="button" class="form-control" id="btnLogin" name="btnLogin" value="立即登录" />
		       		</div>
	       		</div>
	       		
     </form><!--reg-login/-->
     
     <!--footNav-->
    	<%@ include file="/common/mobilefooter.jsp" %>
     <!--footNav/-->
    </div><!--maincont-->
</body>
  <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/keyboard_opt.js"></script>
</html>