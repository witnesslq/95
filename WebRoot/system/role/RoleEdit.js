function loadInfo(id){
		     $.ajax({
				url:"roleQueryById.action", 
				data:"model.id="+id, 
				dataType:"json", 
				type:"post",
				success:function (mm) {
		    	 
		    	$("#subId").val(mm.model.id); 
		    	$("#rolename").val(mm.model.rolename); 
		    	$("#remark").val(mm.model.remark);
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
	 var rolename=$("#rolename").val();
	 var remark=$("#remark").val();
 if(rolename!=""){
	 var data={
			 "model.id":id,
			 "model.rolename": rolename,
			 "model.remark": remark,
         };
	 
	 $.ajax({
			url:"roleEdit.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if("error"==mm.result){
					 window.parent.$("#tipContent").html("角色信息修改失败！");
					 window.parent.$("#myModal").modal('show'); 
					
				}else{	
					 window.parent.$("#tipContent").html("角色信息修改成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#adduser").modal('hide'); 
					 window.parent.location.reload();
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("角色信息修改失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }
			});
	 
}else{
	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
	 window.parent.$("#myModal").modal('show'); 
}	
} 
 

