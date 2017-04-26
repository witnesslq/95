
 function submit(){
	 var startRow=$("#startRow").val();
	 var lastRow=$("#lastRow").val();
	 var startCol=$("#startCol").val();
	 var lastCol=$("#lastCol").val();
	 var maxpower=$("#maxpower").val();

	 
	 var rowNumber="";
	 var colNumber="";  
	 var room_id=window.parent.$("#markroomid").val();
 	$.ajax({
			url:"queryrowno.action", 
			data:{"room_id":room_id},
			dataType:"json",
			async:false,
			type:"post",
			success:function (msg) {
				rowNumber=msg.rowcount;
				colNumber=msg.colcount;
			}, 
			error:function (error) {
				  window.parent.$("#tipContent").html("获取机架信息失败")
				  window.parent.$('#myModal').modal('show');
		}});
    
	
	 var re = /^[1-9]+[0-9]*]*$/;
	 if(!re.test(startRow)){
		 window.parent.$("#tipContent").html("起始行为正整数且不为空")
		  window.parent.$('#myModal').modal('show');	
			return;
		}
		if(!re.test(lastRow)){
			 window.parent.$("#tipContent").html("结束行为正整数且不为空")
		      window.parent.$('#myModal').modal('show');	
			return;
		}
		if(!re.test(startCol)){
			 window.parent.$("#tipContent").html("起始列为正整数且不为空")
		      window.parent.$('#myModal').modal('show');	
			return;
		}
		if(!re.test(lastCol)){
			 window.parent.$("#tipContent").html("结束列为正整数且不为空")
		      window.parent.$('#myModal').modal('show');	
			return;
		}
		if(!re.test(maxpower)){
			 window.parent.$("#tipContent").html("最大功率为正整数且不为空")
		      window.parent.$('#myModal').modal('show');	
			return;
		}
		
		if(parseInt(lastRow)<parseInt(startRow)){
			 window.parent.$("#tipContent").html("结束行数应大于等于起始行数")
		      window.parent.$('#myModal').modal('show');	
			return;
		}
	    if(parseInt(lastCol)<parseInt(startCol)){
	    	 window.parent.$("#tipContent").html("结束列数应大于等于起始列数")
		      window.parent.$('#myModal').modal('show');	
			return;
		}
	    if(parseInt(lastRow)>parseInt(rowNumber)){
	    	 window.parent.$("#tipContent").html("所填行数超出机房行数")
		      window.parent.$('#myModal').modal('show');		
			return;
		}
	    if(parseInt(lastCol)>parseInt(colNumber)){
	    	 window.parent.$("#tipContent").html("所填列数超出机房列数")
		      window.parent.$('#myModal').modal('show');		
			return;	
	    }else{	
	  
	    		$.ajax({
					url:"saveSomeRack.action", 
					data:{"room_id":room_id,"startRow":startRow,"lastRow":lastRow,"startCol":startCol,"lastCol":lastCol,"maxpower":maxpower},
					dataType:"json", 
					type:"post",
					success:function (msg) {
		       			if("error" == msg.result){
		       			     window.parent.$("#tipContent").html("添加机架信息失败")
			        	     window.parent. $('#myModal').modal('show');	
		       			}else{
		       			    window.parent.$("#tipContent").html("添加机架信息成功")
			        	    window.parent.$('#myModal').modal('show');	
		       			    window.parent.location.reload(true);
		       			}
					}, 
					error:function (error) {
						  window.parent.$("#tipContent").html("添加机架信息失败")
		        	      window.parent.$('#myModal').modal('show');	
				}
				});
		}	
} 
