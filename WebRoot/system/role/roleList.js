$(function () {
	
	/**
	 加载数据
	*/
  $.ajax({
		url: "roleInfoQueryList.action",
		type: "post",
		dataType: 'json',
		success: function(data) {
	    var dataSet = [];
	   
		for(var i=0;i<data.length;i++){
			 var menu = [];
		var item = data[i];
		menu.push("<input name=\"checked_info\" type=\"checkbox\" value=\""+item.id+"\">");   
		menu.push(item.rolename);
		menu.push(item.remark);
		dataSet.push(menu);
		}		
		 $("#example1").DataTable({
			 "data": dataSet,//数据源
			 "columns": [
		                    { "title": "<input name=\"checked_all_info\" type=\"checkbox\" value=\"\">" },
		                    { "title": "角色名称" },
		                    { "title": "备注" }
		                ],
			 "oLanguage": {//插件的汉化
             "sLengthMenu": "每页显示 _MENU_ 条记录",
             "sZeroRecords": "抱歉， 没有找到",
             "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
             "sInfoEmpty": "没有数据",
             "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
             "oPaginate": {
                 "sFirst": "首页",
                 "sPrevious": "前一页",
                 "sNext": "后一页",
                 "sLast": "尾页"
             },
		    "sZeroRecords": "没有检索到数据",
            "sProcessing": "<img src='' />",
            "sSearch": "搜索"
		 },
             
		 });
			/**点击复选框，行选中*/
		 $('#example1').on('click', 'input[name="checked_info"]', function (event) {
			  if($(this).is(':checked')){
				  $(this).parent().parent().addClass('selected');  
			  }else{	
				  $(this).parent().parent().removeClass('selected');
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
            	 $('#confirm').modal('show');          	 
             }
		});
	
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
	

	/**
	按钮权限
	*/
$("#RoleBtnQX").click(function(){ 
	 var id=getselectinfo();
	 if(id==""){
    	 $("#tipContent").html("请选择要查看的数据")
    	 $("#myModal").modal('show');	 
     }else if(id.indexOf(",") > 0){
    	 $("#tipContent").html("只能查看一条按钮权限");
    	 $("#myModal").modal('show');	 
     }else{
    	$("iframe[name='content_info']").get(0).contentWindow.loadInfo(id);
     }
	});

 $("button[name='adduser']").click(function(){
	 $("iframe[name='adduser_content']").get(0).contentWindow.submit();
 });
 $("button[name='editUser']").click(function(){ 
	 $("iframe[name='editUser_content']").get(0).contentWindow.submit();
 });
 $("button[name='but_confirm']").click(function(){ 
	 $("iframe[name='content_info']").get(0).contentWindow.submit();
 });
 
  });
/**
删除选中项
*/
function detletedate(){

	var idstr=getselectinfo();
	var counr=idstr.split(",").length;
	
	
	
	 if(QueryIsDelete("role",idstr)=="false"){
		 $("#tipContent").html("删除数据失败，已分配人员的角色不能删除");
			$("#myModal").modal('show');
		}else{
	        /**
	           删除数据库数据
	        */
			
			var Dtable = $('#example1').DataTable();
			 Dtable.rows('.selected').remove().draw(false);
			 $.post("roleDel.action", { ids: idstr},
						function(data){
				 			if(data.result == "success"){
				 				 Dtable.rows('.selected').remove().draw(false);
						      	$("#tipContent").html("您删除了"+counr+"条数据");
					 			$("#myModal").modal('show');
					        }else{
					        	$("#tipContent").html("删除数据失敗");
					 			$("#myModal").modal('show');
					        }
				      },"json");	
   }
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
是否可以删除信息
*/
function  QueryIsDelete(type,id){
	var dataPost = {"type":type,"ids":id};
	var dataMM;
		$.ajax({
			url:"QueryIsDelete.action", 
			data:dataPost, 
			async:false,
			dataType:"json", 
			type:"post",
			success:function (mm) {
				dataMM = mm; 
			}, 
			error:function (error) {
				dataMM = false;
			}
		});
		return dataMM;
}


$("#own_user").click(function(){
	 var id=getselectinfo();
	    if(id==""){
	   	 $("#tipContent").html("请选择角色")
	   	 $("#myModal").modal('show');	 
	    }else if(id.indexOf(",") > 0){
	   	 $("#tipContent").html("同时只能查看一个角色关联用户");
	   	 $("#myModal").modal('show');	 
	    }else{
	       location.href="roleHaveUserList.jsp?id="+id;
	    }
}); 

$("#RoleOfAU").click(function(){
	 var id=getselectinfo();
	    if(id==""){
	   	 $("#tipContent").html("请选择角色")
	   	 $("#myModal").modal('show');	 
	    }else if(id.indexOf(",") > 0){
	   	 $("#tipContent").html("同时只能查看一个角色操作权限");
	   	 $("#myModal").modal('show');	 
	    }else{
	       location.href="RoleOfAU.jsp?id="+id;
	    }
}); 

