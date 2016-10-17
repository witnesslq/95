<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="com.dhcc.common.util.CreateNum;"%>
<%
String path = request.getContextPath();
String id = request.getParameter("netdevpackId");
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>设备信息添加</title>
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
                { label: "ID",name: "netdevpackid",newline: true,type: "text",hidden:'true'},
               		{ label: "端口编码",name: "portcode",newline: true,type: "text",
               		editor:{
                    		onMouseOver: function(){
								$("input[name='portcode']").ligerTip({
                            		content:"<div><span>点击<a href='javascript:createportDeviceNo();'>这里</a>重新生成设备编号。</span><span><a href='javascript:hideTip();'>关闭</a></span></div>",
                            		width:120
          						}); 
                    		}               		
                    	}},
                    	{ label: "端口类型",name: "type",newline: false,type: "select",
                         editor:{
                    		data:queryDictionary('OWNER',null),
                    		initValue:""
                    	}                     
                    },
                    	{ label: "端口数量",name: "no",newline: true,type: "text",hidden:'true'}
                                            
                                
                    
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
			var aaaa=$("#netdevpackId").val();
			var data = {"portdevice.portcode":formData.portcode,"portdevice.type":formData.type,"portdevice.no":formData.no,"portdevice.netdevpackid":aaaa};
			return data;
		}
		
        function createportDeviceNo(){
       		$.ajax({
				url:"createportDeviceNo.action", 
				dataType:"json", 
				type:"post",
				success:function (msg) {
					top.$.ligerDialog.success("获取板卡编号成功!");
					$("input[name='portcode']").val(msg.portcode);
				}, 
				error:function (error) {
					top.$.ligerDialog.error("获取板卡编号失败!" + error.status,"错误");
				}
			});		
		}
		
		function hideTip(){
			$("input[name='portcode']").ligerHideTip();
		};	
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
	<input id="netdevpackId" type="hidden" value="<%=id%>"/>
</body>
</html>
