var getDataFlag = true;
$(document).ready(function(){
	
	$('#ajax_loading').css("display","block"); //显示加载时候的提示
	getIndexList(1,pageSize);
	gotoNextPage(1,pageSize);
	getRecommendList();
	$("#aboutMyMall").on("click",function(){
		$("#myModal").modal('show');
	});
	
	$("#secondCode").on("click",function(){
		$("#mySecondCodeModal").modal('show');
	});
	$("#mySecondCodeModal").on("click",function(){
		$("#mySecondCodeModal").modal('hide');
	});
});

function getIndexList(page,rows){
	$.ajax({
		type: "get",
		url: ctx+"/getFrontProductsDatas.do?t="+new Date(),
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
			$("#totalCount").html(data.total);
			$.each(rows,function(index, item) {
				//console.log(item.prodName);
				var div = $('<div class="index-pro1-list">');
				var dl = $('<dl>');
				var dt = $('<dt><a href="'+ctx+'/prodDetail.do?prodId='+item.prodId+'"><img src='+getFirstImg(ctx,item.imgUrl)+' /></a></dt>');
				var dd1 = $('<dd class="ip-text"><a href="'+ctx+'/prodDetail.do?prodId='+item.prodId+'">'+item.prodName+'</a></dd>');
				var dd2 = $('<dd class="ip-text" style="padding-left:0px;"><span>库存&nbsp;'+item.stockSituation+'&nbsp;件</span>&nbsp;&nbsp;<span>已售&nbsp;'+item.sellNum+'&nbsp;件</span></dd>');
				var dd3 = $('<dd class="ip-price"><strong>¥'+moneyFormatterNoY(item.unitPriceSell / 100)+'</strong></dd>');
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
        		getIndexList(page,rows);
        	}else{
        		setTimeout(function(){
        			$('#ajax_loading').css("display","none"); //显示加载时候的提示
        		},2000);
        	}
        }
	});
}

function getRecommendList(){
	$.ajax({
		type: "get",
		url: ctx+"/getFrontProductsDatas.do?t="+new Date(),
		data: "page=1&rows=3&filter_recommend=1",
		dataType: "json",
		success: function(data){
			if(data.rows.length>0){
				$("#sliderA").empty();
			}
			$.each(data.rows,function(index, item) {
				var img = $('<img src="'+getFirstImg(ctx,item.imgUrl)+'"/>');
				img.on("click",function(){
					document.location.href=ctx+"/prodDetail.do?prodId="+item.prodId;
				});
				$("#sliderA").append(img);
			});
			$("#sliderA").excoloSlider();
		},
		error:function(messg)  { 
			//alertMsg(messg.responseText);
			console.log(messg.responseText);
       } 
	});
}