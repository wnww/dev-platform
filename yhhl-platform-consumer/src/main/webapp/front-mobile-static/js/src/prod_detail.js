$(document).ready(function(){
	//getProdStockInfo();
	$(".guige li").click(function(){
		//$(this).addClass("guigeCur").siblings("li").removeClass("guigeCur");
		$(".guige li").removeClass("guigeCur");
		$(this).addClass("guigeCur");
		var children = $(this).children("input");
		$.each(children,function(idx, itm) {
			if(itm.id=="remainNum"){
				$("#showRemainNum").val(itm.value);
			}else if(itm.id=="selledNum"){
				$("#showSelledNum").val(itm.value);
			}
		});
	});
	
	var lis = $(".guige").children("li");
	$.each(lis,function(index, item) {
		console.log("=================="+$(this)[0].className);
		if($(this)[0].className=="guigeCur"){
			var children = $(this).children("input");
			$.each(children,function(idx, itm) {
				if(itm.id=="remainNum"){
					$("#showRemainNum").val(itm.value);
				}else if(itm.id=="selledNum"){
					$("#showSelledNum").val(itm.value);
				}
			});
		}
	});
	
	
});

function saveCarts(){
	var prodId = $("#prodId").val();
	var stockId = $("#selectStockId").val();
	var buyNum = $(".spinnerExample").val();
	var url = "/carts/saveCarts.do";
	$.ajax({
		type: "get",
		url: ctx+"/carts/saveCarts.do?t="+new Date(),
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
       	    $.messager.alert('错误提示', '操作失败:'+messg.responseText, 'error');
       } 
	});
}

