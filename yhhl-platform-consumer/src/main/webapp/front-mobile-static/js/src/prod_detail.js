$(document).ready(function(){
	//getProdStockInfo();
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
	var token = $("#token").val();
	var url_add = ctx + "/carts/saveCarts.do?t="+new Date();
	$.ajax({
		type: "get",
		url: url_add,
		data: "prodId="+prodId+"&stockId="+stockId+"&buyNum="+buyNum+"&token="+token,
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

