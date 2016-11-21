
 function submit(){
	 var startip=$("#startip").val();
	 var count=$("#count").val();
	 var endip=$("#endip").val();
	 var name=$("#name").val();
	 var netmask=$("#netmask").val();
	 var gatewayip=$("#gatewayip").val();
	 var status=$("#status").val();
	 var vlanno=$("#vlanno").val();
	 var dns1=$("#dns1").val();
	 var dns2=$("#dns2").val();
	 var dcname=$("#dcname").val();
	 var usefor=$("#usefor").val();
	 var remark=$("#remark").val();

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
 

