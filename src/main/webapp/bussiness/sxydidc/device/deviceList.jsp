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
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <link rel="stylesheet" href="<%=basePath  %>/css/newAddStyle.css">
</head>
<body class="hold-transition">
<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
 
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        网络设备
      
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 资源管理</a></li>
        <li class="active"><a href="#">网络设备</a></li>   
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
            <button type="button" class="btn btn-default btn-sm" id="add_user" >
              <span class="fa fa-user-plus"></span> 增加
            </button>
            <button type="button" class="btn btn-default btn-sm" id="edit_user">
              <span class="fa fa-edit"></span> 编辑
            </button>
            <button type="button" class="btn btn-default btn-sm" id="detele_user">
              <span class="fa fa-user-times"></span> 删除
            </button>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
           
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
  <div class="control-sidebar-bg"></div>
</div>
<!-- 模态框（Modal） -->
<!-- 添加-->
<div class="modal fade" name="myModal" id="adduser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content mymodal-width">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><b>添加设备信息</b></h4>
            </div>
            <div class="modal-body" >
            <iframe src="deviceAdd.jsp" class="middleModel" frameborder="0" scrolling="no" name="adduser_content"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" name="adduser">添加</button> 
                 <button type="button" class="btn btn-default" data-dismiss="modal">取消</button> 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 修改-->
<div class="modal fade" name="myModal" id="editUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content mymodal-width">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><b>机房信息编辑</b></h4>
            </div>
            <div class="modal-body" >
            <iframe src="rackEdit.jsp" class="middleModel" frameborder="0" scrolling="no" name="editUser_content"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" name="editUser">修改</button> 
                 <button type="button" class="btn btn-default" data-dismiss="modal">取消</button> 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 详情-->
<div class="modal fade" name="myModal" id="detailUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content mymodal-width">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><b>机房信息详情</b></h4>
            </div>
            <div class="modal-body" >
            <iframe src="<%=basePath  %>/bussiness/sxydidc/rsroom/rsroomView.jsp" class="add-user" frameborder="0" scrolling="no" name="content_info"></iframe>
            </div>
            <div class="modal-footer">
                 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- -->
<div class="modal fade" name="myModal" id="RoomInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content mymodal-width">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><b>选择所属机房</b></h4>
            </div>
            <div class="modal-body" >
            <iframe src="<%=basePath  %>/bussiness/sxydidc/rsroom/rsroomListQuery.jsp" class="middleModel" frameborder="0" scrolling="no" name="room_info"></iframe>
            </div>
            <div class="modal-footer">
                 <button type="button" class="btn btn-default" name="getRoomInfo">确定</button> 
                 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- -->
<div class="modal fade" name="myModal" id="IpInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content mymodal-width">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><b>选择Ip地址</b></h4>
            </div>
            <div class="modal-body" >
            <iframe src="<%=basePath  %>/bussiness/sxydidc/ip/ipListQuery.jsp" class="middleModel" frameborder="0" scrolling="no" name="ip_info"></iframe>
            </div>
            <div class="modal-footer">
                 <button type="button" class="btn btn-default" name="getIpInfo">确定</button> 
                 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- -->
<div class="modal fade" name="myModal" id="RackInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content mymodal-width">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><b>选择所属机架</b></h4>
            </div>
            <div class="modal-body" >
            <iframe src="<%=basePath  %>/bussiness/sxydidc/rack/rackListQuery.jsp" class="middleModel" frameborder="0" scrolling="no" name="rack_info"></iframe>
            </div>
            <div class="modal-footer">
                 <button type="button" class="btn btn-default" name="getRackInfo">确定</button> 
                 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="myModal" name="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body" id="tipContent">请选择要数据</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<div class="modal fade" name="myModal" id="confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body">确定要删除选中的数据</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="detletedate()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" name="myModal" id="deleteSucess" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body">删除数据成功</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button> 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- ./wrapper -->

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
<script src="<%=basePath  %>/node_modules/admin-lte/dist/js/demo.js"></script>

<!-- page script -->   
<script src="deviceList.js"></script>


</body>
</html>