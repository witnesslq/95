<%@page language="java" contentType="text/html;charset=utf-8"%>

<%
	request.getSession().removeAttribute("userid");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>山西移动IDC运营管理平台</title>
<link  rel="stylesheet" type="text/css" href="css/login.css"/>
<script type="text/javascript" src="include/jQuery/jquery-1.9.1.js"></script>
<style type="text/css">

</style>
 <script language="javascript">
	 jQuery(function($){
		$("#loginBtn").click(function(){
		  if(doLogin()){
		   	 userlogin();
		    }
		});
		$("#cancelBtn").click(function(){
		  $("#username").val("");	//用户名称
		  $("#password").val("");	//用户密码
		  $("#verifycode").val("");	//用户验证码
		});
		$("#password").keyup(function(event){
			if(event.keyCode == 13){
	            if(doLogin()){
			   	 	userlogin();
			    }
	        }
		});
		$("#username").keyup(function(event){
			if(event.keyCode == 13){
	            if(doLogin()){
			   	 	userlogin();
			    }
	        }
		});
		$("#verifycode").keyup(function(event){
			if(event.keyCode == 13){
	            if(doLogin()){
			   	 	userlogin();
			    }
	        }
		});
	 });
	 /*用户登录*/
	 function userlogin(){
		 var username = $("#username").val();		//用户名称
		 var userpass = $("#password").val();		//用户密码
		 var verifycode = $("#verifycode").val();	//用户验证码
		 var userdata = {"userid":username,"password":userpass,"verifycode":verifycode};
		$.ajax({
			url:"ossLogin.action", 
			data: userdata, 
			async:false,
			dataType:"json", 
			type:"post",
			success:function (data) {
				if(data.msg == "LOGINSUCCESS"){
					document.location.replace("index.jsp");
				}else{
					alert("账号、密码或验证码错误！请重新输入。");
				}
			}, 
			error:function (error) {
				alert("网络状况不佳，用户登录失败！" + error.status);
			}
		});
	   }
	 function doLogin(){
		 var username = $("#username").val();		//用户名称
		 var userpass = $("#password").val();		//用户密码
		 var verifycode = $("#verifycode").val();	//用户验证码
		 if(username == null || username == ""){
			 alert("请输入账号!");
	       	 return false;
	     }else if(userpass == null || userpass == ""){
	       	 alert("请输入密码!");
	       	 return false;
	     } else if(verifycode == null || verifycode == ""){
	       	 alert("请输入验证码!");
	       	 return false;
	     }
	     return  true;
	 }
	 function refreshRandomCode() {
		 var rCodeImg = document.getElementById("rCodeImg");
		 rCodeImg.src = "randomCode.do";
	 }
  </script>
</head>

<body>
	<div class="bg">
     <div class="bg1">
         <div class="dengluk">
                    <div class="zh">
                       <img src="images/login/zhanghao.jpg" width="31" height="31" class="tu" />
                       <input type="text" id="username" class="zhanghao" value="" onchange="value=value.replace(/[^\w\.\/]/ig,'')" placeholder="请输入账号"/>
                    </div>
                    <div class="zh">
                       <img src="images/login/mima.jpg" width="31" height="31" class="tu" />
                       <input type="password" id="password" class="zhanghao" value="" placeholder="请输入密码"/>
                    </div>
                    <div class="zh">
                       <img src="images/login/mima.jpg" width="31" height="31" class="tu" />
                       <input type="text" id="verifycode" class="verifycode" value="" placeholder="请输入验证码"/>
                       <img id="rCodeImg" src="randomCode.do" class="rcode" align="top" onmouseover="this.style.cursor='pointer'" onclick="refreshRandomCode()"/>
                    </div>
                    <div class="button">
                         <a href="#"><div class="login" id="loginBtn"></div></a>
                         <a href="#"><div class="cancel" id="cancelBtn"></div></a>
                    </div>
         </div>
  </div>
</div>
</body>
</html>