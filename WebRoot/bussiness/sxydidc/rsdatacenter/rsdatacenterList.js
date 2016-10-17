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
		url:"queryRsdatacenter.action",
		columns: [
		{ display: 'id', name: 'id',width:'1%',hide:true},
		{ display: '所属公司id', name: 'companyId',width:'1%',hide:true},
		{ display: '所属区域id', name: 'regionId',width:'1%',hide:true},
		{ display: '名称', name: 'name',width:'30%'},
		{ display: '地址', name: 'address',width:'30%'},
		{ display: '所属公司', name: 'companyname',width:'20%'},
		{ display: '所属区域', name: 'regionname',width:'20%'}
		],
		pageSize:10,
		root:"listmodel",
		sortname:"id",
		record:"record",
	 	rownumbers:true, 
	 	title:"数据中心列表"
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
	var url = "bussiness/sxydidc/rsdatacenter/rsdatacenterAdd.jsp";
	winBlockOpen(url,'添加数据中心',800,400,'添加','取消',function(dialog,data){
       	$.ajax({
			url:"saveRsdatacenter.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
       			if("error" == msg.result){
       				top.$.ligerDialog.error("添加信息失败!");
       			}else{
					var unblock=dialog.frame.unblock;//执行锁屏
					unblock();
       				dialog.close();
	       			top.$.ligerDialog.success("添加信息成功!");
	       			my_initGrid();
       			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("添加信息失败" + error.status,"错误");
		}});
	});
	
}
/**
打开编辑信息窗口的方法
*/
function  itemedit(){
	var selected = g.getSelected();
	var id=selected.id;//所有选择行的id
	var url = "bussiness/sxydidc/rsdatacenter/rsdatacenterEdit.jsp?id="+id;
	winBlockOpen(url,'数据中心',800,400,'保存','取消',function(dialog,data){
	
       	$.ajax({
			url:"updateRsdatacenterByIds.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
       			if("error" == msg.result){
       				top.$.ligerDialog.error("添加信息失败!");
       			}else{
					var unblock=dialog.frame.unblock;//执行锁屏
					unblock();
       				dialog.close();
	       			top.$.ligerDialog.success("添加信息成功!");
	       			my_initGrid();
       			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("添加信息失败" + error.status,"错误");
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
				url:"deleteRsdatacenterByIds.action?ids="+idstr, 
				dataType:"json", 
				type:"post",
				success:function (msg) {
		   			top.$.ligerDialog.success("删除邮箱信息成功!");
		   			my_initGrid();
				}, 
				error:function (error) {
					top.$.ligerDialog.error("删除邮箱信息失败!" + error.status);
				}
				});
	     }
	 });
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




