$(function(){
	//面包屑
	$('.choice_span').on('click',function(){
		$(this).toggleClass('choice_chosen');
	})
	//藏品介绍和价格走势切换
	$('.dm_li').on('click',function(){
		$(this).addClass('dm_li_chosen').siblings('li').removeClass('dm_li_chosen');
		$('.dm_part').eq($(this).index('.dm_li')).show().siblings('.dm_part').hide();
	})
	//买入和卖出切换
	$('.detail_trade li').on('click',function(){
		$(this).addClass('trade_li_chosen').siblings('li').removeClass('trade_li_chosen');
		$('.trade_content').eq($(this).index('.detail_trade li')).show().siblings('.trade_content').hide();
	})
	//数量加减
	$('.trade_operate').on('click',function(){
		var $mount = $(this).siblings('.trade_mount').val();
		if($(this).hasClass('trade_minus')){
			if($mount<1){return false;}
			$mount--;
		}else if($(this).hasClass('trade_add')){
			$mount++;
		}else{
			return false;
		}
		$(this).siblings('.trade_mount').val($mount);
	})
	//关注商品
	//$('.attention_p').on('click',function(){
	//	$(this).find('a').toggleClass('attention_a_chosen');
	//	$(this).find('a').hasClass('attention_a_chosen')?$(this).find('span').text('已关注该藏品'):$(this).find('span').text('关注该藏品');
	//})
	//表格滑过效果
	$('.ds_tbody tr').on('mouseenter',function(){
		$(this).css('background-color','#eaeaea')
	}).on('mouseleave',function(){
		$(this).css('background-color','#fff')
	})
	//列表成交切换
	$('.list_rank li').on('click',function(){
		$(this).siblings('.list_rank li').removeClass('rank_li_chosen').removeClass('rank_li_chosen_un');
		if($(this).hasClass('rank_li_chosen')){
			$(this).addClass('rank_li_chosen_un').removeClass('rank_li_chosen');
		}else if($(this).hasClass('rank_li_chosen_un')){
			$(this).addClass('rank_li_chosen').removeClass('rank_li_chosen_un');
		}else{
			$(this).addClass('rank_li_chosen');
		}
	})
	//页码跳转
	$('.num_page').on('click',function(){
		$(this).addClass('page_chosen').siblings('.num_page').removeClass('page_chosen');
	})
	//选择分类
	$('.list_classes span').livequery('click',function(){
		$('.list_classes span').removeClass('span_chosen');
		$(this).addClass('span_chosen');
		bind(1);
	}).livequery('mouseenter',function(){
		$(this).addClass('span_hover');
	}).livequery('mouseleave',function(){
		$(this).removeClass('span_hover');
	});
	$('.list_classes a').on('click',function(e){
		$(this).parents('span').removeClass('span_chosen');
		$(this).parent().removeClass('span_hover');
		e.stopPropagation();
		bind(1);
		
	});
	//图片放大镜效果
	$(".jqzoom").jqueryzoom({xzoom:380,yzoom:410});
	$('.pc_item').on('mouseleave',function(){
		$(this).removeClass('pc_item_chosen');
	})
})
//鼠标经过预览图片函数
function preview(img){
	$(img).parents('.pc_item').addClass('pc_item_chosen');
	$(img).parents('.pc_item').siblings('.pc_item').removeClass('pc_item_chosen');
	$("#preview .jqzoom img").attr("src",$(img).attr("src"));
	$("#preview .jqzoom img").attr("jqimg",$(img).attr("bimg"));
}
