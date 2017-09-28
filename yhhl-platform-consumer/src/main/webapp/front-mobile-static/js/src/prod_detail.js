$(document).ready(function(){
	//getProdStockInfo();
	$(".guige li").click(function(){
		//$(this).addClass("guigeCur").siblings("li").removeClass("guigeCur");
		$(".guige li").removeClass("guigeCur");
		$(this).addClass("guigeCur");
		var children = $(this).children("input");
		$.each(children,function(idx, itm) {
			if(itm.id=="remainNum"){
				$("#showRemainNum").val(itm.value);
			}else if(itm.id=="selledNum"){
				$("#showSelledNum").val(itm.value);
			}
		});
	});
	
	var lis = $(".guige").children("li");
	$.each(lis,function(index, item) {
		console.log("=================="+$(this)[0].className);
		if($(this)[0].className=="guigeCur"){
			var children = $(this).children("input");
			$.each(children,function(idx, itm) {
				if(itm.id=="remainNum"){
					$("#showRemainNum").val(itm.value);
				}else if(itm.id=="selledNum"){
					$("#showSelledNum").val(itm.value);
				}
			});
		}
	});
	
	
});

function getProdStockInfo(){
}

