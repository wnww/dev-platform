var getDataFlag = true;
var currentPage = 1;
$(document).ready(function(){
	
	$('#ajax_loading').css("display","block"); //显示加载时候的提示
	
});

function getOrderList(rows,status){
	$.ajax({
		type: "get",
		url: ctx+"/orders/getMyOrdersDatas.do?t="+new Date(),
		data: "page="+currentPage+"&rows="+rows+"&filter_status="+status,
		dataType: "json",
		beforeSend:function(){
            
        },
		success: function(data){
			var rows = data.rows;
			//$("#prodList").empty();
			$("#clearDiv").remove();
			if(rows.length==0){
				getDataFlag = false;
				$('#ajax_loading').css("display","none");
			}
			if(currentPage==1&&rows.length==0){
				$("#orderList").append('<div style="text-align:center;margin-top:30px;"><span style="font-size:16px; border: 1px solid #f60;border-color: #ff3800;border-radius:3px;background: #ffeecc; width:90%;padding-left:30%;padding-right:30%">没有订单!</span></div>');
			}
			
			$.each(rows,function(index, item) {
				getDataFlag = true;
				//console.log(item.prodName);
				var div = $('<div class="dingdanlist">');
				var table = $('<table>');
				// 第一行 tr
				var tr1 = $('<tr>');
				var tr1Td1 = $('<td colspan="2" width="65%">订单号：<strong>'+item.orderId+'</strong></td>');
				tr1Td1.on("click",function(){
					if(status==11){
						document.location.href=ctx+"/orders/pay.do?orderId="+item.orderId;
					}
				});
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

function gotoNextPage(rows,status){
	orderStatus = status;
	$(window).scroll(function(){
		var stp = $(document).scrollTop()+40;
		var sub = $(document).height()-$(window).height();
        if(stp >= sub){
        	$('#ajax_loading').css("display","block"); //显示加载时候的提示
        	if(getDataFlag){
        		currentPage = currentPage+1;
        		getOrderList(rows,orderStatus);
        	}else{
        		setTimeout(function(){
        			$('#ajax_loading').css("display","none"); //显示加载时候的提示
        		},2000);
        	}
        }
	});
}