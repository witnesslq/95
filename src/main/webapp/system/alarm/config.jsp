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
              <form id="importForm" action="import_customer.action" target="importResult" class="form-horizontal" method="POST" enctype="multipart/form-data">
                <div class="form-group">
                  <label for="import" class="col-xs-1 control-label">导入模板</label>
                  <div class="col-xs-8">
                    <input id="import" type="file" name="file" class="form-control" value="导入模板">
                  </div>
                  <div class="col-xs-2"></div>
                </div>
                <div class="form-group">
                  <div class="col-xs-12 text-center">
                    <button class="btn btn-primary" type="submit">导入</button>
                    <!-- <button class="btn btn-default" type="button">导出</button> -->
                  </div>
                </div>
              </form>
            </div>
            <!-- /.box-body -->
          </div>
          <iframe src="" id="importResult" name="importResult" frameborder="0" style="display: none;visibility: hidden;width: 0;height: 0;margin: 0;padding: 0;"></iframe>
          <!-- 门限阀值 -->
          <div class="box box-default">
            <div class="box-header with-border">
              <h3 class="box-title">门限阀值配置</h3>
            </div>
            <div class="box-body">
              <table  class="table" id="alarmIndicatorsTable">
                <thead>
                  <tr>
                    <th>序号</th>
                    <th>门限名称</th>
                    <th class="col-md" >阀值</th>
                    <th>操作</th>
                  </tr>
                </thead>
                <tbody>
                  
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
              <table class="table" id="alarmRulesTable">
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
              
            </div>
          </div>
          <div class="modal-footer">
            <button class="btn btn-default" type="button" data-dismiss="modal" >取消</button>
            <button class="btn btn-primary" type="button" name="btn-save-receiver" id="" data-dismiss="modal" >确定</button>
          </div>
        </div>
      </div>
    </div>

    <div class="modal fade modal-danger" id="alertBox">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button class="close" type="button" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">警告</h4>
          </div>
          <div class="modal-body">
            <p></p>
          </div>
          <div class="modal-footer">
            <button class="btn btn-outline" type="button" data-dismiss="modal" >关闭</button>
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
    <script>
    var basePath = "<%= basePath %>";
    </script>
    <!-- 告警规则配置模板 -->
    <script type="text/x-ejs-template" id="alarmRulesTmpl">
    <\%
    for(var i = 0,size = alarmRules.length;i<size;i++){
    var rule = alarmRules[i],
    alarmIndicatorToRule = rule.alarmIndicator;
    %>
    <tr>
      <td><\%=i+1  %></td>
      <td><\%=alarmIndicatorToRule.name + rule.name  %></td>
      <td><\%=rule.value  %></td>
      <td>
        <select name="severity-rule<\%=rule.ruleId  %>" class="form-control <\%=rule.alarmSeverity.color  %>">
          <\%
          var severityId = rule.alarmSeverity.severityId;
          for(var j = 0,jSize = alarmSeveritys.length;j<jSize;j++){
          var alarmSeverity = alarmSeveritys[j];
          if(severityId == alarmSeverity.severityId){
          %>
          <option value="<\%=alarmSeverity.severityId  %>" class="<\%=alarmSeverity.color  %>" selected="selected"><\%= alarmSeverity.name %></option>
          <\%   }else{
          %>
          <option value="<\%=alarmSeverity.severityId  %>" class="<\%=alarmSeverity.color  %>"><\%= alarmSeverity.name %></option>
          <\%
          }
          }
          %>
          
        </select>
      </td>
      <td>
        <select name="isSuppress-rule<\%=rule.ruleId  %>" class="form-control">
          <\%
          if(parseInt(rule.isSuppress)){
          %>
          <option value="1" selected="selected">是</option>
          <option value="0">否</option>
          <\%
          }else{
          %>
          <option value="1">是</option>
          <option value="0" selected="selected">否</option>
          
          <\%
          }
          %>
        </select>
      </td>
      <td>
        <\%
        //遍历告警方式
        var noticeTypes = rule.alarmNoticeTypes;
        for(j = 0,jSize = alarmNoticeTypes.length;j<jSize;j++){
        var alarmNoticeType = alarmNoticeTypes[j],
        hasThisNoticeType=false;
        for(var k = 0,kSize=noticeTypes.length;k<kSize;k++){
        var noticeType = noticeTypes[k];
        if(noticeType.noticeTypeId == alarmNoticeType.noticeTypeId){
        hasThisNoticeType=true;
        break;
        }
        }
        if(hasThisNoticeType){
        %>
        <input type="checkbox" name="noticeType-rule<\%=rule.ruleId  %>"  checked="checked" value="<\%=alarmNoticeType.noticeTypeId %>"><\%=alarmNoticeType.name %></input>
        <\%
        }else{
        %>
        <input type="checkbox"  name="noticeType-rule<\%=rule.ruleId  %>"  value="<\%=alarmNoticeType.noticeTypeId  %>"><\%=alarmNoticeType.name  %></input>
        <\%
        }
        }
        %>
      </td>
      <td>
        <div class="input-group">
          <\%
          var receiversName = "";
          for(var j = 0,jSize = rule.tsusers.length;j<jSize;j++){
          receiversName += rule.tsusers[j].username.trim()+",";
          }
          receiversName = receiversName.length>0?receiversName.substring(0,receiversName.length-1):receiversName;
          %>
          <input type="text" class="form-control" value="<\%=receiversName  %>" name="user-rule<\%=rule.ruleId  %>" readonly></input>
          <div class="input-group-btn"><button id="btn-user-rule<\%=rule.ruleId  %>" class="btn btn-default" type="buton" data-toggle="modal" data-target="#generalModal">通知人</button></div>
        </div>
      </td>
      <td>
        <button id="btn-rule<\%=rule.ruleId  %>" class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
        
      </td>
    </tr>
    <\%
    }
    %>
    
    </script>
    <!-- 告警门限配置模板 -->
    <script type="text/x-ejs-template" id="alarmIndicatorsTmpl">
    <\% for(var i = 0,iSize=alarmIndicators.length;i<iSize;i++){
    var alarmIndicator = alarmIndicators[i];
    %>
    <tr>
      <td><\%=i+1  %></td>
      <td><\%=alarmIndicator.name  %></td>
      <td>
        <input type="text" id="indicator<\%=alarmIndicator.indicatorId  %>" value="" />
      </td>
      <td>
        <button id="btn-indicator<\%=alarmIndicator.indicatorId  %>" class="btn btn-default" type="button"><span class="fa fa-save"></span>保存</button>
      </td>
    </tr>
    <\%
    }
    %>
    </script>
    <!-- 用户模板 -->
    <script type="text/x-ejs-template" id="usersTmpl">
    <\% for(var i =0,size=users.length;i<size;i++){
    var user = users[i];
    if(i%6==0){
    %>
    <div class="row">
      <\%
      }
      
      var checked = "";
      for(var j = 0,jSize = receivers.length;j<jSize;j++){
      var receiver = receivers[j];
      if(receiver.id == user.id){
      checked='checked = "checked"';
      }
      }
      %>
      <div class="col-xs-2">
        <label class="normal"><input type="checkbox" <\%=checked  %> data-username="<\%= user.username.trim() %>" value="<\%=user.id  %>"><\%= user.username.trim() %></input></label>
      </div>
      <\%
      if((i+1)%6==0){
      %>
    </div>
    <\%
    }
    }
    %>
    </script>

<!-- loading模板 -->
    <script type="text/x-ejs-template" id="overlayTmpl">
      <div class="overlay">
              <i class="fa fa-refresh fa-spin"></i>
            </div>
    </script>
    <script src="<%=basePath  %>/node_modules/ejs/ejs.js"></script>
    <script src="<%=basePath  %>/js/alarm_config.js"></script>
  </body>
</html>