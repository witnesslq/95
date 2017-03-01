$(function () {
	
	/**
	 加载数据
	*/
  $.ajax({
		url: "menuQueryList",
		type: "post",
		dataType: 'json',
		success: function(data) {
	    var str=""
		for(var i=0;i<data.listmodal.length;i++){
			var ifpublic="公开页面";
			if(data.listmodal[i].whetherpublic=="02"){
				 ifpublic="私有页面";
			}
			if(data.listmodal[i].pid=="0"){
				str+="<tr><td class='font-center'><input name='checked_info' value='"+data.listmodal[i].id+"' type='checkbox' /></td><td >&nbsp;&nbsp;&nbsp;<div name='showChildren' id='"+data.listmodal[i].id+"' class='fa fa-plus-square' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+data.listmodal[i].title+"</t><td class='font-center'>"+data.listmodal[i].ordernum+"</td> <td class='font-center'>"+ifpublic+"</td><td class='font-center'>"+data.listmodal[i].remark+"</td><tr>"	
			     for(var j=0;j<data.listmodal.length;j++){
			    	 if(data.listmodal[i].id==data.listmodal[j].pid){
			    			str+="<tr style='display:none;' class='childrenRows' name='"+data.listmodal[j].pid+"'><td class='font-center'><input name='checked_info' value='"+data.listmodal[j].id+"' type='checkbox' /></td><td class='font-center'>"+data.listmodal[j].title+"</t><td class='font-center'>"+data.listmodal[j].ordernum+"</td> <td class='font-center'>"+ifpublic+"</td><td class='font-center'>"+data.listmodal[j].remark+"</td><tr>"			 					     
			    	 }	
			     }
			}	
		}		
		$("#example1").append(str);
			/**点击复选框，行选中*/
		 $('#example1').on('click', 'input[name="checked_info"]', function (event) {
			  if($(this).is(':checked')){
				  $(this).parent().parent().addClass('selected');  
			  }else{	
				  $(this).parent().parent().removeClass('selected');
			  }
			  event.stopPropagation();
		  });
			/**展开，折叠子列*/
		 $('#example1').on('click', 'div[name="showChildren"]', function (event) {
			  var thisClass=$(this).attr("class");
			  var pid=$(this).attr("id");
			  if(thisClass=="fa fa-plus-square"){
				  $(this).removeClass();
				  $(this).addClass("fa fa-minus-square-o");
				  $("tr[name='"+pid+"']").show();
			  }else{
				  $(this).removeClass();
				  $(this).addClass("fa fa-plus-square");
				  $("tr[name='"+pid+"']").hide();
			  }
			  event.stopPropagation();
		  });
			/**
			 全选 或取消
			*/
		  $("input[name='checked_all_info']").click(function(event){
			  if($(this).is(':checked')){
				  $("input[name='checked_info']").parent().parent().addClass('selected');  
				  $("input[name='checked_info']").prop("checked",true);
				  
			  }else{
				  $("input[name='checked_info']").prop("checked",false);
				  $("input[name='checked_info']").parent().parent().removeClass('selected');  
			  }
			  event.stopPropagation();
		  });
			/**点击行事件*/
		 $('#example1').on('click', 'tr', function (event) {
			  if($(this).find("input[name='checked_info']").is(':checked')){
				  $(this).removeClass('selected');
				  $(this).find("input[name='checked_info']").prop("checked",false);
			  }else{
				  $(this).addClass('selected');
				  $(this).find("input[name='checked_info']").prop("checked",true);
			  } 
			  event.stopPropagation();
			});
		}
		});


  
	/**
	 添加信息
	*/
$("#add_user").click(function(){ 
	     $("#adduser").modal('show');   
		});
	
	/**
	 删除信息
	*/
  $("#detele_user").click(function(){
	     var id=getselectinfo();
             if(id==""){
            	 $("#tipContent").html("请选择要删除的数据")
            	 $('#myModal').modal('show');
             }else{
            	 if(QueryIsDelete("post",id)=="true"){        		 
            		 $("#tipContent").html("删除数据失败，含有子菜单不能删除")
                	 $('#myModal').modal('show');
            	 }
            	 else{
            		 $('#confirm').modal('show'); 
            	 }
            	          	 
             }
		});
  /**
	验证是否可以删除信息
*/
function  QueryIsDelete(type,id){
 	var dataPost = {"type":type,"ids":id};
 	var dataMM;
	$.ajax({
		url:"menuQueryHasChildren.action", 
		data:dataPost, 
		async:false,
		dataType:"json", 
		type:"post",
		success:function (mm) {
			dataMM = mm.result; 
		}, 
		error:function (error) {
			dataMM = false;
		}
	});
	return dataMM;
}
	/**
	 获取选中项
	*/
  function getselectinfo(){
	  var checkboxval=$("input[name='checked_info']"); 
      var id="";   
      for (var i=0;i<checkboxval.length;i++ ){       
          if(checkboxval[i].checked){ 
              if(id=="") {
              id=id+checkboxval[i].value; 
               }else{
                 id=id+","+checkboxval[i].value;   
               }
          }  
       }
      return id;
  }
  
	/**
	 修改信息
	*/
$("#edit_user").click(function(){ 
	     var id=getselectinfo();
	     if(id==""){
	    	 $("#tipContent").html("请选择要修改的数据")
	    	 $("#myModal").modal('show');	 
	     }else if(id.indexOf(",") > 0){
	    	 $("#tipContent").html("只能修改一条数据");
	    	 $("#myModal").modal('show');	 
	     }else{
	    	 $("iframe[name='editUser_content']").get(0).contentWindow.loadInfo(id);    	 
	     }
	      
		});
	

 $("button[name='adduser']").click(function(){
	 $("iframe[name='adduser_content']").get(0).contentWindow.submit();
 });
 $("button[name='editUser']").click(function(){ 
	 $("iframe[name='editUser_content']").get(0).contentWindow.submit();
 });
 //收缩全部
 $("#compress").click(function(){
	$('div[name="showChildren"]').removeClass().addClass("fa  fa-plus-square"); 
	$(".childrenRows").hide();

 });
 //展开全部
 $("#expand").click(function(){
	 $('div[name="showChildren"]').removeClass().addClass("fa fa-minus-square-o");  
	 $(".childrenRows").show();
 });
  });
/**
删除选中项
*/
function detletedate(){

	var idstr=getselectinfo();
	var counr=idstr.split(",").length;
	$('#example1').find('.selected').remove();
	 $.post("menuDel.action", { ids: idstr},
			 function(data){
	       if(data.result == "success"){
	    		$("#tipContent").html("您删除了"+counr+"条数据");
	 			$("#myModal").modal('show');
	        }else{
	        	$("#tipContent").html("删除了失败");
	 			$("#myModal").modal('show');
	        }
    },"json");
	  
}
/**
获取选中项
*/
function getselectinfo(){
 var checkboxval=$("input[name='checked_info']"); 
 var id="";   
 for (var i=0;i<checkboxval.length;i++ ){       
     if(checkboxval[i].checked){ 
         if(id=="") {
         id=id+checkboxval[i].value; 
          }else{
            id=id+","+checkboxval[i].value;   
          }
     }  
  }
 return id;
}

