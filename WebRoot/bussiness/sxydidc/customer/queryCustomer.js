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
		url:"queryCustomer.action",
		height:"98%",
		columns: [
		{ display: '客户编号', name: 'no',width:100,
				render: function (item){
					if(item.no!=null){
						return "<a href='javascript:void(0);' onclick=\"openmess('"+item.id+"','"+item.type+"');\">"+item.no+"</a>";
					}
				}
		},
		{ display: '客户名称', name: 'name'},
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
		{ display: '开通日期', name: 'createdate'}
		],
		frozenCheckbox:false,
		pageSize:10,
		root:"listmodel",
		sortname:"no",
		record:"record",
	 	rownumbers:true, 
	 	title:"客户信息列表页面"
       });
}

function bindSearch(){
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
function  itemmess(){
	var selected = g.getSelected();
	if (!selected) {  top.$.ligerDialog.warn('请选择行'); return; }
	var id = (g.getSelectedRow()).id;  
	var type=(g.getSelectedRow()).type; 
	var url;
	var title;
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
    		var params;
			if(type=='01'){
				title="集团客户信息展示";
				params="serverCount="+serverCount+"&rackCount="+rackCount+"&useatCount="+useatCount+"&ipsegCount="+ipsegCount+"&ipCount="+ipCount+"&portCount="+portCount;
				url = "bussiness/sxydidc/customer/groupView.jsp?id="+id+"&type="+type+"&"+params;
			}else if(type=='03'){
				title="互联网客户信息展示";
				params="serverCount="+serverCount+"&rackCount="+rackCount+"&useatCount="+useatCount+"&ipsegCount="+ipsegCount+"&ipCount="+ipCount+"&portCount="+portCount;
				url = "bussiness/sxydidc/customer/customerView.jsp?id="+id+"&type="+type+"&"+params;
			}
			winDetailOpen(url,title,760,560,'关闭',function(data){});
		}, 
		error:function (error) {
			top.$.ligerDialog.error("统计资源数量失败!" + error.status,"错误");
		}
	});
}

var serverCount;
var rackCount;
var useatCount;
var ipsegCount;
var ipCount;
var portCount;
var busOrdCount;
var serOrdCount;
var params;
function  openmess(id,type){ 
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
					params="serverCount="+serverCount+"&rackCount="+rackCount+"&useatCount="+useatCount+"&ipsegCount="+ipsegCount+"&ipCount="+ipCount+"&portCount="+portCount+"&portCount="+portCount+"&busOrdCount="+busOrdCount+"&serOrdCount="+serOrdCount+params;
					url = "bussiness/sxydidc/customer/groupView.jsp?id="+id+"&type="+type+"&"+params;
				}else if(type=='03'){
					title="互联网客户信息展示";
					params="serverCount="+serverCount+"&rackCount="+rackCount+"&useatCount="+useatCount+"&ipsegCount="+ipsegCount+"&ipCount="+ipCount+"&portCount="+portCount+"&portCount="+portCount+"&busOrdCount="+busOrdCount+"&serOrdCount="+serOrdCount+params;
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



