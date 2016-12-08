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
		<title>客户每月流量统计</title>
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
					<!-- full calendar -->
					<link rel="stylesheet" href="<%=basePath%>node_modules/fullcalendar/dist/fullcalendar.css">
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
										客户每月流量统计
										</h1>
										<ol class="breadcrumb">
											<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
											<li class="active">Here</li>
										</ol>
									</section>
									<!-- Main content -->
									<section class="content">
										<!-- Your Page Content Here -->
										<div id="customerMonthlyFlow" class="box box-default">
										<iframe src="" id="exportResult" name="exportResult" frameborder="0" style="display: none;visibility: hidden;width: 0;height: 0;margin: 0;padding: 0;" class="result"></iframe>
											<div class="box-header with-border">
												<h3 class="box-title">客户每月流量统计</h3>
												<div class="box-tools">
													<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
												</div>
											</div>
											<div class="box-body">
												<div class="container-fluid">
													<div class="row">
														<div class="col-md-12">
															<form action="" class="form-inline">
																<div class="form-group">
																	<label for="customerName">客户名称</label>
																	<input type="text" class="form-control" id="customerName" name="customerName" />
																</div>
															</form>
														</div>
													</div>
													<div class="row">

														<div class="col-md-12">
															<div  class="fc fc-ltr fc-unthemed calendar">
																<div class="fc-toolbar">
																	<div class="fc-right">
																	<div class="fc-button-group">
																		<button type="button" class="fc-prev-button fc-button fc-state-default fc-corner-left prev-year">
																		<span class="fc-icon fc-icon-left-single-arrow"></span>
																		</button>
																		<button type="button" class="fc-next-button fc-button fc-state-default fc-corner-right next-year"><span class="fc-icon fc-icon-right-single-arrow"></span>
																		</button>
																	</div>
																</div>
																	<div class="fc-center">
																		<h2 class="calendar-year">2016</h2>
																	</div>
																
																</div>
																<div class="fc-view-container">
																<div class="fc-view fc-month-view fc-basic-view">
																<table>
																<tbody>
																<tr>
																<td class="fc-widget-content">
																<div class="fc-day-grid-container">
																<div class="fc-day-grid">
															
																<div class="fc-row" style="height: 117px;">
																	<div class="fc-bg">
																	<table>
																	<tbody>
																		<tr>
																		<td></td>
																		<td></td>
																		<td></td>
																		<td></td>
																		<td ></td>
																		<td></td></tr>
																	</tbody>
																	</table>
																	</div>
																		<div class="fc-content-skeleton">
																		<table>
																		<thead>
																			<tr>
																			<td class="fc-day-number calendar-month">01</td>
																			<td class="fc-day-number calendar-month">02</td>
																			<td class="fc-day-number calendar-month">03</td>
																			<td class="fc-day-number calendar-month">04</td>
																			<td class="fc-day-number calendar-month">05</td>
																			<td class="fc-day-number calendar-month">06</td>
																			</tr>
																		</thead>
																		<tbody>
																			<tr>
																				<td class="pdf">
																					
																				</td>
																				<td class="pdf"></td>
																				<td class="pdf"></td>
																				<td class="pdf"></td>
																				<td class="pdf"></td>
																				<td class="pdf"></td>
																			</tr>

																			<tr>
																				<td class="word">
																					
																				</td>
																				<td class="word"></td>
																				<td class="word"></td>
																				<td class="word"></td>
																				<td class="word"></td>
																				<td class="word"></td>
																			</tr>
																		</tbody>
																	</table>
																</div>

																</div>
															
																<div class="fc-row" style="height: 117px;">
															
																	<div class="fc-bg">
																		<table>
																			<tbody>
																				<tr>
																					<td></td>
																					<td></td>
																					<td></td>
																					<td></td>
																					<td></td>
																					<td></td>
																				</tr>
																			</tbody>
																		</table>
																	</div>
																	
																		<div class="fc-content-skeleton">
																			<table>
																				<thead>
																					<tr><td class="fc-day-number calendar-month">07</td>
																					<td class="fc-day-number calendar-month">08</td>
																					<td class="fc-day-number calendar-month">09</td>
																					<td class="fc-day-number calendar-month">10</td>
																					<td class="fc-day-number calendar-month">11</td>
																					<td class="fc-day-number calendar-month">12</td></tr>
																				</thead>
																				<tbody>
																					<tr>
																					<td class="pdf"></td>
																					<td class="pdf"></td>
																					<td class="pdf"></td>
																					<td class="pdf"></td>
																					<td class="pdf"></td>
																					<td class="pdf"></td>
																					</tr>
																					<tr>
																					<td class="word"></td>
																					<td class="word"></td>
																					<td class="word"></td>
																					<td class="word"></td>
																					<td class="word"></td>
																					<td class="word"></td>
																					</tr>
																				</tbody>
																			</table>
																		</div>
																	</div>
																</div>
																</div>
																</td></tr>
																</tbody>
																</table>
																</div>
																</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<!-- /.box-body -->
											</div>
										</section>
										<!-- /.content -->
									</div>
								</div>
							<%@ include file="/system/commons/common_widgets.jsp"  %>
							
	<script type="text/x-ejs-template" id="customerTmpl">
		<\%
		if(customerList == null ||customerList.length == 0){
  				  %>
				没有客户
  			<\%
  				}else{

  			 %>
		<div class="list-group">
  			<\%
				for(i in customerList){
				var customer = customerList[i];
  			  %>
  			<a href="#" class="list-group-item" data-customer-id="<\%=customer.customerId  %>"><\%=customer.customerName  %></a>
  			<\%
				}
			}
  			  %>
		</div>
	</script>

	<script type="text/x-ejs-template" id="pdfTmpl">
		
		<a href="export_file.action?exportType=pdf&flowAccount.id.customerId=<\%=flowAccount.id.customerId  %>&date=<\%=date  %>" class="fc-day-grid-event fc-event" target="exportResult">
			<div class="fc-content">
						<span class="fc-time"></span> 
						<span class="fc-title">PDF</span>
			</div>
		</a>
	</script>
	<script type="text/x-ejs-template" id="wordTmpl">
		<a href="export_file.action?exportType=docx&flowAccount.id.customerId=<\%=flowAccount.id.customerId  %>&date=<\%=date  %>"  class="fc-day-grid-event fc-event" style="background-color:#00c0ef;border-color:#00c0ef"  target="exportResult">
			<div class="fc-content">
					<span class="fc-time"></span> 
					<span class="fc-title">Word</span>
			</div>
		</a>
	</script>
								<!-- REQUIRED JS SCRIPTS -->
								<!-- jQuery 2.2.3 -->
								<script
								src="<%=basePath%>/node_modules/admin-lte/plugins/jQuery/jquery-2.2.3.min.js"></script>
								<!-- Bootstrap 3.3.6 -->
								<script
								src="<%=basePath%>/node_modules/admin-lte/bootstrap/js/bootstrap.min.js"></script>
								<!-- moment.js -->
								<script src="<%=basePath%>node_modules/moment/moment.js"></script>
								<!-- calender -->
								<script src="<%=basePath%>node_modules/fullcalendar/dist/fullcalendar.js"></script>
								<script src="<%=basePath%>node_modules/fullcalendar/dist/locale/zh-cn.js"></script>
								<!-- AdminLTE App -->
								<script src="<%=basePath%>/node_modules/admin-lte/dist/js/app.min.js"></script>
							<script src="<%=basePath  %>/node_modules/ejs/ejs.js"></script>
								
								<script src="<%=basePath%>js/flow_report.js"></script>
							</body>
						</html>