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
		url:"queryDevice.action",
		parms:[{name:'needRoleFilter',value:'true'}],
		columns: [
		{ display: '编码', name: 'code',width:'13%',
				render: function (item){
					if(item.code!=null){
						return "<a href='javascript:void(0);' onclick=\"openmess('"+item.id+"');\">"+item.code+"</a>";
					}
				}
		},			
		{ display: '设备名称', name: 'name',width:'13%'},
		{ display: '所属数据中心', name: 'dcname',width:'13%'},
		{ display: '所属机房', name: 'roomname'},
		{ display: '所属机架',   name: 'rackname'},
		{ display: 'IP地址', name: 'ipadd',
				render: function (item){
					if(item.ipadd!=null){
						return "<div title='"+item.ipadd+"'>"+item.ipadd+"</div>";
					}else{
						return '';
					}
				}
		},
		{ display: '端口总数', name: 'totalPort',
				render: function (item){
						if (item.totalPort!=''){
							return item.totalPort;
						}else{
							return '0';
						}
				}
		},
		{ display: '空闲端口数', name: 'freePort',
				render: function (item){
						if (item.freePort!=''){
							return item.freePort;
						}else{
							return '0';
						}
				}
		},
		{ display: '实占端口数', name: 'factPort',
				render: function (item){
						if (item.factPort!=''){
							return item.factPort;
						}else{
							return '0';
						}
				}
		},
		{ display: '预占端口数', name: 'prePort',
				render: function (item){
						if (item.prePort!=''){
							return item.prePort;
						}else{
							return '0';
						}
				}
		},
		{ display: '使用端口数', name: 'usedPort',
				render: function (item){
						if (item.usedPort!=''){
							return item.usedPort;
						}else{
							return '0';
						}
				}
		},
		{ display: '资源所有', name: 'owner',
				render: function (item){
						if (item.owner =='1'){
							return '局方设备';
						}else if(item.status =='2'){
							return '客户设备';
						}
				}
		},
		{ display: '查看', name: 'look',
				render: function (item){
					var dataname = '<a id=\''+item.id+'\' href="javascript:void(0)" onclick="openDialog(this);" style="text-decoration:none;color:blue;">设备正视图</a>';
					return dataname;
				}
		}
		],
		pageSize:10,
		root:"listmodel",
		sortname:"no",
		record:"record",
	 	rownumbers:true, 
	 	title:"设备信息列表页面"
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

/**设备正视图*/
function openDialog(obj){
	var id = obj.id;  
	var url = "bussiness/sxydidc/netdevpack/netdevpackforlook.jsp?id="+id;
	window.top.$.ligerDialog.open({
		url: url, title: "网络设备正视图",
		width: 770, height: 560
     });
     return false;
	}

/**
打开添加信息窗口的方法
*/
function  itemsearch(){
	var url = "bussiness/sxydidc/device/deviceSearch.jsp";
	winOpen(url,'设备信息查询',760,560,'查询','重置',function(formData){
		var data =[
			{name:"device.name",value:formData.name},
			{name:"device.code",value:formData.code},
			{name:"device.roomid",value:formData.roomid},
			{name:"device.rackid",value:formData.rackid}
		] ;
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
	var url = "bussiness/sxydidc/device/deviceView.jsp?id="+id;
	winDetailOpen(url,'设备信息展示',760,560,'关闭',function(data){});
}

function  openmess(id){ 
	var url = "bussiness/sxydidc/device/deviceView.jsp?id="+id;
	winDetailOpen(url,'设备信息展示',760,560,'关闭',function(data){});
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

/**
 * 查询设备下的板卡信息
 * @param {Object} row
 * @param {Object} detailPanel
 * @param {Object} callback
 */
	function f_showBoard(row, detailPanel,callback){
		var title="("+row.name+")板卡列表页面";
    	var grid = document.createElement('div'); 
        $(detailPanel).append(grid);
        window['g1']=$(grid).css('margin',5).ligerGrid({
			checkbox: true,
			url:"querypackDeviceById.action",
			parms:[{name:"id",value:row.id}],
        	columns:[
            	{ display: "编码",name: "code"},
            	{ display: "序列号",name: "sn"},
            	{ display: "序号",name: "packno"},
            	{ display: "端口类型",name: "porttype",
					render: function (item){
						if (item.porttype =='01'){
							return '千兆光口';
						}else if(item.porttype =='02'){
							return '百兆电口';
						}
					}
            	},
            	{ display: "端口数量",name: "portcount"},
            	
            ],
			pageSize:10,
			sortname:"portcount",
			record:"record",
			root:"listmodel",
			rownumbers:true, 
	 		title:title, 
            width: '75%',
            columnWidth:150,
			toolbar: { items: [
				{ text: '增加', click: function (item){addBoard(row.id);}, icon: 'add'},
				{ line: true },
  				{ text: '编辑', click: editBoard, icon: 'edit'},
  				{ line: true },
  				{ text: '删除', click: delBoard, icon: 'delete'},
  				{ line: true },
  				{ text: '板卡端口列表', click: showBoard, icon: 'bookpen'}
  			
  			]}
            
        });
	}

/**
 * 添加板卡
 */
function addBoard(deviceid){
	var url = "bussiness/sxydidc/device/devicepackAdd.jsp?deviceId="+deviceid;
	winOpen(url,'添加设备板卡信息',760,560,'添加','取消',function(data){
       	$.ajax({
			url:"savepackDevice.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
       			if("error" == msg.result){
       				top.$.ligerDialog.error("添加设备板卡信息失败!");
       			}else{
	       			g.addRow(msg);
	       			top.$.ligerDialog.success("添加设备板卡信息成功!");
       			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("添加设备板卡信息失败!" + error.status,"错误");
		}});
	});
}
/**
 * 编辑板卡信息
 */
function editBoard(){
	var selected = g1.getSelected();
	if (!selected) {  top.$.ligerDialog.warn('请选择行'); return; }
	var id = (g1.getSelectedRow()).id;  
	var url = "bussiness/sxydidc/device/devicepackEdit.jsp?id="+id;
	winOpen(url,'设备板卡信息编辑',760,560,'保存','取消',function(data){
       	$.ajax({
			url:"updateDevicePack.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
	       		if("error" == msg.result){
	   				top.$.ligerDialog.error("修改设备板卡信息失败!");
	   			}else{
	   				g1.updateRow(selected,msg);
       				top.$.ligerDialog.success("修改设备板卡信息成功!","提示");
	   			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("修改设备板卡信息失败!" + error.status,"错误");
		}});
	});
	
}

/**
 * 板卡信息展示
 */
function showBoard(){
	var selected = g1.getSelected();
	if (!selected) {  top.$.ligerDialog.warn('请选择行'); return; }
	var id = (g1.getSelectedRow()).id;  
	var url = "bussiness/sxydidc/device/devicepackView.jsp?id="+id;
	winOpen(url,'设备板卡信息展示',760,560,'确定','关闭',function(data){});
}


/**
 * 删除板卡
 */
function delBoard(){
	var selected = g1.getSelected();
    if (!selected) {  top.my_alert('请选择要删除的数据行!',"warn"); return; }
    window.top.$.ligerDialog.confirm("确定删除选择的数据", "提示", function (ok) {
	    if (ok) {
	      	g1.deleteSelectedRow();
			var selecteds = g1.getSelecteds();
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
				url:"deleteDevicepackByIds.action?ids="+idstr, 
				dataType:"json", 
				type:"post",
				success:function (msg) {
		       		if("error"==msg.result){
		   				top.$.ligerDialog.error("删除设备板卡信息失败!");
		   			}else if("02"==msg.result){
		   				top.$.ligerDialog.error("该板卡下有正在使用的端口!");
		   			}else if("01"==msg.result){
		   				top.$.ligerDialog.success("删除设备板卡信息成功!");
		   			}
				}, 
				error:function (error) {
					top.$.ligerDialog.error("删除设备板卡信息失败!" + error.status);
				}
			});
	     }
	 });
}


