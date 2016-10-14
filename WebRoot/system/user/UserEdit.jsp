<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userid=(String)request.getSession().getAttribute("userid");//用户id
String username=(String)request.getSession().getAttribute("username");//用户名
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>IDC／ISP流量统计与质量监测系统</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="<%=basePath  %>/node_modules/admin-lte/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=basePath  %>/node_modules/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="<%=basePath  %>/node_modules/ionicons/dist/css/ionicons.min.css">
   <!-- DataTables -->
  <link rel="stylesheet" href="<%=basePath  %>/node_modules/admin-lte/plugins/datatables/dataTables.bootstrap.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=basePath %>/node_modules/admin-lte/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="<%=basePath  %>/node_modules/admin-lte/dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="<%=basePath  %>/css/newAddStyle.css">
   <!-- bootstrap datepicker -->
   <link rel="stylesheet" href="<%=basePath  %>/node_modules/admin-lte/plugins/datepicker/datepicker3.css">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition">
<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
 
    <!-- Content Header (Page header) -->
    <!-- Main content -->
    <section class="content">
 
 <form class="form-horizontal" role="form">
  <div class="form-group form-group-sm ">
    <label for="loginUser" class="col-xs-2 control-label nopadding">登录账号
    </label>
    <div class="col-xs-4 nopadding">
  	 <input type="text" class="form-control" id="loginUser" >
   </div>
    <label for="userName" class="col-xs-2 control-label nopadding1">用户姓名</label>
   	<div class="col-xs-4 nopadding">
   	 <input type="text" class="form-control" id="userName">
   	</div> 
  </div>

  
   <div class="form-group form-group-sm ">
    <label for="userPassword" class="col-xs-2 control-label nopadding">用户密码
    </label>
    <div class="col-xs-4 nopadding">
  	 <input type="text" class="form-control" id="userPassword" >
   </div>
    <label for="confirmPwd" class="col-xs-2 control-label nopadding1">确认密码</label>
   	<div class="col-xs-4 nopadding">
   	 <input type="text" class="form-control" id="confirmPwd">
   	</div> 
  </div>
  
  
  
  
  
   <div class="form-group form-group-sm ">
      <label for="sex" class="col-xs-2 control-label nopadding">用户性别</label>
	    
			<select class="col-xs-4 input-sm nopadding" id="sex">
				<option value="">男</option>
				<option value="">女</option>
			</select>
		
	
       <label for="datepicker" class="col-xs-2 control-label nopadding1">出生年月</label>
           <div class="col-xs-4 nopadding">
		   <div class="input-group date ">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="text" class="form-control pull-right" id="datepicker">
                </div>
	       </div>
  </div>
  
  
   <div class="form-group form-group-sm ">
    <label for="department" class="col-xs-2 control-label nopadding">所属部门
    </label>
        <select class="col-xs-4 input-sm nopadding" id="department">
        </select>
	
    <label for="job" class="col-xs-2 control-label nopadding1">岗位</label>
   	    <select class="col-xs-4 input-sm nopadding" id="job">
        </select>
  </div>
  
   <div class="form-group form-group-sm ">
    <label for="role" class="col-xs-2 control-label nopadding">用户角色
    </label>
        <select class="col-xs-4 input-sm nopadding" id="role">
        </select>
	
    <label for="post" class="col-xs-2 control-label nopadding1">职务</label>
   	    <select class="col-xs-4 input-sm nopadding" id="post">
        </select>
  </div>
    
    
    
   <div class="form-group form-group-sm ">
    <label for="area" class="col-xs-2 control-label nopadding">所属区域
    </label>
        <select class="col-xs-4 input-sm nopadding" id="area">
        </select>
    <label for="dataCenter" class="col-xs-2 control-label nopadding1">所属数据中心</label>
   	    <select class="col-xs-4 input-sm nopadding" id="dataCenter">
        </select>
  </div>
    
    
    
     <div class="form-group form-group-sm ">
    <label for="companyPhone" class="col-xs-2 control-label nopadding">公司座机
    </label>
    <div class="col-xs-4 nopadding">
  	 <input type="text" class="form-control" id="companyPhone" >
   </div>
    <label for="homePhone" class="col-xs-2 control-label nopadding1">家庭座机</label>
   	<div class="col-xs-4 nopadding">
   	 <input type="text" class="form-control" id="homePhone">
   	</div> 
  </div>
    
    
  
    
     <div class="form-group form-group-sm ">
    <label for="companyMobPhone" class="col-xs-2 control-label nopadding">公司手机
    </label>
    <div class="col-xs-4 nopadding">
  	 <input type="text" class="form-control" id="companyMobPhone" >
   </div>
    <label for="PersonPhone" class="col-xs-2 control-label nopadding1">私人手机</label>
   	<div class="col-xs-4 nopadding">
   	 <input type="text" class="form-control" id="PersonPhone">
   	</div> 
  </div>
  
   <div class="form-group form-group-sm ">
    <label for="companyMail" class="col-xs-2 control-label nopadding">公司邮箱
    </label>
    <div class="col-xs-4 nopadding">
  	 <input type="text" class="form-control" id="companyMail" >
   </div>
    <label for="PersonMail" class="col-xs-2 control-label nopadding1">私人邮箱</label>
   	<div class="col-xs-4 nopadding">
   	 <input type="text" class="form-control" id="PersonMail">
   	</div> 
  </div>
    <div class="form-group form-group-sm">
    <label for="description" class="col-xs-2 control-label nopadding">用户描述</label>
    <div class="col-xs-10 form-group">
	<textarea class="form-control" rows="3"  id="description"></textarea>
	</div>
    </div>
</form>
    
    </section>
  <div class="control-sidebar-bg"></div>
</div>


<!-- jQuery 2.2.3 -->
<script src="<%=basePath  %>/node_modules/admin-lte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<%=basePath  %>/node_modules/admin-lte/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="<%=basePath  %>/node_modules/admin-lte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="<%=basePath  %>/node_modules/admin-lte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="<%=basePath  %>/node_modules/admin-lte/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="<%=basePath  %>/node_modules/admin-lte/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="<%=basePath  %>/node_modules/admin-lte/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=basePath  %>/node_modules/admin-lte/dist/js/demo.js"></script>
 <!-- bootstrap datepicker -->
<script src="<%=basePath  %>/node_modules/admin-lte/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="<%=basePath  %>/js/userAdd.js"></script>
<!-- page script -->

</body>
</html>
