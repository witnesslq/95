<%@ page import="com.dhcc.bussiness.sxydidc.datacenter.DataCenterModel" language="java"  pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String id = request.getParameter("id");
	String type = request.getParameter("type");
	DataCenterModel dc=(DataCenterModel)request.getSession(true).getAttribute("dc");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>"/>
    <title>添加机房信息</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script type="text/javascript">
        var form;
        var rackTypeData=[{id:'42',text:'42U机架'},{id:'46',text:'46U机架'}];
        $(function (){
        	form = $("#form").ligerForm({
        		inputWidth: 180, 
        		labelWidth: 120, 
        		space: 40, 
				validate : true,
                fields: [
                	{ label: "机房编码",name: "roomcode",newline: true,type: "text",validate: {required: true}}, 
               		{ label: "机房名称",name: "roomname",newline: false,type: "text",validate: {required: true}},
                    { label: "机房等级",name: "grade",newline: true,type: "select",
                   		 editor:{
                    		data:[{id:"01",text:"A"},{id:"02",text:"B"},{id:"03",text:"C"}],
                    		initValue:"01"
                    	}
                    },
                    { label: "所属公司或单位",name: "deptid",newline: false,type: "select",
                    	editor: {
							selectBoxWidth: 180,
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
						},
						validate: {required: true}
                    },                     
                    { label: "是否添加机架",name: "needRack",newline: true,type: "select",
                   		 editor:{
                    		data:[{id:"",text:"请选择"},{id:"true",text:"是"},{id:"false",text:"否"}],
                    		initValue:"",
                    		onSelected: function (value,text){
                    			if(value=='true'){
                    				$.ligerui.get('racktype').set('data',rackTypeData); 
                    			}else if(value=='false'){
                    				$.ligerui.get('racktype').set('data',rackTypeData); 
                    			}
                 			}
                    	}                    
                    },
                    { label: "机架规格",name: "racktype",newline: false,type: "select",
                         editor:{
                    		data:rackTypeData,
                    		initValue:""
                    	}
                    },                    
                    { label: "行数",name: "colcount",newline: true, type: "text",validate: {required: true}},
                    { label: "列数",name: "rowcount",newline: false,type: "text",validate: {required: true}},
                    { label: "维护人员",name: "maintancer",newline: true,type: "text"},
                    { label: "手机号码",name: "telephone",newline: false,type: "text"},
                    { label: "长(米)",name: "width",newline: true,type: "text"},
                    { label: "宽(米)",name: "height",newline: false,type: "text"},
                    { label: "所属区域",name: "areaid",newline: true,type: "select",
						editor: {
							onBeforeOpen:function(){
								winOpen("system/area/query.jsp","选择所属区域",null,560,400,'确定','取消',function(data){
									$.ligerui.get('areaid').setValue(data.id);
									$.ligerui.get('areaid').setText(data.areaname);
								});								
							}      									
						},
						validate: {required: true}                     
                    }, 
                    { label: "是否启用",name: "status",newline: false,type: "select",
                   		 editor:{
                    		data:[{id:"01",text:"启用"},{id:"02",text:"未启用"}],
                    		initValue:"01"
                    	},
                    	validate: {required: true}
                    },
                    { label: "所属数据中心",name: "dcname",newline: false,type: "text",
                   		editor:{
                    		value:"<%=dc!=null?dc.getName():""%>"
                    	},
                    	options: {disabled: true}                    
                    }, 
                    { label: "地址",name: "address",newline: false,type: "text",width:520},                   
                    { label: "备注",name: "remark",width:520,type:"textarea", validate: { maxlength: 200 }}
                ]
            }); 
            $("input[name='roomcode']").attr('title','机房编码格式参照ROOM-351-02-07!');
                       
        });
		
		/**供回调方法使用*/
		function f_validate(){ 
			if(form.valid()){
				return datePost();
			}else{
			    form.showInvalid();
			    unblock();
			}
		}
		
		/**获取表单要保存的数据以json格式返回*/
		function datePost(){
			var formData = form.getData();		
			var data = {"rsroom.id":"<%=id %>",
						"rsroom.roomname":formData.roomname,
						"rsroom.roomcode":formData.roomcode,
						"rsroom.grade":formData.grade,
						"rsroom.needRack":formData.needRack,
						"rsroom.colcount":formData.rowcount,
						"rsroom.rowcount":formData.colcount,
						"rsroom.maintancer":formData.maintancer,
						"rsroom.telephone":formData.telephone,
						"rsroom.racktype":formData.racktype,
						"rsroom.width":formData.width*100,
						"rsroom.height":formData.height*100,
						"rsroom.address":formData.address,
						"rsroom.deptid":formData.deptid,			
						"rsroom.areaid":formData.areaid,
						"rsroom.status":formData.status,			
						"rsroom.remark":formData.remark		
						
			};
			return data;
		}
		
		function block(){
			$.blockUI({
    			message:$('#wait'),
				css: { 
					border: 'none', 
					padding: '15px'
				}	
    		});
		}
		
		function unblock(){
			$.unblockUI(); 
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
	<div id="wait" style="display: none;opacity:0.4;">
		正在执行操作请稍候...<img src="<%=basePath%>include/LigerUI/skins/Gray2014/images/ui/loadingl.gif"/>
	</div>
	<form id="form"></form> 
</body>
</html>
