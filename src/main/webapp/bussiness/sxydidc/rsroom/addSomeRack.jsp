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
<script type="text/javascript">
function rsroomDevNumber(){
var room_id="<%=room_id%>"
var alldevnumber="<%=alldevnumber%>";
	$.ajax({
			url:"queryrowno.action", 
			data:{"room_id":room_id},
			dataType:"json", 
			type:"post",
			success:function (msg) {
       			$("#rowNumber").val(msg.rowcount);
       			$("#colNumber").val(msg.colcount);
			}, 
			error:function (error) {
				top.$.ligerDialog.error("添加机柜信息失败!" + error.status,"错误");
		}});
	
}


</script>
  </head>
  
  <body onload="rsroomDevNumber()">
   <table style= "position: absolute;top:10px;left:20px;font-size:14px;height:130px">
   <tr>
   <td width="40%" height="20%">起始行:</td>
   <td>
  <input type="text" style="border: #AECAF0 1px solid;" id="startRow" >
   </td>
   </tr>
    <tr>
   <td width="40%" height="20%">结束行:</td>
   <td>
  <input type="text"  style="border: #AECAF0 1px solid;" id="lastRow" >
   </td>
   </tr>
    <tr>
   <td width="40%" height="20%">起始列:</td>
   <td>
  <input type="text"  style="border: #AECAF0 1px solid;" id="startCol">
   </td>
   </tr>
    <tr>
   <td width="40%" height="20%">结束列:</td>
   <td>
  <input type="text" style="border: #AECAF0 1px solid;" id="lastCol" >
   </td>
   </tr>
    <tr>
   <td width="40%" height="20%">最大功率:</td>
   <td>
  <input type="text" style="border: #AECAF0 1px solid;" id="maxpower">
   </td>
   </tr>
   </table>
   <input type="hidden" id="rowNumber">
   <input type="hidden" id="colNumber">
   <input type="hidden" id="room_id" value="<%=room_id %>">
  </body>
</html>
