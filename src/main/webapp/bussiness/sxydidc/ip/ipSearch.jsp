<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>IP信息查询</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script type="text/javascript">
        var form; 
        $(function (){
        	form = $("#form").ligerForm({
        		inputWidth: 180, 
        		labelWidth: 130, 
        		space: 40, 
                fields: [ 
               		{ label: "IP地址",name: "ipadd",newline: true,type: "hidden"},
                    { label: "所属IP段",name: "ipsegid",newline: true,type: "select",
						editor: {						
							onBeforeOpen:function(){
								winOpen("bussiness/sxydidc/ipseg/query.jsp","选择所属IP段",null,560,400,'确定','取消',function(data){
									$.ligerui.get('ipsegid').setValue(data.id);
									$.ligerui.get('ipsegid').setText(data.name);
								});								
							}    									
						}                     
                    },
                    { label: "所属设备",name: "deviceid",newline: false,type: "select",
						editor: {						
							onBeforeOpen:function(){
								winOpen("bussiness/sxydidc/device/query.jsp","选择所属设备",null,560,400,'确定','取消',function(data){
									$.ligerui.get('deviceid').setValue(data.id);
									$.ligerui.get('deviceid').setText(data.name);
								});								
							}    									
						}                     
                    },                    
                    { label: "所属客户",name: "customerid",newline: true,type: "select",
						editor: {						
							onBeforeOpen:function(){
								winOpen("bussiness/sxydidc/customer/query.jsp","选择所属客户",null,560,400,'确定','取消',function(data){
									$.ligerui.get('customerid').setValue(data.id);
									$.ligerui.get('customerid').setText(data.name);
								});								
							}    									
						}                   
                    },
                    { label: "IP状态",name: "status",newline: false,type: "select",
                         editor:{
                    		data:queryDictionary('IPSTATUS',null),
                    		initValue:""
                    	}                     
                    }
                ]
            }); 
        });
		
		/**供回调方法使用*/
		function f_validate(){ 
			return datePost();
		}
		
		/**获取表单要保存的数据以json格式返回*/
		function datePost(){
			var formData = form.getData();		
			return formData;
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
