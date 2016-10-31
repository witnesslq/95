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
	 var postname=$("#dtype").val();
	 var ordernum=$("#dkey").val();
	 var dept=$("#dvalue").val();
	 var remark=$("#remark").val();
	
	 var dtype_check=letter();
	 if(dtype_check==true){
             if(postname!=""&&ordernum!=""&&dept!=""){
				 var data={
						 dtype: postname,
						 dkey: ordernum,
						 remark: remark,
						 dvalue: dept,
			         };
			 
			 $.ajax({
					url:"dictAdd.action", 
					data:data,
					dataType:"json",
					async:false, 
					type:"post",
					success:function (mm) {
						if("error"==mm.result){
							 window.parent.$("#tipContent").html("数据字典添加失败！");
							 window.parent.$("#myModal").modal('show'); 
							
						}else{	
							 window.parent.$("#tipContent").html("数据字典添加成功！");
							 window.parent.$("#myModal").modal('show'); 
							 window.parent.$("#adduser").modal('hide'); 
							 window.parent.location.reload();
						}
					}, 
					error:function (error) {
						 window.parent.$("#tipContent").html("数据字典添加失败！");
						 window.parent.$("#myModal").modal('show'); 
				    }
					});
					
				}else{
					 window.parent.$("#tipContent").html("请确认必填项已都填写！");
					 window.parent.$("#myModal").modal('show'); 
				}	
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

