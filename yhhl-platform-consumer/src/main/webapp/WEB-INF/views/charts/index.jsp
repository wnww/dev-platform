<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<meta charset="utf-8">
<title>国版中心平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/import.jsp"%>
<script type="text/javascript"
	src="https://cdn.bootcss.com/echarts/3.7.1/echarts.min.js"></script>
<script type="text/javascript">


	var inputForm = '#inputForm';
	$(function() {
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));

		// 指定图表的配置项和数据
		var option = {
			title : {
				text : 'ECharts 入门示例'
			},
			tooltip : {},
			legend : {
				data : [ '销量' ]
			},
			xAxis : {
				data : [ "衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子" ]
			},
			yAxis : {},
			series : [ {
				name : '销量',
				type : 'line',
				data : [ 5, 20, 36, 10, 10, 20 ]
			} ]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
		
		var secondChart = echarts.init(document.getElementById('second'));
		
		// 指定图表的配置项和数据
		var secondOption = {
			title : {
				text : '横向条型图'
			},
			tooltip : {
				trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
			},
			legend : {
				data : [ '销量' ]
			},
			xAxis : {
				type : "value"
				//boundaryGap: [0, 0.01]
			},
			yAxis : {
				data : [ "衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子" ]
			},
			series : [ {
				name : '销量',
				type : 'bar',
				data : [ 5, 20, 36, 10, 10, 20 ]
			} ]
		};

		// 使用刚指定的配置项和数据显示图表。
		secondChart.setOption(secondOption);
		
		var bingTuChart = echarts.init(document.getElementById('bingTu'));
		bingTuOption = {
		    title : {
		        text: '某站点用户访问来源',
		        //subtext: '纯属虚构',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
		    },
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[
		                {value:335, name:'直接访问:335'},
		                {value:310, name:'邮件营销:310'},
		                {value:234, name:'联盟广告:234'},
		                {value:135, name:'视频广告:135'},
		                {value:1548, name:'搜索引擎:1548'}
		            ],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		// 使用刚指定的配置项和数据显示图表。
		bingTuChart.setOption(bingTuOption);
		
		
		/////////////////抢/////////////////
		$("#btnQ").on("click",function(){
			$("#qiangForm").submit();
		});
		
		
		cl();
	});
	
	function getFree(){
		$.ajax({
			type : "POST",
			url : "https://free.aliyun.com/json/gainTicket.json?trialType=0&umidToken=Y5bd3bd137f6bd1fcf5e17c2783661a30&collina=098#E1hv0vvOvSyvUpCkvvvvvjiPP25psjEHPLqpzjYHPmPWljDUP2SZlj1RP2zw0jEhRsyCvvpvvhCv9phvHnMNhFAF7rMNYzszMHuDzY5NjYsvrQhvCIkUkO5iXqwCvv4UkOeEt4VzvpvVkUkEk4arRphvCvvvphmjvpvhvUCvp8wCvvpvvhHh2QhvCnPCaIZEvpCW9WkN51z6VCOA55BCgbvqrqpAOH2+Ffmt+3C1BRFE+FuTRogRD70OVuTJEcqh68TxhfnkLixr58TJ+3+utj7p5d8rwZxl+28JeCDQpuyCvv9vvhhN2+QmwUyCvv4CvhE2lnotvpvIvvCvpvvvvvvvvhxavvmvppvvBBWvvUhvvvCHcpvv9pvvvhxrvvmCmmhCvO4U+IFvv9qGFPSCiIyv2R0Ui+NDFCWPt+Fd3RLbmvqDDuNC/tqMAJ0x/EzqySGUTgT5QRAOq9s39RL8gXZjCKmZMUsTQW5ETOdd3PAo2+Fi3JVOMEbUMJJ8iGSe9pWUiGS59RmR/NSyFNVhtGym2Kc4/g/63JzdKUkY6rMSCaTt6RVeqTpC2JJZgi7SQa6EqUelyMKShQdr68qJTEdqCMSVdX/GkYAMKI/TkPuL2S/r5q0PKtqWsqSTdX/GF+spdX/YFqKJsW/T/40NSUqW2/7WqbFeFNGUdX/YFqKJsW/q/u0NSUqWGqVhqr0/kWqbTauGkfKU6NKqzJswKYFq6q/PSaT/FPMbdX/YFqKJsW/T/40NSUqW2PzOKI/qkfMWSOqEqbKRsWs9DPAW/rMWsqSTdX/GFKmMKI/TMR/aMM/e1/onGUZUARKhtwmqF+s9tibDDuAJsGsRuPuNqW5WsqdGqXct5vQ2qOPdFuAJsGsRuJ0Xz4G2//L8gELykbMWSO/T5qKR6agCvpvVvvBvpvvvRphvCvvvphmrvpvEvUmoyOhvvUogdphvmpvCAGFuvvmHCUhCvCLNqggGZDdNzYYvaP1wuY/5zMdwjqwCvv4UkOeEizmzvpvVkUkEkw4hdphvmpmvPFIxvvmXg86Cvvyv9jrM4vvvEkVrvpvEvUhyEr+vv8rN&JSESSIONID=XT6661D1-U53NQ3G6SYE1V3LZCION2-3U8N7L7J-O0IB",
			async : false, ////作用是防止在ajax成功调用之前就调用$("#Pagination").pagination,这个时候数据个数还没有初始化
			dataType : "json",
			//data : "page=" + pageIndex + "&rows="+pageSize,//传递页面索引
			//发送请求前，显示加载动画
			beforeSend : function() {
				
			},
			//请求完毕后，隐藏加载动画
			complete : function() {
				
			},
			success : function(data) {
				$("#showResult").html(data);
			}

		});
	}
	var i=0;
	function cl(){
		//setInterval("getFree();",1000);
		i++;
		console.log(i);
	}
	
	
</script>
</head>
<body>
	<div id="showResult" style="height:100px; width:100%; padding-top:30px; padding-bottom:30px;">
		
	</div>
	<div id="main" style="width: 600px; height: 400px;"></div>

	<div id="second" style="width: 600px; height: 400px;"></div>
	<div style="width:1000px;">
		<div style="float:left;width:190px;"><table ><tr><td>asdfasdfasfdasfdasdfasdfasdfadsf</td></tr></table></div>
		<div id="bingTu" style="width: 600px; height: 400px; float:left"></div>
		<div><table ><tr><td>asdfasdfasdfasfdasdfasfd</td></tr></table></div>
	</div>
</body>
</html>