<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<title>Demo</title>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/import.jsp" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<link href="${ctx}/JQueryFileUpload/css/fileUpload.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx}/JQueryFileUpload/js/fileUpload.js"></script>
<script type="text/javascript">
	var inputForm = '#inputForm';
	$(function(){
		$(inputForm).form({
		    success:function(data){
		    	var result = jQuery.parseJSON(data);
		    	if(result.flag==1){
		    		$.messager.confirm('提交结果', '操作成功', function(){
		    			parent.colseAdd();// 关闭添加窗口
		    			parent.winReload();// 刷新列表
					});
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
		$('#prodName').focus();
		
		/////////// 图片上传 /////////////
		$("#fileUploadContent").initUpload({
	        "uploadUrl":"${ctx}/imageOpt/upload-pic.do",//上传文件信息地址
	        //"progressUrl":"#",//获取进度信息地址，可选，注意需要返回的data格式如下（{bytesRead: 102516060, contentLength: 102516060, items: 1, percent: 100, startTime: 1489223136317, useTime: 2767}）
	        //"showSummerProgress":false,//总进度条，默认限制
	        //"scheduleStandard":true,//模拟进度的方式，设置为true是按总进度，用于控制上传时间，如果设置为false,按照文件数据的总量,默认为false
	        //"size":350,//文件大小限制，单位kb,默认不限制
	        //"maxFileNumber":3,//文件个数限制，为整数
	        //"filelSavePath":"",//文件上传地址，后台设置的根目录
	        "beforeUpload":function(opt){//在上传前执行的函数
	        	 //opt.otherData =[{"name":"你要上传的参数","value":"你要上传的值"}];
	        },
	        "onUpload":function(opt,data){//在上传后执行的函数
	        	console.log(data);
	        	var resultData = JSON.parse(data).resultBean;
	        	
	        	if(resultData.flag==1){
	        		var result = resultData.data;
	        		var picUrl = "";
	        		for(var i=0; i<result.length; i++){
	        			if(i>0){
	        				picUrl = picUrl+",";
	        			}
	        			picUrl = picUrl+result[i].filePath;
	        		}
	        		//$("#imgUrl1").val(picUrl);
	        		document.getElementById("imgUrl1").value=picUrl;
	        	}else{
	        		alert(resultData.code);
	        	}
	            //uploadTools.uploadError(opt);//显示上传错误
	        },
	        "autoCommit":false,//文件是否自动上传
	        "fileType":['png','jpg','jpeg','docx','doc']//文件类型限制，默认不限制，注意写的是文件后缀

	        
	        
	    });
		
		var showImg = "${products.imgUrl }";
		if(showImg!=null && showImg.length>0){
			var images = showImg.split(",");
			var imgLab = "";
			for(var i=0; i<images.length; i++){
				imgLab = imgLab+"<img src='${ctx}"+images[i]+"' width='150px' />&nbsp;";
			}
			$("#showImg").html(imgLab);
		}
	});
	
	function doSubmit(){
		var flag = $(inputForm).form('validate');
		if(flag){
			var inputValue = (Number($("#unitPriceCostTemp").val())*100).toFixed(0);
    		inputValue = parseInt(inputValue);
    		$("#unitPriceCost").val(inputValue);
			$(inputForm).submit();
		}
	}
	
	///////////////////////////////////
</script>
</head>
<body>
<div class="easyui-panel" title="产品信息维护" style="width:100%; height:480px; max-width:630px;padding:20px 150px 20px 20px;">
	<form action="${ctx}/products/saveProducts.do" id="inputForm" name="inputForm" method="post">
		<input type="hidden" name="token" id="token" value="${token}"/>
		<input type="hidden" name="prodId" id="prodId" value="${products.prodId}"/>
		
		<div style="margin-bottom:20px">
	        <label class="label-top">产品名称</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="prodName" value="${products.prodName }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">进货单价</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" id="unitPriceCostTemp" name="unitPriceCostTemp" value='<fmt:formatNumber value="${products.unitPriceCost/100}" type="currency" pattern="0.00"/>' style="width:100%;" data-options="required:true">
	        <input type="hidden" name="unitPriceCost" id="unitPriceCost"/>
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">销售单价</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="unitPriceSell" value="${products.unitPriceSell }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">品牌</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="brands" value="${products.brands }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">单位</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="unit" value="${products.unit }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">规格</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="specification" value="${products.specification }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">参数</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="properties" value="${products.properties }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">标签</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="mark" value="${products.mark }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">类型</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="type" value="${products.type }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">库存数量</label>
	        <input class="easyui-textbox theme-textbox-radius" type="text" name="stockNum" value="${products.stockNum }" style="width:100%;" data-options="required:true">
	    </div>
	    <div style="margin-bottom:20px">
	        <label class="label-top">备注/描述</label>
	        <textarea class="easyui-textarea theme-textbox-radius" type="text" name="remark" style="width:100%;" rows="5" data-options="required:true">${products.remark }</textarea>
	    </div>
	    <div id="showImg">
	    </div>
	    <div>
			<div id="fileUploadContent" class="fileUploadContent">
			
			</div>
		</div>
		<div style="height:20px;">
			<input type="hidden" id="imgUrl1" name="imgUrl" style="width:100%; margin: 0px; padding-top: 0px; padding-bottom: 0px; height: 30px; line-height: 30px; " value="${products.imgUrl }">
		</div>
		<div>
	        <a href="javascript:void(0);" class="easyui-linkbutton button-default" iconCls="icon-ok" style="width:100%;height:32px" onclick="doSubmit();">提交</a>
	    </div>
	    <div style="height:20px">
	    	&nbsp;
	    </div>
    </form>
</div>

</body>
</html>