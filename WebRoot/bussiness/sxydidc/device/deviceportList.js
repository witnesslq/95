$(function($){
    var id=$("#packid").val();
    my_initGrid(id);
    bindSearch();
    
});
/**
初始化产品信息表格
CustomersData：要填充的数据
*/
function my_initGrid(id){
	window['g']=$("#maingrid").ligerGrid({
		checkbox: false,
		url:"queryportByDevpackId.action",
		parms:[{name:"id",value:id},{name:'needRoleFilter',value:'true'}],
		height:'98%',
		columns: [
			{ display: "端口编码",name: "portcode",width:"18%",
				render: function (item){
					if(item.portcode!=null){
						return "<a href='javascript:void(0);' onclick=\"openmess('"+item.id+"');\">"+item.portcode+"</a>";
					}
				}
			},
			{ display: "端口名称",name: "portname"},
            { display: "端口类型",name: "type",
				render: function (item){
					if (item.type =='1'){
						return '百兆电口FE';
					}else if(item.type =='2'){
						return '千兆电口GE/XGE';
					}else if(item.type =='3'){
						return '千兆光口GBIC/SPF/POS';
					}else if(item.type =='4'){
						return '十兆电口';
					}else if(item.type =='5'){
						return '万兆光口XGE';
					}
				}
            },
            { display: "端口状态",name: "status",
				render: function (item){
					if (item.status =='01'){
						return "<div class='showcolor' style='background-color: #15E864'>空闲</div>";
					}else if(item.status =='02'){
						return "<div class='showcolor' style='background-color: #FF9224'>预占</div>";
					}else if(item.status =='03'){
						return "<div class='showcolor' style='background-color: #FFF200'>实占</div>";
					}else if(item.status =='04'){
						return "<div class='showcolor' style='background-color: #FF3333'>使用中</div>";
					}else if(item.status =='05'){
						return "<div class='showcolor' style='background-color: #626C78'>保留</div>";
					}
				}
            },
            { display: "所属板卡",name: "netdevpackcode"},
            { display: "所属设备",name: "devicename"},
            { display: "所属客户",name: "customername"},
            { display: '所属数据中心', name: 'dcname',width:'13%'}
		],
		pageSize:10,
		sortname:"portcount",
		record:"record",
		root:"listmodel",
	 	rownumbers:true,
	 	title:"端口信息列表页面"
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
function  itemedit(){
	var selected = g.getSelected();
	if (!selected) {  top.$.ligerDialog.warn('请选择行'); return; }
	var id = (g.getSelectedRow()).id;  
	var url = "bussiness/sxydidc/device/deviceportEdit.jsp?id="+id;
	winOpen(url,'板卡端口信息编辑',760,560,'保存','取消',function(data){
       	$.ajax({
			url:"updateDevicePort.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (msg) {
	       		if("error" == msg.result){
	   				top.$.ligerDialog.error("修改端口信息失败!");
	   			}else{
	   				g.updateRow(selected,msg);
       				top.$.ligerDialog.success("修改端口信息成功!","提示");
	   			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("修改端口信息失败!" + error.status,"错误");
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
				url:"deleteDeviceportByIds.action?ids="+idstr, 
				dataType:"json", 
				type:"post",
				success:function (msg) {
		       		if("error"==msg.result){
		   				top.$.ligerDialog.error("删除端口信息失败!");
		   			}else{
		   				top.$.ligerDialog.success("删除端口信息成功!");
		   			}
				}, 
				error:function (error) {
					top.$.ligerDialog.error("删除端口信息失败!" + error.status);
				}
			});
	     }
	 });
}

/**
打开添加信息窗口的方法
*/
function  itemsearch(){
	var url = "bussiness/sxydidc/device/deviceportSearch.jsp";
	winOpen(url,'端口信息查询',760,560,'查询','重置',function(formData){
		var data =[{name:"portdevice.portname",value:formData.portname},
			{name:"portdevice.portcode",value:formData.portcode},
			{name:"portdevice.type",value:formData.type},
			{name:"portdevice.status",value:formData.status},
			{name:"portdevice.customerid",value:formData.customerid},
			{name:"portdevice.tonetdevid",value:formData.tonetdevid}
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
	var url = "bussiness/sxydidc/device/deviceportView.jsp?id="+id;
	winDetailOpen(url,'端口信息展示',760,560,'关闭',function(data){});
}

function  openmess(id){ 
	var url = "bussiness/sxydidc/device/deviceportView.jsp?id="+id;
	winDetailOpen(url,'端口信息展示',760,560,'关闭',function(data){});
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