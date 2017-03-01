$(function () {
	
	/**
	 加载数据
	*/
  $.ajax({
		url: "queryCustomerInfoByUser.action",
		type: "post",
		dataType: 'json',
		success: function(data) {
	    var dataSet = [];
	   
		for(var i=0;i<data.length;i++){
			 var menu = [];
		var item = data[i];
	     var type="";
	     if (item.type =='01'){
	    	 type= '集团客户';
			}else if(item.type =='03'){
				type= '互联网客户';
			}
	     var status="";
	     if (item.status =='01'){
	    	 status= '离线客户';
			}else if(item.status =='02'){
				status= '在线客户';
			} 
	     var customerproperty="";
	     if (item.customerproperty =='A'){
	    	    customerproperty= '商业客户';
			}else if(item.customerproperty =='B'){
				customerproperty= '公免客户';
			}else if(item.customerproperty =='C'){
				customerproperty= 'ISP服务商';
			}else if(item.customerproperty =='D'){
				customerproperty= 'IDC业务批发商';
			}
	     var customerfield="";
	     if (item.customerfield =='CITYCUST'){
	    	    customerfield= '市公司客户';
			}else if(item.customerfield =='PROCUST'){
				customerfield= '省公司客户';
			} 
		menu.push("<input name=\"checked_info\" type=\"checkbox\" value=\""+item.id+"\">");   
		menu.push("<a href='javascript:void(0);' name='detailInfo' id='"+item.id+":"+item.type+"' >"+item.no+"</a>");
		menu.push(item.name);
		menu.push(type);
		menu.push(item.customerlevel+"类集团");
		menu.push(status);
		menu.push(customerproperty);
		
		menu.push(customerfield);
		menu.push(item.managername);
		menu.push(item.managerphone);
		menu.push(item.contactname);
		menu.push(item.mobilephone);
		menu.push(item.custkey);  
		menu.push(item.createdate); 
		dataSet.push(menu);
		}
		 $("#example1").DataTable({
			 "data": dataSet,//数据源
			 "columns": [
		                    { "title": "<input name=\"checked_all_info\" type=\"checkbox\" value=\"\">" },
		                    { "title": "客户编号" },
		                    { "title": "客户名称" },
		                    { "title": "客户类型" },
		                    { "title": "客户级别"},
		                    { "title": "客户状态"},
		                    { "title": "客户性质"},
		                    { "title": "客户域级" },
		                    { "title": "客户经理"},
		                    { "title": "客户经理手机号"},
		                    { "title": "联系人"},
		                    { "title": "联系人手机号"},
		                    { "title": "客户密钥"},
		                    { "title": "开通日期"}
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
		  
		  $("a[name='detailInfo']").click(function(){
			  var id=$(this).attr("id").split(":")[0];
			  var type=$(this).attr("id").split(":")[1];
			  var params=queryOccuipedResourceCount(id);
			  
			  if(params!=null&&params!=''){
			    	$.ajax({
						url:"queryOrderResourceCount.action", 
						data:{"order.custid":id},
						dataType:"json", 
						type:"post",
						success:function (msg) {
							serverCount=msg.serverCount;
							rackCount=msg.rackCount;
							useatCount=msg.useatCount;
							ipsegCount=msg.ipsegCount;
							ipCount=msg.ipCount;
							portCount=msg.portCount;
							busOrdCount=msg.busOrdCount;
							serOrdCount=msg.serOrdCount;
							var titlt="";
							var params="";
							var url="";
							if(type=='01'){
								title="集团客户信息展示";
								params="serverCount="+serverCount+"&rackCount="+rackCount+"&useatCount="+useatCount+"&ipsegCount="+ipsegCount+"&ipCount="+ipCount+"&portCount="+portCount+"&busOrdCount="+busOrdCount+"&serOrdCount="+serOrdCount+params;
								url = "bussiness/sxydidc/customer/groupView.jsp?id="+id+"&type="+type+"&"+params;
							}else if(type=='03'){
								title="互联网客户信息展示";
								params="serverCount="+serverCount+"&rackCount="+rackCount+"&useatCount="+useatCount+"&ipsegCount="+ipsegCount+"&ipCount="+ipCount+"&portCount="+portCount+"&busOrdCount="+busOrdCount+"&serOrdCount="+serOrdCount+params;
								url = "bussiness/sxydidc/customer/customerView.jsp?id="+id+"&type="+type+"&"+params;
							}
							 location.href=url; 
							
						}, 
						error:function (error) {
							 window.parent.$("#tipContent").html("统计资源数量失败！");
				        	 window.parent.$("#myModal").modal('show'); 				
						}
					});
			  event.stopPropagation();
			  }
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
	 location.href="customerAdd.jsp"; 
		});
/**
修改密钥
*/
$("#edit_user").click(function(){ 
	var idstr=getselectinfo();
	$.ajax({
		url:"editCustomerKey.action?ids="+idstr, 
		dataType:"json", 
		type:"post",
		success:function (msg) {
       		if("error"==msg.result){
       		 window.parent.$("#tipContent").html("修改客户密钥失败！");
        	 window.parent.$("#myModal").modal('show'); 
   				
   			}else{
   			 window.parent.$("#tipContent").html("修改客户密钥成功！");
        	 window.parent.$("#myModal").modal('show'); 
        	 window.location.reload();
   			}
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("修改客户密钥失败！");
        	 window.parent.$("#myModal").modal('show'); 	
        	
		}
		});
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
  
  
  var preServerCount;
  var preRackCount;
  var preUseatCount;
  var preIpsegCount;
  var preIpCount;
  var prePortCount;
  function queryOccuipedResourceCount(id){
	  var params="";
      $.ajax({
  		url:"queryOccuipedResourceCount.action", 
  		data:{"customerId":id},
  		dataType:"json", 
  		async:false,
  		type:"post",
  		success:function (msg) {
  			preServerCount=msg.serverCount;
  			preRackCount=msg.rackCount;
  			preUseatCount=msg.useatCount;
  			preIpsegCount=msg.ipsegCount;
  			preIpCount=msg.ipCount;
  			prePortCount=msg.portCount;
  			params="&preServerCount="+preServerCount+"&preRackCount="+preRackCount+"&preUseatCount="+preUseatCount+"&preIpsegCount="+preIpsegCount+"&preIpCount="+preIpCount+"&prePortCount="+prePortCount;    		
  		}, 
  		error:function (error) {
  			 window.parent.$("#tipContent").html("统计客户预占资源数量失败！");
        	 window.parent.$("#myModal").modal('show'); 
  		}
  	});
  	return params;
  }
  });

