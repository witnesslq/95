<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String packid = request.getParameter("packid")!=null?request.getParameter("packid").toString():"";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>设备板卡信息列表页面</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script  type="text/javascript"  src="deviceportList1.js"></script> 	
</head>
<body>
	<div class="btnArea">
    	<span style="display:block; float:right">
      		<input id="queryBtn" class="btn_l" name="" type="button" value="查询"/>
      		<input id="exportBtn" class="btn_l" name="" type="submit" value="导出信息" onclick="javascript:exportForm.submit();"/>
	    	<form action="loadDeviceportList.action" id="exportForm">
	    	</form>  
    	</span>
  	</div>  
    <div id="maingrid" style="margin: 5px;"></div>
    <input type="hidden" id="packid" value="<%=packid%>"/>
</body>
</html>
