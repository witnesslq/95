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
		url:"queryIPSeg.action",
		parms:[{name:'needRoleFilter',value:'true'}],
		height:"98%",
		columns: [
		{ display: 'IP段名称', name: 'name',
				render: function (item){
					if(item.name!=null){
						return "<a href='javascript:void(0);' onclick=\"openmess('"+item.id+"');\">"+item.name+"</a>";
					}
				}
		},
		{ display: '起始IP', name: 'startip'},
		{ display: '终止IP', name: 'endip'},
		{ display: '子网掩码', name: 'netmask'},
		{ display: '所属客户', name: 'customername'},
		{ display: '所属数据中心', name: 'dcname',width:'13%'},
		{ display: '状态', name: 'status',
				render: function (item){
						if (item.status =='01'){
							return '空闲';
						}else if(item.status =='02'){
							return '散租';
						}else if(item.status =='03'){
							return '整租';
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
function  itemmess(){
	var selected = g.getSelected();
	if (!selected) {  top.$.ligerDialog.warn('请选择行'); return; }
	var id = (g.getSelectedRow()).id;  
	var url = "bussiness/sxydidc/ipseg/ipsegView.jsp?id="+id;
	winDetailOpen(url,'IP段信息展示',760,560,'关闭',function(data){});
}

function  openmess(id){
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



