<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/mobilemeta.jsp" %>
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
	                 message: '用户名验证',
	                 validators: {
	                     notEmpty: {
	                         message: '用户名不能为空'
	                     },
	                     stringLength: {
	                         min: 6,
	                         max: 30,
	                         message: '用户名的长度在6~30个字符之间'
	                     },
	                     regexp: {
	                         regexp: /^[a-zA-Z0-9_\.]+$/,
	                         message: '用户名必须是大、小写字母、数字、下划线'
	                     },
	                     different: {
	                         field: 'password',
	                         message: '用户名密码不能相同'
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
	                         max: 30,
	                         message: '用户名的长度在6~30个字符之间'
	                     },
	                     different: {
	                         field: 'userName',
	                         message: '密码不能与用户名相同'
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
		      <h3>还没有本店的账号？点此<a class="orange" href="${ctx}/register.do">注册</a></h3>
		      	<div class="lrSub" id="showError">
		      		
	       		</div>
	       		<div class="form-group" style="margin-left: 10px;margin-right: 10px;">
	       			<input type="text" class="form-control" id="userName" name="userName" placeholder="输入手机号码或者邮箱号" />
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
     <div class="height1"></div>
     <div class="footNav">
      <dl>
       <a href="index.html">
        <dt><span class="glyphicon glyphicon-home"></span></dt>
        <dd>微店</dd>
       </a>
      </dl>
      <dl>
       <a href="prolist.html">
        <dt><span class="glyphicon glyphicon-th"></span></dt>
        <dd>所有商品</dd>
       </a>
      </dl>
      <dl>
       <a href="car.html">
        <dt><span class="glyphicon glyphicon-shopping-cart"></span></dt>
        <dd>购物车 </dd>
       </a>
      </dl>
      <dl>
       <a href="user.html">
        <dt><span class="glyphicon glyphicon-user"></span></dt>
        <dd>我的</dd>
       </a>
      </dl>
      <div class="clearfix"></div>
     </div><!--footNav/-->
    </div><!--maincont-->
</body>
  
</html>