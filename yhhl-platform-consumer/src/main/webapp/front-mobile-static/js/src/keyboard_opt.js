function checkClient(){
	if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
	   return "ios"
	} else if (/(Android)/i.test(navigator.userAgent)) {
		return "android";
	} else {
		return "pc";
	};
}
$(document).ready(function(){
	//解决弹出键盘后吸底导航变形问题
	if(checkClient()=="android"){
		var clientHeight = document.documentElement.clientHeight || document.body.clientHeight;
		$(window).on('resize', function () {
		    var nowClientHeight = document.documentElement.clientHeight || document.body.clientHeight;
		    if (clientHeight > nowClientHeight) {
		        //键盘弹出的事件处理
		    	$("#footNav").css("position","absolute");
		    }
		    else {
		        //键盘收起的事件处理
		    	$("#footNav").css("position","fixed");
		    }
		});
	}else if(checkClient()=="ios"){
		$(document).on('focusin', function () {
		　　//软键盘弹出的事件处理
		$("#footNav").css("position","absolute");
		});
		 
		$(document).on('focusout', function () {
		　　//软键盘收起的事件处理
			$("#footNav").css("position","fixed");
		});
	}
});
