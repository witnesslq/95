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
	<base href="<%=basePath%>"/>
    <title>机房信息编辑</title>
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
                	{ label: "机房编码",name: "roomcode",newline: true,type: "text",validate: {required: true},options: {disabled: true}}, 
               		{ label: "机房名称",name: "roomname",newline: false,type: "text",validate: {required: true}},
                    { label: "机房等级",name: "grade",newline: true,type: "select",options: {disabled: true},
                   		 editor:{
                    		data:[{id:"01",text:"A"},{id:"02",text:"B"},{id:"03",text:"C"}],
                    		initValue:"01"
                    	}
                    }, 
                    { label: "所属公司或单位",name: "deptid",newline: false,type: "select",options: {disabled: true},
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
                    { label: "行数",name: "colcount",newline: true, type: "text",validate: {required: true},options: {disabled: true}},
                    { label: "列数",name: "rowcount",newline: false,type: "text",validate: {required: true},options: {disabled: true}},
                    { label: "维护人员",name: "maintancer",newline: true,type: "text"},
                    { label: "手机号码",name: "telephone",newline: false,type: "text"},
                    { label: "机架规格",name: "racktype",newline: true,type: "select",options: {disabled: true},
                         editor:{
                    		data:queryDictionary('RACKTYPE',null),
                    		initValue:""
                    	},
                    	validate: {required: true}                    
                    },
                    { label: "所属区域",name: "areaid",newline: false,type: "select",options: {disabled: true},
						editor: {
                			data: queryAreaData(''),
							initValue:""       									
						}                     
                    },                                        
                    { label: "长(米)",name: "width",newline: true,type: "text"},
                    { label: "宽(米)",name: "height",newline: false,type: "text"},
                    { label: "是否启用",name: "status",newline: true,type: "select",
                   		 editor:{
                    		data:[{id:"01",text:"启用"},{id:"02",text:"未启用"}],
                    		initValue:"01"
                    	},
                    	validate: {required: true}
                    },
                    { label: "所属数据中心ID",name: "dcid",type: "hidden"},
                    { label: "所属数据中心",name: "dcname",newline: false,type: "text",options:{disabled: true}},                    
                    { label: "地址",name: "address",newline: true,type: "text",width:520},                    
                    { label: "备注",name: "remark",width:520,type:"textarea", validate: { maxlength: 200 }}
                ]
            }); 
            $("input[name='roomcode']").attr('title','机房编码格式参照ROOM-351-02-07!');
            getRoomInfo();
            
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
			var data = {"rsroom.id":"<%=id %>",
						"rsroom.roomname":formData.roomname,
						"rsroom.roomcode":formData.roomcode,
						"rsroom.grade":formData.grade,
						"rsroom.colcount":formData.colcount,
						"rsroom.rowcount":formData.rowcount,
						"rsroom.maintancer":formData.maintancer,
						"rsroom.telephone":formData.telephone,
						"rsroom.racktype":formData.racktype,
						"rsroom.width":formData.width*100,
						"rsroom.height":formData.height*100,
						"rsroom.address":formData.address,
						"rsroom.status":formData.status,			
						"rsroom.areaid":formData.areaid,
						"rsroom.deptid":formData.deptid,				
						"rsroom.remark":formData.remark,
						"rsroom.dcid":formData.dcid	
			};
			return data;
		}
		
		/**表单赋值函数*/
		function getRoomInfo(){
		  $.ajax({
			url:"queryRoomById.action", 
			data:{"id":"<%=id%>","type":"<%=type%>"}, 
			async:false,
			dataType:"json", 
			type:"post",
			success:function (msg) {
		       liger.get("form").setData({
					roomname:msg.roomname,
					roomcode:msg.roomcode,
					grade:msg.grade,
					colcount:msg.colcount,
					rowcount:msg.rowcount,
					maintancer:msg.maintancer,
					telephone:msg.telephone,
					racktype:msg.racktype,
					width:msg.width/100,
					height:msg.height/100,
					address:msg.address,
					status:msg.status,				
					areaid:msg.areaid,
					deptid:msg.deptid,			
					remark:msg.remark,
					dcid:msg.dcid,
					dcname:msg.dcname		
															
				});
				$.ligerui.get("areaid").setText(msg.area);
			}, 
			error:function (error) {
				alert("获取机房信息失败" + error.status);
			}
			});
		}
		
		
		function hideTip(){
			$("input[name='no']").ligerHideTip();
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
</body>
</html>