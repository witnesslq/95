<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统欢迎主页</title>
<style type="text/css">
	html,body {height:100%; margin:0px; font-size:12px;  }
	ul{ list-style:none; }
	img{ border:0; }
	.pic { 
		background:url(images/pic.gif);
		font-size: 12px;
		font-weight: bold;
		z-index:99;
		width: 400px;
		height: 120px;
		left:50%;/*FF IE7*/
		top: 50%;/*FF IE7*/
		margin-left:-200px!important;/*FF IE7 该值为本身宽的一半 */
		margin-top:-90px!important;/*FF IE7 该值为本身高的一半*/
		position:absolute!important;/*FF IE7*/
	}

</style>
</head>

<body>
	<div  class="pic"></div>
</body>
</html>
