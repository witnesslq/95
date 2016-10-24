function loadInfo(id){
	console.log("====");
		     $.ajax({
				url:"stationGetMess.action", 
				data:"id="+id, 
				dataType:"json", 
				type:"post",
				success:function (mm) {
		    	$("#subId").val(id); 
		    	$("#postname").val(mm.stationname); 
		    	$("#remark").val(mm.remark);
		    	window.parent.$("#editUser").modal('show');
				}, 
				error:function (error) {
					 window.parent.$("#tipContent").html("获取单个信息失败！");
					 window.parent.$("#myModal").modal('show'); 
			     }
				});
}


 function submit(){
	 var id=$("#subId").val();
	 var postname=$("#postname").val();
	 var ordernum=$("#ordernum").val();
	 var remark=$("#remark").val();
 if(postname!=""&&ordernum!=""){
	 var data={
			 id:id,
			 stationname: postname,
			 remark: remark
         };
	 
	 $.ajax({
			url:"stationUpdate.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if("error"==mm.result){
					 window.parent.$("#tipContent").html("岗位信息修改失败！");
					 window.parent.$("#myModal").modal('show'); 
					
				}else{	
					 window.parent.$("#tipContent").html("岗位信息修改成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#adduser").modal('hide'); 
					 window.parent.location.reload();
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("岗位信息修改失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }
			});
	 
}else{
	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
	 window.parent.$("#myModal").modal('show'); 
}	
} 
 

