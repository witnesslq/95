function loadInfo(id){
		     $.ajax({
				url:"corpgetById.action", 
				data:"id="+id, 
				dataType:"json", 
				type:"post",
				success:function (mm) {
		    	 
		    	$("#subId").val(id); 
		    	$("#postname").val(mm.corpname); 
		    	$("#ordernum").val(mm.ordernum);
		    	$("#remark").val(mm.remark);
		    	$("#deptcode").val(mm.epid);
		    	
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
	 var deptcode=$("#deptcode").val();
	 var pid="EA372C026B154662ACE10F66F5409D4C";
	 var type="2";
 if(postname!=""&&ordernum!=""&&deptcode!=""){
	 var data={
			 id:id,
			 corpname: postname,
			 ordernum: ordernum,
			 remark: remark,
			 epid:deptcode,
			 pid:pid,
			 type: type
         };
	 
	 $.ajax({
			url:"corpEdit.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if("error"==mm.result){
					 window.parent.$("#tipContent").html("部门信息修改失败！");
					 window.parent.$("#myModal").modal('show'); 
					
				}else{	
					 window.parent.$("#tipContent").html("部门信息修改成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#adduser").modal('hide'); 
					 window.parent.location.reload();
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("部门信息修改失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }
			});
	 
}else{
	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
	 window.parent.$("#myModal").modal('show'); 
}	
} 
 

