
<%@ page import="com.dhcc.bussiness.sxydidc.datacenter.DataCenterModel" language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userid=(String)request.getSession().getAttribute("userid");//用户id
String username=(String)request.getSession().getAttribute("username");//用户名
DataCenterModel dc=(DataCenterModel)request.getSession(true).getAttribute("dc");
String id=(String)request.getParameter("id");
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
    <label for="name" class="col-xs-2 control-label nopadding font-size">服务器名称
    </label>
    <div class="col-xs-4 nopadding">
  	 <input type="text" class="form-control" id="name" />
   </div>
   
    <label for="code" class="col-xs-2 control-label nopadding1 font-size">服务器编码</label>
   	<div class="col-xs-4 nopadding"> 
   	 <input type="text" class="form-control" id="code" >
   	</div> 
  </div>

  
   <div class="form-group form-group-sm ">
    <label for="sn" class="col-xs-2 control-label nopadding font-size">设备序列号
    </label>
    <div class="col-xs-4 input-sm nopadding">
     <input type="text" class="form-control" id="sn" />
   </div>
    <label for="moduletype" class="col-xs-2 control-label nopadding1 font-size">服务器型号</label>
   	<div class="col-xs-4 input-sm nopadding">
   	 <input type="text" class="form-control" id="moduletype"/>
   	</div> 
  </div>
  
  
   <div class="form-group form-group-sm ">
    <label for="devicetype" class="col-xs-2 control-label nopadding font-size">设备类型
    </label>
    <div  class="col-xs-4 input-sm nopadding ">
        <input type="text" class="form-control" id="devicetype" >
	</div>
    <label for="owner" class="col-xs-2 control-label nopadding1 font-size">设备所属方
    </label>
    <div  class="col-xs-4 input-sm nopadding ">
   	    <input type="text" class="form-control" id="owner" >
        </div>
  </div>
  
   <div class="form-group form-group-sm ">
    <label for="roomid" class="col-xs-2 control-label nopadding font-size">所属机房
    </label>
    <div  class="col-xs-4 input-sm nopadding ">
       <input type="text" class="form-control" id="roomid" >
	</div>
    <label for="rackid" class="col-xs-2 control-label nopadding1 font-size">所属机架</label>
   	    <div  class="col-xs-4 input-sm nopadding ">
   	    <input type="text" class="form-control" id="rackid" >
        </div>
  </div>
    
    
     <div class="form-group form-group-sm ">
      <label for="ipid" class="col-xs-2 control-label nopadding font-size">设备IP地址</label>
	    <div  class="col-xs-4 input-sm nopadding ">
			<input type="text" class="form-control" id="ipid" >
		</div>
	
       <label for="customerid" class="col-xs-2 control-label nopadding1 font-size">所属客户</label>
		     <div  class="col-xs-4 input-sm nopadding ">
				<input type="text" class="form-control" id="customerid" >
		     </div>      
  </div>
  
   <div class="form-group form-group-sm ">
    <label for="startu" class="col-xs-2 control-label nopadding font-size">起始U位
    </label>
     <div  class="col-xs-4 input-sm nopadding ">
       <input type="text" class="form-control" id="startu" >
        </div>
    <label for="ucount" class="col-xs-2 control-label nopadding1 font-size">占用U位数</label>
   	   <div  class="col-xs-4 input-sm nopadding ">
   	    <input type="text" class="form-control" id="ucount" >
        </div>
  </div>
    
    
    
     <div class="form-group form-group-sm ">

    <label for="status" class="col-xs-2 control-label nopadding font-size">状态</label></label>
   	<div class="col-xs-4 nopadding">
   	  <input type="text" class="form-control" id="status" >
   	</div> 
   	
   	 <label for="power" class="col-xs-2 control-label nopadding font-size">功率（W）</label></label>
   	<div class="col-xs-4 nopadding">
   	  <input type="text" class="form-control" id="power" >
   	</div> 
  </div>
    
    <div class="form-group form-group-sm">
    <label for="buytime" class="col-xs-2 control-label nopadding font-size">购入时间</label>
    <div class="col-xs-4 input-sm nopadding">
	 <input type="text" class="form-control" id="buytime" >
	</div>
	
	<label for="useyears" class="col-xs-2 control-label nopadding font-size">使用年限</label>
    <div class="col-xs-4 input-sm nopadding">
	 <input type="text" class="form-control" id="useyears" >
	</div>
    </div>
  
   <div class="form-group form-group-sm">
    <label for="dcname" class="col-xs-2 control-label nopadding font-size">所属数据中心</label>
    <div class="col-xs-10 nopadding">
	 <input type="text" class="form-control" value=<%=dc!=null?dc.getName():""%> id="dcname" />
	</div>
    </div>

<div class="form-group form-group-sm">
    <label for="remark" class="col-xs-2 control-label nopadding font-size">服务器配置</label>
    <div class="col-xs-10 nopadding">
	<textarea class="form-control" rows="3"  id="remark" ></textarea>
	</div>
    </div>

    <div class="form-group form-group-sm">
    <label for="comment" class="col-xs-2 control-label nopadding font-size">备注</label>
    <div class="col-xs-10 nopadding">
	<textarea class="form-control" rows="3"  id="comment" ></textarea>
	</div>
    </div>
</form>
    
    <input type="hidden" id="subid" value=<%=id %>>

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
<script src="rserviceView.js"></script>
<!-- page script -->

</body>
</html>
