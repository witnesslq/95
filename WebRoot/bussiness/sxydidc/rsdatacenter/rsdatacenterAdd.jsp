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
    <title>添加邮箱信息</title>
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
                	{ name: "area",type: "hidden"},
                	{ label: "名称",name: "name",newline: true,type: "text",validate: {required: true}},
                	{ label: "地址",name: "address",newline: true,type: "text",validate: {required: true}},
                	{ label: "所属公司",name: "companyId",newline: true,type: "select",
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
							},
							onSelected: function(id,value){
								if(''!=id&&''!=value&&"null"!=value){
										$("[name=deptid]").val(id);
										var topcorpid = "";
										$.ajax({
											url:"QueryTopCorpId.action?corpid="+id, 
											async:false,
											type:"post",
											success:function (data) {
												topcorpid = data;
											}, 
											error:function (error) {
												top.my_alert("获取信息失败！" + error.status);
											}
										});
										liger.get("regionId").treeManager.set("url","QueryAreaByDept.action?corpid="+topcorpid);
								}
							}							
						}
                	},
                	{ label: "所属区域",name: "regionId",newline: true,type: "select",
                		editor: {
	                        width : 180, 
				            selectBoxWidth: 200,
				            selectBoxHeight: 200, 
				            valueField: 'id',
				            treeLeafOnly: true,
	                        tree: { 
								ajaxType:'post',
								idFieldName: 'id',
	            				parentIDFieldName: 'pid',
	            				onSuccess:function(){
	            					clearNullValue(liger.get("regionId"));
	            				},
	            				checkbox: false
							},
							onSelected: function(id,value){
								if(''!=id&&''!=value&&"null"!=value){
									$("[name=regionId]").val(id);
								}
							}
	                    }
                	},
                	{ label: "备注", name: "remark", newline: true, width:520,type:"textarea", validate: { maxlength: 200 }}
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
		function clearNullValue(obj){//当前下拉框的ligerui对象。liger.get("id");
		var ids = obj.getValue();
		var tempArr = new Array();
		if(ids != "" && ids != "null"){
			var idArr = ids.split(";");
			for(var i=0;i<idArr.length;i++){
				var id = idArr[i];
				if(id != "" && id != "null"){
					var t = obj.treeManager.getTextByID(id);//
					if(t != null && t != "" && t != "null"){
						tempArr.push(id);
					}
				}
			}
		}
		obj.setValue(tempArr.join(";"));
		if(!tempArr.join(";")){
			obj.setText("");
		}
		return tempArr.join(";");
	}
		
		/**获取表单要保存的数据以json格式返回*/
		function datePost(){
			var formData = form.getData();	
			var data = {"rsdata.id":"<%=id %>",
						"rsdata.name":formData.name,
						"rsdata.address":formData.address,
						"rsdata.companyId":formData.companyId,
						"rsdata.regionId":formData.regionId,
						"rsdata.remark":formData.remark
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
