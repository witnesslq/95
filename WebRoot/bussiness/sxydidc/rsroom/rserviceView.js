
function loadInfo(id){
	$.ajax({
		url:"queryServerById.action", 
		data:"id="+id, 
		dataType:"json", 
	    async:false,
		type:"post",
		success:function (mm) {	
		var devicetype=mm.devicetype;
		if(devicetype="01"){
			devicetype="网络设备";
		}else{
			devicetype="服务器";
		} 
		var owner=mm.owner;
		if(owner="1"){
			owner="局方设备";
		}else{
			owner="客户设备";
		} 
		var status=mm.status;
		if(status="01"){
			status="空闲";
		}else if(status="02"){
			status="预占";
		}else if(status="03"){
			status="实占";
		}else if(status="04"){
			status="使用中";
		}   
		
		    $("#name").val(mm.name);
	        $("#code").val(mm.code);
	        $("#sn").val(mm.sn);
	        $("#roomid").val(mm.roomname);
	        $("#moduletype").val(mm.moduletype);
	        $("#devicetype").val(devicetype);
	        $("#owner").val(owner);  
	        $("#batchIpAdd").val(mm.batchIpAdd);
	        $("#startu").val(mm.startu);
	        $("#ucount").val(mm.ucount);
	        $("#status").val(status);
	        $("#power").val(mm.power);
	        $("#buytime").val(mm.buytime);
	        $("#useyears").val(mm.useyears);
	        $("#comment").val((mm.comment));
	        $("#remark").val(mm.remark);
	        $("#dcname").val(mm.dcname);
	        $("#rackid").val(mm.rackname);
	        $("#customerid").val(mm.customername);
	        window.parent.$("#rsserverInfo").modal('show');
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取单个信息失败！");
			 window.parent.$("#myModal").modal('show');
	}});
	}	


	
