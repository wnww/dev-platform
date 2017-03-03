$(function(){
	//我的关注
	$(".attention").mouseenter(function(){
		$(this).children("a").addClass("on");
		$(".attention-list").show();
	}).mouseleave(function(){
		$(this).children("a").removeClass("on");
		$(".attention-list").hide();
	})
	var _btns = $(".banner-btns");
	var _lis = _btns.find("li");
	var _width = _lis.length * _lis.outerWidth(true);
	_btns.css("margin-left",-(_width/2));
	//显示和隐藏倒计时弹层
	$(".close").click(function(){
		$(this).parent(".countdown-layer").hide();
		$(".countdown-sm").show();
	});
	$(".open").click(function(){
		$(this).parent(".countdown-sm").hide(10);
		$(".countdown-layer").show();
	});
	//藏品分类
	$(".all-coll>a").click(function(){
		if($(this).hasClass("on")){
			return false;
		}else{
			$(this).addClass("on").siblings("li").removeClass("on");
			$(".sort-list>li").removeClass("on");
		}
	});
	var scrollTimer;
	var _index = $(".sort-list .on").index();
	autoPlays(".infos");
	function autoPlays(obj){
		var $this = $(obj).eq(_index);
		scrollTimer = setInterval(function(){
			AutoScroll($this);
		},6000);
	}
	$(".infos").mouseenter(function(){
		clearInterval(scrollTimer);
	}).mouseleave(function(){
			_index = $(this).index()-1;
			autoPlays(".infos");
	});
	//$(".sort-list>li").click(function(){
	$(".sort-list").delegate(".test","click",function(){
		var _index2 = $(this).index();
		var $this = $(".infos").eq(_index2);
		if($(this).hasClass("on")){
			return false;
		}else{
			$(this).addClass("on").siblings("li").removeClass("on");
			if($(".all-coll>a").hasClass("on")){
				$(".all-coll>a").removeClass("on");
			}
		}
		$(".infos").hide().eq(_index2).show();
		clearInterval(scrollTimer);
		_index = $(this).index();
		autoPlays(".infos");
	});
	//隔行背景颜色不同
	var trs = $(".data-table").find("tr");
	var trLen = trs.length;
	for(var i=0;i<trLen;i++){
		trs.eq(2*i).addClass("trbgcolor");
	}
	//全部藏品列表上下切换
	var _idx = 0;
	var _top = 0;
	var _height = $('.sort-list>li').outerHeight(true);
	var _len = $('.sort-list>li').length;
	$(".arrow-down").click(function(){
		if(!$(".sort-list").is(":animated")){
			if(_idx <= _len-9){
				_idx++;
				_top -= _height;
				$(".sort-list").animate({"top":_top},500);
				$(".arrow-up").removeClass("disabled");
			}else{
				$(this).addClass("disabled");
				return false;
			}
		}
	});
	$(".arrow-up").click(function(){
		if(!$(".sort-list").is(":animated")){
			if(_idx > 0){
				_idx--;
				_top += _height;
				$(".sort-list").animate({"top":_top},500);
				$(".arrow-down").removeClass("disabled");
			}else{
				$(this).addClass("disabled");
				return false;
			}
		}
	})
	//交易须知详情显示&隐藏
	$(".notice-list li").each(function(index){
		$(this).mouseenter(function(){
			$(this).addClass("on");
			$(".notice-cont").eq(index).slideDown(200);	
		}).mouseleave(function(){
			$(this).removeClass("on");
			$(".notice-cont").eq(index).slideUp(0);
		});
		$(".notice-cont").mouseenter(function(){
			var _index = $(".notice-cont").index(this);
			$(".notice-cont").stop();
			$(".notice-list li").eq(_index).addClass("on");
			$(this).show();
		}).mouseleave(function(){
			$(".notice-list li").eq(index).removeClass("on");
			$(this).slideUp(0);
		});
	});
	 //模拟复选框
    $('.ll_dl dt').on('click',function(){
        $(this).toggleClass('checked_pwd');
    })
    //输入框提示
    $('.placeholder').focus(function(){ 
        if($(this).val()==$(this).attr('data-value')){ 
        	$(this).val('').css({'color':'#333'});
        }
    }).blur(function(){ 
        if($(this).val()==''){ 
	        $(this).val($(this).attr('data-value')).css({'color':'#cccccc'}); 
	    }
    }).trigger('blur');
	
    
    //表格滑过效果
    $('.tl_tbody tr').on('mouseenter',function(){
        $(this).css('background-color','#eaeaea')
    }).on('mouseleave',function(){
        $(this).css('background-color','#fff')
    })
})
//上下滑动函数
function AutoScroll(obj) {
	var _moveH = $(obj).find("li").outerHeight(true);
	$(obj).find("ul:first").animate({
		marginTop: -_moveH +"px"
	}, 700, function () {
	    $(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
	});
}
//自动居中元素
function autoCenter(el){
	var bodyW = $(window).width();
	var bodyH = $(window).height();
	var elW = $(el).outerWidth(true);
	var elH = $(el).outerHeight(true);
	$(el).css({
		"left":(bodyW-elW)/2,
		"top":(bodyH-elH)/2
	});
}
//自动扩展元素到全部显示区域
function fillToBody(el){
	$(el).width($(window).outerWidth(true));
	$(el).height($(window).outerHeight(true));
}