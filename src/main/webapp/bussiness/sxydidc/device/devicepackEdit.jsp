<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>运维设备信息列表页面</title>
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
					{ name: "deviceid",type:"hidden"},                
               		{ label: "板卡编码",name: "code",newline: true,type: "text",
               			editor:{
                    		onMouseOver: function(){
								$("input[name='code']").ligerTip({
                            		content:"<div><span>点击<a href='javascript:createpackDeviceNo();'>这里</a>重新生成设备编号。</span><span><a href='javascript:hideTip();'>关闭</a></span></div>",
                            		width:120
          						}); 
                    		}               		
                    	},
                    	validate: {required: true}
                    },
                    { label: "板卡序列号",name: "sn",newline: false,type: "text",validate: {required: true}},
                    
                    { label: "端口类型",name: "porttype",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('PORTTYPE',null),
                    		initValue:""
                    	},
                    	validate: {required: true}                     
                    },
                    { label: "端口数量",name: "portcount",newline: false,type: "text",validate: {required: true}},
                    { label: "板卡行数",name: "rowcount",newline: true,type: "text",validate: {required: true}},
                    { label: "每行端口数",name: "perrow",newline: false,type: "text",validate: {required: true}},
                    { label: "所在插槽编号",name: "slotno",newline: true,type: "text",validate: {required: true}},
                    { label: "板卡序号",name: "packno",newline: false,type: "text",validate: {required: true}}
                ]
            }); 
            
            getBoardInfo();
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
			var data = {"packdevice.id":'<%=id%>',
						"packdevice.deviceid":formData.deviceid,
						"packdevice.code":formData.code,
						"packdevice.sn":formData.sn,
						"packdevice.portcount":formData.portcount,
						"packdevice.porttype":formData.porttype,
						"packdevice.rowcount":formData.rowcount,
						"packdevice.perrow":formData.perrow,
						"packdevice.slotno":formData.slotno,
						"packdevice.packno":formData.packno
			};
			return data;
		}
		
		/**表单赋值函数*/
		function getBoardInfo(){
		  $.ajax({
			url:"queryDevicePackById.action", 
			data:{"id":"<%=id%>"}, 
			async:false,
			dataType:"json", 
			type:"post",
			success:function (msg) {
		       liger.get("form").setData({
		       		deviceid:msg.deviceid,
					code:msg.code,
					sn:msg.sn,
					portcount:msg.portcount,
					porttype:msg.porttype,
					rowcount:msg.rowcount,
					perrow:msg.perrow,
					slotno:msg.slotno,
					packno:msg.packno					
				});
			}, 
			error:function (error) {
				alert("获取设备板卡信息失败" + error.status);
			}
		  });
		}		
		
        function createpackDeviceNo(){
       		$.ajax({
				url:"createpackDeviceNo.action", 
				dataType:"json", 
				type:"post",
				success:function (msg) {
					top.$.ligerDialog.success("获取板卡编号成功!");
					$("input[name='code']").val(msg.code);
				}, 
				error:function (error) {
					top.$.ligerDialog.error("获取板卡编号失败!" + error.status,"错误");
				}
			});		
		}
		
		function hideTip(){
			$("input[name='code']").ligerHideTip();
		};
		
			
    </script>    
  	
</head>
<body style="padding:10px">  
    <form id="form"></form>
</body>
</html>
