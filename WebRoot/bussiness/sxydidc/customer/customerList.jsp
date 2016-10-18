<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>客户信息列表页面</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script  type="text/javascript"  src="customerList.js"></script> 
</head>
<body>
	<div class="btnArea">
    	<span style="display:block; float:right">
      		<input id="addBtn" class="btn_l" name="" type="button" value="增加"/>
      		<input id="keyBtn" class="btn_l" name="" type="button" value="修改密钥"/>
    	</span>
    	<input id="searchTxt" class="txt" name="" type="text" size="70"  />
    	<input id="searchBtn" class="btn_l" name="" type="button" value="模糊查询"/>
    	<input id="queryBtn" class="btn_l" name="" type="button" value="高级查询"/>
  	</div>	 
    <div id="maingrid" style="margin: 5px;"></div>
</body>
</html>