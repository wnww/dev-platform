<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>系统错误提示</title>
	<style type=text/css>
		body {
			font-size: 12px;
			font-family: tahoma
		}
		
		td {
			font-size: 12px;
			font-family: tahoma
		}
		
		a:link {
			color: #636363;
			text-decoration: none
		}
		
		a:visited {
			color: #838383;
			text-decoration: none
		}
		
		a:hover {
			color: #a3a3a3;
			text-decoration: underline
		}
		
		body {
			background-color: #cccccc
		}
   </style>
	</head>
	<body style="table-layout: fixed; word-break: break-all" topmargin=10
		marginwidth="10" marginheight="10">
		<p />
		<p />
		<p />
		<table height="95%" cellspacing=0 cellpadding=0 width="100%"
			align=center border=0>
			<tbody>
				<tr valign=center align=middle>
					<td>
						<table cellspacing=0 cellpadding=0 width=468 bgcolor=#ffffff
							border=0>
							<tbody>
								<tr>
									<td width=20 background="${ctx}/images/error/rbox_1.gif"
										height=20></td>
									<td width=108 background="${ctx}/images/error/rbox_2.gif"
										height=20></td>
									<td width=56>
										<img height=20 src="${ctx}/images/error/rbox_ring.gif"
											width=56>
									</td>
									<td width=100 background="${ctx}/images/error/rbox_2.gif"></td>
									<td width=56>
										<img height=20 src="${ctx}/images/error/rbox_ring.gif"
											width=56>
									</td>
									<td width=108 background="${ctx}/images/error/rbox_2.gif"></td>
									<td width=20 background="${ctx}/images/error/rbox_3.gif"
										height=20></td>
								</tr>
								<tr>
									<td align=left background="${ctx}/images/error/rbox_4.gif"
										rowspan=2></td>
									<td align=middle bgcolor=#eeeeee colspan=5 height=50>
										<p>
											<strong>系统错误提示&#8230;&#8230;<br> <br> </strong>
										</p>
									</td>
									<td align=left background="${ctx}/images/error/rbox_6.gif"
										rowspan=2></td>
								</tr>
								<tr>
									<td align=left colspan=5 height=80>
										<p align=center>
											您好！您访问的网页出错了，请联系系统管理员解决问题
											<br>
											<p id=lid2>
												请尝试以下操作：
											</p>
											<ul>
												<li id=list2>
													单击
													<a href="<c:url value="/"/>">主页</a>，返回系统主页
												</li>
												<li id=list3>
													单击
													<a href="javascript:history.back(1)">后退</a>按钮，尝试其他链接
												</li>
												<li id=list1>
													单击
													<a href="#" onclick="javascript:window.close();">关闭</a>，关闭该窗口
													<br>
											</ul>
											<div align=right>
												<br>
											</div>
									</td>
								</tr>
								<tr>
									<td align=left background="${ctx}/images/error/rbox_7.gif"
										height=20></td>
									<td align=left background="${ctx}/images/error/rbox_8.gif"
										colspan=5 height=20></td>
									<td align=left background="${ctx}/images/error/rbox_9.gif"
										height=20></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>

	</body>
</html>