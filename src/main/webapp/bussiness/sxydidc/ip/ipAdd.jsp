<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>IP信息添加</title>
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
               		{ label: "IP地址",name: "ipadd",newline: true,type: "text",validate: {required: true}},
                    { label: "IP状态",name: "status",newline: false,type: "select",
                         editor:{
                    		data:queryDictionary('IPSTATUS',null),
                    		initValue:"01"
                    	},
                    	validate: {required: true}                     
                    },
                    { label: "所属IP段",name: "ipsegid",newline: true,type: "select",
						editor: {						
							onBeforeOpen:function(){
								winOpen("bussiness/sxydidc/ipseg/query.jsp","选择所属IP段",null,560,400,'确定','取消',function(data){
									$.ligerui.get('ipsegid').setValue(data.id);
									$.ligerui.get('ipsegid').setText(data.name);
								});								
							}    									
						},
                    	validate: {required: true}                     
                    },
/*                    { label: "所属客户",name: "customerid",newline: true,type: "popupedit",
						editor: {
                			condition: {
                   			 	prefixID: 'condtion_',
                    			fields: [{ name: 'name',type:'text', label: '客户名称' }]
                			},
                			grid: queryCustomerData(''),
                			valueField: 'id',
                			textField: 'name',
                			width: 600,
                			onButtonClick:function(){
                				
                			}       									
						},
                    	validate: {required: true}                     
                    }, */                                                           
                    { label: "所属设备",name: "deviceid",newline: false,type: "select",
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
                    { label: "IP排序",name: "sort",newline: true,type: "text"},                      
                    { label: "描述", name: "remark", newline: true, width:520,type:"textarea"}                                      
                ]
            });             
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
			var data = {"ip.ipadd":formData.ipadd,
						"ip.status":formData.status,
						"ip.ipsegid":formData.ipsegid,
						//"ip.customerid":formData.customerid,
						"ip.deviceid":formData.deviceid,
						"ip.sort":formData.sort,
						"ip.remark":formData.remark
			};
			return data;
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
