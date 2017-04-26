<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String username = request.getParameter("colleaguename");//搜索的条件
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>头部文件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   
	<script  type="text/javascript"  src="<%=basePath  %>include/jQuery/jquery-1.3.2.min.js"></script>  
	<style type="text/css">
		html,body{
			margin: 0;
			padding: 0
		}
		* ul{ 
			margin:0px; 
			padding:0px; 
			list-style:none;
			}
		.l_ul{ 
			background-color:#333; 
			width:100%;
			height:25px; 
			list-style:none;
			}
		.l_ul li{
			font-size:12px; 
			color:#FFF;
			float:right; 
			text-align:center;
			margin-top:4px;
		}
		.r_logo{
			background-color:#AE0B02;
			width:100%;
			height:70px;
			}
		.r_logo #top1{
			margin-left:100px;
			width:20%;
			float:left;
			}
		.r_logo .r_right{ 
			float:right;
			width:60%;
			height:70px;
			margin-left:100px;
			}
		.r_right .r_ul{ 
			height:35px; 
			width:100%; 
			padding-top:25px;
			}
		.r_right .r_ul li{ 
			background-color:#900; 
			width:70px; 
			float:left; 
			height:22px; 
			text-align:center; 
			color:#FFFFFF; 
			margin-right:1px;
			padding-top:6px; 
			font-size:14px;
			}
		.r_right .r_bottom{
			margin-top:2px;
			}
		.r_right .r_bottom li{
				color:#FFF;
				font-size:12px;
				width:60px;
				float:left;
				}
		   a{ 
			color:#FFF;
			text-decoration: none;
			cursor: pointer;
			}
	</style>	
	<script type="text/javascript">
	var fulls = "left=0,screenX=0,top=0,screenY=0,scrollbars=1,directories=yes";    //定义弹出窗口的参数  
	if (window.screen) {  
	    var ah = screen.availHeight - 30;  
	    var aw = screen.availWidth - 10;  
	    fulls += ",height=" + ah;  
	    fulls += ",innerHeight=" + ah;  
	    fulls += ",width=" + aw;  
	    fulls += ",innerWidth=" + aw;  
	    fulls += ",resizable"  
	} else {  
	    fulls += ",resizable"; // 对于不支持screen属性的浏览器，可以手工进行最大化。 manually  
	}  
	
	function openColleague(){//搜索同事信息
		var user_name = encodeURI($("#colleaguename").val());
		var url = "<%=basePath %>bussiness/addressbook/user/SearchUserList.jsp?USERNAME="+user_name;
		window.open(url,"同事信息列表",fulls) 
		return false;
	}
	</script>
  </head>
  
  <body>
    <div class="l_top">
	<div class="r_logo">
    	<div id="top1"><img height="70px" src="images/login.png" /></div>
        <div class="r_right">
       	    <input type="text" id="colleaguename" style="float:left; width:300px;height:25px;line-height:22px; border:1px solid #AE0B02;margin-top:26px; margin-right: 5px;" value="<%=username==null?"":username %>"/>
            <ul class="r_ul">
                <li><a onclick="return openColleague();">找同事</a></li>
            </ul>
            <ul  class="r_bottom">
            </ul>
        </div>
	</div>
	</div>
  </body>
</html>
