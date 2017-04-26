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
        		dictionaryData="[{id:'',text:'请选择'},"+dictionaryData+"]";
			}else{
				dictionaryData="[{id:'',text:'请选择'}]";
			}
		
        }, 
		error:function (error) {
			alert("获取字典信息失败" + error.status);
		}
	});
	return eval("("+dictionaryData+")");
}

function queryRackData(flownumber,Rack){
//alert(Rack);
	var rackData = {Rows: []};
	$.ajax({
		url:"queryBusinessResourceInfoByResourceType.action",
		data:{"flownumber":flownumber,"resourcetp":Rack},
		async:false,
		dataType:"json", 
		type:"post",
		success:function (data) {
        	for (var i = 0; i < data.length; i++){
            	rackData.Rows.push(data[i]);
            }
		}, 
		error:function (error) {
			alert("获取机柜信息失败" + error.status);
		}
	});	
	return rackData;	  
}
function queryRackInfoData(flownumber,id){
alert(id);
var rackData = {Rows: []};
	$.ajax({
		url:"queryBusinessResourceInfoByResourceType.action",
		data:{"flownumber":flownumber,"roomid":id},
		async:false,
		dataType:"json", 
		type:"post",
		success:function (data) {
        	for (var i = 0; i < data.length; i++){
            	rackData.Rows.push(data[i]);
            }
		}
		
	});	
	return rackData;	  
}



/**下拉框网段展示表格*/ 
function queryIPData(flownumber,Ip){
	
	var IPData = {Rows:[]};
	$.ajax({
		url:"queryBusinessResourceInfoByResourceType.action",
		data:{"flownumber":flownumber,"resourcetp":Ip},
		async:false,
		dataType:"json", 
		type:"post",
		success:function (data) {
        	for (var i = 0; i < data.length; i++){
            	IPData.Rows.push(data[i]);
            }
		}, 
		error:function (error) {
			alert("获取IP信息失败" + error.status);
		}
	});	
	//alert(liger.toJSON(IPData));
	return IPData;	  
}
function queryPortData(flownumber,Port,portid){
	
	var IPData = {Rows:[]};
	$.ajax({
		url:"queryBusinessResourceInfoByResourceType.action",
		data:{"flownumber":flownumber,"resourcetp":Port,"resourceid":portid},
		async:false,
		dataType:"json", 
		type:"post",
		success:function (data) {
        	for (var i = 0; i < data.length; i++){
            	IPData.Rows.push(data[i]);
            }
		}, 
		error:function (error) {
			alert("获取端口信息失败" + error.status);
		}
	});	
	return IPData;	  
}
function queryUseatData(flownumber,Useat,rackid){
	var roomData = {Rows: []};
	$.ajax({
		url:"queryBusinessResourceInfoByResourceType.action",
		data:{"flownumber":flownumber,"resourcetp":Useat,"resourceid":rackid},
		async:false,
		dataType:"json", 
		type:"post",
		success:function (data) {
        	for (var i = 0; i < data.length; i++){
            	roomData.Rows.push(data[i]);
            }
		}, 
		error:function (error) {
			alert("获取U位信息失败" + error.status);
		}
	});	
	return roomData;	  
}
function queryNetdevInfoData(flownumber,id){
var rackData = {Rows: []};
	$.ajax({
		url:"queryBusinessResourceInfoByResourceType.action",
		data:{"flownumber":flownumber,"roomid":id},
		async:false,
		dataType:"json", 
		type:"post",
		success:function (data) {
        	for (var i = 0; i < data.length; i++){
            	rackData.Rows.push(data[i]);
            }
		}
		
	});	
	return rackData;	  
}
function queryRoomData(flownumber,Rack){
var rackData = {Rows: []};
	$.ajax({
		url:"queryBusinessResourceInfoByResourceType.action",
		data:{"flownumber":flownumber,"resourcetp":Rack},
		async:false,
		dataType:"json", 
		type:"post",
		success:function (data) {
        	for (var i = 0; i < data.length; i++){
            	rackData.Rows.push(data[i]);
            }
		}
		
	});	
	return rackData;	  
}