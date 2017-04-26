/**下拉框客户展示表格*/ 

function queryCustomerData(data){
	if(data==null||data==''){
		data=[{name:"",value:""}];
	}
	var options = {
    	url: "queryAllCustomer.action",
    	parms:data,
        checkbox: false,
		columns: [
			{ display: '客户编号', name: 'no',isSort:true},
			{ display: '客户名称', name: 'name'},
			{ display: '客户类型', name: 'type',
						render: function (item){
							if (item.type =='01'){
								return '集团客户';
							}else if(item.type =='02'){
								return '自有业务';
							}else if(item.type =='03'){
								return '互联网客户';
							} 		
						}
			}
		], 
        pageSize: 10,
		rownumbers:true,
		root:"listmodel",
		record:"record"                
     };
    return options;
}

/**下拉框客户展示表格*/ 

function queryOwnSysInfoData(data){
	if(data==null||data==''){
		data=[{name:"",value:""}];
	}
	var options = {
    	url: "queryOwnSysInfo.action",
    	parms:data,
        checkbox: false,
		columns: [
		{ display: '系统编码', name: 'syscode'},
		{ display: '系统名称', name: 'sysname'},
		{ display: '所属部门',   name: 'deptname'},
		{ display: '业务负责人', name: 'manager'}
		], 
        pageSize: 10,
		rownumbers:true,
		root:"listmodel",
		record:"record"                
     };
    return options;
}

/**查询数据字典*/
function queryDictionary(dtype,dkey){
	var dictionaryData="";
	$.ajax({
		url:"dictionaryQuery.action",
		data:{"dtype":dtype,"dkey":dkey,"page":1,"pagesize":999},
		async:false,
		dataType:"json", 
		type:"post",
		success:function (data) {
			var row;
			if(data.listmodel!=null){
        		for (var i = 0; i < data.listmodel.length; i++){
        			if(i!=data.listmodel.length-1){
        				row="{id:'"+data.listmodel[i].dkey+"',"+"text:'"+data.listmodel[i].dvalue+"'},";
        			}else{
        				row="{id:'"+data.listmodel[i].dkey+"',"+"text:'"+data.listmodel[i].dvalue+"'}";
        			}
            		dictionaryData=dictionaryData+row;
            	}
        		dictionaryData="["+dictionaryData+"]";
			}else{
			}
		
        }, 
		error:function (error) {
			alert("获取字典信息失败" + error.status);
		}
	});
	return eval("("+dictionaryData+")");
}


/**根据公司ID查询用户数据数据*/
function queryUserData(corpId){
            var options = {
            	url: "UserQueryList.action",
            	parms:[{name:"corpid",value:corpId}], 
            	checkbox: true,
				columns: [
					{ display: 'ID', name: 'id', align: 'left',minWidth: 10,width: 10,hide:true,isAllowHide:false},
					{ display: '登陆名', name: 'loginname', minWidth:30 ,width:100,isSort:true},
					{ display: '用户姓名', name: 'username', minWidth: 30 ,width:100,isSort:true},
					{ display: '性别', name: 'sex', minWidth: 20 ,width:80,
						render: function (item){
							if (item.sex == 'M'){ 
								return '男';
							}else{
								return '女';
							} 
		        		}
					},
					{ display: '手机', name: 'mobileprivate', minWidth: 110 ,width:100,isSort:true},
					{ display: '邮箱', name: 'emailprivate', minWidth: 120 ,width:200,isSort:true},
					{ display: '备注', name: 'remark', minWidth: 150,width:200 }
				], 
                pageSize: 10,
				rownumbers:true,
				root:"listmodal",
				record:"record"                
             };
             return options;
             
}

/**下拉框机柜展示表格*/ 
function queryRackData(data){
	if(data==null||data==''){
		data=[{name:"",value:""}];
	}
	var options = {
    	url: "queryRack.action",
    	parms:data,
        checkbox: false,
		columns: [
			{ display: '机架名称', name: 'name',isSort:true},
			{ display: '机架编码', name: 'code'},
			{ display: '机架规格', name: 'typeid',			
				render: function (item){
						if (item.typeid =='42'){
							return '42U';
						}else if(item.typeid=='46'){
							return '46U';
						}
				}
			},
			{ display: 'U位数量', name: 'ucount'},
			{ display: '所属机房', name: 'roomName'},
			{ display: '所属客户', name: 'customerName'}
		], 
        pageSize: 10,
		rownumbers:true,
		root:"listmodel",
		record:"record"                
     };
    return options;
}

function  queryUseatData(data){
	if(data==null||data==''){
		data=[{name:"",value:""}];
	}
	var options = {
		url:"queryUSeat.action",
		parms:data,
		checkbox: false,
		columns: [
			{ display: 'U位序号', name: 'no'},
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
			},
			{ display: '所属机架', name: 'rackName'}
		],
		pageSize:10,
		root:"listmodel",
		sortname:"no",
		record:"record",
	 	rownumbers:true, 
	 	title:"U位信息列表页面"
	};
	return options;
}


/**下拉框房间展示表格*/ 
function queryHouseData(data){
	if(data==null||data==''){
		data=[{name:"",value:""}];
	}
	var options = {
    	url: "queryHouse.action",
    	parms:data,
        checkbox: false,
		columns: [
			{ display: '房间名称', name: 'housename',isSort:true},
			{ display: '房间编码', name: 'housecode'},
			{ display: '工位数量', name: 'seatcount'},
			{ display: '所属区域', name: 'areaName'},
			{ display: '房间状态', name: 'status',
				render: function (item){
					if (item.status =='01'){
						return '空闲';
					}else if(item.status =='02'){
						return '使用中';
					} 		
				}
			}
		], 
        pageSize: 10,
		rownumbers:true,
		root:"listmodel",
		record:"record"                
     };
    return options;
}
/**下拉框机房展示表格*/ 
function queryRoomData(data){
	if(data==null||data==''){
		data=[{name:"",value:""}];
	}
	
	var options = {
    	url: "queryRoom.action",
    	parms:data,
        checkbox: false,
		columns: [
			{ display: '机房编码', name: 'roomcode',width:150,isSort:true},
			{ display: '机房名称', name: 'roomname',width:150},
			{ display: '机房级别', name: 'grade',
				render: function (item){
					if (item.grade =='01'){
						return 'A';
					}else if(item.grade =='02'){
						return 'B';
					}else if(item.grade =='03'){
						return 'C';
					}
				}
			}
		], 
        pageSize: 10,
		rownumbers:true,
		root:"listmodel",
		record:"record"                
     };
    return options;
}

/**下拉框网段展示表格*/ 
function queryIPSegData(data){
	if(data==null||data==''){
		data=[{name:"",value:""}];
	}
	var options = {
    	url: "queryIPSeg.action",
    	parms:data, 
        checkbox: false,
		columns: [
			{ display: 'IP段名称', name: 'name',isSort:true},
			{ display: '起始IP', name: 'startip'},
			{ display: '终止IP', name: 'endip'},
			{ display: '子网掩码', name: 'netmask'},
			{ display: '网关IP', name: 'gatewayip'},
			{ display: '所属客户', name: 'customername'}
		], 
        pageSize: 10,
		rownumbers:true,
		root:"listmodel",
		record:"record"                
     };
    return options;
	
}

/**下拉框IP地址展示表格*/ 
function queryIPData(data){
	if(data==null||data==''){
		data=[{name:"",value:""}];
	}
	var options = {
    	url: "queryIP.action",
    	parms:data,
        checkbox: false,
		columns: [
			{ display: 'IP地址', name: 'ipadd'},
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
			}
		], 
        pageSize: 10,
		rownumbers:true,
		root:"listmodel",
		record:"record"                
     };
    return options;
	
}

/**下拉框设备展示表格*/ 

function queryDeviceData(data){
	if(data==null||data==''){
		data=[{name:"",value:""}];
	}
	var options = {
    	url: "queryDevice.action",
    	parms:data,
        checkbox: false,
		columns: [
			{ display: '编码', name: 'code'},
			{ display: '设备名称', name: 'name'},
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
						}
				}
			},
			{ display: '所属机房', name: 'roomname'}
		], 
        pageSize: 10,
		rownumbers:true,
		root:"listmodel",
		record:"record"                
     };
    return options;
}

/**查询区域名称**/
function queryAreaData(data){
	var options = {
    	url: "areaQueryList.action",
        checkbox: false,
		columns: [
			{ display: '区域名称', name: 'areaname'},
			{ display: '备注', name: 'remark'}
		], 
        pageSize: 10,
		rownumbers:true,
		root:"listmodal",
		record:"record"                
     };
    return options;
}

/**
 * 选择客户经理 
 */
function selectManager(){
            var options = {
            	url: "UserQueryList.action",
            	checkbox: true,
				columns: [
					{ display: 'ID', name: 'id', align: 'left',minWidth: 10,width: 10,hide:true,isAllowHide:false},
					{ display: '登陆名', name: 'loginname', minWidth:30 ,width:100,isSort:true},
					{ display: '用户姓名', name: 'username', minWidth: 30 ,width:100,isSort:true},
					{ display: '性别', name: 'sex', minWidth: 20 ,width:80,
						render: function (item){
							if (item.sex == 'M'){ 
								return '男';
							}else{
								return '女';
							} 
		        		}
					},
					{ display: '手机', name: 'mobileprivate', minWidth: 110 ,width:100,isSort:true},
					{ display: '邮箱', name: 'emailprivate', minWidth: 120 ,width:200,isSort:true},
					{ display: '备注', name: 'remark', minWidth: 150,width:200 }
				], 
                pageSize: 10,
				rownumbers:true,
				root:"listmodal",
				record:"record"                
             };
             return options;			
}

/**生成编码函数**/
var code;
function createCode(codeType){
    $.ajax({
		url:"createCode.action", 
		data:{"codeType":codeType},
		dataType:"json",
		type:"post",
		async:false,
		success:function (msg) {
			code=msg.result;
		}, 
		error:function (error) {
			top.$.ligerDialog.error("获取编码失败!" + error.status,"错误");
		}
	});
    return code;
}

function winOpen(url,title,parms,width,height,button1,button2,callback){
	window.top.$.ligerDialog.open({
		width: width, 
		height: height, 
		url: url, 
		title: title,
        data: {
           name: parms==null?new Array():parms
        },
		buttons: [{
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
		}]
	});
}
/**
 * 供详情页面调用方法
 */
function winDetailOpen(url,title,width,height,button,callback){
	window.top.$.ligerDialog.open({
		width: width, height: height, url: url, title: title, buttons: [{
			text: button, onclick: function (item, dialog) {
				dialog.close();
			}
		}]
     });
}


function  getSelectedProductType(id,dcid){
	var m = $("#"+id).ligerComboBox({
		width : 180,
		url:"SelectedProductType.action?dcid="+dcid,
		valueField: 'id',
		selectBoxWidth: 180,
		onSelected:function(value,text){
			var typeid = text;
			var id = value;
			var dcid = $("#dcid").val();
			
			if("" != id){
			$.ajax({
				url:"queryProBytypeid.action?dcid="+dcid+"&id="+id, 
				type:"post",
				dataType:"json",
				success:function (data) {
				liger.get("producttype").setDisabled();
				liger.get("productbigtype").setDisabled();
				liger.get("productsmalltype").setDisabled();	
	       			var data2 = g.getData();
					if(data2){
						var rowArr = [];
						for(var i=0;i<data2.length;i++){
							rowArr.push(g.getRow(i));
						}
						g.deleteRange(rowArr);
					}
	       			g.addRows(data);
	       			liger.get("producttype").setEnabled();
	       			liger.get("productbigtype").setEnabled();
	       			liger.get("productsmalltype").setEnabled();
				}, 
				error:function (error) {
					top.$.ligerDialog.error("获取信息失败!" + error.status,"错误");
				}
				});
			}
			if("" != typeid){
				changebigtype(id);
			}
		}
	}); 
}
function  getSelectedProductBigType(id,dcid){
	 var dcid1 = $("#dcid").val();
	var m = $("#productbigtype").ligerComboBox({
		width : 180,
		url:"SelectedProductBigType.action?dcid="+dcid1+"&id="+id, 
		//data:{"id":id},
		//async:false,
		//dataType:"json", 
		//type:"post",
		valueField: 'id',
		selectBoxWidth: 180,
		onSelected:function(value,text){
		var typeid = text;
			var id = value;
			var dcid = $("#dcid").val();
			if("" != id){
			$.ajax({
				url:"queryProBytypeid.action?dcid="+dcid+"&id="+id, 
				type:"post",
				dataType:"json",
				success:function (data) {
				liger.get("producttype").setDisabled();
				liger.get("productbigtype").setDisabled();
				liger.get("productsmalltype").setDisabled();
	       			var data2 = g.getData();
					if(data2){
						var rowArr = [];
						for(var i=0;i<data2.length;i++){
							rowArr.push(g.getRow(i));
						}
						g.deleteRange(rowArr);
					}
	       			g.addRows(data);
	       			liger.get("producttype").setEnabled();
	       			liger.get("productbigtype").setEnabled();
	       			liger.get("productsmalltype").setEnabled();
				}, 
				error:function (error) {
					top.$.ligerDialog.error("获取信息失败!" + error.status,"错误");
				}
				});
			}
			if("" != typeid){
				changesmalltype(id);
			}
		
		}
	}); 
	
}
function  getSelectedProductSmallType(id){
	var dcid1 = $("#dcid").val();
	var m = $("#productsmalltype").ligerComboBox({
		width : 180,
		url:"SelectedProductSmallType.action?dcid="+dcid1+"&id="+id, 
		//data:{"id":id},
		//async:false,
		//dataType:"json", 
		//type:"post",
		valueField: 'id',
		selectBoxWidth: 180,
		onSelected:function(value,text){
		var typeid = text;
			var id = value;
			var dcid = $("#dcid").val();
			if("" != id){
			$.ajax({
				url:"queryProBytypeid.action?dcid="+dcid+"&id="+id, 
				type:"post",
				dataType:"json",
				success:function (data) {
				liger.get("producttype").setDisabled();
				liger.get("productbigtype").setDisabled();
				liger.get("productsmalltype").setDisabled();
	       			var data2 = g.getData();
					if(data2){
						var rowArr = [];
						for(var i=0;i<data2.length;i++){
							rowArr.push(g.getRow(i));
						}
						g.deleteRange(rowArr);
					}
	       			g.addRows(data);
	       			liger.get("producttype").setEnabled();
	       			liger.get("productbigtype").setEnabled();
	       			liger.get("productsmalltype").setEnabled();
				}, 
				error:function (error) {
					top.$.ligerDialog.error("获取信息失败!" + error.status,"错误");
				}
				});
			}
		}
	}); 

}
/**机房行*/ 
function queryrowno(room_id){
	var returnData="";
	$.ajax({
		url:"queryrowno.action",
		data:{"room_id":room_id},
		async:false,
		dataType:"json", 
		type:"post",
		success:function (data) {	
			for(var i=data.rowcount;i>0;i--){
				if((data.rowcount-i+1)<data.rowcount){
			       row="{id:'"+(data.rowcount-i+1)+"',"+"text:'"+(data.rowcount-i+1)+"'},";
			    }else{
			       row="{id:'"+(data.rowcount-i+1)+"',"+"text:'"+(data.rowcount-i+1)+"'}";
			    }
				returnData=returnData+row;
			 }
			console.log(returnData);
			returnData="[{id:'',text:'请选择'},"+returnData+"]";
        }, 
		error:function (error) {
			alert("获取行信息失败" + error.status);
		}
	});
	return eval("("+returnData+")");
}

/**机房列*/ 
function querycolno(room_id){
	var returnData="";
	$.ajax({
		url:"queryrowno.action",
		data:{"room_id":room_id},
		async:false,
		dataType:"json", 
		type:"post",
		success:function (data) {	
			for(var i=data.colcount;i>0;i--){
				if((data.colcount-i+1)<data.colcount){
			       row="{id:'"+(data.colcount-i+1)+"',"+"text:'"+(data.colcount-i+1)+"'},";
			    }else{
			       row="{id:'"+(data.colcount-i+1)+"',"+"text:'"+(data.colcount-i+1)+"'}";
			    }
				returnData=returnData+row;
			 }
			console.log(returnData);
			returnData="[{id:'',text:'请选择'},"+returnData+"]";
        }, 
		error:function (error) {
			alert("获取列信息失败" + error.status);
		}
	});
	return eval("("+returnData+")");
}
