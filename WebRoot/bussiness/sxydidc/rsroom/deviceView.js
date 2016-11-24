
function loadInfo(id){
	$.ajax({
		url:"queryDeviceById.action", 
		data:"id="+id, 
		dataType:"json", 
	    async:false,
		type:"post",
		success:function (mm) {	
		    $("#name").val(mm.name);
	        $("#code").val(mm.code);
	     
	        $("#moduletype").val(mm.moduletype);
	        $("#roomid").val(mm.roomname);

	        $("#startu").val(mm.startu);
	        $("#ucount").val(mm.ucount);
	        $("#rackid").val(mm.rackname);
	        $("#ipid").val(mm.batchIpAdd);
	        $("#dcname").val(mm.dcname);
	
	        $("#remark").val(mm.remark);
	        window.parent.$("#deviceInfoModel").modal('show');
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取单个信息失败！");
			 window.parent.$("#myModal").modal('show');
	}});
	}	


	
