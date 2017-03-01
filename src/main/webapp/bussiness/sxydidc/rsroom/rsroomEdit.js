
function loadInfo(id){
	$("#subid").val(id);
	$.ajax({
		url:"queryRoomById.action", 
		data:"id="+id, 
		dataType:"json", 
	    async:false,
		type:"post",
		success:function (mm) {	
		    $("#roomcode").val(mm.roomcode);
	        $("#roomname").val(mm.roomname);
	        $("#grade").val(mm.gradeValue);
	        $("#deptid").val(mm.deptname);
	        $("#deptidhidden").val(mm.deptid);
	        $("#rowcount").val(mm.rowcount);  
	        $("#colcount").val(mm.colcount);
	        $("#dcid").val(mm.dcid);
	        $("#maintancer").val(mm.maintancer);
	        $("#telephone").val(mm.telephone);
	        $("#gradelevle").val(mm.grade);
	        $("#areaid").val(mm.area);
	        $("#racktype").val(mm.racktype);
	        $("#width").val((mm.width)/100);
	        $("#remark").val(mm.remark);
	        $("#height").val((mm.height)/100);
	        $("#areaidhidden").val(mm.areaid)
	        $("#status").val(mm.status);
	        $("#address").val(mm.address);
	        window.parent.$("#editUser").modal('show');
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取单个信息失败！");
			 window.parent.$("#myModal").modal('show');
	}});
	}	
function submit(){
	 
	 var roomcode=$("#roomcode").val();
	 var roomname=$("#roomname").val();
	 var grade=$("#gradelevle").val();
	 var deptid=$("#deptidhidden").val();
	 var needRack=$("#needRack").val();

     var racktype=$("#racktype").val();
	 var colcount=$("#colcount").val();
	 var rowcount=$("#rowcount").val();
	 var maintancer=$("#maintancer").val();
	 var telephone=$("#telephone").val();
	 
     var width=$("#width").val();
	 var height=$("#height").val();
	 var areaid=$("#areaidhidden").val();
	 var status=$("#status").val();
	 var dcname=$("#dcid").val();
	 var address=$("#address").val();
	 var remark=$("#remark").val();
	 var id=$("#subid").val();
 if(status!=""){
		var data = {
				"rsroom.id":id,
				"rsroom.roomname":roomname,
				"rsroom.roomcode":roomcode,
				"rsroom.grade":grade,
				"rsroom.colcount":rowcount,
				"rsroom.rowcount":colcount,
				"rsroom.maintancer":maintancer,
				"rsroom.telephone":telephone,
				"rsroom.racktype":racktype,
				"rsroom.width":width*100,
				"rsroom.height":height*100,
				"rsroom.address":address,
				"rsroom.deptid":deptid,			
				"rsroom.areaid":areaid,
				"rsroom.status":status,	
				"rsroom.dcid":dcname,	
				
				"rsroom.remark":remark	
				
	};
		 
		 $.ajax({
				url:"updateRoom.action", 
				data:data,
				dataType:"json",
				async:false, 
				type:"post",
				success:function (mm) {
			 console.log(mm.result);
					if("error"==mm.result){
						 window.parent.$("#tipContent").html("编辑机房信息失败！");
						 window.parent.$("#myModal").modal('show');
					}else{	
						 window.parent.$("#tipContent").html("编辑机房信息成功！");
						 window.parent.$("#myModal").modal('show');
						 window.parent.$("#editUser").modal('hide'); 
						 window.parent.location.reload();
					}
				}, 
				error:function (error) {
					 window.parent.$("#tipContent").html("编辑机房信息失败！");
					 window.parent.$("#myModal").modal('show');
			}});
	
}else{
		 window.parent.$("#tipContent").html("请确认必填项已都填写！");
		 window.parent.$("#myModal").modal('show'); 
}	
} 


	
