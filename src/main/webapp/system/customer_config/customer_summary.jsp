<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userid = (String) request.getSession()
			.getAttribute("userid");//用户id
	String username = (String) request.getSession().getAttribute(
			"username");//用户名

%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLTE 2 | Starter</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet"
	href="<%=basePath%>/node_modules/admin-lte/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=basePath%>/node_modules/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="<%=basePath%>/node_modules/ionicons/dist/css/ionicons.min.css">

<!-- Theme style -->
<link rel="stylesheet"
	href="<%=basePath%>/node_modules/admin-lte/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
    page. However, you can choose any other skin. Make sure you
    apply the skin class to the body tag so the changes take effect.
    -->
<link rel="stylesheet"
	href="<%=basePath%>/node_modules/admin-lte/dist/css/skins/skin-blue.min.css">
	<!-- DataTables -->
	<link rel="stylesheet" href="<%=basePath%>node_modules/admin-lte/plugins/datatables/dataTables.bootstrap.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<link rel="stylesheet" href="<%=basePath %>css/config.css"></link>
</head>
<!--
  BODY TAG OPTIONS:
  =================
  Apply one or more of the following classes to get the
  desired effect
  |---------------------------------------------------------|
  | SKINS         | skin-blue                               |
  |               | skin-black                              |
  |               | skin-purple                             |
  |               | skin-yellow                             |
  |               | skin-red                                |
  |               | skin-green                              |
  |---------------------------------------------------------|
  |LAYOUT OPTIONS | fixed                                   |
  |               | layout-boxed                            |
  |               | layout-top-nav                          |
  |               | sidebar-collapse                        |
  |               | sidebar-mini                            |
  |---------------------------------------------------------|
  -->
<body class="hold-transition skin-blue layout-top-nav">

	<div class="wrapper">
		<div class="content-wrapper">
			<!-- Content Wrapper. Contains page content -->

			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					客户
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">客户</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<!-- Your Page Content Here -->
				<div class="box box-default" id="customerBox">
					<div class="box-header with-border">
						<h3 class="box-title">所有客户</h3>
						<div class="box-tools"><button class="btn btn-box-tool" data-widget="collapse">
						<i class="fa fa-minus"></i>
						</button>
						</div>
					</div>
					<div class="box-body">
						<a class="btn btn-default" href="customer_detail.jsp">
						<i class="fa fa-user-plus"></i>添加客户</a>
						<hr>
						<table id="customerTable" class="table"></table>
						
					</div>
					<!-- /.box-body -->
				</div>
			</section>
			<!-- /.content -->
		</div>
	</div>
	<%@ include file="/system/commons/common_widgets.jsp"  %>
	<%@ include file="/system/commons/common_confirm_dialog.jsp"  %>
	<script type="x-ejs-template" id="customerOperationTmpl">
          <div class="btn-group">
	          <a class="btn btn-default btn-sm" href="customer_detail.jsp?customer_id=<\%=customer.customerId  %>&customer_name=<\%=customer.customerName  %>&port_count=<\%=portCount  %>">修改</a>
	          <button class="btn btn-default btn-sm" type="button" data-toggle="dropdown">
	          	<span class="caret"></span>
	          </button>
	          <ul class="dropdown-menu">
	          	<li class="unbound-customer" data-customer-id="<\%=customer.customerId  %>"><a href="#">下线</a></li>
	          </ul>
          </div>
	</script>
	<!-- REQUIRED JS SCRIPTS -->
	<!-- jQuery 2.2.3 -->
	<script
		src="<%=basePath%>/node_modules/admin-lte/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script
		src="<%=basePath%>/node_modules/admin-lte/bootstrap/js/bootstrap.min.js"></script>

	<!-- DataTables -->
	<script src="<%=basePath%>node_modules/admin-lte/plugins/datatables/jquery.dataTables.js"></script>
	<script src="<%=basePath  %>/node_modules/admin-lte/plugins/datatables/dataTables.bootstrap.js">
	</script>
	<!-- ejs -->
	<script src="<%=basePath  %>node_modules/ejs/ejs.js"></script>
	<!-- AdminLTE App -->
	<script src="<%=basePath%>/node_modules/admin-lte/dist/js/app.min.js"></script>
	<script>
		var basePath = "<%=basePath  %>";
	</script>
	<script src="<%=basePath%>js/customer_summary.js"></script>
</body>
</html>