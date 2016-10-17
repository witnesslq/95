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
		height:'98%',
		url:"queryIP.action",
		columns: [
		{ display: 'IP地址', name: 'ipadd',
				render: function (item){
					if(item.ipadd!=null){
						return "<a href='javascript:void(0);' onclick=\"itemmess('"+item.id+"');\">"+item.ipadd+"</a>";
					}
				}
		},
		{ display: '所属网段', name: 'ipsegname'},
		{ display: '状态', name: 'status',
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
		},
		{ display: '所属设备',   name: 'devicename'},
		{ display: '所属客户', name: 'customername'},
		{ display: '所属数据中心', name: 'dcname'}
		],
		pageSize:10,
		root:"listmodel",
		sortname:"sort",
		record:"record",
	 	rownumbers:true, 
	 	title:"IP信息列表页面"
       });
}

function bindSearch(){
	$("#searchBtn").bind("click", function(){
		var value=$("#searchTxt").val();
  		quicksearch(value);
	});
	
	$("#queryBtn").bind("click", function(){
		itemsearch();
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
	var url = "bussiness/sxydidc/ip/ipAdd.jsp";
	winOpen(url,'添加IP信息',760,560,'添加','取消',function(data){
       	$.ajax({
			url:"saveIP.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
       			if("error" == msg.result){
       				top.$.ligerDialog.error("添加IP信息失败!");
       			}else{
	       			g.addRow(msg);
	       			top.$.ligerDialog.success("添加IP信息成功!");
       			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("添加IP信息失败!" + error.status,"错误");
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
	var url = "bussiness/sxydidc/ip/ipEdit.jsp?id="+id;
	winOpen(url,'IP信息编辑',760,560,'保存','取消',function(data){
       	$.ajax({
			url:"updateIP.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
	       		if("error" == msg.result){
	   				top.$.ligerDialog.error("修改IP信息失败!");
	   			}else{
	   				g.updateRow(selected,msg);
       				top.$.ligerDialog.success("修改IP信息成功!","提示");
	   			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("修改IP信息失败!" + error.status,"错误");
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
    			top.$.ligerDialog.warn("预占状态IP地址无法删除!");
    			return; 
    		}else if(row.status=='03'){
    			top.$.ligerDialog.warn("实占状态IP地址无法删除!");
    			return; 
    		}else if(row.status=='04'){
    			top.$.ligerDialog.warn("使用中状态IP地址无法删除!");
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
				url:"deleteIPByIds.action?ids="+idstr, 
				dataType:"json", 
				type:"post",
				success:function (msg) {
		       		if("error"==msg.result){
		   				top.$.ligerDialog.error("删除IP信息失败!");
		   			}else if("02"==msg.result){
		   				top.$.ligerDialog.warn("非空闲状态IP信息无法删除!");
		   			}else if("01"==msg.result){
		   				top.$.ligerDialog.success("删除IP信息成功!");
		   			}
				}, 
				error:function (error) {
					top.$.ligerDialog.error("删除IP信息失败!" + error.status);
				}
				});
	     }
	 });
}

/**
打开添加信息窗口的方法
*/
function  itemsearch(){
	var url = "bussiness/sxydidc/ip/ipSearch.jsp";
	winOpen(url,'IP信息查询',760,560,'查询','重置',function(formData){
		var data =[
				{name:"ip.ipadd",value:formData.ipadd},
				{name:"ip.status",value:formData.status},
				{name:"ip.ipsegid",value:formData.ipsegid},
				{name:"ip.customerid",value:formData.customerid},
				{name:"ip.deviceid",value:formData.deviceid},
				{name:"ip.needDevice",value:true}
		];
		g.setOptions({newPage:1});
		g.setOptions({parms:data});
		g.loadData();
	});
}

/**
打开添加信息窗口的方法
*/
function  itemmess(id){
	var url = "bussiness/sxydidc/ip/ipView.jsp?id="+id;
	winDetailOpen(url,'IP信息展示',760,560,'关闭',function(data){});
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



