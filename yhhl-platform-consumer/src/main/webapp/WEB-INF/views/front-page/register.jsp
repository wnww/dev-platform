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
	         message: '此内容验证未通过',
	         feedbackIcons: {
	             valid: 'glyphicon glyphicon-ok',
	             invalid: 'glyphicon glyphicon-remove',
	             validating: 'glyphicon glyphicon-refresh'
	         },
	         fields: {
	        	 userName: {
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
	                         message: '密码长度在6~18个字符之间'
	                     },
	                     regexp: {
	                    	 regexp: /^[0-9a-zA_Z]+$/,
	                         message: '密码只能是字母、数字'
	                     }
	                 },
	                 identical: {//相同
                         field: 'rePwd', //需要进行比较的input name值
                         message: '两次密码不一致'
                     }
	             },
	             rePwd: {
	            	 validators: {
	                     notEmpty: {
	                         message: '确认密码不能为空'
	                     },
	                     identical: {//相同
	                         field: 'password',
	                         message: '两次密码不一致'
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
    	
    	$("#regUser").on("click", function(){
    		var bootstrapValidator = $("#inputForm").data('bootstrapValidator');
    		bootstrapValidator.validate();
    		if(bootstrapValidator.isValid()){
    			regUser();
    		}else{
    		 	return;
    		}
    	});
});

function regUser(){
	var userName = $("#userName").val();
	var password = $("#password").val();
	$('#loading').modal('show');
	$.ajax({
		type : "post",
		url : "${ctx}/submitRegister.do",
		data : "userName="+userName+"&password="+password,
		dataType : "json",
		success : function(data) {
			$('#loading').modal('hide');
			if (data.flag == 1) {
				$.confirm({
				    title: '提示!',
				    content: '注册成功!',
				    buttons: {
				        OK: function () {
				            document.location.href="${ctx}/index.do";
				        }
				    }
				});
			} else if (data.flag == 2) {
				// 无权限
				alertMsg(data.msg,"/index.do");
			} else {
				alertMsg(data.msg);
			}
		},
		error : function(messg) {
			$('#loading').modal('hide');
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
       <h1>会员注册</h1>
      </div>
     </header>
     <form id="inputForm" name="inputForm" action="${ctx}/submitRegister.do" method="get" class="reg-login">
      <h3 style="padding-left: 8px">已经有账号了？点此<a class="orange" href="${ctx}/login.do">登陆</a></h3>
      <div class="lrBox">
       <div class="form-group lrList"><input type="text" name="userName" id="userName" placeholder="输入手机号码" /></div>
       <!-- div class="lrList2"><input type="text" placeholder="输入短信验证码" /> <button>获取验证码</button></div-->
       <div class="form-group lrList"><input type="password" name="password" id="password" placeholder="设置密码（6-18位数字或字母）" /></div>
       <div class="form-group lrList"><input type="password" name="rePwd" id="rePwd" placeholder="再次输入密码" /></div>
      </div><!--lrBox/-->
      <div class="form-group lrSub">
       <input type="button" id="regUser" name="regUser" value="立即注册" />
      </div>
     </form><!--reg-login/-->
     <%@ include file="/common/mobilefooter.jsp" %>
     <!--footNav/-->
    </div><!--maincont-->
    
    <!-- loading -->
<div class="modal fade" style="margin-top: 50%" id="loading" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop='static'>
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">提示</h4>
      </div>
      <div class="modal-body">
        	请稍候。。。
      </div>
    </div>
  </div>
</div>
</body>
  <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/keyboard_opt.js"></script>
</html>