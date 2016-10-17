<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String alldevnumber=request.getParameter("alldevnumber");
String room_id=request.getParameter("room_id");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addSomeRack.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link    rel="stylesheet" type="text/css" href="<%=basePath %>include/LigerUI/skins/Aqua/css/ligerui-all.css"/>
	<script  type="text/javascript"  src="<%=basePath  %>include/jQuery/jquery-1.9.1.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body >
   <table style= "width:80%;position: absolute;top:30px;left:20px;font-size:14px">
   <tr>
   <td width="45%">添加位置:</td>
   <td>
  第<input type="text"  id="rowNumber"  style= "border: #AECAF0 1px solid;width:30px;"/>行
   </td>
   </tr>
   </table>
  </body>
</html>
