$(document).ready(function(){
	getIndexList();
	getDefaultAddress();
	$("#addAddress").on('click',function(){
		document.location.href=ctx+"/address/initAddAddress.do";
	});
	$("#selectAddress").on('click',function(){
		document.location.href=ctx+"/address/selectAddress.do?orderId="+$("#orderId").val();
	});
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

function getDefaultAddress(){
	var url = ctx+"/address/getAddressDatas.do?page=1&rows=0&filter_addrId="+$("#addrId").val();
	if($("#addrId").val()==""){
		url = ctx+"/address/getAddressDatas.do?page=1&rows=0&filter_defaultAdd=1";
	}
	$.ajax({
		type: "get",
		url: url,
		dataType: "json",
		success: function(data){
			if(data.rows.length>0){
				$("#addAddress").hide();
				$("#realName").html(data.rows[0].realName+" "+data.rows[0].mobile);
				$("#addressDetail").html(data.rows[0].province+data.rows[0].city+data.rows[0].address);
			}else{
				$("#showtAddress").hide();
				$("#addAddress").show();
			}
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
