$(function () {
	
	/**
	 加载数据
	*/
  $.ajax({
		url: "queryRoomInfo.action",
		type: "post",
		dataType: 'json',
		success: function(data) {
	    var dataSet = [];
	   
		for(var i=0;i<data.length;i++){
			 var menu = [];
		var item = data[i];
	     var level=item.grade;
			if(level=="01"){
				level="A";
			}else if(level=="02"){
				level="B";
			}else if(level=="03"){
				level="c";
			}
		menu.push("<input name=\"checked_info\" type=\"checkbox\" value=\""+item.id+"\">");   
		menu.push("<a href='javascript:void(0);' name='detailInfo' id='"+item.id+"' >"+item.roomcode+"</a>");
		menu.push(item.roomname);
		menu.push(level);
		menu.push(item.deptname);
		menu.push(item.area);
		menu.push(item.dcname);
		
		menu.push(item.totalRack);
		menu.push(item.freeRack);
		menu.push(item.disRentRack);
		menu.push(item.whlRentRack);
		menu.push(item.preRack);
		menu.push("<a href='rsroomEditorView.action?id="+item.id+"' target='blank'>查看</a>");   
		dataSet.push(menu);
		}
		 $("#example1").DataTable({
			 "data": dataSet,//数据源
			 "columns": [
		                    { "title": "<input name=\"checked_all_info\" type=\"checkbox\" value=\"\">" },
		                    { "title": "机房编码" },
		                    { "title": "机房名称" },
		                    { "title": "机房级别" },
		                    { "title": "所属公司或单位"},
		                    { "title": "所属区域"},
		                    { "title": "所属数据中心"},
		                    { "title": "总机架数" },
		                    { "title": "空闲机架数"},
		                    { "title": "散租机架数"},
		                    { "title": "整租机架数"},
		                    { "title": "预占机架数"},
		                    { "title": "机房查看"}
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
			 $('#example1').on('click', 'a[name="detailInfo"]', function (event) {
			  var id=$(this).attr("id");
				$("iframe[name='content_info']").get(0).contentWindow.loadInfo(id);
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
	详细信息
	*/
$("#detail_user").click(function(){ 
	 var id=getselectinfo();
	 if(id==""){
    	 $("#tipContent").html("请选择要查看的数据")
    	 $("#myModal").modal('show');	 
     }else if(id.indexOf(",") > 0){
    	 $("#tipContent").html("只能查看一条数据详情");
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
  });
/**
删除选中项
*/
function detletedate(){

	var idstr=getselectinfo();
	var counr=idstr.split(",").length;
	var Dtable = $('#example1').DataTable();
	 Dtable.rows('.selected').remove().draw(false);
	 $.ajax({
			url:"deleteRoomByIds.action?ids="+idstr, 
			dataType:"json", 
			type:"post",
			success:function (msg) {
	       		if("error"==msg.result){
	       			$("#tipContent").html("删除机房信息失败");
		 			$("#myModal").modal('show');	
	   			}else if("01"==msg.result){
	   				$("#tipContent").html("您删除了"+counr+"条数据");
		 			$("#myModal").modal('show');
	   		
	   			}else if("02"==msg.result){
	   				$("#tipContent").html("机房中有正在使用的机架无法删除");
		 			$("#myModal").modal('show');
	   			}
			}, 
			error:function (error) {
				$("#tipContent").html("删除机房信息失败");
	 			$("#myModal").modal('show');
			}
			});
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
