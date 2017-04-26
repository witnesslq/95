<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>客户类型选择</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script type="text/javascript">
        $(function (){
           var custTypeData = [{id:'03', text:'互联网客户'}];
        	form = $("#form").ligerForm({ 
				inputWidth: 140, 
				labelWidth: 90, 
				space: 40,
                fields: [ 
               		{ label: "客户类型",name: "custType",newline: true,type: "select",
						editor: { 
							data:custTypeData,
							initValue:"03",
							onSelected:function(id,value){
								$("input[name='custType']").val(id);
								getCustType();
							}			
						}
               		}
                ]
            });                         				 
        });
        
        function getCustType(){
        	return $("input[name='custType']").val(); 
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
