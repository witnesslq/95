$(function () {
	$('#enddate').datepicker({
		  format: 'yyyy-mm-dd',
	      weekStart: 1,
	      autoclose: true,
	      todayBtn: 'linked',
	      language: 'cn'
	    });
	$("#no").val(createCode('CUSTNO'));
	$("#type").html("<option value='03'>互联网客户</option>");
	$("#customerlevel").html(getOption(queryDictionary('CUSTOMERLEVEL',null)));
	$("#customerproperty").html(getOption(queryDictionary('CUSTOMERPROPERTY',null)));
	var privaceinfo=[{id:'BEIJING',text:'北京市'},                        
						{id:'HEBEI',text:'河北省'},
						{id:'HENAN',text:'河南省'},
						{id:'YUNNAN',text:'云南省'},
						{id:'LIAONING',text:'辽宁省'},
						{id:'HEILONGJIANG',text:'黑龙江省'},
						{id:'HUNAN',text:'湖南省'},
						{id:'ANHUI',text:'安徽省'},
						{id:'SHANDONG',text:'山东省'},
						{id:'XINJIANG',text:'新疆省'},
						{id:'JIANGSU',text:'江苏省'},
						{id:'ZHEJIANG',text:'浙江省'},
						{id:'JIANGXI',text:'江西省'},
						{id:'HUBEI',text:'湖北省'},
						{id:'GUANGXI',text:'广西省'},
						{id:'GANSU',text:'甘肃省'},
						{id:'SHANXI',text:'山西省'},
						{id:'NEIMENG',text:'内蒙古'},
						{id:'SHAANXI',text:'陕西省'},
						{id:'JILING',text:'吉林省'},
						{id:'FUJIAN',text:'福建省'},
						{id:'GUIZHOU',text:'贵州省'},
						{id:'GUANGDONG',text:'广东省'},
						{id:'QINGHAI',text:'青海省'},
						{id:'XIZANG',text:'西藏'},
						{id:'SICHUAN',text:'四川省'},
						{id:'NINGXIA',text:'宁夏省'},
						{id:'HAINAN',text:'海南省'},
						{id:'TAIWAN',text:'台湾省'},
						{id:'XIANGGANG',text:'香港'},
						{id:'AOMEN',text:'澳门'}]
										
	$("#province").html(getOption(privaceinfo));	
	$("#customerfield").html(getOption(queryDictionary('CUSTFIELD',null)));
  $.ajax({
		url:"cropDeptQuery", 
		dataType: 'json',
		type:"post",
		success:function (data) {
	        $("#regionid").append('<option>'+''+'</option>');
	       for(var i=0;i<data.length;i++){
		       $("#regionid").append('<option value="'+data[i].id+'">'+data[i].text+'</option>');
	       }
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取信息失败！");
			 window.parent.$("#myModal").modal('show'); 
			
		}
	}); 


  
     function getOption(data){
    	 strs="<option value=''>"+""+"</option>";
    	 for(var i=0;i<data.length;i++){
    		 strs+="<option value='"+data[i].id+"'>"+data[i].text+"</option>";
    		}
    	 return strs;
     }
     
     $("#save_all").click(function(){
    	 submit();
     });
     $("#return_back").click(function(){
    	 location.href="customerList.jsp";
     });
     
     
     function submit(){
    	 var no =$("#no").val();
    	 var type =$("#type").val();
    	 var customerlevel =$("#customerlevel").val();
    	 var name =$("#name").val();
    	 var sortname =$("#sortname").val();
    	 var customerproperty =$("#customerproperty").val();
    	 var parentid =$("#parentid").val();
    	 var manager =$("#manager").val();
    	 var corporate =$("#corporate").val();
    	 var province =$("#province").val();

    	 var contactname =$("#contactname").val();
    	 var mobilephone =$("#mobilephone").val();
    	 var contactphone =$("#contactphone").val();
    	 var contactaddress =$("#contactaddress").val();

    	 var customerfield =$("#customerfield").val();
    	 var icpno =$("#icpno").val();
    	 var sitename =$("#sitename").val();
    	 var domainname =$("#domainname").val();
    	 var skillpeople =$("#skillpeople").val();
    	 var subdomain =$("#subdomain").val();
    	 var content =$("#content").val();
    	 var bandwidth =$("#bandwidth").val();
    	 var method =$("#method").val();
    	 var dispatch =$("#dispatch").val();
    	 var prot =$("#prot").val();
    	 var regionid =$("#regionid").val();
    	 var registername =$("#registername").val();
    	 var companyname =$("#companyname").val();
    	 var enddate =$("#enddate").val();
    	 var slano =$("#remark").val();
    	 var remark =$("#remark").val();

    	 
     if(no!=""&&type!=""&&customerlevel!=""&&name!=""){
    		var data = {
    				"customer.no":no,
    				"customer.name":name,
    				"customer.type":type,
    				"customer.customerlevel":customerlevel,
    				"customer.contactname":contactname,
    				"customer.mobilephone":mobilephone,
    				"customer.contactphone":contactphone,
    				"customer.contactaddress":contactaddress,
    				"customer.customerproperty":customerproperty,
    				"customer.parentid":parentid,
    				"customer.manager":manager,			
    				"customer.sortname":sortname,			
    				"customer.corporate":corporate,			
    				"customer.customerfield":customerfield,	
    				"customer.icpno":icpno,					
    				"customer.sitename":sitename,				
    				"customer.domainname":domainname,			
    				"customer.skillpeople":skillpeople,		
    				"customer.subdomain":subdomain,			
    				"customer.content":content,				
    				"customer.bandwidth":bandwidth,			
    				"customer.method":method,					
    				"customer.dispatch":dispatch,	
    				"customer.prot":prot,				
    				"customer.province":province,				
    				"customer.regionid":regionid,				
    				"customer.registername":registername,		
    				"customer.companyname":companyname,		
    				"customer.slano":slano,
    				"customer.enddate":enddate,
    				"customer.remark":remark	
    				
    	};
    	 
    	 $.ajax({
    			url:"saveCustomer.action", 
    			data:data,
    			dataType:"json",
    			async:false, 
    			type:"post",
    			success:function (mm) {
    				if("error"==mm.result){
    					 window.parent.$("#tipContent").html("客户信息添加失败！");
    					 window.parent.$("#myModal").modal('show'); 
    					
    				}else{	
    					 window.parent.$("#tipContent").html("客户信息添加成功！");
    					 window.parent.$("#myModal").modal('show'); 
    					 location.href="customerList.jsp";
    				}
    			}, 
    			error:function (error) {
    				 window.parent.$("#tipContent").html("客户信息添加失败！");
    				 window.parent.$("#myModal").modal('show'); 
    		    }
    			});
    	
    }else{
    	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
    	 window.parent.$("#myModal").modal('show'); 
    }	
    } 
     

  });


 
