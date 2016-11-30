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
   <link href="<%=basePath%>system/queryFlow/loading/jquery.loadmask.css" rel="stylesheet" type="text/css" />
      <script src="<%=basePath%>system/queryFlow/loading/jquery-latest.pack.js"></script>
	  <script src='<%=basePath%>system/queryFlow/loading/jquery.loadmask.js'></script>
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <link rel="stylesheet" href="<%=basePath  %>/css/newAddStyle.css">
</head>
<body class="hold-transition skin-blue layout-top-nav">
<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
 	<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
     	 <h1>
          	流量查询
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 流量查询</a></li>
            <li class="active">流量查询</li>
          </ol>
          <form role="form">
              <div class="box-body">
	              <table border='0' style="width:1000px;text-align:center;" >
		              	<tr>
		              		<td style="vertical-align: top;"> 
		                 	 	<div class="input-group">
					                <div class="input-group-btn">
					                 	 <button type="button" class="btn btn-danger">客户</button>
					                </div>
				                	<input type="text" class="form-control" id="user_id" style="width:200px">
				              	</div>
		             		</td>
		              		<td style="vertical-align: top;"> 
		                  		<div class="input-group">
					                <div class="input-group-btn">
					                 	 <button type="button" class="btn btn-danger">端口</button>
					                </div>
				                	<input type="text" class="form-control" id="ip_address" style="width:200px">
				              	</div>
							</td>
							<td style="vertical-align: top;">
								<div class="input-group" id="dateForRatio">
									<div class="input-group-btn">
										<button class="btn btn-default " data-toggle="dropdown">按天<span class="caret"></span></button>
										<ul class="dropdown-menu date">
											<li data-view-mode="0">
												<a href="####">按天</a>
											</li>
											<li data-view-mode="1">
												<a href="####">按月</a>
											</li>
											<li data-view-mode="2">
												<a href="####">按年</a>
											</li>
										</ul>
									</div>
									<input id="datetime" type="text" data-view-mode="0" data-mask="" class="form-control" style="width: 200px;">
								</div>
							</td>
							<td style="vertical-align: top;"><button id="query_submit" type="button" class="btn btn-primary" >查询</button></td>
		              	</tr>
	                </table>
              </div>
              <!-- /.box-body -->
            </form>
    </section>

    <!-- Main content -->
    <section class="content">
    	
           <div class="modal fade" name="myModal" id="userid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		       <div class="modal-content" style="width:700px;">
		           
		           <div class="modal-body" >
		           		
						<table id="example1" class="table table-bordered table-striped">
				          
              		</table> 
		           </div>
		           <div class="modal-footer">
		               <button type="button" class="btn btn-default" name="adduser">确定</button> 
		                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button> 
		           </div>
		       </div><!-- /.modal-content -->
		   </div><!-- /.modal -->
		</div>
		
		<div class="modal fade" name="myModal" id="ipaddress" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		       <div class="modal-content" style="width:700px;">
		           
		           <div class="modal-body" >
		           		<table id="example2" class="table table-bordered table-striped">
				          
              		</table>
		           </div>
		           <div class="modal-footer">
		               <button type="button" class="btn btn-default" id="ip_submit">确定</button> 
		                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button> 
		           </div>
		       </div><!-- /.modal-content -->
		   </div><!-- /.modal -->
		</div>
		<div class="box box-default">
		 <div class="box-header with-border">
           	图像显示
          </div>
          <div id="content" class="box-body">
          			<div class="overlay">
              				<i id="datashow" ></i>
           			   </div> 
            	<div id="container" style="min-width: 100px; height: 400px; margin: 0 auto"></div>
          </div>
          </div>
    </section>
	</div>
</div>
<input type="hidden" id="ip" />
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
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
<!-- highcharts -->
	 <script src="<%=basePath  %>/node_modules/highcharts/highcharts.js"></script>
      <!-- Optionally, you can add Slimscroll and FastClick plugins.
      Both of these plugins are recommended to enhance the
      user experience. Slimscroll is required when using the
      fixed layout. -->
      <!-- InputMask -->
	  <script src="<%=basePath%>node_modules/admin-lte/plugins/input-mask/jquery.inputmask.js"></script>
	  <script src="<%=basePath%>node_modules/admin-lte/plugins/input-mask/jquery.inputmask.extensions.js"></script>
	  <script src="<%=basePath%>node_modules/admin-lte/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
      <!-- page script -->   
      <script  type="text/javascript"  src="<%=basePath  %>js/dateformat.js"></script>   
	  <script  type="text/javascript"  src="<%=basePath  %>js/moment.js"></script>   
	  <script  type="text/javascript"  src="<%=basePath  %>js/daterangepicker.js"></script>   
<!-- page script -->   
<script src="flow.js"></script>


</body>
</html>
