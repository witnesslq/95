<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>端口信息展示</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script type="text/javascript">
        var form; 
        $(function (){
        	form = $("#form").ligerForm({
        		inputWidth: 180, 
        		labelWidth: 120, 
        		space: 40, 
        		readonly:true,
				validate : true,
                fields: [ 
                    { label: "端口编码",name: "portcode",newline: true,type: "text",validate: {required: true}},
                    { label: "端口名称",name: "portname",newline: false,type: "text",validate: {required: true}},
                    { name: "netdevpackid",type: "hidden"},
                    { label: "端口类型",name: "type",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('PORTTYPE',null),
                    		readonly:true,
                    		initValue:""
                    	},
                    	validate: {required: true}
                    },                    
                    { label: "速率(M)",name: "confrate",newline: false,type: "text"},
                    
                    { label: "状态",name: "status",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('IPSTATUS',null),
                    		readonly:true,
                    		initValue:""
                    	},
                    	validate: {required: true}
                    },
                    { label: "端口序号",name: "no",newline: false,type: "text"},  
                    { label: "所属设备",name: "devicename",newline: true,type: "text",validate: {required: true}},                    
                    { label: "所属客户",name: "customerid",newline: false,type: "popupedit",
						editor: {
                			condition: {
                   			 	prefixID: 'condtion_',
                    			fields: [{ name: 'name',type:'text', label: '客户名称' }]
                			},
                			grid: queryCustomerData(''),
                			valueField: 'id',
                			textField: 'name',
                			width: 600,
                			readonly:true,
                			onButtonClick:function(){
                				
                			}       									
						},
                    	validate: {required: true}                     
                    },
                    { label: "MAC地址",name: "macaddr",newline: true,type: "text"},
                    { label: "IP地址",name: "ipaddr",newline: false,type: "text"},
                    { label: "对口服务器",name: "toserverid",newline: true,type: "text"},
                    { label: "对口网络设备",name: "tonetdevid",newline: false,type: "text"},
                    { label: "端口行号",name: "rowno",newline: true,type: "text"}, 
                    { label: "端口列号",name: "colno",newline: false,type: "text"},
                    { label: "描述", name: "description", newline: true, width:520,type:"textarea"}
                ]
            }); 
            getPortInfo();
            
        });
		
		/**供回调方法使用*/
		function f_validate(){ 
		}
		
		/**表单赋值函数*/
		function getPortInfo(){
		  $.ajax({
			url:"queryPortById.action", 
			data:{"id":"<%=id%>"}, 
			async:false,
			dataType:"json", 
			type:"post",
			success:function (msg) {
		       liger.get("form").setData({
					portname:msg.portname,
					portcode:msg.portcode,
					netdevpackid:msg.netdevpackid,
					type:msg.type,
					confrate:msg.confrate,
					status:msg.status,
					devicename:msg.devicename,
					customerid:msg.customerid,
					macaddr:msg.macaddr,
					ipaddr:msg.ipaddr,
					toserverid:msg.toservername,
					tonetdevid:msg.tonetdevname,
					description:msg.description,
					no:msg.no,
					rowno:msg.rowno,
					colno:msg.colno		
				});
				$.ligerui.get('customerid').setText(msg.customername);
				
			}, 
			error:function (error) {
				alert("获取端口信息失败" + error.status);
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
