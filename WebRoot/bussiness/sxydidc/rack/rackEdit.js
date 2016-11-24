
function loadInfo(rack_id){
	$("#rack_id").val(rack_id);
	$.ajax({
		url:"queryRackById.action", 
		data:{"id":rack_id},
		dataType:"json", 
		type:"post",
		success:function (msg) {
   				$("#code").val(msg.code);
   				$("#name").val(msg.name);
   				$("#typeid").html("<option value='"+msg.typeid+"'>"+msg.typeid+"U机架</option>");
   				$("#ucount").val(msg.ucount);
   				$("#rowno").val(msg.rowno);
   				$("#colno").val(msg.colno);
   				$("#xposition").val(msg.xposition);
   				$("#yposition").val(msg.yposition);
   				$("#dcname").val(msg.dcname);
   				$("#dcid").val(msg.customerid);
   				$("#power").val(msg.power);
   				var typeName="";
   				  			    
	    		if(msg.status=="01"){
	    			typeName="空闲";
	    		}else if(msg.status=="02"){
	    			typeName="散租";
	    		}else if(msg.status=="03"){
	    			typeName="整租";
	    		}else if(msg.status=="04"){
	    			typeName="预占";
	    		}
   				$("#status").html("<option value='"+msg.status+"'>"+typeName+"</option>");
   				$("#roomid").html("<option value='"+msg.roomid+"'>"+msg.roomName+"</option>");
   				window.parent.$("#editInfo").modal('show'); 
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取机房信息失败！");
			 window.parent.$("#myModal").modal('show'); 
	}});
} 
 function submit(){
	 var name=$("#name").val();
	 var code=$("#code").val();
	 var typeid=$("#typeid").val();
	 var ucount=$("#ucount").val();
	 var roomid=$("#roomid").val();

     var rowno=$("#rowno").val();
	 var colno=$("#colno").val();
	 var xposition=$("#xposition").val();
	 var yposition=$("#yposition").val();
	 var power=$("#power").val();
	 
     var status=$("#status").val();
	 var id=$("#rack_id").val();
	 var customerid=$("#dcid").val();
 if(code!=""){
		var data = {
				"rack.id":id,
				"rack.name":name,
				"rack.code":code,			
				"rack.typeid":typeid,
				"rack.ucount":ucount,
				"rack.roomid":roomid,
				"rack.rowno":rowno,
				"rack.colno":colno,
				"rack.xposition":xposition,
				"rack.yposition":yposition,
				"rack.power":power,
				"rack.status":status,
				"rack.customerid":customerid
				
	};
	 
	 $.ajax({
			url:"saveRack.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if("error"==mm.result){
					 window.parent.$("#tipContent").html("修改机架信息失败！");
					 window.parent.$("#myModal").modal('show'); 
					
				}else{	
					 window.parent.$("#tipContent").html("修改机架信息成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#adduser").modal('hide'); 
					 window.parent.location.reload();
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("修改机架信息失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }
			});
	
}else{
	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
	 window.parent.$("#myModal").modal('show'); 
}	
} 
