<%@ page language="java"  pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>" />
    <title>网络设备正视图</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>   
	<link    rel="stylesheet" type="text/css" href="<%=basePath %>css/reportMain.css"/>
	<link    rel="stylesheet" type="text/css" href="<%=basePath %>include/LigerUI/skins/Aqua/css/ligerui-all.css"/>
	<link    rel="stylesheet" type="text/css"  href="<%=basePath %>include/LigerUI/skins/ligerui-icons.css" /> 
    <script  type="text/javascript"  src="<%=basePath  %>include/jQuery/jquery-1.5.2.min.js"></script>    
    <script  type="text/javascript"  src="<%=basePath  %>include/LigerUI/js/ligerui.all.js" ></script> 
    <script src="<%=basePath  %>include/LigerUI/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath  %>include/LigerUI/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath  %>include/LigerUI/jquery-validation/messages_cn.js" type="text/javascript"></script>
     <style type="text/css">
    	.l-text{width:250px;}
    	.l-text input{width:250px;}
    </style>
    <script type="text/javascript">
        var form;
        $(function (){
        	form = $("#form").ligerForm({
        		labelWidth: 120, 
        		inputWidth: 260, 
        		space: 40, 
				validate : true,
                fields: [ 
                    { label: "插槽编号",name: "slotno",newline: true,type: "spinner",validate: { required: true},
                    	editor:{
                   			minValue:0,
                   			type:"int"
                    	}  
                    },
               		{ label: "板卡编码",name: "code",newline: true,type: "text",validate: { required: true},
               			editor:{
                    		             		
                    	}
                    },
                    { label: "板卡序列号",name: "sn",newline: true,type: "text",validate: { required: false}},
                    { name: "perrow",newline: false,type: "hidden"},
                    { label: "端口类型",name: "porttype",newline: true,type: "select",validate: { required: true},
                         editor:{
                   			url:"selectedQuery.action",
							parms:[{name: 'dictType', value: "PORTTYPE"}],
							isMultiSelect:false,
							valueField: 'id',
							onSelected:function(v,t){
								if(t.indexOf("十")>=0){
									$("[name = 'confrate']").val(10);
								}else if(t.indexOf("百")>=0){
									$("[name = 'confrate']").val(100);
								}else if(t.indexOf("千")>=0){
									$("[name = 'confrate']").val(1000);
								}else if(t.indexOf("万")>=0){
									$("[name = 'confrate']").val(10000);
								}
							}
                    	}                     
                    },
                    { label: "端口速率(MB)",name: "confrate",newline: true,type: "spinner",validate: { required: true},
                     	editor:{
                   			minValue:0,
                   			type:"int"
                    	}       
                    },
                    { label: "端口数量",name: "portcount",newline: true,type: "spinner",validate: { required: true},
                    	editor:{
                   			minValue:1,
                   			maxValue:99,
                   			type:"int"
                    	}    
                    }
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
			var data = {
						"model.deviceid":'<%=id %>',
						"model.code":formData.code,
						"model.sn":formData.sn,
						"model.portcount":formData.portcount,
						"model.perrow":formData.perrow,
						"model.slotno":formData.slotno,
						"model.porttype":formData.porttype,
						"portmodel.no":"0",
						"portmodel.type":formData.porttype,
						"portmodel.confrate":formData.confrate
						};
			return data;
		}
		
        function createpackDeviceNo(){
       		$.ajax({
				url:"createpackDeviceNo.action", 
				dataType:"json", 
				type:"post",
				success:function (msg) {
					$("input[name='code']").val(msg.code);
					hideTip();
				}, 
				error:function (error) {
					top.$.ligerDialog.error("获取板卡编号失败!" + error.status,"错误");
				}
			});		
		}
		function hideTip(){
			$("input[name='code']").ligerHideTip();
		}	
    </script>
	<script type="text/javascript">
	 var slotmenu,packmenu,cslotno,cpackid;
	 jQuery(function($){
	 	slotmenu = $.ligerMenu({ top: 100, left: 100, width: 120, items:
              [
              { text: '添加板卡', click: addPack,icon:'add' },
              { text: '删除插槽', click: deleteSlot,icon:'delete'}
             ]
             });
         packmenu = $.ligerMenu({ top: 100, left: 100, width: 120, items:
              [
              { text: '删除板卡及端口', click: deletePack,icon:'delete'}
             ]
             });
		/*端口双击事件*/
		$(".divport>img").live("click",function(){
		
		});
		$(".divport>img").live("mouseover",function(){
			$(this).addClass("img");
		});
		$(".divport>img").live("mouseout",function(){
			$(this).removeClass("img");
		});
		$(".indexdiv").live("contextmenu",function(e){
			slotmenu.show({ top: e.pageY, left: e.pageX });
			cslotno = $(this).html();
			return false;
		});
		$(".packdiv").live("contextmenu",function(e){
			packmenu.show({ top: e.pageY, left: e.pageX });
			cpackid = $(this).attr("id");
			return false;
		});
		$("#addslot").click(function(){
			  form.setData({
            	"slotno":cslotno,
            	"perrow":24,
            	"confrate":0,
            	"portcount":1,
            	"sn":"",
            	"porttype":""
            	
             });
            createpackDeviceNo();
	 		$.ligerDialog.open({
				target: $("#target2"),width: 500, height: 300, title: "添加板卡", buttons: [
				{text: "添加", onclick: function (item, dialog) {
						var data = f_validate();
						if(data){
						$.ligerDialog.waitting('正在保存中,请稍候...'); 
						$.ajax({
							url:"addPackInfo.action", 
							data:data,
							type:"post",
							dataType:"json", 
							success:function (msg) {
								$.ligerDialog.closeWaitting(); 
				       			if("error" == msg.result){
				       				top.$.ligerDialog.error("添加设备信息失败!");
				       			}else{
					       			top.$.ligerDialog.success("添加设备信息成功!");
					       			showPortList();
					       			dialog.hide();
				       			}
							}, 
							error:function (error) {
								$.ligerDialog.closeWaitting(); 
								top.$.ligerDialog.error("添加设备信息失败!" + error.status,"错误");
							}
							});
							}
						}
				},
				{text: "取消", onclick: function (item, dialog) {
						dialog.hide();
					}
				}
			]
	     	});
			});
		showPortList();
	    $("#slotno").ligerSpinner({ height: 28,width:200, type: 'int',isNegative:false });
	    
	 });
	 /*添加板卡*/
	 function addPack(item,i){
	 		 form.setData({
            	"slotno":cslotno,
            	"perrow":24,
            	"confrate":0,
            	"portcount":1,
            	"sn":"",
            	"porttype":""
            	
             });
            createpackDeviceNo();
	 		$.ligerDialog.open({
				target: $("#target2"),width: 500, height: 300, title: "添加板卡", buttons: [
				{text: "添加", onclick: function (item, dialog) {
						var data = f_validate();
						if(data){
						$.ligerDialog.waitting('正在保存中,请稍候...'); 
						$.ajax({
							url:"addPackInfo.action", 
							data:data,
							type:"post",
							dataType:"json", 
							success:function (msg) {
								$.ligerDialog.closeWaitting(); 
				       			if("error" == msg.result){
				       				top.$.ligerDialog.error("添加设备信息失败!");
				       			}else{
					       			top.$.ligerDialog.success("添加设备信息成功!");
					       			showPortList();
					       			dialog.hide();
				       			}
							}, 
							error:function (error) {
								$.ligerDialog.closeWaitting();
								top.$.ligerDialog.error("添加设备信息失败!" + error.status,"错误");
							}
							});
							}
						}
				},
				{text: "取消", onclick: function (item, dialog) {
						dialog.hide();
					}
				}
			]
	     	});
	 }
	 /*删除板卡*/
	 function deletePack(){
		$.ligerDialog.confirm('确定要删除板卡及端口？', function (yes) { 
				if(yes){
				$.ajax({
			 		url:"checkPackPortInfo.action",
			 		data:{"model.id":cpackid},
			 		dataType:"json",
			 		type:"post",
			 		success:function(data){
			 			if(data.result == "error"){
			 				$.ligerDialog.error("网络状况不好，检查端口能否删除出错！");
			 			}else{
			 				if(data.result == "0"){
			 					$.ajax({
							 		url:"deletePackInfo.action",
							 		data:{"model.id":cpackid},
							 		dataType:"json",
							 		type:"post",
							 		success:function(data){
							 			if(data.result == "success"){
							 				$.ligerDialog.success("删除成功！");
							 				showPortList();
							 			}else{
							 				$.ligerDialog.error("网络不好，删除失败！");
							 			}
							 		}
							 	});
			 				}else{
			 					$.ligerDialog.warn("该板卡中已有端口被占用，不能删除！");
			 				}
			 			}
			 		}
			 		});
				}
		 });
	 }
	  /*删除插槽*/
	 function deleteSlot(){
	 $.ligerDialog.confirm('确定要删除该插槽？', function (yes) { 
		if(yes){
			$.ajax({
		 		url:"checkSlotPortInfo.action",
		 		data:{"deviceid":"<%=id %>","model.slotno":cslotno},
		 		dataType:"json",
		 		type:"post",
		 		success:function(data){
		 			if(data.result == "error"){
		 				$.ligerDialog.error("网络状况不好，检查端口能否删除出错！");
		 			}else{
		 				if(data.result == "0"){
		 					$.ajax({
						 		url:"deleteSlotInfo.action",
						 		data:{"model.deviceid":"<%=id %>","model.slotno":cslotno},
						 		dataType:"json",
						 		type:"post",
						 		success:function(data){
						 			if(data.result == "success"){
						 				$.ligerDialog.success("删除成功！");
						 				showPortList();
						 			}else{
						 				$.ligerDialog.error("网络不好，删除失败！");
						 			}
						 		}
						 		});
		 				}else{
		 					$.ligerDialog.warn("该插槽中已有端口被占用，不能删除！");
		 				}
		 			}
		 		}
		 		});
	 		}
		});
	 }
	/*展示端口列表*/
	function showPortList(){
		$.ajax({
			url:"deviceSlotQueryListByDevId.action",
			data:{"deviceid":"<%=id %>"},
			type:"post",
			dataType:"json",
			success:function(data){
			 	if((data.list).length == 0){
			 		$("#showpack").html("");
			 		return false;
			 	}
				var mm = data.list;
				var divstr = "";
				$("#showpack").empty();
				var flagslot = "#";
				$.each(mm,function(index,item){
					var slothei = 32;
					if(item.rowcount){
						 //slothei = 32 * item.rowcount;
					}
					if(flagslot == "#"){
						divstr += "<div class='slotdiv' id='"+(item.deviceid + item.slotno)+"' style='height:"+slothei+"px;line-height:"+slothei+"px'>";
						divstr += "<table><tr>";
						divstr += "<td><div class='indexdiv' style='height:"+slothei+"px;line-height:"+slothei+"px'>"+parseInt(item.slotno)+"</div></td>";
						/*新的插槽开始*/
						var porthtml = getPackPort(item.id,item.type);
						if(porthtml){
							divstr += "<td><div class='packdiv' id='"+item.id+"'>"+porthtml+"</div></td>";
						}
						flagslot = item.slotno;
					}else if(flagslot == item.slotno){
						/*插槽的板卡添加*/
						var porthtml = getPackPort(item.id,item.type);
						if(porthtml){
							divstr += "<td><div class='packdiv' id='"+item.id+"'>"+porthtml+"</div></td>";
						}
					}else{
						/*新的插槽开始*/
						divstr += "</tr></table>";
						divstr += "</div>";
						divstr += "<div class='slotdiv' id='"+(item.deviceid + item.slotno)+"' style='height:"+slothei+"px;line-height:"+slothei+"px'>";
						divstr += "<table><tr>";
						divstr += "<td><div class='indexdiv' style='height:"+slothei+"px;line-height:"+slothei+"px'>"+parseInt(item.slotno)+"</div></td>";
						var porthtml = getPackPort(item.id,item.type);
						if(porthtml){//千兆光口
							divstr += "<td><div class='packdiv' id='"+item.id+"' >"+porthtml+"</div></td>";
						}
						flagslot = item.slotno
					}
				});
				divstr += "</tr></table>";
				divstr += "</div>";
				$("#showpack").html(divstr);
				var ccwidth = $("#showpack").width();//插槽宽度
				$(".slotdiv>table").each(function(index,item){
					var thiswidth = $(this).width();
					if(parseInt(thiswidth) > parseInt(ccwidth)){
						ccwidth = thiswidth;
					}
				});
				var showpackwidth = $("#showpack").width();
				if(parseInt(showpackwidth) < parseInt(ccwidth)){
						ccwidth = (parseInt(ccwidth) + 20);
				}
				$("#showpack").css("width",ccwidth+"px");
				var slotwidth = "";
				$(".slotdiv>table").each(function(index,item){
					var thisheight = ($(this).css("height")).substring(0,$(this).css("height").length-2);
					if(parseInt(thisheight) > parseInt(slotwidth)){
						slotwidth = thisheight;
					}
					$(this).parent().css("height",slotwidth);
				});
			},
			error:function(e){
				$.ligerDialog.warn("网络状况不佳，端口列表查询失败！");
			}
		});
	}
	/*板卡端口获取*/
	function  getPackPort(packid,type){
		var portstr = "";
		$.ajax({
			url:"packPortQueryListByPackId.action",
			async:false,
			data:{"packid":packid},
			type:"post",
			dataType:"json",
			success:function(data){
			     if((data.list).length != 0){
				 portstr += "<table border = '0' class='packtable'>";
				 portstr += "<tr>";
				 var btow = true;
				 var twostart = 1;
				 var currrow = "#";
				$.each(data.list,function(index,item){
					var cls = "divport";
					var title = "端口序号："+item.no+"\n";
						title += "端口编码："+item.portcode+"\n";
						if(item.status == "01"){
							title += "端口状态：空闲\n";
						}else if(item.status == "02"){
							title += "端口状态：预占\n";
						}else if(item.status == "03"){
							title += "端口状态：实占\n";
						}else if(item.status == "04"){
							title += "端口状态：使用中\n";
						}
						title += "端口类型："+item.porttypename+"\n";
						title += "端口速率："+item.confrate+"MB\n";
						if(item.servicename == null){
							title += "对端服务器：\n";
						}else{
							title += "对端服务器："+item.servicename+"\n";
						}
						if(item.customername == null){
							title += "所属客户：\n";
						}else{
							title += "所属客户："+item.customername+"\n";
						}
						
						if((item.porttypename).indexOf("光口") >= 0){
							item.type = "01";
						}else{
							item.type = "02";
						}
						
					var imgsrc = "";
					var imgclass = "";
					if(currrow == "#"){
						if(item.type == '01'){
							imgsrc = "images/portimage/fo-"+item.status+".png";
						}else{
							imgsrc = "images/portimage/rj45-"+item.status+".png";
						}
						if(item.status == "01"){
							imgclass = "kximg";
						}else if(item.status == "02"){
							imgclass = "yzimg";
						}else if(item.status == "03"){
							imgclass = "szimg";
						}else if(item.status == "04"){
							imgclass = "syimg";
						}else if(item.status == "00"){
							imgclass = "blimg";
						}
						var imgstr = "<img id='"+item.id+"' src='"+imgsrc+"' class='"+imgclass+"'  title='"+title+"'/>";
						portstr += "<td style='height:22px;width:22px;line-height:22px;'>"
						portstr += "<div class='"+cls+"'>"
						portstr += "<input type='hidden' value='"+item.portname+"' id='portname'>";
						portstr += imgstr;
						portstr += "<input type='hidden' value='"+item.porttypename+"' id='porttype'>"
						portstr += "</div>"
						portstr += "</td>";
						currrow = item.rowno;
					}else if(item.rowno == currrow){
						if(item.type == '01'){
							imgsrc = "images/portimage/fo-"+item.status+".png";
						}else{
							if(item.rowno%2 == 0){
								imgsrc = "images/portimage/rj45-"+item.status+"-.png";
							 }else{
							 	imgsrc = "images/portimage/rj45-"+item.status+".png";
							 }
						}
						if(item.status == "01"){
							imgclass = "kximg";
						}else if(item.status == "02"){
							imgclass = "yzimg";
						}else if(item.status == "03"){
							imgclass = "szimg";
						}else if(item.status == "04"){
							imgclass = "syimg";
						}else if(item.status == "00"){
							imgclass = "blimg";
						}
						var imgstr = "<img id='"+item.id+"' src='"+imgsrc+"' class='"+imgclass+"'  title='"+title+"'/>";
						portstr += "<td style='height:22px;width:22px;line-height:22px;'>"
						portstr += "<div class='"+cls+"'>"
						portstr += "<input type='hidden' value='"+item.portname+"' id='portname'>";
						portstr += imgstr;
						portstr += "<input type='hidden' value='"+item.porttypename+"' id='porttype'>"
						portstr += "</div>"
						portstr += "</td>";
						if((twostart+1)%4 == 0 && twostart > 0 && (twostart+1)%24 != 0){
							portstr += "<td style='width:22px;'><div style='width:22px;'></div></td>"
						}
						twostart = twostart + 1;
						currrow = item.rowno;
					}else{
						var imgstr = "";
						var imgclass = "";
						if(item.type == '01'){
							imgsrc = "images/portimage/fo-"+item.status+".png";
						}else{
							if(item.rowno%2 == 0){
								imgsrc = "images/portimage/rj45-"+item.status+"-.png";
							 }else{
							 	imgsrc = "images/portimage/rj45-"+item.status+".png";
							 }
						}
						if(item.status == "01"){
							imgclass = "kximg";
						}else if(item.status == "02"){
							imgclass = "yzimg";
						}else if(item.status == "03"){
							imgclass = "szimg";
						}else if(item.status == "04"){
							imgclass = "syimg";
						}else if(item.status == "00"){
							imgclass = "blimg";
						}
						imgstr = "<img id='"+item.id+"' src='"+imgsrc+"' class='"+imgclass+"'  title='"+title+"'/>";
						portstr += "</tr>";
						portstr += "<tr>";
						portstr += "<td style='height:22px;width:22px;'>"
						portstr += "<div  class='"+cls+"'>"
						portstr += "<input type='hidden' value='"+item.portname+"' id='portname'>";
						portstr += imgstr;
						portstr += "<input type='hidden' value='"+item.porttypename+"' id='porttype'>"
						portstr += "</div>"
						portstr += "</td>";
						twostart = 1;
						currrow = item.rowno;
					}
					
				});
				portstr += "</tr>";
				portstr += "</table>";
				}else{
					portstr +="";
				}
			},
			error:function(e){
				
			}
			});
			return portstr;
	}
	</script>
	<style type="text/css">
			/*鼠标手势*/
			.moveu{
				cursor: pointer;
			}
			/* 左侧div*/
			#main {
				position: absolute;
				top:30px;
				left:10px;
				width: 702px;
				height:430px;
				border: 0px solid #999;
				overflow: auto;
				-moz-user-select:none;
			}
		
			#showpack{
				margin-top: 10px;
				border: 2px solid #3D3D3D;
				padding:0px;
			}
			
			#showep{
				width:99%;
				margin: 0 auto;
			}
		
			.showtable{  
				border: 0px solid #B1CDE3;  
				padding:0;   
				margin:0 auto;  
				border-collapse: collapse;  
			} 
			.showtable td {  
				border: 0px solid #B1CDE3;  
				font-size:14px;  
				padding: 3px 3px 3px 8px;  
				color: #000;  
				height: 25px;
				line-height:25px;
				}
			#showpack{
				margin-top: 10px;
				border: 2px solid #3D3D3D;
				padding:0px;
			}
			
			.showcolor{
				width:10px;
				height:10px;
			}
			.divport{
				width:22px;
				height:22px;
				text-align: center;
				padding-top:1px;
			}
			.indexdiv{
				text-align: center;
				cursor: default;
			}
			.img{
				margin: 0 auto;
				border: 0px solid black !important;
				width:18px !important;
				height:18px !important;
				margin-top:2px;
			}
			.selectimg{
				margin: 0 auto;
				border: 0px solid green !important;
			}
			.tdcla{
				border-left: 1px solid gray;
				border-top: 1px solid gray;
				border-bottom: 1px solid gray;
			}
			.slotdiv{
				position:relative;
				margin:5px;
				clear: both;
				background: #DDDDDD;
				border: 0px solid #8F9192;
			}
			.indexdiv{
				position:relative;
				float: left;
				width:40px;
				background-color: #DDDDDD;
				border: 0px solid #8F9192;
				line-height:28px;
			}
			.packdiv{
				position:relative;
				float: left;
				background: #DDDDDD;
				border: 2px solid #8F9192;
			}
			.packtable{
				margin-top: 2px;
				margin-left: 2px;
				margin-bottom: 2px;
				margin-right: 2px;
				padding: 0;
				height: 100%;
				background-color: #555555;
			}
			.packtable td{
				border: 0px solid black !important;
			}
	</style>
</head>
  
<body>
	<div id="showRack" style="width:98%;position: relative;margin: 0 auto;">
		<div id="main" onselectstart="return false;">
			<div id="showep">
				<table class='showtable' style="width:70%">
					<tr>
						<td><input type="button" class="btn" id="addslot" value="添加板卡"/></td>
						<td><div class='showcolor' style="background-color: #626C78"></div></td>
						<td>保留</td>
						<td><div class='showcolor' style="background-color: #15E864"></div></td>
						<td>空闲</td>
						<td><div class='showcolor' style="background-color: #FF9224"></div></td>
						<td>预占</td>
						<td><div class='showcolor' style="background-color: #FFF200"></div></td>
						<td>实占</td>
						<td><div class='showcolor' style="background-color: #FF3333"></div></td>
						<td>使用中</td>
					</tr>
				</table>
			</div>
			<div id="showpack">
			</div>
		</div>
	</div>
	
	<div id="target1" style="margin:3px; display:none;">
	    <table style='width:99%;margin:0 auto;'>
		    <tr>
		    	<td>插槽编号：</td>
		    	<td> <input type="text" id="slotno" /></td>
		    </tr>
	    </table>
 	</div>
 	<div id="target2" style="margin:3px; display:none;">
 		<form id="form"></form> 
 	</div>
 	
</body>
</html>
