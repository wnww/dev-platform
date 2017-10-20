<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/mobilemeta.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<%@ include file="/common/mobileimport.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="https://cdn.bootcss.com/bootstrap-validator/0.4.2/css/bootstrapValidator.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/bootstrap-validator/0.4.2/js/bootstrapValidator.min.js"></script>
<link href="${frontMobileStaticCtx}/css/bootstrap-switch.css?v=1.0.0" rel="stylesheet">
<script src="${frontMobileStaticCtx}/js/src/bootstrap-switch.js?v=1.0.0"></script>
    <title>${websiteTitle}</title>
    <style type="text/css">
    	#provSelect,#citySelect{
		    background: url('${frontMobileStaticCtx}/images/arrow.png') right center no-repeat; 
		   /* the width and the height of your image */
		    overflow: hidden;
		    border: thick 0px 0px 1px 0px;
		}
		
		#provSelect select{
		    -webkit-appearance:none;
		    -moz-appearance:none;
		    appearance:none;
		    background:transparent;
		    border:none;
		    padding-left:0px;
		    /*width: 120px;
		    height:100%;*/    
		}
		#citySelect select{
		    -webkit-appearance:none;
		    -moz-appearance:none;
		    appearance:none;
		    background:transparent;
		    border:none;
		    padding-left:0px;
		    /*width: 120px;
		    height:100%;*/    
		}
		.form-group {
		    margin-bottom: 0px;
		}
    </style>
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
    	        	 realName: {
    	                 validators: {
    	                     notEmpty: {
    	                         message: '收货人不能为空'
    	                     },
    	                     stringLength: {
    	                         min: 2,
    	                         max: 20,
    	                         message: '收货人长度在2~20个字符之间'
    	                     },
    	                     regexp: {
    	                         regexp: /^([\u4e00-\u9fa5]{1,20}|[a-zA-Z\.\s]{1,20})$/,
    	                         message: '收货人必须为汉字或字母'
    	                     }
    	                 }
    	             },
    	             address: {
    	            	 validators: {
    	                     notEmpty: {
    	                         message: '详细地址不能为空'
    	                     },
    	                     stringLength: {
    	                         min: 2,
    	                         max: 80,
    	                         message: '地址的长度在2~80个字符之间'
    	                     }
    	                 }
    	             },
    	             mobile: {
    	            	 validators: {
    	                     notEmpty: {
    	                         message: '手机号不能为空'
    	                     },
    	                     regexp: {
    	                         regexp:/^1[3|4|5|8][0-9]\d{4,8}$/,
    	                         message: '请输入正确的手机号'
    	                     }
    	                 }
    	             }
    	         }
    	     });
    	

        	$("#inputForm").submit(function(ev){
        		ev.preventDefault();
        	});
        	
        	$("#addAddress").on("click", function(){
        		var bootstrapValidator = $("#inputForm").data('bootstrapValidator');
        		bootstrapValidator.validate();
        		if(bootstrapValidator.isValid()){
        			saveAddress();
        		}else{
        		 	return;
        		}
        	});
    });
    
    function saveAddress(){
    	var realName = $("#realName").val();
    	var province = $("#province").val();
    	var city = $("#city").val();
    	var address = $("#address").val();
    	var mobile = $("#mobile").val();
    	var defaultAdd = $("#defaultAdd").val();
    	var token = $("#token").val();
    	var addrId = $("#addrId").val();
    	$.ajax({
    		type : "post",
    		url : "${ctx}/address/saveAddress.do",
    		data : "addrId="+addrId+"&realName="+realName+"&province="+province+"&city="+city+"&address="+address+"&mobile="+mobile+"&defaultAdd="+defaultAdd+"&token="+token,
    		dataType : "json",
    		success : function(data) {
    			if (data.flag == 1) {
    				$.confirm({
    				    title: '提示!',
    				    content: '地址添加成功!',
    				    buttons: {
    				        OK: function () {
								var orderIds = "${orderId}";
    				        	if(orderIds==""){
    				        		document.location.href="${ctx}/address/selectAddress.do";    				        		
    				        	}else{
    				        		document.location.href="${ctx}/orders/pay.do?orderId=${orderId}";	
    				        	}
    				        }
    				    }
    				});
    			} else if (data.flag == 2) {
    				// 无权限
    				alertMsg(data.msg,"/index.do");
    			} else if (data.flag == 3) {
    				alertMsg(data.msg,"/login.do");
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
       <h1>收货地址</h1>
      </div>
     </header>
     <form id="inputForm" name="inputForm" action="${ctx}/address/saveAddress.do" method="get" class="reg-login">
     	<input type="hidden" name="token" id="token" value="${token}"/>
     	<input type="hidden" name="addrId" id="addrId" value="${address.addrId}"/>
      <div class="lrBox">
       <div class="form-group lrList"><input type="text" id="realName" name="realName" placeholder="收货人" value="${address.realName}" /></div>
       <div class="form-group lrList" id="provSelect">
       		<select id="province" class="prov"></select>
       </div>
       <div class="form-group lrList" id="citySelect">
        	<select id="city" class="city" disabled="disabled"></select>
       </div>
       <div class="form-group lrList"><input type="text" id="address" name="address" value="${address.address}" placeholder="详细地址" /></div>
       <div class="form-group lrList"><input type="text" id="mobile" name="mobile" value="${address.mobile}" placeholder="手机" /></div>
      	<div class="form-group lrList2">
       		<div style="float:left;line-height: 45px;">
       			<strong class="orange">设为默认</strong>
       		</div>
       		<div style="margin-left:10px;float:left; padding-top: 6px;">
			    <input type="checkbox" id="isDefault" name="isDefault" checked>
			    <input type="hidden" id="defaultAdd" name="defaultAdd" value="1"/>
			    
			</div>
       </div>
       <div class="form-group lrSub">
       <input type="button" id="addAddress" value="保存" />
      </div>
      </div><!--lrBox/-->
     </form>
     <!--reg-login/-->
    <!--footNav-->
    <%@ include file="/common/mobilefooter.jsp" %>
    <!--footNav/-->
    </div><!--maincont-->
  </body>
  <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/jquery.cityselect.js?v=1.0.0"></script>
  <script type="text/javascript" src="${frontMobileStaticCtx}/js/src/keyboard_opt.js?v=1.0.0"></script>
  <script type="text/javascript">
  $.fn.bootstrapSwitch.defaults.onText = '是';
  $.fn.bootstrapSwitch.defaults.offText = '否';
  var provData = "${address.province}";
  if(provData!=""){
	  $("#provSelect").citySelect({
	      prov: "${address.province}",
	      city: "${address.city}"
	  });
	  var isDefault = "${address.defaultAdd}";
	  $("#defaultAdd").val("${address.defaultAdd}");
	  if(isDefault==1){
		  $("[name='isDefault']").bootstrapSwitch('state', true);
	  }else{
		  $("[name='isDefault']").bootstrapSwitch('state', false);
	  }
  }else{
	  $("#provSelect").citySelect({
	      prov: "北京",
	      city: "东城区"
	  });
	  $("[name='isDefault']").bootstrapSwitch();
  }
  
  $('input[name="isDefault"]').on('switchChange.bootstrapSwitch', function(event, state) {
	  	console.log(state); // true | false
	  	if(state==true){
			$("#defaultAdd").val(1);
	  	}else{
	  		$("#defaultAdd").val(0);
	  	}
  });
  </script>
</html>