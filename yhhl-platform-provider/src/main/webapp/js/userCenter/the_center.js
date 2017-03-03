$(function(){
	//我的关注
	$(".attention").mouseenter(function(){
		$(this).children("a").addClass("on");
		$(".attention-list").show();
	}).mouseleave(function(){
		$(this).children("a").removeClass("on");
		$(".attention-list").hide();
	});
	//导航条
	$(".nav a").hover(function(){
		$(this).addClass("on");
	},function(){
		$(this).removeClass("on");
	});
	//模拟单选
	$(".fund_radio span").on("click",function(){
		var $parLi = $(this).parents('li');
		$parLi.addClass('fund_li_selected').siblings('li').removeClass('fund_li_selected');
		var $liIndex = $(this).parents('ul.fund_radio').find('.fund_li_selected').index();
		if($liIndex == 0){
			$('.fund_btn').text('确定出金');
		}else if($liIndex == 1){
			$('.fund_btn').text('确定入金');
		}else{
			return false;
		}

	});
	//模拟下拉
	$('.record_select span').on('click',function(){
		$(this).siblings('ul').show();
	});
	$('.record_select').on('mouseleave',function(){
		$(this).children('ul').hide();	
	});
	$('.record_select li').on('mouseover',function(){
		$(this).addClass('record_li_chosen');	
	}).on('mouseleave',function(){
		$(this).removeClass('record_li_chosen');	
	}).on('click',function(){
		var $liTxt = $(this).text();
		var $liVal = $(this).val();
		if($liVal!=0){
			$("#identitytype_err").hide();
		}
		$(this).parent().siblings('span').text($liTxt);
		$(this).parent().siblings('span').val($liVal);
		$(this).parent('ul').hide();	
	});
	//左侧栏目划过效果
	$('.center_left_list a').on('mouseenter',function(){
		$(this).addClass('list_item_hover');
	}).on('mouseleave',function(){
		$(this).removeClass('list_item_hover');	
	});
	//左侧栏目点击
	$('.center_left_list a').on('click',function(){
		$(this).addClass('list_item_chosen');
		$(this).parent().siblings().removeClass('list_item_chosen');
	});
	//表格划过
	$('.item_tab tbody tr').hover(function(){
		var $height = $(this).height();
		var $width = $(this).width();
		var $left = $(this).position().left;
		var $top = $(this).position().top;
		$('.float_tr').css({'height':$height-1,'width':$width-1,'left':$left,'top':$top}).show();
		if((navigator.userAgent.split(";")[1].toLowerCase().indexOf("msie 6.0")=="-1") || (navigator.userAgent.split(";")[1].toLowerCase().indexOf("msie 7.0")=="-1")){
			$('.float_tr').css({'height':$height-1,'width':$width,'left':$left,'top':$top}).show();
		}
		
	});
	$('.tab_lay').mouseleave(function(){
		$('.float_tr').hide();	
	});
	//右侧高度控制
	/*if($('.right_main').height()<539){
		$('.right_main').height(539);
	}*/
	
});

//自动居中元素
function autoCenter(el) {
	var bodyW = $(window).width();
	var bodyH = $(window).height();
	var elW = $(el).outerWidth(true);
	var elH = $(el).outerHeight(true);
	$(el).css({
		"left" : (bodyW - elW) / 2,
		"top" : (bodyH - elH) / 2
	});
}
//自动扩展元素到全部显示区域
function fillToBody(el) {
	$(el).width($(window).outerWidth(true));
	$(el).height($(window).outerHeight(true));
}
//屏幕大小改变时， 保持弹层居中
$(window).resize(function() {
	fillToBody(".mask_layer");
	if ($(".login_layer").css("display") === "block") {
		autoCenter(".login_layer");
	} else {
		autoCenter(".trade_layer");
	}
});
//显示遮罩层
function showLayer() {
	$(".mask_layer").show();
	fillToBody(".mask_layer");
}
//隐藏遮罩层
function hideLayer() {
	$(".mask_layer").hide();
}

function showTradeDialog() {
	$(".trade_layer").show();
	autoCenter(".trade_layer");
	showLayer();
}

function hideTradeDialog() {
	$(".trade_layer").hide();
	$(".mask_layer").hide();
	hideLayer();
}
