var getDataFlag = true;
pageSize = 10;

$(document).ready(function(){
	
	$('#ajax_loading').css("display","block"); //显示加载时候的提示
	getProdList(1,pageSize);
	gotoNextPage(1,pageSize);
	getAllCategory();
	// 筛选、排序
	$('.retrie dt a').click(function(){
		var $t=$(this);
		if($t.hasClass('up')){
			$(".retrie dt a").removeClass('up');
			$('.downlist').hide();
			$('.mask').hide();
		}else{
			$(".retrie dt a").removeClass('up');
			$('.downlist').hide();
			$t.addClass('up');
			$('.downlist').eq($(".retrie dt a").index($(this)[0])).show();
			$('.mask').show();
		}
	});
	$(".area ul li a:contains('"+$('#area').text()+"')").addClass('selected');
	$(".wage ul li a:contains('"+$('#wage').text()+"')").addClass('selected');
});

function getProdList(page,rows,type){
	var param = "page="+page+"&rows="+rows;
	if(type!=null && typeof(type)!="undefined" && type!='' && type!=undefined){
		param = "page="+page+"&rows="+rows+"&filter_type="+type;
	}
	$.ajax({
		type: "get",
		url: ctx+"/getFrontProductsDatas.do?t="+new Date(),
		data: param,
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

function getAllCategory(){
	$.ajax({
		type: "get",
		url: ctx+"/category/getCategoryDatas.do?t="+new Date(),
		data: "page=1&rows=0",
		dataType: "json",
		success: function(data){
			$.each(data.rows,function(index, item) {
				var space = "";
				var wbs = item.wbs.split("-");
				if(wbs.length>1){
					space="|";
				}
				for(var i=1; i<wbs.length;i++){
					space = space+"----";
				}
				space = space+item.catName;
				var cat = $('<li><a href="javascript:void(0);">'+space+'</a></li> ');
				cat.on("click",function(){
					$("#prodList").empty();
					getProdList(1,pageSize,item.wbs);
					hideShow();
				});
				$("#category").append(cat);
			});
		},
		error:function(messg)  { 
			console.log(messg.responseText);
       } 
	});
}

function hideShow(){
	$(".retrie dt a").removeClass('up');
	$('.downlist').hide();
	$('.mask').hide();
}

function prodOrder(){
	$("#prodList").empty();
	getProdList(1,pageSize,item.wbs);
	hideShow();
}