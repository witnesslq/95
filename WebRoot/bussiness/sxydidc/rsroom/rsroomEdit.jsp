
<%@ page import="com.dhcc.bussiness.sxydidc.datacenter.DataCenterModel" language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userid=(String)request.getSession().getAttribute("userid");//用户id
String username=(String)request.getSession().getAttribute("username");//用户名
DataCenterModel dc=(DataCenterModel)request.getSession(true).getAttribute("dc");

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
    <label for="roomcode" class="col-xs-2 control-label nopadding font-size">机房编码<font class="muststyle">(必填)</font>
    </label>
    <div class="col-xs-4 nopadding">
  	 <input type="text" class="form-control" id="roomcode" readonly/>
   </div>
   
    <label for="roomname" class="col-xs-2 control-label nopadding1 font-size">机房名称<font class="muststyle">(必填)</font></label>
   	<div class="col-xs-4 nopadding"> 
   	 <input type="text" class="form-control" id="roomname">
   	</div> 
  </div>

  
   <div class="form-group form-group-sm ">
    <label for="grade" class="col-xs-2 control-label nopadding font-size">机房等级
    </label>
    <div class="col-xs-4 input-sm nopadding">
     <input type="text" class="form-control" id="grade" readonly/>
   </div>
    <label for="deptid" class="col-xs-2 control-label nopadding1 font-size">所属部门<font class="muststyle">(必填)</font></label>
   	<div class="col-xs-4 input-sm nopadding">
   	 <input type="text" class="form-control" id="deptid" readonly/>
   	</div> 
  </div>
  
  
   <div class="form-group form-group-sm ">
    <label for="colcount" class="col-xs-2 control-label nopadding font-size">行数<font class="muststyle">(必填)</font>
    </label>
    <div  class="col-xs-4 input-sm nopadding ">
        <input type="text" class="form-control" id="colcount" readonly>
	</div>
    <label for="rowcount" class="col-xs-2 control-label nopadding1 font-size">列数<font class="muststyle">(必填)</font>
    </label>
    <div  class="col-xs-4 input-sm nopadding ">
   	    <input type="text" class="form-control" id="rowcount" readonly>
        </div>
  </div>
  
   <div class="form-group form-group-sm ">
    <label for="maintancer" class="col-xs-2 control-label nopadding font-size">维护人员
    </label>
    <div  class="col-xs-4 input-sm nopadding ">
       <input type="text" class="form-control" id="maintancer">
	</div>
    <label for="telephone" class="col-xs-2 control-label nopadding1 font-size">手机号</label>
   	    <div  class="col-xs-4 input-sm nopadding ">
   	    <input type="text" class="form-control" id="telephone">
        </div>
  </div>
    
    
     <div class="form-group form-group-sm ">
      <label for="racktype" class="col-xs-2 control-label nopadding font-size">机架规格</label>
	    <div  class="col-xs-4 input-sm nopadding ">
			<input type="text" class="form-control" id="racktype" readonly>
		</div>
	
       <label for="areaid" class="col-xs-2 control-label nopadding1 font-size">所属区域</label>
		     <div  class="col-xs-4 input-sm nopadding ">
				<input type="text" class="form-control" id="areaid" readonly>
		     </div>      
  </div>
  
   <div class="form-group form-group-sm ">
    <label for="width" class="col-xs-2 control-label nopadding font-size">长(米)
    </label>
     <div  class="col-xs-4 input-sm nopadding ">
       <input type="text" class="form-control" id="width">
        </div>
    <label for="height" class="col-xs-2 control-label nopadding1 font-size">宽(米)</label>
   	   <div  class="col-xs-4 input-sm nopadding ">
   	    <input type="text" class="form-control" id="height">
        </div>
  </div>
    
    
    
     <div class="form-group form-group-sm ">

    <label for="status" class="col-xs-2 control-label nopadding font-size">是否启用<font class="muststyle">(必填)</font></label></label>
   	<div class="col-xs-10 nopadding">
   	 <select class="form-control" id="status">
			        <option value=""></option>
					<option value="01">启用</option>
					<option value="02">未启用</option>
				</select>
   	</div> 
  </div>
    
    <div class="form-group form-group-sm">
    <label for="address" class="col-xs-2 control-label nopadding font-size">地址</label>
    <div class="col-xs-10 nopadding">
	 <input type="text" class="form-control" id="address">
	</div>
    </div>
  
   <div class="form-group form-group-sm">
    <label for="dcname" class="col-xs-2 control-label nopadding font-size">所属数据中心</label>
    <div class="col-xs-10 nopadding">
	 <input type="text" class="form-control" value=<%=dc!=null?dc.getName():""%> id="dcname" readonly/>
	</div>
    </div>

    <div class="form-group form-group-sm">
    <label for="remark" class="col-xs-2 control-label nopadding font-size">备注</label>
    <div class="col-xs-10 nopadding">
	<textarea class="form-control" rows="3"  id="remark"></textarea>
	</div>
    </div>
</form>
    
    <input type="hidden" id="subid">
    <input type="hidden" id="dcid">
    <input type="hidden" id="gradelevle">
    <input type="hidden" id="deptidhidden">
    <input type="hidden" id="areaidhidden">
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
<script src="rsroomEdit.js"></script>
<!-- page script -->

</body>
</html>
