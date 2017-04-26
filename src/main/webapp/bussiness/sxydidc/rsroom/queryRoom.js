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
		checkbox: false,
		url:"queryRoom.action",
		parms:[{name:'needRoleFilter',value:'true'}],
		height:"98%",
		columns: [
		{ display: '机房编码', name: 'roomcode',width:'13%',
				render: function (item){
					if(item.roomcode!=null){
						return "<a href='javascript:void(0);' onclick=\"openmess('"+item.id+"');\">"+item.roomcode+"</a>";
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
		{ display: '所属数据中心',   name: 'dcname',width:'13%'},
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
function  itemmess(){
	var selected = g.getSelected();
	if (!selected) {  top.$.ligerDialog.warn('请选择行'); return; }
	var id = (g.getSelectedRow()).id;  
	var url = "bussiness/sxydidc/rsroom/rsroomView.jsp?id="+id;
	winDetailOpen(url,'机房信息展示',760,560,'关闭',function(data){});
}

function  openmess(id){ 
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


function checkout(obj){
	var id=obj.id;
	var url = "checkoutRoom.action?id="+id;
	window.top.$.ligerDialog.open({width: 1380, height: 653, url: url, title: "机房信息"});

}


