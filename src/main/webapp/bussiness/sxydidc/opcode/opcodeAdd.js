 $("#codemodel").change(function(){
	 var codemodel_value=$(this).val();
	 if(codemodel_value==1){
		 $("div[name='hiddendiv']").hide();
	 }else{
		 $("div[name='hiddendiv']").show();
	 }
 });
function loadinfo(){
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
    	    window.parent.$("#adduser").modal('show');

            },
            error:function (error) {
				 window.parent.$("#tipContent").html("编码类别加载失败！");
				 window.parent.$("#myModal").modal('show'); 
		    }
 
    });
 }



 function submit(){
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
	 var data=	{"model.type":type,
				 "model.codetype":codetype,
				 "model.codevalue":codevalue,
				 "model.displayvalue":displayvalue,
				 "model.codeformat":codeformat,
				 "model.displayformat":displayformat,
				 "model.seqvalue":seqvalue,
				 "model.seqlength":seqlength,
				 "model.displayseq":displayseq,
				 "model.remark":remark};
	 $.ajax({
			url:"codeAdd.action", 
			data:data,
			dataType:"json",
			async:false, 
			type:"post",
			success:function (mm) {
				if("error"==mm.result){
					 window.parent.$("#tipContent").html("编码信息添加失败！");
					 window.parent.$("#myModal").modal('show'); 
					
				}else{	
					 window.parent.$("#tipContent").html("编码信息添加成功！");
					 window.parent.$("#myModal").modal('show'); 
					 window.parent.$("#adduser").modal('hide'); 
					 window.parent.location.reload();
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("编码信息添加失败！");
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
