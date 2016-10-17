<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
  <head>
	<script type="text/javascript" src="<%=basePath %>include/jQuery/jquery-1.9.1.js"></script> 
	<style type="text/css">
		html,body{
			margin: 0;
			padding: 0;
		}
		#myReport{
			width:1010px;
			margin:0 auto;
		}
		.codeImage{
			width:500px;
			height:500px;
			border:0px solid #000;
		}
	</style>

  </head>
  
  <body>
  	<div  id="myReport"></div>
  
  </body>
  <script type="text/javascript">
  	$(function () {  
		init();
	});
  	function init(){
		$.ajax({
				url:"queryAllQRCodeImgs.action", 
				data:{"roomId":"<%=id%>"}, 
				type:"post",
				dataType:"json",
				success:function (data) {
					var imgStr = "";
	       			for(var i=0;i<data.length;i++){
		       			var imgPath = data[i].imgPath;
		       			imgStr += "<img class='codeImage' src='"+imgPath+"'/>";
	       			}
	       			$("#myReport").html(imgStr);
				}, 
				error:function (error) {
					top.$.ligerDialog.error("获取信息失败!" + error.status,"错误");
				}
				});	
	}
  
  
  </script>
  
  
</html>
