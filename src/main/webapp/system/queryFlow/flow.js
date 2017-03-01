 $(function () { 
	 $("#content").mask("Waiting...");
	 	setTimeout(init, 500);
	 	//init();
	 	//$("#datetime").val(new Date());
	 	var userid;
	 	var username;
	 	//存储端口信息
	 	var ipaddress=[];
	 	//存储IP信息
	 	var ip=[];
	 	
	 	/**
		 加载数据
		*/
	  $.ajax({
			url: "getcustomerInfo.action",
			type: "post",
			dataType: 'json',
			success: function(data) {
		    var dataSet = [];
		   
			for(var i=0;i<data.length;i++){
			var menu = [];
			var item = data[i];
			menu.push("<input name=\"checked_info\" type=\"radio\" value=\""+item.customer_id+"\"  valuename="+item.customer_name+"   >");   
			menu.push(item.customer_name);
			dataSet.push(menu);
			}		
			 $("#example1").DataTable({
				 "data": dataSet,//数据源
				 "columns": [
			                    { "title": " " },
			                    { "title": "客户名称" }
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
	            "sProcessing": "处理中",
	            "sSearch": "搜索"
			 },
	             
			 });
				/**点击复选框，行选中*/
			 $('#example1').on('click', 'input[name="checked_info"]', function (event) {
				  if($(this).is(':checked')){
					  $(this).parent().parent().addClass('selected');
					  userid=$('input[name="checked_info"]:checked').val();
					  username=$('input[name="checked_info"]:checked').attr('valuename');
				  }else{	
					  $(this).parent().parent().removeClass('selected');
				  }
				 // event.stopPropagation();
			  });
				
				/**点击行事件*/
			 
			}
			});
	  
	  
	  	 $("button[name='adduser']").click(function(){
			 //$("iframe[name='adduser_content']").get(0).contentWindow.submit();
	  		 $("#user_id").val(username);
	  		 //清空 客户端口 、ip信息
	  		 $("#ip_address").val("");
	  		 $("#ip").val("");
	  		 ipaddress=[];
	  		 ip =[];
	  		 $("#userid").modal('hide'); 
		 });
	 	
	 	/**
		 显示用户名称信息
		*/
	 	$("#user_id").click(function(){ 
		    $("#userid").modal('show');   
		});
	 	
		 
		/**
		 显示端口信息
		*/
	 	$("#ip_address").click(function(){ 
	 		
	 		$("#ip_address").val("");
	 		$("#ip").val("");
		  	ipaddress=[];
		  	ip =[];
		    var url;
		    if (userid) {
		    	url = "getcustomer_port.action?userid="+userid+"";
		    } else {
		    	url = "getcustomer_port.action";
		    }
		    /**
			 根据用户信息加载ip数据
			*/
		    $.ajax({
				url: url,
				type: "post",
				dataType: 'json',
				success: function(data) {
			    var dataSet = [];
			   
				for(var i=0;i<data.length;i++){
					var menu = [];
					var item = data[i];
					menu.push("<input name=\"checked_info_ip\" type=\"radio\" value=\""+item.if_desc+"\"  ip=\""+item.ipaddress+"\" >");   
					menu.push(item.customer_name);
					menu.push(item.ipaddress);
					menu.push(item.if_desc);
					dataSet.push(menu);
				}		
				 $("#example2").DataTable({
					 "destroy": true,
					 "data": dataSet,//数据源
					 "columns": [
				                    { "title": "" },
				                    { "title": "客户名称" },
				                    { "title": "IP地址" },
				                    { "title": "端口" }
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
		            "sProcessing": "处理中",
		            "sSearch": "搜索"
				 },
		             
				 });
				 /**点击复选框，行选中*/
				 $('#example2').on('click', 'input[name="checked_info_ip"]', function (event) {
					  if($(this).is(':checked')){
						  $(this).parent().parent().addClass('selected');
					  }else{	
						  $(this).parent().parent().removeClass('selected');
					  }
					  event.stopPropagation();
				  });
				 
				 
				 /*全选 或取消
					*/
				  $("input[name='checked_all_info']").click(function(event){
					  if($(this).is(':checked')){
						  
						  $("input[name='checked_info_ip']").parent().parent().addClass('selected');  
						  $("input[name='checked_info_ip']").prop("checked",true);
					  }else{
						  $("input[name='checked_info_ip']").prop("checked",false);
						  $("input[name='checked_info_ip']").parent().parent().removeClass('selected');  
					  }
					  event.stopPropagation();
				  });
				 
				}
				});
		    
		    $("#ipaddress").modal('show');  
		  });
	 	
	 	/**
	 	 * 时间控件
	 	 */
	 	$('#datetime').datepicker({
			format: "yyyy-mm-dd",
		    language: "zh-CN",
		    startView: 0,
		    minViewMode: 0
 		});
	 	
	 	 /**
		  获取IP复选框的所有值
		*/
	 	$("#ip_submit").click(function(){ 
	 		$('input[name="checked_info_ip"]:checked').each(function(){ 
	 			ipaddress.push($(this).val()); 
	 			ip.push($(this).attr('ip')); 
	 		}); 
	 		$("#ip_address").val(ipaddress);
	 		$("#ip").val(ip);
	 		$("#ipaddress").modal('hide');  
		});
	 	
	 	/**
		  提交form表单
		*/
	 	$("#query_submit").click(function(){ 
	 		$("#content").mask("Waiting...");
 			setTimeout( submit, 500);
		});
	 	
	 	/**
		  打印excel
		*/
	 	$("#excle_flow").click(function(){ 
	 		//$("#mainForm").attr("action", "report_flow.action?userid="+user_id+"&ipaddress="+ipaddress+"&datetime="+datetime+"&ip="+ip+""); 
	 		$("#mainForm").submit();
		});
	 		
	 		
		 function submit(){
			// var userid=userid;
			 var ipaddress=$("#ip_address").val();
			 var ip=$("#ip").val();
			 var datetime=$("#datetime").val();
			 var url = "";
			 
			 if (userid!=null && userid!="" && ipaddress!=null && ipaddress!="") {
				 url="queryUserFlow.action?userid="+userid+"&ipaddress="+ipaddress+"&datetime="+datetime+"&ip="+ip+"";
			 } else if (userid!=null && userid!="") {
				 url="queryUserFlow.action?userid="+userid+"&datetime="+datetime+"";
			 } else if (ipaddress!=null && ipaddress!="") {
				 url="queryUserFlow.action?ipaddress="+ipaddress+"&datetime="+datetime+"&ip="+ip+"";
			 } else {
				 url="queryFlow.action?datetime="+datetime+"";
			 }
			 drowcharts(url);
			 
		} 
		 //首页加载总流量信息
		 function init(){
			 drowcharts("queryFlow.action");
		 }
		 
		 function drowcharts(url) {
			 var xdate = new Array();//出口正常流量
			 var inxdate = new Array(); //入口流量
			 var excdate = new Array(); //异常流量
			 var outavgvalue="0";
			 var outsumvalue="0";
			 var inavgvalue="0";
			 var insumvalue="0";
			 var i=0;
			 var j=0;
			 var k=0;
			 var map = {};  //存放带宽利用率
			 $.ajax({
					url:url, 
					dataType:"json",
					async:false, 
					type:"post",
					success:function (data) {
				    //对数据进行加工处理，也可以按你自己的需求进行处理
					//if(data!=null && data.length != 0){
                         for(var w=0;w<data.length;w++){
	                         	  if(data[w].utilhdxflag==0 || data[w].utilhdxflag==1) {
	                         		  //封装出口流量
	                         		  if (data[w].entity=="出口") {
	                         			 xdate[i]=new Array();
		                         		 xdate[i][0]=new Date(data[w].collecttime).getTime()+28800000;
			                         	 //xdate[i][1]=parseFloat((parseFloat(data[w].utilhdx)*8*1000*1000/1024/1024/300*8).toFixed(2));
		                         		 xdate[i][1]=parseFloat(data[w].utilhdx);
			                         	 map[xdate[i][1]]=data[w].utilhdxperc;
			                         	 
			                         	//平均流量
		                         	    if(data[w].avgthevalue) {
		                         		   outavgvalue=data[w].avgthevalue;
		                         	    }
		                         	    //最大流量
		                         	    if(data[w].sumthevalue) {
		                         		   outsumvalue=data[w].sumthevalue;
		                         	    }
			                         	i++;
	                         		  //封装入口流量	  
	                         		  } else {
	                         			 inxdate[j]=new Array();
	                         			 inxdate[j][0]=new Date(data[w].collecttime).getTime()+28800000;
	                         			 //inxdate[j][1]=parseFloat((parseFloat(data[w].utilhdx)*8*1000*1000/1024/1024/300*8).toFixed(2));
	                         			 inxdate[j][1]=parseFloat(data[w].utilhdx);
	                         			//console.log(inxdate[j][1]);
			                         	 map[inxdate[j][1]]=data[w].utilhdxperc;
			                         	 
		                         	     //平均流量
		                         	     if(data[w].avgthevalue) {
		                         		   inavgvalue=data[w].avgthevalue;
		                         	     }
		                         	     //最大流量
		                         	     if(data[w].sumthevalue) {
		                         		   insumvalue=data[w].sumthevalue;
		                         	     }
			                         	 j++;
	                         		  }
		                         	   
	                               } else {
	                            	   /*excdate[k]=new Array();
	                            	   excdate[k][0]=new Date(data[w].collecttime).getTime()+28800000;
	                            	   excdate[k][1]=parseFloat(data[w].utilhdx);
		                         	   map[excdate[k][1]]=data[w].utilhdxperc;
		                         	   k++;*/
	                               }
                         }        
                         
                       //设置初始值
                         Highcharts.setOptions({
	                            lang: {
	                            resetZoom: "返回",
	                            resetZoomTitle: "回到初始状态"
	                            }
                         });
                         
                         $('#container').highcharts({
         		            chart: {
         		                zoomType: 'x'
         		            },
         		            credits: {
         		                enabled: false
         		           },
         		            title: {
         		                text: '流量汇总'
         		            },
         		            subtitle: {
         		            	text:"出口平均流量为: "+outavgvalue+"M --出口峰值流量为:"+outsumvalue+"M <br/> 入口平均流量为: "+inavgvalue+"M --入口峰值流量为:"+insumvalue+"M"
         		            },
         		            xAxis: {
         		                type: 'datetime',
         		                dateTimeLabelFormats: {
         		                    millisecond: '%H:%M:%S.%L',
         		                    second: '%H:%M:%S',
         		                    minute: '%H:%M',
         		                    hour: '%H:%M',
         		                    day: '%m-%d',
         		                    week: '%m-%d',
         		                    month: '%Y-%m',
         		                    year: '%Y'
         		                }
         		            },
         		            tooltip: {
         				            formatter: function () {
         				                return '<span><b>时间：'+getMyDate(this.x)+'</b><br/><b>'+this.series.name+':'+ this.y +
         				                    'M</b><br/> <b>带宽利用率:' + map[this.y] + '%</b></span>';
         				            }
         		            },
         		            yAxis: {
         		                title: {
         		                    text: 'M'
         		                }
         		            },
         		            legend: {
         		                enabled: false
         		            },
         		            plotOptions: {
         		                area: {
         		                    fillColor: {
         		                        linearGradient: {
         		                            x1: 0,
         		                            y1: 0,
         		                            x2: 0,
         		                            y2: 1
         		                        },
         		                        stops: [
         		                            [0, Highcharts.getOptions().colors[0]],
         		                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
         		                        ]
         		                    },
         		                    marker: {
         		                        radius: 2
         		                    },
         		                    lineWidth: 1,
         		                    states: {
         		                        hover: {
         		                            lineWidth: 1
         		                        }
         		                    },
         		                    threshold: null
         		                }
         		            },
         		            series: [{
         		                type: 'area',
         		                name: '出口流量',
         		                data: xdate
         		            },
         		            {
         		                type: 'area',
         		                name: '入口流量',
         		                color:'#87CEFA',
         		                data: inxdate
         		            },
         		            {
         		                type: 'area',
         		                name: '异常流量',
         		                color: '#BF0B23',
         		                data: excdate
         		            }]
         		        });
                         $("#content").unmask();
                 /*} else {
                 	 $("#content").unmask();
                 	 $("#datashow").html("<div style='width:500px;height:380px;position:absolute;left:50%;margin-left:-250px;top:90%;margin-top:-190px;text-align:center;'><span>数据不存在</span></div>");
                 }*/
                 
                
                 
					}, 
					error:function (error) {
						alert("error");
					}
				});
		 }
		 
		 /*
			按天，月，年查询的掩码日期控件
		 */
		$("input[data-mask]").inputmask("", {
			"placeholder": "yyyy-mm-dd",
			"clearIncomplete": true
		});
		$('.dropdown-menu.date').on("click", "li", function(event) {
			var triggerBtn = $(event.delegateTarget).prev("button");
			triggerBtn.contents().first().remove();
			triggerBtn.prepend($(event.target).text());

			var viewMode = $(this).attr("data-view-mode"),
				maskedInput = $(event.delegateTarget).parent("div.input-group-btn").next("input[data-mask]");
			if (viewMode == 0) {
				maskedInput.inputmask("yyyy-mm-dd", {
					"placeholder": "yyyy-mm-dd"
				});
				$(".daterangepicker").remove();
				$('#datetime').datepicker("remove");
				$("#datetime").removeAttr("readonly");
				$('#datetime').datepicker({format: "yyyy-mm-dd",language: "zh-CN",startView:0, minViewMode: 0});

			} else if (viewMode == 1) {
				maskedInput.inputmask("y-m", {
					"placeholder": "yyyy-mm"
				});
				$(".daterangepicker").remove();
				$('#datetime').datepicker("remove");
				$("#datetime").removeAttr("readonly");
				$('#datetime').datepicker({format: "yyyy-mm",language: "zh-CN",startView:1, minViewMode: 1});
				
			} else if (viewMode == 2) {
				maskedInput.inputmask("y", {
					"placeholder": "yyyy"
				});
				$(".daterangepicker").remove();
				$('#datetime').datepicker("remove");
				$("#datetime").removeAttr("readonly");
				$('#datetime').datepicker({format: "yyyy",language: "zh-CN",startView:2, minViewMode: 2});
			} else if (viewMode == 3) {
				$('#datetime').inputmask('remove');
				$('#datetime').datepicker("remove");
				$('#datetime').attr("readonly","readonly");
				$('#datetime').daterangepicker({
					"locale": {
				        "format": "YYYY-MM-DD",
				        "separator": " ~ ",
				        "applyLabel": "确认",
				        "cancelLabel": "取消",
				        "fromLabel": "从",
				        "toLabel": "到",
				        "customRangeLabel": "Custom",
				        "weekLabel": "W",
				        "daysOfWeek": [
				            "周日",
				            "周一",
				            "周二",
				            "周三",
				            "周四",
				            "周五",
				            "周六"
				        ],
				        "monthNames": [
				            "一月",
				            "二月",
				            "三月",
				            "四月",
				            "五月",
				            "六月",
				            "七月",
				            "八月",
				            "九月",
				            "十月",
				            "十一月",
				            "十二月"
				        ],
				        "firstDay": 1
				    },
				    "linkedCalendars": false,
				    "startDate":"2017-01-01",
				    "endDate": new Date(),
				    "drops": "down",
				    "opens": "left",
				    "format":"YYYY-MM-DD"
				},function(start, end) {
					console.log(start.format('YYYY-MM-DD') + ' ~ ' + end.format('YYYY-MM-DD'));
				});
			}
			maskedInput.attr("data-view-mode", viewMode);
		});

		$("#dateForRatio").on("click","button.query-btn",function(event) {
			console.log($(event.delegateTarget).children("input[data-mask]").val());
		});

		$("#dateForAlarm").on("click","button.query-btn",function(event) {
			console.log($(event.delegateTarget).children("input[data-mask]").val());
		});

		 
	});
 
		/*
		 * 日期格式化
		 */
		function getMyDate(str){  
            var oDate = new Date(str-28800000),  
            oYear = oDate.getFullYear(),  
            oMonth = oDate.getMonth()+1,  
            oDay = oDate.getDate(),  
            oHour = oDate.getHours(),  
            oMin = oDate.getMinutes(),  
            oSen = oDate.getSeconds(),  
            oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间  
            return oTime;  
        };
        
      //补0操作  
        function getzf(num){  
            if(parseInt(num) < 10){  
                num = '0'+num;  
            }  
            return num;  
        } 
