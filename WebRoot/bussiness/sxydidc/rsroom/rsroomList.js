$(function($){
    my_initGrid();
    bindSearch();
});
/**
初始化机房信息表格
RsroomData：要填充的数据
*/
function my_initGrid(){
	window['g']=$("#maingrid").ligerGrid({
		checkbox: true,
		height:'98%',
		url:"queryRoom.action",
		columns: [
		{ display: '机房编码', name: 'roomcode',width:'13%',
				render: function (item){
					if(item.roomcode!=null){
						return "<a href='javascript:void(0);' onclick=\"itemmess('"+item.id+"');\">"+item.roomcode+"</a>";
					}
				}
		},
		{ display: '机房名称', name: 'roomname',width:'10%'},
		{ display: '机房级别', name: 'grade',
			render: function(item){
				if(item.grade=='01'){
					return 'A';
				}else if(item.grade=='02'){
					return 'B';
				}else if(item.grade=='03'){
					return 'C';
				}
			}
		},
		{ display: '所属公司或单位',   name: 'deptname'},
		{ display: '所属区域',   name: 'area'},
		{ display: '所属数据中心',   name: 'dcname',width:'15%'},
		{ display: '总机架数',   name: 'totalRack'},
		{ display: '空闲机架数',   name: 'freeRack'},
		{ display: '散租机架数',   name: 'disRentRack'},
		{ display: '整租机架数',   name: 'whlRentRack'},
		{ display: '预占机架数',   name: 'preRack'},
		{ display: '机房查看', isAllowHide: false, width:'10%',
				render: function(item){
						var dataname = '<a href="javascript:void(0)" id='+item.id+' onclick="checkout(this);">查看</a>';
						return dataname;
			    }	
		}
		],
		pageSize:10,
		root:"listmodel",
		sortname:"no",
		record:"record",
	 	rownumbers:true, 
	 	title:"机房列表"
       });
}

function bindSearch(){
	$("#addBtn").bind("click", function(){
		itemadd();
	});
	
	$("#editBtn").bind("click", function(){
		itemedit();
	});
	
	$("#delBtn").bind("click", function(){
		itemdelete();
	});
	$("#QRCodeBtn").bind("click", function(){
		itemQRCodeBtn();
	});
	
	$("#searchBtn").bind("click", function(){
		var value=$("#searchTxt").val();
  		quicksearch(value);
	});
	
	$("#searchTxt").bind("keydown", function(event){
		if(event.keyCode==13){
			var value=$("#searchTxt").val();
  			quicksearch(value);
  			return false;
		}
	});
}

/**
打开添加信息窗口的方法
*/
function  itemadd(){
	var url = "bussiness/sxydidc/rsroom/rsroomAdd.jsp";
	winBlockOpen(url,'添加机房信息',760,560,'添加','取消',function(dialog,data){
       	$.ajax({
			url:"saveRoom.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
       			if("error" == msg.result){
       				top.$.ligerDialog.error("添加机房失败!");
       			}else{
					var unblock=dialog.frame.unblock;//执行锁屏
					unblock();
       				dialog.close();
	       			top.$.ligerDialog.success("添加机房成功!");
	       			my_initGrid();
       			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("添加机房失败" + error.status,"错误");
		}});
	});
	
}

/**
打开添加信息窗口的方法
*/
function  itemedit(){
	var selected = g.getSelected();
	if (!selected) {  top.$.ligerDialog.warn('请选择行'); return; }
	var id = (g.getSelectedRow()).id; 
	var type=(g.getSelectedRow()).type; 
	var url = "bussiness/sxydidc/rsroom/rsroomEdit.jsp?id="+id+"&type="+type;
	winOpen(url,'机房信息编辑',760,560,'保存','取消',function(data){
       	$.ajax({
			url:"updateRoom.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
	       		if("error" == msg.result){
	   				top.$.ligerDialog.error("编辑机房信息失败!");
	   			}else{
       				top.$.ligerDialog.success("编辑机房信息成功!","提示");
       				g.updateRow(selected,msg);
	   			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("编辑机房信息失败!" + error.status,"错误");
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
				url:"deleteRoomByIds.action?ids="+idstr, 
				dataType:"json", 
				type:"post",
				success:function (msg) {
		       		if("error"==msg.result){
		   				top.$.ligerDialog.error("删除机房信息失败!");
		   			}else if("01"==msg.result){
		   				g.deleteSelectedRow();
		   				top.$.ligerDialog.success("删除机房信息成功!");
		   			}else if("02"==msg.result){
		   				top.$.ligerDialog.warn("机房中有正在使用的机架无法删除!");
		   			}
				}, 
				error:function (error) {
					top.$.ligerDialog.error("删除机房信息失败!" + error.status);
				}
				});
	     }
	 });
}
/**
打开二维码窗口的方法
*/
function  itemQRCodeBtn(){

	var selected = g.getSelected();
    if (!selected) {  top.my_alert('请选择要查看的机房!',"warn"); return; }
    var id = (g.getSelectedRow()).id; 
	//var url = "bussiness/sxydidc/rsroom/QRcodeRoom.jsp?id="+id;
	var url = "QRcodeRoom.jsp?id="+id;
	//window.top.$.ligerDialog.open({width: 1200, height: 653, url: url, title: "机架二维码"});
	window.open(url,"机架二维码","width=1200,height=653,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes");
	


}




/**
打开添加信息窗口的方法
*/
function  itemmess(id){  
	var url = "bussiness/sxydidc/rsroom/rsroomView.jsp?id="+id;
	winDetailOpen(url,'机房信息展示',760,560,'关闭',function(data){});
}

/**
 * 模糊查询
 */
function quicksearch(value){
	var data=[{name:'key',value:value}];
	g.setOptions({newPage:1});
	g.setOptions({parms:data});
	g.loadData();
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

function winBlockOpen(url,title,width,height,button1,button2,callback){
	window.top.$.ligerDialog.open({
		width: width, height: height, url: url, title: title, buttons: [{
			text: button1, onclick: function (item, dialog) {
				var block=dialog.frame.block;//执行锁屏
				block();
				var fn = dialog.frame.f_validate || dialog.frame.window.f_validate;
				var data = fn();
				if(data){
					callback(dialog,data);
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

function checkout(obj){
	var id=obj.id;
	var url = "rsroomEditorView.action?id="+id;
	window.top.$.ligerDialog.open({width: 1380, height: 653, url: url, title: "机房信息"});
}




