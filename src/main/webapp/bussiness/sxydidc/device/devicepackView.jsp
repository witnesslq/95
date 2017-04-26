<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>运维设备信息列表页面</title>
	<jsp:include page="../common/head.jsp" flush="true"/> 
    <script type="text/javascript">
        var form;
        var navtab;
        var groupicon="<%=basePath%>include/LigerUI/skins/icons/communication.gif";
        $(function (){
        	form = $("#form").ligerForm({
        		inputWidth: 180, 
        		labelWidth: 120, 
        		space: 40, 
				validate : true,
				readonly:true,
                fields: [ 
					{ name: "deviceid",type:"hidden"},                
               		{ label: "板卡编码",name: "code",newline: true,type: "text",group: "板卡信息", groupicon: groupicon},
                    { label: "板卡序列号",name: "sn",newline: false,type: "text",validate: {required: true}},
                    
                    { label: "端口类型",name: "porttype",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('PORTTYPE',null),
                    		readonly:true, 
                    		initValue:""
                    	}
                    },
                    { label: "端口数量",name: "portcount",newline: false,type: "text",validate: {required: true}},
                    { label: "板卡行数",name: "rowcount",newline: true,type: "text",validate: {required: true}},
                    { label: "每行端口数",name: "perrow",newline: false,type: "text",validate: {required: true}},
                    { label: "所在插槽编号",name: "slotno",newline: true,type: "text",validate: {required: true}},
                    { label: "板卡序号",name: "packno",newline: false,type: "text",validate: {required: true}}
                ]
            }); 
            
            getBoardInfo();
            $("#tab").ligerTab({
				showSwitch: true,
                ShowSwitchInTab: true            
            });
            navtab = $("#tab").ligerGetTabManager();            
        });
		
		/**供回调方法使用*/
		function f_validate(){ 

		}
		
		/**表单赋值函数*/
		function getBoardInfo(){
		  $.ajax({
			url:"queryDevicePackById.action", 
			data:{"id":"<%=id%>"}, 
			async:false,
			dataType:"json", 
			type:"post",
			success:function (msg) {
		       liger.get("form").setData({
		       		deviceid:msg.deviceid,
					code:msg.code,
					sn:msg.sn,
					portcount:msg.portcount,
					porttype:msg.porttype,
					rowcount:msg.rowcount,
					perrow:msg.perrow,
					slotno:msg.slotno,
					packno:msg.packno					
				});
			}, 
			error:function (error) {
				alert("获取设备板卡信息失败" + error.status);
			}
		  });
		}		
    </script>    
  	
</head>
<body style="padding:10px">  
    <form id="form"></form>
    <div id="tab" style="width: 100%;overflow:hidden; border:1px solid #A3C0E8; "> 
       <div tabid="home" title="板卡端口信息列表" style="height:300px">
       		<iframe frameborder="0" src="<%=basePath%>bussiness/sxydidc/device/deviceportList.jsp?packid=<%=id%>"></iframe>
       </div>            
     </div>    
</body>
</html>
