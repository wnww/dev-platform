$(document).ready(function(){
	getOrderInfo();
});


function getOrderInfo(){
	$.ajax({
		type : "get",
		url : ctx+"/userCenter/getOrdersInfo.do",
		dataType : "json",
		success : function(data) {
			if (data.flag == 1) {
				$("#finishCount").html(data.data.finishCount);
				$("#unFinishCount").html(data.data.unFinishCount);
			} else if (data.flag == 3) {
				alertMsg(data.msg,"/login.do");
			} else {
				alertMsg(data.msg);
			}
		},
		error : function(messg) {
			alertMsg("获取订单数量失败："+messg.responseText);
		}
	});
}
