<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>"/>
    <title>IP段信息查询</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script type="text/javascript">
        var form; 
        $(function (){
        	form = $("#form").ligerForm({
        		inputWidth: 180, 
        		labelWidth: 130, 
        		space: 40, 
                fields: [ 
               		{ label: "IP段名称",name: "name",newline: true,type: "text"},
               		{ label: "起始IP",name: "startip",newline: true,type: "text"},
               		{ label: "终止IP",name: "endip",newline: false,type: "text"},
               		{ label: "子网掩码",name: "netmask",newline: true,type: "text"},
               		{ label: "网关IP",name: "gatewayip",newline: false,type: "text"},
                    { label: "IP段状态",name: "status",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('IPSEGSTATUS',null),
                    		initValue:""
                    	}                     
                    },
                    { label: "VLAN编号",name: "vlanno",newline: false,type: "text"},
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
                    { label: "所属单位或部门",name: "areaid",newline: false,type: "select",
						editor: {
							width : 180, 
							selectBoxWidth: 190,
							selectBoxHeight: 190, 
							valueField: 'id',
							treeLeafOnly: false,
							tree: { 
								url:"cropDeptTreeQuery.action", 
								ajaxType:'post',
								idFieldName: 'id',
								parentIDFieldName: 'pid',
								checkbox: false
							}
						}                   
                    },                                   		
               		
					{ label: "首先DNS",name: "dns1",newline: true,type: "text"},
					{ label: "备选DNS",name: "dns2",newline: false,type: "text"}
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
