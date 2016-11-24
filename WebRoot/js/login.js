jQuery(function($){
		$("#loginBtn").click(function(){
		  if(doLogin()){
		   	 userlogin();
		    }
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
		$("#rCodeImg").click(function(){
			$(this).attr("src","randomCode.do?t=" + Math.random()); //加随机数防止缓存
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
					$("#tipInfo").html("账号、密码或验证码错误！请重新输入。");
					$("#tipModel").modal('show');
				}
			}, 
			error:function (error) {
				$("#tipInfo").html("网络状况不佳，用户登录失败！");
				$("#tipModel").modal('show');
			}
		});
	   }
	 function doLogin(){
		 var username = $("#username").val();		//用户名称
		 var userpass = $("#password").val();		//用户密码
		 var verifycode = $("#verifycode").val();	//用户验证码
		 if(username == null || username == ""){
			 $("#tipInfo").html("请输入账号!");
			 $("#tipModel").modal('show');
	       	 return false;
	     }else if(userpass == null || userpass == ""){
	    	 $("#tipInfo").html("请输入密码!");
			 $("#tipModel").modal('show');
	       	 return false;
	     } else if(verifycode == null || verifycode == ""){
	    	 $("#tipInfo").html("请输入验证码!");
			 $("#tipModel").modal('show');
	       	 return false;
	     }
	     return  true;
	 }
