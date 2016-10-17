<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="com.dhcc.common.util.CreateNum"%>
<%@page import="com.dhcc.bussiness.sxydidc.datacenter.DataCenterModel"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	DataCenterModel dc=(DataCenterModel)request.getSession(true).getAttribute("dc");
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
                	{ label: "设备编码",name: "code",newline: true,type: "text",validate: {required: true},options:{disabled:true}},
               		{ label: "设备名称",name: "name",newline: false,type: "text",validate: {required: true}},
               		{ label: "设备型号",name: "moduletype",newline: true,type: "text",validate: {required: true}},
                    { label: "所属机房",name: "roomid",newline: true,type: "select",
						editor: {						
							onBeforeOpen:function(){
								winOpen("bussiness/sxydidc/rsroom/query.jsp","选择所属机房",null,560,400,'确定','取消',function(data){
									$.ligerui.get('roomid').setValue(data.id);
									$.ligerui.get('roomid').setText(data.roomname);
								});								
							}    									
						},           	
                    	validate: {required: true}                     
                    },
                    { label: "所属机架",name: "rackid",newline: false,type: "select",
						editor: {						
							onBeforeOpen:function(){
								var parms=[{name:'rack.roomid',value:$.ligerui.get('roomid').getValue()},{name:'rack.needFilter',value:true}];
								winOpen("bussiness/sxydidc/rack/query.jsp","选择所属机架",parms,560,400,'确定','取消',function(data){
									$.ligerui.get('rackid').setValue(data.id);
									$.ligerui.get('rackid').setText(data.name);
								});								
							}    									
						},                    	
                    	validate: {required: true}
                    },                                        
					{ label: "起始U位",name: "startu",newline: true,type: "select",
						editor: {						
							onBeforeOpen:function(){
								var parms=[{name:'useat.rackid',value:$.ligerui.get('rackid').getValue()},{name:'useat.needFilter',value:true}];
								winOpen("bussiness/sxydidc/useat/query.jsp","选择起始U位",parms,560,400,'确定','取消',function(data){
									$.ligerui.get('startu').setValue(data.no);
									$.ligerui.get('startu').setText(data.no);
								});								
							}    									
						}					
					},
					{ label: "占用U位数量",name: "ucount",newline: false,type: "text"},
					{ label: "设备IP地址",name: "ipid",newline: true,type: "select",
						editor: {						
							onBeforeOpen:function(){
								var parms=[{name:'ip.status',value:'01'}];							
								winOpen("bussiness/sxydidc/ip/query.jsp","选择IP地址",parms,560,400,'确定','取消',function(data){
									$.ligerui.get('ipid').setValue(data.id);
									$.ligerui.get('ipid').setText(data.ipadd);
								});								
							}    									
						}					
					},
                    { label: "所属数据中心",name: "dcname",newline: false,type: "text",
                   		editor:{
                    		value:"<%=dc!=null?dc.getName():""%>"
                    	},
                    	options: {disabled: true}                    
                    },					
                    { label: "状态",name: "status",newline: false,type: "hidden",options:{disabled:true},
                         editor:{
                    		data:queryDictionary('IPSTATUS',null),
                    		initValue:"01"
                    	}                    
                    },
                    { label: "描述", name: "remark", newline: true, width:520,type:"textarea"} 
                ]
            }); 
	        initCode();   
        });
        
        function initCode(){
			$("input[name='code']").val(createCode('DEVCODE'));
		}
		
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
			var data = {"device.name":formData.name,
			            "device.code":formData.code, 
			            "device.moduletype":formData.moduletype,
						"device.status":'01',
						"device.roomid":formData.roomid,
						"device.startu":formData.startu,
						"device.ucount":formData.ucount,
						"device.rackid":formData.rackid,
						"device.ipid":formData.ipid,
						"device.remark":formData.remark
						
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
