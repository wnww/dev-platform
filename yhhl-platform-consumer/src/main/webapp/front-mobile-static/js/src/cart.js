$(document).ready(function(){
	getCartList();
	$("#jiesuan").on('click',function(){
		sumbitOrder();
	});
});

function selectAll(){
	$("input[name='selectBox']").prop("checked", "checked");
	computePrice();
}
function unSelectAll(){
	$("input[name='selectBox']").removeAttr("checked");
	computePrice();
}

function getCartList(){
	var totalMoney = 0.00;
	var totalCount = 0;
	$.ajax({
		type: "get",
		url: ctx+"/carts/getCartsDatas.do",
		data: "page=1&rows=0",
		dataType: "json",
		success: function(data){
			var rows = data.rows;
			$.each(rows,function(index, item) {
				var div = $('<div class="dingdanlist">');
				var table = $('<table>');
				var tr1 = $('<tr>');
				var td1 = $('<td width="4%"><input type="checkbox" id="box_'+item.cartId+'" name="selectBox" value="'+item.cartId+'" /></td>');
				var td2 = $('<td class="dingimg" width="30%"><img src="'+getFirstImg(ctx,item.imgUrl)+'" /></td>');
				var td3 = $('<td>');
				var h3 = $('<h3>'+item.prodName+'</h3>');
				var tm = $('<time>时间：'+dateFormat(item.createTime)+'</time>');
				td3.append(h3);
				td3.append(tm);
				tr1.append(td1);
				tr1.append(td2);
				tr1.append(td3);
				table.append(tr1);
				var tr2 = $('<tr>');
				var th2 = $('<th colspan="2"><input type="hidden" value="'+item.unitPrice/100+'" id="unitPrice_'+item.cartId+'" name="unitPrice_'+item.cartId+'"><strong class="orange">¥</strong><strong class="orange" id="showMoney_'+item.cartId+'">'+moneyFormatterNoY(item.unitPrice*item.buyNum/100)+'</strong></th>');
				var td4 = $('<td align="right" style="padding-right:15px;" id="spnId_'+item.cartId+'"><input type="text" id="count_'+item.cartId+'" /></td>');
				tr2.append(th2);
				tr2.append(td4);
				table.append(tr2);
				div.append(table);
				$("#dataList").append(div);
				$('#count_'+item.cartId).spinner({min:1,value:item.buyNum});
				$('#box_'+item.cartId).change(function(){
					computePrice();
				});
				setPrice(item.cartId,item.unitPrice);
				totalMoney = totalMoney+(item.unitPrice*item.buyNum);
				totalCount = totalCount+item.buyNum;
			});
			$("#showTotalMoney").html(moneyFormatterNoY(totalMoney/100));
			$("#totalMoney").val(totalMoney/100);
			$("#totalCount").html(totalCount);
			selectAll();
			computePrice();
		},
		error:function(messg)  { 
			alertMsg('操作失败:'+messg.responseText);
       } 
	});
}

function setPrice(cartId,unitPrice){
	$('#spnId_'+cartId).find(".increase").on("click",function(){
		if(!$('#box_'+cartId).get(0).checked){
			$('#box_'+cartId).prop("checked", "checked")
		}
		$("#showMoney_"+cartId).html(moneyFormatterNoY($('#count_'+cartId).val()*unitPrice/100));
		computePrice();
		updateCart(cartId,$("#count_"+cartId).val(),$(this));
		$(this).attr('disabled', 'disabled');
	});
	$('#spnId_'+cartId).find(".decrease").on("click",function(){
		if(!$('#box_'+cartId).get(0).checked){
			$('#box_'+cartId).prop("checked", "checked")
		}
		$("#showMoney_"+cartId).html(moneyFormatterNoY($('#count_'+cartId).val()*unitPrice/100));
		computePrice();
		updateCart(cartId,$("#count_"+cartId).val(),$(this));
		$(this).attr('disabled', 'disabled');
	});
}

function computePrice(){
	var chk_value =[];
	var totalMoney = 0.00;
	var totalCount = 0;
	$('input[name="selectBox"]:checked').each(function(){
		chk_value.push($(this).val());
		var count = $("#count_"+$(this).val()).val();
		var unitPrice = $("#unitPrice_"+$(this).val()).val();
		var price = parseFloat(count)*parseFloat(unitPrice);
		totalMoney = totalMoney+price;
		totalCount = parseInt(totalCount)+parseInt(count);
	});
	$("#totalMoney").val(totalMoney);
	$("#showTotalMoney").html(moneyFormatterNoY(totalMoney));
	$("#totalCount").html(totalCount);
	//console.log(chk_value.length==0 ?'你还没有选择任何内容！':chk_value); 
}

function updateCart(cartId,buyNum,obj){
	$.ajax({
		type : "post",
		url : ctx+"/carts/updateCartBuyNum.do",
		data : "cartId="+cartId+"&buyNum="+buyNum,
		dataType : "json",
		success : function(data) {
			if (data.flag == 1) {
				
			} else if (data.flag == 2) {
				// 无权限
				alertMsg(data.msg,"/index.do");
			} else if (data.flag == 3) {
				alertMsg(data.msg,"/login.do");
			} else {
				alertMsg(data.msg);
			}
			if($("#count_"+cartId).val()>1){
				$(obj).removeAttr('disabled');
			}
		},
		error : function(messg) {
			alertMsg("修改数量失败："+messg.responseText);
			if($("#count_"+cartId).val()>1){
				$(obj).removeAttr('disabled');
			}
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
				alertMsg(data.msg,"/orders/pay.do?orderId="+data.data);
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
			alertMsg("修改数量失败："+messg.responseText);
		}
	});
}
