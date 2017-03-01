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
    <label for="menuname" class="col-xs-2 control-label nopadding font-size">菜单名称<font class="muststyle">(必填)</font>
    </label>
    <div class="col-xs-9 nopadding">
  	 <input type="text" class="form-control" id="menuname" >
   </div>
  </div>

  <div class="form-group form-group-sm ">
    <label for="topmenu" class="col-xs-2 control-label nopadding font-size">上级菜单
    </label>
      <div class="col-xs-9 nopadding">
  	     <select class="form-control" id="topmenu">
        </select>
      </div>
  </div>
  
   <div class="form-group form-group-sm ">
    <label for="menusqe" class="col-xs-2 control-label nopadding font-size">菜单排序<font class="muststyle">(必填)</font>
    </label>
      <div class="col-xs-9 nopadding">
  	     <input type="text" class="form-control" id="menusqe" >
      </div>
  </div>
  
   <div class="form-group form-group-sm ">
    <label for="doingadress" class="col-xs-2 control-label nopadding font-size">执行地址
    </label>
      <div class="col-xs-9 nopadding">
  	       <input type="text" class="form-control" id="doingadress" >
      </div>
  </div>
    <!--
     <div class="form-group form-group-sm ">
    <label for="imageurl" class="col-xs-2 control-label nopadding font-size">菜单图片url<font class="muststyle">(必填)</font>
    </label>
      <div class="col-xs-9 nopadding">
  	       <input type="text" class="form-control" id="imageurl" >
      </div>
  </div>
  -->
  <div class="form-group form-group-sm ">
    <label for="menustate" class="col-xs-2 control-label nopadding font-size">菜单状态<font class="muststyle">(必填)</font>
    </label>
      <div class="col-xs-9 nopadding">
  	       <select class="form-control" id="menustate">
  	          <option value="01">公开页面</option>
              <option value="02">私有页面</option>
           </select>
      </div>
  </div>
  
    <div class="form-group form-group-sm ">
    <label for="openmode" class="col-xs-2 control-label nopadding font-size">打开方式<font class="muststyle">(必填)</font>
    </label>
      <div class="col-xs-9 nopadding">
           <select class="form-control" id="openmode">
              <option value="1">刷新当前页面</option>
              <option value="2">tab页打开</option>
              <option value="3">弹出窗口</option>
           </select>
      </div>
  </div>
  
  
   <div class="form-group form-group-sm">
      <label for="remark" class="col-xs-2 control-label nopadding font-size">信息描述</label>
       <div class="col-xs-9 nopadding">
	      <textarea class="form-control" rows="3"  id="remark"></textarea>
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
<script src="menuAdd.js"></script>
<!-- page script -->

</body>
</html>
