

 function submit(){
	 var rolename=$("#rolename").val();
	 var remark=$("#remark").val();
 if(rolename!=""){
		 var data={
			 "model.rolename":rolename,
			 "model.remark": remark
         };
	 
	 $.ajax({
			url:"roleAdd.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if(mm.result == "success"){
					 window.parent.$("#tipContent").html("角色信息添加成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#adduser").modal('hide'); 
					 window.parent.location.reload();
					
				}else{	

					 window.parent.$("#tipContent").html("角色信息添加失败！");
					 window.parent.$("#myModal").modal('show'); 
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("角色信息添加失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }
			});
	
}else{
	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
	 window.parent.$("#myModal").modal('show'); 
}	
} 
 

