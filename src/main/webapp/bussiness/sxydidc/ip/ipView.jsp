<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>IP信息展示</title>
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
				readonly:true,
                fields: [ 
               		{ label: "IP地址",name: "ipadd",newline: true,type: "text"},
               		{ label: "所属网段",name: "ipsegname",newline: false,type: "text"},
                    { label: "IP状态",name: "status",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('IPSTATUS',null),
                    		readonly:true,
                    		initValue:""
                    	}                     
                    },
                    { label: "所属数据中心",name: "dcname",newline: false,type: "text"},                                                            
                    { label: "IP排序",name: "sort",newline: false,type: "hidden"}, 
                    { label: "所属设备",name: "devicename",newline: true,type: "text"}, 
                    { label: "所属客户",name: "customername",newline: false,type: "text"}, 
                    { label: "描述", name: "remark", newline: true, width:520,type:"textarea"} 
                ]
            }); 
             getIPInfo();
            
        });
		
		/**供回调方法使用*/
		function f_validate(){ 

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
					ipsegname:msg.ipsegname,
					devicename:msg.devicename,
					customername:msg.customername,
					status:msg.status,
					sort:msg.sort,
					remark:msg.remark,
					dcname:msg.dcname		
				});
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
