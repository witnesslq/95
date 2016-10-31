$(function () {
	
	/**
	 加载数据
	*/
  $.ajax({
		url: "codeInfoQueryList.action",
		type: "post",
		dataType: 'json',
		success: function(data) {
	    var dataSet = [];
	   
		for(var i=0;i<data.length;i++){
		var menu = [];
		var item = data[i];
		var type="程序编码";	
		if(item.type == "0"){
			type="固定编码";
		}
		var displayvalue="是"
		if(item.displayvalue == "0"){
			displayvalue= "否";
		}
		var displayformat="是"
		if(item.displayformat == "0"){
			displayformat= "否";
		}
		var displayseq="是"
		if(item.displayseq == "0"){
			displayseq= "否";
		}
		menu.push("<input name=\"checked_info\" type=\"checkbox\" value=\""+item.id+"\">");   
		menu.push(item.codetypevalue);
		menu.push(type);
		menu.push(item.codevalue);
		menu.push(displayvalue);
		menu.push(item.codeformat);
		menu.push(displayformat);
		menu.push(item.seqvalue);
		menu.push(item.seqlength);
		menu.push(displayseq);
		menu.push(item.remark);
		
		dataSet.push(menu);
		}		
		 $("#example1").DataTable({
			 "data": dataSet,//数据源
			 "columns": [
		                    { "title": "<input name=\"checked_all_info\" type=\"checkbox\" value=\"\">" },
		                    { "title": "编码类别" },
		                    { "title": "编码类型" },
		                    { "title": "编码前缀"},
		                    { "title": "是否显示" },
		                    { "title": "编码格式" },
		                    { "title": "是否显示"},
		                    { "title": "序列号起始"},
		                    { "title": "序列号长度" },
		                    { "title": "是否显示" },
		                    { "title": "备注"}
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
	 $("iframe[name='adduser_content']").get(0).contentWindow.loadinfo();     
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
	var count=idstr.split(",").length;
	var Dtable = $('#example1').DataTable();

	$.ajax({
		url:"codeDelete.action?ids="+idstr, 
		type:"post",
		success:function (mm) {
		if("error"==mm.result){
			$("#tipContent").html("删除编码信息失败");
 			$("#myModal").modal('show');
			
			}else{
				    Dtable.rows('.selected').remove().draw(false);
					$("#tipContent").html("您删除了"+count+"条数据");
		 			$("#myModal").modal('show');	
			}	
		}, 
		error:function (error) {
			$("#tipContent").html("删除部门信息失败");
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
