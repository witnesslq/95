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
String customerId = request.getParameter("customerId"),
customerName = request.getParameter("customerName"),
portCount = request.getParameter("portCount");
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
					<link rel="stylesheet" href="<%=basePath%>node_modules/admin-lte/plugins/datepicker/datepicker3.css">
					<link rel="stylesheet" href="<%=basePath%>node_modules/admin-lte/plugins/daterangepicker/daterangepicker.css">
					<!-- Theme style -->
					<link rel="stylesheet"
						href="<%=basePath%>/node_modules/admin-lte/dist/css/AdminLTE.min.css">
						<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
						page. However, you can choose any other skin. Make sure you
						apply the skin class to the body tag so the changes take effect.
						-->
						<link rel="stylesheet"
							href="<%=basePath%>/node_modules/admin-lte/dist/css/skins/skin-blue.min.css">
							<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
							<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
							<!--[if lt IE 9]>
							<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
							<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
							<![endif]-->
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
										质量监测数据 <small><%=customerName  %></small>
										</h1>
										<ol class="breadcrumb">
											<li><a href="#"><i class="fa fa-dashboard"></i> 质量监测</a></li>
											<li class="active">可视化</li>
										</ol>
									</section>
									<!-- Main content -->
									<section class="content">
										
										<!-- 质量监测的图表 -->
										<div class="box box-default">
											<div class="box-header with-border">
												<h3 class="box-title">错误率、丢包率统计</h3>
												<div class="box-tools pull-right">
													<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
												</div>
											</div>
											<div class="box-body">
												<div class="container-fluid">
													<div class="row">
														
														
														<div class="col-md-4">
															<!-- 分时段查询控件 -->
															<div class="input-group" id="dateForRatio">
																<div class="input-group-btn">
																	<button class="btn btn-default " data-toggle="dropdown" >按天<span class="caret"></span></button>
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
																<input type="text" data-view-mode="0" data-mask="" class="form-control">
																<div class="input-group-btn">
																	<button class="btn btn-default query-btn" type="button">查询</button>
																</div>
															</div>
															
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<div id="ratioContainer">
																
															</div>
														</div>
													</div>
												</div>
												
											</div>
											
										</div>
										<!-- 质量监测的图表 -->
										<!-- <div class="box box-default">
												<div class="box-header with-border">
														<h3 class="box-title">告警统计</h3>
														<div class="box-tools pull-right">
																<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
														</div>
												</div>
												<div class="box-body">
														<div class="container-fluid">
																<div class="row">
																		<div class="container-fluid">
																				<div class="row">
																						<div class="col-md-4">
																								<div class="input-group" id="dateForAlarm">
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
																										<input type="text" data-mask="" class="form-control" data-view-mode="0">
																										<div class="input-group-btn">
																												<button class="btn btn-default query-btn" type="button">查询</button>
																										</div>
																								</div>
																						</div>
																				</div>
																				<div class="row">
																						<div class="col-md-12">
																								<div id="alarmContainer">
																										
																								</div>
																						</div>
																				</div>
																		</div>
																</div>
														</div>
												</div>
												
										</div> -->
										<!-- IP表 -->
										<div id="portList" class="box box-default">
											<div class="box-header with-border">
												<h3 class="box-title">端口
													<span class="label label-primary">共<%=portCount  %>个</span>
												</h3>
												<!-- <div class="box-tools">
														<div class="has-feedback">
																<input type="text" class="form-control input-sm" placeholder="输入IP">
																<span class="glyphicon glyphicon-search form-control-feedback"></span>
														</div>
												</div> -->
												<div class="box-tools pull-right"><button class="btn btn-box-tool" data-widget="collapse">
													<i class="fa fa-minus"></i>
												</button></div>
											</div>
											<div class="box-body">
												
											</div>
											<div class="box-footer">
												*点击端口，输入日期，点击查询按钮查询
											</div>
										</div>
										
									</section>
									<!-- /.content -->
								</div>
							</div>

							<%@ include file="/system/commons/alert_modal.jsp"  %>
							<!-- REQUIRED JS SCRIPTS -->
							<!-- jQuery 2.2.3 -->
							<script
							src="<%=basePath%>/node_modules/admin-lte/plugins/jQuery/jquery-2.2.3.min.js"></script>
							<!-- Bootstrap 3.3.6 -->
							<script
							src="<%=basePath%>/node_modules/admin-lte/bootstrap/js/bootstrap.min.js"></script>
							<!-- highchats -->
							<script src="<%=basePath%>node_modules/highcharts/highcharts.js"></script>
							<script src="<%=basePath%>node_modules/highcharts/modules/exporting.js"></script>
							<script src="<%=basePath%>node_modules/admin-lte/plugins/datepicker/bootstrap-datepicker.js"></script>
							<script src="<%=basePath%>node_modules/admin-lte/plugins/daterangepicker/moment.min.js"></script>
							<script src="<%=basePath%>/node_modules/admin-lte/plugins/daterangepicker/daterangepicker.js"></script>
							<!-- InputMask -->
							<script src="<%=basePath%>node_modules/admin-lte/plugins/input-mask/jquery.inputmask.js"></script>
							<script src="<%=basePath%>node_modules/admin-lte/plugins/input-mask/jquery.inputmask.extensions.js"></script>
							<script src="<%=basePath%>node_modules/admin-lte/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
							<!-- AdminLTE App -->
							<script src="<%=basePath%>/node_modules/admin-lte/dist/js/app.min.js"></script>
							<script>
								var basePath = "<%=basePath  %>",
									customerId = "<%=customerId  %>",
									customerName = "<%=customerName  %>";
							</script>
							<!-- loading模板 -->
							<script type="text/x-ejs-template" id="overlayTmpl">
							<div class="overlay">
								<i class="fa fa-refresh fa-spin"></i>
							</div>
							</script>
							<script type="text/x-ejs-template" id="portBtnTmpl">
								<dl>
								<\%
										for(var i = 0,size = ips.length;i<size;i++){
										var ipToPorts = ips[i],
											ip = ipToPorts.ip,
											ports = ipToPorts.ports;
								 %>
									<dt>在设备<\%=ip  %>上<span class="label label-primary"><\%=ports.length  %>个端口</span></dt>
									<dd>
								 <\%
											for(var j=0,jSize = ports.length;j<jSize;j++){
											var port = ports[j];
								%>
									
										
										
									
									<button class="btn btn-default" type="button" data-node-id="<\%=port.nodeId  %>" data-if-index="<\%=port.ifIndex  %>"><\%=port.ifDesc  %></button>
								<\%
								}
								 %>
								 </dd>
								 <\% 
								}
								%>
								
								</dl>
							</script>
							<script src="<%=basePath  %>/node_modules/ejs/ejs.js"></script>
							<script src="<%=basePath%>/js/quality_visual.js"></script>
						</body>
					</html>