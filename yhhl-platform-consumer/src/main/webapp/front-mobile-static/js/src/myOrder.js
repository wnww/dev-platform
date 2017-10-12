var getDataFlag = true;
$(document).ready(function(){
	
	$('#ajax_loading').css("display","block"); //显示加载时候的提示
	getOrderList(1,2);
	gotoNextPage(1,2);
});

function getOrderList(page,rows){
	$.ajax({
		type: "get",
		url: ctx+"/orders/getMyOrdersDatas.do?t="+new Date(),
		data: "page="+page+"&rows="+rows,
		dataType: "json",
		beforeSend:function(){
            
        },
		success: function(data){
			var rows = data.rows;
			//$("#prodList").empty();
			$("#clearDiv").remove();
			console.log("=============="+rows.length);
			if(rows.length==0){
				getDataFlag = false;
				$('#ajax_loading').css("display","none"); 
			}
			$.each(rows,function(index, item) {
				//console.log(item.prodName);
				var div = $('<div class="dingdanlist">');
				var table = $('<table>');
				// 第一行 tr
				var tr1 = $('<tr>');
				var tr1Td1 = $('<td colspan="2" width="65%">订单号：<strong>'+item.orderId+'</strong></td>');
				var tr1Td2 = $('<td width="35%" align="right"><div class="qingqu"><a href="javascript:;" class="orange">取消</a></div></td>');
				tr1.append(tr1Td1);
				tr1.append(tr1Td2);
				table.append(tr1);
				// 第二行 tr
				$.each(item.orderDetailList,function(idx, detail) {
					var tr2 = $('<tr>');
					var tr2Td1 = $('<td class="dingimg" width="30%"><img src="'+getFirstImg(ctx,detail.imgUrl)+'" /></td>');
					var tr2Td2 = $('<td width="50%">');
					var h3 = $('<h3>'+detail.prodName+'</h3>');
					var time = $('<time>下单时间：'+dateFormat(item.createTime)+'</time>');
					tr2Td2.append(h3);
					tr2Td2.append(time);
					var tr2Td3 = $('<td align="right"><img src="'+ctx+'/front-mobile-static/images/jian-new.png" /></td>');
					tr2.append(tr2Td1);
					tr2.append(tr2Td2);
					tr2.append(tr2Td3);
					table.append(tr2);
					tr2.on("click",function(){
						document.location.href=ctx+"/prodDetail.do?prodId="+detail.prodId;
					});
				});
				// 第三行 tr
				var tr3 = $('<tr>');
				var totalAmount = parseInt(item.orderAmount)+parseInt(item.expressFee);
				var th1 = $('<th colspan="3"><strong class="orange">¥'+moneyFormatterNoY(totalAmount/100)+'</strong></th>');
				tr3.append(th1);
				table.append(tr3);
				div.append(table);
				
				$("#orderList").append(div);
			});
			var divClear = $('<div class="clearfix" id="clearDiv"></div>');
			$("#prodList").append(divClear);
			$('#ajax_loading').css("display","none"); 
		},
		error:function(messg)  { 
       	    alertMsg('操作失败:'+messg.responseText);
       } 
	});
}

function gotoNextPage(page,rows){
	$(window).scroll(function(){
        if($(document).scrollTop()>=$(document).height()-$(window).height()){
        	$('#ajax_loading').css("display","block"); //显示加载时候的提示
        	if(getDataFlag){
        		page = page+1;
        		getOrderList(page,rows);
        	}else{
        		setTimeout(function(){
        			$('#ajax_loading').css("display","none"); //显示加载时候的提示
        		},2000);
        	}
        }
	});
}