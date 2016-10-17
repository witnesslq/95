<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String room_id=(String)request.getParameter("room_id");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>机柜信息添加</title>
    <link    rel="stylesheet" type="text/css" href="<%=basePath %>css/reportMain.css"/>
    <link    rel="stylesheet" type="text/css" href="<%=basePath %>include/LigerUI/skins/Aqua/css/ligerui-all.css"/>
    <link    rel="stylesheet" type="text/css"  href="<%=basePath %>include/LigerUI/skins/ligerui-icons.css" /> 
    <script  type="text/javascript"  src="<%=basePath  %>include/jQuery/jquery-1.3.2.min.js"></script>    
    <script  type="text/javascript"  src="<%=basePath  %>include/LigerUI/js/ligerui.all.js" ></script>
    <script  type="text/javascript"  src="<%=basePath  %>include/LigerUI/js/plugins/ligerDateEditor.js" ></script>
    <script  type="text/javascript"  src="<%=basePath  %>include/LigerUI/js/plugins/ligerComboBox.js" ></script>
    <script src="<%=basePath  %>include/LigerUI/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath  %>include/LigerUI/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath  %>include/LigerUI/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="<%=basePath  %>bussiness/sxydidc/js/common.js" type="text/javascript"></script>
   <script type="text/javascript">
        var form;
        var customerData;
        var room_id="<%=room_id%>";
        $(function (){
        	form = $("#form").ligerForm({
        		inputWidth: 180, 
        		labelWidth: 120, 
        		space: 40, 
				validate : true,
                fields: [ 
               		{ label: "机架名称",name: "name",newline: true,type: "text",validate: {required: true}},
                    { label: "机架编码",name: "code",newline: false,type: "text",validate: {required: true}},
                    { label: "机架规格",name: "typeid",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('RACKTYPE',null),
                    		initValue:"",
                    		onSelected:function(ucount){
                    			$("input[name='ucount']").val(ucount);
                    		}
                    	}                    
                    },
                    { label: "U位数量",name: "ucount",newline: false,type: "text"},
                    { label: "所属机房",name: "roomid",newline: true,type: "select",readonly:true,
                    
                         editor:{
                				selectBoxWidth: 320,
                				selectBoxHeight: 300,
                				slide: false,
                				valueField: 'id',
                				textField: 'roomname',                         
                  				grid: {
                  					checkbox: true, 
                    				columns: [
                						{ display: '机房ID', name: 'id',hide:'true'},
										{ display: '机房编号', name: 'roomcode'},
										{ display: '机房名称', name: 'roomname'}
                    				], 
									switchPageSizeApplyComboBox: false,
									pageSize: 10, 
                					data: $.extend({}, queryRoomData())
                				},
                				condition: { 
                					fields: [{
                						name: 'roomname', 
                						label: '机房名称', 
                						width: 90, 
                						type: 'text'}]
                					} 
                    	},
                    	validate: {required: true}                     
                    },                    
                    { label: "行号",name: "rowno",newline: false,type: "select",    
                      editor:{
                    		data:queryrowno(room_id),
                    		initValue:""
                    		}
                    },
                    { label: "列号",name: "colno",newline: true,type: "select",
                      editor:{
                    		data:querycolno(room_id),
                    		initValue:""
                    		}
                    },
                    { label: "位置X坐标值",name: "xposition",newline: false,type: "text"},
                    { label: "位置Y坐标值",name: "yposition",newline: true,type: "text"}, 
                    { label: "最大功率(W)",name: "power",newline: false,type: "text"},                   
                    { label: "机架状态",name: "status",newline: false,type: "select",
                         editor:{
                    		data:queryDictionary('RACKSTATUS',null),
                    		initValue:""
                    	},
                    	validate: {required: true}                     
                    }                                     
                ]
            });         
            liger.get("roomid").setValue(room_id);   
            liger.get("roomid").setDisabled();   
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
			var data = {"rack.name":formData.name,
						"rack.code":formData.code,			
						"rack.typeid":formData.typeid,
						"rack.ucount":formData.ucount,
						"rack.roomid":formData.roomid,
						"rack.rowno":formData.rowno,
						"rack.colno":formData.colno,
						"rack.xposition":formData.xposition,
						"rack.yposition":formData.yposition,
						"rack.power":formData.power,
						"rack.status":formData.status,
					
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
