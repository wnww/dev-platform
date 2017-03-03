$(function(){
	$(".input").each(function(){
		checkInput($(this));
		check($(this));
	});
	placeHolder1(".input-tips",".input-name");
	placeHolder1(".pwd-tips",".input-pwd");
	placeHolder1(".pwd2-tips",".input-pwd2");
});
function placeHolder1(obj1,obj2){
	$(obj2).keydown(function(event){
		$(obj1).hide();
	}).blur(function(){
		if($(obj2).val() === ""){
			$(obj1).show();
		}
	});
}

//模拟单选按钮
$(function() {
	$('.check').bind('click', function() {
		if($(this).hasClass('active')) {
			$(this).removeClass('active').find('input:checkbox').removeAttr('checked');
		} else {
			$(this).addClass('active').find('input:checkbox').attr('checked', 'checked');
			return false;
		}
	});
});
//input框交互效果
function checkInput(obj){
	var nameTxt = $(obj).val();
	$(obj).focus(function(){
		$(this).addClass("selected");
		$(this).removeClass("error");
	}).blur(function(){
		$(this).removeClass("selected");
	});
}
//输入错误提示
function check(obj){
	var name = "123";
	$(obj).blur(function(){
		if($(this).val() === name){
			$(this).addClass("error");
			$(this).siblings(".errorMsg").css({"display":"block"});
			$(this).siblings(".errorMsg").text("此用户名已被使用");
			$(this).removeClass("col666");
		}else{
			$(this).siblings(".errorMsg").css({"display":"none"});
		}
	});
}

