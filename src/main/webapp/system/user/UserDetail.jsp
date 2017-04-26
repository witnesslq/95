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
    <label for="detail_loginUser" class="col-xs-2 control-label nopadding font-size">登录账号
    </label>
    <div class="col-xs-4 nopadding" >
  	 <input type="text" class="form-control"  title="000" id="detail_loginUser" value="" readonly>
   </div>
    <label for="detail_userName" class="col-xs-2 control-label nopadding1 font-size">用户姓名</label>
   	<div class="col-xs-4 nopadding">
   	 <input type="text" class="form-control " id="detail_userName" readonly>
   	</div> 
  </div>

  
  
   <div class="form-group form-group-sm ">
      <label for="sex" class="col-xs-2 control-label nopadding font-size">用户性别</label>
	    
	    <div class="col-xs-4 nopadding">
  	     <input type="text" class="form-control" id="detail_sex" readonly>
      </div>
       <label for="datepicker" class="col-xs-2 control-label nopadding1 font-size">出生年月</label>
         <div class="col-xs-4 nopadding">
  	     <input type="text" class="form-control" id="detail_datepicker" readonly>
      </div>
		
  </div>
  
  
   <div class="form-group form-group-sm ">
    <label for="department" class="col-xs-2 control-label nopadding font-size">所属部门
    </label>
     <div class="col-xs-4 nopadding">
       <input type="text" class="form-control" id="detail_department" readonly>
	</div>
    <label for="job" class="col-xs-2 control-label nopadding1 font-size">岗位</label>
     <div class="col-xs-4 nopadding">
   	     <input type="text" class="form-control" id="detail_job" readonly>
   	     </div>
  </div>
  
   <div class="form-group form-group-sm ">
    <label for="role" class="col-xs-2 control-label nopadding font-size">用户角色</label>
     <div class="col-xs-4 nopadding">
        <input type="text" class="form-control" id="detail_role" readonly>
	</div>
    <label for="post" class="col-xs-2 control-label nopadding1 font-size">职务</label>
     <div class="col-xs-4 nopadding">
   	  <input type="text" class="form-control" id="detail_post" readonly>
   	  </div>
  </div>
    
    
    
   <div class="form-group form-group-sm ">
    <label for="area" class="col-xs-2 control-label nopadding font-size" >所属区域
    </label>
     <div class="col-xs-4 nopadding">
        <input type="text" class="form-control" id="detail_area" readonly>
        </div>
    <label for="dataCenter" class="col-xs-2 control-label nopadding1 font-size">数据中心</label>
   	    <div class="col-xs-4 nopadding">
   	    <input type="text" class="form-control" id="detail_dataCenter" readonly>
   	    </div>
  </div>
    
    
    
     <div class="form-group form-group-sm ">
    <label for="companyPhone" class="col-xs-2 control-label nopadding font-size">公司座机
    </label>
    <div class="col-xs-4 nopadding">
  	 <input type="text" class="form-control" id="detail_companyPhone" readonly>
   </div>
    <label for="homePhone" class="col-xs-2 control-label nopadding1 font-size">家庭座机</label>
   	<div class="col-xs-4 nopadding">
   	 <input type="text" class="form-control" id="detail_homePhone" readonly>
   	</div> 
  </div>
    
    
  
    
     <div class="form-group form-group-sm ">
    <label for="companyMobPhone" class="col-xs-2 control-label nopadding font-size">公司手机
    </label>
    <div class="col-xs-4 nopadding">
  	 <input type="text" class="form-control" id="detail_companyMobPhone" readonly>
   </div>
    <label for="PersonPhone" class="col-xs-2 control-label nopadding1 font-size">私人手机</label>
   	<div class="col-xs-4 nopadding">
   	 <input type="text" class="form-control" id="detail_PersonPhone" readonly>
   	</div> 
  </div>
  
   <div class="form-group form-group-sm ">
    <label for="companyMail" class="col-xs-2 control-label nopadding font-size">公司邮箱
    </label>
    <div class="col-xs-4 nopadding">
  	 <input type="text" class="form-control" id="detail_companyMail" readonly>
   </div>
    <label for="PersonMail" class="col-xs-2 control-label nopadding1 font-size">私人邮箱</label>
   	<div class="col-xs-4 nopadding">
   	 <input type="text" class="form-control" id="detail_PersonMail" readonly>
   	</div> 
  </div>
    <div class="form-group form-group-sm">
    <label for="description" class="col-xs-2 control-label nopadding font-size">用户描述</label>
    <div class="col-xs-10 nopadding">
	<textarea class="form-control" rows="3"  id="detail_description" readonly></textarea>
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
<script  type="text/javascript"  src="<%=basePath  %>js/dateformat.js"></script>   

<script src="<%=basePath  %>/js/userDetail.js"></script>
<!-- page script -->

</body>
</html>
