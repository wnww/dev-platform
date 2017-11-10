<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/import.jsp" %>
<script type="text/javascript" src="${ctx}/js/easyui/raphael-2.1.4.min.js?v=1.0.0"></script>
<script type="text/javascript" src="${ctx}/js/easyui/justgage.js?v=1.0.0"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户中心</title>
<script type="text/javascript">
$(function(){
	var orderTotalCount = parseInt('${orderTotalCount}');
	var dflt = {
	  min: 0,
	  max: orderTotalCount,
	  donut: true,
	  gaugeWidthScale: 0.6,
	  counter: true,
	  hideInnerShadow: true
	}
	
	var productTotalCount = parseInt('${prodTotalCount}');
	var dflt2 = {
		  min: 0,
		  max: productTotalCount,
		  donut: true,
		  gaugeWidthScale: 0.6,
		  counter: true,
		  hideInnerShadow: true
	}
	var obligationCount = parseInt('${obligationCount}');
	var gg1 = new JustGage({
	  id: 'gg1',
	  value : obligationCount,
	  defaults: dflt
	});
	var waitSendCount = parseInt('${waitSendCount}');
	var gg2 = new JustGage({
	  id: 'gg2',
	  value : waitSendCount,
	  defaults: dflt
	});
	var gg3 = new JustGage({
	  id: 'gg3',
	  defaults: dflt
	});
	var gg4 = new JustGage({
	  id: 'gg4',
	  defaults: dflt2
	});

  });
</script>
</head>
<body>
	<div class="theme-user-info-panel">
                	<div class="left">
                    	<img src="${loginUser.userPhoto }" width="86" height="86">
                    </div>
                    <div class="right">
                    	
                          <style>
						  .gauge {
							width: 130px;
							height: 130px;
						  }
						  </style>
                    	<ul>
                        	<li><div id="gg1" class="gauge"  data-value="${obligationCount}"></div><span>待付款数量</span></li>
                            <li><div id="gg2" class="gauge"  data-value="${waitSendCount}"></div><span>待发货数量</span></li>
                        	<li><div id="gg3" class="gauge"  data-value="${waitReceiveCount}"></div><span>待收货数量</span></li>
                            <li><div id="gg4" class="gauge"  data-value="${prodTotalCount}"></div><span>商品总数量</span></li>
                        </ul>
                    </div>
                    <div class="center">
                        <h1>匿名<span class="color-warning badge">未认证</span></h1>
                        <h2>管理员</h2>
                        <dl>
                            <dt>examples@insdep.com</dt>
                            <dd>13000000000</dd>
                        </dl>
                    </div>
                	
                </div>
                <div id="user-info-more" class="easyui-tabs theme-tab-blue-line theme-tab-body-unborder" data-options="tools:'#tab-tools',fit:true">
                    <div title="帮助" data-options="closable:true" style="padding:10px">
                        	目前系统设置状态为“待支付”，“支付成功”，“已出库”，“已发货”，“已收货”，“已取消”，“已退货”，对于“已出库”可以不使用。
                    </div>
                </div>
                
    

            </div>
            <div id="tab-tools">
              <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-set'"></a>
            </div>
</body>
</html>