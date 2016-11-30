$(function () {
  $('#datepicker').datepicker({
	  format: 'yyyy-mm-dd',
      weekStart: 1,
      autoclose: true,
      todayBtn: 'linked',
      language: 'cn'
    });
  var  setSelectCss="sex,department,job,role,post,area,dataCenter";
  for(var i=0;i<setSelectCss.split(",").length;i++){
	  $('#'+setSelectCss.split(",")[i]).selectpicker({
	      'selectedText': 'cat',
	      'noneSelectedText':'请选择'
	     
	  });  
  }
 
  $("#sex").html('<option value="M">男</option><option value="F">女</option>');
  $('#sex').selectpicker('refresh');
  $.ajax({
		url:"cropDeptQuery", 
		dataType: 'json',
		type:"post",
		success:function (data) {  
	     var selectOption="";
	       for(var i=0;i<data.length;i++){
	    	   selectOption=selectOption+'<option value="'+data[i].id+'">'+data[i].text+'</option>';	       
	       }
	       $("#department").html(selectOption);
	       $('#department').selectpicker('refresh');
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
		  var selectOption="";
		  for(var i=0;i<data.length;i++){
			    selectOption=selectOption+'<option value="'+data[i].id+'">'+data[i].text+'</option>';
		    
	    };
	          $("#dataCenter").html(selectOption);
	          $('#dataCenter').selectpicker('refresh');
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
    		      var selectOption="";
    		       for(var i=0;i<data.length;i++){
    		    	   selectOption=selectOption+'<option value="'+data[i].id+'">'+data[i].text+'</option>';
    		       }  
    		       $("#"+id).html(selectOption);      
    		       $('#'+id).selectpicker('refresh');
    		      
    			}, 
    			error:function (error) {
    				 window.parent.$("#tipContent").html("获取信息失败！");
    				 window.parent.$("#myModal").modal('show'); 
    			}
    		});
     }
  });
 function submit(){
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
 if(loginUser!=""&&userName!=""&&userPassword!=""&&confirmPwd!=""&&sex!=""&&datepicker!=""&&department!=""&&role!=null&&job!=null&&post!=""&&area!=""&&dataCenter!=""){
	 var loginnameCheck=getloginname(loginUser);
	 var userPasswordCheck=getpassword(userPassword);
	 var userPasswordCheck=getpasswordqr(userPassword,confirmPwd);
	 var PersonMailCheck=getemailpublic(PersonMail);
	 var PersonPhoneCheck=getmobilepublic(PersonPhone); 
	 var datepickerCheck=checkdate(datepicker);
	 var companyPhoneCheck=true;
	 var companyMailCHeck=true;
	 var roles=""
		 for(var i=0;i<role.length;i++){
			 if(roles!=""){
				 roles=roles+";"+role[i];  
			 }else{
				 roles=role[i];
			 }
			
		 }
	 var jobs=""
		 for(var i=0;i<job.length;i++){
			 if(jobs!=""){
				 jobs=jobs+";"+job[i];  
			 }else{
				 jobs=job[i];
			 }
			
		 }
	 if(companyMobPhone!=""){
		 companyPhoneCheck=getmobilepublic(companyMobPhone);  
		 }
	 if(companyMail!=""){
		 companyMailCHeck=getemailpublic(companyMail);	
	 }
	 if(loginnameCheck==true&&userPasswordCheck==true&&userPasswordCheck==true&&PersonMailCheck==true&&PersonPhoneCheck==true&&datepickerCheck==true&&datepickerCheck==true&&companyPhoneCheck==true&&companyMailCHeck==true){
		 var data={
				 loginname: loginUser,
				 username: userName,
				 userpass: userPassword,
				 sex: sex,
				 birth: datepicker,
				 deptid: department,
				 roleid: roles,
				 stationid: jobs,
				 postid: post,
				 mobilepublic: companyMobPhone,
				 phonepublic: companyPhone,
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
					 window.parent.$("#tipContent").html("添加用户信息失败！");
					 window.parent.$("#myModal").modal('show'); 
				}else{				
					 window.parent.$("#tipContent").html("添加用户信息成功！");
					 window.parent.$("#myModal").modal('show');
					 window.parent.$("#editUser").modal('hide'); 
					 window.parent.location.reload();
				}
			}, 
			error:function (error) {
				 window.parent.$("#tipContent").html("添加用户信息失败！");
				 window.parent.$("#myModal").modal('show');
		}});
	
 }
}else{
	 window.parent.$("#tipContent").html("请确认必填项已都填写！");
	 window.parent.$("#myModal").modal('show'); 
}	
} 
 
 /**登录名验证*/
 function getloginname(loginname){
 	var datas = {"loginname":loginname};
 	var result=true;
    	$.ajax({
			url:"treeuserExist.action", 
			data:datas, 
			async:false, 
			type:"post",
			success:function (mm) {
				if(mm == "EXIST"){
					 window.parent.$("#tipContent").html("该登录名已经存在，请更换登录名称！");
					 window.parent.$("#myModal").modal('show');
					 result=false;
				}
			}, 
			error:function (error) {
				result=false;
				 window.parent.$("#tipContent").html("该登录名已存在！");
				 window.parent.$("#myModal").modal('show');
				
		}});
    	return result;
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
