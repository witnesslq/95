
	
	/**
	 加载数据
	*/
function loadInfo(){
	$("#example1").html("");
  data={
		 "ip.status":"01",
		 length:1
		 };
	
  $.ajax({
		url: "queryIPInfo.action",
		type: "post",
		data:data,
		dataType: 'json',
		success: function(data) {
	    var dataSet = [];
	   
		for(var i=0;i<data.length;i++){
			 var menu = [];
			 var status="";
			 var item = data[i];
			 if (item.status =='01'){
				 status= '空闲';
				}else if(item.status =='02'){
					status= '预占';
				}else if(item.status =='03'){
					status= '实占';
				}else if(item.status =='04'){
					status= '使用中';
				}else{
					status= '未知';
				}
		
		menu.push("<input name=\"checked_info\" type=\"radio\" value='ipid:"+item.id+":"+item.ipadd+"'>");   
		menu.push(item.ipadd);
		menu.push(item.ipsegname);
		menu.push(status);
	    dataSet.push(menu);
		}
		 $("#example1").DataTable({
			 "data": dataSet,//数据源
			 "columns": [
		                    { "title": "","class":"col-xs-1" },
		                    { "title": "IP地址" ,"class":"font-center"},
		                    { "title": "所有网段" ,"class":"font-center"},
		                    { "title": "状态" ,"class":"font-center"}
		                ],
		     "bLengthChange": false, //去除每页显示条数
		     "iDisplayLength":6,//每页显示条数
		     "bSort": false,//去除排序
		     "sDom": '<"topinfo"fp>rt<"bottom"><"clear">',//自定义布局  
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
			/**点击行事件*/
		 $('#example1').on('click', 'tr', function (event) {	
			  if($(this).find("input[name='checked_info']").is(':checked')){	
			  }else{
				  $(this).find("input[name='checked_info']").prop("checked",true);
			  } 
			  event.stopPropagation();
			});
		}
		});
	window.parent.$("#IpInfo").modal('show');
}

/**
获取选中项
*/
function getselectinfo(){
 var checkboxval=$("input[name='checked_info']:checked"); 
 return checkboxval.val();
}
