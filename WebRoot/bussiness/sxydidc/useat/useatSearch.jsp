<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>U位信息查询</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script type="text/javascript">
        var form; 
        $(function (){
        	form = $("#form").ligerForm({
        		inputWidth: 180, 
        		labelWidth: 130, 
        		space: 40, 
                fields: [
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
                    { label: "所属机房",name: "roomid",newline: false,type: "select",
						editor: {						
							onBeforeOpen:function(){
								winOpen("bussiness/sxydidc/rsroom/query.jsp","选择所属机房",null,560,400,'确定','取消',function(data){
									$.ligerui.get('roomid').setValue(data.id);
									$.ligerui.get('roomid').setText(data.roomname);
								});								
							}    									
						}         
                    },                                      
                    { label: "所属机架",name: "rackid",newline: true,type: "select",
						editor: {						
							onBeforeOpen:function(){
								var parms=[{name:'rack.roomid',value:$.ligerui.get('roomid').getValue()},{name:'rack.needFilter',value:false}];
								winOpen("bussiness/sxydidc/rack/query.jsp","选择所属机架",parms,560,400,'确定','取消',function(data){
									$.ligerui.get('rackid').setValue(data.id);
									$.ligerui.get('rackid').setText(data.name);
								});								
							}    									
						}
                    },
                    { label: "放置设备",name: "deviceid",newline: false,type: "select",
						editor: {						
							onBeforeOpen:function(){
								winOpen("bussiness/sxydidc/device/query.jsp","选择所属设备",null,560,400,'确定','取消',function(data){
									$.ligerui.get('deviceid').setValue(data.id);
									$.ligerui.get('deviceid').setText(data.name);
								});								
							}    									
						}                  
                    },
                     
                    { label: "U位状态",name: "status",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('USEATSTATUS',null),
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
