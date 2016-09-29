<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userid=(String)request.getSession().getAttribute("userid");//用户id
String username=(String)request.getSession().getAttribute("username");//用户名
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>山西移动IDC运营管理平台</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link    rel="stylesheet" type="text/css" href="<%=basePath %>include/LigerUI/skins/Aqua/css/ligerui-all.css"  /> 
    <link    rel="stylesheet" type="text/css" href="<%=basePath %>css/index.css"/> 
    <script  type="text/javascript" src="<%=basePath  %>include/jQuery/jquery-1.3.2.min.js"></script>    
    <script  type="text/javascript"  src="<%=basePath  %>include/LigerUI/js/ligerui.all.js" ></script> 
    <script  type="text/javascript"  src="<%=basePath  %>js/clock.js" ></script>
    <script  type="text/javascript"  src="<%=basePath  %>js/index.js" ></script>
    <script type="text/javascript">
  	   var uid = "<%=userid %>";
       var tab = null;
       var accordion = null;
       $(function (){
           //布局
           $("#layout1").ligerLayout({ leftWidth: 190, height: '100%',heightDiff:-34,space:4, onHeightChanged: f_heightChanged });

           var height = $(".l-layout-center").height();

           //Tab
           $("#framecenter").ligerTab({ height: height });

           //面板
           $("#accordion1").ligerAccordion({ height: height - 24, speed: null });

           $(".l-link").hover(function (){
               $(this).addClass("l-link-over");
           }, function (){
               $(this).removeClass("l-link-over");
           });

           tab = $("#framecenter").ligerGetTabManager();
           accordion = $("#accordion1").ligerGetAccordionManager();
           tick();//时间
           remind();
           getTopMenu();
           $("#passform").ligerForm();
           
            /*获取当前年份*/
		    var today = new Date();
		    var intYear = today.getFullYear();
			$("#currYear").html(intYear);
       });
       function f_heightChanged(options){
           if (tab)
               tab.addHeight(options.diff);
           if (accordion && options.middleHeight - 24 > 0)
               accordion.setHeight(options.middleHeight - 24);
       }
       function f_addTab(tabid,text, url){ 
       	   if(text.indexOf("待办工单") >= 0){
       	  	 text = "待办工单";
       	   }
           tab.addTabItem({ tabid : tabid,text: text, url: url });
       } 
       
       function getSelectedTabId() { 
		    $tabIndex = tab.getSelectedTabItemID();
		    $("#tabid").val($tabIndex);
		    return $tabIndex;
		}
		
		function my_removeTab(tabid){
		   tab.removeTabItem(tabid);
		}
        
        /*
           弹出的提示框
          cont 要显示的内容
          type 类型
        */
       function my_alert(cont,type){
         $.ligerDialog.alert(cont,"提示",type);
       }
       var manager,manger1;
       function  my_openwindow(id,url,mwid,mhei,tit){
          getSelectedTabId();
          $("#diaid").val(id);
          manager=$.ligerDialog.open({name:id,url: url, width:mwid, height:mhei, modal: true, isResize: true ,title:tit,allowClose:true,isHidden:false});
       }
       
       function my_closewindow(){
          manager.close();
       }
       
       function  my_openwindow1(id,url,mwid,mhei,tit){
          getSelectedTabId();
          manager1=$.ligerDialog.open({name:id, url: url, width:mwid, height:mhei, modal: true, isResize: true ,title:tit,allowClose:true,isHidden:false});
       }
       
       function my_closewindow1(){
          manager1.close();
       }
       
       function  my_addData(data){
         var tempid = $("#tabid").val();
         document.getElementById(tempid).contentWindow.addData(data);
       }
       
        function  my_editData(data){
         var tempid = $("#tabid").val();
         document.getElementById(tempid).contentWindow.editData(data);
       }
       
       function  my_addData1(data){
         var tempid= $("#diaid").val();
         document.getElementById(tempid).contentWindow.addData(data);
       }
       
       function  my_editData1(data){
         var tempid= $("#diaid").val();
         document.getElementById(tempid).contentWindow.editData(data);
       }
       
       function  my_loadData(data){
         var tempid= $("#diaid").val();
         document.getElementById(tempid).contentWindow.loadDatas(data);
       }
        function  my_loadData1(){
          var tempid=  $("#tabid").val();
         document.getElementById(tempid).contentWindow.loadDatas();
       }
       
       /*加载左侧菜单*/
       function reloadTreeMenu(){
      	 liger.get("treeMenu").reload();
       }
        
</script> 
</head>
<body style="padding:0px;background:#EAEEF5;">  
	<div id="pageloading"></div>
	<div id="topmenu" class="l-topmenu">
	    <div class="l-topmenu-welcome">
	        <a href="" class="l-link2" onclick="return modifyPassword();">修改密码</a>
	        <span class="space">|</span>
	        <a href="" class="l-link2" onclick="return openUserMess();">用户信息</a> 
	        <span class="space">|</span>
	        <a href="login.jsp" class="l-link2" onclick="return userSignOut();">注销&关闭</a>
	    </div> 
	    <div class="l-topmenu-welcome-logo">
	   <label id="labelwelcome"></label> <%=username %> &nbsp;&nbsp;&nbsp;
	   <label id="my_clock"></label>
	    </div>
	</div>
    <div style="margin: 0; padding: 0; background: url(images/headbg.gif); height: 28px; overflow: hidden; border-bottom: 1px solid #8db2e3; width: 100%;">
         <div id="toolbar" style="height: 27px;line-height:27px; width: 100%; float: left; margin-top: 1px;padding-left:10px;padding-top: 2px"></div>
    </div>
	<div id="layout1" style="width:99.2%; margin:0 auto; margin-top:4px; "> 
	    <div position="left"  title="功能列表" id="accordion1"> 
	      <ul id="treeMenu" style="margin-top:3px;">
	    </div>
	    <div position="center" id="framecenter"> 
	        <div tabid="home" title="系统门户" style="height:300px" >
	            <iframe frameborder="0" name="home" id="home" src="welcome.jsp"></iframe>
	        </div> 
	    </div> 
	    
	</div>
	<div  style="height:32px; line-height:32px; text-align:center;">
	         Copyright © 1999-<label id="currYear"></label> 中国移动 All Rights Reserved
	</div>
<div style="display:none"></div>
<input type="hidden" id="tabid"/>
<input type="hidden" id="diaid"/>
<div id="XGMM" class="dialogCtrlPanl" title="修改密码框" style="display:none" align="center">
	<form action="" id="passform">
		<table class="ctrlPanlGrid">
			<tr>
			<td align="right" style="height:27px;padding:4px"><font size="2">原密码：</font></td>
			<td style="height:27px;padding:4px"><input type="password" id="oldpass"/></td>
			</tr>
			<tr>
			<td align="right" style="height:27px;padding:4px"><font size="2">新密码：</font></td>
			<td style="height:27px;padding:4px"><input type="password" id="newpass"/></td>
			</tr>
			<tr>
			<td align="right" style="height:27px;padding:4px"><font size="2">确认密码：</font></td>
			<td style="height:27px;padding:4px"><input type="password" id="newpass1"/></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
