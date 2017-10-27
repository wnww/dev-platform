var getDataFlag = true;
var currentPage = 1;
$(document).ready(function(){
	
	$('#ajax_loading').css("display","block"); //显示加载时候的提示
	getProdList(pageSize);
	gotoNextPage(pageSize);
	getAllCategory();
	// 检索
	$("#searchBtn").on("click",function(){
		$("#prodList").empty();
        getDataFlag = true;
        currentPage = 1;
        getProdList(pageSize,$("#category").val(),$("#prodOrder").val(),$("#keyWords").val());
	});
	
});

function getProdList(rows,type,orderBy,keyWords){
	var param = "page="+currentPage+"&rows="+rows;
	if(type!=null && typeof(type)!="undefined" && type!='' && type!=undefined){
		param = param+"&filter_type="+type;
	}
	if(orderBy!=null && typeof(orderBy)!="undefined" && orderBy!='' && orderBy!=undefined){
		param = param+"&filter_orderBy="+orderBy;
	}
	if(keyWords!=null && typeof(keyWords)!="undefined" && keyWords!='' && keyWords!=undefined){
		param = param+"&filter_keyWords="+keyWords;
	}
	$.ajax({
		type: "post",
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
				var h3 = $('<h3 style="white-space:normal;width:191px;"><a href="'+ctx+'/prodDetail.do?prodId='+item.prodId+'">'+item.prodName+'</a></h3>');
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

function gotoNextPage(rows){
	$(window).scroll(function(){
		var stp = $(document).scrollTop()+40;
		var sub = $(document).height()-$(window).height();
        if(stp >= sub){
        	$('#ajax_loading').css("display","block"); //显示加载时候的提示
        	if(getDataFlag){
        		currentPage = currentPage+1;
        		getProdList(rows,$("#category").val(),$("#prodOrder").val(),$("#keyWords").val());
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
			$($(".topmenu").children()[0]).find(".select_first_ul").empty();
			var all = $('<li><p wbs="" catName="全部">全部</p></li>');
			$($(".topmenu").children()[0]).find(".select_first_ul").append(all);
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
				var show = '<li><p wbs="'+item.wbs+'" catName="'+item.catName+'">'+space+'</p></li>';
				//var hideWbs = '<input type="hidden" id="wbs_'+item.wbs+'" name="wbs_'+item.wbs+'" value="'+item.wbs+'"/>';
				//var hideCatName = '<input type="hidden" id="catName_'+item.catName+'" name="catName_'+item.wbs+'" value="'+item.catName+'"/>';
				var cat = $(show);
				$($(".topmenu").children()[0]).find(".select_first_ul").append(cat);
			});
			
			selectMenuJs();
		},
		error:function(messg)  { 
			console.log(messg.responseText);
       } 
	});
}

// 分类样式下拉基础JS
function selectMenuJs(){
	var v_width= $(document.body).width();
    $(".select_textul").width(v_width);
    
    $(".select_textdiv").click(function(){
    	$(".select_textdiv").removeClass("divfocus");
    	$(this).addClass("divfocus");
    	$(this).siblings(".select_textul").fadeToggle(500);
        var lilength = $(this).siblings(".select_textul").find("li.focus").has(".select_second_ul").length;
    	if(lilength > 0){
    		$(this).siblings(".select_textul").find("li.focus>.select_second_ul").show();
    	}else{
    		$(".select_first_ul>li>p").css("width","100%");
    	}
    })
	$(".select_first_ul>li>p").click(function(){
		//$(".select_second_ul").hide();
		$(this).parent("li").addClass("focus").siblings("li").removeClass("focus");
		var choose = $(this).attr("catName");
		var chooseWbs = $(this).attr("wbs");
        $(this).parents(".select_textul").siblings(".select_textdiv").find(".s_text").text(choose);
        $(this).parents(".select_textul").siblings(".select_textdiv").find("input").val(chooseWbs);
        $(this).parents(".select_textul").fadeOut(300);
        $("#prodList").empty();
        getDataFlag = true;
        currentPage = 1;
        getProdList(pageSize,$("#category").val(),$("#prodOrder").val(),$("#keyWords").val());
	});
	
}


