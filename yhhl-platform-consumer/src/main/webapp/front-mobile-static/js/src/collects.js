var getDataFlag = true;
$(document).ready(function(){
	
	$('#ajax_loading').css("display","block"); //显示加载时候的提示
	getCollectList(1,pageSize);
	gotoNextPage(1,pageSize);
});

function getCollectList(page,rows){
	$.ajax({
		type: "get",
		url: ctx+"/collects/getCollectsDatas.do?t="+new Date(),
		data: "page="+page+"&rows="+rows,
		dataType: "json",
		beforeSend:function(){
            
        },
		success: function(data){
			var rows = data.rows;
			if(rows.length==0){
				getDataFlag = false;
				$('#ajax_loading').css("display","none"); 
			}
			$("#showCollectCount").html(data.total);
			$.each(rows,function(index, item) {
				
				var div = $('<div class="dingdanlist" >')
				var table = $('<table>');
				// 第一个tr
				var tr1 = $('<tr>');
				var tr1td1 = $('<td colspan="3" width="100%" align="right"><div class="qingqu"><a href="javascript:void(0);" class="orange">取消收藏</a></div></td>');
				tr1td1.on("click",function(){
					deleteCollect(item.prodId);
				});
				tr1.append(tr1td1);
				table.append(tr1);
				// 第二个tr
				var tr2 = $('<tr>');
				var tr2td1 = $('<td class="dingimg" width="30%"><img src="'+getFirstImg(ctx,item.imgUrl)+'" /></td>');
				var tr2td2 = $('<td width="50%">');
				var h3 = $('<h3>'+item.prodName+'</h3>');
				var time = $('<time>收藏时间：'+dateFormat(item.createTime)+'</time>');
				tr2td2.append(h3);
				tr2td2.append(time);
				var tr2td3 = $('<td align="right"><img src="'+ctx+'/front-mobile-static/images/jian-new.png" /></td>');
				tr2.append(tr2td1);
				tr2.append(tr2td2);
				tr2.append(tr2td3);
				tr2.on("click",function(){
					document.location.href=ctx+"/prodDetail.do?prodId="+item.prodId;
				});
				table.append(tr2);
				// 第三个tr
				var tr3 = $('<tr>');
				var tr3th1 = $(' <th colspan="3"><strong class="orange">¥36.60</strong></th>');
				tr3.append(tr3th1);
				table.append(tr3);
				div.append(table);
				$('#prodList').append(div);
			});
			$('#ajax_loading').css("display","none"); 
		},
		error:function(messg)  { 
			alertMsg(messg.responseText);
       } 
	});
}

function gotoNextPage(page,rows){
	$(window).scroll(function(){
		var stp = $(document).scrollTop()+40;
		var sub = $(document).height()-$(window).height();
        if(stp >= sub){
        	$('#ajax_loading').css("display","block"); //显示加载时候的提示
        	if(getDataFlag){
        		page = page+1;
        		getCollectList(page,rows);
        	}else{
        		setTimeout(function(){
        			$('#ajax_loading').css("display","none"); //显示加载时候的提示
        		},2000);
        	}
        }
	});
}

function deleteCollect(prodId){
	if(prodId=='' && $("#showCollectCount").html()==0){
		alertMsg("没有收藏过商品");
		return;
	}
	var add_collect_url = ctx + "/collects/delCollects.do?t="+new Date();
	$.ajax({
		type: "get",
		url: add_collect_url,
		data: "prodId="+prodId,
		dataType: "json",
		success: function(data){
			if (data.flag == 1) {
				alertMsg(data.msg);
				$("#prodList").empty();
				getCollectList(1,pageSize);
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