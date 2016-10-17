<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>IP信息编辑</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script type="text/javascript">
        var form; 
        var groupicon="<%=basePath%>include/LigerUI/skins/icons/communication.gif";
        $(function (){
        	form = $("#form").ligerForm({
        		inputWidth: 180, 
        		labelWidth: 120, 
        		space: 40, 
				validate : true,
                fields: [ 
               		{ label: "IP地址",name: "ipadd",newline: true,type: "text",validate: {required: true}},
                    { label: "所属网段",name: "ipsegid",newline: false,type: "select",options:{disabled:true},validate:{required: true}},               		
                    { label: "IP状态",name: "status",newline: true,type: "select",options:{disabled:true},
                         editor:{
                    		data:queryDictionary('IPSTATUS',null),
                    		initValue:"01"
                    	},
                    	validate: {required: true}                     
                    },
					{ label: "IP排序",name: "sort",newline: false,type: "text"}, 
                    { label: "所属设备",name: "deviceid",newline: true,type: "select",options:{disabled:true},validate: {required: true}},
                    { label: "所属客户",name: "customerid",newline: false,type: "select",options:{disabled:true},validate: {required: true}},                      
                    { label: "描述", name: "remark", newline: true, width:520,type:"textarea"}                                      
                ]
            }); 
            getIPInfo();
            
        });
		
		/**供回调方法使用*/
		function f_validate(){ 
			if(form.valid()){
				return datePost();
			}else{
			    form.showInvalid();
			}
		}
		
		/**获取表单要保存的数据以json格式返回*/
		function datePost(){
			var formData = form.getData();
			var data = {"ip.id":"<%=id %>",
						"ip.ipadd":formData.ipadd,
						"ip.status":formData.status,
						"ip.ipsegid":formData.ipsegid,
						"ip.customerid":formData.customerid,
						"ip.serverid":formData.serverid,
						"ip.deviceid":formData.deviceid,
						"ip.sort":formData.sort,
						"ip.remark":formData.remark
			};			
			return data;		
		}
		
		/**表单赋值函数*/
		function getIPInfo(){
		  $.ajax({
			url:"queryIPById.action", 
			data:{"id":"<%=id%>"}, 
			async:false,
			dataType:"json", 
			type:"post",
			success:function (msg) {
		       liger.get("form").setData({
					ipadd:msg.ipadd,
					status:msg.status,
					ipsegid:msg.ipsegid,
					customerid:msg.customerid,
					serverid:msg.serverid,
					deviceid:msg.deviceid,
					sort:msg.sort,
					remark:msg.remark		
				});
				$.ligerui.get("ipsegid").setData([{id:msg.ipsegid,text:msg.ipsegname}]);
				$.ligerui.get("deviceid").setData([{id:msg.deviceid,text:msg.devicename}]);
				$.ligerui.get("customerid").setData([{id:msg.customerid,text:msg.customername}]);
			}, 
			error:function (error) {
				alert("获取IP信息失败" + error.status);
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
