<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>设备信息列表页面</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script  type="text/javascript"  src="queryDevice.js"></script> 
</head>
<body> 
	<div class="btnArea">
    	<input id="searchTxt" class="txt" name="" type="text" size="70"  />
    	<input id="searchBtn" class="btn_l" name="" type="button" value="模糊查询"/>
    	<input id="queryBtn" class="btn_l" name="" type="button" value="高级查询"/>  
    	<input id="exportBtn" class="btn_l" name="" type="submit" value="导出信息" onclick="javascript:exportForm.submit();"/>
    	<form action="loadDeviceList.action" id="exportForm">
    	</form>  	
  	</div>  
    <div id="maingrid" style="margin: 5px;"></div>
</body>
</html>
