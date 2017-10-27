<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<meta charset="utf-8">
<title>瀛海Soft</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/import.jsp"%>
<script type="text/javascript" src="https://cdn.bootcss.com/echarts/3.7.1/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/js/WdatePicker/WdatePicker.js?v=1.0.0"></script>
<script type="text/javascript">

	//基于准备好的dom，初始化echarts实例
	
	$(function() {
		var myChart = echarts.init(document.getElementById('main'));
		showData(myChart);
	});
	
	function showData(myChart){
		var get_url = "${ctx}/sysManage/charts/orderStatistics.do?filter_cycle=MD";
		if($("#startDate").val()!=""){
			get_url = get_url+"&startDate="+startDate;
		}
		if($("#endDate").val()!=""){
			get_url = get_url+"&endDate="+endDate;
		}
		$.ajax({
			type: "post",
			url: get_url,
			dataType: "json",
			success: function(data){
				if(data.flag==1){
					setOption1(myChart,data);
					showData(data);
				}else if(data.flag==2){
					$.messager.alert('结果', data.msg, 'info');	
				}else if (data.flag == 3) {
					$.messager.alert('结果', '您还未登录，请先登录！', 'error', function(){
						document.location.href="${ctx}/sysManage/index.do";
					});
				}else{
					$.messager.alert('结果', '操作失败，请重试', 'error');	
				}
			},
			error:function(messg)  { 
	       	    $.messager.alert('错误提示', '操作失败:'+messg.responseText, 'error');
	       } 
		});
	}
	
	function setOption1(myChart,data){
		var chartsData = data.data;
		var series = data.rows;
		console.log(chartsData.xAxis);
		console.log(series[0].data);
		var option = {
			    title : {
			        text: '订单统计'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['订单金额','购买数量']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data :  chartsData.xAxis
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:series[0].name,
			            type:'bar',
			            data:series[0].data,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            },
			            markLine : {
			                data : [
			                    {type : 'average', name: '平均值'}
			                ]
			            }
			        },
			        {
			            name:series[1].name,
			            type:'bar',
			            data:series[1].data,
			            markPoint : {
			                data : [
			                	{type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            },
			            markLine : {
			                data : [
			                    {type : 'average', name : '平均值'}
			                ]
			            }
			        }
			    ]
			};
		myChart.setOption(option);
	}
	
	function showData(data){
		var xAxis = data.data.xAxis;
		var series = data.rows;
		for(var i=0; i<xAxis.length; i++){
			var tr = $('<tr id="datagrid-row-r1-2-0" datagrid-row-index="0" class="datagrid-row" style="height: 35px;">');
			var td1 = $('<td field="orderId" width="40%">');
			var div1 = $('<div style="white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-orderId">');
			div1.append(xAxis[i]);
			td1.append(div1);
			tr.append(td1);
			
			var td2 = $('<td field="ownerRealName" width="30%">');
			var div2 = $('<div style="white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-ownerRealName">');
			div2.append(series[0].data[i]);
			td2.append(div2);
			tr.append(td2);
			
			var td3 = $('<td field="ownerMobile" width="30%">');
			var div3 = $('<div class="datagrid-cell datagrid-sort datagrid-cell-c1-ownerMobile">');
			div3.append(series[1].data[i]);
			td3.append(div3);
			tr.append(td3);
			$('#showDataList').append(tr);
		}
	}
</script>
</head>
<body>
<div id="" class="easyui-panel" title="查询条件" collapsible="true"
		style="padding: 5px;">
		<form id="publishProductsDetailForm" action="">
			<center style="line-height: 22spx; padding: 5px;">
				开始日期： <span class="textbox easyui-fluid"
					style="width: 120px; height: 30px;"><input type="text" id="startDate" name="startDate"
					class="textbox-text validatebox-text textbox-prompt Wdate"
					style="margin: 0px 0px 0px 0px; padding-top: 0px; padding-bottom: 0px; padding-left: 3px; height: 30px; line-height: 30px; width: 120px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></span>
				&nbsp;&nbsp;结束日期： <span class="textbox easyui-fluid"
					style="width: 120px; height: 30px;"><input
					type="text" id="endDate" name="endDate" class="textbox-text validatebox-text textbox-prompt Wdate"
					style="margin: 0px 0px 0px 0px; padding-top: 0px; padding-bottom: 0px; padding-left: 3px; height: 30px; line-height: 30px; width: 120px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></span>
				<a href="javascript:void(0);" onclick="publishDetail();"
					class="easyui-linkbutton" iconCls="icon-search">统计</a>	
			</center>
		</form>
	</div>

</div>
<div id="main" class="easyui-panel" style="height: 400px; margin-top: 5px; "></div>
	
<div class="datagrid-header" style=" height: 37px; margin-top:5px;">
   <div class="datagrid-header-inner" style="display: block;">
    <table class="datagrid-htable" style="height: 38px; width:1000px" cellspacing="0" cellpadding="0" border="0">
     <tbody>
      <tr class="datagrid-header-row">
       <td field="orderId" width="40%">
        <div class="datagrid-cell datagrid-cell-c1-orderId">
         <span>日期</span>
         <span class="datagrid-sort-icon"></span>
        </div></td>
       <td field="ownerRealName" width="30%">
        <div class="datagrid-cell datagrid-cell-c1-ownerRealName">
         <span>订单金额</span>
         <span class="datagrid-sort-icon"></span>
        </div></td>
       <td field="ownerMobile" width="30%">
        <div class="datagrid-cell datagrid-cell-c1-ownerMobile">
         <span>订单数量</span>
         <span class="datagrid-sort-icon"></span>
        </div>
       </td>
      </tr>
     </tbody>
    </table>
   </div>
  </div>
  <div class="datagrid-body" style="margin-top: 0px; overflow-x: hidden; height: 350px; width:1000px">
   <table class="datagrid-btable" cellspacing="0" cellpadding="0" border="0" style="width:1000">
    <tbody id="showDataList">
     
    </tbody>
   </table>
  </div>
</body>
</html>