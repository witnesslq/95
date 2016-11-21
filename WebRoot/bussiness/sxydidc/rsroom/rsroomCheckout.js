$(document).ready(function(){
    $(document).bind("contextmenu",function(e){
          return false;
    });
    $("button[name='adduser']").click(function(){
    	 $("iframe[name='adduser_content']").get(0).contentWindow.submit();
    });
    $("button[name='editInfo']").click(function(){
		$("iframe[name='edit_content']").get(0).contentWindow.submit();   
	});
    $("button[name='addsome']").click(function(){
		$("iframe[name='addsome_content']").get(0).contentWindow.submit();   
	});
    $("button[name='addPillar']").click(function(){
    	addPillarCheck();
	});
    
});

function showinfo(id,unumber,rackNo,rackName,rackModel,rackstuts,belongCustomer,belongRoom,totalU,freeU,disRentU,whlRentU,preU){
	var height = 423 /(unumber-5)+ 'px';
	var height2 = (423 /(unumber-5))-2+ 'px';
	var utable=document.getElementById('unumber_info');
	var tbodies=utable.getElementsByTagName("tbody");
	if(tbodies.length>0){
	utable.removeChild(tbodies[0]);}
	var detable=document.getElementById('show_u');
	var tbodie=detable.getElementsByTagName("tbody");
	if(tbodie.length>0){
	detable.removeChild(tbodie[0]);}
	else{
		return;
	}
	document.getElementById('show_u').style.background='White';
	var rackNo=rackNo;
	$.ajax({
		url:"queryUInfo.action", 
		data:{'id':id},
		dataType:"json", 
		type:"post",
		success:function (msg) {
			if(unumber>0){
				
				var tbody = document.createElement("tbody");
				var tbody2 = document.createElement("tbody");
				for(var i=1;i<=unumber-5;i++){
					
					var row=document.createElement("tr");  
					var cell=document.createElement("td");  
					var note=document.createTextNode(unumber-i+1);
					var note1=document.createTextNode("-");
					if((unumber-i+1)%5==0||i<2||i==(unumber-5)){
						cell.appendChild(note);
						}
					  else{
						cell.appendChild(note1); 
					  }
					row.style.height=height;
					row.style.fontSize = height;
					row.appendChild(cell);	
					tbody.appendChild(row);
					
					var row2=document.createElement("tr");  
					var cell2=document.createElement("td");  
					row2.style.height=height2;
					
					var status="未知";
					  if(msg[i-1].status==01){
						    cell2.style.backgroundColor='#80E6FF';					  
							status="空闲";
							cell2.title=msg[i-1].no+"U位"+status; 
							}else if(msg[i-1].status==02){
							cell2.style.backgroundColor='#8F6EE7';	
							status="预占";
							cell2.title=msg[i-1].no+"U位"+status+"\r所属客户:"+msg[i-1].customerName;
							
							}else if(msg[i-1].status==03){
							cell2.style.backgroundColor='#930185';	
							status="实占";
							cell2.title=msg[i-1].no+"U位"+status+"\r所属客户:"+msg[i-1].customerName;
							
							}else if(msg[i-1].status==04){
							cell2.style.backgroundColor='#5F61E3';	
							status="使用中";
							cell2.title=msg[i-1].no+"U位"+status+"\r所属客户:"+msg[i-1].customerName;
							
							}
					
					row2.appendChild(cell2);	
					tbody2.appendChild(row2);
					
					}	
				utable.appendChild(tbody);
				detable.appendChild(tbody2)
				var deviceinfo="";
				for(var i=0;i<msg.length;i++){
				if(msg[i].deviceid!=""){
					var uno=msg[i].uno;  //所占U位数
					var nonumber=msg[i].nonumber;  //最大U位编号
					var no=msg[i].no; //U位编号
					var cell=detable.rows[i].cells[0];
					var devicetype=msg[i].devicetype;//设备类型  01 网络设备 02：服务器
					if(uno==1){
						var status="未知";
						  if(msg[i].status==01){
								status="空闲";
								}else if(msg[i].status==02){
								status="预占";
								}else if(msg[i].status==03){
								status="实占";
								}else if(msg[i].status==04){
								status="使用中";
								}
						var startU=msg[i].nonumber-msg[i].uno+1;
						deviceinfo=	msg[i].deviceid;
						if(devicetype==01){  // 01 网络设备
						cell.title="名称:"+msg[i].deviceName+"\r型号:"+msg[i].model+"\r运行状态:"+status+"\r占用ip:"+msg[i].ip+"\r起止U位:"+startU+"~"+msg[i].nonumber;			
						cell.innerHTML="<img src='images/rsroom/switch.jpg' width='148' height="+(423*uno/(unumber-5)-2)+" width='148' ondblclick='switchinfo(\""+deviceinfo+"\")'/>";
						}else{
							cell.title="名称:"+msg[i].deviceName+"\r型号:"+msg[i].model+"\r运行状态:"+status+"\r所属客户:"+msg[i].customerName+"\r占用ip:"+msg[i].ip+"\r上联端口:"+msg[i].port+"\r起止U位:"+startU+"~"+msg[i].nonumber;		
							cell.innerHTML="<img src='images/rsroom/server.jpg' width='148' height="+(423*uno/(unumber-5)-2)+" width='148' ondblclick='deinfo(\""+deviceinfo+"\")'/>";			
						}
					}
					else if(uno>1){
						
						 if(deviceinfo==""){
								for(var j=1;j<uno;j++){
									detable.rows[i+j].deleteCell(0);
									detable.rows[i+j].title="";
									              }
									detable.rows[i].cells[0].rowSpan=uno;
									
									var status="未知";
									  if(msg[i].status==01){
											status="空闲";
											}else if(msg[i].status==02){
											status="预占";
											}else if(msg[i].status==03){
											status="实占";
											}else if(msg[i].status==04){
											status="使用中";
											}
									  
									var runstatus="未知";
									if(msg[i].runstatus==01){
										runstatus="运行"
									}
									var startU=msg[i].nonumber-msg[i].uno+1;
									if(devicetype==01){  // 01 网络设备
									detable.rows[i].cells[0].title="名称:"+msg[i].deviceName+"\r型号:"+msg[i].model+"\r运行状态:"+status+"\r占用ip:"+msg[i].ip+"\r起止U位:"+startU+"~"+msg[i].nonumber  ;
									deviceinfo=	msg[i].deviceid;
									detable.rows[i].cells[0].innerHTML="<img src="+whitchimage(uno)+" width='148' height="+(423*uno/(unumber-5)-2)+" ondblclick='switchinfo(\""+deviceinfo+"\")'/>";
									}else{
										detable.rows[i].cells[0].title="名称:"+msg[i].deviceName+"\r型号:"+msg[i].model+"\r运行状态:"+status+"\r所属客户:"+msg[i].customerName+"\r占用ip:"+msg[i].ip+"\r上联端口:"+msg[i].port+"\r起止U位:"+startU+"~"+msg[i].nonumber  ;
										deviceinfo=	msg[i].deviceid;
										detable.rows[i].cells[0].innerHTML="<img src='images/rsroom/server.jpg' width='148' height="+(423*uno/(unumber-5)-2)+" ondblclick='deinfo(\""+deviceinfo+"\")'/>";
									}
						   }else {
		
							   if(deviceinfo!=msg[i].deviceid&&msg[i].deviceid!=""){
							   for(var j=1;j<uno;j++){
									detable.rows[i+j].deleteCell(0);
									detable.rows[i+j].title="";
									              }
									detable.rows[i].cells[0].rowSpan=uno;
								
									deviceinfo=	msg[i].deviceid;
									var status="未知";
									  if(msg[i].status=="01"){
											status="空闲";
											}else if(msg[i].status==02){
											status="预占";
											}else if(msg[i].status==03){
											status="实占";
											}else if(msg[i].status==04){
											status="使用中";
											}
									  
									var runstatus="未知";
									if(msg[i].runstatus==01){
										runstatus="运行"
									}
									var startU=msg[i].nonumber-msg[i].uno+1;
									if(devicetype==01){  // 01 网络设备
									detable.rows[i].cells[0].title="名称:"+msg[i].deviceName+"\r型号:"+msg[i].model+"\r运行状态:"+status+"\r占用ip:"+msg[i].ip+"\r起止U位:"+startU+"~"+msg[i].nonumber ;	
									detable.rows[i].cells[0].innerHTML="<img src="+whitchimage(uno)+" width='148' height="+(423*uno/(unumber-5)-2)+" ondblclick='switchinfo(\""+deviceinfo+"\")'/>";
									}else{
										detable.rows[i].cells[0].title="名称:"+msg[i].deviceName+"\r型号:"+msg[i].model+"\r运行状态:"+status+"\r所属客户:"+msg[i].customerName+"\r占用ip:"+msg[i].ip+"\r上联端口:"+msg[i].port+"\r起止U位:"+startU+"~"+msg[i].nonumber ;	
										detable.rows[i].cells[0].innerHTML="<img src='images/rsroom/server.jpg' width='148' height="+(423*uno/(unumber-5)-2)+" ondblclick='deinfo(\""+deviceinfo+"\")'/>";	
									}
									
								   }	
						   }}	
				    }
				}
			}else {
				var tbody = document.createElement("tbody");
				var tbody2 = document.createElement("tbody");
				utable.appendChild(tbody);
				detable.appendChild(tbody2)
				document.getElementById('show_u').style.background='#80E6FF';
			}
		}, 
		error:function (error) {
			
	    }
		});
	document.getElementById('rackNo').innerHTML="&nbsp;"+rackNo;
	document.getElementById('rackName').innerHTML="&nbsp;"+rackName;
	document.getElementById('rackModel').innerHTML="&nbsp;"+rackModel;
	document.getElementById('rackstuts').innerHTML="&nbsp;"+rackstuts;
	document.getElementById('belongCustomer').innerHTML="&nbsp;"+belongCustomer;
	document.getElementById('belongRoom').innerHTML="&nbsp;"+belongRoom;
	document.getElementById('totalU').innerHTML="&nbsp;"+totalU;
	document.getElementById('freeU').innerHTML="&nbsp;"+freeU;
	document.getElementById('disRentU').innerHTML="&nbsp;"+disRentU;
	document.getElementById('whlRentU').innerHTML="&nbsp;"+whlRentU;
	document.getElementById('preU').innerHTML="&nbsp;"+preU;
}
function whitchimage(uno){
	var detable=document.getElementById('show_u');
	var url="";
	if(uno<5){url="images/rsroom/switch.jpg";}
	else if(uno>=5&&uno<10){url="images/rsroom/switch_5u.jpg";}
	else{url="images/rsroom/switch_10u.jpg";}
	return url;
}
function deinfo(id){//服务器
	$("iframe[name='rsserverInfo_content']").get(0).contentWindow.loadInfo(id);   
}
function switchinfo(id){//网络设备
	$("iframe[name='device_content']").get(0).contentWindow.loadInfo(id);   
}

function updaterack(id){
	 $("iframe[name='edit_content']").get(0).contentWindow.loadInfo(id);   	
//	var url = "bussiness/sxydidc/rack/rackEdit.jsp?id="+id;
//	winOpen(url,'机架信息编辑',760,560,'保存','取消',function(data){
//       	$.ajax({
//			url:"updateRack.action", 
//			data:data,
//			dataType:"json", 
//			type:"post",
//			success:function (msg) {
//       		if("error" == msg.result){
//       			
//   				top.$.ligerDialog.error("修改机架信息失败!");
//   				
//   			}else{ 
//       			top.$.ligerDialog.success("修改机架信息成功!");
//       			window.location.reload(true);
//   			}
//			}});
//	});
}

//function newRack(){
//	var room_id=document.getElementById('markroomid').value;
//	var url = "bussiness/sxydidc/rsroom/newRack.jsp?room_id="+room_id;
//
//	window.top.$.ligerDialog.open({ width: 500, height: 400, url: url, title: '新建', buttons: [ { text: '新建', onclick: function (item, dialog) { console.log(dialog); } }, { text: '关闭', onclick: function (item, dialog) { dialog.close(); } } ] });
//}
function  newRack(){
	 $("#adduser").modal('show');	 
//	
//	var url = "bussiness/sxydidc/rack/rackAdd.jsp?room_id="+room_id+"&&roomtype="+roomtype;
//	winOpen(url,'添加机架信息',760,560,'添加','取消',function(data){
//       	$.ajax({
//			url:"saveRack.action", 
//			data:data,
//			dataType:"json", 
//			type:"post",
//			success:function (msg) {
//       			if("error" == msg.result){
//       				top.$.ligerDialog.error("添加机架信息失败!");
//       			}else{
//	       			top.$.ligerDialog.success("添加机架信息成功!");
//	       			window.location.reload(true);
//       			}
//			}, 
//			error:function (error) {
//				top.$.ligerDialog.error("添加机架信息失败!" + error.status,"错误");
//		}});
//	});	
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

function  newSomeRack(){
	 $("#addsome").modal('show');	 
          }

function newPillar(){
	$("#addPillar").modal('show');	
}
function addPillarCheck(){
	var room_id=$("#markroomid").val();
	var alldevnumber=$("#alldevnumber").val();
	var rowNumber=$("#rowNumber").val(); 
	$.ajax({
		url:"roomAndrackInfo.action", 
		data:{"room_id":room_id},
		dataType:"json", 
		type:"post",
		success:function (msg) {
			var everyRowNumber=0;
			for(var i=0;i<msg.length;i++){
				if(parseInt(msg[i].rowno)==parseInt(rowNumber)){					
					everyRowNumber=everyRowNumber+1;
				}
			}
   			var rsroomDevNumber=(msg[0].colcount)*(msg[0].rowcount)
   			if(parseInt(rsroomDevNumber)>parseInt(alldevnumber)){
   				var re = /^[1-9]+[0-9]*]*$/;
   				if(!re.test(rowNumber)){
   				 $("#tipContent").html("行数为正整数")
	        	     $('#myModal').modal('show');			
   					return;
   				}else{
   					if(everyRowNumber>=parseInt(msg[0].colcount)){
   					 $("#tipContent").html("机架此行已满")
		        	     $('#myModal').modal('show');		
   	   					return;
   					}
   					else if(parseInt(rowNumber)>parseInt(msg[0].rowcount)){
   					 $("#tipContent").html("行数超出机房行数")
		        	     $('#myModal').modal('show');			
   	   					return;
   					}else{	
   						$.ajax({
   							url:"savePillar.action", 
   							data:{"room_id":room_id,"rowNumber":rowNumber,"col":msg[0].colcount,"roomcode":msg[0].roomcode},
   							dataType:"json", 
   							type:"post",
   							success:function (date) {
   				       			if("error" == date.result){
   				       			 $("#tipContent").html("添加柱子失败")
   				        	     $('#myModal').modal('show');		
   				       				
   				       			}else{
   				       			 $("#tipContent").html("添加柱子成功")
   				        	     $('#myModal').modal('show');		       		
   					       		    window.location.reload(true);
   				       			}
   							}, 
   							error:function (error) {
   								$("#tipContent").html("添加柱子失败")
   				        	   $('#myModal').modal('show');
   								
   						}
   						});
   					}
   				}
   			}else{
   				$("#tipContent").html("机房已满")
         	   $('#myModal').modal('show');
   			    }
   			
		}, 
		error:function (error) {
			$("#tipContent").html("添加柱子失败")
        	   $('#myModal').modal('show');
			
	}});
}

