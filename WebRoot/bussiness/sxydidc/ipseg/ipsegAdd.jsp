<%@ page import="com.dhcc.bussiness.sxydidc.datacenter.DataCenterModel" language="java"  pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	DataCenterModel dc=(DataCenterModel)request.getSession(true).getAttribute("dc");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>"/>
    <title>IP段信息添加</title>
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
                	{ label: "起始IP",name: "startip",newline: true,type: "text"},
                	{ label: "IP数量",name: "count",newline: false,type: "text"},
               		{ label: "终止IP",name: "endip",newline: true,type: "text",
               			editor:{
               				onFocus:function(){
               					var startip=$("input[name='startip']").val();
               					var count=$("input[name='count']").val();
               					var array=new Array();
               					if(startip!=null&&startip!=''&&count!=null&&count!=''){
               						array=startip.split(".");
               						var lastip;
               						if(count=='0'){
            							lastip=parseInt(array[3]);
            						}else{
            							lastip=parseInt(array[3])+parseInt(count)-1;
            						}
               						if(lastip>255){
               							$("input[name='endip']").val('');
          								$("input[name='endip']").attr("title","IP地址错误.");
               						}else{
               							var endip=array[0]+"."+array[1]+"."+array[2]+"."+lastip.toString();
               							$("input[name='endip']").val(endip);
               						}               					
               					}

               				}                			
               			}
               		},                	
               		{ label: "IP段名称",name: "name",newline: false,type: "text",
               			editor:{
               				onFocus:function(){
               					var startip=$("input[name='startip']").val();
               					var endip=$("input[name='endip']").val();
               					var array=new Array();
               					if(startip!=null&&startip!=''&&endip!=null&&endip!=''){
               						array=endip.split(".");
               						$("input[name='name']").val(startip+"~"+array[3].toString());               					
               					}
               				}                			
               			}               		
               		
               		},
               		{ label: "子网掩码",name: "netmask",newline: true,type: "text"},
               		{ label: "网关IP",name: "gatewayip",newline: false,type: "text"},
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
						}                     
                    },
                    { label: "所属单位或部门",name: "deptid",newline: false,type: "select",
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
                    },*/
                    { label: "IP段状态",name: "status",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('IPSEGSTATUS',null),
                    		initValue:"01",
                    		options:{disabled:true}
                    	}                     
                    },                                   		
               		{ label: "VLAN编号",name: "vlanno",newline: false,type: "text"},
					{ label: "首先DNS",name: "dns1",newline: true,type: "text"},
					{ label: "备选DNS",name: "dns2",newline: false,type: "text"},
                    { label: "所属数据中心",name: "dcname",newline: true,type: "text",
                   		editor:{
                    		value:"<%=dc!=null?dc.getName():""%>"
                    	},
                    	options: {disabled: true}                    
                    },					  
                    { label: "用户说明", name: "usefor", newline: true, width:520,type:"textarea"},                     
                    { label: "备注", name: "remark", newline: true, width:520,type:"textarea"}                                      
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
			var data = {"ipseg.name":formData.name,
						"ipseg.startip":formData.startip,
						"ipseg.endip":formData.endip,
						"ipseg.netmask":formData.netmask,
						"ipseg.gatewayip":formData.gatewayip,
						//"ipseg.customerid":formData.customerid,
						"ipseg.status":formData.status,
						//"ipseg.areaid":formData.areaid,
						"ipseg.count":formData.count,
						"ipseg.vlanno":formData.vlanno,
						"ipseg.dns1":formData.dns1,
						"ipseg.dns2":formData.dns2,
						"ipseg.usefor":formData.usefor,
						"ipseg.remark":formData.remark
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
