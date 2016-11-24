$(function () {
  $.ajax({
		url:"cropDeptQuery", 
		dataType: 'json',
		type:"post",
		success:function (data) {
	        $("#deptid").append('<option>'+''+'</option>');
	       for(var i=0;i<data.length;i++){
		       $("#deptid").append('<option value="'+data[i].id+'">'+data[i].text+'</option>');
	       }
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取信息失败！");
			 window.parent.$("#myModal").modal('show'); 
			
		}
	}); 
  $.ajax({
		url:"areaInfoQueryList.action", 
		type:"post",
		dataType: 'json',
		success:function (data) {
	  
	  $("#areaid").append('<option>'+''+'</option>');
      for(var i=0;i<data.length;i++){
	       $("#areaid").append('<option value="'+data[i].id+'">'+data[i].areaname+'</option>');
      }
		}, 
		error:function (error) {
			window.parent.$("#tipContent").html("获取信息失败！");
			window.parent.$("#myModal").modal('show');
		}
	}); 

  });


 function submit(){
	 var roomcode=$("#roomcode").val();
	 var roomname=$("#roomname").val();
	 var grade=$("#grade").val();
	 var deptid=$("#deptid").val();
	 var needRack=$("#needRack").val();

     var racktype=$("#racktype").val();
	 var colcount=$("#colcount").val();
	 var rowcount=$("#rowcount").val();
	 var maintancer=$("#maintancer").val();
	 var telephone=$("#telephone").val();
	 
     var width=$("#width").val();
	 var height=$("#height").val();
	 var areaid=$("#areaid").val();
	 var status=$("#status").val();
	 var dcname=$("#dcname").val();
	 var address=$("#address").val();
	 var remark=$("#remark").val();
	 
 if(roomcode!=""&&roomname!=""&&colcount!=""&&rowcount!=""&&areaid!=""&&status!=""){
		var data = {
				"rsroom.roomname":roomname,
				"rsroom.roomcode":roomcode,
				"rsroom.grade":grade,
				"rsroom.needRack":needRack,
				"rsroom.colcount":rowcount,
				"rsroom.rowcount":colcount,
				"rsroom.maintancer":maintancer,
				"rsroom.telephone":telephone,
				"rsroom.racktype":racktype,
				"rsroom.width":width*100,
				"rsroom.height":height*100,
				"rsroom.address":address,
				"rsroom.deptid":deptid,			
				"rsroom.areaid":areaid,
				"rsroom.status":status,			
				"rsroom.remark":remark		
				
	};
	 
	 $.ajax({
			url:"saveRoom.action", 
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
 

