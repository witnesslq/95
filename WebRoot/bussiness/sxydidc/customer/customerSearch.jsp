<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>客户信息查询</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script type="text/javascript">
        var form; 
        $(function (){
        	form = $("#form").ligerForm({
        		inputWidth: 180, 
        		labelWidth: 130, 
        		space: 40, 
                fields: [ 
               		{ label: "客户编号",name: "no",newline: true},
                    { label: "客户类型",name: "type",newline: false,type: "select",
                        editor:{
                    		data:queryDictionary('CUSTOMERTYPE',null),
                    		initValue:""
                    	}
                    },
                    { label: "客户级别",name: "customerlevel",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('CUSTOMERLEVEL',null),
                    		initValue:""
                    	}                   
                    }, 
                    { label: "客户状态",name: "status",newline: false,type: "select",
                         editor:{
                    		data:queryDictionary('CUSTSTATUS',null),
                    		initValue:""
                    	}                   
                    },
                    { label: "客户性质",name: "customerproperty",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('CUSTOMERPROPERTY',null),
                    		initValue:""
                    	}                    
                    },                    
                    { label: "客户域级",name: "customerfield",newline: false,type: "select",
                         editor:{
                    		data:queryDictionary('CUSTFIELD',null),
                    		initValue:""
                    	}                   
                    },                                                      		
                    { label: "客户名称",name: "name",newline: true, type: "text"},
                    { label: "客户简称",name: "sortname",newline: false,type: "text"},
                    { label: "是否分配客户经理",name: "manager",newline: true,type: "select",
                         editor:{
                    		data:[{id:"",text:"请选择"},{id:"01",text:"是"},{id:"02",text:"否"}],
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
