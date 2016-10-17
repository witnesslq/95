<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
String type = request.getParameter("type");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>设备信息编辑</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script type="text/javascript">
        var form; 
        $(function (){
        	form = $("#form").ligerForm({
        		inputWidth: 180, 
        		labelWidth: 120, 
        		space: 40, 
				validate : true,
				readonly:true,
				fields: [
					{ label: "设备编码",name: "code",newline: true,type: "text",validate: {required: true}}, 
               		{ label: "设备名称",name: "name",newline: false,type: "text",validate: {required: true}},
               		{ label: "设备型号",name: "moduletype",newline: true,type: "text",validate: {required: true}},
               		{ label: "所属数据中心",name: "dcname",newline: false,type: "text"}, 
					{ label: "起始U位",name: "startu",newline: true,type: "text"},
					{ label: "U位数",name: "ucount",newline: false,type: "text"},
                    { label: "所属机房",name: "roomid",newline: true,type: "popupedit",
						editor: {
                			condition: {
                   			 	prefixID: 'condtion_',
                    			fields: [{ name: 'roomname',type:'text', label: '机房名称' }]
                			},
                			grid: queryRoomData(''),
                			valueField: 'id',
                			textField: 'roomname',
                			width: 600,
                			readonly:true,
                			onButtonClick:function(){
                				
                			}       									
						},
                    	validate: {required: true}                                      
                    },
                    { label: "所属机架",name: "rackid",newline: false,type: "popupedit",
						editor: {
                			condition: {
                   			 	prefixID: 'condtion_',
                    			fields: [{ name: 'name',type:'text', label: '机柜名称' }]
                			},
                			grid: queryRackData(''),
                			valueField: 'id',
                			textField: 'name',
                			width: 600,
                			readonly:true,
                			onButtonClick:function(){
                				
                			}       									
						},
                    	validate: {required: true}                  
                    },
                    { label: "IP地址",name: "ipid",newline: true,type: "text"},
                    { label: "描述", name: "remark", newline: true, width:520,type:"textarea"}

                ]
            }); 
            getDeviceInfo();
            
        });
		
		/**供回调方法使用*/
		function f_validate(){ 
			
		}
		
		/**表单赋值函数*/
		function getDeviceInfo(){
		  $.ajax({
			url:"queryDeviceById.action", 
			data:{"id":"<%=id%>"}, 
			async:false,
			dataType:"json", 
			type:"post",
			success:function (msg) {
		       liger.get("form").setData({
					name:msg.name,
					code:msg.code,
					moduletype:msg.moduletype,
					roomid:msg.roomid,
					startu:msg.startu,
					ucount:msg.ucount,					
					rackid:msg.rackid,
					ipid:msg.batchIpAdd,
					remark:msg.remark,
					dcname:msg.dcname
				});
				$.ligerui.get("roomid").setText(msg.roomname);
				$.ligerui.get("rackid").setText(msg.rackname);
				$("input[name=ipid]").attr('title',msg.batchIpAdd);
			}, 
			error:function (error) {
				alert("获取设备信息失败" + error.status);
			}
		  });
		}
		
    </script>
    <style type="text/css">
        html,body{ 
	        margin:0;
	        padding:0;
        	font-size:14px;
        }
    </style>
</head>
<body style="padding:10px">   
	<form id="form"></form> 
</body>
</html>
