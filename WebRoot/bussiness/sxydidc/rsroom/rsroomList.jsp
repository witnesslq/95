<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>机房列表</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
	<script type="text/javascript"  src="rsroomList.js"></script>
  </head>
  <body> 
	<div class="btnArea">
    	<span style="display:block; float:right">
      		<input id="addBtn" class="btn_l" name="" type="button" value="增加"/>
      		<input id="editBtn" class="btn_l" name="" type="button" value="编辑"/>
      		<input id="delBtn" class="btn_l" name="" type="button" value="删除"/>
      		<input id="QRCodeBtn" class="btn_l" name="" type="button" value="查看二维码"/>
    	</span>
    	<input id="searchTxt" class="txt" name="" type="text" size="70"  />
    	<input id="searchBtn" class="btn_l" name="" type="button" value="模糊查询"/>
  	</div>	
    <div id="maingrid" style="margin: 5px;"></div>
  </body>
</html>