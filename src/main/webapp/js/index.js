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
/*  
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



 获取原来的密码

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
