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
		checkbox: true,
		height:'98%',
		url:"queryIPSeg.action",
		columns: [
		{ display: 'IP段名称', name: 'name',width:'12%',
				render: function (item){
					if(item.name!=null){
						return "<a href='javascript:void(0);' onclick=\"itemmess('"+item.id+"');\">"+item.name+"</a>";
					}
				}
		},
		{ display: '起始IP', name: 'startip'},
		{ display: '终止IP', name: 'endip'},
		{ display: '子网掩码', name: 'netmask'},
		{ display: '所属客户', name: 'customername'},
		{ display: '所属数据中心', name: 'dcname'},
		{ display: '状态', name: 'status',
				render: function (item){
						if (item.status =='01'){
							return '空闲';
						}else if(item.status =='02'){
							return '散租';
						}else if(item.status =='03'){
							return '整租';
						}else if(item.status =='04'){
							return '预占';
						}
				}
		},
		{ display: '总IP数', name: 'totalIP'},
		{ display: '空闲IP数', name: 'freeIP'},
		{ display: '预占IP数', name: 'preIP'},
		{ display: '实占IP数', name: 'factIP'}
		],
		pageSize:10,
		root:"listmodel",
		sortname:"no",
		record:"record",
	 	rownumbers:true, 
	 	title:"IP段信息列表页面"
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
	
	
	$("#splitBtn").bind("click", function(){
		itemsplit();
	});
	
	$("#mergeBtn").bind("click", function(){
		itemmerge();
	});
	
	$("#queryBtn").bind("click", function(){
		itemsearch();
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

function itemsplit(){
	var selected = g.getSelected();
	if (!selected) {  
		top.$.ligerDialog.warn('请选择行'); 
		return; 
	}else{
		var row=g.getSelectedRow();
    	if(row.preIP!='0'){
    		top.$.ligerDialog.warn("有预占状态IP地址的网段无法进行拆分!");
    		return; 
    	}else if(row.factIP!='0'){
    		top.$.ligerDialog.warn("有实占状态IP地址的网段无法进行拆分!");
    		return; 
    	}
	}
	
	var id = (g.getSelectedRow()).id;  
	var url = "ipsegSplit.jsp?id="+id;
	$.ligerDialog.open({
		title:'IP段拆分操作',
		url: url,
		height:400,
		width:760,
		buttons: [
			{
				text: '确定', 
				onclick: function (item, dialog){
					var fn = dialog.frame.datePost || dialog.frame.window.datePost;
					var data = fn();
					dialog.close();
					addIPSeg(data);
				} 
			},
			{ 
				text: '取消',
				onclick: function (item, dialog){ 
					dialog.close(); 
				} 
			}
		] 
	});
}

/**
 * 批量添加网段
*/
function addIPSeg(data){
	$.ajax({
		url:"addBatchIPSeg.action", 
		data:{"splitArray":JSON.stringify(data.splitArray)},
		dataType:"json", 
		type:"post",
		success:function (msg) {
       		if("error" == msg.result){
       			top.$.ligerDialog.error("拆分IP网段信息失败!");
       		}else{
	       		top.$.ligerDialog.success("拆分IP网段信息成功!");
	       		my_initGrid();
       		}
		}, 
		error:function (error) {
			top.$.ligerDialog.error("拆分IP网段信息失败!" + error.status,"错误");
		}
	});
}

function itemmerge(){
	var selected = g.getSelected();
    if (!selected) {  
    	top.my_alert('请选择要合并的数据!',"warn"); 
    	return; 
    }else{
    	var rows=g.getSelectedRows();
    	if(rows.length<2){
    		top.my_alert('请至少选择2条以上数据进行合并!',"warn"); 
    		return;
    	}else{
    		for(var i=0;i<rows.length;i++){
    			var row=rows[i];
    			if(row.preIP!='0'){
    				top.$.ligerDialog.warn("有预占状态IP地址的网段无法进行拆分!");
    				return; 
    			}else if(row.factIP!='0'){
    				top.$.ligerDialog.warn("有实占状态IP地址的网段无法进行拆分!");
    				return; 
    			}
    		}
    	}

	}
    window.top.$.ligerDialog.confirm("确定选择合并的数据", "提示", function (ok) {
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
				合并数据库数据
			 */
			$.ajax({
				url:"mergeIPSegByIds.action?ids="+idstr, 
				dataType:"json", 
				type:"post",
				success:function (msg) {
		       		if("error"==msg.result){
		   				top.$.ligerDialog.error("合并IP段信息失败!");
		   				return; 
		   			}else{
		   				top.$.ligerDialog.success("合并IP段信息成功!");
		   				my_initGrid();
		   				return; 
		   				 
		   			}
				}, 
				error:function (error) {
					top.$.ligerDialog.error("合并IP段信息失败!" + error.status);
					return; 
				}
				});
	     }
	 });
}

/**
打开添加信息窗口的方法
*/
function  itemadd(){
	var url = "bussiness/sxydidc/ipseg/ipsegAdd.jsp";
	winOpen(url,'添加IP段信息',760,560,'添加','取消',function(data){
       	$.ajax({
			url:"saveIPSeg.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
       			if("error" == msg.result){
       				top.$.ligerDialog.error("添加IP段信息失败!");
       			}else{
	       			g.addRow(msg);
	       			top.$.ligerDialog.success("添加IP段信息成功!");
       			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("添加IP段信息失败!" + error.status,"错误");
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
	var url = "bussiness/sxydidc/ipseg/ipsegEdit.jsp?id="+id;
	winOpen(url,'IP段信息编辑',760,560,'保存','取消',function(data){
       	$.ajax({
			url:"updateIPSeg.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
	       		if("error" == msg.result){
	   				top.$.ligerDialog.error("修改IP段信息失败!");
	   			}else{
	   				g.updateRow(selected,msg);
       				top.$.ligerDialog.success("修改IP段信息成功!","提示");
	   			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("修改IP段信息失败!" + error.status,"错误");
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
    			top.$.ligerDialog.warn("散租状态网段无法删除!");
    			return; 
    		}else if(row.status=='03'){
    			top.$.ligerDialog.warn("整租状态网段无法删除!");
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
				url:"deleteIPSegByIds.action?ids="+idstr, 
				dataType:"json", 
				type:"post",
				success:function (msg) {
		       		if("error"==msg.result){
		   				top.$.ligerDialog.error("删除网段信息失败!");
		   			}else if("02"==msg.result){
		   				top.$.ligerDialog.warn("网段下有非空闲状态IP地址无法删除!");
		   			}else if("01"==msg.result){
		   				top.$.ligerDialog.success("删除网段信息成功!");
		   			}
				}, 
				error:function (error) {
					top.$.ligerDialog.error("删除IP段信息失败!" + error.status);
				}
				});
	     }
	 });
}

/**
打开添加信息窗口的方法
*/
function  itemsearch(){
	var url = "bussiness/sxydidc/ipseg/ipsegSearch.jsp";
	winOpen(url,'IP段信息查询',760,560,'查询','重置',function(formData){
		var data =[
			{name:"ipseg.name",value:formData.name},
			{name:"ipseg.startip",value:formData.startip},
			{name:"ipseg.endip",value:formData.endip},
			{name:"ipseg.netmask",value:formData.netmask},
			{name:"ipseg.gatewayip",value:formData.gatewayip},
			{name:"ipseg.customerid",value:formData.customerid},
			{name:"ipseg.status",value:formData.status},
			{name:"ipseg.areaid",value:formData.areaid},
			{name:"ipseg.vlanno",value:formData.vlanno},
			{name:"ipseg.dns1",value:formData.dns1},
			{name:"ipseg.dns2",value:formData.dns2}];
		g.setOptions({newPage:1});
		g.setOptions({parms:data});
		g.loadData();
	});
}

/**
打开添加信息窗口的方法
*/
function  itemmess(id){ 
	var url = "bussiness/sxydidc/ipseg/ipsegView.jsp?id="+id;
	winDetailOpen(url,'IP段信息展示',760,560,'关闭',function(data){});
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



