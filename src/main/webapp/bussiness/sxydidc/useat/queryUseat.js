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
		parms:[{name:'needRoleFilter',value:'true'}],
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
function  itemsearch(){
	var url = "bussiness/sxydidc/useat/useatSearch.jsp";
	winOpen(url,'U位信息查询',760,560,'查询','重置',function(formData){
		var data =[{name:"useat.rackid",value:formData.rackid},
				   {name:"useat.deviceid",value:formData.deviceid},
				   {name:"useat.customerid",value:formData.customerid},
				   {name:"useat.status",value:formData.status},
				   {name:"useat.no",value:formData.no}];
		g.setOptions({newPage:1});
		g.setOptions({parms:data});
		g.loadData();
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



