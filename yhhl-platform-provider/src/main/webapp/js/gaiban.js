$(function(){
//	首页多行文本溢出省略
	$('.s_classCont .s_contTxt').dotdotdot({ });
	$('.s_itemCont .s_itemTxt').dotdotdot({ });
	
//////////  首页banner图轮播

	var dotKey = 0;
	var imgKey = 0;
	var banerTimer = null;
	var bannerFn= function(){
		//切换点变化
		dotKey++;
		if(dotKey>2){
			dotKey = 0;
		}
		$('.s_dot li').eq(dotKey).addClass('current').siblings().removeClass('current');
		//banner图变化
		imgKey++;
		if(imgKey > 2){
			imgKey = 0;
		}
		$('.s_banner li').eq(imgKey).stop().fadeIn(500).siblings().stop().fadeOut(500);	
	}	
	//	默认每3秒banner切换一次
	bannerTimer = setInterval(bannerFn,3000);
	
	//	点击切换点，banner图切换
	$('.s_dot li').click(function(){
		$(this).addClass('current').siblings().removeClass('current');
		$('.s_banner li').eq($(this).index()).fadeIn(50).siblings().fadeOut(50);
		dotKey = $(this).index();
		imgKey = $(this).index();
	});
	
	//	鼠标划过时，暂停定时器
	$('.s_banner').hover(function(){
		clearInterval(bannerTimer);
	},function(){
		bannerTimer = setInterval(bannerFn,3000);
	});
	
//////////	商品列表页点击选择藏品类型
	$('.s_listTitRight a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
	})
})
