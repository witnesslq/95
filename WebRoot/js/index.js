/*问候语
    
function remind() {
         var now = new Date(), hour = now.getHours();
         if (hour > 4 && hour < 6) { $("#labelwelcome").html("凌晨好！") }
         else if (hour < 9) { $("#labelwelcome").html("早上好！") }
         else if (hour < 12) { $("#labelwelcome").html("上午好！") }
         else if (hour < 14) { $("#labelwelcome").html("中午好！") }
         else if (hour < 17) { $("#labelwelcome").html("下午好！") }
         else if (hour < 19) { $("#labelwelcome").html("傍晚好！") }
         else if (hour < 22) { $("#labelwelcome").html("晚上好！") }
         else { $("#labelwelcome").html("夜深了，注意休息！") }
         setTimeout("remind()",30000);
     } */
  /*
   获取顶级菜单信息
  */
var a=[{"actiontype":1,"id":"e4adc42bcfe94f25b2447a8560f879c7","image":"images/Icon/90.png","ordernum":1,"pid":"0","remark":"","title":"工作台","url":"","whetherpublic":""},{"actiontype":1,"id":"38169bd229b343eb97449beb5c55713c","image":"images/Icon/83.png","ordernum":1,"pid":"e4adc42bcfe94f25b2447a8560f879c7","remark":"","title":"业务受理","url":"flow/bussiness/util/flowRequest.jsp","whetherpublic":""},{"actiontype":1,"id":"5c5146015afd4f93bc93634f01979151","image":"images/Icon/27.png","ordernum":2,"pid":"e4adc42bcfe94f25b2447a8560f879c7","remark":"","title":"服务受理","url":"flow/bussiness/util/serviceRequest.jsp","whetherpublic":""},{"actiontype":1,"id":"0a013853d6d9463b8a13d6423f86d430","image":"images/Icon/25.png","ordernum":3,"pid":"e4adc42bcfe94f25b2447a8560f879c7","remark":"","title":"待办工单","url":"flow/deployflow/flowDB.jsp","whetherpublic":""},{"actiontype":1,"id":"18d67cae8c064aa7819e0a34fe952327","image":"images/Icon/24.png","ordernum":4,"pid":"e4adc42bcfe94f25b2447a8560f879c7","remark":"","title":"已办工单","url":"flow/deployflow/flowYB.jsp","whetherpublic":""},{"actiontype":1,"id":"c9578c4547db4d90929433714951f0ce","image":"images/Icon/37.png","ordernum":5,"pid":"e4adc42bcfe94f25b2447a8560f879c7","remark":"","title":"我的客户","url":"","whetherpublic":""},{"actiontype":1,"id":"c218b9a4bfd14aabba4cf34c8133c518","image":"images/Icon/41.png","ordernum":6,"pid":"e4adc42bcfe94f25b2447a8560f879c7","remark":"","title":"我的自有业务","url":"","whetherpublic":""},{"actiontype":1,"id":"2cdaffd9d019472d9037af559dc46b8c","image":"images/Icon/71.png","ordernum":7,"pid":"e4adc42bcfe94f25b2447a8560f879c7","remark":"","title":"客户密钥查询","url":"bussiness/sxydidc/custkey/custKeyList.jsp","whetherpublic":""},{"actiontype":1,"id":"a18c77771bfa460592c2caca3b06fc69","image":"images/Icon/10.png","ordernum":7,"pid":"e4adc42bcfe94f25b2447a8560f879c7","remark":"","title":"信息安全","url":"","whetherpublic":""},{"actiontype":1,"id":"5c7b7cb7e2d146b5902cd6fab18d0768","image":"images/Icon/83.png","ordernum":1,"pid":"c218b9a4bfd14aabba4cf34c8133c518","remark":"","title":"自有业务受理","url":"flow/bussiness/util/flowRequestSYS.jsp","whetherpublic":""},{"actiontype":1,"id":"9c3a63e0456449fdafc202130933a26f","image":"images/Icon/11.png","ordernum":4,"pid":"c218b9a4bfd14aabba4cf34c8133c518","remark":"","title":"自有业务系统","url":"bussiness/sxydidc/ownsysinfo/ownsysinfoList.jsp","whetherpublic":""},{"actiontype":1,"id":"658a0013f74640549c7eb2823f1d5956","image":"images/Icon/15.png","ordernum":5,"pid":"c218b9a4bfd14aabba4cf34c8133c518","remark":"","title":"自有业务订单管理","url":"bussiness/sxydidc/ownsysinfo/ownsysInfoOrderList.jsp","whetherpublic":""},{"actiontype":1,"id":"7869eae8472d4c1ab2ee1b10654fb136","image":"images/Icon/25.png","ordernum":50,"pid":"a18c77771bfa460592c2caca3b06fc69","remark":"","title":"信息安全受理","url":"bussiness/sxydidc/safeinfo/sendEmail.jsp","whetherpublic":""},{"actiontype":1,"id":"ee38afa853bf48359419923aac5fca59","image":"images/Icon/25.png","ordernum":51,"pid":"a18c77771bfa460592c2caca3b06fc69","remark":"","title":"信息安全处理","url":"bussiness/sxydidc/safeinfo/safeInfoList.jsp","whetherpublic":""},{"actiontype":1,"id":"76f6561379e04cdd974cd35c6e6f0b53","image":"images/Icon/25.png","ordernum":53,"pid":"a18c77771bfa460592c2caca3b06fc69","remark":"","title":"常用邮箱维护","url":"bussiness/sxydidc/sectmail/sectmailList.jsp","whetherpublic":""},{"actiontype":1,"id":"ba8d4daa8a0c4508b6c4d48b3e30100b","image":"images/Icon/25.png","ordernum":54,"pid":"a18c77771bfa460592c2caca3b06fc69","remark":"","title":"客户安全信息员邮箱维护","url":"bussiness/sxydidc/sectmail/sectcustmailList.jsp","whetherpublic":""},{"actiontype":1,"id":"63ced11fc70248649f548b0ddac6a4f4","image":"images/Icon/93.png","ordernum":2,"pid":"c9578c4547db4d90929433714951f0ce","remark":"","title":"客户管理","url":"bussiness/sxydidc/customer/customerList.jsp","whetherpublic":""},{"actiontype":1,"id":"36ce10a8b3e841a9b3d3c55996154d5c","image":"images/Icon/21.png","ordernum":4,"pid":"c9578c4547db4d90929433714951f0ce","remark":"","title":"订单管理","url":"bussiness/sxydidc/order/queryOrder.jsp","whetherpublic":""},{"actiontype":1,"id":"0dbcc03140af4c9ab420714c42e6fc18","image":"images/Icon/22.png","ordernum":5,"pid":"c9578c4547db4d90929433714951f0ce","remark":"","title":"合同管理","url":"bussiness/sxydidc/contract/queryContract.jsp","whetherpublic":""}];
/*
 * 左侧菜单信息*/
function getTopMenu(){
	 $.ajax({
   		url:"allMenuQuery.action",
	    type:"post",
	    dataType:'json',
	    success:function(data){
		 var barinfo="";
		 var allRootId=[];
		 var barlength=data.length;
		 for(var i=0;i<barlength;i++){//外层菜单
			 if(data[i].pid==0){ 
				 barinfo=barinfo+'<li class="topbar" id="'+data[i].id+'"><a href="#"><i class="fa fa-link"></i><span>'+data[i].title +'</span></a> <ul class="treeview-menu" id="'+data[i].id+'_ul"></ul></li>';
				 allRootId.push(data[i].id)
			 }
		 }
	    	$(".sidebar-menu").append(barinfo); 
	    	for(var j=0;j<allRootId.length;j++){//二层菜单
	    		 for(var i=0;i<barlength;i++){
					 if(allRootId[j]==data[i].pid){ 					 
						$("#"+data[i].pid+"_ul").append('<li><a href="'+data[i].url+'" target="content">'+data[i].title +'</a></li>');
					 }
				 }	
	    	}
	    	
	     }    
	  	});
}
	  
function myitemclick(item){
	$.ajax({
   		url:"menuQueryS.action",
   		data:{"tid":item.id},
	    type:"post",
	    success:function(data){
		      $("#accordion1").html(data);
		      accordion._render();
        	  accordion.setHeight($(".l-layout-center").height() - 25);
	     }
  		});
}


/*
 获取原来的密码
*/
function modifyPassword(){
	$.ajax({
		 url:"treeuserGetById.action",
		 data:"id="+uid,
		 type:"post",
		 dataType:'json',
		 ansys:false,
		 success:function(mess){
			  $("#oldpass").val(mess.userpass);
			  openPass();
		 }
	});
	return false;
}
/*
    弹出一个修改密码的窗口
  */   
function openPass(){
 $.ligerDialog.open({ target: $("#XGMM"),width:400,height:200,title:"密码修改",allowClose:true,isHidden:true,
		buttons: [ 
			    { text: '确定', onclick: function (item, dialog) { 
				   	var np1 = $("#newpass").val();
					var np2 = $("#newpass1").val();
					if(checkXT()){
					 	$.ajax({
					      url:"userModifyPass.action",
					      data:{"userpass":np1},
					      type:"post",
					      success:function(mess){
					      	if(mess == "success"){
						        $.ligerDialog.success('修改成功！');
						        dialog.hide();
						        $("#newpass").val("");
						        $("#newpass1").val("");
						        $("#oldpass").val("");
					        }else{
					         	$.ligerDialog.success('修改失败！');
					        }
					      }
				     	});
					  }
				    } 
				  },
	    		{ text: '取消', onclick: function (item, dialog) { 
					dialog.hide();
			    } 
			    }
	   		] 
	   });
	  return false;
}
	/**
	 密码修改时校验数据
	 */
	function checkXT(){
		var np1 = $("#newpass").val();
		var np2 = $("#newpass1").val();
		var passReg = /[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/;
		if(np1 == null || np1 == ""){
		 	$.ligerDialog.alert("密码不能为空！","提示","warn");
		     return false;
		}
		if(!passReg.test(np1)){
			$.ligerDialog.alert("密码必须为数字字母混合！","提示","warn");
		     return false;
		}
		if(np1.length < 8){
		 	$.ligerDialog.alert("密码长度不能小于8！","提示","warn");
		     return false;
		}
	   if(np1 != np2){
		  	$.ligerDialog.alert("两次密码不同！","提示","warn");
		   	return false;
	   }
	 return true;
	}
	    
/*
打开用户的详细信息弹窗
*/
function openUserMess(){
	var url = "system/user/UserMess.jsp?id="+uid;
	$.ligerDialog.open({
			title:"用户详细信息",allowClose:true,
		    url: url, width:700, height:480, 
		    modal: true, isResize: false ,
		    isHidden:false
		    });
	return false;
}
	    
/**
用户退出系统
*/
function userSignOut(){
  var userOutFlag = false;
  $.ligerDialog.confirm("确定退出系统？", "提示", function (ok) {
		if (ok) {
			document.location.replace("login.jsp");
		}
	});
	return false;
}