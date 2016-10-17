<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
String type = request.getParameter("type");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>互联网客户信息编辑</title>
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
               		{ label: "客户编号",name: "no",newline: true,type: "text",options:{disabled:true},group: "客户基础信息", groupicon: groupicon,
                    	validate: {required: true,maxlength:12}
               		},
               		
                    { label: "客户类型",name: "type",newline: false,type: "select",options:{disabled:true},
                        editor:{
                    		data:[{id:"03",text:"互联网客户"}],
                    		initValue:"03"
                    	},
                    	validate: {required: true}
                    },
                    { label: "客户级别",name: "customerlevel",newline: true,type: "select",
                         editor:{
                    		data:[{id:"1",text:"一级"},{id:"2",text:"二级"},{id:"3",text:"三级"}],
                    		initValue:"1"
                    	},
                    	validate: {required: true}                   
                    },               		
                    { label: "客户名称",name: "name",newline: false, type: "text",validate: {required: true,maxlength:255}},
                    { label: "客户简称",name: "sortname",newline: true,type: "text"},
                    { label: "客户性质",name: "customerproperty",newline: false,type: "select",
                         editor:{
                    		data:queryDictionary('CUSTOMERPROPERTY',null),
                    		initValue:""
                    	}                     
                    },
                    { label: "上级客户",name: "parentid",newline: true,type: "text"},
                    { label: "客户经理",name: "manager",newline: false,type: "select",options:{disabled:true}},
                    { label: "法人代表",name: "corporate",newline: true,type: "text"},
                    { label: "客户省份",name: "province",newline: false,type: "select",
                        editor:{
                    		data:[{id:'BEIJING',text:'北京市'},                        
								{id:'HEBEI',text:'河北省'},
								{id:'HENAN',text:'河南省'},
								{id:'YUNNAN',text:'云南省'},
								{id:'LIAONING',text:'辽宁省'},
								{id:'HEILONGJIANG',text:'黑龙江省'},
								{id:'HUNAN',text:'湖南省'},
								{id:'ANHUI',text:'安徽省'},
								{id:'SHANDONG',text:'山东省'},
								{id:'XINJIANG',text:'新疆省'},
								{id:'JIANGSU',text:'江苏省'},
								{id:'ZHEJIANG',text:'浙江省'},
								{id:'JIANGXI',text:'江西省'},
								{id:'HUBEI',text:'湖北省'},
								{id:'GUANGXI',text:'广西省'},
								{id:'GANSU',text:'甘肃省'},
								{id:'SHANXI',text:'山西省'},
								{id:'NEIMENG',text:'内蒙古'},
								{id:'SHAANXI',text:'陕西省'},
								{id:'JILIN',text:'吉林省'},
								{id:'FUJIAN',text:'福建省'},
								{id:'GUIZHOU',text:'贵州省'},
								{id:'GUANGDONG',text:'广东省'},
								{id:'QINGHAI',text:'青海省'},
								{id:'XIZANG',text:'西藏'},
								{id:'SICHUAN',text:'四川省'},
								{id:'NINGXIA',text:'宁夏省'},
								{id:'HAINAN',text:'海南省'},
								{id:'TAIWAN',text:'台湾省'},
								{id:'XIANGGANG',text:'香港'},
								{id:'AOMEN',text:'澳门'}
							],
                    		initValue:"SHANXI"
                    	}                     
                    },
                    
                    { label: "联系人",name: "contactname",newline: true,type: "text",group: "联系人信息", groupicon: groupicon},
                    { label: "手机号码",name: "mobilephone",newline: false,type: "text"},
                    { label: "座机号码",name: "contactphone",newline: true,type: "text"},
                    { label: "通讯地址",name: "contactaddress",newline: true,type:"text",width:520},
                    { label: "客户域级",name: "customerfield",newline: true,type: "select",group: "其他信息", groupicon: groupicon,
                         editor:{
                    		data:queryDictionary('CUSTFIELD',null),
                    		initValue:""
                    	}                    
                    },                    
                    { label: "ICP证号",name: "icpno",newline: false,type: "text"}, 
                    { label: "网站名称",name: "sitename",newline: true,type: "text"},
                    { label: "网站主域名",name: "domainname",newline: false,type: "text"},
                    { label: "技术负责人",name: "skillpeople",newline: true,type: "text"},
                    { label: "引用内容域名",name: "subdomain",newline: false, type: "text"},
                    { label: "引用内容",name: "content",newline: true,type: "text"},
                    { label: "带宽要求",name: "bandwidth",newline: false,type: "text"},
                    { label: "引用方式",name: "method",newline: true,type: "text"},
                    { label: "调度方式",name: "dispatch",newline: false,type: "text"},   
                    { label: "主要协议",name: "prot",newline: true,type: "text"}, 
                    { label: "开户属地",name: "regionid",newline: false,type: "select",
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
                    { label: "备案名称",name: "registername",newline: true,type: "text"},
                    { label: "公司名称",name: "companyname",newline: false,type: "text"},
                    { label: "合同终止日期",name: "enddate",newline: true,type: "date",readonly:true}, 
                    { label: "SLA",name: "slano",newline: true,width:520,type: "textarea"},                                                                                 
                    { label: "备注", name: "remark", newline: true, width:520,type:"textarea", validate: { maxlength: 200 }}
                ]
            }); 
            getCustomerInfo();
            
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
			var data = {"customer.id":"<%=id %>",
						"customer.no":formData.no,
						"customer.name":formData.name,
						"customer.type":formData.type,
						"customer.customerlevel":formData.customerlevel,
						"customer.contactname":formData.contactname,
						"customer.mobilephone":formData.mobilephone,
						"customer.contactphone":formData.contactphone,
						"customer.contactaddress":formData.contactaddress,
						"customer.customerproperty":formData.customerproperty,
						"customer.parentid":formData.parentid,
						"customer.manager":formData.manager,
						"customer.sortname":formData.sortname,			
						"customer.corporate":formData.corporate,			
						"customer.customerfield":formData.customerfield,	
						"customer.icpno":formData.icpno,					
						"customer.sitename":formData.sitename,				
						"customer.domainname":formData.domainname,			
						"customer.skillpeople":formData.skillpeople,		
						"customer.subdomain":formData.subdomain,			
						"customer.content":formData.content,				
						"customer.bandwidth":formData.bandwidth,			
						"customer.method":formData.method,					
						"customer.dispatch":formData.dispatch,	
						"customer.prot":formData.prot,				
						"customer.province":formData.province,				
						"customer.regionid":formData.regionid,				
						"customer.registername":formData.registername,		
						"customer.companyname":formData.companyname,		
						"customer.slano":formData.slano,
						"customer.enddate":$("input[name=enddate]").val(),
						"customer.remark":formData.remark
			};
			return data;
		}
		
		/**表单赋值函数*/
		function getCustomerInfo(){
		  $.ajax({
			url:"queryCustomerById.action", 
			data:{"id":"<%=id%>","type":"<%=type%>"}, 
			async:false,
			dataType:"json", 
			type:"post",
			success:function (msg) {
		       liger.get("form").setData({
					no:msg.no,
					name:msg.name,
					type:msg.type,
					customerlevel:msg.customerlevel,
					contactname:msg.contactname,
					mobilephone:msg.mobilephone,
					contactphone:msg.contactphone,
					contactaddress:msg.contactaddress,
					customerproperty:msg.customerproperty,
					parentid:msg.parentid,
					manager:msg.manager,			
					sortname:msg.sortname,			
					corporate:msg.corporate,			
					customerfield:msg.customerfield,	
					icpno:msg.icpno,					
					sitename:msg.sitename,				
					domainname:msg.domainname,			
					skillpeople:msg.skillpeople,		
					subdomain:msg.subdomain,			
					content:msg.content,				
					bandwidth:msg.bandwidth,			
					method:msg.method,					
					dispatch:msg.dispatch,	
					prot:msg.prot,				
					province:msg.province,				
					regionid:msg.regionid,				
					registername:msg.registername,		
					companyname:msg.companyname,		
					slano:msg.slano,
					enddate:msg.enddate,
					remark:msg.remark			
				});
				$("input[name=enddate]").val(msg.enddate);
				$.ligerui.get("manager").setData([{id:msg.manager,text:msg.managername}]);
				var regionid=$.ligerui.get("regionid").getValue();
				if($.trim(regionid).length==0){
					$.ligerui.get("regionid").setText('');
				}
			}, 
			error:function (error) {
				alert("获取客户信息失败" + error.status);
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
