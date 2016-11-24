$(function () {
  $.ajax({
		url:"topMenuQueryTree", 
		dataType: 'json',
		type:"post",
		success:function (data) {
	        $("#topmenu").append('<option>'+''+'</option>');
	       for(var i=0;i<data.length;i++){
		       $("#topmenu").append('<option value="'+data[i].id+'">'+data[i].text+'</option>');
	       }
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取信息失败！");
			 window.parent.$("#myModal").modal('show'); 
			
		}
	}); 
  });

function loadInfo(id){
		     $.ajax({
				url:"menugetById.action", 
				data:"id="+id, 
				dataType:"json", 
				type:"post",
				success:function (mm) {
		    	 
		    	$("#subId").val(id); 		    	
		   	 $("#menuname").val(mm.modal.title);
			 $("#topmenu").val(mm.modal.pid);
			 $("#menusqe").val(mm.modal.ordernum);
			 $("#doingadress").val(mm.modal.url);
			// var imageurl=$("#imageurl").val();
			 $("#menustate").val(mm.modal.whetherpublic);
			 $("#openmode").val(mm.modal.actiontype);
			 $("#remark").val(mm.modal.remark);
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
	 var menuname=$("#menuname").val();
	 var topmenu=$("#topmenu").val();
	 var menusqe=$("#menusqe").val();
	 var doingadress=$("#doingadress").val();
	// var imageurl=$("#imageurl").val();
	 var menustate=$("#menustate").val();
	 var openmode=$("#openmode").val();
	 var remark=$("#remark").val();
	 if(topmenu==""||topmenu==null){
			topmenu="0"
		}
 if(menuname!=""&&menusqe!=""&&menustate!=""&&openmode!=""){
	 var data={
			 "modal.id": id,
			 "modal.title": menuname,
			 "modal.pid": topmenu,
			 "modal.remark": remark,
			 "modal.ordernum": menusqe,
			 "modal.url": doingadress,
			 "modal.whetherpublic":menustate,
			 "modal.actiontype":openmode
         };
	 $.ajax({
			url:"menuEdit.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if("success"==mm.result){
					 window.parent.$("#tipContent").html("菜单修改成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#adduser").modal('hide'); 
					 window.parent.location.reload(); 
					
				}else{	

					 window.parent.$("#tipContent").html("菜单修改失败！");
					 window.parent.$("#myModal").modal('show');
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("菜单修改失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }
			});
	 
}else{
	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
	 window.parent.$("#myModal").modal('show'); 
}	
} 
 

