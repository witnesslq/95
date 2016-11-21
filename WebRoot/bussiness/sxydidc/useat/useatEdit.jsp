<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>U位信息编辑</title>
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
                    { label: "所属客户",name: "customerid",newline: true,type: "select",options:{disabled:true}},                 
                    { label: "所属机架",name: "rackid",newline: false,type: "select",options:{disabled:true}},
                    { label: "放置设备",name: "deviceid",newline: true,type: "select",
						editor: {						
							onBeforeOpen:function(){
								winOpen("bussiness/sxydidc/device/query.jsp","选择所属设备",null,560,400,'确定','取消',function(data){
									$.ligerui.get('deviceid').setValue(data.id);
									$.ligerui.get('deviceid').setText(data.name);
								});								
							}    									
						},                    
                    	validate: {required: true}
                    }, 
                    { label: "U位状态",name: "status",newline: false,type: "select",options:{disabled:true},
                         editor:{
                    		data:queryDictionary('USEATSTATUS',null),
                    		initValue:""
                    	}
                    },
                    { label: "U位序号",name: "no",newline: true,type: "text"}
                ]
            }); 
            getUSeatInfo();
            
        });
		
		/**供回调方法使用*/
		function f_validate(){ 
			return datePost();
		}
		
		/**获取表单要保存的数据以json格式返回*/
		function datePost(){
			var formData = form.getData();
			var data = {"useat.id":"<%=id %>",
						"useat.rackid":formData.rackid,
						"useat.deviceid":formData.deviceid,
						"useat.customerid":formData.customerid,
						"useat.status":formData.status,
						"useat.no":formData.no
			};						
			return data;		
		}
		
		/**表单赋值函数*/
		function getUSeatInfo(){
		  $.ajax({
			url:"queryUSeatById.action", 
			data:{"id":"<%=id%>"}, 
			async:false,
			dataType:"json", 
			type:"post",
			success:function (msg) {
		       liger.get("form").setData({
					rackid:msg.rackid,
					deviceid:msg.deviceid,
					customerid:msg.customerid,
					status:msg.status,
					no:msg.no	
				});
/*				$.ligerui.get("customerid").setText(msg.customerName);
				$.ligerui.get("rackid").setText(msg.rackName);
				$.ligerui.get("deviceid").setText(msg.deviceName);*/
				
				$.ligerui.get("customerid").setData([{id:msg.customerid,text:msg.customerName}]);
				$.ligerui.get("rackid").setData([{id:msg.rackid,text:msg.rackName}]);
				$.ligerui.get("deviceid").setData([{id:msg.deviceid,text:msg.deviceName}]);
			}, 
			error:function (error) {
				alert("获取U位信息失败" + error.status);
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
