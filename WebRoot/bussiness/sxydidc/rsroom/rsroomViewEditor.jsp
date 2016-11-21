<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.dhcc.bussiness.sxydidc.rack.RackModel"%>
<%@page import="com.dhcc.bussiness.sxydidc.rsroom.RoomStatuInfoModel"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<RackModel> allinfo=(List<RackModel>)request.getAttribute("allinfo");
List<RoomStatuInfoModel> StatuInfo=(List<RoomStatuInfoModel>)request.getAttribute("StatuInfo");
String roomid=(String)request.getAttribute("roomid");
String roomtype=(String)request.getAttribute("roomtype");
int Preemption=0;  //预占
int free=0;          //空闲
int sanRent=0;    //散租
int entireRent=0;  //整租
int unknown=0;      //未知

for(int i=0;i<StatuInfo.size();i++){
	 String no=StatuInfo.get(i).getStatus();
	 if(no.equals("01")){
	 free=StatuInfo.get(i).getNumber();
	 }else if(no.equals("02")){
	 sanRent=StatuInfo.get(i).getNumber();
	 }else if(no.equals("03")){
	 entireRent=StatuInfo.get(i).getNumber();
	 }else if(no.equals("04")){
	 Preemption=StatuInfo.get(i).getNumber();
	 }else{
	 unknown=unknown+StatuInfo.get(i).getNumber();
	 }
	 } 
	int alldevnumber=Preemption+free+sanRent+entireRent+unknown;                                                                     
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>机房信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
	
	<link    rel="stylesheet" type="text/css" href="<%=basePath %>include/LigerUI/skins/Aqua/css/ligerui-all.css"/>
    <link    rel="stylesheet" type="text/css"  href="<%=basePath %>include/LigerUI/skins/ligerui-icons.css" /> 
    <link    rel="stylesheet" type="text/css" href="<%=basePath %>css/rsroomViewEditor.css"/>
    
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
	<script  type="text/javascript"  src="<%=basePath  %>include/jQuery/jquery-1.9.1.js"></script>  

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
<script src="<%=basePath  %>/bussiness/sxydidc/js/common.js"></script>

	<script src="<%=basePath%>/include/LigerUI/json2.js"type="text/javascript"></script>
	<script src="<%=basePath%>/bussiness/sxydidc/rsroom/rsroomCheckout.js"type="text/javascript"></script>
    <script src="<%=basePath%>/bussiness/sxydidc/rsroom/rsroomRackDrag.js"type="text/javascript"></script>
 
	 </head>
  
  <body>
  <div id="top" class="roombtn"> 
  <button type="button" class="btn btn-default btn-sm"  onclick="newRack()">
              <span class="fa fa-user-plus"></span> 新建机架
            </button>
  <button type="button" class="btn btn-default btn-sm"  onclick="newSomeRack()">
              <span class="fa fa-user-plus"></span> 批量添加
            </button>
  <button type="button" class="btn btn-default btn-sm"  onclick="newPillar()">
              <span class="fa fa-user-plus"></span> 添加柱子
            </button>
  <button type="button" class="btn btn-default btn-sm"  id="saveAllInfo">
              <span class="fa fa-user-plus"></span> 保存位置
            </button>

  </div>
   <div id="main" >
   <%if(allinfo.size()>0){ 
      for(int i=0;i<allinfo.size();i++){
       String name=allinfo.get(i).getName();
       int xpostion=allinfo.get(i).getXposition();
       int ypostion=allinfo.get(i).getYposition();
	   int unumber=0;
	   int usednumber=0;
	   String no=allinfo.get(i).getStatus();
	   String status="未知";
	   String imageUrl="/images/rsroom/Pillar1.jpg";
	   
	   if(no.equals("01")){
	    imageUrl="/images/rsroom/U.png";
		status="空闲";
		}else if(no.equals("02")){
		imageUrl="/images/rsroom/SanRent1.jpg";
		status="散租";
		}else if(no.equals("03")){
		imageUrl="/images/rsroom/EntireRent1.jpg";
		status="整租";
		}else if(no.equals("04")){
		imageUrl="/images/rsroom/Preemption1.jpg";
		status="预占";
		}
		int totalU=0;//总U位数
		if(allinfo.get(i).getTotalU()!=null){totalU=allinfo.get(i).getTotalU();}
		int freeU=0;
		if(allinfo.get(i).getFreeU()!=null){freeU=allinfo.get(i).getFreeU();}//空闲U位数
		int disRentU=0;
		if(allinfo.get(i).getDisRentU()!=null){disRentU=allinfo.get(i).getDisRentU();}//实占U位数
		int whlRentU=0;
		if(allinfo.get(i).getWhlRentU()!=null){whlRentU=allinfo.get(i).getWhlRentU();}//使用中U位数
		int preU=0;
		if(allinfo.get(i).getPreU()!=null){preU=allinfo.get(i).getPreU();}//预占U位数
	
		if(allinfo.get(i).getUcount()!=null){
		unumber=allinfo.get(i).getUcount();
		}
		
		usednumber=disRentU+whlRentU+preU;
			
		String racktype="未知";
		if(allinfo.get(i).getUcount()!=null){
		racktype=allinfo.get(i).getUcount()+"U";
		}
		int UNumberLong=0;
		if(unumber!=0){
		 UNumberLong=usednumber*40/unumber;
		}
		String rackNo=allinfo.get(i).getCode();  //机架编号
		if(rackNo==null){
		rackNo="";
		}
		String rackName=allinfo.get(i).getName();   //机架名称
		if(rackName==null){
		rackName="";
		}
		String rackModel=allinfo.get(i).getTypeid();   //机架规格
		
		String rackstuts=status;//机架状态
		String belongCustomer=allinfo.get(i).getCustomerName();   //所属客户
		if(belongCustomer==null){
		belongCustomer="";
		}
		String belongRoom=allinfo.get(i).getRoomName();//所属机房

   %> 
   <div id="<%=allinfo.get(i).getId()%>" name="rackinfo" style="cursor:pointer;left:<%=xpostion%>px;top:<%=ypostion%>px;background-image: url(<%=basePath+imageUrl%>);" 
   <%if(!name.equals("柱子")&&!rackModel.equals("05")){%>
   title="机架名称:<%=name %>&#13;机架类型:<%=racktype %>&#13;U位数量:<%=allinfo.get(i).getUcount()==null?"":allinfo.get(i).getUcount()%>&#13;空闲U位:<%=allinfo.get(i).getFreeU()==null?"":allinfo.get(i).getFreeU() %>&#13;分配状态:<%=status %>&#13;所属客户:<%=belongCustomer%>"
  
    ondblclick="showinfo('<%=allinfo.get(i).getId()%>',<%=unumber %>,'<%=rackNo %>','<%=rackName %>','<%=rackModel %>','<%=rackstuts %>','<%=belongCustomer %>','<%=belongRoom %>','<%=totalU %>','<%=freeU %>','<%=disRentU %>','<%=whlRentU %>','<%=preU %>')  <%} %>"><%=name%>
   <span style="position: absolute;left:0px;top:33px; height:8px; width:<%=UNumberLong %>px;background:red"></span>
   </div>
   <%}} %>
   </div>
	  <div id="front_view">
	  <div id="show_unumber" style="position: absolute; left: 35px; top: 10px; height: 460px; width: 30px;">
	  <table id="unumber_info" >
	  	<tbody></tbody>
	  </table>
	  </div>
	  <div id="content"><img src="<%=basePath%>/images/rsroom/rackpanel.png" height="460px" width="170px"/>
	  <table id="show_u">
	  <tbody></tbody>
	  </table>
     </div>
      </div>

<div id="information">
   <table width="292"  border="0" cellspacing="0" cellpadding="0">
    <tr class="firstrow">
      <td width="30%"  ><b>属性</b></td>
      <td width="70%" ><b>值</b></td>
    </tr>
    <tr >
      <td>&nbsp;&nbsp;机架编号</td>
      <td id="rackNo">&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;&nbsp;机架名称</td>
      <td id="rackName">&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;&nbsp;机架规格</td>
      <td id="rackModel">&nbsp;</td>
    </tr>
     <tr>
      <td>&nbsp;&nbsp;机架状态</td>
      <td id="rackstuts">&nbsp;</td>
    </tr>
     <tr>
      <td>&nbsp;&nbsp;所属机房</td>
      <td id="belongRoom">&nbsp;</td>
    </tr>
     <tr>
      <td>&nbsp;&nbsp;所属客户</td>
      <td id="belongCustomer">&nbsp;</td>
    </tr>
     <tr>
      <td>&nbsp;&nbsp;总U位数</td>
      <td id="totalU">&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;&nbsp;空闲U位数</td>
      <td id="freeU">&nbsp;</td>
    </tr>
     <tr>
      <td>&nbsp;&nbsp;预占U位数</td>
      <td id="preU">&nbsp;</td>
    </tr>
     <tr>
      <td>&nbsp;&nbsp;实占U位数</td>
      <td id="disRentU">&nbsp;</td>
    </tr>
     <tr>
      <td>&nbsp;&nbsp;使用U位数</td>
      <td id="whlRentU">&nbsp;</td>
    </tr>
   
  </table>
</div>

<div style="position: absolute; top: 581px; left: 26px; width: 500px; height:22px">
  <div class="state"><img src="<%=basePath%>/images/rsroom/Free.jpg"/>空闲(<span id="free"><%=free %></span>)</div>
  <div class="state"><img src="<%=basePath%>/images/rsroom/SanRent.jpg"/>散租(<%=sanRent %>)</div>
  <div class="state"><img src="<%=basePath%>/images/rsroom/Preemption.jpg"/>预占(<%=Preemption %>)</div>
  <div class="state"><img src="<%=basePath%>/images/rsroom/EntireRent.jpg"/>整租(<%=entireRent %>)</div>
  <div class="state"><img src="<%=basePath%>/images/rsroom/Pillar.jpg"/>柱子(<span id="pillar"><%=unknown %></span>)</div>
</div>
<input type="hidden" id="markroomid" value="<%=roomid %>"/>
<input type="hidden" id="alldevnumber" value="<%=alldevnumber %>"/>
<input type="hidden" id="roomtype" value="<%=roomtype%>"/>

<!-- 模态框（Modal） -->
<!-- 添加-->
<div class="modal fade" name="myModal" id="adduser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><b>新增机架</b></h4>
            </div>
            <div class="modal-body" >
            <iframe src="<%=basePath  %>/bussiness/sxydidc/rack/rackAdd.jsp" class="smallModel" frameborder="0" scrolling="no" name="adduser_content"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" name="adduser">添加</button> 
                 <button type="button" class="btn btn-default" data-dismiss="modal">取消</button> 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 修改 -->
<div class="modal fade" name="myModal" id="editInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><b>修改机架信息</b></h4>
            </div>
            <div class="modal-body" >
            <iframe src="<%=basePath  %>/bussiness/sxydidc/rack/rackEdit.jsp" class="smallModel" frameborder="0" scrolling="no" name="edit_content"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" name="editInfo">确定</button> 
                 <button type="button" class="btn btn-default" data-dismiss="modal">取消</button> 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 批量添加-->
<div class="modal fade" name="myModal" id="addsome" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><b>批量添加机架</b></h4>
            </div>
            <div class="modal-body" >
            <iframe src="<%=basePath  %>/bussiness/sxydidc/rack/addSomeRack.jsp" class="small-amsllModel" frameborder="0" scrolling="no" name="addsome_content"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" name="addsome">添加</button> 
                 <button type="button" class="btn btn-default" data-dismiss="modal">取消</button> 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 服务器信息-->
<div class="modal fade" name="myModal" id="rsserverInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><b>服务器信息</b></h4>
            </div>
            <div class="modal-body" >
            <iframe  src="<%=basePath  %>/bussiness/sxydidc/rsroom/rserviceView.jsp"   class="add-user" frameborder="0" scrolling="no" name="rsserverInfo_content"></iframe>
            </div>
            <div class="modal-footer"> 
                 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 设备信息-->
<div class="modal fade" name="myModal" id="deviceInfoModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><b>设备信息</b></h4>
            </div>
            <div class="modal-body" >
            <iframe  src="<%=basePath  %>/bussiness/sxydidc/rsroom/deviceView.jsp"   class="middleModel" frameborder="0" scrolling="no" name="device_content"></iframe>
            </div>
            <div class="modal-footer"> 
                 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 添加柱子-->
<div class="modal fade" name="myModal" id="addPillar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><b>添加柱子</b></h4>
            </div>
            <div class="modal-body" >
				  <div class="form-group form-group-sm to-bottom">
					    <label for="startRow" class="col-xs-3 control-label nopadding font-size font-padding-size">
					添加位置
					    </label>
					    <div class="col-xs-9 nopadding">
					  	 <input type="text" class="form-control" id="rowNumber" placeholder="第几行">
					   </div>
				  </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" name="addPillar">添加</button> 
                 <button type="button" class="btn btn-default" data-dismiss="modal">取消</button> 
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
                <button type="button" class="btn btn-default" data-dismiss="modal" id="detleteinfo">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

  </body>
</html>
