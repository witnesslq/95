
function loadInfo(id){
	$("#subid").val(id);
	$.ajax({
		url:"queryRoomById.action", 
		data:"id="+id, 
		dataType:"json", 
	    async:false,
		type:"post",
		success:function (mm) {	
		var status=mm.status;
		if(status="01"){
			status="是";
		}else{
			status="否";
		} 
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
	        window.parent.$("#detailUser").modal('show');
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取单个信息失败！");
			 window.parent.$("#myModal").modal('show');
	}});
	}	


	
