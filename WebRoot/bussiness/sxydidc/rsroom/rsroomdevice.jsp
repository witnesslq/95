<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.dhcc.bussiness.sxydidc.rsserver.RsserverModelRs"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
RsserverModelRs deviceInfo=(RsserverModelRs)request.getAttribute("deviceInfo");
String ip=(String)request.getAttribute("ip");
String port=(String)request.getAttribute("port");
String name="";//服务器名称
String code="";//服务器编码
String moduletype="";//服务器型号
//配置名称
String Uno="";//占用U位数量
String power="";//电压
String Ownername="";//拥有者
String Status="";//业务状态
//运行状态
String Createtime="";//购入时间
//使用年限
//描述
String MinU="";//占用最高U位
String MaxU="";//所占U为起始位
String remark="";//描述
System.out.println(deviceInfo);
if(deviceInfo.getName()!=null){name=deviceInfo.getName();}
if(deviceInfo.getPower()!=null){power=deviceInfo.getPower()+"";}
if(deviceInfo.getCode()!=null){code=deviceInfo.getCode();}
if(deviceInfo.getModuletype()!=null&&!"null".equals(deviceInfo.getModuletype())){ moduletype=deviceInfo.getModuletype();}
if(deviceInfo.getOwnername()!=null){Ownername=deviceInfo.getOwnername();}
if(deviceInfo.getStatus()!=null){Status=deviceInfo.getStatus();}
if(deviceInfo.getGettime()!=null){Createtime=deviceInfo.getGettime();}
if(deviceInfo.getUno()!=null){ Uno=deviceInfo.getUno();}
if(deviceInfo.getMinU()!=null){ MinU=deviceInfo.getMinU();}
if(deviceInfo.getMaxU()!=null){ MaxU=deviceInfo.getMaxU();}
if(deviceInfo.getRemark()!=null){ remark=deviceInfo.getRemark();}
  if("01".equals(Status)){
								Status="空闲";
								}else if("02".equals(Status)){
								Status="预占";
								}else if("03".equals(Status)){
								Status="实占";
								}else if("04".equals(Status)){
								Status="使用中";
								}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>设备信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/rsroomCheckout.css"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <table id="deviceInfo" >
	   <tr>
	     <td width="30%">服务器名称:</td>
	     <td width="70%"><%=name %></td>
	   </tr>
	   <tr>
	     <td>服务器编码:</td>
	     <td><%=code %></td>
	   </tr>
	   <tr>
	     <td>服务器型号:</td>
	     <td><%=moduletype %></td>
	   </tr>
	   <tr>
	     <td>占用IP:</td>
	     <td><%=ip %></td>
	   </tr>
	   <tr>
	     <td>起止U位:</td>
	     <td><%=MinU%>U~<%=MaxU%>U</td>
	   </tr>
	   <tr>
	     <td>占用U位数量:</td>
	     <td><%=Uno%></td>
	   </tr>
	   <tr>
	     <td>电压:</td>
	     <td><%=power%></td>
	   </tr>
	   <tr>
	     <td>拥有者:</td>
	     <td><%=Ownername %></td>
	   </tr>
	   <tr>
	     <td>运行状态:</td>
	     <td><%=Status %></td>
	   </tr>
	   <tr>
	     <td>购入时间:</td>
	     <td><%=Createtime%></td>
	   </tr>
	   <tr>
	     <td>上联端口:</td>
	     <td><%=port %></td>
	   </tr>
	   <tr>
	     <td>描述:</td>
	     <td><%=remark %></td>
	   </tr>
   </table>
  </body>
</html>
