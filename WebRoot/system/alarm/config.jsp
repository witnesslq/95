<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userid=(String)request.getSession().getAttribute("userid");//用户id
String username=(String)request.getSession().getAttribute("username");//用户名
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
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="<%=basePath  %>/node_modules/admin-lte/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=basePath  %>/node_modules/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="<%=basePath  %>/node_modules/ionicons/dist/css/ionicons.min.css">
    <link rel="stylesheet" href="<%=basePath  %>/node_modules/admin-lte/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet" href="<%=basePath  %>/node_modules/admin-lte/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<%=basePath  %>/node_modules/admin-lte/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
    page. However, you can choose any other skin. Make sure you
    apply the skin class to the body tag so the changes take effect.
    -->
    <link rel="stylesheet" href="<%=basePath  %>/node_modules/admin-lte/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="<%=basePath  %>/node_modules/admin-lte/plugins/iCheck/minimal/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="<%=basePath  %>/css/config.css">
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
          配置管理
          <small>Optional description</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
            <li class="active">配置管理</li>
          </ol>
        </section>
        <!-- Main content -->
        <section class="content">
          <!-- 流量采集 -->
          <div class="box box-default">
            <div class="box-header with-border">
              <h3 class="box-title">流量采集配置</h3>
            </div>
            <div class="box-body">
              <form action="" class="form-horizontal">
                <div class="form-group">
                  <label for="import" class="col-xs-1 control-label">导入模板</label>
                  <div class="col-xs-8">
                    <input id="import" type="file" class="form-control" value="导入模板">
                  </div>
                  <div class="col-xs-2"></div>
                </div>
                <div class="form-group">
                  <div class="col-xs-12 text-center">
                    <button class="btn btn-primary" type="submit">导入</button>
                    <button class="btn btn-default" type="button">导出</button>
                  </div>
                </div>
              </form>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- 门限阀值 -->
          <div class="box box-default">
            <div class="box-header with-border">
              <h3 class="box-title">门限阀值配置</h3>
            </div>
            <div class="box-body">
              <table  class="table">
                <thead>
                  <tr>
                    <th>序号</th>
                    <th>门限名称</th>
                    <th class="col-md" >阀值</th>
                    <th>操作</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>1</td>
                    <td>客户带宽利用率</td>
                    <td>
                      <input type="text" id="threshold1" />
                    </td>
                    <td>
                      <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
                    </td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>网络设备带宽利用率</td>
                    <td>
                      <input type="text" id="threshold2" />
                      
                    </td>
                    <td>
                      <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
                      
                    </td>
                  </tr>
                  <tr>
                    <td>3</td>
                    <td>网络设备丢包率</td>
                    <td>
                      <input type="text" id="threshold3" />
                      
                    </td>
                    <td>
                      <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
                      
                    </td>
                  </tr>
                  <tr>
                    <td>4</td>
                    <td>网络设备错报率</td>
                    <td>
                      <input type="text" id="threshold4" />
                      
                    </td>
                    <td>
                      <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
                      
                    </td>
                  </tr>
                  <tr>
                    <td>5</td>
                    <td>客户异常流量倍数</td>
                    <td>
                      <input type="text" id="threshold5" />
                      
                    </td>
                    <td>
                      <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
                      
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!-- 告警规则配置 -->
          <div class="box box-default">
            <div class="box-header with-border">
              <h3 class="box-title">告警规则配置</h3>
            </div>
            <div class="box-body">
              <table class="table">
                <thead>
                  <tr>
                    <th>序号</th>
                    <th>告警类别</th>
                    <th>告警值</th>
                    <th>告警级别</th>
                    <th>是否压制</th>
                    <th>通知方式</th>
                    <th>通知人</th>
                    <th>操作</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>1</td>
                    <td>客户带宽利用率下限</td>
                    <td>0-10</td>
                    <td>
                      <select name="" id="" class="form-control panic">
                        <option value="1" class="panic" selected="selected">严重</option>
                        <option value="2" class="crit">重要</option>
                        <option value="3" class="warn">次要</option>
                        <option value="4" class="notice">警告</option>
                      </select>
                    </td>
                    <td><select name="" id="" class="form-control">
                      <option value="0">否</option>
                      <option value="1">是</option>
                    </select>
                  </td>
                  <td>
                    <input type="checkbox" checked="checked">短信</input>
                    <input type="checkbox" checked="checked">邮件</input>
                  </td>
                  <td>
                    <div class="input-group">
                      <input type="text" class="form-control">
                      <div class="input-group-btn"><button class="btn btn-default" type="buton">通知人</button></div>
                    </div>
                  </td>
                  <td>
                    <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
                    
                  </td>
                </tr>
                <tr>
                  <td>1</td>
                  <td>客户带宽利用率下限</td>
                  <td>0-10</td>
                  <td><select name="" id="" class="form-control panic">
                    <option value="1" class="panic" selected="selected">严重</option>
                    <option value="2" class="crit">重要</option>
                    <option value="3" class="warn">次要</option>
                    <option value="4" class="notice">警告</option>
                  </select></td>
                  <td><select name="" id="" class="form-control">
                    <option value="0">否</option>
                    <option value="1">是</option>
                  </select>
                </td>
                <td>
                  <input type="checkbox" checked="checked">短信</input>
                  <input type="checkbox" checked="checked">邮件</input>
                </td>
                <td>
                  <div class="input-group">
                    <input type="text" class="form-control">
                    <div class="input-group-btn"><button class="btn btn-default" type="buton">通知人</button></div>
                  </div>
                </td>
                <td>
                  <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
                  
                </td>
              </tr>
              <tr>
                <td>2</td>
                <td>客户带宽利用率上限</td>
                <td>70-10</td>
                <td>
                  <select name="" id="" class="form-control panic">
                    <option value="1" class="panic" selected="selected">严重</option>
                    <option value="2" class="crit">重要</option>
                    <option value="3" class="warn">次要</option>
                    <option value="4" class="notice">警告</option>
                  </select></td>
                  <td><select name="" id="" class="form-control">
                    <option value="0">否</option>
                    <option value="1">是</option>
                  </select>
                </td>
                <td>
                  <input type="checkbox" checked="checked">短信</input>
                  <input type="checkbox" checked="checked">邮件</input>
                </td>
                <td>
                  <div class="input-group">
                    <input type="text" class="form-control">
                    <div class="input-group-btn"><button class="btn btn-default" type="buton">通知人</button></div>
                  </div>
                </td>
                <td>
                  <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
                  
                </td>
              </tr>
              <tr>
                <td>3</td>
                <td>网络设备带宽利用率下限</td>
                <td>0-10</td>
                <td><select name="" id="" class="form-control">
                  <option value="1">严重</option>
                  <option value="2">重要</option>
                  <option value="3">次要</option>
                  <option value="4">警告</option>
                </select></td>
                <td><select name="" id="" class="form-control">
                  <option value="0">否</option>
                  <option value="1">是</option>
                </select>
              </td>
              <td>
                <input type="checkbox" checked="checked">短信</input>
                <input type="checkbox" checked="checked">邮件</input>
              </td>
              <td>
                <div class="input-group">
                  <input type="text" class="form-control">
                  <div class="input-group-btn"><button class="btn btn-default" type="buton">通知人</button></div>
                </div>
              </td>
              <td>
                <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
                
              </td>
            </tr>
            <tr>
              <td>4</td>
              <td>网络设备带宽利用率上限</td>
              <td>80-100</td>
              <td><select name="" id="" class="form-control">
                <option value="1">严重</option>
                <option value="2">重要</option>
                <option value="3">次要</option>
                <option value="4">警告</option>
              </select></td>
              <td><select name="" id="" class="form-control">
                <option value="0">否</option>
                <option value="1">是</option>
              </select>
            </td>
            <td>
              <input type="checkbox" checked="checked">短信</input>
              <input type="checkbox" checked="checked">邮件</input>
            </td>
            <td>
              <div class="input-group">
                <input type="text" class="form-control">
                <div class="input-group-btn"><button class="btn btn-default" type="buton">通知人</button></div>
              </div>
            </td>
            <td>
              <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
              
            </td>
          </tr>
          <tr>
            <td>5</td>
            <td>丢包率门限</td>
            <td>10-100</td>
            <td><select name="" id="" class="form-control">
              <option value="1">严重</option>
              <option value="2">重要</option>
              <option value="3">次要</option>
              <option value="4">警告</option>
            </select></td>
            <td><select name="" id="" class="form-control">
              <option value="0">否</option>
              <option value="1">是</option>
            </select>
          </td>
          <td>
            <input type="checkbox" checked="checked">短信</input>
            <input type="checkbox" checked="checked">邮件</input>
          </td>
          <td>
            <div class="input-group">
              <input type="text" class="form-control">
              <div class="input-group-btn"><button class="btn btn-default" type="buton">通知人</button></div>
            </div>
          </td>
          <td>
            <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
            
          </td>
        </tr>
        <tr>
          <td>6</td>
          <td>错报率门限</td>
          <td>10-100</td>
          <td><select name="" id="" class="form-control">
            <option value="1">严重</option>
            <option value="2">重要</option>
            <option value="3">次要</option>
            <option value="4">警告</option>
          </select></td>
          <td><select name="" id="" class="form-control">
            <option value="0">否</option>
            <option value="1">是</option>
          </select>
        </td>
        <td>
          <input type="checkbox" checked="checked">短信</input>
          <input type="checkbox" checked="checked">邮件</input>
        </td>
        <td>
          <div class="input-group">
            <input type="text" class="form-control">
            <div class="input-group-btn"><button class="btn btn-default" type="buton">通知人</button></div>
          </div>
        </td>
        <td>
          <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
          
        </td>
      </tr>
      <tr>
        <td>7</td>
        <td>异常流量倍数门限</td>
        <td>10</td>
        <td><select name="" id="" class="form-control">
          <option value="1">严重</option>
          <option value="2">重要</option>
          <option value="3">次要</option>
          <option value="4">警告</option>
        </select></td>
        <td><select name="" id="" class="form-control">
          <option value="0">否</option>
          <option value="1">是</option>
        </select>
      </td>
      <td>
        <input type="checkbox" checked="checked">短信</input>
        <input type="checkbox" checked="checked">邮件</input>
      </td>
      <td>
        <div class="input-group">
          <input type="text" class="form-control">
          <div class="input-group-btn"><button class="btn btn-default" type="buton">通知人</button></div>
        </div>
      </td>
      <td>
        <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
        
      </td>
    </tr>
    <tr>
      <td>8</td>
      <td>端口通断</td>
      <td>Up</td>
      <td><select name="" id="" class="form-control">
        <option value="1">严重</option>
        <option value="2">重要</option>
        <option value="3">次要</option>
        <option value="4">警告</option>
      </select></td>
      <td><select name="" id="" class="form-control">
        <option value="0">否</option>
        <option value="1">是</option>
      </select>
    </td>
    <td>
     <label class="normal"> <input type="checkbox" checked="checked">短信</input></label>
     <label class="normal"> <input type="checkbox" checked="checked">邮件</input></label>
    </td>
    <td>
      <div class="input-group">
        <input type="text" class="form-control">
        <div class="input-group-btn"><button class="btn btn-default" type="buton" data-toggle="modal" data-target="#generalModal">通知人</button></div>
      </div>
    </td>
    <td>
      <button class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
    </td>
  </tr>
</tbody>
</table>
</div>
<!-- /.box-body -->
</div>
</section>
</div>
</div>

<div class="modal fade" id="generalModal">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" type="button" data-dismiss="modal">&times;</button>
<h4 class="modal-title">选择被通知人</h4>
</div>
<div class="modal-body">

<div class="container-fluid">

<div class="row">
<div class="col-xs-2">
  <label><input type="checkbox" value="">张三</label>
</div>
<div class="col-xs-2">
  <label><input type="checkbox" value="">张三</label>
</div>
<div class="col-xs-2">
  <label><input type="checkbox" value="">张三</label>
</div>
<div class="col-xs-2">
  <label><input type="checkbox" value="">张三</label>
</div>
<div class="col-xs-2">
  <label class="normal"><input type="checkbox" value="">张三</label>
</div>
<div class="col-xs-2">
  <label class="normal"><input type="checkbox" value="">张三三</label>
</div>
</div>
</div>

</div>
<div class="modal-footer">
<button class="btn btn-default" type="button" data-dismiss="modal" >取消</button>
<button class="btn btn-primary" type="button">确定</button>
</div>
</div>
</div>
</div>
<!-- /.content -->
<!-- REQUIRED JS SCRIPTS -->
<!-- jQuery 2.2.3 -->
<script src="<%=basePath  %>/node_modules/admin-lte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<%=basePath  %>/node_modules/admin-lte/bootstrap/js/bootstrap.min.js"></script>
<!-- Ion.rangeSlider -->
<script src="<%=basePath  %>/node_modules/admin-lte/plugins/ionslider/ion.rangeSlider.min.js"></script>
<!-- iCheck -->
<script src="<%=basePath  %>/node_modules/admin-lte/plugins/iCheck/icheck.js"></script>

<!-- AdminLTE App -->
<script src="<%=basePath  %>/node_modules/admin-lte/dist/js/app.min.js"></script>
<script src="<%=basePath  %>/js/alarm_config.js"></script>
</body>
</html>