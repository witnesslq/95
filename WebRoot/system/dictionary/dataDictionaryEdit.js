function loadInfo(id){
		     $.ajax({
				url:"dictgetById.action", 
				data:"id="+id, 
				dataType:"json", 
				type:"post",
				success:function (mm) {
		    	$("#hidden_id").val(id); 
		    	$("#dtype").val(mm.dtype); 
		    	$("#dkey").val(mm.dkey);
		    	$("#dvalue").val(mm.dvalue);
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
	 var id=$("#hidden_id").val();
	 var dtype=$("#dtype").val();
	 var dkey=$("#dkey").val();
	 var dvalue=$("#dvalue").val();
	 var remark=$("#remark").val();
 if(dtype!=""&&dtype!=""&&dvalue!=""){
	 var data={
			 id:id,
			 dtype: dtype,
			 dkey: dkey,
			 dvalue:dvalue,
			 remark: remark,
         };
	 
	 $.ajax({
			url:"dictEdit.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if("error"==mm.result){
					 window.parent.$("#tipContent").html("数据字典修改失败！");
					 window.parent.$("#myModal").modal('show'); 
					
				}else{	
					 window.parent.$("#tipContent").html("数据字典修改成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#adduser").modal('hide'); 
					 window.parent.location.reload();
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("数据字典修改失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }
			});
	 
}else{
	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
	 window.parent.$("#myModal").modal('show'); 
}	
} 
 
 function letter(){
	    var result=true;
		var dtype=$("#dtype").val();
		var str = /^[A-Z]+$/;
		if (!str.test(dtype)){
			//top.my_alert("类别必须由大写字母组成！");
			 window.parent.$("#tipContent").html("类别必须由大写字母组成！");
			 window.parent.$("#myModal").modal('show'); 
			 result=false;
		}
		return result;
	}

