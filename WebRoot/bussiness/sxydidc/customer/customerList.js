$(function($){
    my_initGrid();
    bindSearch();
});
/**
初始化客户信息表格
CustomersData：要填充的数据
*/
function my_initGrid(){
	window['g']=$("#maingrid").ligerGrid({
		checkbox: false,
		url:"queryCustomerByUser.action",
		height:"98%",
		columns: [
		{ display: '客户编号', name: 'no',width:'10%',
				render: function (item){
					if(item.no!=null){
						return "<a href='javascript:void(0);' onclick=\"itemmess('"+item.id+"','"+item.type+"');\">"+item.no+"</a>";
					}
				}
		},
		{ display: '客户名称', name: 'name',width:'15%'},
		{ display: '客户类型', name: 'type',
			render: function (item){
				if (item.type =='01'){
					return '集团客户';
				}else if(item.type =='03'){
					return '互联网客户';
				} 		
			}
		},
		{ display: '客户级别', name: 'customerlevel',
			render: function (item){
				return item.customerlevel+"类集团";
			}
		},
		{ display: '客户状态', name: 'status',
			render: function (item){
				if (item.status =='01'){
					return '离线客户';
				}else if(item.status =='02'){
					return '在线客户';
				} 		
			}
		},
		{ display: '客户性质', name: 'customerproperty',
			render: function (item){
				if (item.customerproperty =='A'){
					return '商业客户';
				}else if(item.customerproperty =='B'){
					return '公免客户';
				}else if(item.customerproperty =='C'){
					return 'ISP服务商';
				}else if(item.customerproperty =='D'){
					return 'IDC业务批发商';
				}
			}
		},
		{ display: '客户域级', name: 'customerfield',
			render: function (item){
				if (item.customerfield =='CITYCUST'){
					return '市公司客户';
				}else if(item.customerfield =='PROCUST'){
					return '省公司客户';
				} 		
			}
		},
		{ display: '客户经理', name: 'managername'},
		{ display: '客户经理手机号码', name: 'managerphone'},
		{ display: '联系人',   name: 'contactname'},
		{ display: '联系人手机号码', name: 'mobilephone'},
		{ display: '客户密钥', name: 'custkey'},
		{ display: '开通日期', name: 'createdate'}
		],
		frozenCheckbox:false,
		pageSize:10,
		root:"listmodel",
		sortname:"no",
		record:"record",
	 	rownumbers:false, 
	 	title:"客户信息列表页面"
/*		toolbar: { items: [
   			{ text: '增加', click: itemadd, icon: 'add' },
  			{ line: true },
  			{ text: '查询', click: itemsearch, icon: 'search2'},
  			{ line: true },
  			{ text: '修改客户密钥', click: editkey, icon: 'myaccount'}
  			
  		]}*/
       });
}

function bindSearch(){
	$("#addBtn").bind("click", function(){
		itemadd();
	});
	
	$("#keyBtn").bind("click", function(){
		editkey();
	});
	
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
 * 选择客户类型
 */
function selectCustomerType(){
	$.ligerDialog.open({
		title:'客户类型选择',
		url: 'customerType.jsp',
		height:180,
		buttons: [
			{
				text: '确定', 
				onclick: function (item, dialog){
					var fn = dialog.frame.getCustType || dialog.frame.window.getCustType;
					var custType = fn();
					dialog.close();
					itemadd(custType);
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
打开添加信息窗口的方法
*/
function  itemadd(){
	var url="bussiness/sxydidc/customer/customerAdd.jsp";
	var title="添加互联网客户信息";
	winOpen(url,title,760,560,'添加','取消',function(data){
       	$.ajax({
			url:"saveCustomer.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
       			if("error" == msg.result){
       				top.$.ligerDialog.error("添加客户信息失败!");
       			}else{
	       			g.addRow(msg);
	       			top.$.ligerDialog.success("添加客户信息成功!");
       			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("添加客户信息失败!" + error.status,"错误");
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
	var url;
	var title;
	if(type=='01'){
		title="编辑集团客户信息";
		url = "bussiness/sxydidc/customer/groupEdit.jsp?id="+id+"&type="+type;
	}else if(type=='03'){
		title="编辑互联网客户信息";
		url = "bussiness/sxydidc/customer/customerEdit.jsp?id="+id+"&type="+type;
	}
	
	winOpen(url,title,760,560,'保存','取消',function(data){
       	$.ajax({
			url:"updateCustomer.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
	       		if("error" == msg.result){
	   				top.$.ligerDialog.error("修改客户信息失败!");
	   			}else{
	   				g.updateRow(selected,msg);
       				top.$.ligerDialog.success("修改客户信息成功!","提示");
	   			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("修改客户信息失败!" + error.status,"错误");
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
				url:"deleteCustomerByIds.action?ids="+idstr, 
				dataType:"json", 
				type:"post",
				success:function (msg) {
		       		if("error"==msg.result){
		   				top.$.ligerDialog.error("删除客户信息失败!");
		   			}else{
		   				top.$.ligerDialog.success("删除客户信息成功!");
		   			}
				}, 
				error:function (error) {
					top.$.ligerDialog.error("删除客户信息失败!" + error.status);
				}
				});
	     }
	 });
}

/**
打开添加信息窗口的方法
*/
function  itemsearch(){
	var url = "bussiness/sxydidc/customer/customerSearch.jsp";
	winOpen(url,'客户信息查询',760,380,'查询','重置',function(formData){
		var data =[
			{name:"customer.no",value:formData.no},
			{name:"customer.name",value:formData.name},
			{name:"customer.type",value:formData.type},
			{name:"customer.customerlevel",value:formData.customerlevel},
			{name:"customer.status",value:formData.status},
			{name:"customer.sortname",value:formData.sortname},
			{name:"customer.manager",value:formData.manager},
			{name:"customer.customerproperty",value:formData.customerproperty},
			{name:"customer.customerfield",value:formData.customerfield}
		];
		g.setOptions({newPage:1});
		g.setOptions({parms:data});
		g.loadData();
	});
}


/**
打开添加信息窗口的方法
*/
var serverCount;
var rackCount;
var useatCount;
var ipsegCount;
var ipCount;
var portCount;
var busOrdCount;
var serOrdCount;
var params;
function  itemmess(id,type){
	var url;
	var title;
	params=queryOccuipedResourceCount(id);
	if(params!=null&&params!=''){
    	$.ajax({
			url:"queryOrderResourceCount.action", 
			data:{"order.custid":id},
			dataType:"json", 
			type:"post",
			success:function (msg) {
				serverCount=msg.serverCount;
				rackCount=msg.rackCount;
				useatCount=msg.useatCount;
				ipsegCount=msg.ipsegCount;
				ipCount=msg.ipCount;
				portCount=msg.portCount;
				busOrdCount=msg.busOrdCount;
				serOrdCount=msg.serOrdCount;
				if(type=='01'){
					title="集团客户信息展示";
					params="serverCount="+serverCount+"&rackCount="+rackCount+"&useatCount="+useatCount+"&ipsegCount="+ipsegCount+"&ipCount="+ipCount+"&portCount="+portCount+"&busOrdCount="+busOrdCount+"&serOrdCount="+serOrdCount+params;
					url = "bussiness/sxydidc/customer/groupView.jsp?id="+id+"&type="+type+"&"+params;
				}else if(type=='03'){
					title="互联网客户信息展示";
					params="serverCount="+serverCount+"&rackCount="+rackCount+"&useatCount="+useatCount+"&ipsegCount="+ipsegCount+"&ipCount="+ipCount+"&portCount="+portCount+"&busOrdCount="+busOrdCount+"&serOrdCount="+serOrdCount+params;
					url = "bussiness/sxydidc/customer/customerView.jsp?id="+id+"&type="+type+"&"+params;
				}
				winDetailOpen(url,title,760,560,'关闭',function(data){});
			}, 
			error:function (error) {
				top.$.ligerDialog.error("统计资源数量失败!" + error.status,"错误");
			}
		});
	}

}


var preServerCount;
var preRackCount;
var preUseatCount;
var preIpsegCount;
var preIpCount;
var prePortCount;
function queryOccuipedResourceCount(id){
    $.ajax({
		url:"queryOccuipedResourceCount.action", 
		data:{"customerId":id},
		dataType:"json", 
		type:"post",
		success:function (msg) {
			preServerCount=msg.serverCount;
			preRackCount=msg.rackCount;
			preUseatCount=msg.useatCount;
			preIpsegCount=msg.ipsegCount;
			preIpCount=msg.ipCount;
			prePortCount=msg.portCount;
			params="&preServerCount="+preServerCount+"&preRackCount="+preRackCount+"&preUseatCount="+preUseatCount+"&preIpsegCount="+preIpsegCount+"&preIpCount="+preIpCount+"&prePortCount="+prePortCount;    		
		}, 
		error:function (error) {
			top.$.ligerDialog.error("统计客户预占资源数量失败!" + error.status,"错误");
		}
	});
	return params;
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


function  editkey(){
	var selected = g.getSelected();
    if (!selected) {  top.my_alert('请选择要修改密钥的客户!',"warn"); return; }
    window.top.$.ligerDialog.confirm("确定要修改客户密钥", "提示", function (ok) {
	    if (ok) {
			var selecteds = g.getSelecteds();
			var idstr="";//所有选择行的id
			for(var i=0;i<selecteds.length;i++){
				idstr = idstr + selecteds[i].id;
				if(i!=(selecteds.length-1)){
					idstr = idstr + ",";
				}
			}
			$.ajax({
				url:"editCustomerKey.action?ids="+idstr, 
				dataType:"json", 
				type:"post",
				success:function (msg) {
		       		if("error"==msg.result){
		   				top.$.ligerDialog.error("修改客户密钥失败!");
		   			}else{
		   				top.$.ligerDialog.success("修改客户密钥成功!");
		   				g.loadData();
		   			}
				}, 
				error:function (error) {
					top.$.ligerDialog.error("修改客户密钥失败!" + error.status);
				}
				});
	     }
	 });
}