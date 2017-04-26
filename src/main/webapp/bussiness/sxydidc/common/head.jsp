<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <link   rel="stylesheet" type="text/css" href="<%=basePath %>css/reportMain.css"/>
    <link   rel="stylesheet" type="text/css" href="<%=basePath %>css/style.css"/>
    <link    rel="stylesheet" type="text/css" href="<%=basePath %>include/LigerUI/skins/Aqua/css/ligerui-all.css"/>
    <link    rel="stylesheet" type="text/css"  href="<%=basePath %>include/LigerUI/skins/ligerui-icons.css" /> 
    <script  type="text/javascript"  src="<%=basePath  %>include/jQuery/jquery-1.5.2.min.js"></script>
    <script  type="text/javascript"  src="<%=basePath  %>include/jQuery/jquery.blockUI.js"></script>  
    <script  type="text/javascript"  src="<%=basePath  %>include/LigerUI/js/ligerui.all.js" ></script>
    <script  type="text/javascript"  src="<%=basePath  %>js/btnQuery.js" ></script>
    <script src="<%=basePath  %>include/LigerUI/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath  %>include/LigerUI/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath  %>include/LigerUI/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="<%=basePath  %>bussiness/sxydidc/js/common.js" type="text/javascript"></script>
  </head>
</html>
