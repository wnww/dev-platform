$(document).ready(function(){
	getIndexList();
//	$("#jiesuan").on('click',function(){
//		sumbitOrder();
//	});
});


function getIndexList(){
	var orderId = $("#orderId").val();
	var totalMoney = 0.00;
	$.ajax({
		type: "get",
		url: ctx+"/orders/getOrderProductsDatas.do?orderId="+orderId,
		dataType: "json",
		success: function(data){
			var rows = data.rows;
			$.each(rows,function(index, item) {
				var tr1 = $('<tr>');
				var td1 = $('<td class="dingimg" width="30%"><img src="'+getFirstImg(ctx,item.imgUrl)+'" /></td>');
				var td2 = $('<td width="50%">');
				var th = $('<h3>'+item.prodName+'</h3>');
				td2.append(th);
				var td3 = $('<td align="right" width="20%"><span class="qingdan">×'+item.prodNum+'</span></td>');
				tr1.append(td1);
				tr1.append(td2);
				tr1.append(td3);
				var tr2 = $('<tr>');
				var th2 = $('<th colspan="3"><strong class="orange">'+moneyFormatterNoY(item.unitPrice*item.prodNum/100)+'</strong></th>');
				tr2.append(th2);
				$("#orderProdList").after(tr1);
				tr1.after(tr2);
				totalMoney = totalMoney+(item.unitPrice*item.prodNum);
			});
			$("#orderAmount").val(totalMoney);
			$("#showOrderAmount").html(moneyFormatterNoY(totalMoney/100));
		},
		error:function(messg)  { 
			alertMsg('操作失败:'+messg.responseText);
       } 
	});
}

function sumbitOrder(){
	var carts = "";
	var i=0;
	$('input[name="selectBox"]:checked').each(function(){
		carts = carts+$(this).val()+",";
	});
	if(carts.endWith(",")){
		carts = carts.substring(0,carts.length-1);
	}
	console.log(carts);

	$.ajax({
		type : "post",
		url : ctx+"/orders/saveOrdersByCarts.do",
		data : "carts="+carts,
		dataType : "json",
		success : function(data) {
			if (data.flag == 1) {
				alertMsg(data.msg,"/index.do");
			} else if (data.flag == 3) {
				alertMsg(data.msg,"/login.do");
			} else {
				alertMsg(data.msg);
			}
		},
		error : function(messg) {
			alertMsg("修改数量失败："+messg.responseText);
		}
	});
}
