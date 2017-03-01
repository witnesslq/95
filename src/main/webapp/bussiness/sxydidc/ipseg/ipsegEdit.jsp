<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>"/>
    <title>IP段信息编辑</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script type="text/javascript">
        var form; 
        var groupicon="<%=basePath%>include/LigerUI/skins/icons/communication.gif";
        $(function (){
        	form = $("#form").ligerForm({
        		inputWidth: 180, 
        		labelWidth: 120, 
        		space: 40, 
				validate : true,
                fields: [ 
               		{ label: "IP段名称",name: "name",newline: true,type: "text"},
               		{ label: "IP数量",name: "count",newline: false,type: "text",options:{disabled:true}},
               		{ label: "起始IP",name: "startip",newline: true,type: "text",options:{disabled:true}},
               		{ label: "终止IP",name: "endip",newline: false,type: "text",options:{disabled:true}},
               		{ label: "子网掩码",name: "netmask",newline: true,type: "text",options:{disabled:true}},
               		{ label: "网关IP",name: "gatewayip",newline: false,type: "text"},
                    { label: "所属客户",name: "customerid",newline: true,type: "select",options:{disabled:true}},
                    { label: "所属数据中心ID",name: "dcid",type: "hidden"},
                    { label: "所属数据中心",name: "dcname",newline: false,type: "text",options:{disabled: true}},                     
/*                    { label: "所属单位或部门",name: "deptid",newline: false,type: "select",options:{disabled:true},
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
                    { label: "IP段状态",name: "status",newline: true,type: "select",options:{disabled:true},
                         editor:{
                    		data:queryDictionary('IPSEGSTATUS',null),
                    		initValue:""
                    	}                     
                    },
               		{ label: "vLan编号",name: "vlanno",newline: false,type: "text"},
					{ label: "首先DNS",name: "dns1",newline: true,type: "text"},
					{ label: "备选DNS",name: "dns2",newline: false,type: "text"},  
                    { label: "用户说明", name: "usefor", newline: true, width:520,type:"textarea"},                     
                    { label: "备注", name: "remark", newline: true, width:520,type:"textarea"}
                ]
            }); 
            getIPSegInfo();
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
			var data = {"ipseg.id":"<%=id %>",
						"ipseg.name":formData.name,
						"ipseg.startip":formData.startip,
						"ipseg.endip":formData.endip,
						"ipseg.netmask":formData.netmask,
						"ipseg.gatewayip":formData.gatewayip,
						"ipseg.customerid":formData.customerid,
						"ipseg.status":formData.status,
						//"ipseg.deptid":formData.deptid,
						"ipseg.count":formData.count,
						"ipseg.vlanno":formData.vlanno,
						"ipseg.dns1":formData.dns1,
						"ipseg.dns2":formData.dns2,
						"ipseg.usefor":formData.usefor,
						"ipseg.remark":formData.remark,
						"ipseg.dcid":formData.dcid	
			};			
			return data;		
		}
		
		/**表单赋值函数*/
		function getIPSegInfo(){
		  $.ajax({
			url:"queryIPSegById.action", 
			data:{"id":"<%=id%>"}, 
			async:false,
			dataType:"json", 
			type:"post",
			success:function (msg) {
		       liger.get("form").setData({
					name:msg.name,
					startip:msg.startip,
					endip:msg.endip,
					netmask:msg.netmask,
					gatewayip:msg.gatewayip,
					customerid:msg.customerid,
					status:msg.status,
					deptid:msg.deptid,
					count:msg.count,
					vlanno:msg.vlanno,
					dns1:msg.dns1,
					dns2:msg.dns2,
					usefor:msg.usefor,
					remark:msg.remark,
					dcid:msg.dcid,
					dcname:msg.dcname							
				});
				$.ligerui.get("customerid").setData([{id:msg.customerid,text:msg.customername}]);		
			}, 
			error:function (error) {
				alert("获取IP段信息失败" + error.status);
			}
			});
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
