<%@ page language="java"  pageEncoding="UTF-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>错误页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<style type="text/style">
	</style>
	<body>
	<center>
	<table border="0" height="99%">
	<tr>
	<td align="center">
	<img src="<%=basePath %>images/wrong_1.gif">
	<div>
		<font face="verdana" size="3">
		<a href="javascript:history.go(-1)">返回上一页&gt;&gt;</a> 
		</font>
	</div>
	</td>
	</tr>
	<tr>
	<td align="center">
	</td>
	</tr>
	</table>
	</center>
	</body>
  </HTML>
