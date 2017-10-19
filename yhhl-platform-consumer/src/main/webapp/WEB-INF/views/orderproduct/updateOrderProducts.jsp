<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<title>添加-订单商品</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/import.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style type="text/css"> 
	#search{ 
		text-align: left; 
		position:relative; 
	} 
	.autocomplete{ 
		border: 1px solid #9ACCFB; 
		background-color: white; 
		text-align: left; 
	} 
	.autocomplete li{ 
		list-style-type: none; 
	} 
	.clickable { 
		cursor: default; 
	} 
	.highlight { 
		background-color: #eeeeee; 
	} 
</style> 
<script type="text/javascript">
	var inputForm = '#inputForm';
	$(function() {
		$(inputForm).form({
			success : function(data) {
				var result = jQuery.parseJSON(data);
				if (result.flag == 1) {
					$.messager.confirm('提交结果', '操作成功', function() {
						parent.colseAdd();// 关闭添加窗口
						parent.winReload();// 刷新列表
					});
				} else if (result.flag == 2) {
					$.messager.alert('提交结果', result.msg, 'info');
				} else if (result.flag == 3) {
					$.messager.alert('结果', '您还未登录，请先登录！', 'error', function(){
						document.location.href="${ctx}/sysManage/index.do";
					});
				} else {
					$.messager.alert('提交结果', '操作失败:' + result.msg, 'error');
				}
			},
			error : function(messg) {
				$.messager.alert('错误提示', messg.responseText, 'error');
			}
		});

		//取得div层 
		var $search = $('#search');
		//取得输入框JQuery对象 
		var $searchInput = $search.find('#_easyui_textbox_input1');
		//关闭浏览器提供给输入框的自动完成 
		$searchInput.attr('autocomplete', 'off');
		//创建自动完成的下拉列表，用于显示服务器返回的数据,插入在搜索按钮的后面，等显示的时候再调整位置 
		var $autocomplete = $('<div class="autocomplete"></div>').hide()
				.insertAfter('#submit');
		//清空下拉列表的内容并且隐藏下拉列表区 
		var clear = function() {
			$autocomplete.empty().hide();
		};
		//注册事件，当输入框失去焦点的时候清空下拉列表并隐藏 
		$searchInput.blur(function() {
			setTimeout(clear, 500);
		});
		//下拉列表中高亮的项目的索引，当显示下拉列表项的时候，移动鼠标或者键盘的上下键就会移动高亮的项目，想百度搜索那样 
		var selectedItem = null;
		//timeout的ID 
		var timeoutid = null;
		//设置下拉项的高亮背景 
		var setSelectedItem = function(item) {
			//更新索引变量 
			selectedItem = item;
			//按上下键是循环显示的，小于0就置成最大的值，大于最大值就置成0 
			if (selectedItem < 0) {
				selectedItem = $autocomplete.find('li').length - 1;
			} else if (selectedItem > $autocomplete.find('li').length - 1) {
				selectedItem = 0;
			}
			//首先移除其他列表项的高亮背景，然后再高亮当前索引的背景 
			$autocomplete.find('li').removeClass('highlight').eq(selectedItem)
					.addClass('highlight');
		};
		var ajax_request = function() {
			if($("#_easyui_textbox_input1").val()=="" || $("#_easyui_textbox_input1").val().length==0){
				return;
			}
			//ajax服务端通信 
			$.ajax({
				type : "post",
				url : "${ctx}/sysManage/products/getProductsDatas.do?t=" + new Date(),
				data : "page=1&rows=10&filter_prodName="
						+ $("#_easyui_textbox_input1").val(),
				dataType : "json",
				'success' : function(prods) {
					var data = prods.rows;
					if (data.length) {
						//遍历data，添加到自动完成区 
						$.each(data, function(index, term) {
							//创建li标签,添加到下拉列表中 
							$('<li style="height:25px"></li>').text(term.prodName+","+term.prodId).appendTo($autocomplete)
									.addClass('clickable').hover(
											function() {
												//下拉列表每一项的事件，鼠标移进去的操作 
												$(this).siblings().removeClass(
														'highlight');
												$(this).addClass('highlight');
												selectedItem = index;
											},
											function() {
												//下拉列表每一项的事件，鼠标离开的操作 
												$(this)
														.removeClass(
																'highlight');
												//当鼠标离开时索引置-1，当作标记 
												selectedItem = -1;
											}).click(function() {
										//鼠标单击下拉列表的这一项的话，就将这一项的值添加到输入框中
										$searchInput.val(term.prodName);
										$("#prodId").val(term.prodId);
										//清空并隐藏下拉列表 
										$autocomplete.empty().hide();
									});
						});//事件注册完毕 
						//设置下拉列表的位置，然后显示下拉列表 
						var ypos = $searchInput.position().top;
						var xpos = $searchInput.position().left;
						$autocomplete.css('width', "100%");
						$autocomplete.css({
							'position' : 'relative',
							'left' : xpos + "px",
							'top' : ypos + "px"
						});
						setSelectedItem(0);
						//显示下拉列表 
						$autocomplete.show();
					}
				}
			});
		};
		//对输入框进行事件注册 
		$searchInput
				.keyup(
						function(event) {
							//字母数字，退格，空格 
							if (event.keyCode > 40 || event.keyCode == 8
									|| event.keyCode == 32) {
								//首先删除下拉列表中的信息 
								$autocomplete.empty().hide();
								clearTimeout(timeoutid);
								timeoutid = setTimeout(ajax_request, 100);
							} else if (event.keyCode == 38) {
								//上 
								//selectedItem = -1 代表鼠标离开 
								if (selectedItem == -1) {
									setSelectedItem($autocomplete.find('li').length - 1);
								} else {
									//索引减1 
									setSelectedItem(selectedItem - 1);
								}
								event.preventDefault();
							} else if (event.keyCode == 40) {
								//下 
								//selectedItem = -1 代表鼠标离开 
								if (selectedItem == -1) {
									setSelectedItem(0);
								} else {
									//索引加1 
									setSelectedItem(selectedItem + 1);
								}
								event.preventDefault();
							}
						}).keypress(
						function(event) {
							//enter键 
							if (event.keyCode == 13) {
								//列表为空或者鼠标离开导致当前没有索引值 
								if ($autocomplete.find('li').length == 0
										|| selectedItem == -1) {
									return;
								}
								var allText = $autocomplete.find('li').eq(selectedItem).text();
								var text = allText.split(",");
								$searchInput.val(text[0]);
								$("#prodId").val(text[1]);
								$autocomplete.empty().hide();
								event.preventDefault();
							}
						}).keydown(function(event) {
					//esc键 
					if (event.keyCode == 27) {
						$autocomplete.empty().hide();
						event.preventDefault();
					}
				});
		//注册窗口大小改变的事件，重新调整下拉列表的位置 
		$(window).resize(function() {
			var ypos = $searchInput.position().top;
			var xpos = $searchInput.position().left;
			$autocomplete.css('width', "100%");
			$autocomplete.css({
				'position' : 'relative',
				'left' : xpos + "px",
				'top' : ypos + "px"
			});
		});
	});

	function doSubmit() {
		var flag = $(inputForm).form('validate');
		if (flag) {
			var inputValue = (Number($("#unitPriceTemp").val()) * 100)
					.toFixed(0);
			inputValue = parseInt(inputValue);
			$("#unitPrice").val(inputValue);
			$(inputForm).submit();
		}
	}
</script>
</head>
<body>
	<div class="easyui-panel" title="修改订单商品"
		style="width: 100%; height: 480px; max-width: 630px; padding: 20px 150px 20px 20px;">
		<form action="${ctx}/sysManage/orderProducts/saveOrderProducts.do" id="inputForm"
			name="inputForm" method="post">
			<input type="hidden" name="token" id="token" value="${token}" />
			<input type="hidden" name="orderProdId" id="orderProdId" value="${orderProducts.orderProdId}" />
			<input type="text" name="orderId" id="orderId" value="${orderProducts.orderId }" />
			<input type="text" name="prodId" id="prodId" value="${orderProducts.prodId}" />
			<div style="margin-bottom: 20px" id="search">
				<label class="label-top">商品名称</label> <input
					class="easyui-textbox theme-textbox-radius" type="text"
					id="prodName" name="prodName" value="${orderProducts.prodName }"
					style="width: 100%;" data-options="required:true"> <span id="submit"></span>
			</div>
			<div style="margin-bottom: 20px">
				<label class="label-top">出售单价</label> <input
					class="easyui-textbox theme-textbox-radius" type="text"
					id="unitPriceTemp" name="unitPriceTemp"
					value='<fmt:formatNumber value="${orderProducts.unitPrice/100}" type="currency" pattern="0.00"/>'
					style="width: 100%;" data-options="required:true"> <input
					type="hidden" name="unitPrice" id="unitPrice" />
			</div>
			<div style="margin-bottom: 20px">
				<label class="label-top">出售数量</label> <input
					class="easyui-textbox theme-textbox-radius" type="text"
					name="ownerMobile" value="${orderProducts.prodNum }"
					style="width: 100%;" data-options="required:true">
			</div>
			<div>
				<a href="javascript:void(0);"
					class="easyui-linkbutton button-default" iconCls="icon-ok"
					style="width: 100%; height: 32px" onclick="doSubmit();">提交</a>
			</div>
			<div style="height: 20px"></div>
		</form>
	</div>

</body>
</html>