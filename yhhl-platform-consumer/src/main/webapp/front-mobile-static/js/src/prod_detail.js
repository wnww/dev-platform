var collectFlag = 0;
$(document).ready(function(){
	//getProdStockInfo();
	// 规格操作
	$(".guige li").click(function(){
		//$(this).addClass("guigeCur").siblings("li").removeClass("guigeCur");
		$(".guige li").removeClass("guigeCur");
		$(this).addClass("guigeCur");
		var children = $(this).children("input");
		setFormValue(children);
	});
	
	var lis = $(".guige").children("li");
	$.each(lis,function(index, item) {
		console.log("=================="+$(this)[0].className);
		if($(this)[0].className=="guigeCur"){
			var children = $(this).children("input");
			setFormValue(children);
		}
	});
	
	// 收藏按钮操作
	$("#showCang").on("click",function(){
		if(collectFlag==0){
			$(this).css("color","#ff6600");
			$("#start").removeClass();
			$("#start").addClass("glyphicon glyphicon-star");
			collectFlag=1;
			collect();
		}else{
			$(this).css("color","#999999");
			$("#start").removeClass();
			$("#start").addClass("glyphicon glyphicon-star-empty");
			collectFlag=0;
			deleteCollect();
		}
	});
	
	checkCollect();
});

function setFormValue(children){
	$.each(children,function(idx, itm) {
		if(itm.id=="remainNum"){
			$("#showRemainNum").val(itm.value);
		}else if(itm.id=="selledNum"){
			$("#showSelledNum").val(itm.value);
		}else if(itm.id=="stockId"){
			$("#selectStockId").val(itm.value);
		}
	});
}

function saveCarts(){
	var prodId = $("#prodId").val();
	var stockId = $("#selectStockId").val();
	var buyNum = $(".spinnerExample").val();
	if(buyNum<=0){
		alertMsg("购买数量必须大于0！");
		return;
	}
	var stockNum = $("#showRemainNum").val();
	if(buyNum > stockNum){
		alertMsg("购买数量不能大于库存数量！");
		return;
	}
	var url_add = ctx + "/carts/saveCarts.do?t="+new Date();
	$.ajax({
		type: "get",
		url: url_add,
		data: "prodId="+prodId+"&stockId="+stockId+"&buyNum="+buyNum,
		dataType: "json",
		success: function(data){
			if (data.flag == 1) {
				alertMsg(data.msg);
			} else if (data.flag == 2) {
				alertMsg(data.msg);
			} else if (data.flag == 3) {
				alertMsg(data.msg,"/login.do");
			} else {
				alertMsg(data.msg);
			}
		},
		error:function(messg)  { 
       	    alertMsg("添加购物车失败："+messg.responseText);
		} 
	});
}

function saveOrders(){
	var prodId = $("#prodId").val();
	var stockId = $("#selectStockId").val();
	var buyNum = $(".spinnerExample").val();
	if(buyNum<=0){
		alertMsg("购买数量必须大于0！");
		return;
	}
	var stockNum = $("#showRemainNum").val();
	if(buyNum > stockNum){
		alertMsg("购买数量不能大于库存数量！");
		return;
	}
	var url_add = ctx + "/orders/saveOrdersDirect.do?t="+new Date();
	$.ajax({
		type: "get",
		url: url_add,
		data: "prodId="+prodId+"&stockId="+stockId+"&buyNum="+buyNum,
		dataType: "json",
		success: function(data){
			if (data.flag == 1) {
				var url = "/orders/pay.do?orderId="+data.data;
				alertMsg(data.msg,url);
			} else if (data.flag == 2) {
				alertMsg(data.msg);
			} else if (data.flag == 3) {
				alertMsg(data.msg,"/login.do");
			} else {
				alertMsg(data.msg);
			}
		},
		error:function(messg)  { 
       	    alertMsg("添加购物车失败："+messg.responseText);
		} 
	});
}

function checkCollect(){
	var check_collect_url = ctx + "/collects/isCollects.do?t="+new Date();
	$.ajax({
		type: "get",
		url: check_collect_url,
		data: "prodId="+$("#prodId").val(),
		dataType: "json",
		success: function(data){
			if (data.flag == 1) {
				if(data.data>0){
					$("#showCang").css("color","#ff6600");
					$("#start").removeClass();
					$("#start").addClass("glyphicon glyphicon-star");
					collectFlag=1;
				}
			} else if (data.flag == 2) {
				alertMsg(data.msg);
			} else if (data.flag == 3) {
				// 如果未登录，则不再显示是否收藏
				//alertMsg(data.msg,"/login.do");
			} else {
				alertMsg(data.msg);
			}
		},
		error:function(messg)  { 
       	    //alertMsg("添加购物车失败："+messg.responseText);
		} 
	});
}

function collect(){
	$("#prodId").val();
	var add_collect_url = ctx + "/collects/saveCollects.do?t="+new Date();
	$.ajax({
		type: "get",
		url: add_collect_url,
		data: "prodId="+$("#prodId").val(),
		dataType: "json",
		success: function(data){
			if (data.flag == 1) {
				alertMsg(data.msg);
			} else if (data.flag == 2) {
				alertMsg(data.msg);
			} else if (data.flag == 3) {
				alertMsg(data.msg,"/login.do");
			} else {
				alertMsg(data.msg);
			}
		},
		error:function(messg)  { 
       	    alertMsg("添加收藏失败："+messg.responseText);
		} 
	});
}

function deleteCollect(){
	$("#prodId").val();
	var add_collect_url = ctx + "/collects/delCollects.do?t="+new Date();
	$.ajax({
		type: "get",
		url: add_collect_url,
		data: "prodId="+$("#prodId").val(),
		dataType: "json",
		success: function(data){
			if (data.flag == 1) {
				alertMsg(data.msg);
			} else if (data.flag == 2) {
				alertMsg(data.msg);
			} else if (data.flag == 3) {
				alertMsg(data.msg,"/login.do");
			} else {
				alertMsg(data.msg);
			}
		},
		error:function(messg)  { 
       	    alertMsg("添加收藏失败："+messg.responseText);
		} 
	});
}