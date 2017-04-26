
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
    <section class="content-header">
      <h1>
        客户信息展示
      
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 工作台</a></li>
        <li class="active"><a href="customerList.jsp" target="content">我的客户</a></li>   
        <li class="active"><a href="#">  客户信息展示</a></li>
      </ol>
    </section>
    <section class="content">
   <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">集团客户信息</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>  
              </div>
            </div>
            <div class="box-body">
                <form  class="form-horizontal" role="form">
                  <div class="form-group form-group-sm customer-content-side">
				    <label for="no" class="col-xs-2 control-label nopadding font-size font-padding-size">客户编号
				    </label>
				    <div class="col-xs-4 nopadding">
				  	   <input type="text" class="form-control" id="no" readonly>
				    </div>
				   
				    <label for="type" class="col-xs-2 control-label nopadding1 font-size font-padding-size">客户类型</label>
				   	<div class="col-xs-4 nopadding"> 
				      	<input type="text" class="form-control" id="type" readonly>   
				   	</div> 
				  </div>
				  <div class="form-group form-group-sm customer-content-side">
				    <label for="customerlevel" class="col-xs-2 control-label nopadding font-size font-padding-size">客户级别
				    </label>
				    <div class="col-xs-4 nopadding">
				      <input type="text" class="form-control" id="customerlevel" readonly>    	  
				    </div> 
				    <label for="name" class="col-xs-2 control-label nopadding1 font-size font-padding-size">客户名称</label>
				   	<div class="col-xs-4 nopadding"> 
				   	   <input type="text" class="form-control" id="name" readonly>
				   	</div> 
				  </div> 
				  <div class="form-group form-group-sm customer-content-side">
				    <label for="sortname" class="col-xs-2 control-label nopadding font-size font-padding-size">客户简称
				    </label>
				    <div class="col-xs-4 nopadding">
				  	    <input type="text" class="form-control" id="sortname" readonly>
				    </div>
				   
				    <label for="customerproperty" class="col-xs-2 control-label nopadding1 font-size font-padding-size">客户性质</label>
				   	<div class="col-xs-4 nopadding"> 
				   	  <input type="text" class="form-control" id="customerproperty" readonly> 
				   	</div> 
				  </div>
				  <div class="form-group form-group-sm customer-content-side">
				    <label for="parentid" class="col-xs-2 control-label nopadding font-size font-padding-size">上级客户
				    </label>
				    <div class="col-xs-4 nopadding">
				  	    <input type="text" class="form-control" id="parentid" readonly>
				    </div>
				   
				    <label for="manager" class="col-xs-2 control-label nopadding1 font-size font-padding-size">客户经理</label>
				   	<div class="col-xs-4 nopadding"> 
				   	   <input type="text" class="form-control" id="manager" readonly> 	  
				   	</div> 
				  </div>
				  
				  <div class="form-group form-group-sm customer-content-side">
				    <label for="corporate" class="col-xs-2 control-label nopadding font-size font-padding-size">法人代表
				    </label>
				    <div class="col-xs-4 nopadding">
				  	    <input type="text" class="form-control" id="corporate" readonly>
				    </div>
				   
				    <label for="province" class="col-xs-2 control-label nopadding1 font-size font-padding-size">客户省份</label>
				   	<div class="col-xs-4 nopadding"> 
				   	     <input type="text" class="form-control" id="province" readonly>
				   	  
				   	</div> 
				  </div>
				  
				  
				  <div class="form-group form-group-sm customer-content-side">
				    <label for="contactname" class="col-xs-2 control-label nopadding font-size font-padding-size">客户联系人
				    </label>
				    <div class="col-xs-4 nopadding">
				  	   <input type="text" class="form-control" id="contactname" readonly>
				    </div>
				   
				    <label for="mobilephone" class="col-xs-2 control-label nopadding1 font-size font-padding-size">客户手机号码</label>
				   	<div class="col-xs-4 nopadding"> 
				   	   <input type="text" class="form-control" id="mobilephone" readonly>
				   	</div> 
				  </div>
				  
				  
				   <div class="form-group form-group-sm customer-content-side">
				    <label for="contactphone" class="col-xs-2 control-label nopadding font-size font-padding-size">座机号码
				    </label>
				    <div class="col-xs-10 nopadding">
				  	   <input type="text" class="form-control" id="contactphone" readonly>
				    </div>
				  </div> 	
				  
				  
				    
				  <div class="form-group form-group-sm customer-content-side">
				    <label for="contactaddress" class="col-xs-2 control-label nopadding font-size font-padding-size">通讯地址
				    </label>
				    <div class="col-xs-10 nopadding">
				  	    <input type="text" class="form-control" id="contactaddress" readonly>
				    </div>
				  </div>
				  
				  
				  <div class="form-group form-group-sm customer-content-side">
				    <label for="customerfield" class="col-xs-2 control-label nopadding font-size font-padding-size">客户域级
				    </label>
				    <div class="col-xs-4 nopadding">
				        <select class="form-control" id="customerfield" readonly>
			            </select>
				    </div>
				   
				    <label for="icpno" class="col-xs-2 control-label nopadding1 font-size font-padding-size">ICP证号</label>
				   	<div class="col-xs-4 nopadding"> 
				   	   <input type="text" class="form-control" id="icpno" readonly>
				   	</div> 
				  </div>
				  
				    <div class="form-group form-group-sm customer-content-side">
				    <label for="registername" class="col-xs-2 control-label nopadding font-size font-padding-size">备案名称
				    </label>
				    <div class="col-xs-4 nopadding">
				  	    <input type="text" class="form-control" id="registername" readonly>
				    </div>
				   
				    <label for="companyname" class="col-xs-2 control-label nopadding1 font-size font-padding-size">公司名称</label>
				   	<div class="col-xs-4 nopadding">  	  
			            <input type="text" class="form-control" id="companyname" readonly>
				   	</div> 
				  </div>
				  
				  
				  <div class="form-group form-group-sm customer-content-side">	    
				    <label for="enddate" class="col-xs-2 control-label nopadding font-size font-padding-size">合同终止日期</label>
		            <div class="col-xs-4 nopadding">
				      <input type="text" class="form-control" id="enddate" readonly>
			         </div>  
			        <label for="regionid" class="col-xs-2 control-label nopadding1 font-size font-padding-size">开户属地</label>
				   	<div class="col-xs-4 nopadding"> 
				   	  <input type="text" class="form-control" id="regionid" readonly>
				   	</div> 
				  </div>
				  
				  
				    
				  <div class="form-group form-group-sm customer-content-side">
					    <label for="slano" class="col-xs-2 control-label nopadding font-size font-padding-size">SLA</label>
					    <div class="col-xs-10 nopadding">
						   <textarea class="form-control" rows="3"  id="slano"></textarea>
						</div>
				  </div>
				  
				  
				  <div class="form-group form-group-sm customer-content-side">
					    <label for="remark" class="col-xs-2 control-label nopadding font-size font-padding-size">备注</label>
					    <div class="col-xs-10 nopadding">
						   <textarea class="form-control" rows="3"  id="remark"></textarea>
						</div>
				  </div>
                </form>
            </div>
              <!-- /.box-body -->
          </div>
          
          <button type="button" class="btn btn-default" data-dismiss="modal"  id="save_all">关闭</button>
                
    </section>
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

<script src="<%=basePath  %>bussiness/sxydidc/js/common.js"></script>
<script src="customerView.js"></script>
<!-- page script -->

</body>
</html>
