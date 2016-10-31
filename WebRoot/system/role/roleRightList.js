$(function() {
   var node_id=$("#node_id").val();
   var role_id=$("#role_id").val();
   $.ajax({
         url:"menuListForAu.action",
		 data:{"tid":node_id,"roleid":role_id},
         type: 'post',
         async:'false',
         dataType: 'json',
         success: function (data) {   
				var tempStr = "<table class=\"table table-bordered\" style=\"\" cellspacing=\"1px\" width='98%'>";             	
				tempStr+="<tr><th><input name=\"checked_all_info\" type=\"checkbox\" value=\"\"/></th><th>菜单名称</th><th>按钮</th></tr>"
				$.each(data.list,function(index,item){
               		if(item.id != null && item.pid != "0" && item.id != ""){  			   
	                 		tempStr += "<tr>"
	                 		tempStr += "<th><input name=\"checked_info\" type=\"checkbox\" value=\""+item.id+"\"";
	                 		if(item.flag==1){
	                 			tempStr +=" checked=\"checked\"></th>"
	                 		}else{
	                 			tempStr +="></th>";
	                 		}
	                 		tempStr += "<th class='font-size'>"+item.title+"</th>";          		
	                 	if(item.btnStr != null && item.btnStr != ""){
	                 		var btnArr = (item.btnStr).split(",");
	                 		tempStr += "<td>";
		                 		for(var i=0;i<btnArr.length;i++){
									if(btnArr[i]!=""){
										var tempArr = btnArr[i].split(";");
										tempStr += "<div class='checkbox check-floatleft'><label><input type=\"checkbox\" class='btncheck1' id ='"+tempArr[0]+"' value='"+tempArr[0]+"'  style=\"vertical-align:middle;\"/>"+tempArr[1]+"</label></div>";
									}
								}
	                 		tempStr += "</td>";
	                 	}else{
	                 		tempStr += "<td></td>";
	                 	}	   	                       		   
	                 		tempStr += "</tr>"
               		}
               	});
               	tempStr += "</table>"
               	$("#all_info_content").html(tempStr);
               	getCheckValue(role_id,node_id);
            	/**
       		 全选 或取消
       		*/
       	  $("input[name='checked_all_info']").click(function(event){
       		  if($(this).is(':checked')){
       			  $("input[name='checked_info']").prop("checked",true);	  
       		  }else{
       			  $("input[name='checked_info']").prop("checked",false);
       		  }
       		  event.stopPropagation();
       	  });
			 
         },
         error: function (XMLHttpRequest, textStatus, errorThrown) {
         	window.parent.$("#tipContent").html("操作权限获取出错！");
				 window.parent.$("#myModal").modal('show'); 
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
  function getCheckValue(role_id,node_id){
	  $.ajax({//按钮权限获取
         	url:"roleBtnQueryByMenuId.action",
			data:"roleid="+role_id+"&menuSort="+node_id,
            type: 'post',
            success: function (data) {
           	var m = eval(data);
           	$.each(m,function(index,item){
           		if(item != ""){
           			$("#"+item).attr("checked","checked");
           		}
           	});
          },
          error: function (XMLHttpRequest, textStatus, errorThrown) {
        	  window.parent.$("#tipContent").html("按钮权限获取出错！");
				 window.parent.$("#myModal").modal('show'); 
          }
         });
  }
  
  //保存
  $("#save_info").click(function(){
	  var idstr=getselectinfo();
	  $.ajax({
      	url:"roleMenuModify.action",
			data:"ch="+idstr+"&roleId="+role_id+"&sort="+node_id,
			async:false,
          type: 'post',
          success: function (data) {
             
          },
          error: function (XMLHttpRequest, textStatus, errorThrown) {
        	  window.parent.$("#tipContent").html("修改权限出错！");
				 window.parent.$("#myModal").modal('show'); 
          }
      });
      var btnIDStr = "";
     $(".btncheck1:checked").each(function(index,item){
     		btnIDStr += item.value;
     		btnIDStr += ",";
     });
     
     $.ajax({
         	url:"roleBtnModify.action",
			data:"ch="+btnIDStr+"&roleid="+role_id+"&menuSort="+node_id,
          success: function (data) {       
                	  window.parent.location.href="RoleList.jsp";
          },
          error: function (XMLHttpRequest, textStatus, errorThrown) {
        	  window.parent.$("#tipContent").html("按钮权限修改出错！");
			  window.parent.$("#myModal").modal('show');
          }
    	 });
  });
  //返回
  $("#return_back").click(function(){
	  window.parent.location.href="RoleList.jsp";
  });
});