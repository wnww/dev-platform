var getDataFlag = true;
pageSize = 10;
$(document).ready(function(){
	
	$('#ajax_loading').css("display","block"); //显示加载时候的提示
	getProdList(1,pageSize);
	gotoNextPage(1,pageSize);
});

function getProdList(page,rows){
	$.ajax({
		type: "get",
		url: ctx+"/getFrontProductsDatas.do?t="+new Date(),
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
			$.each(rows,function(index, item) {
				var dl = $('<dl>');
				var dt = $('<dt style="width:30%"><a href="'+ctx+'/prodDetail.do?prodId='+item.prodId+'"><img src="'+getFirstImg(ctx,item.imgUrl)+'" width="150" /></a></dt>');
				var dd = $('<dd>');
				var h3 = $('<h3><a href="'+ctx+'/prodDetail.do?prodId='+item.prodId+'">'+item.prodName+'</a></h3>');
				var div1 = $('<div class="prolist-price"><strong>¥'+moneyFormatterNoY(item.unitPriceSell / 100)+'</strong></div>');
				var div2 = $('<div class="prolist-yishou"><em>库存：'+item.stockSituation+'</em> <em>已售：'+item.sellNum+'</em></div>');
				dd.append(h3);
				dd.append(div1);
				dd.append(div2);
				var divClearFix = $('<div class="clearfix"></div>');
				dl.append(dt);
				dl.append(dd);
				dl.append(divClearFix);
				$('#prodList').append(dl);
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
        		getProdList(page,rows);
        	}else{
        		setTimeout(function(){
        			$('#ajax_loading').css("display","none"); //显示加载时候的提示
        		},2000);
        	}
        }
	});
}
