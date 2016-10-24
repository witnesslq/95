$(function () {
$('#datepicker').datepicker({
		  format: 'yyyy-mm-dd',
	      weekStart: 1,
	      autoclose: true,
	      todayBtn: 'linked',
	      language: 'cn'
	    });
 $.ajax({
		url:"cropDeptQuery", 
		dataType: 'json',
		type:"post",
		success:function (data) {
	        $("#department").append('<option>'+''+'</option>');
	       for(var i=0;i<data.length;i++){
		       $("#department").append('<option value="'+data[i].id+'">'+data[i].text+'</option>');
	       }
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取信息失败！");
			 window.parent.$("#myModal").modal('show'); 
		}
	});
  $.ajax({
	    url:"dcidTreeQuery.action",
		dataType: 'json',
		type:"post",
		success:function (data) {
	  $("#dataCenter").append('<option>'+''+'</option>');
	  for(var i=0;i<data.length;i++){
	       $("#dataCenter").append('<option value="'+data[i].id+'">'+data[i].text+'</option>');
    };
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取信息失败！");
			 window.parent.$("#myModal").modal('show'); 
		}
	});
    $("#department").change(function(){
    	 var topcorpid= $("#department").val();
    	 
    	 var result="";
    	  $.ajax({
  			url:"QueryTopCorpId", 
  			data:"corpid="+topcorpid,
  			type:"post",
  			success:function (data) {
  		    result=data;
  		    getinfo("QueryRoleByDept","role",result);
      	    getinfo("QueryStationByDept","job",result);
      	    getinfo("QueryPostByDept","post",result);
      	    getinfo("QueryAreaByDept","area",result);
  			}, 
  			error:function (error) {

  			}
  		});    
    });
     function getinfo(url,id,topcorpid){
    	 $("#"+id).html("");
    	  $.ajax({
    			url:url, 
    			dataType: 'json',
    			data:"corpid="+topcorpid,
    			type:"post",
    			success:function (data) {
    		        $("#"+id).append('<option>'+''+'</option>');
    		       for(var i=0;i<data.length;i++){
    			       $("#"+id).append('<option value="'+data[i].id+'">'+data[i].text+'</option>');
    		       }
    			}, 
    			error:function (error) {
    				 window.parent.$("#tipContent").html("获取信息失败！");
    				 window.parent.$("#myModal").modal('show');
    			}
    		});
     }
});
function loadInfo(id){
	$.ajax({
		url:"treeuserGetById.action", 
		data:"id="+id, 
		dataType:"json", 
	    async:false,
		type:"post",
		success:function (mm) {	
		    $("#user_id").val(mm.id);
	        $("#loginUser").val(mm.loginname);
	        $("#userName").val(mm.username);
	        $("#sex option[value='"+mm.sex+"']").attr("selected","selected");
	        $("#datepicker").val(getFormatDate(mm.birth));  
	        $("#department").val(mm.deptid);
	        $("#role").append('<option value="'+mm.roleid+'">'+mm.rolename+'</option>');
	        $("#job").append('<option value="'+mm.stationid+'">'+mm.stationname+'</option>');
	        $("#post").append('<option value="'+mm.postid+'">'+mm.postname+'</option>');
	        $("#companyPhone").val(mm.phonepublic);
	        $("#companyMobPhone").val(mm.mobilepublic);
	        $("#homePhone").val(mm.phoneprivate);
	        $("#PersonPhone").val(mm.mobileprivate);
	        $("#companyMail").val(mm.emailpublic);
	        $("#PersonMail").val(mm.emailprivate);
	        $("#description").val(mm.remark);
	        $("#area").append('<option value="'+mm.area+'">'+mm.areaname+'</option>');
	        $("#dataCenter").val(mm.dcid);
	        window.parent.$("#editUser").modal('show');
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取单个信息失败！");
			 window.parent.$("#myModal").modal('show');
	}});
	}	
function submit(){
	 var user_id=$("#user_id").val();
	 var loginUser=$("#loginUser").val();
	 var userName=$("#userName").val();
	 var userPassword=$("#userPassword").val();
	 var confirmPwd=$("#confirmPwd").val();
	 var sex=$("#sex").val();
	 var datepicker=$("#datepicker").val();  
	 var department=$("#department").val();
	 var role=$("#role").val();
	 var job=$("#job").val();
	 var post=$("#post").val();
	 var companyPhone=$("#companyPhone").val();
	 var companyMobPhone=$("#companyMobPhone").val();
	 var homePhone=$("#homePhone").val();
	 var PersonPhone=$("#PersonPhone").val();
	 var companyMail=$("#companyMail").val();
	 var PersonMail=$("#PersonMail").val();
	 var description=$("#description").val();
	 var area=$("#area").val();
	 var dataCenter=$("#dataCenter").val();
	 if(loginUser!=""&&userName!=""&&userPassword!=""&&confirmPwd!=""&&sex!=""&&datepicker!=""&&department!=""&&role!=""&&job!=""&&post!=""&&area!=""&&dataCenter!=""){
	 var userPasswordCheck=getpassword(userPassword);
	 var userPasswordCheck=getpasswordqr(userPassword,confirmPwd);
	 var PersonMailCheck=getemailpublic(PersonMail);
	 var PersonPhoneCheck=getmobilepublic(PersonPhone); 
	 var datepickerCheck=checkdate(datepicker);
	 var companyPhoneCheck=true;
	 var companyMailCHeck=true;
	 if(companyPhone!=""){
		 companyPhoneCheck=getmobilepublic(companyPhone);  
		 }
	 if(companyMail!=""){
		 companyMailCHeck=getemailpublic(companyMail);	
	 }
	 if(userPasswordCheck==true&&userPasswordCheck==true&&PersonMailCheck==true&&PersonPhoneCheck==true&&datepickerCheck==true&&datepickerCheck==true&&companyPhoneCheck==true&&companyMailCHeck==true){
		 var data={
				     id:user_id,
					 loginname: loginUser,
					 username: userName,
					 userpass: userPassword,
					 sex: sex,
					 birth: datepicker,
					 deptid: department,
					 roleid: role,
					 stationid: job,
					 postid: post,
					 mobilepublic: companyPhone,
					 phonepublic: companyMobPhone,
					 mobileprivate: PersonPhone,
					 phoneprivate: homePhone,
					 emailpublic: companyMail,
					 emailprivate: PersonMail,
					 remark: description,
					 area: area,
					 dcid: dataCenter
		 };
		 
		 $.ajax({
				url:"treeAdduser.action", 
				data:data,
				dataType:"json",
				async:false, 
				type:"post",
				success:function (mm) {
					if("error"==mm.result){
						 window.parent.$("#tipContent").html("修改用户信息失败！");
						 window.parent.$("#myModal").modal('show');
					}else{	
						 window.parent.$("#tipContent").html("修改用户信息成功！");
						 window.parent.$("#myModal").modal('show');
						 window.parent.$("#editUser").modal('hide'); 
						 window.parent.location.reload();
					}
				}, 
				error:function (error) {
					 window.parent.$("#tipContent").html("修改用户信息失败！");
					 window.parent.$("#myModal").modal('show');
			}});
	 }
}else{
		 window.parent.$("#tipContent").html("请确认必填项已都填写！");
		 window.parent.$("#myModal").modal('show'); 
}	
} 

/**密码验证*/
function getpassword(userpass){
   	var str = /[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/;
   	if(!str.test(userpass)){
   		 window.parent.$("#tipContent").html("密码必须且只许包含字母和数字！");
			 window.parent.$("#myModal").modal('show');
			 return false;
   	}
   	 return true;
}
/**确认密码验证*/
function getpasswordqr(userpass,passwordqr){
	if(userpass!=passwordqr){
		 window.parent.$("#tipContent").html("确认密码与密码输入不同！");
		 window.parent.$("#myModal").modal('show');
		 return false;
	}
	 return true;
}
/**手机号码验证*/
function getmobilepublic(mobilepublic){
	if(!checkPhoneNum(mobilepublic)){
		 window.parent.$("#tipContent").html("请正确输入手机号！");
		 window.parent.$("#myModal").modal('show');
		 return false;
	}
	return true;
}
/**邮箱地址验证*/
function getemailpublic(emailpublic){
	if(!checkEmail(emailpublic)){
		 window.parent.$("#tipContent").html("请正确输入邮箱地址！");
		 window.parent.$("#myModal").modal('show');
		 return false;
	}
	return true;
}

/**手机验证*/
function checkPhoneNum(value){
		var tel=/^1[3|5|8][0-9]\d{8}$/;
		if(!tel.test(value)){
			return false;
		}
		return true;
	}

	/**验证邮箱*/
	function checkEmail(value){
		var email = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if(!email.test(value)){
			return false;
		}
		return true;
	}
	/**出生年月日*/
	function checkdate(value){
		var bdate = value;
		var currentDate = getFormatDate(new Date());
	   	if(bdate > currentDate){
   		 window.parent.$("#tipContent").html("出生年月不能大于今天！");
   		 window.parent.$("#myModal").modal('show');
   		 return false;
	   	}
	   	return true;
}
