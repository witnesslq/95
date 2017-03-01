$(function($){
    my_initGrid();
    bindSearch();
});
/**
初始化产品信息表格
CustomersData：要填充的数据
*/
function my_initGrid(){
	window['g']=$("#maingrid").ligerGrid({
		checkbox: false,
		url:"queryUSeat.action",
		height:'98%', 
		columns: [
			{ display: '所属机房', name: 'roomName'},
			{ display: '所属机架', name: 'rackName'},
			{ display: 'U位序号', name: 'no'},
			{ display: '放置设备', name: 'deviceName',
				render: function (item){
					if(item.deviceName!=null){
						return "<a href='javascript:void(0);' onclick=\"showDevice('"+item.deviceid+"');\">"+item.deviceName+"</a>";
					}
				}
			},
			{ display: '所属客户', name: 'customerName'},
			{ display: 'U位状态', name: 'status',
				render: function (item){
						if (item.status =='01'){
							return '空闲';
						}else if(item.status =='02'){
							return '预占';
						}else if(item.status =='03'){
							return '实占';
						}else if(item.status =='04'){
							return '使用中';
						}else{
							return '未知';
						}
				}
			}
		],
		pageSize:10,
		root:"listmodel",
		sortname:"no",
		record:"record",
	 	rownumbers:true, 
	 	title:"U位信息列表页面"
       });
}

function bindSearch(){
	$("#queryBtn").bind("click", function(){
		itemsearch();
	});
}

/**
打开添加信息窗口的方法
*/
function  itemadd(){
	var url = "bussiness/sxydidc/useat/useatAdd.jsp";
	winOpen(url,'添加U位信息',760,560,'添加','取消',function(data){
       	$.ajax({
			url:"saveUSeat.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
       			if("error" == msg.result){
       				top.$.ligerDialog.error("添加U位信息失败!");
       			}else{
	       			g.addRow(msg);
	       			top.$.ligerDialog.success("添加U位信息成功!");
       			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("添加U位信息失败!" + error.status,"错误");
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
	var url = "bussiness/sxydidc/useat/useatEdit.jsp?id="+id;
	winOpen(url,'U位信息编辑',760,560,'保存','取消',function(data){
       	$.ajax({
			url:"updateUSeat.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
	       		if("error" == msg.result){
	   				top.$.ligerDialog.error("修改U位信息失败!");
	   			}else{
	   				g.updateRow(selected,msg);
       				top.$.ligerDialog.success("修改U位信息成功!","提示");
	   			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("修改U位信息失败!" + error.status,"错误");
		}});
	});
	
}


/**
打开添加信息窗口的方法
*/
function  itemdelete(){
	var selected = g.getSelected();
    if (!selected) {  
    	top.my_alert('请选择要删除的数据行!',"warn"); 
    	return;
    }else{
    	var rows=g.getSelectedRows();
    	for(var i=0;i<rows.length;i++){
    		var row=rows[i];
    		if(row.status=='02'){
    			top.$.ligerDialog.warn("预占状态U位无法删除!");
    			return; 
    		}else if(row.status=='03'){
    			top.$.ligerDialog.warn("实占状态U位无法删除!");
    			return; 
    		}else if(row.status=='04'){
    			top.$.ligerDialog.warn("使用状态U位无法删除!");
    			return; 
    		}
    	}
    }
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
				url:"deleteUSeatByIds.action?ids="+idstr, 
				dataType:"json", 
				type:"post",
				success:function (msg) {
		       		if("error"==msg.result){
		   				top.$.ligerDialog.error("删除U位信息失败!");
		   			}else{
		   				top.$.ligerDialog.success("删除U位信息成功!");
		   			}
				}, 
				error:function (error) {
					top.$.ligerDialog.error("删除U位信息失败!" + error.status);
				}
				});
	     }
	 });
}

/**
打开添加信息窗口的方法
*/
function  itemsearch(){
	var url = "bussiness/sxydidc/useat/useatSearch.jsp";
	winOpen(url,'U位信息查询',760,560,'查询','重置',function(formData){
		var data =[{name:"useat.roomid",value:formData.roomid},
				   {name:"useat.rackid",value:formData.rackid},
				   {name:"useat.deviceid",value:formData.deviceid},
				   {name:"useat.customerid",value:formData.customerid},
				   {name:"useat.status",value:formData.status}];
		g.setOptions({newPage:1});
		g.setOptions({parms:data});
		g.loadData();
	});
}

/**
 * 模糊查询
 */
function quicksearch(){
	$.ligerDialog.prompt('请输入查询关键字',function(yes,value){ 
		if(yes){
			var data=[{name:'key',value:value}];
			g.setOptions({newPage:1});
			g.setOptions({parms:data});
			g.loadData();		
		}  
	});
}

function showDevice(id){
	var url = "bussiness/sxydidc/device/deviceView.jsp?id="+id;
	winOpen(url,'设备信息展示',760,560,'确定','关闭',function(data){});
	
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



