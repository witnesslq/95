<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.dhcc.bussiness.sxydidc.rack.RackModel"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
RackModel StatuInfo=(RackModel)request.getAttribute("rackInfo");
String room_id=(String)request.getAttribute("room_id");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'rackInfoUpdate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link    rel="stylesheet" type="text/css" href="<%=basePath %>include/LigerUI/skins/Aqua/css/ligerui-all.css"/>
    <link    rel="stylesheet" type="text/css"  href="<%=basePath %>include/LigerUI/skins/ligerui-icons.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/rsroomCheckout.css"/>
    <script  type="text/javascript"  src="<%=basePath  %>include/jQuery/jquery-1.9.1.js"></script>  
    <script  type="text/javascript"  src="<%=basePath  %>include/LigerUI/js/ligerui.all.js" ></script> 
	<script src="<%=basePath%>/include/LigerUI/json2.js"type="text/javascript"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form id="mainform" name="mainform">
   <table class="rackInfo">
   <tr>
   <td>机架名称:</td>
   <td ><input type="text" name="name"  disabled="disabled" value="<%=StatuInfo.getName()==null?"":StatuInfo.getName() %>"></td>
   <td>机架编码:</td>
   <td ><input type="text" name="code"   disabled="disabled" value="<%=StatuInfo.getCode()==null?"":StatuInfo.getCode() %>"></td>
   </tr>
   <tr>
   <td>所在机房:</td>
   <td ><input type="text" name="roomname"  disabled="disabled" value="<%=StatuInfo.getRoomName()==null?"":StatuInfo.getRoomName() %>"></td>
   <td>所属客户:</td>
   <td ><input type="text" name="status"  disabled="disabled" value="<%=StatuInfo.getCustomerName()==null?"":StatuInfo.getCustomerName()%>"></td>
   </tr>
     <tr>
   <td>机架规格:</td>
   <td><input type="text"></td>
   <td>U位数量:</td>
   <td ><input type="text" name="power"  disabled="disabled" value="<%=StatuInfo.getPower()==null?"":StatuInfo.getPower() %>"></td>
   </tr>
     <tr>
   <td>机架状态:</td>
   <td ><input type="text" name="Ucount"  disabled="disabled" value="<%=StatuInfo.getUcount()==null?"":StatuInfo.getUcount() %>"></td>
   <td>最大功率(W):</td>
   <td><input type="text"></td>
   </tr>
    <tr>
   <td>行号:</td>
   <td ><input type="text" name="rowno" value="<%=StatuInfo.getRowno()==null?"":StatuInfo.getRowno() %>"></td>
   <td>列号:</td>
   <td ><input type="text" name="colno" value="<%=StatuInfo.getColno()==null?"":StatuInfo.getColno() %>"></td>
   </tr>
   <tr>
   <td>x坐标:</td>
   <td ><input type="text" name="xposition" value="<%=StatuInfo.getXposition()==null?"":StatuInfo.getXposition() %>"></td>
   <td>Y坐标:</td>
   <td ><input type="text" name="yposition" value="<%=StatuInfo.getYposition()==null?"":StatuInfo.getYposition() %>"></td>
   </tr>
   </table>
   <div>
   <input type="hidden"  name="room_id" value="<%=room_id%>"/>
   <input type="hidden"  name="id" value="<%=StatuInfo.getId()%>"/>

   </div>
   </form>
  </body>
</html>
