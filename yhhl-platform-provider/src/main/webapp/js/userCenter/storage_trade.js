
$(function() {// 页面加载时，进行绑定
	$(".trade").click(function() {
		$("#prodName").text($(this).children('input').eq(1).val());
		$("#prodId").text($(this).children('input').eq(0).val());
		$("#priceLatest").text("￥" + $(this).children('input').eq(2).val());
		var num = $(this).parents().parents().children('.c1').text();
		$(".availableNum").text(num);
		$("#availableNum").val(num);
		showTradeDialog();
	});
	// 数量加减
	$('.tl_trade_operate').on('click', function() {
		var $mount = $(this).siblings('.tl_trade_mount').val();
		if ($(this).hasClass('tl_trade_minus')) {
			if ($mount < 1) {
				return false;
			}
			$mount--;
		} else if ($(this).hasClass('tl_trade_add')) {
			$mount++;
		} else {
			return false;
		}
		$(this).siblings('.tl_trade_mount').val($mount);
	});
	// 挂单参数请求
	$("#dosell").click(function() {
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/balance/orderUp.do",
			dataType : "json",
			traditional : true,
			contentType : "application/json; charset=utf-8",
			async : true,
			cache : false,
			data : JSON.stringify({
				'prodId' : $("#prodId").text(),
				'prodName' : $("#prodName").text(),
				'num' : $("#availableNum").val(),
				'priceUnit' : $("#priceUnit").val(),
				'orderType' : 'buy'
			}),
			success : function(data) {
				hideTradeDialog();
				if (data != null) {
					$("#transactionid").val(data.transactionid);
					$("#merId").val(data.merId);
					$("#freezeamt").val(data.freezeamt);
					$("#copies").val(data.copies);
					$("#ordertime").val(data.ordertime);
					$("#bgurl").val(data.bgurl);
					$("#signData").val(data.signData);
					$("#userId").val(data.userId);
					$("#orderUpForm").attr("action", data.url);
					orderUpFormSubmit();
				} else {
					tradMes("挂单参数请求失败！");
				}
			},
			error : function() {
				tradMes("挂单参数请求失败！");
			}
		});
	});

});
// 交易状态提醒
function tradMes(data) {
	hideTradeDialog();
	$("tradeing_layer .tl_ul li label").text("");
	$("tradeing_layer .tl_ul li label").text(data);
	showTradeingDialog();
}
// 挂单表单提交
function orderUpFormSubmit() {
	$.ajax({
		type : "GET",
		dataType : 'jsonp',
		jsonp : 'callback',
		url : $('#orderUpForm').attr("action"),
		data : $('#orderUpForm').serialize(),
		success : function(result) {
			msg = "挂单交易中请等待~";
			if (result != null) {
				if (result.ret_code == '000000') {
					freezeFormSubmit(result);

				} else {
					msg = "挂单失败,失败原因：" + result.rsp_msg;
				}

			} else {
				msg = "挂单失败,失败原因：冻结请求失败！";
			}
			tradMsg(msg);
		},
		error : function(event, XMLHttpRequest, ajaxOptions, thrownError) {
			tradMsg("挂单失败,失败原因：冻结请求失败！");
		}

	});
}
// 冻结表单提交
function freezeFormSubmit(data) {
	$("#partic_user_id").val(data.partic_user_id);
	$("#order_id").val(data.order_id);
	$("#mer_date").val(data.mer_date);
	$("#amount").val(data.amount);
	$("#partic_account_id").val(data.partic_account_id);
	$("#freezeForm").attr("action", data.freeAmtUrl);
	console.log(data.freeAmtUrl);
	$("#freezeForm").submit();
}
// 显示遮罩层
function showLayer() {
	$(".mask_layer").show();
	fillToBody(".mask_layer");
}
// 隐藏遮罩层
function hideLayer() {
	$(".mask_layer").hide();
}
// 显示交易中弹层
function showTradeingDialog() {
	$(".tradeing_layer").show();
	autoCenter(".tradeing_layer");
	showLayer();
}
// 显示交易弹层
function showTradeDialog() {
	$(".trade_layer").show();
	autoCenter(".trade_layer");
	showLayer();
}
// 隐藏交易弹层
function hideTradeDialog() {
	$(".trade_layer").hide();
	$(".mask_layer").hide();
	hideLayer();
}
// 自动居中元素
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
// 自动扩展元素到全部显示区域
function fillToBody(el) {
	$(el).width($(window).outerWidth(true));
	$(el).height($(window).outerHeight(true));
}
// 屏幕大小改变时， 保持弹层居中
$(window).resize(function() {
	fillToBody(".mask_layer");
	if ($(".login_layer").css("display") === "block") {
		autoCenter(".login_layer");
	} else {
		autoCenter(".trade_layer");
	}
});