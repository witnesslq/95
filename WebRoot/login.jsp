<%@ page language="java"  pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userid=(String)request.getSession().getAttribute("userid");//用户id
String username=(String)request.getSession().getAttribute("username");//用户名
%>
<!DOCTYPE html>
<html lang="cn">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>IDC／ISP流量统计与质量监测系统</title>


		  <link rel="stylesheet" href="<%=basePath %>/node_modules/admin-lte/bootstrap/css/bootstrap.min.css"/>
		  <!-- Font Awesome -->
		  <link rel="stylesheet" href="<%=basePath  %>/node_modules/font-awesome/css/font-awesome.min.css">
		  <!-- Ionicons -->
		  <link rel="stylesheet" href="<%=basePath  %>/node_modules/ionicons/dist/css/ionicons.min.css">
		  <!-- Theme style --> <!-- Theme style -->
		  <link rel="stylesheet" href="<%=basePath %>/node_modules/admin-lte/dist/css/AdminLTE.min.css">
		  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
		        page. However, you can choose any other skin. Make sure you
		        apply the skin class to the body tag so the changes take effect.
		  -->
		  <link rel="stylesheet" href="<%=basePath %>/node_modules/admin-lte/dist/css/skins/skin-blue.min.css">
          <link rel="stylesheet" href="<%=basePath  %>/css/newAddStyle.css">

    </head>

    <body class="login-background">

        <!-- Top content -->
        <div class="container">
          <div class="col-sm-6 col-sm-offset-3 form-box">
	       
			 <!-- /.login-logo -->
			  <div class="login-body">
			   <div class="form-top">
			   
			         <div class="form-top-left">
                        			<h3>IDC／ISP流量统计与质量监测系统</h3>
                     </div>
                     <div class="form-top-right">
                        			<i class="fa fa-key"></i>
                     </div>
			   </div>
			   <div class="form-bottom">
				   
				      <div class="form-group has-feedback">
				        <input type="username"  id="username" class="form-control form-control-change" placeholder="请输入账号">
				        <span class="glyphicon glyphicon-user form-control-feedback form-control-feedback-change"></span>
				      </div>
				      <div class="form-group has-feedback ">
				        <input type="password" id="password" class="form-control form-control-change" placeholder="请输入密码">
				        <span class="glyphicon glyphicon-lock form-control-feedback form-control-feedback-change"></span>
				      </div>
				     <div class="row">
						     <div class="form-group has-feedback col-md-8">
									 <input type="text" id="verifycode" class="form-control form-control-change" placeholder="验证码">
									 <span class="glyphicon glyphicon-lock form-control-feedback form-control-feedback-change padding-right"></span>
						     </div>
				             <div class="col-md-4">
				                     <img id="rCodeImg" src="randomCode.do" class="rCodeImg-psotion" onmouseover="this.style.cursor='pointer'" />  
				             </div>
				     </div>
				          <button type="submit" class="login-btn" id="loginBtn">登录</button>
				        <!-- /.col -->
				     
				    
			    <!-- /.social-auth-links -->
			      </div>
			  </div>
			  <!-- /.login-box-body -->
			        
            </div>
        </div>

<div class="modal fade" name="myModal" id="tipModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body" id="tipInfo">删除数据成功</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button> 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
        <!-- Javascript -->
      <!-- jQuery 2.2.3 -->
		<script src="<%=basePath %>/node_modules/admin-lte/plugins/jQuery/jquery-2.2.3.min.js"></script>
		<!-- Bootstrap 3.3.6 -->
		<script src="<%=basePath %>/node_modules/admin-lte/bootstrap/js/bootstrap.min.js"></script>
		<!-- SlimScroll -->
		<script src="<%=basePath %>/node_modules/admin-lte/plugins/slimScroll/jquery.slimscroll.min.js"></script>
		
		<!-- AdminLTE App -->
		<script src="<%=basePath %>/node_modules/admin-lte/dist/js/app.js"></script>
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
        <script src="<%=basePath %>/js/login.js"></script>
    
    </body>

</html>
