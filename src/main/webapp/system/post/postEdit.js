function loadInfo(id){
		     $.ajax({
				url:"postGetMess.action", 
				data:"id="+id, 
				dataType:"json", 
				type:"post",
				success:function (mm) {
		    	 
		    	$("#subId").val(id); 
		    	$("#postname").val(mm.postname); 
		    	$("#ordernum").val(mm.ordernum);
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
			 postname: postname,
			 ordernum: ordernum,
			 remark: remark,
         };
	 
	 $.ajax({
			url:"postUpdate.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if("error"==mm.result){
					 window.parent.$("#tipContent").html("职务信息修改失败！");
					 window.parent.$("#myModal").modal('show'); 
					
				}else{	
					 window.parent.$("#tipContent").html("职务信息修改成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#adduser").modal('hide'); 
					 window.parent.location.reload();
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("职务信息修改失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }
			});
	 
}else{
	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
	 window.parent.$("#myModal").modal('show'); 
}	
} 
 

