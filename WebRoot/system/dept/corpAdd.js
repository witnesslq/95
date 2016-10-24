
 function submit(){
	 var corpname=$("#postname").val();
	 var ordernum=$("#ordernum").val();
	 var remark=$("#remark").val();
	 var pid="EA372C026B154662ACE10F66F5409D4C";
	 var epid=$("#deptcode").val();
	 var type="2";
 if(corpname!=""&&ordernum!=""&&epid!=""){
	 var data={
			 corpname: corpname,
			 ordernum: ordernum,
			 remark: remark,
			 type: type,
			 epid:epid,
			 pid:pid	 
         };
	 
	 $.ajax({
			url:"corpAdd.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if("error"==mm.result){
					 window.parent.$("#tipContent").html("部门信息添加失败！");
					 window.parent.$("#myModal").modal('show'); 
					
				}else{	
					 window.parent.$("#tipContent").html("部门信息添加成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#adduser").modal('hide'); 
					 window.parent.location.reload();
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("部门信息添加失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }
			});
	
}else{
	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
	 window.parent.$("#myModal").modal('show'); 
}	
} 
 

