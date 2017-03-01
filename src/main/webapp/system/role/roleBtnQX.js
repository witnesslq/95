//加载数据
function loadInfo(id){
      $("#subId").val(id);
		     $.ajax({
				url:"btnQueryAll.action", 
				data:{"menuSort":"QT"},
				dataType:"json", 
				type:"post",
				success:function (data) {
					var tempStr = "<table class=\"table\" style=\"\" cellspacing=\"1px\" width='98%'>";
               	 tempStr += "<caption  class='font-size' >非菜单页面的按钮授权管理</caption>"
                	$.each(data,function(index,item){
                		if(item.btnStr != null && item.btnStr != ""){
	                 		tempStr += "<tr>"
	                 		tempStr += "<th class='font-size'>"+item.pagename+"</th><td>";
	                 		var btnArr = (item.btnStr).split(",");
							for(var i=0;i<btnArr.length;i++){
								if(btnArr[i]!=""){
									var tempArr = btnArr[i].split("|");
									tempStr += "<div class='checkbox check-floatleft'><label><input type=\"checkbox\" class='btncheck1' id ='"+tempArr[0]+"' value='"+tempArr[0]+"' name='"+item.pageurl+"' style=\"vertical-align:middle;\"/>"+tempArr[1]+"</label></div>";
								}
							}
	                 		tempStr += "</td></tr>"
                		}
                	});
                	 tempStr += "</table>"
                	 $("#all_info_content").html(tempStr);
                	 getRoleBtn(id);
		    	window.parent.$("#content_info").modal('show');
				}, 
				error:function (error) {
					 window.parent.$("#tipContent").html("获取单个信息失败！");
					 window.parent.$("#myModal").modal('show'); 
			     }
				});
		     
		     
		    
			
}
//按钮权限获取
function getRoleBtn(id){
	 $.ajax({
        	url:"roleBtnQueryByMenuId.action",
			data:{"roleid":id,"menuSort":"QT"},
         type: 'post',
         async:'false',
         success: function (data) {
          	var m = eval(data);
          	$.each(m,function(index,item){
          		$("#"+item).attr("checked","checked");
          	});
         },
         error: function (XMLHttpRequest, textStatus, errorThrown) {
         	window.parent.$("#tipContent").html("按钮权限获取出错！");
				 window.parent.$("#myModal").modal('show'); 
         }
        });
}

 function submit(){
	  var qt = 'QT';
	  var roleid = $("#subId").val();
      var btnIDStr1 = "";
       $(".btncheck1:checked").each(function(index,item){
       		btnIDStr1 += item.value;
       		btnIDStr1 += ",";
       });
      $.ajax({
       	url:"roleBtnModify.action",
		data:"ch="+btnIDStr1+"&roleid="+roleid+"&menuSort="+qt,
        type: 'post',
        async:'false',
        success: function (data) {
    	  window.parent.$("#content_info").modal('hide');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	window.parent.$("#tipContent").html("按钮权限修改出错！");
			 window.parent.$("#myModal").modal('show'); 
        }
       });
} 
 

