 $("#codemodel").change(function(){
	 var codemodel_value=$(this).val();
	 if(codemodel_value==1){
		 $("div[name='hiddendiv']").hide();
	 }else{
		 $("div[name='hiddendiv']").show();
	 }
 });
function loadInfo(id){
	 $.ajax({
			url:"queryConfigSelected.action", 
			dataType:"json",
			data:{dtype:"CODETYPE"},
			async:false, 
			type:"post",
			success:function (mm) {
				var str="<option>"+""+"</option>";
				for(var i=0;i<mm.length;i++){
					str+="<option value='"+mm[i].id+"'>"+mm[i].text+"</option>"
				}
			$("#codetype").html(str); 
         },
         error:function (error) {
				 window.parent.$("#tipContent").html("编码类别加载失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }

 });
	 
		     $.ajax({
				url:"codeQueryById.action", 
				data:"model.id="+id, 
				dataType:"json", 
				type:"post",
				success:function (mm) {
		    	 
		    	$("#subId").val(mm.id); 
		   	    $("#codetype").val(mm.codetype);
			    $("#codemodel").val(mm.type);
			    if(mm.type==1){
			    	 $("div[name='hiddendiv']").hide();
			    }
			    $("#codebefore").val(mm.codevalue);
			    $("#codebeforedisplay").val(mm.displayvalue);
				$("#codeformatdisplay").val(mm.displayformat);
				$("#seqstar").val(mm.seqvalue);
				$("#sizeinfo").val(mm.seqlength);
				$("#seqstardisplay").val(mm.displayseq);
				$("#remark").val(mm.remark);
				if(mm.codeformat.indexOf("yyyy")>=0){
					$("#yyyy").prop("checked",true);
				}
				if(mm.codeformat.indexOf("MM")>=0){
					$("#MM").prop("checked",true);
				}
				if(mm.codeformat.indexOf("dd")>=0){
					$("#dd").prop("checked",true);
				}
				if(mm.codeformat.indexOf("HH")>=0){
					$("#HH").prop("checked",true);
				}
				if(mm.codeformat.indexOf("mm")>=0){
					$("#mm").prop("checked",true);
				}
				if(mm.codeformat.indexOf("ss")>=0){
					$("#ss").prop("checked",true);
				}
				if(mm.codeformat.indexOf("SSS")>=0){
					$("#SSS").prop("checked",true);
				}
			
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
	 var codetype=$("#codetype").val();
	 var type=$("#codemodel").val();
	 var codevalue=$("#codebefore").val();
	 var displayvalue=$("#codebeforedisplay").val();
	 var codeformat=getselectinfo();
	 var displayformat=$("#codeformatdisplay").val();
	 var seqvalue=$("#seqstar").val();
	 var seqlength=$("#sizeinfo").val();
	 var displayseq=$("#seqstardisplay").val();
	 var remark=$("#remark").val();
 if(type!=""&&codetype!=""){
	 var data=	{
			 	 "model.id":id,
			     "model.type":type,
				 "model.codetype":codetype,
				 "model.codevalue":codevalue,
				 "model.displayvalue":displayvalue,
				 "model.codeformat":codeformat,
				 "model.displayformat":displayformat,
				 "model.seqvalue":seqvalue,
				 "model.seqlength":seqlength,
				 "model.displayseq":displayseq,
				 "model.remark":remark};
	 if(type==1){
		 data=	{
				 "model.id":id,
			     "model.type":type,
				 "model.codetype":codetype,
				 
				 "model.codevalue":"",
				 "model.displayvalue":"",
				 "model.codeformat":"",
				 "model.displayformat":"",
				 "model.seqvalue":"",
				 "model.seqlength":"",
				 "model.displayseq":"",
				 
				 "model.remark":remark};
	 }
	 $.ajax({
			url:"codeModify.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if("error"==mm.result){
					 window.parent.$("#tipContent").html("编码信息修改失败！");
					 window.parent.$("#myModal").modal('show'); 
					
				}else{	
					 window.parent.$("#tipContent").html("编码信息修改成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#editUser").modal('hide'); 
					 window.parent.location.reload();
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("编码信息修改失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }
			});
	
}else{
	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
	 window.parent.$("#myModal").modal('show'); 
}	
} 
 
 /**
 获取选中项
 */
 function getselectinfo(){
  var checkboxval=$("input[name='timeforamt']"); 
  var id="";   
  for (var i=0;i<checkboxval.length;i++ ){       
      if(checkboxval[i].checked){ 
          id=id+checkboxval[i].id;  
      }  
   }
  return id;
 }

