 function loadInfo(id){
    //alert(id);
	$.ajax({
		url:"queryRsdatacenterById.action", 
		data:"ids="+id,
		//data:"rsdata.id", 
		dataType:"json", 
	    async:false,
		type:"post",
		success:function (mm) {	
		
		    $("#name").val(mm.name);
	        $("#address").val(mm.address);
	        $("#companyId").append('<option value="'+mm.companyId+'">'+mm.companyname+'</option>');
	        $("#regionId").append('<option value="'+mm.regionId+'">'+mm.regionname+'</option>');
	        window.parent.$("#editUser").modal('show');
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取单个信息失败！");
			 window.parent.$("#myModal").modal('show');
	}});
	}	
$(function () {
   $.ajax({
		url:"cropDeptQuery", 
		dataType: 'json',
		type:"post",
		success:function (data) {
	        $("#companyId").append('<option>'+''+'</option>');
	       for(var i=0;i<data.length;i++){
		       $("#companyId").append('<option value="'+data[i].id+'">'+data[i].text+'</option>');
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
$.ajax({
	    url:"dcidTreeQuery.action",
		dataType: 'json',
		type:"post",
		success:function (data) {
	     $("#dataCenter").append('<option>'+''+'</option>');
	  for(var i=0;i<data.length;i++){
	       $("#dataCenter").append('<option value="'+data[i].id+'">'+data[i].text+'</option>');
    };
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取信息失败！");
			 window.parent.$("#myModal").modal('show'); 
		}
	});
  
  
    $("#companyId").change(function(){
    
    	 var topcorpid= $("#companyId").val();	 
    	 var result="";
    	 
    	  $.ajax({
  			url:"QueryTopCorpId", 
  			data:"corpid="+topcorpid,
  			type:"post",
  			success:function (data) {
  		    result=data;
  		    getinfo("QueryRoleByDept","role",result);
      	    getinfo("QueryStationByDept","job",result);
      	    getinfo("QueryPostByDept","post",result);
      	    getinfo("QueryAreaByDept","regionId",result);
      	    
  			}, 
  			error:function (error) {

  			}
  		});    
    });
    function getinfo(url,id,topcorpid){
    	 $("#"+id).html("");
    	  $.ajax({
    			url:url, 
    			dataType: 'json',
    			data:"corpid="+topcorpid,
    			type:"post",
    			success:function (data) {
    		        $("#"+id).append('<option>'+''+'</option>');
    		       for(var i=0;i<data.length;i++){
    			       $("#"+id).append('<option value="'+data[i].id+'">'+data[i].text+'</option>');
    		       }
    			}, 
    			error:function (error) {
    				 window.parent.$("#tipContent").html("获取信息失败！");
    				 window.parent.$("#myModal").modal('show'); 
    			}
    		});
     }
     });
   
     
  


 function submit(){
	 var name=$("#name").val();
	 var address=$("#address").val();
	 var companyId=$("#companyId").val();
	 var regionId=$("#regionId").val();
	
	 var dtype_check=letter();
	 if(dtype_check==true){
             if(name!=""&&address!=""&&companyId!=""&&regionId!=""){
				 var data={
						 "rsdata.name": name,
						 "rsdata.address": address,
						 "rsdata.companyId": companyId,
						 "rsdata.regionId": regionId
			         };
			 
			 $.ajax({
					url:"saveRsdatacenter.action", 
					data:data,
					dataType:"json",
					async:false, 
					type:"post",
					success:function (mm) {
						if("error"==mm.result){
						//alert("1"+JSON.stringify(data)+JSON.stringify(mm));
							 window.parent.$("#tipContent").html("数据中心添加失败！");
							 window.parent.$("#myModal").modal('show'); 
							
						}else{	
							 window.parent.$("#tipContent").html("数据中心添加成功！");
							 window.parent.$("#myModal").modal('show'); 
							 window.parent.$("#adduser").modal('hide'); 
							 window.parent.location.reload();
						}
					}, 
					error:function (error) {
					//alert("2");
						 window.parent.$("#tipContent").html("数据中心添加失败！");
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
		
		var str = /^[A-Z]+$/;
		
		return result;
	}
