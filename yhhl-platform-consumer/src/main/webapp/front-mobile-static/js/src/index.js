var getDataFlag = true;
$(document).ready(function(){
	
	$('#ajax_loading').css("display","block"); //显示加载时候的提示
	getIndexList(1,2);
	gotoNextPage(1,2);
});

function getIndexList(page,rows){
	$.ajax({
		type: "get",
		url: ctx+"/products/getFrontProductsDatas.do?t="+new Date(),
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
				//return;
			}
			$.each(rows,function(index, item) {
				//console.log(item.prodName);
				var div = $('<div class="index-pro1-list">');
				var dl = $('<dl>');
				var dt = $('<dt><a href="'+ctx+'/front/prodDetail.do?prodId='+item.prodId+'"><img src='+getFirstImg(ctx,item.imgUrl)+' /></a></dt>');
				var dd1 = $('<dd class="ip-text"><a href="'+ctx+'/front/prodDetail.do?prodId='+item.prodId+'">'+item.prodName+'</a></dd>');
				var dd2 = $('<dd class="ip-text" style="padding-left:0px;"><span>库存余&nbsp;'+item.stockSituation+'&nbsp;件</span>&nbsp;&nbsp;<span>已售&nbsp;'+item.sellNum+'&nbsp;件</span></dd>');
				var dd3 = $('<dd class="ip-price"><strong>¥'+item.unitPriceSell+'</strong></dd>');
				dl.append(dt);
				dl.append(dd1);
				dl.append(dd2);
				dl.append(dd3);
				div.append(dl);
				$("#prodList").append(div);
			});
			var divClear = $('<div class="clearfix" id="clearDiv"></div>');
			$("#prodList").append(divClear);
			$('#ajax_loading').css("display","none"); 
		},
		error:function(messg)  { 
       	    $.messager.alert('错误提示', '操作失败:'+messg.responseText, 'error');
       } 
	});
}

function gotoNextPage(page,rows){
	$(window).scroll(function(){
        if($(document).scrollTop()>=$(document).height()-$(window).height()){
        	$('#ajax_loading').css("display","block"); //显示加载时候的提示
        	if(getDataFlag){
        		page = page+1;
        		getIndexList(page,rows);
        	}else{
        		setTimeout(function(){
        			$('#ajax_loading').css("display","none"); //显示加载时候的提示
        		},2000);
        	}
        }
	});
}