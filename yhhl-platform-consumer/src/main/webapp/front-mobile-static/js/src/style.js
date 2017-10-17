// JavaScript Document
$(function(){
	//.bankList li 银行选择	
	$(".bankList li").click(function(){
		$(this).addClass("banSty").siblings("li").removeClass("banSty");
		})	
	//添加银行卡	
	$(".tianjiayinhang").click(function(){
		$(".addYinhang").fadeIn();
		})	
	$(".glyphicon-remove").click(function(){
		$(".addYinhang").fadeOut();
		})	
	$(".proinfoList:first").fadeIn();
		
	});