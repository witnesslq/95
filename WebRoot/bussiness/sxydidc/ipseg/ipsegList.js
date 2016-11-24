$(function () {
	
	/**
	 加载数据
	*/	
	table= $("#example1").DataTable({	
             "processing": true,//开启读取服务器数据时显示正在加载中……特别是大数据量的时开启此功能比较好
             "serverSide": true,//开启服务器模式，使用服务器端处理配置datatable。
             "processing": true,//是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个)
//             "sAjaxSource": "sysLogInfoQuery.action", //给服务器发请求的url
             "searching": false,    //禁用原生搜索
             "ajax": {
                 "url":"queryIPSegInfo.action",
                 "data": function ( d ) {
                     //添加额外的参数传给服务器       
                     d.search=$("#searchinfo").val();	
//              				 d.ipsegid=$("#ipsegid").val(),
//                             d.deviceid=$("#deviceid").val(),
//                       		 d.customerid=$("#customerid").val(),
//                       		 d.statusName=$("#statusName").val()
              		 
                 }},
             "bSort": false,
             
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
            "sProcessing": "处理中...",
            "sSearch": "模糊查询",
             },
            "aoColumns": [ //这个属性下的设置会应用到所有列，按顺序没有是空
                           {"mData": function(obj){
                        	   return '<input name="checked_info" type="checkbox" value="'+obj.id+'">';}},
                           {"mData": 'name'}, //listmodel 表示发请求时候本列的列明，返回的数据中相同下标名字的数据会填充到这一列
                           {"mData": 'startip'},
                           {"mData": 'endip'},
                           {"mData": 'netmask'},
                           {"mData": 'customername'},
                           {"mData": 'dcname'},
                           {"mData": function(obj){
                        	   return getstatus( obj.status);
                           }},
                           {"mData": 'totalIP'},
                           {"mData": 'freeIP'},
                           
                           {"mData": 'preIP'},
                           {"mData": 'factIP'}
                          ] ,                    

		 });

		 function getstatus(status){
			 if (status =='01'){
					return '空闲';
				}else if(status =='02'){
					return '散租';
				}else if(status =='03'){
					return '整租';
				}else if(status =='04'){
					return '预占';
				}
		 }
		 
			
		 $("#btn-simple-search").click(function(){
		        //出发dt的重新加载数据的方法
		        table.ajax.reload();
		        //获取dt请求参数
		        var args = table.ajax.params(); 
		    });
		 $("#btn-advanced-search").click(function(){
			  //出发dt的重新加载数据的方法
		        table.ajax.reload();
		        //获取dt请求参数
		        var args = table.ajax.params(); 
		 });
//		 $("#toggle-advanced-search").click(function(){
//				$("i",this).toggleClass("fa-angle-double-down fa-angle-double-up");
//				$("#div-advanced-search").slideToggle("fast");
//			});
		 
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
		});