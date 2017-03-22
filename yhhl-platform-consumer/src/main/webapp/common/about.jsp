<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/import.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户中心</title>
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
                        	<li><div id="gg1" class="gauge"  data-value="250"></div><span>我的数据</span></li>
                            <li><div id="gg2" class="gauge"  data-value="2025"></div><span>我的数据</span></li>
                        	<li><div id="gg3" class="gauge"  data-value="115"></div><span>我的数据</span></li>
                            <li><div id="gg4" class="gauge"  data-value="687"></div><span>我的数据</span></li>
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
                        	系统使用帮助......
                    </div>
                </div>
                
    

            </div>
            <div id="tab-tools">
              <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-set'"></a>
            </div>
</body>
</html>