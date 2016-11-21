
 function submit(){
	 var postname=$("#postname").val();
	 var ordernum=$("#ordernum").val();
	 var department=$("#department").val();
	 var remark=$("#remark").val();
	 var checktype=letter(postname);
	 if(checktype==true){
		 if(postname!=""&&ordernum!=""&&department!=""){
		 var data={
				 dtype: postname,
				 dkey: ordernum,
				 remark: remark,
				 dvalue: department
         };
	 $.ajax({
			url:"configAdd.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if("error"==mm.result){
					 window.parent.$("#tipContent").html("添加系统配置信息失败！");
					 window.parent.$("#myModal").modal('show'); 
					
				}else{	
					 window.parent.$("#tipContent").html("添加系统配置信息成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#adduser").modal('hide'); 
					 window.parent.location.reload();
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("添加系统配置信息失败！");
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
