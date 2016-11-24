function loadInfo(id){
		     $.ajax({
				url:"configQueryById.action", 
				data:"id="+id, 
				dataType:"json", 
				type:"post",
				success:function (mm) {
		    	 
		    	$("#subId").val(id); 
		    	$("#postname").val(mm.dtype); 
		    	$("#ordernum").val(mm.dkey);
		    	$("#remark").val(mm.remark);
		    	$("#department").val(mm.dvalue);
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
	 var department=$("#department").val();
	 var remark=$("#remark").val();
	 var checktype=letter(postname);
	 if(checktype==true){
 if(postname!=""&&ordernum!=""&&department!=""){
	 var data={
			 id:id,
			 dtype: postname,
			 dkey: ordernum,
			 remark: remark,
			 dvalue: department
         };
	 
	 $.ajax({
			url:"configEdit.action", 
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
	}
 

	function letter(data){
		var str = /^[A-Z]+$/;
		var result=true;
		if (!str.test(data)){
			 window.parent.$("#tipContent").html("类别必须由大写字母组成！");
			 window.parent.$("#myModal").modal('show'); 
			 result=false;
		}
		return result;
	}