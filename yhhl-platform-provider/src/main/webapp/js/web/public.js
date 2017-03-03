$(function(){
	//搜索框
	placeHolder(".search-input");
	//首页导航条
	$(".nav a").hover(function(){
		$(this).addClass("on");
	},function(){
		$(this).removeClass("on");
	}).click(function(){
		$(this).addClass("on1").parent().siblings().find("a").removeClass("on1");
	});
	//我的关注
	$(".attention").mouseenter(function(){
		$(this).children("a").addClass("on");
		$(".attention-list").show();
	}).mouseleave(function(){
		$(this).children("a").removeClass("on");
		$(".attention-list").hide();
	})
	//banner图切换
	$(".fullSlide").slide({
		titCell:".banner-btns li",
		mainCell:".banners ul",
		effect:"fold",
		autoPlay:true,
		delayTime:700
	});
})
//模拟placeholder
function placeHolder(obj){
	var txt = $(obj).val();
	$(obj).focus(function(){
		if($(this).val() == txt){
			$(this).val("");
		}
	})
	$(obj).blur(function(){
		if($(this).val() == ""){
			$(this).val(txt);
		}
	})
}
