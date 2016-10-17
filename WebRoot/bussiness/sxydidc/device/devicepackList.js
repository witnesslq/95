$(function($){
    var id=$("#deviceId").val();
    my_initGrid(id);
    
});
/**
初始化产品信息表格
CustomersData：要填充的数据
*/
function my_initGrid(id){
	window['g']=$("#maingrid").ligerGrid({
		checkbox: true,
		url:"querypackDeviceById.action?id="+id,
		columns: [
			{ display: "设备ID",name: "deviceid",newline: true,type: "text",hide:'true'},
            { display: "板卡编码",name: "code",newline: true,type: "text",
               render: function(item){
					var id = item.code;
					var dataname = '<a id=\''+item.id+'\' href="javascript:void(0)" onclick="openDialog(this);">'+ id +'</a>';
					return dataname;
				}
			},
            { display: "端口数量",name: "portcount",newline: true,type: "text"},
            { display: "端口类型",name: "porttype",newline: false,type: "select"}
		],
		pageSize:10,
		sortname:"portcount",
		record:"record",
		root:"listmodel",
	 	rownumbers:true, 
	 	title:"设备板卡列表页面",
		toolbar: { items: [
			{ text: '增加', click: itemadd, icon: 'add' },
  			{ line: true },
  			{ text: '删除', click: itemdelete, icon: 'delete'},
  			{ line: true }
  			
  		]}
       });
}

/**
打开添加信息窗口的方法
*/
function  itemadd(){
    var id=$("#deviceId").val();
	var url = "bussiness/sxydidc/device/devicepackAdd.jsp?deviceId="+id;
	winOpen(url,'添加板卡信息',760,560,'添加','取消',function(data){
	alert(liger.toJSON(data));
       	$.ajax({
			url:"savepackDevice.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
       			if("error" == msg.result){
       				top.$.ligerDialog.error("添加板卡信息失败!");
       			}else{
	       			g.addRow(msg);
	       			top.$.ligerDialog.success("添加板卡信息成功!");
       			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("添加板卡信息失败!" + error.status,"错误");
		}});
	});
	
}




/**
打开添加信息窗口的方法
*/
function  itemdelete(){
	var selected = g.getSelected();
    if (!selected) {  top.my_alert('请选择要删除的数据行!',"warn"); return; }
    window.top.$.ligerDialog.confirm("确定删除选择的数据", "提示", function (ok) {
	    if (ok) {
	      	g.deleteSelectedRow();
			var selecteds = g.getSelecteds();
			var idstr="";//所有选择行的id
			for(var i=0;i<selecteds.length;i++){
				idstr = idstr + selecteds[i].id;
				if(i!=(selecteds.length-1)){
					idstr = idstr + ",";
				}
			}
			/**
				删除数据库数据
			 */
			$.ajax({
				url:"deleteDevicepackByIds.action?ids="+idstr, 
				dataType:"json", 
				type:"post",
				success:function (msg) {
		       		if("error"==msg.result){
		   				top.$.ligerDialog.error("删除板卡信息失败!");
		   			}else if("2"==msg.result){
		   				top.$.ligerDialog.error("该板卡下有正在使用的端口!");
		   			}else if("succ"==msg.result){
		   				top.$.ligerDialog.success("删除板卡信息成功!");
		   			}
				}, 
				error:function (error) {
					top.$.ligerDialog.error("删除板卡信息失败!" + error.status);
				}
				});
	     }
	 });
}



function winOpen(url,title,width,height,button1,button2,callback){
	window.top.$.ligerDialog.open({
		width: width, height: height, url: url, title: title, buttons: [{
			text: button1, onclick: function (item, dialog) {
				var fn = dialog.frame.f_validate || dialog.frame.window.f_validate;
				var data = fn();
				if(data){
					callback(data);
					dialog.close();
				}
			}
		},{
			text: button2, onclick: function (item, dialog) {
				dialog.close();
			}
		}
		
		]
     });
}