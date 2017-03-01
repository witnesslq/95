
$(function () {  
	
	$("#code").val(createCode('RACKCODE'));
	var rackTypeData=[{id:'42',text:'42U机架'},{id:'46',text:'46U机架'}];
	$("#typeid").html(getOption(rackTypeData)); 
	$("#status").html("<option value='01'>"+"空闲"+"</option>");
	function getOption(data){
	    	 strs="<option value=''>"+""+"</option>";
	    	 for(var i=0;i<data.length;i++){
	    		 strs+="<option value='"+data[i].id+"'>"+data[i].text+"</option>";
	    		}
	    	 return strs;
	     }
	var  roon_id=window.parent.$("#markroomid").val();
    		$.ajax({
				url:"queryRoomById.action", 
				data:{"id":roon_id},
				dataType:"json", 
				type:"post",
				success:function (msg) {
	       			if("error"!=msg.result&&msg.id!=null){
	       				$("#dcname").val(msg.dcname);
	       				$("#dcid").val(msg.dcid);
	       				$("#roomid").html("<option value='"+msg.id+"'>"+msg.roomname+"</option>");   						   					
	   				}
				}, 
				error:function (error) {
					 window.parent.$("#tipContent").html("获取机房信息失败！");
					 window.parent.$("#myModal").modal('show'); 
			}});			
		$("#typeid").change(function(){
			var ucount=$(this).val();
			$("#ucount").val(ucount);
		});
});
 function submit(){
	 var name=$("#name").val();
	 var code=$("#code").val();
	 var typeid=$("#typeid").val();
	 var ucount=$("#ucount").val();
	 var roomid=$("#roomid").val();

     var rowno=$("#rowno").val();
	 var colno=$("#colno").val();
	 var xposition=$("#xposition").val();
	 var yposition=$("#yposition").val();
	 var power=$("#power").val();
	 
     var status=$("#status").val();
	 var dcid=$("#dcid").val();
	 
 if(code!=""&&typeid!=""){
		var data = {
				"rack.name":name,
				"rack.code":code,			
				"rack.typeid":typeid,
				"rack.ucount":ucount,
				"rack.roomid":roomid,
				"rack.rowno":rowno,
				"rack.colno":colno,
				"rack.xposition":xposition,
				"rack.yposition":yposition,
				"rack.power":power,
				"rack.status":status,
				"rack.dcid":dcid	
				
	};
	 
	 $.ajax({
			url:"saveRack.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if("error"==mm.result){
					 window.parent.$("#tipContent").html("添加机架信息失败！");
					 window.parent.$("#myModal").modal('show'); 
					
				}else{	
					 window.parent.$("#tipContent").html("添加机架信息成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#adduser").modal('hide'); 
					 window.parent.location.reload();
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("添加机架信息失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }
			});
	
}else{
	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
	 window.parent.$("#myModal").modal('show'); 
}	
} 

function setInfo(id){
	var values=id.split(":");
	$("#"+values[0]).html("<option value='"+values[1]+"'>"+values[2]+"</option>");
}