<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>端口信息查询</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script type="text/javascript">
        var form; 
        $(function (){
        	form = $("#form").ligerForm({
        		inputWidth: 180, 
        		labelWidth: 120, 
        		space: 40, 
				validate : true,
                fields: [ 
               		{ label: "端口名称",name: "portname",newline: true,type: "text"},
                    { label: "端口编码",name: "portcode",newline: false,type: "text"},
                    { label: "端口类型",name: "type",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('PORTTYPE',null),
                    		initValue:""
                    	}
                    },                    
                    { label: "状态",name: "status",newline: false,type: "select",
                         editor:{
                    		data:queryDictionary('PORTSTATUS',null),
                    		initValue:""
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
                    { label: "所属网络设备",name: "tonetdevid",newline: false,type: "select",
/*						editor: {
                			condition: {
                   			 	prefixID: 'condtion_',
                    			fields: [{ name: 'name',type:'text', label: '名称' }]
                			},
                			grid: queryDeviceData([{name:"device.devicetype",value:'01'}]),
                			valueField: 'id',
                			textField: 'name',
                			width: 600       									
						}*/
						editor: {						
							onBeforeOpen:function(){
								var parms=[{name:"device.devicetype",value:'01'}];
								winOpen("bussiness/sxydidc/device/query.jsp","选择所属设备",parms,560,400,'确定','取消',function(data){
									$.ligerui.get('tonetdevid').setValue(data.id);
									$.ligerui.get('tonetdevid').setText(data.name);
								});								
							}    									
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
