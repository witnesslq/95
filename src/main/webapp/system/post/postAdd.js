$(function () {
  $.ajax({
		url:"cropDeptQuery", 
		dataType: 'json',
		type:"post",
		success:function (data) {
	        $("#department").append('<option>'+''+'</option>');
	       for(var i=0;i<data.length;i++){
		       $("#department").append('<option value="'+data[i].id+'">'+data[i].text+'</option>');
	       }
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取信息失败！");
			 window.parent.$("#myModal").modal('show'); 
			
		}
	}); 
  $.ajax({
		url:"queryConfigSuperUserID", 
		type:"post",
		success:function (data) {
                $("#dept").val(data);
		}, 
		error:function (error) {
			window.parent.$("#tipContent").html("获取信息失败！");
			window.parent.$("#myModal").modal('show');
		}
	}); 

  });


 function submit(){
	 var postname=$("#postname").val();
	 var ordernum=$("#ordernum").val();
	 var remark=$("#remark").val();
	 var dept=$("#dept").val();
	 var department=$("#department").val();
 if(postname!=""&&ordernum!=""&&department!=""){
	 var data={
			 postname: postname,
			 ordernum: ordernum,
			 remark: remark,
			 deptid: department,
			 topcorpid: dept,
         };
	 
	 $.ajax({
			url:"postAdd.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if("error"==mm.result){
					 window.parent.$("#tipContent").html("职务信息添加失败！");
					 window.parent.$("#myModal").modal('show'); 
					
				}else{	
					 window.parent.$("#tipContent").html("职务信息添加成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#adduser").modal('hide'); 
					 window.parent.location.reload();
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("职务信息添加失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }
			});
	
}else{
	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
	 window.parent.$("#myModal").modal('show'); 
}	
} 
 

